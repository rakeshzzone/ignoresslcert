package com.apitest.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class TestIgnoreSSLcert {

	public static void main(String[] args) {
		try {
				String line = "";
				StringBuffer result = new StringBuffer();
				
				SSLContextBuilder builder = new SSLContextBuilder();
			    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
			            builder.build());
			    CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
			            sslsf).build();
			    
			    HttpGet httpGet = new HttpGet("URL");
			    httpGet.setHeader("", ""); // add headers here 
			    
			    CloseableHttpResponse response = httpclient.execute(httpGet);  // Executing get
			    
			    // reading response 
			    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				while ((line = rd.readLine()) != null) {
					 result.append(line);
				}
				
			System.out.println("response : "+result);
			httpclient.getConnectionManager().closeExpiredConnections();
			rd.close();
		}catch (Exception e) {

		}
	}

}
