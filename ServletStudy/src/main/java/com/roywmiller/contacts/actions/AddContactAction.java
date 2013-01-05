package com.roywmiller.contacts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.roywmiller.contacts.model.Contact;
import com.roywmiller.contacts.model.ContactsUser;

public class AddContactAction implements Action {
	
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		Contact newContact = createContact(request);
		
		HttpSession session = request.getSession();
		ContactsUser user = (ContactsUser) session.getAttribute("user");
		user.addContact(newContact);
		session.setAttribute("user", user);
		
		return "/contactList.jsp";
	}

	protected Contact createContact(HttpServletRequest request) {
		Contact contact = new Contact();
		contact.setFirstname(request.getParameter(FIRSTNAME));
		contact.setLastname(request.getParameter(LASTNAME));
		contact.setStreet(request.getParameter(STREET));
		contact.setCity(request.getParameter(CITY));
		contact.setState(request.getParameter(STATE));
		contact.setZip(request.getParameter(ZIP));
		contact.setType(request.getParameter(TYPE));
		
		return contact;
	}	
}