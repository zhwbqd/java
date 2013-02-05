/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.study.mailsender.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import zhwb.study.mailsender.bean.ReceptionEmail.SendType;

public class MailSenderInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String subject;

    private String fromAddress;

    private boolean isSendGroup;

    private List<ReceptionEmail> sendAddress;

    private String templateName;

    private String templateStr;

    private Map<String, Object> templateMapping;

    private String emailBody;

    public static Map<String, Integer> convertReceptionEmailToMap(final List<ReceptionEmail> sendAddress)
    {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        for (ReceptionEmail addr : sendAddress)
        {
            map.put(addr.getEmailAdress(), addr.getSendType().getTypeCode());
        }
        return map;
    }

    public static List<ReceptionEmail> convertReceptionEmailToMap(final Map<String, Integer> sendAddressMap)
    {
        List<ReceptionEmail> sendAddress = new ArrayList<ReceptionEmail>();
        for (Entry<String, Integer> addr : sendAddressMap.entrySet())
        {
            ReceptionEmail email = new ReceptionEmail();
            email.setEmailAdress(addr.getKey());
            email.setSendType(SendType.valueOf(addr.getValue()));
            sendAddress.add(email);
        }
        return sendAddress;
    }

    public Map<String, Integer> convertReceptionEmailToMap()
    {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        for (ReceptionEmail addr : sendAddress)
        {
            map.put(addr.getEmailAdress(), addr.getSendType().getTypeCode());
        }
        return map;
    }

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

    public String getEmailBody()
    {
        return emailBody;
    }

    public void setEmailBody(final String emailBody)
    {
        this.emailBody = emailBody;
    }

}
