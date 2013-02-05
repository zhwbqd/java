package zhwb.study.mailsender.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import zhwb.study.mailsender.bean.MailSenderInfo;

public class EmailJmsMapCreator implements MessageCreator
{

    private String emailBody;

    private MailSenderInfo info;

    public EmailJmsMapCreator(final String emailBody, final MailSenderInfo info)
    {
        this.emailBody = emailBody;
        this.info = info;
    }

    @Override
    public Message createMessage(final Session session)
        throws JMSException
    {
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("emailBody", emailBody);
        mapMessage.setObject("recptEmailMap", info.convertReceptionEmailToMap());
        mapMessage.setBoolean("isSendInGroup", info.isSendGroup());
        mapMessage.setString("fromAddr", info.getFromAddress());
        mapMessage.setString("subjectText", info.getSubject());
        return mapMessage;
    }

}
