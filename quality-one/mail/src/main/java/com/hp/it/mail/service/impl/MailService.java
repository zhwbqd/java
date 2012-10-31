package com.hp.it.mail.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hp.it.mail.bean.EMail;
import com.hp.it.mail.service.IMailService;

public class MailService implements IMailService
{

	private String smtp;

	public void sendMail(EMail eMail)
	{
		String toList = "";
		if (eMail.getToList() != null)
		{
			for (String to : eMail.getToList())
			{
				toList += to + ",";
			}
		}

		String ccList = "";
		if (eMail.getCcList() != null)
		{
			for (String cc : eMail.getCcList())
			{
				ccList += cc + ",";
			}
		}

		String bccList = "";
		if (eMail.getBccList() != null)
		{
			for (String bcc : eMail.getBccList())
			{
				bccList += bcc + ",";
			}
		}

		sendMail(eMail.getFrom(), toList, ccList, bccList, new Date(), eMail.getSubject(), eMail.getContent());
	}

	public void sendMail(String from, String toList, String ccList, String bccList, Date date, String subject,
			String body)
	{
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtp);

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage msg = new MimeMessage(session);

		try
		{
			if ((null != from) && (from.length() > 0))
			{
				msg.setFrom(new InternetAddress(from));
			} else
			{
				msg.setFrom();
			}
			if ((null != toList) && (toList.length() > 0))
			{
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toList, false));
			}
			if ((null != ccList) && (ccList.length() > 0))
			{
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccList, false));
			}
			if ((null != bccList) && (bccList.length() > 0))
			{
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccList, false));
			}

			msg.setHeader("X-Mailer", "Mailer");
			msg.setSubject(subject);
			msg.setContent(body, "text/html; charset=\"UTF-8\"");
			msg.setSentDate(date);

			Transport.send(msg);
		} catch (AddressException e)
		{
			e.printStackTrace();
		} catch (MessagingException e)
		{
			e.printStackTrace();
		}
	}

	public void sendExceptionMail(String from, String to, String cc, String bcc, Date date, String subject, Exception e)
	{
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtp);
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage msg = new MimeMessage(session);

		try
		{
			if ((null != from) && (from.length() > 0))
			{
				msg.setFrom(new InternetAddress(from));
			} else
			{
				msg.setFrom();
			}
			if ((null != to) && (to.length() > 0))
			{
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			}
			if ((null != cc) && (cc.length() > 0))
			{
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
			}
			if ((null != bcc) && (bcc.length() > 0))
			{
				msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
			}

			msg.setHeader("X-Mailer", "Mailer");
			msg.setSubject(subject);
			msg.setContent(e, "text/html; charset=\"UTF-8\"");
			msg.setSentDate(date);

			Transport.send(msg);
		} catch (AddressException ex)
		{
			e.printStackTrace();
		} catch (MessagingException ex)
		{
			e.printStackTrace();
		}
	}

	public String getSmtp()
	{
		return smtp;
	}

	public void setSmtp(String smtp)
	{
		this.smtp = smtp;
	}

}
