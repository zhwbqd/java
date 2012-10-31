package com.hp.sbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class RequestHandler extends AbstractHandler {

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if ("/commandExecutor".equals(target)) {

			String command = request.getParameter("command");
			if (command != null) {
				try {
					IExecutor executor = new CommandExecutor(command);
					AsynTask task = TaskPool.instance().submitTask(executor);

					baseRequest.setHandled(true);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().write(task.getTaskID());
				} catch (Exception e) {
					e.printStackTrace();

					baseRequest.setHandled(true);
					response.getWriter().write(e.toString());

				}
			}

		} else if ("/classExecutor".equals(target)) {

			String className = request.getParameter("className");
			if (className != null) {
				try {
					IExecutor executor = (IExecutor) Class.forName(
							className.trim()).newInstance();
					AsynTask task = TaskPool.instance().submitTask(executor);

					baseRequest.setHandled(true);
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().write(task.getTaskID());
				} catch (Exception e) {
					e.printStackTrace();

					baseRequest.setHandled(true);
					response.getWriter().write(e.toString());

				}
			}
		} else if ("/getTaskStatus".equals(target)) {
			String taskID = request.getParameter("taskID");
			String status = TaskPool.instance().getTaskStatus(taskID);
			baseRequest.setHandled(true);
			response.getWriter().write(status);
		} else if ("/waitForTaskResult".equals(target)) {
			String taskID = request.getParameter("taskID");
			AsynTask task = TaskPool.instance().getTask(taskID);
			try {
				String result = task.waitForTaskResult();

				baseRequest.setHandled(true);
				response.getWriter().write(result);

			} catch (Exception e) {
				e.printStackTrace();
				baseRequest.setHandled(true);
				response.getWriter().write("500 - INTERNAL_SERVER_ERROR");
			}

		}
	}
}
