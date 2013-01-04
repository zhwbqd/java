/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.sbs.mail.bean;

import java.util.List;
import java.util.Map;

public class MailSenderInfo
{

    private String subject;

    private String fromAddress;

    private List<String> toAddress;

    private String content;

    private String templateName;

    private Map<String, Object> templateMapping;

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public Map<String, Object> getTemplateMapping()
    {
        return templateMapping;
    }

    public void setTemplateMapping(Map<String, Object> templateMapping)
    {
        this.templateMapping = templateMapping;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getFromAddress()
    {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress)
    {
        this.fromAddress = fromAddress;
    }

    public List<String> getToAddress()
    {
        return toAddress;
    }

    public void setToAddress(List<String> emailAddress)
    {
        this.toAddress = emailAddress;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

}
