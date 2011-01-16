package com.dt.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Calendar;

import android.provider.SyncStateContract.Constants;
import android.util.Log;

public class Server implements Runnable {
	
	// get the response times when ping to servers
	public static long[] GetICMPs(String[] serverNames) {
		long[] responseTime = new long[serverNames.length];
		Server[] servers = new Server[serverNames.length];
		boolean stop;
		
		// start one thread for one server connection 
		for (int i = 0; i < serverNames.length; i++) {
			servers[i] = new Server(serverNames[i]);
			servers[i].run();
		}
		
		// Wait until all the ping is finished or TTL reach the limit
		// default TTL = 5s ( or we can stop whether any Ping is finished ? 
		// comment here)
		do
		{
			stop = true;
			for(int i=0;i<serverNames.length;i++)
			{
				if (servers[i].responseTime==-1) stop=false;
				else responseTime[i] = servers[i].responseTime;
			}
		}
		while (stop == false);
		return responseTime;
	}

	// fields and getters-setters
	private String serverName;
	private boolean reachable = false;
	private long responseTime = -1;

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public boolean isReachable() {
		return reachable;
	}

	public void setReachable(boolean reachable) {
		this.reachable = reachable;
	}

	// Constructors
	public Server() {
	}

	public Server(String serverName) {
		this.serverName = serverName;
	}

	// run method for starting a thread
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// try to reach the server within 5s
		long startTime = Calendar.getInstance().getTimeInMillis();
		try {
			reachable = InetAddress.getByName(serverName).isReachable(5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = Calendar.getInstance().getTimeInMillis();
		responseTime = endTime - startTime;
	}
}

