package zhwb.ldap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TrustStoreManager
{
	/** Logger */
	private static final Logger SBSLOGGER = LoggerFactory.getLogger( TrustStoreManager.class );

	/** Property for Trust Store Password */
	private static final String TRUST_STORE_PASSWORD = "trust_store_password";

	/** Property for Trust Store Name */
	private static final String TRUST_STORE_NAME = "trust_store_name";

	/** Trust Store Name */
	private static String trustStoreName;

	/** Trust Store Location */
	private static String trustStoreLocation;

	/** Trust Store Password */
	private static String trustStorePassword;
	
	/**
	 * Default Constructor.
	 */
	private TrustStoreManager()
	{
		// Default Constructor. Do nothing right now.
	}

	/**
	 * Initialize the Trust Store
	 */
	public static void initializeTrustStore()
	{
		// Only load if not already set
		if ( trustStoreLocation == null || trustStorePassword == null ) {
			loadTrustStoreProperties();
		}

		// Set the necessary system properties
		System.setProperty( "java.naming.ldap.factory.socket", "javax.net.ssl.SSLSocketFactory" );
		if ( trustStoreLocation != null && trustStorePassword != null ) {
			System.setProperty( "javax.net.ssl.trustStore", trustStoreLocation );
			System.setProperty( "javax.net.ssl.trustStorePassword", trustStorePassword );
		}
	}

	/**
	 * Load Trust Store Properties
	 */
	private static synchronized void loadTrustStoreProperties()
	{
		// Acquire system properties
		Properties props = System.getProperties();

		// Check if disable flag is set
		Boolean keystoreDisable = false;
		if ( props.get( "sbs.core.keystore.disable" ) != null ) {
			// If disabled, do not set the system properties
			keystoreDisable = Boolean.valueOf( (String) props.get( "sbs.core.keystore.disable" ) );
			if ( keystoreDisable ) {
				// Set the necessary system properties and return
				System.setProperty( "java.naming.ldap.factory.socket", "javax.net.ssl.SSLSocketFactory" );
				trustStoreLocation = System.getProperty( "javax.net.ssl.trustStore" );
				trustStorePassword = System.getProperty( "javax.net.ssl.trustStorePassword" );
				return;
			}
		}

		// Acquire the Enterprise Directory properties
		Properties edProperties = new Properties();
		InputStream fis = null;
		try {
			// Load from a properties file delivered with the deployment
			fis = AuthenticationManager.class.getResourceAsStream( "/entdir.properties" );
			edProperties.load( fis );

			// Acquire the trust store name and password
			trustStoreName = edProperties.getProperty( TRUST_STORE_NAME );
			trustStorePassword = edProperties.getProperty( TRUST_STORE_PASSWORD );
			trustStoreLocation = System.getProperty( "user.home" ) + File.separator + "certs" + File.separator + trustStoreName;
		} catch ( IOException ioe ) {
			SBSLOGGER.error( "Error while reading entdir.properties file - " + ioe );
			throw new CoreServiceException( "Error while reading entdir.properties file - " + ioe );
		} catch ( Exception e ) {
			SBSLOGGER.error( "Error initializing in static block - " + e );
			throw new CoreServiceException( "Error initializing in static block - " + e );
		} finally {
			try {
				fis.close();
			} catch ( IOException e ) {
				SBSLOGGER.error( e.getMessage() );
			}
		}
	}
}
