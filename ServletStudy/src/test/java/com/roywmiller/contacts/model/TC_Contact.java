package com.roywmiller.contacts.model;

import junit.framework.TestCase;


public class TC_Contact extends TestCase {
	
	protected Contact contact;
	
	public void setUp() {
		contact = new Contact();
	}
	
	public void testConstructor() {
		assertEquals(1, contact.getId());
		
		contact = new Contact();
		assertEquals(2, contact.getId());
	}
	
	public void testEquals() {
		contact.setFirstname("firstname");
		contact.setLastname("lastname");
		contact.setStreet("street");
		contact.setCity("city");
		contact.setState("state");
		contact.setZip("zip");
		contact.setType("type");
		contact.setId(1);
		
		Contact equalContact = new Contact();
		equalContact.setFirstname("firstname");
		equalContact.setLastname("lastname");
		equalContact.setStreet("street");
		equalContact.setCity("city");
		equalContact.setState("state");
		equalContact.setZip("zip");
		equalContact.setType("type");
		equalContact.setId(1);
		
		assertTrue(contact.equals(equalContact));

		equalContact.setFirstname("different firstname");
		assertFalse(contact.equals(equalContact));		
	}
}
