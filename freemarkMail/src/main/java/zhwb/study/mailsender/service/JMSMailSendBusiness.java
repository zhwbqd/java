package zhwb.study.mailsender.service;

import zhwb.study.mailsender.bean.MailSenderInfo;
import zhwb.study.mailsender.bean.ResponseStatusCode;
import zhwb.study.mailsender.jms.EmailJmsPublisher;
import zhwb.study.mailsender.service.template.ITemplateService;

public class JMSMailSendBusiness
{
    private EmailJmsPublisher jmsPublisher;

    private ITemplateService templateService;

    public JMSMailSendBusiness(final EmailJmsPublisher jmsPublisher, final ITemplateService templateService)
    {
        super();
        this.jmsPublisher = jmsPublisher;
        this.templateService = templateService;
    }

    public ResponseStatusCode dispatchEmailByMailSenderInfo(final MailSenderInfo info)
    {
        ResponseStatusCode status = new ResponseStatusCode();
        if (info == null)
        {
            status.setStatusCode(ResponseStatusCode.EMAIL_VERIFICATION_FAILED);
            return status;
        }

        /*  process template with parameter */
        String emailText = templateService.processTempalte(info.getTemplateName(), info.getTemplateStr(),
                info.getTemplateMapping());
        if (emailText == null || emailText.length() <= 0)
        {
            status.setStatusCode(ResponseStatusCode.EMAIL_TEMPLATE_PROCESS_FAILED);
            return status;
        }

        /*unchecked JmsException will throw here*/
        boolean success = jmsPublisher.sendEmailJms(emailText, info);

        if (success)
        {
            status.setStatusCode(ResponseStatusCode.SUCCESS);
        }
        return status;
    }


}