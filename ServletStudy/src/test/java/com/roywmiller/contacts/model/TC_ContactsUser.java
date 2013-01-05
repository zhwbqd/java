package com.roywmiller.contacts.model;

import junit.framework.TestCase;

public class TC_ContactsUser extends TestCase {
	
	protected ContactsUser user;
	protected Contact contact;
	
	public void setUp() {
		user = new ContactsUser();
		contact = new Contact();
	}
	
	public void testHasContacts() {
		assertFalse(user.hasContacts());
		
		user.addContact(contact);
		assertTrue(user.hasContacts());
	}
	
    public void testAddContact() {
    	assertFalse(user.hasContacts());
    	
    	user.addContact(contact);
    	assertEquals(1, user.getContacts().size());
    }
    
    public void testRemoveContact() {
    	user.getContacts().add(contact);
    	assertEquals(1, user.getContacts().size());
    	
    	user.removeContact(contact);
    	assertEquals(0, user.getContacts().size());
    }
    
    public void testRemoveWithId() {
    	user.getContacts().add(contact);
    	assertEquals(1, user.getContacts().size());
    	
    	int contactId = contact.getId();
    	user.removeContact(contactId);
    	assertEquals(0, user.getContacts().size());    	
    }
}