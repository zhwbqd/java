package zhwb.ldap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import netscape.ldap.LDAPAttribute;
import netscape.ldap.LDAPConnection;
import netscape.ldap.LDAPEntry;
import netscape.ldap.LDAPException;
import netscape.ldap.LDAPSearchConstraints;
import netscape.ldap.factory.JSSESocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AuthenticationManager
{
    /** Logger */
    private static final Logger SBSLOGGER = LoggerFactory.getLogger(AuthenticationManager.class);

    /** Default LDAP Host */
    private static final String DEFAULT_LDAP_HOST = "ldap.org.com";

    /** Default LDAP Port */
    private static final String DEFAULT_LDAP_PORT = "port";

    /** Property Name for LDAP Host */
    private static final String LDAP_HOST = "ldap_host_name";

    /** Property Name for LDAP Port */
    private static final String LDAP_PORT = "ldap_port_num";

    /** LDAP Version */
    private static final int LDAP_VERSION = 3;

    /** Connection Host Name */
    private static String host;

    /** Connection Port */
    private static int port;

    /** Connection Socket Factory */
    private static JSSESocketFactory jsseFactory;

    /** Default Connection Timeout */
    private static final int CONNECTION_TIMEOUT = 1800;

    /** Default Time Period for Caching (4hrs) */
    private static final int CACHE_DURATION = 1000 * 60 * 60 * 4;

    /** LDAP Distinguished Name */
    private static final String LDAP_SEARCH_CN = "cn=%s,%s";

    /** LDAP Distinguished Name */
    private static final String LDAP_SEARCH_UID = "uid=%s,%s";

    /** LDAP Descriptor */
    private static final String LDAP_APPLICATION = "ou=Applications,o=org.com";

    /** LDAP Descriptor */
    private static final String LDAP_GROUP = "ou=Groups,o=org.com";

    /** LDAP Attribute */
    private static final String LDAP_ATTRIBUTE_MEMBER = "member";

    /** Authentication Message: Invalid Credentials */
    private static final String AUTH_INVALID_CREDENTIALS = "Could not authenticate credentials. Invalid id and/or password.";

    /** Authentication Message: Missing Credentials */
    private static final String AUTH_MISSING_CREDENTIALS = "Could not authenticate credentials. Missing id and/or password.";

    /** Authentication Message: Prefix */
    private static final String AUTHENTICATION_LOOKUP_PREFIX = "Application authentication against: ";

    /** Authorization Message: Prefix */
    private static final String AUTHORIZATION_LOOKUP_PREFIX = "Application authorization against: ";

    /** Message: Cache Success */
    private static final String CACHE_SUCCESS = "Cache (SUCCESS)";

    /** Message: Cache Failure */
    private static final String CACHE_FAILURE = "Cache (FAILURE)";

    /** Message: Enterprise Directory Success */
    private static final String ENTERPRISE_SUCCESS = "Enterprise Directory (SUCCESS)";

    /** Message: Enterprise Directory Failure */
    private static final String ENTERPRISE_FAILURE = "Enterprise Directory (FAILURE)";

    /** Message: LDAP Exception */
    private static final String LDAP_EXCEPTION_PREFIX = "LDAP Exception: ";

    /** Authentication Cache - Stores authenticated IDs for a set period of time */
    private static CoreTimeBasedMap authenticationCache = new CoreTimeBasedMap(CACHE_DURATION);

    /** Permission Cache - Stores authorized IDs for a set period of time */
    private static CoreTimeBasedMap authorizationCache = new CoreTimeBasedMap(CACHE_DURATION);

    /**
     * Static Initializer
     */
    static
    {
        // Acquire the Enterprise Directory properties
        Properties edProperties = new Properties();
        InputStream fis = null;
        try
        {
            // Open an inputstream to the properties file
            fis = AuthenticationManager.class.getResourceAsStream("/entdir.properties");
            if (fis != null)
            {
                // Load the host and port properties from the file for the LDAP
                edProperties.load(fis);
                host = edProperties.getProperty(LDAP_HOST, DEFAULT_LDAP_HOST);
                port = Integer.parseInt(edProperties.getProperty(LDAP_PORT, DEFAULT_LDAP_PORT));
            }
            else
            {
                // Handle missing properties
                SBSLOGGER.error("Enterprise Directory Properties missing (entdir.properties). Halting");
                throw new IllegalStateException();
            }
        }
        catch (IOException ioe)
        {
            // Handle issues reading the properties file
            SBSLOGGER.error("Error while reading entdir.properties file - " + ioe);
            throw new CoreServiceException("Error while reading entdir.properties file - " + ioe, ioe);
        }
        catch (Exception e)
        {
            // Handle issues reading the properties file
            SBSLOGGER.error("Error initializing in static block - " + e);
            throw new CoreServiceException("Error initializing in static block - " + e, e);
        }
        finally
        {
            try
            {
                // Ensure the inputstream is closed
                fis.close();
            }
            catch (IOException e)
            {
                SBSLOGGER.error(e.getMessage(), e);
            }
        }

        // Create a socket factory
        jsseFactory = new JSSESocketFactory(null);

        try
        {
            // Create a socket to the LDAP
            jsseFactory.makeSocket(host, port);
        }
        catch (LDAPException ex)
        {
            SBSLOGGER.error("Error while creating ssl socket to Enterprise directory - " + ex, ex);
            throw new CoreServiceException("Error while creating ssl socket to Enterprise directory - " + ex, ex);
        }
    }

    /**
     * Private Constructor
     */
    private AuthenticationManager()
    {
        // Do Nothing
    }

    /**
     * Authenticates User Credentials
     * @param id User ID
     * @param password User Password
     * @return boolean Whether Authentication Succeeded
     * @throws AuthenticationException on any error
     * 
     */
    public static boolean authenticateUser(final String id, final String password)
        throws AuthenticationException
    {
        // Track whether authenticated
        boolean authenticated = false;

        // Force to uppercase since case insensitive
        String uppercaseID = id.toUpperCase(Locale.US);

        // Format into a Distinguished Name recognized by the Enterprise Directory
        String accountDN = String.format(LDAP_SEARCH_UID, uppercaseID, LDAP_APPLICATION);

        // Attempt to authenticate against the cache
        authenticated = authenticateAgainstCache(accountDN, password);

        // If not found in cache, try the enterprise directory
        if (!authenticated)
        {
            authenticated = authenticateAgainstEnterpriseDirectory(accountDN, password);
        }

        // Return whether the user is authenticated
        return authenticated;
    }

    /**
     * Authenticates Application Credentials against the Enterprise Directory
     * @param id Application Id to Authenticate
     * @param password Application Password to Authenticate
     * @return boolean Whether Authentication Succeeded
     * @throws AuthenticationException on any error
     */
    public static boolean authenticateApplication(final String id, final String password)
        throws AuthenticationException
    {
        // Track whether authenticated
        boolean authenticated = false;

        // Force to uppercase since case insensitive
        String uppercaseID = id.toUpperCase(Locale.US);

        // Format into a Distinguished Name recognized by the Enterprise Directory
        String accountDN = String.format(LDAP_SEARCH_CN, uppercaseID, LDAP_APPLICATION);

        // Attempt to authenticate against the cache
        authenticated = authenticateAgainstCache(accountDN, password);

        // If not found in cache, try the enterprise directory
        if (!authenticated)
        {
            authenticated = authenticateAgainstEnterpriseDirectory(accountDN, password);
        }

        // Return whether the application is authenticated
        return authenticated;
    }

    /**
     * Authorizes an Application based on memberships in Enterprise Directory
     * @param id Application Id
     * @param password Application Password
     * @param groups LDAP Access Control Groups
     * @return boolean Whether Authorized
     * @throws AuthenticationException on any error
     */
    public static boolean authorizeApplication(final String id, final String password, final String[] groups)
        throws AuthenticationException
    {
        // Ensure non null values were provided before authorization occurs
        if (id == null || password == null)
        {
            SBSLOGGER.error(AUTH_MISSING_CREDENTIALS);
            throw new AuthenticationException(AUTH_MISSING_CREDENTIALS);
        }

        // Track whether the application is authorized
        boolean authorized = false;

        // Force to uppercase since case insensitive
        String uppercaseID = id.toUpperCase(Locale.US);

        // Format into a Distinguished Name recognized by the Enterprise Directory
        String accountDN = String.format(LDAP_SEARCH_CN, uppercaseID, LDAP_APPLICATION);

        // Attempt to authorize the application against the local cache
        for (int x = 0; !authorized && x < groups.length; x++)
        {
            // Force to uppercase since case insensitive
            String uppercaseGroupID = groups[x].toUpperCase(Locale.US);

            // Format into a Distinguished Name recognized by the Enterprise Directory
            String groupDN = String.format(LDAP_SEARCH_CN, uppercaseGroupID, LDAP_GROUP);

            // Authorize against the cache
            authorized = authorizeAgainstCache(accountDN, groupDN);
        }

        // If authorized by cache, still need to authenticate the application
        if (authorized)
        {
            return authenticateApplication(id, password);
        }

        // After attempting the cache, try the enterprise directory
        LDAPConnection connection = null;
        try
        {
            // Acquire a connection to the LDAP Server
            connection = getLdapConnection(host, port);

            // Set connection properties
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            LDAPSearchConstraints constr = new LDAPSearchConstraints();
            constr.setMaxResults(0);
            constr.setServerTimeLimit(0);

            // Connect and bind to the directory as application account
            connection.connect(host, port);
            connection.bind(LDAP_VERSION, accountDN, password);
            connection.connect(LDAP_VERSION, host, port, accountDN, password);

            // Place it into the cache.
            authenticationCache.put(accountDN, password);

            // Note that the authentication succeeded
            SBSLOGGER.info(AUTHENTICATION_LOOKUP_PREFIX + ENTERPRISE_SUCCESS);

            // If authorization against the cache was unsuccessful, then try the enterprise directory
            // We waited due to the cost of establishing connections
            for (int x = 0; !authorized && x < groups.length; x++)
            {
                // Force to uppercase since case insensitive
                String uppercaseGroupID = groups[x].toUpperCase(Locale.US);

                // Format into a Distinguished Name recognized by the Enterprise Directory
                String groupDN = String.format(LDAP_SEARCH_CN, uppercaseGroupID, LDAP_GROUP);

                // Authorize against the Enterprise Directory
                authorized = authorizeAgainstEnterpriseDirectory(connection, accountDN, groupDN);
            }
        }
        catch (LDAPException le)
        {
            // Handle any exceptions
            processLDAPException(le, null);
        }

        try
        {
            // Close the connection
            closeLdapConnection(connection);
        }
        catch (LDAPException le)
        {
            // Handle any authorization errors
            SBSLOGGER.error(LDAP_EXCEPTION_PREFIX + le);
        }

        // Otherwise, return false
        return authorized;
    }



    /**
     * Authenticates Credentials against the Cache
     * @param dn Distinguished Name
     * @param password Password
     * @return boolean Whether Authentication Succeeded
     * @throws AuthenticationException on any error
     */
    private static boolean authenticateAgainstCache(final String dn, final String password)
        throws AuthenticationException
    {
        // Ensure non null values were provided before processing occurs
        if (dn == null || password == null)
        {
            SBSLOGGER.error(AUTH_MISSING_CREDENTIALS);
            throw new AuthenticationException(AUTH_MISSING_CREDENTIALS);
        }

        // Check the cache first for whether the credentials are valid
        if (authenticationCache.get(dn) != null)
        {
            if (authenticationCache.get(dn).equals(password))
            {
                // Cache hit and matching password...
                SBSLOGGER.info(AUTHENTICATION_LOOKUP_PREFIX + CACHE_SUCCESS);
                return true;
            }

            // If the passwords do not match, clear the entry from the cache
            authenticationCache.remove(dn);
        }

        // Otherwise note the failure and continue
        SBSLOGGER.debug(AUTHENTICATION_LOOKUP_PREFIX + CACHE_FAILURE);

        // An LDAPException will be thrown if cannot be authenticated
        return false;
    }

    /**
     * Authenticates Credentials against the Enterprise Directory
     * @param dn Distinguished Name
     * @param password Password
     * @return boolean Whether Authentication Succeeded
     * @throws AuthenticationException on any error
     */
    private static boolean authenticateAgainstEnterpriseDirectory(final String dn, final String password)
        throws AuthenticationException
    {
        // Ensure non null values were provided before processing occurs
        if (dn == null || password == null)
        {
            SBSLOGGER.error(AUTH_MISSING_CREDENTIALS);
            throw new AuthenticationException(AUTH_MISSING_CREDENTIALS);
        }

        try
        {
            // Check if authenticated
            boolean authFlag = authenticate(dn, password);
            if (authFlag)
            {
                // Good match, put it in the cache.
                SBSLOGGER.info(AUTHENTICATION_LOOKUP_PREFIX + ENTERPRISE_SUCCESS);
                authenticationCache.put(dn, password);
                return true;
            }
        }
        catch (LDAPException le)
        {
            // Handle any exceptions
            processLDAPException(le, null);
        }

        // Otherwise, do not put in the cache
        SBSLOGGER.info(AUTHENTICATION_LOOKUP_PREFIX + ENTERPRISE_FAILURE);

        // An LDAPException will be thrown if cannot be authenticated
        return false;
    }

    /**
     * Authenticate against Enterprise Directory
     * @param dn Distinguished Name
     * @param password Password
     * @return boolean Whether Authentication Succeeded
     * @throws LDAPException on any error
     */
    private static boolean authenticate(final String dn, final String password)
        throws LDAPException
    {
        // Acquire a connection to the LDAP for an authentication check
        LDAPConnection connection = getLdapConnection(host, port);

        // Set connection properties
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        LDAPSearchConstraints constr = new LDAPSearchConstraints();
        constr.setMaxResults(0);
        constr.setServerTimeLimit(0);

        // Connect and bind to the directory for authentication purposes
        connection.connect(host, port);
        connection.bind(LDAP_VERSION, dn, password);
        connection.connect(LDAP_VERSION, host, port, dn, password);

        // Close the connection
        closeLdapConnection(connection);

        // Otherwise, success
        return true;
    }

    /**
     * Authorizes Credentials against the Cache
     * @param memberDN Member Distinguished Name
     * @param groupDN Group Distinguished Name
     * @return boolean Whether Authentication Succeeded
     * @throws AuthenticationException on any error
     */
    private static boolean authorizeAgainstCache(final String memberDN, final String groupDN)
        throws AuthenticationException
    {
        // Ensure non null values were provided before authorization occurs
        if (memberDN == null || groupDN == null)
        {
            SBSLOGGER.error(AUTH_MISSING_CREDENTIALS);
            throw new AuthenticationException(AUTH_MISSING_CREDENTIALS);
        }

        // Check the cache first for whether authorized
        if (authorizationCache.get(new GroupMembership(memberDN, groupDN)) != null)
        {
            // Cache hit and matching group...
            SBSLOGGER.info(AUTHORIZATION_LOOKUP_PREFIX + CACHE_SUCCESS);
            return true;
        }

        // Otherwise note the failure and continue
        SBSLOGGER.debug(AUTHORIZATION_LOOKUP_PREFIX + CACHE_FAILURE);

        // Otherwise, return false
        return false;
    }

    /**
     * Authorizes Credentials against the Enterprise Directory
     * @param connection LDAP Connection
     * @param memberDN Member Distinguished Name
     * @param groupDN Group Distinguished Name
     * @return boolean Whether Authentication Succeeded
     * @throws AuthenticationException on any error
     */
    private static boolean authorizeAgainstEnterpriseDirectory(final LDAPConnection connection, final String memberDN,
            final String groupDN)
        throws AuthenticationException
    {
        // Ensure non null values were provided before authorization occurs
        if (memberDN == null || groupDN == null)
        {
            SBSLOGGER.error(AUTH_MISSING_CREDENTIALS);
            throw new AuthenticationException(AUTH_MISSING_CREDENTIALS);
        }

        try
        {
            boolean authFlag = authorize(connection, memberDN, groupDN);
            if (authFlag)
            {
                // If authorization succeeded, put in the cache
                SBSLOGGER.info(AUTHORIZATION_LOOKUP_PREFIX + ENTERPRISE_SUCCESS + " (" + groupDN + ')');
                authorizationCache.put(new GroupMembership(memberDN, groupDN), groupDN);
                return true;
            }

            // Otherwise, do not put in the cache
            SBSLOGGER.debug(AUTHORIZATION_LOOKUP_PREFIX + ENTERPRISE_FAILURE + " (" + groupDN + ')');
        }
        catch (LDAPException le)
        {
            // Handle any exceptions
            processLDAPException(le, groupDN);
        }

        // An LDAPException will be thrown if cannot be authorized
        return false;
    }

    /**
     * Authorize against Enterprise Directory
     * @param connection LDAP Connection
     * @param memberDN Member Distinguished Name
     * @param groupDN Group Distinguished Name
     * @return boolean Whether Authentication Succeeded
     * @throws LDAPException on any error
     * @throws AuthenticationException on any error
     */
    private static boolean authorize(final LDAPConnection connection, final String memberDN, final String groupDN)
        throws LDAPException, AuthenticationException
    {
        // Track whether membership is found in the permission group
        boolean isMember = false;
        String[] members = null;

        // Acquire the permission group
        LDAPEntry groupEntry = connection.read(groupDN);
        if (groupEntry != null)
        {
            // Acquire the attribute "member" on the permission group
            LDAPAttribute membersEntry = groupEntry.getAttribute(LDAP_ATTRIBUTE_MEMBER);
            if (membersEntry != null)
            {
                members = membersEntry.getStringValueArray();
            }
        }

        // Check whether the application is a member of the group
        for (int x = 0; members != null && x < members.length && !isMember; x++)
        {
            // Check whether the member is the application
            if (members[x].equalsIgnoreCase(memberDN))
            {
                // Mark and stop looping
                isMember = true;
            }
        }

        // Return whether the account is a member
        return isMember;
    }

    /**
     * Creates and returns LDAP Connection
     * @param ldaphost LDAP Host
     * @param ldapport LDAP Port
     * @return LDAP Connection
     * @throws LDAPException Issue Connecting to LDAP Server
     */
    private static LDAPConnection getLdapConnection(final String ldaphost, final int ldapport)
        throws LDAPException
    {
        // Create a new connection
        LDAPConnection connection = new LDAPConnection(jsseFactory);
        return connection;
    }

    /**
     * Closes LDAP Connection
     * @param connection LDAP Connection
     * @throws LDAPException Issue Connecting to LDAP Server
     */
    private static void closeLdapConnection(final LDAPConnection connection)
        throws LDAPException
    {
        // Close the connection if necessary
        if (connection != null)
        {
            connection.disconnect();
        }
    }

    /**
     * Process LDAP Exception
     * @param le LDAP Exception
     * @param group Access Control Group
     * @throws AuthenticationException Adjusted Exception
     */
    private static void processLDAPException(final LDAPException le, final String group)
        throws AuthenticationException
    {
        // Handle based on whether authorization or authentication
        if (group == null)
        {
            // Note that the authentication succeeded
            SBSLOGGER.error(AUTHENTICATION_LOOKUP_PREFIX + ENTERPRISE_FAILURE);
        }
        else
        {
            // Otherwise, do not put in the cache
            SBSLOGGER.debug(AUTHORIZATION_LOOKUP_PREFIX + ENTERPRISE_FAILURE + " (" + group + ')');
        }

        // Handle any authorization errors
        switch (le.getLDAPResultCode())
        {
            case LDAPException.INVALID_CREDENTIALS:
                // Modify the message for this known exception
                throw new AuthenticationException(AUTH_INVALID_CREDENTIALS, le);
            case LDAPException.NO_SUCH_OBJECT:
                // Ignore the exception and simply return false
                // Non-existing LDAP Groups should be handled gracefully
                SBSLOGGER.error("Application Group does not exist: " + le);
                break;
            default:
                // Log the exception
                SBSLOGGER.error(LDAP_EXCEPTION_PREFIX + le.getLDAPErrorMessage());

                // Throw an authentication exception with the error
                throw new AuthenticationException(String.format("LDAP Error code %s:%s", le.getLDAPResultCode(),
                        le.getLDAPErrorMessage()), le);
        }
    }
}
