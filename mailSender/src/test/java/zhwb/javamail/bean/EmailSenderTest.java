/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.javamail.bean;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class EmailSenderTest extends TestCase
{
    @Test
    public void testSendEmail()
        throws IOException
    {
		String mailTo = "zhwbqd@unkonwnsdadggggggaas.dom,zhwbqd@gmail.com,asdd@gasada.sdac";
		String mailFrom = "sbs@hp.com";
		String smtpHost = "smtp3.hp.com";
        String subject = "TEST";
        String msgContent = "TEST";
		String port = "25";
        Email email = new Email.Builder(mailTo, mailFrom, smtpHost, subject, msgContent, port).builder();
		EmailSender bean = new EmailSender(email);
		ResponseStatus status = null;
        try
        {
			status = bean.sendEmail(true, false);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
		assertFalse(status.getErrorMessages().toString(), status.isSuccess());
		assertTrue(status.getFailEmails().size() == 2);
    }
}
