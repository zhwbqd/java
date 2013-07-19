package com.lyw.resteasy.service.content;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.lyw.resteasy.beans.Customer;

@Provider
//@Consumes("application/customize-xml1")
//@Produces("application/customize-xml1")
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

	public JAXBContextResolver() {

	}

	public JAXBContext getContext(Class<?> type) {
		JAXBContext ctx = null;
		if (type.equals(Customer.class)) {
			try {
				ctx = JAXBContext.newInstance(Customer.class);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				return ctx;
			}
		} else {
			return null;
		}
	}

}
