/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.javamail.bean;

import java.util.List;

public class Email
{

    private final String mailTo;

    private final String mailFrom;

    private final String smtpHost;

    private final String messageBasePath;

    private final String subject;

    private final String msgContent;

    private final List<String> attachedFileList;

    private final String mailAccount;

    private final String mailPass;

    private final String messageContentMimeType = "text/html; charset=utf-8";

    private final String mailbccTo;

    private final String mailccTo;

    private final String port;

    private Email(Builder builder)
    {
        mailTo = builder.mailTo;

        mailFrom = builder.mailFrom;

        smtpHost = builder.smtpHost;

        messageBasePath = builder.messageBasePath;

        subject = builder.subject;

        msgContent = builder.msgContent;

        attachedFileList = builder.attachedFileList;

        mailAccount = builder.mailAccount;

        mailPass = builder.mailPass;

        mailbccTo = builder.mailbccTo;

        mailccTo = builder.mailccTo;

        port = builder.port;
    }

    public static class Builder
    {
        //require parameter
        private final String mailTo;

        private final String mailFrom;

        private final String smtpHost;

        private final String subject;

        private final String msgContent;

        public final String port;

        //optional parameter
        private String messageBasePath;

        private List<String> attachedFileList;

        private String mailAccount;

        private String mailPass;

        private String mailbccTo;

        private String mailccTo;

        public Builder(String mailTo, String mailFrom, String smtpHost, String subject, String msgContent, String port)
        {
            this.mailTo = mailTo;
            this.mailFrom = mailFrom;
            this.smtpHost = smtpHost;
            this.subject = subject;
            this.msgContent = msgContent;
            this.port = port;
        }

        public Builder messageBasePath(String messageBasePath)
        {
            this.messageBasePath = messageBasePath;
            return this;
        }

        public Builder attachedFileList(List<String> attachedFileList)
        {
            this.attachedFileList = attachedFileList;
            return this;
        }

        public Builder mailAccount(String mailAccount)
        {
            this.mailAccount = mailAccount;
            return this;
        }

        public Builder mailPass(String mailPass)
        {
            this.mailPass = mailPass;
            return this;
        }

        public Builder mailbccTo(String mailbccTo)
        {
            this.mailbccTo = mailbccTo;
            return this;
        }

        public Builder mailccTo(String mailccTo)
        {
            this.mailccTo = mailccTo;
            return this;
        }

        public Email builder()
        {
            return new Email(this);
        }
    }

    public String getMailTo()
    {
        return mailTo;
    }

    public String getMailFrom()
    {
        return mailFrom;
    }

    public String getSmtpHost()
    {
        return smtpHost;
    }

    public String getMessageBasePath()
    {
        return messageBasePath;
    }

    public String getSubject()
    {
        return subject;
    }

    public String getMsgContent()
    {
        return msgContent;
    }

    public List<String> getAttachedFileList()
    {
        return attachedFileList;
    }

    public String getMailAccount()
    {
        return mailAccount;
    }

    public String getMailPass()
    {
        return mailPass;
    }

    public String getMessageContentMimeType()
    {
        return messageContentMimeType;
    }

    public String getMailbccTo()
    {
        return mailbccTo;
    }

    public String getMailccTo()
    {
        return mailccTo;
    }

    public String getPort()
    {
        return port;
    }


}
