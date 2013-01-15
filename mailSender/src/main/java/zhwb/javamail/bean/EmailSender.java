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
import javax.mail.Address;
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

import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPSendFailedException;

public class EmailSender {
	private Email email;

	// 是否采用调试方式
	private boolean debug = false;

	public EmailSender() {
	}

	public EmailSender(Email email) {
		this.email = email;
	}

	private void fillEmail(Session session, MimeMessage msg) throws IOException, MessagingException {
		Multipart mPart = new MimeMultipart();
		if (email.getMailFrom() != null) {
			msg.setFrom(new InternetAddress(email.getMailFrom()));
			System.out.println("发送人Mail地址：" + email.getMailFrom());
		} else {
			System.out.println("没有指定发送人邮件地址！");
			return;
		}
		if (email.getMailTo() != null) {
			InternetAddress[] address = InternetAddress.parse(email.getMailTo());
			msg.setRecipients(Message.RecipientType.TO, address);
			System.out.println("收件人Mail地址：" + email.getMailTo());
		} else {
			System.out.println("没有指定收件人邮件地址！");
			return;
		}
		if (email.getMailccTo() != null) {
			InternetAddress[] ccaddress = InternetAddress.parse(email.getMailccTo());
			System.out.println("CCMail地址：" + email.getMailccTo());
			msg.setRecipients(Message.RecipientType.CC, ccaddress);
		}
		if (email.getMailbccTo() != null) {
			InternetAddress[] bccaddress = InternetAddress.parse(email.getMailbccTo());
			System.out.println("BCCMail地址：" + email.getMailbccTo());
			msg.setRecipients(Message.RecipientType.BCC, bccaddress);
		}

		msg.setSubject(email.getSubject());
		InternetAddress[] replyAddress = { new InternetAddress(email.getMailFrom()) };

		msg.setReplyTo(replyAddress);

		// create and fill the first message part
		MimeBodyPart mBodyContent = new MimeBodyPart();
		if (email.getMsgContent() != null)
			mBodyContent.setContent(email.getMsgContent(), email.getMessageContentMimeType());
		else
			mBodyContent.setContent("", email.getMessageContentMimeType());
		mPart.addBodyPart(mBodyContent);
		// attach the file to the message
		if (email.getAttachedFileList() != null) {
			for (String fileName : email.getAttachedFileList()) {
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

	public ResponseStatus sendEmail(boolean sendPartial, boolean authenticate) throws IOException, MessagingException {
		ResponseStatus status = new ResponseStatus();

		Authenticator auth = null;

		Properties props = System.getProperties();
		props.put("mail.smtp.host", email.getSmtpHost());
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.sendpartial", String.valueOf(sendPartial));
		props.setProperty("mail.smtp.port", email.getPort());
		if (authenticate) {
			props.put("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.port", email.getPort());
			auth = new MailAuthenticator();
		} else {
			props.put("mail.smtp.auth", "false");
		}

		Session session = Session.getInstance(props, auth);
		session.setDebug(debug);
		MimeMessage msg = new MimeMessage(session);
		Transport trans = null;

		try {
			fillEmail(session, msg);
			// send the message
			trans = session.getTransport("smtp");
			try {
				if (authenticate) {
					trans.connect(email.getSmtpHost(), MailAuthenticator.MAIL_USER, MailAuthenticator.MAIL_PASSWORD);
				} else {
					trans.connect();
				}
			} catch (AuthenticationFailedException e) {
				e.printStackTrace();
				status.setErrorMessage("连接邮件服务器错误");
				return status;
			} catch (MessagingException e) {
				status.setErrorMessage("连接邮件服务器错误");
				return status;
			}
			Transport.send(msg);
			trans.close();
		} catch (MessagingException mex) {
			handleException(mex, status);
			return status;
		} finally {
			try {
				if (trans != null && trans.isConnected())
					trans.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		System.out.println("发送邮件成功！");
		status.setSuccess(true);
		for (String successEmail : email.getMailTo().split(",")) {
			status.setSuccessEmail(successEmail);
		}

		return status;
	}

    private void handleException(Exception mex, ResponseStatus status)
    {
        if (mex != null)
        {
            if (mex instanceof SMTPSendFailedException)
            {
                SMTPSendFailedException ssf = (SMTPSendFailedException)mex;
                for (Address addr : ssf.getValidSentAddresses())
                {
                    status.setSuccessEmail(addr.toString());
                }
                for (Address addr : ssf.getInvalidAddresses())
                {
                    status.setFailEmail(addr.toString());
                }
                handleException(ssf.getNextException(), status);
            }
            else if (mex instanceof SMTPAddressFailedException)
            {
                SMTPAddressFailedException saf = (SMTPAddressFailedException)mex;
                status.addIntoErrorMap(saf.getAddress().toString(), saf.getMessage());
                handleException(saf.getNextException(), status);
            }
            else
            {
                status.setErrorMessage(mex.getMessage());
            }
        }
    }
}
