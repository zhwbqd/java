package com.lyw.resteasy.service.nagotiation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Variant;

import com.lyw.resteasy.beans.Address;
import com.lyw.resteasy.beans.Customer;

@Path("/myservice")
public class MyService {

	@GET
	public Response getSomething(@Context Request request) {
		List<Variant> variants = new ArrayList();
//
//		variants.add(new Variant(MediaType.valueOf("application/xml"),
//				new Locale("en"), "deflate"));
//		variants.add(new Variant(MediaType.valueOf("application/xml"),
//				new Locale("es"), "deflate"));
//		variants.add(new Variant(MediaType.valueOf("application/json"),
//				new Locale("en"), "deflate"));
//		variants.add(new Variant(MediaType.valueOf("application/json"),
//				new Locale("es"), "deflate"));
//		variants.add(new Variant(MediaType.valueOf("application/xml"),
//				new Locale("en"), "gzip"));
//		variants.add(new Variant(MediaType.valueOf("application/xml"),
//				new Locale("es"), "gzip"));
//		variants.add(new Variant(MediaType.valueOf("application/json"),
//				new Locale("en"), "gzip"));
//		variants.add(new Variant(MediaType.valueOf("application/json"),
//				new Locale("es"), "gzip"));
//		// Pick the variant
//		Variant v = request.selectVariant(variants);
//		System.out.println("Variant value is " + v);
		
		Variant.VariantListBuilder vb = Variant.VariantListBuilder.newInstance();
		vb.mediaTypes(MediaType.valueOf("application/xml"),
		MediaType.valueOf("application/json"))
		.languages(new Locale("en"), new Locale("es"))
		.encodings("deflate", "gzip")
		.add()
		.mediaTypes(MediaType.valueOf("text/plain"))
		.languages(new Locale("en"), new Locale("es"), new Locale("fr"))
		.encodings("compress");
		variants = vb.build();
		System.out.println("Variant list value is " + variants);
		Variant v = request.selectVariant(variants);
		
		Customer customer = new Customer();
		customer.setId("1");
		customer.setName("michael");
		Address address = new Address();
		address.setCity("ShangHai");
		address.setState("Not Married");
		address.setStreet("jinke road");
		address.setZip("12345");
		customer.setAddress(address);
		ResponseBuilder builder = Response.ok(customer);
		builder.type(v.getMediaType()).language(v.getLanguage())
				.header("Content-Encoding", v.getEncoding());
		return builder.build();
	}
}