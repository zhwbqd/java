package com.lyw.resteasy.service;

import javax.ws.rs.GET;

public class SubResource2 {
	
	@GET
	public void SubResource2Get(){
		System.out.println("this is SubResource2Get method!");
	}
}
