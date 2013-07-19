package com.lyw.resteasy.service;

import java.util.List;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

@Path("/CommonAnnotationResource")
public class CommonAnnotationResource {
	@GET
	@Path("{id}")
	public void pathParamDisplay(@PathParam("id") int id) {
		System.out.println("id is : " + id);
	}

	@GET
	@Path("/{age:\\d{1,2}}/name/{first}-{last}/{like:.+}")
	public void displayNameAndLikeThings(@PathParam("first") String firstName,
			@PathParam("last") String lastName,
			@MatrixParam("sports") String sport,
			@PathParam("like") List<PathSegment> likes) {
		System.out.println("My name is " + lastName + "." + firstName);
		System.out.println("My favorite sport is : " + sport);
		for (PathSegment ps : likes) {
			System.out.println("path : " + ps.getPath());
			System.out.println(ps.getMatrixParameters());
		}
	}

	@GET
	@Path("/{age:\\d{1,2}}/name/{like:.+}")
	public void displayAllThings(@Context UriInfo info) {

		System.out.println("path : " + info.getPath());
		System.out.println("absolute path : " + info.getAbsolutePath());

		System.out.println(info.getMatchedURIs());
		List<PathSegment> pses = info.getPathSegments();
		for (PathSegment ps : pses) {
			System.out.println("path : " + ps.getPath());
			System.out.println(ps.getMatrixParameters());
		}
		System.out.println(info.getPathParameters());
		System.out.println(info.getQueryParameters());
	}

	@GET
	@Path("/QueryParameter")
	public void displayAllQueryParam(@QueryParam("") List<String> param,
			@QueryParam("name") List<String> params) {
		System.out.println(params);
	}

	@GET
	@Path("/CookieParameter")
	public void displayAllCookieParameter(
			@CookieParam("sessionId") String sessionIds) {
		System.out.println("SessionId : " + sessionIds);
	}

	@GET
	@Path("/HeaderParameter")
	public void displayAllHeaderParameter(
			@HeaderParam("Cookie") String header1) {
		System.out.println("Cookie : " + header1);
	}
	
	@POST
	@Path("/FormParameter")
	public void displayAllFormParameter(
			@FormParam("queryParam") List<String> queryParams) {
		System.out.println(queryParams);
	}
}
