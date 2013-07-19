package com.lyw.resteasy.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;



public class SubResource1 {
	
	private SubResource2 subResource2 = new SubResource2();
	
	@Path("SubResource2")
	public SubResource2 dispatchSubResource2(){
		return subResource2;
	}
	
	@GET
	public void SubResource1Get(){
		System.out.println("this is SubResource1Get method!");
	}
}
