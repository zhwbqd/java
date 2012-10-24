package com.hp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



public class GetWeatherJson {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		HttpClient httpclient=new DefaultHttpClient();
		
		HttpGet get=new HttpGet("http://localhost:8080/jquerydemo");
		
		HttpResponse response=httpclient.execute(get);
		
		HttpEntity entity=response.getEntity();
		
		if(null!= entity){
			InputStream in=entity.getContent();
			
			BufferedReader br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
			
			String line=null;
			
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
			
			in.close();
		}
		
		httpclient.getConnectionManager().shutdown();
		
	}
}
