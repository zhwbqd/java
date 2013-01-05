package com.roywmiller.contacts.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.roywmiller.contacts.model.Contact;
import com.roywmiller.contacts.model.ContactsUser;

public class UserRecord {
	protected String name = "";
	protected String password = "";
	protected List contactList = new ArrayList();
	protected String fullRecord = "";

	protected EncoderDecoder encoderDecoder = new EncoderDecoder();

	public UserRecord(String recordString) {
		StringTokenizer tokenizer = new StringTokenizer(recordString, " ");
		this.name = tokenizer.nextToken();
		this.password = encoderDecoder.decode(tokenizer.nextToken());
		if (tokenizer.hasMoreTokens())
			this.contactList = createContacts(tokenizer.nextToken());
		
		this.fullRecord = recordString + "\n"; 
	}
	
	public UserRecord(ContactsUser user) {
		this.name = user.getUsername();
		this.password = user.getPassword();
		this.contactList = user.getContacts();
		
		StringBuffer recordString = new StringBuffer();
		recordString.append(user.getUsername());
		recordString.append(" ");
		String encodedPassword = encoderDecoder.encode(user.getPassword());
		recordString.append(encodedPassword);		
		recordString.append(" ");
		recordString.append(createContactsString(user));
		this.fullRecord = recordString.toString() + "\n";
	}
	
	protected String createContactsString(ContactsUser user) {
		StringBuffer contactsString = new StringBuffer();
		
		List contacts = user.getContacts();
		Iterator iterator = contacts.iterator();
		while (iterator.hasNext()) {
			Contact each = (Contact) iterator.next();

			if (contactsString.length() > 0)
				contactsString.append("|");
			contactsString.append(each.getFirstname());
			contactsString.append(",");
			contactsString.append(each.getLastname());
			contactsString.append(",");
			contactsString.append(each.getStreet());
			contactsString.append(",");
			contactsString.append(each.getCity());
			contactsString.append(",");
			contactsString.append(each.getState());
			contactsString.append(",");
			contactsString.append(each.getZip());
			contactsString.append(",");
			contactsString.append(each.getType());
		}

		return contactsString.toString();
	}

	protected List createContacts(String contactsListString) {
		contactList = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(contactsListString, "|");
		while (tokenizer.hasMoreTokens()) {
			String contactString = tokenizer.nextToken();
			Contact contact = createContact(contactString);
			contactList.add(contact);
		}

		return contactList;
	}

	protected Contact createContact(String contactString) {
		StringTokenizer tokenizer = new StringTokenizer(contactString, ",");
		Contact contact = new Contact();
		while (tokenizer.hasMoreTokens()) {
			contact.setFirstname(tokenizer.nextToken());
			contact.setLastname(tokenizer.nextToken());
			contact.setStreet(tokenizer.nextToken());
			contact.setCity(tokenizer.nextToken());
			contact.setState(tokenizer.nextToken());
			contact.setZip(tokenizer.nextToken());
			contact.setType(tokenizer.nextToken());
		}
		return contact;
	}

	public List getContactList() {
		return contactList;
	}

	public void setContactList(List contactList) {
		this.contactList = contactList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullRecord() {
		return this.fullRecord;
	}
	
	public void setFullRecord(String aFullRecord) {
		this.fullRecord = aFullRecord;
	}
}