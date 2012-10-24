package com.hp.sbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

public interface ResultHandler {
	public void handleResult(AsynTask asynTask, String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response);

}
