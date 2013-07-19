package com.lyw.resteasy.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;


public class CommonAnnotationResourceTest {
	
	@Test
	public void testPathParamDisplay(){
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/CommonAnnotationResource/123");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
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
	public void testDisplayNameAndLikeThings(){
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/CommonAnnotationResource/24/name/yiwei-liao/sport;sports=basketball/music;music1=jay;music2=westlife");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
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
	public void testDisplayAllThings(){
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/CommonAnnotationResource/24/name/sport;sports=basketball/music;music1=jay;music2=westlife");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
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
	public void testDisplayAllCookieParameter(){
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/CommonAnnotationResource/CookieParameter");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie", "sessionId=123498877778990");
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
	public void testDisplayAllFormParameter(){
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/CommonAnnotationResource/FormParameter");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Cookie", "SessionId=123498877778990;SessionId=123498877778991");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			 OutputStream os = connection.getOutputStream();
		      os.write("queryParam=1&queryParam=2&queryParam=3&".getBytes());
		      os.flush();
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
	public void testDisplayAllHeaderParameter(){
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/CommonAnnotationResource/HeaderParameter");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie", "SessionId=123498877778990");
			//connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
	public void testDisplayAllQueryParam(){
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/CommonAnnotationResource/QueryParameter?name=123&name=234");
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie", "sessionId=123498877778990");
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
