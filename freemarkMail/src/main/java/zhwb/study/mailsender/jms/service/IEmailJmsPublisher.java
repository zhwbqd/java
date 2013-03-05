
package zhwb.study.mailsender.jms.service;


/**
 * The Interface IEmailJmsPublisher.
 */
public interface IEmailJmsPublisher
{

    /**
     * Send email jms.
     *
     * @param emailBody the email body
     * @param info the info
     * @param emailSubject the email subject
     * @return true, if successful
     */
    boolean sendEmailJms(final String emailBody, final EmailTemplateParameterInfo info, final String emailSubject);
}
