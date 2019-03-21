package com.sag.c8y.inseego.util;

import java.util.Base64;

public class Base64Util {

	public static String encode(String token) {
		Base64.Encoder encoder = Base64.getEncoder();
		String encodedTokenStr = encoder.encodeToString(token.getBytes());
		return encodedTokenStr;
	}
}
