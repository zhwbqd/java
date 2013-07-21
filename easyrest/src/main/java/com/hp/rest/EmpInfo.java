package com.hp.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/empInfo")
public class EmpInfo {
	public static List<Employee> emp = new ArrayList<Employee>();
	static {
		emp.add(new Employee("jack", 10, 23));
		emp.add(new Employee("Joye", 23, 24));
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/emplist")
	public List<Employee> listEmp() {
		return emp;
	}

}
