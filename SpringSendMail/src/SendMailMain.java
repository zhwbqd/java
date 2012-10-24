import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMailMain {

	public static void sendTextMail() throws MessagingException {
		// 直接使用JavaMail API:
		Properties props = new Properties();
		// 设置SMTP服务器:
        props.put("mail.smtp.host", "smtp3.hp.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "false");
		// 使用SSL安全连接:
		props.put("mail.smtp.starttls.enable", "true");
        //		props.put("mail.smtp.socketFactory.class",
        //				"javax.net.ssl.SSLSocketFactory");
        send(props, "", // Username
                "", // Password
                "wen-bin.zhang@hp.com", // From
                new String[] {"wen-bin.zhang@hp.com"}, // To
				"A test mail", // Subject
				"测试使用JavaMail API发送邮件" // Text
		);
	}

	public static void javaMailSender() throws MessagingException {
		// 使用Spring提供的MailSender:
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"config.xml");
		JavaMailSender mailSender = (JavaMailSender) context
				.getBean("mailSender");
		// 创建一个纯文本邮件:
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("654752907@qq.com");
		mail.setTo("shenzhen_zsw@163.com");
		mail.setSubject("Another test mail");
		mail.setText("测试使用Spring MailSender发送邮件");
		// 发送:
		mailSender.send(mail);

		// 发送Mime邮件:
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
        helper.setFrom("wen-bin.zhang@hp.com");
        helper.setTo("wen-bin.zhang@hp.com");
		helper.setSubject("Test mime mail");
		// 设定为HTML格式:
		helper.setText("<html><body>访问Live在线书店：<br>"
				+ "<a href='http://www.livebookstore.net' target='_blank'>"
				+ "<img src='cid:logo'></a></body></html>", true);
        //		helper.addInline("logo", new ClassPathResource("logo.gif"));
        //		helper.addAttachment("freebsd.gif",
        //				new ClassPathResource("freebsd.gif"));
		// 发送:
		mailSender.send(mime);
	}

	public static void send(Properties props, final String username,
			final String password, String from, String[] to, String subject,
			String text) throws MessagingException {
		Session session = Session.getInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setSubject(subject);
		message.setText(text);
		message.setSentDate(new Date());
		Address[] addressTo = new Address[to.length];
		for (int i = 0; i < to.length; i++) {
			addressTo[i] = new InternetAddress(to[i]);
		}
		message.setRecipients(Message.RecipientType.TO, addressTo);
		message.saveChanges();
		Transport.send(message, addressTo);
	}

	public static void main(String[] args) throws Exception {
		javaMailSender();
        sendTextMail();
	}

}
