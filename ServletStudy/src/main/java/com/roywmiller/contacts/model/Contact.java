package com.roywmiller.contacts.model;

public class Contact {
	
	protected static int nextId = 1;	
	protected int id = 0;
	protected String firstname = "firstname";
	protected String lastname = "lastname";
	protected String street = "street";
	protected String city = "city";
	protected String state = "state";
	protected String zip = "zip";
	protected String type = "type";
	
	public static void setNextId(int anId) {
		Contact.nextId = anId;
	}
	
	public Contact() {
		this.id = nextId;
		++nextId;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String toString() {
		return "";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Contact))
			return false;
		
		Contact otherContact = (Contact)other;
		if (otherContact.getId() == this.getId() &&
			otherContact.getFirstname().equals(this.getFirstname()) &&
			otherContact.getLastname().equals(this.getLastname()) &&
			otherContact.getStreet().equals(this.getStreet()) &&
			otherContact.getCity().equals(this.getCity()) &&
			otherContact.getState().equals(this.getState()) &&
			otherContact.getZip().equals(this.getZip()) &&
			otherContact.getType().equals(this.getType()))
			return true;
		
		return false;
	}

	public int hashCode() {
		return String.valueOf(id).hashCode() +
				firstname.hashCode() + 
				lastname.hashCode() +
				street.hashCode() +
				city.hashCode() + 
				state.hashCode() +
				zip.hashCode() +
				type.hashCode();
	}
}