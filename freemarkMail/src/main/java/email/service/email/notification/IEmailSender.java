/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package email.service.email.notification;

import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * The Interface IEmailSender.
 */
public interface IEmailSender
{

    /**
     * Creates the mime message.
     *
     * @param recptEmailAddr the recpt email addr
     * @param subjectText the subject text
     * @param fromAddr the from addr
     * @param emailText the email text
     * @param replyTo the reply to, optional field
     * @param bounceAddr the bounce addr, optional field
     * @return the mime message
     * @throws MessagingException the messaging exception
     */
    MimeMessage createMimeMessage(final Map<String, RecipientType> recptEmailAddr, final String subjectText,
            final String fromAddr, final String emailText, final String replyTo, String bounceAddr)
        throws MessagingException;

    /**
     * Send MimeMessage .
     *
     * @param message the message
     * @return the email send status
     */
    EmailSendStatus send(MimeMessage[] message);

}
