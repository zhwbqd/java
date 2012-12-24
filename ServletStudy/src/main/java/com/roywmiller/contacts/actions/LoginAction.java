package com.roywmiller.contacts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roywmiller.contacts.model.ContactsUser;
import com.roywmiller.contacts.persistence.UserDatabase;

public class LoginAction implements Action {

	public String perform(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		
		ContactsUser user = UserDatabase.getSingleton().get(username, password);
		if (user != null) {
			ContactsUser contactsUser = (ContactsUser) user;
			request.getSession().setAttribute("user", contactsUser);
			return "/contactList.jsp";
		} else {
			request.getSession().setAttribute("errorMessage", "Invalid username/password.");
			return "/error.jsp";
		}
	}

}
