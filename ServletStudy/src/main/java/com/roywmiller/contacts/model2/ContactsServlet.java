package com.roywmiller.contacts.model2;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roywmiller.contacts.actions.Action;
import com.roywmiller.contacts.persistence.UserDatabase;

public class ContactsServlet extends HttpServlet {

	protected ActionFactory factory = new ActionFactory();

	public ContactsServlet() {
		super();
	}

	protected String getActionName(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.substring(1, path.lastIndexOf("."));
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UserDatabase database = UserDatabase.getSingleton();
		database.setDatabaseFilePathname(getServletContext().getRealPath(""));
		database.initialize();
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = factory.create(getActionName(request));
		String url = action.perform(request, response);
		if (url != null)
			getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	public void destroy() {
		super.destroy();
		UserDatabase.getSingleton().shutDown();
	}
}