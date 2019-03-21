package com.sag.c8y.inseego.util;

import java.util.Arrays;

public class C8yUtil {

	public static String getTenantEncodedToken(String tenant, String userName, String password) {
		
		String tokenStr = tenant+"/"+userName+":"+password;
		String encodedTokenStr = Base64Util.encode(tokenStr);
		return encodedTokenStr;
	}
	
	public static String getTenantUrl(String tenant) {
		String url = "https://"+tenant+".cumulocity.com";
		return url;
	}
	
	public static String buildC8yUrl(String url, String... paths) {
		StringBuilder builder = new StringBuilder(url);
		Arrays.asList(paths).forEach(path -> {
			builder.append("/").append(path);
		});
		
		return builder.toString();
	}
}
