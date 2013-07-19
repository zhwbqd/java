package com.lyw.resteasy.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class PrecedenceRuleResourceTest {
	
	@Test
	public void Precedence() {
		URL postUrl;
		try {
			postUrl = new URL("http://localhost:8080/Precedence/user/123/address");
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
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
	
}
