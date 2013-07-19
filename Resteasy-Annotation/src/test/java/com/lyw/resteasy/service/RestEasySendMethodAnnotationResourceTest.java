package com.lyw.resteasy.service;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.junit.Test;

public class RestEasySendMethodAnnotationResourceTest {

	@Test
	public void testGetMethod() {
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/methodAnnotation/123");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
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
	public void testPostMethod() {
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/methodAnnotation");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "text/plain");
			OutputStream os = connection.getOutputStream();
			os.write("Hello Post Method!".getBytes("utf8"));
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
	public void retrieveMethod() {
	      DefaultHttpClient client = new DefaultHttpClient();
	      HttpPatch patch = new HttpPatch("http://localhost:8080/methodAnnotation");
	      HttpResponse response;
		try {
			response = client.execute(patch);
		    System.out.println(response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 private static class HttpPatch extends HttpPost
	   {
	      public HttpPatch(String s)
	      {
	         super(s);
	      }

	      public String getMethod()
	      {
	         return "RETRIEVE";
	      }
	   }
}
