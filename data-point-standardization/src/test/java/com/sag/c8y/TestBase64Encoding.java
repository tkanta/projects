package com.sag.c8y;

import java.util.Base64;

public class TestBase64Encoding {

	public static void main(String[] args) {
		String tokenStr = "t79467893/amzadbasha.shaik@ispace.com:January#2019";
		Base64.Encoder encoder = Base64.getEncoder();
		String encodedTokenStr = encoder.encodeToString(tokenStr.getBytes());
		System.out.println(encodedTokenStr);
	}
}
