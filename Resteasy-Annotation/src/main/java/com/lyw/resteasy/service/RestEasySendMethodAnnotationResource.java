package com.lyw.resteasy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.StreamingOutput;

import com.lyw.resteasy.custom.annotation.RETRIEVE;

@Path("/methodAnnotation")
public class RestEasySendMethodAnnotationResource {

	@GET
	@POST
	@Path("{id:.+}/abc")
	public void getMethod() {
		System.out.println("This is getMethod!");

	}

	@POST
	@Path("id")
	//@Consumes("*/*")
	public StreamingOutput postMethod(InputStream in) {
		System.out.print("This is postMethod!");

		BufferedReader breader = new BufferedReader(new InputStreamReader(in));

		String s = null;
		try {
			while ((s = breader.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new StreamingOutput() {
			public void write(OutputStream output) throws IOException{
				output.write("I have recieved your message,Good luck guys!".getBytes());
				output.close();
			}
		};
	}
	
	@RETRIEVE
	public void retrieveMethod(){
		System.out.println("This is retrieveMethod!");
	}
}
