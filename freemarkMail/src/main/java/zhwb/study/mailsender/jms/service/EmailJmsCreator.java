package zhwb.study.mailsender.jms.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

import com.hp.it.sbs.notification.beans.EmailTemplateParameterInfo;

/**
 * The Class EmailJmsCreator.
 */
public final class EmailJmsCreator implements MessageCreator
{

    /** The email body. */
    private String emailBody;

    /** The info. */
    private EmailTemplateParameterInfo info;

    /** The email subject. */
    private String emailSubject;

    /** The destination. */
    private Destination destination;

    /**
     * Instantiates a new email jms creator.
     *
     * @param emailBody the email body
     * @param info the info
     * @param emailSubject the email subject
     * @param destination the destination
     */
    public EmailJmsCreator(final String emailBody, final EmailTemplateParameterInfo info, final String emailSubject,
            final Destination destination)
    {
        super();
        this.emailBody = emailBody;
        this.info = info;
        this.emailSubject = emailSubject;
        this.destination = destination;
    }

    /** {@inheritDoc}
     *  @see org.springframework.jms.core.MessageCreator#createMessage(javax.jms.Session)
     */
    public Message createMessage(final Session session)
        throws JMSException
    {
        MapMessage message = session.createMapMessage();
        message.setString("emailBody", emailBody);
        message.setString("templateInfo", info.toString());
        message.setString("emailSubject", emailSubject);
        message.setJMSReplyTo(destination);

        return message;
    }

}
