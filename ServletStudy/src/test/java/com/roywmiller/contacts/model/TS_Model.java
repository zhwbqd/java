package com.roywmiller.contacts.model;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TS_Model {

	public TS_Model() {
		super();
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TC_Contact.class);
		suite.addTestSuite(TC_ContactsUser.class);
		return suite;
	}
}