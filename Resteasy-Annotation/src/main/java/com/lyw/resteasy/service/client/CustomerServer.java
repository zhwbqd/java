package com.lyw.resteasy.service.client;

import com.lyw.resteasy.beans.Address;
import com.lyw.resteasy.beans.Customer;

public class CustomerServer implements CustomerInterface {

	public String sendMessage2Server(String message) {
		System.out.println(message);
		return "I have recieved your message!";
	}

	public Customer getResponseFromServer() {
		Customer customer = new Customer();
		customer.setId("1");
		customer.setName("michael");
		Address address = new Address();
		address.setCity("ShangHai");
		address.setState("Not Married");
		address.setStreet("jinke road");
		address.setZip("12345");
		customer.setAddress(address);
		return customer;
	}

	public void setPathParam(String id) {
		System.out.println(id);
	}

	

}
