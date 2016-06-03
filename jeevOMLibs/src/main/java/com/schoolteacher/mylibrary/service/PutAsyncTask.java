package com.schoolteacher.mylibrary.service;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;

import com.schoolteacher.mylibrary.interfaces.UpdateListner;
import com.schoolteacher.mylibrary.util.InputStreamToString;

public class PutAsyncTask extends AsyncTask<String, Void, String> {
	Activity activity;
	private UpdateListner callback;
	static String typeOfRequest;
	static JSONObject jobj;
	static String putJson;

	public PutAsyncTask(Activity activity, String type, JSONObject object) {
		super();
		this.activity = activity;
		PutAsyncTask.typeOfRequest = type;
		jobj = object;
		callback = (UpdateListner) activity;
	}

	public PutAsyncTask(Activity activity, String type, String object) {
		super();
		this.activity = activity;
		PutAsyncTask.typeOfRequest = type;
		putJson = object;
		callback = (UpdateListner) activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... urls) {
		return makePutRequest(urls[0]);
	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		if (result.equals("Service Error")) {
			if (typeOfRequest.equals("edu_qualification")) {
				callback.onEducationUpdate(result);
			} else {
				callback.onUpdate(result);
			}
		} else if (result.equals("No Internet Connectivity")) {
			if (typeOfRequest.equals("edu_qualification")) {
				callback.onEducationUpdate(result);
			} else {
				callback.onUpdate(result);
			}
		} else {
			if (typeOfRequest.equals("edu_qualification")) {
				callback.onEducationUpdate(result);
			} else {
				callback.onUpdate(result);
			}
		}
	}

	// Method to Send User Information To Server
	public static String makePutRequest(String url) {
		InputStream inputStream = null;
		String result = "";
		JSONObject jsonObject = null;
		try {
			// 1. create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 2. make POST request to the given URL
			HttpPut httpPut = new HttpPut(url);
			String json = "";
			// 3. build jsonObject
			if (typeOfRequest.equals("add_clinic")) {
				json = putJson;
			} else if (typeOfRequest.equals("ser_expert") || (typeOfRequest.equals("edu_qualification"))) {
				json = putJson;
			} else {
				json = jobj.toString();
			}
			// 4. convert JSONObject to JSON to String
			// 5. set json to StringEntity
			StringEntity se = new StringEntity(json);
			// 6. set httpPost Entity
			httpPut.setEntity(se);
			// 7. Set some headers to inform server about the type of the
			// content
			httpPut.setHeader("Content-type", "application/json");
			// 8. Execute POST request to the given URL
			HttpResponse httpResponse = httpclient.execute(httpPut);
			// 9. receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
			// 10. receiving the response code ( eg. 200(OK) )
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			// 11. convert inputstream to string
			if (inputStream != null && responseCode == 200 || responseCode == 400) {
				result = InputStreamToString.convertInputStreamToString(inputStream);
			} else {
				result = "Service Error";
			}
		} catch (Exception e) {
			result = "No Internet Connectivity";
		}
		return result;
	}
}