package com.lyw.resteasy.service.content;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

@Provider
@Consumes("application/customize-xml")
@Produces("application/customize-xml")
public class JAXBMarshaller implements MessageBodyWriter<Object>,
		MessageBodyReader<Object> {

	@Context
	private Providers providers;

	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return type.isAnnotationPresent(XmlRootElement.class);
	}

	public Object readFrom(Class<Object> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		JAXBContext ctx = providers.getContextResolver(
				JAXBContext.class, mediaType).getContext(type);
		System.out.println("find JAXBContext in readForm method : " + ctx);
		Object o = null;
		try {
			 o = ctx.createUnmarshaller().unmarshal(entityStream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			return o;
		}
		
	}

	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return type.isAnnotationPresent(XmlRootElement.class);
	}

	public long getSize(Object t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return -1;
	}

	public void writeTo(Object t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		// TODO Auto-generated method stub
		JAXBContext ctx = providers.getContextResolver(
				JAXBContext.class, mediaType).getContext(type);
		System.out.println("find JAXBContext in writeTo method : " + ctx);
		try {
			 ctx.createMarshaller().marshal(t, entityStream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
