package com.schoolteacher.mylibrary.util;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

public class MyUrlConnectionClient extends UrlConnectionClient {
	@Override
	protected HttpURLConnection openConnection(Request request) {
		HttpURLConnection connection = null;
		try {
			connection = super.openConnection(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.setConnectTimeout(90*1000);
		connection.setReadTimeout(90*1000);
		return connection;
	}
}