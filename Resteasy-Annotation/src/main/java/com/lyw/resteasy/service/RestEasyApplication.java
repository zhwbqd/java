package com.lyw.resteasy.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.lyw.resteasy.service.client.CustomerServer;
import com.lyw.resteasy.service.content.CustomerResource;
import com.lyw.resteasy.service.content.JAXBContextResolver;
import com.lyw.resteasy.service.content.JAXBMarshaller;
import com.lyw.resteasy.service.content.JavaMarshaller;
import com.lyw.resteasy.service.jaxb.CustomerResourceJAXB;
import com.lyw.resteasy.service.nagotiation.MyService;


public class RestEasyApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	   public RestEasyApplication() {
	     singletons.add(new RestEasySendMethodAnnotationResource());
	     singletons.add(new RootResource());
	     singletons.add(new CommonAnnotationResource());
	     singletons.add(new PrecedenceRuleResource());
	     singletons.add(new CustomerResourceJAXB());
	     //Content nagotiation
	     singletons.add(new MyService());
	     singletons.add(new CustomerResource());
	     singletons.add(new CustomerServer());
	     empty.add(JavaMarshaller.class);
	     empty.add(JAXBContextResolver.class);
	     empty.add(JAXBMarshaller.class);
	   }

	   @Override
	   public Set<Class<?>> getClasses() {
	      return empty;
	   }

	   @Override
	   public Set<Object> getSingletons() {
	      return singletons;
	   }
}
