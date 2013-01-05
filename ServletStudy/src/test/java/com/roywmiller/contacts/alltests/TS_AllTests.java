package com.roywmiller.contacts.alltests;

import junit.framework.Test;
import junit.framework.TestSuite;


public class TS_AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite();

		suite.addTest(com.roywmiller.contacts.model.TS_Model.suite());
		suite.addTest(com.roywmiller.contacts.model2.TS_Model2.suite());
		suite.addTest(com.roywmiller.contacts.persistence.TS_Persistence.suite());
		
		return suite;
	}

}
