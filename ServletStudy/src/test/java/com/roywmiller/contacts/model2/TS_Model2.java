package com.roywmiller.contacts.model2;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TS_Model2 {

	public TS_Model2() {
		super();
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TC_ActionFactory.class);
		return suite;
	}
}