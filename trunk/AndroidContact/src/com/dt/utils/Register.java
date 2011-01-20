package com.dt.utils;

import org.apache.http.HttpResponse;

import android.util.Log;

public class Register {

	public static boolean doRegister(String registerUrl, String username,
			String password) {

		//boolean isSuccess = false;

		HttpResponse response = com.dt.http.HttpSender.doPost(registerUrl,
				username, password);
		String responseString = com.dt.http.HttpSender
				.getResponseContent(response);
		
		String result = responseString.substring(0, 1).trim();
		Log.d("Register resultString", result);

		if ( result.equalsIgnoreCase("1")) {
			
			return true;
			
		} else {
			
			return false;
			
		}

		//return isSuccess;
	}
}
