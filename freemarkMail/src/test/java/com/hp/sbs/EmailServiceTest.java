/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.sbs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import zhwb.study.mailsender.bean.MailSenderInfo;
import zhwb.study.mailsender.bean.ReceptionEmail;
import zhwb.study.mailsender.bean.ReceptionEmail.SendType;
import zhwb.study.mailsender.bean.ResponseStatusCode;
import zhwb.study.mailsender.service.MailSendBusiness;


public class EmailServiceTest
{
    private static MailSendBusiness service;


    public static void main(final String[] args)
    {
        service = (MailSendBusiness)new ClassPathXmlApplicationContext("applicationContext.xml").getBean("businessService");
        testSendEmailOneByOne();
        testSendEmailInGroup();
        testSendInCorrectEmailInGroup();
    }

    private static void testSendEmailOneByOne()
    {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setSendGroup(false);
        mailInfo.setFromAddress("SBS@hp.com");
        mailInfo.setSubject("Test Send Email One by One");
        mailInfo.setSendAddress(createEmailList());
        mailInfo.setTemplateName("emailReminder.ftl");
        mailInfo.setTemplateMapping(createTemplate());
        ResponseStatusCode status = service.dispatchEmailByMailSenderInfo(mailInfo);
        Assert.assertEquals(1, status.getStatusCode());
    }


    private static void testSendEmailInGroup()
    {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setSendGroup(true);
        mailInfo.setFromAddress("zhwbqd@gmail.com");
        mailInfo.setSubject("Test Send Email In Group");
        mailInfo.setSendAddress(createEmailList());
        ResponseStatusCode status = service.dispatchEmailByMailSenderInfo(mailInfo);
        Assert.assertEquals(1, status.getStatusCode());
    }


    private static void testSendInCorrectEmailInGroup()
    {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setSendGroup(true);
        mailInfo.setFromAddress("SBS@hp.com");
        mailInfo.setSubject("Test Send Email In Group");
        mailInfo.setSendAddress(createInCorrectEmailList());
        ResponseStatusCode status = service.dispatchEmailByMailSenderInfo(mailInfo);
        Assert.assertEquals(1, status.getStatusCode());
    }

    private static List<ReceptionEmail> createEmailList()
    {
        List<ReceptionEmail> emailAddress = new ArrayList<ReceptionEmail>();
        ReceptionEmail mail1 = new ReceptionEmail();
        mail1.setEmailAdress("wen-bin.zhang@hp.com");
        mail1.setSendType(SendType.TO);
        emailAddress.add(mail1);
        mail1 = new ReceptionEmail();
        mail1.setEmailAdress("kid_zhwb@163.com");
        mail1.setSendType(SendType.CC);
        emailAddress.add(mail1);
        mail1 = new ReceptionEmail();
        mail1.setEmailAdress("zhwbqd@gmail.com");
        mail1.setSendType(SendType.BCC);
        emailAddress.add(mail1);
        return emailAddress;
    }

    private static List<ReceptionEmail> createInCorrectEmailList()
    {
        List<ReceptionEmail> emailAddress = new ArrayList<ReceptionEmail>();
        ReceptionEmail mail1 = new ReceptionEmail();
        mail1.setEmailAdress("fdsdf@hsfpsf.sssm");
        mail1.setSendType(SendType.TO);
        emailAddress.add(mail1);
        mail1 = new ReceptionEmail();
        mail1.setEmailAdress("sdag@163ddm");
        mail1.setSendType(SendType.CC);
        emailAddress.add(mail1);
        mail1 = new ReceptionEmail();
        mail1.setEmailAdress("sdsddsail.com");
        mail1.setSendType(SendType.BCC);
        emailAddress.add(mail1);
        return emailAddress;
    }

    private static Map<String, Object> createTemplate()
    {
        Map<String, Object> template = new HashMap<String, Object>();
        template.put("nowDate", new Date());
        template.put("companyName", "ABC");
        template.put("userName", "Jack");
        template.put("telphone", "021-38898775");
        template.put("location", "ZhangJiang HighTech, Jinke Road #2517, Shanghai, China");
        return template;

    }
}
