package zhwb.study.mailsender.service;

import java.util.concurrent.Callable;

import zhwb.study.mailsender.bean.MailSenderInfo;
import zhwb.study.mailsender.bean.ResponseStatus;
import zhwb.study.mailsender.bean.ResponseStatusCode;
import zhwb.study.mailsender.jms.EmailJmsPublisher;
import zhwb.study.mailsender.service.mail.IEmailService;
import zhwb.study.mailsender.service.template.ITemplateService;
import zhwb.study.mailsender.utils.ThreadPoolManager;

public class JMSMailSendBusiness
{
    private IEmailService emailService;

    private EmailJmsPublisher jmsPublisher;

    private ITemplateService templateService;

    public JMSMailSendBusiness(final IEmailService emailService, final EmailJmsPublisher jmsPublisher,
            final ITemplateService templateService)
    {
        super();
        this.emailService = emailService;
        this.jmsPublisher = jmsPublisher;
        this.templateService = templateService;
    }

    public ResponseStatusCode dispatchEmailByMailSenderInfo(final MailSenderInfo info, final boolean isOnline)
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
        boolean success = false;
        if (isOnline)
        {
            Callable<ResponseStatus> task = emailService.createSendTask(emailText, info.getSendAddress(), info.isSendGroup(),
                    info.getFromAddress(), info.getSubject());

            success = ThreadPoolManager.getInstance().submitTask(task, true);
        }
        else
        {/*unchecked JmsException will throw here*/
            success = jmsPublisher.sendEmailJms(emailText, info);
        }
        if (success)
        {
            status.setStatusCode(ResponseStatusCode.SUCCESS);
        }
        return status;
    }


}