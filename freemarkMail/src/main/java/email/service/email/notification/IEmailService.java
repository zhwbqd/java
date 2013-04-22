/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package email.service.email.notification;

import java.util.List;

import javax.mail.internet.MimeMessage;

import com.hp.it.sbs.notification.beans.RecipientEmailAddr;

/**
 * The Interface IEmailService.
 */
public interface IEmailService
{
    
    /**
     * Builds the mime message.
     *
     * @param emailText the email text
     * @param recptEmailAddr the recpt email addr
     * @param isSendInGroup the is send in group
     * @param fromAddr the from addr
     * @param subjectText the subject text
     * @param replyTo replyTo email Address, it's optional
     * @param bounceAddr the bounce addr, it's optional
     * @return the list
     */
    List<MimeMessage> buildMimeMessage(String emailText, List<RecipientEmailAddr> recptEmailAddr, boolean isSendInGroup,
            String fromAddr, String subjectText, String replyTo, String bounceAddr);

    /**
     * Send email.
     *
     * @param emailText the email text
     * @param recptEmailAddr the recpt email address
     * @param isSendInGroup the is send in group
     * @param fromAddr the from address
     * @param subjectText the subject text
     * @param replyTo replyTo email Address, it's optional
     * @param bounceAddr the bounce addr, it's optional
     * @return the email send status
     */
    EmailSendStatus sendEmail(String emailText, List<RecipientEmailAddr> recptEmailAddr, boolean isSendInGroup, String fromAddr,
            String subjectText, String replyTo, String bounceAddr);

    /**
     * Send mime message.
     *
     * @param message the message
     * @return the email send status
     */
    EmailSendStatus sendMimeMessage(List<MimeMessage> message);

}
