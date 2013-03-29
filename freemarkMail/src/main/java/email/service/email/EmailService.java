/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package email.service.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hp.it.cas.xa.validate.Verifier;
import com.hp.it.sbs.core.shared.exceptions.CoreServiceException;
import com.hp.it.sbs.notification.beans.RecipientEmailAddr;
import com.hp.it.sbs.notification.service.util.IVerificationMessages;
import com.hp.it.sbs.notification.service.util.LoggingUtility;

/**
 * The Class EmailService.
 */
@Service
public final class EmailService implements IEmailService
{

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    /** The mail sender. */
    private IEmailSender mailSender;

    /**
     * Instantiates a new email service.
     *
     * @param mailSender the mail sender
     */
    public EmailService(final IEmailSender mailSender)
    {
        super();
        this.mailSender = mailSender;
    }

    /** {@inheritDoc}
     *  @see email.service.email.IEmailService#buildMimeMessage(java.lang.String, com.hp.it.sbs.notification.beans.EmailTemplateParameterInfo, java.lang.String)
     */
    public List<MimeMessage> buildMimeMessage(final String emailText, final List<RecipientEmailAddr> recptEmailAddr,
            final boolean isSendInGroup, final String fromAddr, final String subjectText, final String replyTo)
    {
        List<MimeMessage> mimeMessages = new ArrayList<MimeMessage>();
        try
        {
            if (isSendInGroup)
            {
                LOG.info("Email will send in group.");
                Map<String, RecipientType> recptEmailAddrMap = convertRecipientAddr(recptEmailAddr);
                MimeMessage msg = mailSender.createMimeMessage(recptEmailAddrMap, subjectText, fromAddr, emailText, replyTo);
                mimeMessages.add(msg);
            }
            else
            {
                LOG.info("Email will send one by one.");
                for (RecipientEmailAddr receptEmail : recptEmailAddr)
                {
                    List<RecipientEmailAddr> email = new ArrayList<RecipientEmailAddr>();
                    email.add(receptEmail);
                    Map<String, RecipientType> recptEmailAddrMap = convertRecipientAddr(email);
                    MimeMessage msg = mailSender.createMimeMessage(recptEmailAddrMap, subjectText, fromAddr, emailText, replyTo);
                    mimeMessages.add(msg);
                }
            }
            return mimeMessages;
        }
        catch (MessagingException e)
        {
            LOG.error("Error occur in buildMimeMessage, exception is {}.", LoggingUtility.getStackTrace(e));
            throw new CoreServiceException("Error occur in build email object " + e.getMessage(), e);
        }
    }

    /**
     * Convert recipient addr.
     *
     * @param recptEmailAddr the recpt email addr
     * @return the map
     */
    private Map<String, RecipientType> convertRecipientAddr(final List<RecipientEmailAddr> recptEmailAddr)
    {
        Map<String, RecipientType> map = new HashMap<String, RecipientType>();
        for (RecipientEmailAddr recipientEmailAddr : recptEmailAddr)
        {
            String emailAddr = recipientEmailAddr.getEmailAddr();
            int sendType = recipientEmailAddr.getSendType();
            switch (sendType)
            {
                case 1:
                    map.put(emailAddr, RecipientType.TO);
                    break;
                case 2:
                    map.put(emailAddr, RecipientType.CC);
                    break;
                case 3:
                    map.put(emailAddr, RecipientType.BCC);
                    break;
                default:
                    map.put(emailAddr, RecipientType.TO);
                    break;
            }
        }
        return map;
    }

    /** {@inheritDoc}
     *  @see email.service.email.IEmailService#sendEmail(java.lang.String, java.util.List, boolean, java.lang.String, java.lang.String, String)
     */
    public EmailSendStatus sendEmail(final String emailText, final List<RecipientEmailAddr> recptEmailAddr,
            final boolean isSendInGroup, final String fromAddr, final String subjectText, final String replyTo)
    {
        new Verifier().isNotNull(emailText, IVerificationMessages.MSG_EML_BODY_NULL)
                .isNotEmpty(recptEmailAddr, IVerificationMessages.MSG_RCPT_EML_EMPTY)
                .isNotNull(fromAddr, IVerificationMessages.MSG_EML_FROM_NULL)
                .isNotEmpty(subjectText, IVerificationMessages.MSG_EML_SUBJCT_EMPTY)
                .throwIfError(IVerificationMessages.MSG_INVALID_INPUTS);

        EmailSendStatus status = new EmailSendStatus();
        List<MimeMessage> messages = buildMimeMessage(emailText, recptEmailAddr, isSendInGroup, fromAddr, subjectText, replyTo);
        try
        {
            status = mailSender.send(messages.toArray(new MimeMessage[messages.size()]));
            return status;
        }
        finally
        {
            if (LOG.isDebugEnabled())
            {
                logDebug(status);
            }
        }
    }

    /** {@inheritDoc}
     *  @see email.service.email.IEmailService#sendMimeMessage(java.util.List)
     */
    public EmailSendStatus sendMimeMessage(final List<MimeMessage> message)
    {
        EmailSendStatus status = null;
        try
        {
            status = mailSender.send(message.toArray(new MimeMessage[message.size()]));
            return status;
        }
        finally
        {
            if (LOG.isDebugEnabled())
            {
                logDebug(status);
            }
        }
    }

    /**
     * Log debug.
     *
     * @param status the status
     */
    public static void logDebug(final EmailSendStatus status)
    {
        LOG.debug("-----DEBUG SEND RESULT START-----");
        LOG.debug("Success mail address: {}", status.getSuccessEmailList());
        if (!status.getFailEmailList().isEmpty())
        {
            LOG.debug("Failed mail address: {}", status.getFailEmailList());
        }
        LOG.debug("Error message " + status.getErrorMessage());
        LOG.debug("-----DEBUG SEND RESULT END-----");
    }
}
