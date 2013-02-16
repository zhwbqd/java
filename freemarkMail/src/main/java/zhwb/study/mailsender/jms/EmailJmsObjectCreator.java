package zhwb.study.mailsender.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import zhwb.study.mailsender.bean.MailSenderInfo;

public class EmailJmsObjectCreator implements MessageCreator
{

    private String emailBody;

    private MailSenderInfo info;

    public EmailJmsObjectCreator(final String emailBody, final MailSenderInfo info)
    {
        this.emailBody = emailBody;
        this.info = info;
    }

    @Override
    public Message createMessage(final Session session)
        throws JMSException
    {
        info.setEmailBody(emailBody);
        ObjectMessage objMessage = session.createObjectMessage();
        objMessage.setObject(info);

        return objMessage;
    }

}
