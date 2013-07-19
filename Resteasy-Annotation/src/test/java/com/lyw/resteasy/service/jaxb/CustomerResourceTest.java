package com.lyw.resteasy.service.jaxb;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class CustomerResourceTest {

	@Test
	public void testGetAllCustomers() {
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/Customer/getAllCustomers");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Accept","application/json;q=0.6, application/xml;q=0.7");
			OutputStream os = connection.getOutputStream();
			//os.write("<element>ok</element>".getBytes("utf8"));
			os.write("<element>ok</element>".getBytes("utf8"));
			os.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			System.out.println(connection.getResponseCode());
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllCustomersJSON() {
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/Customer/getAllCustomers");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept","*/*");
//			OutputStream os = connection.getOutputStream();
			//os.write("<element>ok</element>".getBytes("utf8"));
//			os.write("<element>ok</element>".getBytes("utf8"));
//			os.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			System.out.println(connection.getResponseCode());
			connection.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
