package com.lyw.resteasy.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/Precedence")
public class PrecedenceRuleResource {
	
//	@GET
//	@Path("user/{id}/{name}/address")
//	public void Method1(@PathParam("id") String id, @PathParam("name") String name){
//		System.out.println("this is method1");
//		System.out.println("this is method1's id: " + id);
//		System.out.println("this is method1's name: " + name);
//	}
	
	@GET
	@Path("user/{id:.+}/address")
	public void Method2(@PathParam("id") String id){
		System.out.println("this is method2");
		System.out.println("this is method2's id: " + id);
	}
	
	@GET
	@Path("user/{id}/address")
	public void Method3(@PathParam("id") String id){
		System.out.println("this is method3");
		System.out.println("this is method3's id: " + id);
	}
	
	@GET
	@Path("user/{id:.+}")
	public void Method4(@PathParam("id") String id){
		System.out.println("this is method4");
		System.out.println("this is method4's id: " + id);
	}
	
	@GET
	@Path("user/{id}")
	public void Method5(@PathParam("id") String id){
		System.out.println("this is method5");
		System.out.println("this is method5's id: " + id);
	}
}
