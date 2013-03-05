package zhwb.study.mailsender.jms.service;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;

import com.hp.it.cas.xa.validate.Verifier;
import com.hp.it.sbs.notification.beans.EmailTemplateParameterInfo;
import com.hp.it.sbs.notification.service.util.IVerificationMessages;

/**
 * The Class EmailJmsPublisher.
 */
public final class EmailJmsPublisher implements IEmailJmsPublisher
{

    /** The Constant LOG. */
    private static final Logger SERVICELOG = LoggerFactory.getLogger(EmailJmsPublisher.class);

    /** The template. */
    private JmsOperations template;

    /** The destination. */
    private Destination destination;

    /**
     * Instantiates a new email jms publisher.
     *
     * @param template the template
     * @param destination the destination
     */
    public EmailJmsPublisher(final JmsOperations template, final Destination destination)
    {
        this.template = template;
        this.destination = destination;
    }

    /** {@inheritDoc}
     *  @see zhwb.study.mailsender.jms.service.IEmailJmsPublisher#sendEmailJms(java.lang.String, com.hp.it.sbs.notification.beans.EmailTemplateParameterInfo, java.lang.String)
     */
    public boolean sendEmailJms(final String emailBody, final EmailTemplateParameterInfo info, final String emailSubject)
    {
        new Verifier().isNotNull(emailBody, IVerificationMessages.MSG_EML_BODY_NULL)
                .isNotNull(info, IVerificationMessages.MSG_EML_TMPLTE_PARAM_INFO_NULL)
                .isNotEmpty(emailSubject, IVerificationMessages.MSG_EML_SUBJCT_EMPTY)
                .throwIfError(IVerificationMessages.MSG_INVALID_INPUTS);
        
        new Verifier().isNotEmpty(info.getRecipients(), IVerificationMessages.MSG_RCPT_EML_EMPTY)
                .isNotNull(info.getFromAddr(), IVerificationMessages.MSG_EML_FROM_NULL)
                .throwIfError(IVerificationMessages.MSG_INVALID_INPUTS);

        MessageCreator messageCreator = new EmailJmsCreator(emailBody, info, emailSubject, destination);
        try
        {
            template.send(destination, messageCreator);
        }
        catch (Exception ex)
        {
            SERVICELOG.error("Exception occur while send jms message. {}", ex.getMessage());
            return false;
        }
        return true;
    }
}
