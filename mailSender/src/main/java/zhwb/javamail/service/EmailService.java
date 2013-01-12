/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.javamail.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhwb.javamail.bean.Email;
import zhwb.javamail.bean.EmailSender;
import zhwb.javamail.bean.ResponseStatus;

public class EmailService
{
    private HttpServletRequest req;

    private HttpServletResponse res;

    public HttpServletRequest getReq()
    {
        return req;
    }

    public void setReq(HttpServletRequest req)
    {
        this.req = req;
    }

    public HttpServletResponse getRes()
    {
        return res;
    }

    public void setRes(HttpServletResponse res)
    {
        this.res = res;
    }

    public static EmailService getEmailService()
    {
        return new EmailService();

    }

    private EmailService()
    {
    }

    public EmailService(HttpServletRequest req, HttpServletResponse res)
    {
        this.req = req;
        this.res = res;
    }

    public void sendEmail(String stmp, String from, String to, String subject, String content, String atachs,
            String port)
    {
        List<String> vec = null;
        if (atachs != null)
        {
            vec = new Vector<String>();
            String[] atach_list = atachs.split(";");
            for (String atach : atach_list)
            {
                vec.add(atach);
            }
        }

		ResponseStatus result = new ResponseStatus();
        Email email = new Email.Builder(to, from, stmp, subject, content, port).attachedFileList(vec).builder();
        EmailSender bean = new EmailSender(email);

        try
        {
			result = bean.sendEmail(true, false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }

        try
        {
            PrintWriter out = this.res.getWriter();

			if (result.isSuccess())
            {
                out.print("success");
            }
            else
            {
				out.print(result.getErrorMessages());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
