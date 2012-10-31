package com.hp.it.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class LoggerInitializerListener implements ServletContextListener
{
	private static Logger logger = Logger.getLogger(LoggerInitializerListener.class);

	public void contextInitialized(ServletContextEvent sce)
	{
		
	}

	public void contextDestroyed(ServletContextEvent sce)
	{
		
	}

}
