package com.roywmiller.contacts.persistence;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import com.roywmiller.contacts.model.Contact;
import com.roywmiller.contacts.model.ContactsUser;

public class TC_UserRecord extends TestCase {

	protected List expectedContacts;
	protected String testUserRecordString = "username cGFzc3dvcmQ= contact1FirstName,contact1LastName,contact1Street,contact1City,contact1State,contact1Zip,contact1Type|contact2FirstName,contact2LastName,contact2Street,contact2City,contact2State,contact2Zip,contact2Type"; 
	
	public void setUp() {
		Contact.setNextId(1);
		
		Contact contactOne = new Contact();
		contactOne.setId(1);
		contactOne.setFirstname("contact1FirstName");
		contactOne.setLastname("contact1LastName");
		contactOne.setStreet("contact1Street");
		contactOne.setCity("contact1City");
		contactOne.setState("contact1State");
		contactOne.setZip("contact1Zip");
		contactOne.setType("contact1Type");
		
		Contact contactTwo = new Contact();
		contactTwo.setId(2);
		contactTwo.setFirstname("contact2FirstName");
		contactTwo.setLastname("contact2LastName");
		contactTwo.setStreet("contact2Street");
		contactTwo.setCity("contact2City");
		contactTwo.setState("contact2State");
		contactTwo.setZip("contact2Zip");
		contactTwo.setType("contact2Type");
		
		expectedContacts = Arrays.asList(new Contact[] { contactOne, contactTwo });
		
		Contact.setNextId(1);
	}
	
	public void testConstructorWithString() {
		UserRecord record = new UserRecord(testUserRecordString);
		
		assertEquals("username", record.getName());
		assertEquals("password", record.getPassword());
		assertEquals(2, record.getContactList().size());
		
		List actualContacts = record.getContactList();
		assertTrue(actualContacts.equals(expectedContacts));

		String expectedFullRecord = testUserRecordString + "\n";
		assertEquals(expectedFullRecord, record.getFullRecord());
	}
	
	public void testConstructorWithUser() {
		ContactsUser user = new ContactsUser();
		user.setUsername("username");
		user.setPassword("password");
		
		Iterator iterator = expectedContacts.iterator();
		while (iterator.hasNext()) {
			user.addContact((Contact) iterator.next());
		}
		UserRecord record = new UserRecord(user);
		
		assertEquals("username", record.getName());
		assertEquals("password", record.getPassword());
		assertEquals(2, record.getContactList().size());
		
		List actualContacts = record.getContactList();
		assertTrue(actualContacts.equals(expectedContacts));

		String expectedFullRecord = testUserRecordString + "\n";
		assertEquals(expectedFullRecord, record.getFullRecord());
	}
}