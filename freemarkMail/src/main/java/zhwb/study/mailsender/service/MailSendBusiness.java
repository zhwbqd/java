package zhwb.study.mailsender.service;

import java.util.concurrent.Callable;

import zhwb.study.mailsender.bean.MailSenderInfo;
import zhwb.study.mailsender.bean.ResponseStatus;
import zhwb.study.mailsender.bean.ResponseStatusCode;
import zhwb.study.mailsender.service.mail.IEmailService;
import zhwb.study.mailsender.service.template.ITemplateService;
import zhwb.study.mailsender.utils.ThreadPoolManager;

public class MailSendBusiness
{
    private IEmailService emailService;

    private ITemplateService templateService;

    public MailSendBusiness(final IEmailService emailService, final ITemplateService templateService)
    {
        super();
        this.emailService = emailService;
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

        /* create runnable task for email*/
        Callable<ResponseStatus> sendTask = emailService.createSendTask(emailText, info.getSendAddress(), info.isSendGroup(),
                info.getFromAddress(), info.getSubject());

        /* add task into the working queue, will return false if the queue is full. */
        boolean success = ThreadPoolManager.getInstance().submitTask(sendTask);
        if (success)
        {
            status.setStatusCode(ResponseStatusCode.SUCCESS);
        }
        else
        {
            status.setStatusCode(ResponseStatusCode.WORKING_QUEUE_FULL);
        }
        return status;
    }


}