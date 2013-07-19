package com.lyw.resteasy.service.jaxb;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lyw.resteasy.beans.Address;
import com.lyw.resteasy.beans.Customer;

@Path("/Customer")
public class CustomerResourceJAXB {
	
	private Map<String, Customer> customers = new HashMap<String, Customer>();
	
	public CustomerResourceJAXB(){
		//michael
		Customer customer = new Customer();
		customer.setId("1");
		customer.setName("michael");
		Address address = new Address();
		address.setCity("ShangHai");
		address.setState("Not Married");
		address.setStreet("jinke road");
		address.setZip("12345");
		customer.setAddress(address);
		customers.put(customer.getId(), customer);
		//john
		customer = new Customer();
		customer.setId("2");
		customer.setName("john");
	    address = new Address();
		address.setCity("ShangHai");
		address.setState("Not Married too");
		address.setStreet("jinke road");
		address.setZip("12345");
		customer.setAddress(address);
		customers.put(customer.getId(), customer);
	}
	
	@POST
	@Path("getAllCustomers")
	@Produces({"application/xml", "application/json"})
	//@Consumes("application/xml")
	public Map<String, Customer> getAllCustomers(){
		return customers;
	}
//	
//	@POST
//	@Path("getAllCustomers")
//	@Produces("application/json")
//	public Map<String, Customer> getAllCustomersJSON(){
//		return customers;
//	}
}
