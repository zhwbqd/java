/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.study.mailsender.bean;

import java.util.List;
import java.util.Map;

public class MailSenderInfo
{
    private String subject;

    private String fromAddress;

    private boolean isSendGroup;

    private List<ReceptionEmail> sendAddress;

    private String templateName;

    private String templateStr;

    private Map<String, Object> templateMapping;

    public List<ReceptionEmail> getSendAddress()
    {
        return sendAddress;
    }

    public void setSendAddress(final List<ReceptionEmail> sendAddress)
    {
        this.sendAddress = sendAddress;
    }

    public String getTemplateStr()
    {
        return templateStr;
    }

    public void setTemplateStr(final String templateStr)
    {
        this.templateStr = templateStr;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName(final String templateName)
    {
        this.templateName = templateName;
    }

    public Map<String, Object> getTemplateMapping()
    {
        return templateMapping;
    }

    public void setTemplateMapping(final Map<String, Object> templateMapping)
    {
        this.templateMapping = templateMapping;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(final String subject)
    {
        this.subject = subject;
    }

    public String getFromAddress()
    {
        return fromAddress;
    }

    public void setFromAddress(final String fromAddress)
    {
        this.fromAddress = fromAddress;
    }

    public boolean isSendGroup()
    {
        return isSendGroup;
    }

    public void setSendGroup(final boolean isSendGroup)
    {
        this.isSendGroup = isSendGroup;
    }

}
