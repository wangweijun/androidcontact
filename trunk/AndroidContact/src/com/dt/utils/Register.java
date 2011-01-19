package com.dt.utils;

import org.apache.http.HttpResponse;

public class Register {

	public static boolean doRegister(String registerUrl, String username,
			String password) {

		HttpResponse response = com.dt.http.HttpSender.doPost(registerUrl,
				username, password);
		String responseString = com.dt.http.HttpSender
				.getResponseContent(response);

		if (responseString == "1") {
			return true;
		} else {
			return false;
		}

	}
}
