/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.javamail.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhwb.javamail.service.EmailService;

public class EmailServlet extends HttpServlet
{
    /**
     * serialVersionUID
     * long
     */
    private static final long serialVersionUID = 1368786877488781222L;
    private EmailService service;

    public EmailServlet()
    {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {

        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        service = new EmailService(request, response);

        String action = "";
        action = request.getParameter("action");

        if (action == null || action.equals(""))
        {
            return;
        }
        //集中处理请求类型
        if (action.toUpperCase().equals("SENDEMAIL"))
        {
            String stmp = request.getParameter("stmp_host");
            String from = request.getParameter("mail_from");
            String to = request.getParameter("mail_to");
            String subject = request.getParameter("mail_subject");
            String content = request.getParameter("mail_content");
            String atachs = request.getParameter("mail_atach_list");
            if (atachs.length() == 0)
            {
                atachs = null;
            }
            String port = request.getParameter("stmp_host_port");

            service.sendEmail(stmp, from, to, subject, content, atachs, port);
        }
    }
}
