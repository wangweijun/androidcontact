package com.dt.utils;

import org.apache.http.HttpResponse;

import android.util.Log;

public class Authentication {

	public static boolean doLogin(String loginUrl, String username,
			String password) {
		//boolean isSuccess = false;

		HttpResponse response = com.dt.http.HttpSender.doPost(loginUrl,
				username, password);

		String responseString = com.dt.http.HttpSender
				.getResponseContent(response);
		String result = responseString.substring(0, 1).trim();
		Log.d("Login resultString", result);

		if ( result.equalsIgnoreCase("1")) {
			
			return true;
			
		} else {
			
			return false;
			
		}

		//return isSuccess;

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
