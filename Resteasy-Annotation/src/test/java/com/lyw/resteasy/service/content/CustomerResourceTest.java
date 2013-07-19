package com.lyw.resteasy.service.content;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import com.lyw.resteasy.beans.Address;
import com.lyw.resteasy.beans.Customer;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class CustomerResourceTest {
	@Test
	public void testCreateCustomer() throws Exception {
		System.out.println("*** Create a new Customer ***");
		Customer customer = new Customer();
		customer.setId("1");
		customer.setName("michael");
		Address address = new Address();
		address.setCity("ShangHai");
		address.setState("Not Married");
		address.setStreet("jinke road");
		address.setZip("12345");
		customer.setAddress(address);

		URL postUrl = new URL("http://localhost:8080/customers");
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type",
				"application/x-java-serialized-object");
		OutputStream os = connection.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(customer);
		oos.flush();
		Assert.assertEquals(HttpURLConnection.HTTP_CREATED,
				connection.getResponseCode());
		System.out
				.println("Location: " + connection.getHeaderField("Location"));
		connection.disconnect();
	}

	@Test
	public void testGetCustomer() throws Exception {
		URL postUrl = new URL("http://localhost:8080/customers/1");
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type",
				"application/x-java-serialized-object");
		ObjectInputStream ois = new ObjectInputStream(
				connection.getInputStream());
		Customer cust = (Customer) ois.readObject();
		System.out.println(cust);
		System.out.println(connection.getHeaderFields());
		connection.disconnect();
	}

	@Test
	public void testCreateCustomerXML() throws Exception {
		System.out.println("*** Create a new Customer ***");
		String customer = "<customer id=\"1\">" + "<address>"
				+ "<city>ShangHai</city>" + "<state>Not Married</state>"
				+ "<street>jinke road</street>" + "<zip>12345</zip>"
				+ "</address>" + "<name>michael</name>" + "</customer>";
		URL postUrl = new URL("http://localhost:8080/customers");
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type",
				"application/customize-xml");
		OutputStream os = connection.getOutputStream();
		os.write(customer.getBytes());
		os.flush();
		Assert.assertEquals(HttpURLConnection.HTTP_CREATED,
				connection.getResponseCode());
		System.out
				.println("Location: " + connection.getHeaderField("Location"));
		connection.disconnect();
	}

	@Test
	public void testGetCustomerXML() throws Exception {
		URL postUrl = new URL("http://localhost:8080/customers/1");
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type",
				"application/customize-xml");
		connection.setRequestProperty("Accept", "application/customize-xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
		System.out.println(connection.getResponseCode());
		connection.disconnect();
	}
	
}
