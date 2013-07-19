package com.lyw.resteasy.service.content;


import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.lyw.resteasy.beans.Customer;

@Path("/customers")
public class CustomerResource
{
   private Map<String, Customer> customerDB = new ConcurrentHashMap<String, Customer>();
   private AtomicInteger idCounter = new AtomicInteger();

   @POST
   @Consumes("application/x-java-serialized-object")
   public Response createCustomer(Customer customer)
   {
      customer.setId(String.valueOf(idCounter.incrementAndGet()));
      customerDB.put(customer.getId(), customer);
      System.out.println("Created customer " + customer.getId());
      return Response.created(URI.create("/customers/" + customer.getId())).build();

   }

   @GET
   @Path("{id}")
   @Produces("application/x-java-serialized-object")
   public Customer getCustomer(@PathParam("id") String id)
   {
      Customer customer = customerDB.get(id);
      if (customer == null)
      {
         throw new WebApplicationException(Response.Status.NOT_FOUND);
      }
      return customer;
   }

   @PUT
   @Path("{id}")
   @Consumes("application/x-java-serialized-object")
   public void updateCustomer(@PathParam("id") int id, Customer update)
   {
      Customer current = customerDB.get(id);
      if (current == null) throw new WebApplicationException(Response.Status.NOT_FOUND);
	    System.out.println("This is update customer method!");
   }
   
   @POST
   @Consumes("application/customize-xml")
   public Response createCustomerXML(Customer customer)
   {
      customer.setId(String.valueOf(idCounter.incrementAndGet()));
      customerDB.put(customer.getId(), customer);
      System.out.println("Created customer " + customer.getId());
      return Response.created(URI.create("/customers/" + customer.getId())).build();

   }
   
   @GET
   @Path("{id}")
   @Produces("application/customize-xml")
   public Customer getCustomerXML(@PathParam("id") String id)
   {
      Customer customer = customerDB.get(id);
      if (customer == null)
      {
         throw new WebApplicationException(Response.Status.NOT_FOUND);
      }
      return customer;
   }
}
