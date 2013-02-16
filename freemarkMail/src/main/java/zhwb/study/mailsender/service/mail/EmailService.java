/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.study.mailsender.service.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import zhwb.study.mailsender.bean.ReceptionEmail;
import zhwb.study.mailsender.bean.ResponseStatus;
import zhwb.study.mailsender.bean.SendEmailTask;
import zhwb.study.mailsender.utils.IVerificationMessages;
import zhwb.study.mailsender.utils.Verifier;

public class EmailService implements IEmailService
{

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    /** The mail sender. */
    private JavaMailSender mailSender;

    /**
     * Instantiates a new email service.
     *
     * @param mailSender the mail sender
     */
    public EmailService(final JavaMailSender mailSender)
    {
        super();
        this.mailSender = mailSender;
    }

    @Override
    public Callable<ResponseStatus> createSendTask(final String emailText, final List<ReceptionEmail> recptEmailAddr,
            final boolean isSendInGroup, final String fromAddr, final String localLanguageSubjectLineText)
    {
        List<MimeMessage> messages = buildMimeMessage(emailText, recptEmailAddr, isSendInGroup, fromAddr,
                localLanguageSubjectLineText);

        Callable<ResponseStatus> task = new SendEmailTask(mailSender, messages);
        return task;
    }

    @Override
    public List<MimeMessage> buildMimeMessage(final String emailText, final List<ReceptionEmail> recptEmailAddr,
            final boolean isSendInGroup, final String fromAddr, final String subjectText)
    {
        new Verifier().isNotNull(emailText, IVerificationMessages.MSG_EML_BODY_NULL)
                .isNotEmpty(recptEmailAddr, IVerificationMessages.MSG_RCPT_EML_EMPTY)
                .isNotNull(fromAddr, IVerificationMessages.MSG_EML_FROM_NULL)
                .isNotNull(subjectText, IVerificationMessages.MSG_EML_SUBJCT_NULL)
                .throwIfError(IVerificationMessages.MSG_INVALID_INPUTS);

        List<MimeMessage> mimeMessages = new ArrayList<MimeMessage>();
        try
        {
            if (isSendInGroup)
            {
                LOG.info("Email will send in group.");
                MimeMessage msg = createMessage(recptEmailAddr, subjectText, fromAddr, emailText);
                mimeMessages.add(msg);
                return mimeMessages;
            }
            else
            {
                LOG.info("Email will send one by one.");
                for (ReceptionEmail receptEmail : recptEmailAddr)
                {
                    List<ReceptionEmail> email = new ArrayList<ReceptionEmail>();
                    email.add(receptEmail);
                    MimeMessage msg = createMessage(email, subjectText, fromAddr, emailText);
                    mimeMessages.add(msg);
                }
                return mimeMessages;
            }
        }
        catch (MessagingException e)
        {
            LOG.error("Error occur in build email object {}." + e.getMessage());
            throw new RuntimeException("Error occur in build email object " + e.getMessage(), e);
        }
    }

    /**
     * Creates the message.
     *
     * @param recptEmailAddr the recpt email addr
     * @param subjectText the subject text
     * @param fromAddr the from addr
     * @param emailText the email text
     * @return the mime message
     * @throws MessagingException the messaging exception
     */
    private MimeMessage createMessage(final List<ReceptionEmail> recptEmailAddr, final String subjectText,
            final String fromAddr, final String emailText)
        throws MessagingException
    {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
        helper.setSubject(subjectText);
        helper.setFrom(fromAddr);
        helper.setText(emailText, true);

        setMessageSendDetail(recptEmailAddr, helper);
        return msg;
    }

    /**
     * Sets the message send type.
     *
     * @param recptEmailAddr the recpt email addr
     * @param helper the helper
     * @throws MessagingException the messaging exception
     */
    private void setMessageSendDetail(final List<ReceptionEmail> recptEmailAddr, final MimeMessageHelper helper)
        throws MessagingException
    {
        List<String> toList = new ArrayList<String>();
        List<String> ccList = new ArrayList<String>();
        List<String> bccList = new ArrayList<String>();
        for (ReceptionEmail recepEmail : recptEmailAddr)
        {
            String emailAddr = recepEmail.getEmailAdress();
            ReceptionEmail.SendType sendType = recepEmail.getSendType();
            switch (sendType)
            {
                case TO:
                    toList.add(emailAddr);
                    break;
                case CC:
                    ccList.add(emailAddr);
                    break;
                case BCC:
                    bccList.add(emailAddr);
                    break;
                default:
                    toList.add(emailAddr);
            }
        }
        if (!toList.isEmpty())
        {
            helper.setTo(toList.toArray(new String[toList.size()]));
        }
        if (!ccList.isEmpty())
        {
            helper.setCc(ccList.toArray(new String[ccList.size()]));
        }
        if (!bccList.isEmpty())
        {
            helper.setBcc(bccList.toArray(new String[bccList.size()]));
        }

    }

    @Override
    public void sendMail(final List<MimeMessage> mailMessage)
    {
        mailSender.send(mailMessage.toArray(new MimeMessage[mailMessage.size()]));
    }

}
