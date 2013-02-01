/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.mailsender.bean;

public class ReceptionEmail
{
private SendType sendType;
private String emailAdress;

    public SendType getSendType()
    {
        return sendType;
    }

    public void setSendType(final SendType sendType)
    {
        this.sendType = sendType;
    }

    public String getEmailAdress()
    {
        return emailAdress;
    }

    public void setEmailAdress(final String emailAdress)
    {
        this.emailAdress = emailAdress;
    }

    public enum SendType
    {
        TO(1),
        CC(2),
        BCC(3);
        private final int typeCode;

        SendType(final int i)
        {
            typeCode = i;
        }

        public int getTypeCode()
        {
            return typeCode;
        }
    }

}


