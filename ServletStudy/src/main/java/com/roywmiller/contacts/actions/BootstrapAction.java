package com.roywmiller.contacts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BootstrapAction implements Action {

	public String perform(HttpServletRequest request, HttpServletResponse response) {
		return "/login.jsp";
	}
}
