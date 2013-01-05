package com.roywmiller.contacts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roywmiller.contacts.persistence.UserDatabase;

public class LogoutAction implements Action {

	public String perform(HttpServletRequest request, HttpServletResponse response) {
		UserDatabase.getSingleton().shutDown();
		return "/goodbye.jsp";
	}

}
