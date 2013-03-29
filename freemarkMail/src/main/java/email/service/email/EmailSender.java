/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package email.service.email;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.it.sbs.notification.service.util.LoggingUtility;

/**
 * The Class EmailSender.
 */
public final class EmailSender implements IEmailSender
{

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(EmailSender.class);

    /** The Constant DEFAULT_PROTOCOL. */
    private static final String DEFAULT_PROTOCOL = "smtp";

    /** The Constant CONTENT_TYPE_HTML. */
    private static final String CONTENT_TYPE_HTML = "text/html; charset=UTF-8";

    /** The Constant CONTENT_TYPE_TEXT. */
    private static final String CONTENT_TYPE_TEXT = "text/plain; charset=UTF-8";

    /** The Constant DEFAULT_PORT. */
    public static final int DEFAULT_PORT = -1;

    /** The java mail properties. */
    private Properties javaMailProperties = new Properties();

    /** The host. */
    private String host;

    /** The port. */
    private int port = DEFAULT_PORT;

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    /** The session. */
    private Session session;

    /**
     * Instantiates a new email sender.
     */
    public EmailSender()
    {
        super();
    }

    /**
     * Get the property javaMailProperties.
     *
     * @return the javaMailProperties
     */
    public Properties getJavaMailProperties()
    {
        return javaMailProperties;
    }

    /**
     * Set the property javaMailProperties.
     *
     * @param javaMailProperties the javaMailProperties to set
     */
    public void setJavaMailProperties(final Properties javaMailProperties)
    {
        this.javaMailProperties = javaMailProperties;
    }

    /**
     * Get the property host.
     *
     * @return the host
     */
    public String getHost()
    {
        return host;
    }

    /**
     * Set the property host.
     *
     * @param host the host to set
     */
    public void setHost(final String host)
    {
        this.host = host;
    }

    /**
     * Get the property port.
     *
     * @return the port
     */
    public int getPort()
    {
        return port;
    }

    /**
     * Set the property port.
     *
     * @param port the port to set
     */
    public void setPort(final int port)
    {
        this.port = port;
    }

    /**
     * Get the property username.
     *
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Set the property username.
     *
     * @param username the username to set
     */
    public void setUsername(final String username)
    {
        this.username = username;
    }

    /**
     * Get the property password.
     *
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Set the property password.
     *
     * @param password the password to set
     */
    public void setPassword(final String password)
    {
        this.password = password;
    }

    /**
     * Gets the session.
     *
     * @return the session
     */
    public synchronized Session getSession()
    {
        if (this.session == null)
        {
            this.session = Session.getInstance(this.javaMailProperties);
        }
        return this.session;
    }

    /** {@inheritDoc}
     *  @see email.service.email.IEmailSender#send(javax.mail.internet.MimeMessage[])
     */
    public EmailSendStatus send(final MimeMessage[] mimeMessages)
    {
        EmailSendStatus status = new EmailSendStatus();

        Transport transport = null;
        try
        {
            /*check connection before sending*/
            transport = session.getTransport(DEFAULT_PROTOCOL);
            transport.connect(getHost(), getPort(), getUsername(), getPassword());
        }
        catch (AuthenticationFailedException e)
        {
            LOG.error("Authentication Failed, Exception {}.", LoggingUtility.getStackTrace(e));
            status.setErrorMessage(LoggingUtility.getStackTrace(e));
            return status;
        }
        catch (MessagingException e)
        {
            LOG.error("Connect Mail Server Failed, Exception {}.", LoggingUtility.getStackTrace(e));
            status.setErrorMessage(LoggingUtility.getStackTrace(e));
            status.setNeedRetry(true);
            return status;
        }
        try
        {
            for (int i = 0; i < mimeMessages.length; i++)
            {
                MimeMessage mimeMessage = mimeMessages[i];
                try
                {
                    if (mimeMessage.getSentDate() == null)
                    {
                        mimeMessage.setSentDate(new Date());
                    }
                    /*save changes for each mimeMessage*/
                    mimeMessage.saveChanges();
                    transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                    for(Address addr : mimeMessage.getAllRecipients()){
                        status.addSuccessEmail(addr.toString());
                    }
                }
                catch (SendFailedException sfe)
                {
                    LOG.error("Send partial failed because of invalid addresses, Exception {}.",
                            LoggingUtility.getStackTrace(sfe));
                    handleException(sfe, status);
                }
                catch (MessagingException ex)
                {
                    LOG.error("Connection is dead or not in the connected state, Exception {}.", LoggingUtility.getStackTrace(ex));
                    status.setErrorMessage(LoggingUtility.getStackTrace(ex));
                    status.setNeedRetry(true);
                    /*Connection dead, need retry and return directly*/
                    return status;
                }
            }
        }
        finally
        {
            try
            {
                transport.close();
            }
            catch (MessagingException ex)
            {
                LOG.error("Failed to close server connection, Exception {}.", LoggingUtility.getStackTrace(ex));
            }
        }
        return status;
    }

    /**
     * Handle exception.
     *
     * @param mex the mex
     * @param status the status
     */
    private void handleException(final SendFailedException mex, final EmailSendStatus status)
    {
        status.setErrorMessage("Send partial failed because of invalid address.");

        Address[] validAddr = mex.getValidSentAddresses();
        if (validAddr != null)
        {
            for (Address address : validAddr)
            {
                status.addSuccessEmail(address.toString());
            }
        }

        Address[] invalidAddr = mex.getInvalidAddresses();
        if (invalidAddr != null)
        {
            for (Address address : invalidAddr)
            {
                status.addFailEmail(address.toString());
            }
        }
    }

    /** {@inheritDoc}
     *  @see email.service.email.IEmailSender#createMimeMessage(java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public MimeMessage createMimeMessage(final Map<String, RecipientType> recptEmailAddr, final String subjectText,
            final String fromAddr, final String emailText, final String replyTo)
        throws MessagingException
    {
        getSession().setDebug(LOG.isDebugEnabled());
        MimeMessage msg = new MimeMessage(getSession());

        /*set FROM addr*/
        if ((null != fromAddr) && (fromAddr.length() > 0))
        {
            msg.setFrom(new InternetAddress(fromAddr));
        }
        else
        {
            msg.setFrom();
        }

        /*set ReplyTo addr*/
        if ((null != replyTo) && (replyTo.length() > 0))
        {
            msg.setReplyTo(InternetAddress.parse(replyTo, true));
        }

        /*set subject*/
        msg.setSubject(subjectText);

        /*set email body*/
        if (emailText.toLowerCase().contains("<html>"))
        {
            msg.setContent(emailText, CONTENT_TYPE_HTML);
        }
        else
        {
            msg.setContent(emailText, CONTENT_TYPE_TEXT);
        }

        /*set recipient*/
        for (Entry<String, RecipientType> recipientEmailAddr : recptEmailAddr.entrySet())
        {
            String emailAddr = recipientEmailAddr.getKey();
            RecipientType sendType = recipientEmailAddr.getValue();
            msg.addRecipient(sendType, new InternetAddress(emailAddr));
        }

        msg.setSentDate(new Date());

        return msg;
    }

}
