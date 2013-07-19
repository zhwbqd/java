package com.lyw.resteasy.service.client;

import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lyw.resteasy.beans.Customer;

public class CustomerClientTest {
	static CustomerClientInterface client = null;

	@BeforeClass
	public static void setUp() {
	 client = ProxyFactory.create(
				CustomerClientInterface.class, "http://localhost:8080");
	}

	@Test
	public void sendMessage2Server() {
		String response = client.sendMessage2Server("hello server");
		System.out.println(response);
	}

	@Test
	public void testGetResponseFromServer() {
		ClientResponse<Customer> cp = client.getResponseFromServer();
		System.out.println("status :" + cp.getStatus());
		System.out.println("entity is :" +cp.getEntity());
		System.out.println("headers :" + cp.getHeaders().values());
		System.out.println("attributes :" + cp.getAttributes());	
	}
	
	@Test
	public void testSetPathParam() {
		client.setPathParam("100");
	}
}
