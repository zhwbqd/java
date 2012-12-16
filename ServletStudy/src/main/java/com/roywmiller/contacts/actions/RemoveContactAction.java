package com.roywmiller.contacts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.roywmiller.contacts.model.ContactsUser;

public class RemoveContactAction implements Action {

	public String perform(HttpServletRequest request, HttpServletResponse response) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		ContactsUser user = (ContactsUser) session.getAttribute("user");
		user.removeContact(contactId);
		session.setAttribute("user", user);
		
		return "/contactList.jsp";
	}
}