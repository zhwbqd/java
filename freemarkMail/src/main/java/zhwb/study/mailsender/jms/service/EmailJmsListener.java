package zhwb.study.mailsender.jms.service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.hp.it.sbs.notification.beans.EmailTemplateParameterInfo;
import com.hp.it.sbs.notification.service.email.EmailSendStatus;
import com.hp.it.sbs.notification.service.email.IEmailService;

/**
 * The listener interface for receiving emailJms events.
 * The class that is interested in processing a emailJms
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addEmailJmsListener</code> method. When
 * the emailJms event occurs, that object's appropriate
 * method is invoked.
 *
 * @see EmailJmsEvent
 */
public final class EmailJmsListener implements SessionAwareMessageListener<Message>
{

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(EmailJmsListener.class);

    /** The email service. */
    private IEmailService emailService;

    /**
     * Instantiates a new email jms listener.
     *
     * @param emailService the email service
     */
    public EmailJmsListener(final IEmailService emailService)
    {
        super();
        this.emailService = emailService;
    }

    /** {@inheritDoc}
     *  @see org.springframework.jms.listener.SessionAwareMessageListener#onMessage(javax.jms.Message, javax.jms.Session)
     */
    public void onMessage(final Message message, final Session session)
        throws JMSException
    {
        if (message instanceof MapMessage)
        {
            MapMessage mapMessage = (MapMessage)message;
            try
            {
                String emailBody = mapMessage.getString("emailBody");
                EmailTemplateParameterInfo info = EmailTemplateParameterInfo.valueOf(mapMessage.getString("templateInfo"));
                String emailSubject = mapMessage.getString("emailSubject");
                EmailSendStatus status = emailService.sendEmail(emailBody, info.getRecipients(), info.isSendInGroup(),
                        info.getFromAddr(), emailSubject, info.getReplyTo());
                if (status.isNeedRetry())
                {
                    MessageProducer producer = session.createProducer(message.getJMSReplyTo());
                    producer.send(message);
                    LOG.info("Sending retry message back.");
                }
            }
            catch (JMSException e)
            {
                LOG.error("JMS consumer error: {}.", e.getMessage());
            }
        }
    }
}
