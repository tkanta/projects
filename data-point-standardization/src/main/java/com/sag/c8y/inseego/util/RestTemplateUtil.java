package com.sag.c8y.inseego.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {
	
	private static RestTemplate restTemplate = null;
	
	static {
		restTemplate = new RestTemplate();
	}
	
	private static ResponseEntity<String> invoke(String url, HttpMethod type, String token, String jsonPayload) {
		
		//RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + token);
		
		HttpEntity<String> requestEntity = null;
		if(null != jsonPayload) {
			requestEntity = new HttpEntity<>(jsonPayload, headers);
		}else {
			requestEntity = new HttpEntity<>(headers);
		}
		
		ResponseEntity<String> result = null;
		
		synchronized(RestTemplateUtil.class) {
			result = restTemplate.exchange(url, type, requestEntity, String.class);
		}
		
		
		return result;
	}
	
	public static String get(String url, String auth) {
		
		ResponseEntity<String> result = null;
		
		try {
			result = invoke(url, HttpMethod.GET, auth, null);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

		return result == null ? IConstants.EMPTY_STRING : result.getBody();
	}
	
	public static String post(String url, String authStr, String jsonPayload) {
		ResponseEntity<String> result = null;
		
		try {
			result = invoke(url, HttpMethod.POST, authStr, null);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		

		return result == null ? IConstants.EMPTY_STRING : result.getBody();
	}
}
