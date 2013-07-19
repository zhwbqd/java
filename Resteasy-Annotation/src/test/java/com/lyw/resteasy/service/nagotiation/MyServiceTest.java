package com.lyw.resteasy.service.nagotiation;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class MyServiceTest {

	@Test
	public void testGetSomething() {
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/myservice");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Accept",
					"application/json;q=0.6, application/xml;q=0.7");
			connection.setRequestProperty("Accept-Language","en");
			connection.setRequestProperty("Accept-Encoding","gzip, deflate");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
			System.out.println(connection.getResponseCode());
			System.out.println(connection.getHeaderFields());
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
