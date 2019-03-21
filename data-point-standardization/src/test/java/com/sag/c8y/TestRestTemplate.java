package com.sag.c8y;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sag.c8y.inseego.util.Base64Util;
import com.sag.c8y.inseego.util.RestTemplateUtil;

public class TestRestTemplate {

	public static void main(String[] args) {
		/*RestTemplate restTemplate = new RestTemplate();
		
		String endPointUrl = "https://t79467893f.cumulocity.com/measurement/measurements";
		String tokenStr = "t79467893/amzadbasha.shaik@ispace.com:January#2019";
		String encodedTokenStr = Base64Util.encode(tokenStr);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + encodedTokenStr);
		
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		 
		ResponseEntity<String> result = restTemplate.exchange(endPointUrl, HttpMethod.GET, requestEntity, String.class);*/
		
		
		String endPointUrl = "https://t79467893f.cumulocity.com/measurement/measurements";
		String tokenStr = "t79467893/amzadbasha.shaik@ispace.com:January#2019";
		String encodedTokenStr = Base64Util.encode(tokenStr);
		
		long before = System.currentTimeMillis();
		RestTemplateUtil.get(endPointUrl, encodedTokenStr);
		long after = System.currentTimeMillis();
		System.out.println((after-before));
		System.out.println(RestTemplateUtil.get(endPointUrl, encodedTokenStr));
		
	}
	
}
