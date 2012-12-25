/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.quartz.job;

import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zhwb.javamail.bean.Email;
import zhwb.javamail.bean.EmailSender;

public class SendMailJob implements Job
{
    private static Logger _log = LoggerFactory.getLogger(SendMailJob.class);
    public void execute(JobExecutionContext context)
        throws JobExecutionException
    {
        JobKey jobKey = context.getJobDetail().getKey();
        _log.info("Executing job: " + jobKey + " executing at " + new Date() + ", fired by: "
                + context.getTrigger().getKey());

        String mailTo = "wen-bin.zhang@hp.com";
        String mailFrom = "wen-bin.zhang@hp.com";
        String smtpHost = "smtp.hp.com";
        String subject = "TEST";
        String msgContent = "TEST";
        String port = "25";
        Email email = new Email.Builder(mailTo, mailFrom, smtpHost, subject, msgContent, port).builder();
        EmailSender bean = new EmailSender(email);
        try
        {
            bean.sendEmail();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }

    }

}
