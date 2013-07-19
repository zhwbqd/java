package com.lyw.resteasy.service.content;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.lyw.resteasy.beans.Customer;

@Provider
public class CustomerResolver implements ContextResolver<JAXBContext> {
	JAXBContext ctx = null;
	
	public CustomerResolver(){
		try {
			ctx = JAXBContext.newInstance("com.lyw.resteasy.beans");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public JAXBContext getContext(Class<?> type) {
		if (type.equals(Customer.class)) {
			return ctx;
		} else {
			return null;
		}
	}
}
