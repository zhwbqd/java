package com.hp.it.server.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class AbstractReportServlet extends HttpServlet
{
	private ApplicationContext applicationContext;

	@Override
	public void init()
	{
		setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(getServletContext()));
	}

	public ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
	}

}