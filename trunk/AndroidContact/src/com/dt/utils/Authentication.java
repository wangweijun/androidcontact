package com.dt.utils;

import org.apache.http.HttpResponse;

public class Authentication {

	public static boolean doLogin(String loginUrl, String username,
			String password) {
		boolean isSuccess = false;

		HttpResponse response = com.dt.http.HttpSender.doPost(loginUrl,
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

	public static boolean matchingPassword(String inputPassword,
			String inputConfirmPassword) {
		if (inputPassword.equalsIgnoreCase(inputConfirmPassword)) {
			return true;
		} else {
			return false;
		}

	}

}
