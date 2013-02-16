package zhwb.study.mailsender.jms;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSendException;

import zhwb.study.mailsender.bean.MailSenderInfo;
import zhwb.study.mailsender.bean.ReceptionEmail;
import zhwb.study.mailsender.bean.ResponseStatus;
import zhwb.study.mailsender.service.mail.EmailService;

import com.sun.mail.smtp.SMTPAddressFailedException;

public class EmailJmsListener implements MessageListener
{

    private static final Logger LOG = LoggerFactory.getLogger(EmailJmsListener.class);

    private EmailService emailService;

    public EmailJmsListener(final EmailService emailService)
    {
        this.emailService = emailService;
    }

    @Override
    public void onMessage(final Message message)
    {
        try
        {
            if (message instanceof MapMessage)
            {
                MapMessage mapMessage = (MapMessage)message;

                String emailBody = mapMessage.getString("emailBody");
                @SuppressWarnings("unchecked")
                Map<String, Integer> recptEmailMap = (Map<String, Integer>)mapMessage.getObject("recptEmailMap");
                boolean isSendInGroup = mapMessage.getBoolean("isSendInGroup");
                String fromAddr = mapMessage.getString("fromAddr");
                String subjectText = mapMessage.getString("subjectText");

                List<ReceptionEmail> recptEmailAddr = MailSenderInfo.convertReceptionEmailToMap(recptEmailMap);
                List<MimeMessage> emailList = emailService.buildMimeMessage(emailBody, recptEmailAddr, isSendInGroup, fromAddr,
                        subjectText);
                send(emailList);
            }
            else if (message instanceof ObjectMessage)
            {
                MailSenderInfo info = (MailSenderInfo)((ObjectMessage)message).getObject();
                List<MimeMessage> emailList = emailService.buildMimeMessage(info.getEmailBody(), info.getSendAddress(),
                        info.isSendGroup(), info.getFromAddress(), info.getSubject());
                send(emailList);
            }
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }

    private void send(final List<MimeMessage> emailList)
    {
        try
        {
            emailService.sendMail(emailList);
        }
        catch (Exception ex)
        {
            ResponseStatus status = new ResponseStatus();
            status.setErrorMessage(ex.getMessage());
            if (ex instanceof MailSendException)
            {
                @SuppressWarnings("unchecked")
                Map<Object, MessagingException> map = ((MailSendException)ex).getFailedMessages();
                for (Entry<Object, MessagingException> entry : map.entrySet())
                {
                    MessagingException me = entry.getValue();
                    handleException(me, status);
                }
            }

            LOG.error("Send email failed. Error message: {}", status.getErrorMessage());
            LOG.error("Failed email address: {}", status.getFailEmailList());
            if (LOG.isDebugEnabled())
            {
                logDebug(status);
            }
        }
    }

    private void logDebug(final ResponseStatus status)
    {
        LOG.debug("-----DEBUG SEND RESULT START-----");
        LOG.debug("Send email task is done, whole status: {}.", status.isSuccess());
        LOG.debug("Success mail address: {}", status.getSuccessEmailList());

        if (!status.getFailEmailList().isEmpty())
        {
            LOG.debug("Failed mail address: {}", status.getFailEmailList());
        }
        LOG.debug("Error message " + status.getErrorMessage());
        if (!status.getErrorMap().isEmpty())
        {
            for (Entry<String, String> entry : status.getErrorMap().entrySet())
            {
                LOG.debug("---Failed email address: {}, exception message: {}.", entry.getKey(), entry.getValue());
            }
        }
        LOG.debug("-----DEBUG SEND RESULT END-----");
    }

    private void handleException(final Exception mex, final ResponseStatus status)
    {
        if (mex != null)
        {
            if (mex instanceof SMTPAddressFailedException)
            {
                SMTPAddressFailedException saf = (SMTPAddressFailedException)mex;
                status.addIntoErrorMap(saf.getAddress().toString(), saf.getMessage());
                handleException(saf.getNextException(), status);
            }
            else if (mex instanceof SendFailedException)
            {
                SendFailedException ssf = (SendFailedException)mex;
                if (ssf.getValidSentAddresses() != null)
                {
                    for (Address addr : ssf.getValidSentAddresses())
                    {
                        status.setSuccessEmail(addr.toString());
                    }
                }
                if (ssf.getInvalidAddresses() != null)
                {
                    for (Address addr : ssf.getInvalidAddresses())
                    {
                        status.setFailEmail(addr.toString());
                    }
                }
                handleException(ssf.getNextException(), status);
            }
        }
    }

}
