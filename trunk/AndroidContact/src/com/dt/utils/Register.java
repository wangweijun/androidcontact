package com.dt.utils;

import org.apache.http.HttpResponse;

public class Register {

	public static boolean doRegister(String registerUrl, String username,
			String password) {

		boolean isSuccess = false;

		HttpResponse response = com.dt.http.HttpSender.doPost(registerUrl,
				username, password);
		String responseString = com.dt.http.HttpSender
				.getResponseContent(response);

		if (responseString.substring(0, 0) == "1") {
			
			isSuccess = true;
			
		} else {
			
			isSuccess = false;
			
		}

		return isSuccess;
	}
}
