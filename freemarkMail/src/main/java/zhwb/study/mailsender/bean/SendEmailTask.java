/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.mailsender.bean;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import com.sun.mail.smtp.SMTPAddressFailedException;

public class SendEmailTask implements Callable<ResponseStatus>
{
    private JavaMailSender mailSender;

    private List<MimeMessage> msg;

    public SendEmailTask(final JavaMailSender mailSender, final List<MimeMessage> msg)
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
        mailSender.send(msg.toArray(new MimeMessage[msg.size()]));
        }
        catch (Exception ex)
        {
            status.setErrorMessage(ex.getMessage());
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
