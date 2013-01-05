package com.roywmiller.contacts.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactsUser {

	protected String username = "";
	protected String password = "";
	protected List contactList = new ArrayList();

	public ContactsUser() {
	}

	public ContactsUser(String username, String password, List contactList) {
		this.username = username;
		this.password = password;
		this.contactList.addAll(contactList);
	}

	public boolean hasContacts() {
		return !contactList.isEmpty();
	}

	public void addContact(Contact aContact) {
		contactList.add(aContact);
	}

	public void removeContact(Contact aContact) {
		contactList.remove(aContact);
	}

	public void removeContact(int id) {
		Contact toRemove = findContact(id);
		contactList.remove(toRemove);
	}

	protected Contact findContact(int id) {
		Contact found = null;

		Iterator iterator = contactList.iterator();
		while (iterator.hasNext()) {
			Contact current = (Contact) iterator.next();
			if (current.getId() == id)
				found = current;
		}
		return found;
	}

	public List getContacts() {
		return contactList;
	}

	public void setContacts(List contacts) {
		this.contactList = contacts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}