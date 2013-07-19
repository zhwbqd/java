package com.lyw.resteasy.service.content;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Provider
@Produces("application/x-java-serialized-object")
@Consumes("application/x-java-serialized-object")
public class JavaMarshaller implements MessageBodyReader, MessageBodyWriter {
	public boolean isReadable(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		System.out.println("execute JavaMarshaller isReadable method!");
		return Serializable.class.isAssignableFrom(type);
	}

	public Object readFrom(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, InputStream is) throws IOException,
			WebApplicationException {
		System.out.println("execute JavaMarshaller readFrom method!");
		System.out.println("Class type is : " + type);
		System.out.println("type is : " + genericType);
		for(Annotation annt : annotations){
			System.out.println("annotations is : " + annt.getClass().getName());
		}
		System.out.println("MediaType is : " + mediaType);
		System.out.println("httpHeaders is " + httpHeaders);
		ObjectInputStream ois = new ObjectInputStream(is);
		try {
			return ois.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isWriteable(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		System.out.println("execute JavaMarshaller isWriteable method!");
		return Serializable.class.isAssignableFrom(type);
	}

	public long getSize(Object o, Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		System.out.println("execute JavaMarshaller getSize method!");
		return -1;
	}

	public void writeTo(Object o, Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, OutputStream os) throws IOException,
			WebApplicationException {
		System.out.println("Object is : " + o);
		System.out.println("Class type is : " + type);
		System.out.println("type is : " + genericType);
		for(Annotation annt : annotations){
			System.out.println("annotations is : " + annt.getClass().getName());
		}
		System.out.println("MediaType is : " + mediaType);
		System.out.println("httpHeaders is " + httpHeaders);
		System.out.println("execute JavaMarshaller writeTo method!");
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(o);
	}
}
