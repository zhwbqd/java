package com.roywmiller.contacts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	String ID = "id";
	String FIRSTNAME = "firstname";
	String LASTNAME = "lastname";
	String STREET = "street";
	String CITY = "city";
	String STATE = "state";
	String ZIP = "zip";
	String TYPE = "type";
	String USERNAME = "username";
	String PASSWORD = "password";

	public String perform(HttpServletRequest request, HttpServletResponse response);
}