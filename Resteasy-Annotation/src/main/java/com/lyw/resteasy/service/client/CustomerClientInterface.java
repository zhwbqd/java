package com.lyw.resteasy.service.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.client.ClientResponse;

import com.lyw.resteasy.beans.Customer;

@Path("CustomerClientAndServer")
public interface CustomerClientInterface {
	
	@POST
	@Path("sendMessage2Server")
	@Consumes("text/plain")
	String sendMessage2Server(String message);
	
	@POST
	@Path("getResponseFromServer")
	@Produces("application/xml")
	ClientResponse<Customer> getResponseFromServer();
	
	@POST
	@Path("setPathParam/{id}")
	void setPathParam(@PathParam("id") String id);
}
