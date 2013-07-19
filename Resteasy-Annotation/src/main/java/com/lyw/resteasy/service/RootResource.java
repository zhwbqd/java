package com.lyw.resteasy.service;

import javax.ws.rs.Path;

@Path("/")
public class RootResource {
	private SubResource1 subResource1 = new SubResource1();
	
	@Path("/SubResource1")
	public Object dispatchSubResource1(){
		System.out.println("this is dispatchSubResource1 method!");
		return subResource1;
	}
	
	@Path("SubResource1")
	public Object dispatchSubResource2(){
		System.out.println("this is dispatchSubResource1 method!");
		return subResource1;
	}
}
