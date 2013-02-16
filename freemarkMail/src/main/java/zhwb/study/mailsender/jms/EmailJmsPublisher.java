package zhwb.study.mailsender.jms;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import zhwb.study.mailsender.bean.MailSenderInfo;
import zhwb.study.mailsender.utils.IVerificationMessages;
import zhwb.study.mailsender.utils.Verifier;

public class EmailJmsPublisher {
	
	private JmsTemplate template;
	
    private Destination destination;

    public EmailJmsPublisher(final JmsTemplate template, final Destination destination)
    {
        this.template = template;
        this.destination = destination;
	}

    public boolean sendEmailJms(final String emailText, final MailSenderInfo info)
    {
        new Verifier().isNotNull(emailText, IVerificationMessages.MSG_EML_BODY_NULL)
                .isNotEmpty(info.getSendAddress(), IVerificationMessages.MSG_RCPT_EML_EMPTY)
                .isNotNull(info.getFromAddress(), IVerificationMessages.MSG_EML_FROM_NULL)
                .isNotNull(info.getSubject(), IVerificationMessages.MSG_EML_SUBJCT_NULL)
                .throwIfError(IVerificationMessages.MSG_INVALID_INPUTS);

        //        MessageCreator messageCreator = new EmailJmsObjectCreator(emailText, info);
        MessageCreator messageCreator = new EmailJmsMapCreator(emailText, info);
        template.send(destination, messageCreator);
        return true;
    }
}
