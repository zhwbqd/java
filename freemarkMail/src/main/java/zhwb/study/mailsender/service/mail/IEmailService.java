/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.mailsender.service.mail;

import java.util.List;
import java.util.concurrent.Callable;

import javax.mail.internet.MimeMessage;

import zhwb.study.mailsender.bean.ReceptionEmail;
import zhwb.study.mailsender.bean.ResponseStatus;

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
     * @return the list
     */
    List<MimeMessage> buildMimeMessage(String emailText, List<ReceptionEmail> recptEmailAddr, boolean isSendInGroup,
            String fromAddr, String subjectText);

    /**
     * Creates the send task.
     *
     * @param emailText the email text
     * @param recptEmailAddr the recpt email addr
     * @param isSendInGroup the is send in group
     * @param fromAddr the from addr
     * @param localLanguageSubjectLineText the local language subject line text
     * @return the runnable
     */
    Callable<ResponseStatus> createSendTask(String emailText, List<ReceptionEmail> recptEmailAddr, boolean isSendInGroup,
            String fromAddr,
            String localLanguageSubjectLineText);

}
