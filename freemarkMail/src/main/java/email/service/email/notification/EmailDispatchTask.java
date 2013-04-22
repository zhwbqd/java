package email.service.email.notification;

import java.util.List;
import java.util.concurrent.Callable;

import javax.mail.internet.MimeMessage;

/**
 * The Class EmailDispatchTask.
 */
public final class EmailDispatchTask implements Callable<EmailSendStatus>
{

    /** The messages. */
    private List<MimeMessage> messages;

    /** The email service. */
    private IEmailService emailService;

    /**
     * Instantiates a new email dispatch task.
     *
     * @param emailService the email service
     * @param messages the messages
     */
    public EmailDispatchTask(final IEmailService emailService, final List<MimeMessage> messages)
    {
        this.emailService = emailService;
        this.messages = messages;
    }

    /** {@inheritDoc}
     *  @see java.util.concurrent.Callable#call()
     */
    public EmailSendStatus call()
    {
        EmailSendStatus status = emailService.sendMimeMessage(messages);
        return status;
    }
}