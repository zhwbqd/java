/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.javamail.bean;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender
{
    private Email email;

    // 是否采用调试方式
    private boolean debug = false;

    public EmailSender()
    {
    }

    public EmailSender(Email email)
    {
        this.email = email;
    }

    public Email getEmail()
    {
        return email;
    }

    public void setEmail(Email email)
    {
        this.email = email;
    }

    private void fillEmail(Session session, MimeMessage msg)
        throws IOException, MessagingException
    {
        Multipart mPart = new MimeMultipart();
        if (email.getMailFrom() != null)
        {
            msg.setFrom(new InternetAddress(email.getMailFrom()));
            System.out.println("发送人Mail地址：" + email.getMailFrom());
        }
        else
        {
            System.out.println("没有指定发送人邮件地址！");
            return;
        }
        if (email.getMailTo() != null)
        {
            InternetAddress[] address = InternetAddress.parse(email.getMailTo());
            msg.setRecipients(Message.RecipientType.TO, address);
            System.out.println("收件人Mail地址：" + email.getMailTo());
        }
        else
        {
            System.out.println("没有指定收件人邮件地址！");
            return;
        }
        if (email.getMailccTo() != null)
        {
            InternetAddress[] ccaddress = InternetAddress.parse(email.getMailccTo());
            System.out.println("CCMail地址：" + email.getMailccTo());
            msg.setRecipients(Message.RecipientType.CC, ccaddress);
        }
        if (email.getMailbccTo() != null)
        {
            InternetAddress[] bccaddress = InternetAddress.parse(email.getMailbccTo());
            System.out.println("BCCMail地址：" + email.getMailbccTo());
            msg.setRecipients(Message.RecipientType.BCC, bccaddress);
        }

        msg.setSubject(email.getSubject());
        InternetAddress[] replyAddress = {new InternetAddress(email.getMailFrom())};

        msg.setReplyTo(replyAddress);

        // create and fill the first message part
        MimeBodyPart mBodyContent = new MimeBodyPart();
        if (email.getMsgContent() != null)
            mBodyContent.setContent(email.getMsgContent(), email.getMessageContentMimeType());
        else
            mBodyContent.setContent("", email.getMessageContentMimeType());
        mPart.addBodyPart(mBodyContent);
        // attach the file to the message
        if (email.getAttachedFileList() != null)
        {
            for (String fileName : email.getAttachedFileList())
            {
                MimeBodyPart mBodyPart = new MimeBodyPart();
                // attach the file to the message
                FileDataSource fds = new FileDataSource(email.getMessageBasePath() + fileName);
                System.out.println("Mail发送的附件为：" + email.getMessageBasePath() + fileName);
                mBodyPart.setDataHandler(new DataHandler(fds));
                mBodyPart.setFileName(fileName);
                mPart.addBodyPart(mBodyPart);
            }
        }
        msg.setContent(mPart);
        msg.setSentDate(new Date());
    }

    public String sendEmail()
        throws IOException, MessagingException
    {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", email.getSmtpHost());
        props.put("mail.smtp.auth", "false");
        //        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", email.getPort());
        props.setProperty("mail.smtp.socketFactory.port", email.getPort());

        //        Authenticator auth = new MailAuthenticator();
        Authenticator auth = null;
        Session session = Session.getInstance(props, auth);
        session.setDebug(debug);
        MimeMessage msg = new MimeMessage(session);
        Transport trans = null;

        String result = "";

        try
        {
            fillEmail(session, msg);
            // send the message
            trans = session.getTransport("smtp");
            try
            {
                //                trans.connect(email.getSmtpHost(), MailAuthenticator.MAIL_USER, MailAuthenticator.MAIL_PASSWORD);
                trans.connect();
            }
            catch (AuthenticationFailedException e)
            {
                e.printStackTrace();
                result = "连接邮件服务器错误";
                return result;
            }
            catch (MessagingException e)
            {
                result = "连接邮件服务器错误";
                return result;
            }
            Transport.send(msg);
            trans.close();
        }
        catch (MessagingException mex)
        {
            result = "发送邮件失败";
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null)
            {
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
            return result;
        }
        finally
        {
            try
            {
                if (trans != null && trans.isConnected())
                    trans.close();
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        System.out.println("发送邮件成功！");
        return "";
    }

}
