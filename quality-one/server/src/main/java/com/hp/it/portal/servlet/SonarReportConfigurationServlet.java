package com.hp.it.portal.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.it.portal.handler.SonarReportConfigurationHandler;
import com.hp.it.server.configuration.AggregateReportConstant;

public class SonarReportConfigurationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String urlStr = req.getRequestURI();
		String action = urlStr.substring(urlStr.lastIndexOf("/") + 1);
		SonarReportConfigurationHandler handler = new SonarReportConfigurationHandler();
		boolean result = handler.handle(action, req);
		if ("D".equalsIgnoreCase(action)) {
			try {
				resp.sendRedirect(this.getServletContext().getContextPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (result) {
				String contextPath = this.getServletContext().getContextPath();
				try {
					if (req.getParameter(AggregateReportConstant.AGGREGATE) != null) {
						resp.sendRedirect(contextPath + "/aggregate.jsp?Aggregate="
								+ req.getParameter(AggregateReportConstant.AGGREGATE));

					} else {
						resp.sendRedirect(contextPath + "/query.jsp?KEE=" + req.getParameter("KEE"));
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					resp.sendError(500);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
