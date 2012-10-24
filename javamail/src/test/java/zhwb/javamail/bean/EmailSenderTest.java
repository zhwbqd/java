/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.javamail.bean;

import java.io.IOException;

import javax.mail.MessagingException;

import junit.framework.TestCase;

import org.junit.Test;

public class EmailSenderTest extends TestCase
{
    @Test
    public void testSendEmail()
        throws IOException, MessagingException
    {
        String mailTo = "wen-bin.zhang@hp.com";
        String mailFrom = "wen-bin.zhang@hp.com";
        String smtpHost = "smtp.hp.com";
        String subject = "TEST";
        String msgContent = "TEST";
        String port = "25";
        Email email = new Email.Builder(mailTo, mailFrom, smtpHost, subject, msgContent, port).builder();
        EmailSender bean = new EmailSender(email);
        bean.sendEmail();
    }
}
