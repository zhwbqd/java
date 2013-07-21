package com.hp.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/library")
public class Library {
	public static List<Book> books = new ArrayList<Book>();
	static {
		books.add(new Book("huhu", "huhu"));
		books.add(new Book("haha", "haha"));
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/books")
	public List<Book> listBooks() {
		return books;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/book/{id}")
	public Book getBook(@PathParam("id") String id) {
		if ("1".equals(id))
			return new Book("huhu", "huhu");
		else
			return new Book("haha", "haha");
	}
}
