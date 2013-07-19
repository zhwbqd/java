package com.lyw.resteasy.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="address")
public class Address implements Serializable{
	
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String zip;
	@XmlElement
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	@XmlElement
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@XmlElement
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@XmlElement
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
