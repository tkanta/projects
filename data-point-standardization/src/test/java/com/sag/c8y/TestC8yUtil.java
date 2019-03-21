package com.sag.c8y;

import com.sag.c8y.inseego.util.C8yUtil;

public class TestC8yUtil {

	public static void main(String[] args) {
		String url = C8yUtil.getTenantUrl("boydmachtolff");
		String completePath = C8yUtil.buildC8yUrl(url, "1234" );
		System.out.println(completePath);
	}
}
