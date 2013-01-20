package com.hp.sbs.freemarker.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hp.sbs.mail.bean.MailSenderInfo;
import com.hp.sbs.mail.bean.ResponseStatus;
import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPSendFailedException;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailSendBusiness
{
    private JavaMailSender mailSender;

    private FreeMarkerConfigurer freeMarkerConfigurer = null;

    private ExecutorService pool = null;

    public MailSendBusiness(final JavaMailSender mailSender, final FreeMarkerConfigurer freeMarkerConfigurer)
    {
        super();
        pool = Executors.newCachedThreadPool();
        this.mailSender = mailSender;
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    private String getMailByDefaultTemplate(final String content)
        throws IOException, TemplateException
    {
        String htmlText = "";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("content", content);
        Template tpl = null;
        tpl = freeMarkerConfigurer.getConfiguration().getTemplate("reg.ftl");
        StringWriter writer = new StringWriter();
        tpl.process(map, writer);
        htmlText = writer.toString();//FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
        return htmlText;
    }

    private String getMailByTemplate(final Map<String, Object> map, final String templateName)
        throws IOException, TemplateException
    {
        Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
        String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, map);
        return htmlText;
    }

    public Future<ResponseStatus> sendByTemplate(final MailSenderInfo info, final boolean isSendInGroup)
        throws MessagingException, IOException, TemplateException
    {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
        helper.setSubject(info.getSubject());
        helper.setFrom(info.getFromAddress());
        if (info.getContent() != null)
        {
            helper.setText(this.getMailByDefaultTemplate(info.getContent()), true);
        }
        else
        {
            helper.setText(this.getMailByTemplate(info.getTemplateMapping(), info.getTemplateName()), true);
        }
        String[] emailAddresses = new String[info.getToAddress().size()];
        info.getToAddress().toArray(emailAddresses);
        Future<ResponseStatus> futureResult = null;
        if (isSendInGroup)
        {
            helper.setTo(emailAddresses);
            SendEmailTask task = new SendEmailTask(mailSender, msg);
            futureResult = pool.submit(task);
        }
        else
        {
            for (String emailAddress : emailAddresses)
            {
                helper.setTo(emailAddress);
                SendEmailTask task = new SendEmailTask(mailSender, msg);
                futureResult = pool.submit(task);
            }
        }
        return futureResult;
    }
}

class SendEmailTask implements Callable<ResponseStatus>
{
    private JavaMailSender mailSender;

    private MimeMessage msg;

    public SendEmailTask(final JavaMailSender mailSender, final MimeMessage msg)
    {
        this.mailSender = mailSender;
        this.msg = msg;
    }

    @Override
    public ResponseStatus call()
    {
        ResponseStatus status = new ResponseStatus();
        try
        {
        mailSender.send(msg);
        }
        catch (Exception ex)
        {
            if(ex instanceof MailSendException){
                @SuppressWarnings("unchecked")
                Map<Object, MessagingException> map = ((MailSendException)ex).getFailedMessages();
                for (Entry<Object, MessagingException> entry : map.entrySet())
                {
                    MessagingException me = entry.getValue();
                    handleException(me, status);
                }
                }
        }
        return status;
}

    private void handleException(final Exception mex, final ResponseStatus status)
    {
        if (mex != null)
        {
            if (mex instanceof SMTPSendFailedException)
            {
                SMTPSendFailedException ssf = (SMTPSendFailedException)mex;
                for (Address addr : ssf.getValidSentAddresses())
                {
                    status.setSuccessEmail(addr.toString());
                }
                for (Address addr : ssf.getInvalidAddresses())
                {
                    status.setFailEmail(addr.toString());
                }
                handleException(ssf.getNextException(), status);
            }
            else if (mex instanceof SMTPAddressFailedException)
            {
                SMTPAddressFailedException saf = (SMTPAddressFailedException)mex;
                status.addIntoErrorMap(saf.getAddress().toString(), saf.getMessage());
                handleException(saf.getNextException(), status);
            }
            else
            {
                status.setErrorMessage(mex.getMessage());
            }
        }
    }
}