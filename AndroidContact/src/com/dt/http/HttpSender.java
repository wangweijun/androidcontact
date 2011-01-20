package com.dt.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class HttpSender {

	// //////////////////send post message/////////////////////////////

	public static HttpResponse doPost(String url, String data) {
		HttpClient httpclient = new DefaultHttpClient();

		HttpPost postRequest = new HttpPost(url);
		postRequest.setHeader("Accept", "text/xml");
		postRequest.setHeader("Content-Type", "application/xml");

		HttpResponse response = null;

		try {
			StringEntity strEntity = new StringEntity(data);
			strEntity.setContentType("application/xml");

			postRequest.setEntity(strEntity);

			response = httpclient.execute(postRequest);
			Log.d("Yahoo", "Successful!");

		} catch (Exception e) {
			Log.d("Process status", "Proccess fail!");

		}

		if (response != null) {
			Log.d("AndroidContact", "Successful!");
		} else {
			Log.d("Fail", "Post Fail!");
		}

		return response;

	}

	public static HttpResponse doPost(String url, String dataString,
			String userName, String passWord) {
		HttpClient httpclient = new DefaultHttpClient();
		
		byte[] zippedDataByte = com.dt.utils.DataUtils.compress(dataString.getBytes());
		String zippedDataString = zippedDataByte.toString();
		

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("user", userName));
		formparams.add(new BasicNameValuePair("pass", passWord));
		formparams.add(new BasicNameValuePair("data", zippedDataString));
		UrlEncodedFormEntity entity = null;

		HttpPost postRequest = new HttpPost(url);
		// postRequest.setHeader("Accept", "text/xml"); 				//xai la loi~ ngay!
		// postRequest.setHeader("Content-Type", "application/xml");

		HttpResponse response = null;

		try {
			entity = new UrlEncodedFormEntity(formparams);

			postRequest.setEntity(entity);
			response = httpclient.execute(postRequest);
			Log.d("Yahoo", "Successful!");

		} catch (Exception e) {
			Log.d("Process status", "Proccess fail!");

		}

		if (response != null) {
			Log.d("AndroidContact", "Successful! Status code: "
					+ response.getStatusLine().getStatusCode());
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				InputStream instream = null;

				try {
					instream = responseEntity.getContent();
					byte[] buff = new byte[128];
					// ;
					instream.read(buff);

					String responseContent = new String(buff);
					Log.d("Response", responseContent);

				} catch (IOException ex) {

					try {
						throw ex;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (RuntimeException ex) {

					throw ex;
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			} else {
				Log.d("ResponseEntity", "null");
			}
		} else {
			Log.d("Fail", "Post Fail!");
		}

		return response;

	}

	// / get the response string from server
	public static String getResponseContent(HttpResponse response) {
		String responseString = "";

		if (response != null) {
			Log.d("AndroidContact", "Successful! Status code: "
					+ response.getStatusLine().getStatusCode());
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				InputStream instream = null;

				try {
					instream = responseEntity.getContent();
					byte[] buff = new byte[128];
					// ;
					instream.read(buff);

					responseString = new String(buff);
					Log.d("Response", responseString);

				} catch (IOException ex) {

					try {
						throw ex;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (RuntimeException ex) {

					throw ex;
				} finally {
					try {
						instream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			} else {
				Log.d("ResponseEntity", "null");
			}
		} else {
			responseString += "The Response is Null";
			Log.d("Fail", "Post Fail!");
		}

		return responseString;

	}

	// /

	public static HttpResponse doPost(String url, String userName,
			String passWord) {
		HttpClient httpclient = new DefaultHttpClient();

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("user", userName));
		formparams.add(new BasicNameValuePair("pass", passWord));

		UrlEncodedFormEntity entity = null;

		HttpPost postRequest = new HttpPost(url);

		HttpResponse response = null;

		try {
			entity = new UrlEncodedFormEntity(formparams);

			postRequest.setEntity(entity);
			response = httpclient.execute(postRequest);
			Log.d("Yahoo", "Successful!");

		} catch (Exception e) {
			Log.d("Process status", "Proccess fail!");

		}

		return response;

	}

}
