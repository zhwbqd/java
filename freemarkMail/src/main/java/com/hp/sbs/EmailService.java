/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.sbs;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hp.sbs.freemarker.service.MailSendBusiness;
import com.hp.sbs.mail.bean.MailSenderInfo;
import com.hp.sbs.mail.bean.ResponseStatus;

import freemarker.template.TemplateException;

public class EmailService
{
    private MailSendBusiness businessService;

    public EmailService()
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application*.xml");
        businessService = (MailSendBusiness)ctx.getBean("businessService");
    }

    public ResponseStatus sendFreemarkEmail(MailSenderInfo mailInfo, boolean isSendInGroup)
    {
        ResponseStatus status = new ResponseStatus();
        Future<ResponseStatus> future = null;
        try
        {
            future = businessService.sendByTemplate(mailInfo, isSendInGroup);
            return future.get();
        }
        catch (MessagingException e)
        {
            status.setErrorMessage(e.getMessage());
            status.setSuccess(false);
        }
        catch (IOException e)
        {
            status.setErrorMessage(e.getMessage());
            status.setSuccess(false);
        }
        catch (TemplateException e)
        {
            status.setErrorMessage(e.getMessage());
            status.setSuccess(false);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            future.cancel(true);
            status.setErrorMessage(e.getMessage());
            status.setSuccess(false);
        }
        catch (ExecutionException e)
        {
            status.setErrorMessage(e.getMessage());
            status.setSuccess(false);
        }
        return status;
    }
}
