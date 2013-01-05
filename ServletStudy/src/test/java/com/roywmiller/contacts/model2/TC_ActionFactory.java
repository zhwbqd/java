package com.roywmiller.contacts.model2;

import junit.framework.TestCase;

import com.roywmiller.contacts.actions.Action;
import com.roywmiller.contacts.actions.BootstrapAction;


public class TC_ActionFactory extends TestCase {
	
	public void testCreate() {
		//This is a partial test. It doesn't test all entries, only one to be sure the mechanism works
		ActionFactory factory = new ActionFactory();
		Action action = factory.create("index");
		
		assertTrue(action instanceof BootstrapAction);
	}
}
