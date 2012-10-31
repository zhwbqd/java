package com.hp.gadsc.cdc.et.birthday.email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Flags.Flag;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.hp.gadsc.cdc.et.birthday.util.Constants;
import com.hp.gadsc.cdc.et.birthday.util.PropertyReader;

public class EmailUtility {
	private static final String SMTP_HOST = "mail.smtp.host";

	private static final String MAILER = "Mailer";

	public EmailUtility() {
		super();
	}

	public static void sendEmail(Email email) throws Exception {
		sendMail(email.getFrom(), email.getTo(), email.getCc(), email.getBcc(),
				email.getSentDate(), email.getSubject(), email.getBody());
	}

	public static void sendInvitation(Invitation invitation) throws Exception {
		sendInvitation(invitation.getFrom(), invitation.getTo(),
				invitation.getCc(), invitation.getSentDate(),
				invitation.getStartDt(), invitation.getEndDt(),
				invitation.getLocation(), invitation.getSubject(),
				invitation.getBody(), invitation.getCategory());
	}

	public static void sendInvitation(String from, String to, String cc,
			Date sentDate, Date startDt, Date endDt, String location,
			String subject, String body, String category) throws Exception {
		try {
			Properties prop = new Properties();
			prop.put(SMTP_HOST, PropertyReader.readValue(Constants.SMTP_HOST));
			Session session = Session.getDefaultInstance(prop, null);
			// Define message

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyyMMdd'T'HHmm'00'");
			MimeMessage message = new MimeMessage(session);
			message.addHeaderLine("method=REQUEST");
			message.addHeaderLine("charset=UTF-8");
			message.addHeaderLine("component=VEVENT");
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					from));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(
					cc));
			message.setSubject(subject);

			StringBuffer sb = new StringBuffer();
			StringBuffer buffer = sb
					.append("BEGIN:VCALENDAR\n"
							+ "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n"
							+ "VERSION:2.0\n"
							+ "METHOD:REQUEST\n"
							+ "BEGIN:VEVENT\n"
							+ "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=FALSE:MAILTO:"
							+ to
							+ ";"
							+ cc
							+ ";"
							+ from
							+ "\n"
							+ "ORGANIZER:MAILTO:"
							+ from
							+ "\n"
							+ "DTSTART:"
							+ dateFormat.format(startDt)
							+ "\n"
							+ "DTEND:"
							+ dateFormat.format(endDt)
							+ "\n"
							+ "LOCATION:"
							+ location
							+ "\n"
							+ "TRANSP:TRANSPARENT\n"
							+ "SEQUENCE:0\n"
							+ "UID:"
							+ UUID.randomUUID()
							+ "\n"
							+ " 000004377FE5C37984842BF9440448399EB02\n"
							+ "DTSTAMP:20051206T120102Z\n"
							+ "CATEGORIES:"
							+ category
							+ "\n"
							+ "DESCRIPTION:"
							+ "\n"
							+ "SUMMARY:\n"
							+ "PRIORITY:5\n"
							+ "CLASS:PUBLIC\n"
							+ "BEGIN:VALARM\n"
							+ "TRIGGER:PT1440M\n"
							+ "ACTION:DISPLAY\n"
							+ "DESCRIPTION:Reminder\n"
							+ "END:VALARM\n" + "END:VEVENT\n" + "END:VCALENDAR");
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Fill the message
			messageBodyPart.setHeader("Content-Class",
					"urn:content-classes:calendarmessage");
			messageBodyPart.setHeader("Content-ID", "calendar_message");
			messageBodyPart
					.setDataHandler(new DataHandler(new ByteArrayDataSource(
							buffer.toString(), "text/calendar")));
			BodyPart htmlBodyPart = new MimeBodyPart();
			htmlBodyPart.setDataHandler(new DataHandler(
					new ByteArrayDataSource(body.toString(),
							"text/html; charset=\"UTF-8\"")));

			// very important
			// Create a Multipart
			Multipart multipart = new MimeMultipart();
			// Add part one
			multipart.addBodyPart(htmlBodyPart);
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// send message
			Transport.send(message);
		} catch (MessagingException me) {
			me.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static void sendMail(String from, String to, String cc, String bcc,
			Date date, String subject, String body) throws Exception {
		try {
			Properties props = new Properties();
			props.put(SMTP_HOST, PropertyReader.readValue(Constants.SMTP_HOST));

			// Get a Session object
			Session session = Session.getDefaultInstance(props, null);

			// construct the message
			Message msg = new MimeMessage(session);

			if ((null != from) && (from.length() > 0)) {
				msg.setFrom(new InternetAddress(from));
			} else {
				msg.setFrom();
			}

			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));

			if ((null != cc) && (cc.length() > 0)) {
				msg.setRecipients(Message.RecipientType.CC,
						InternetAddress.parse(cc, false));
			}

			if ((null != bcc) && (bcc.length() > 0)) {
				msg.setRecipients(Message.RecipientType.BCC,
						InternetAddress.parse(bcc, false));
			}

			msg.setSubject(subject);

			msg.setContent(body, "text/html; charset=\"UTF-8\"");

			msg.setHeader("X-Mailer", MAILER);
			msg.setSentDate(date);

			msg.setFlag(Flag.SEEN, false);

			// send mail
			Transport.send(msg);
		} catch (Exception e) {
			throw new Exception("Unable to send " + ", message.from=" + from
					+ ", message.to=" + to + ", message.cc=" + cc
					+ ", message.cc=" + bcc + ", message.date=" + date
					+ ", message.subject=" + subject + ", message.body=" + body
					+ ", failed with exception=" + e, e);
		}
	}

}
