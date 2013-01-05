package com.roywmiller.contacts.persistence;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TS_Persistence extends TestCase {

	public TS_Persistence() {
		super();
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TC_UserRecord.class);
		return suite;
	}

}