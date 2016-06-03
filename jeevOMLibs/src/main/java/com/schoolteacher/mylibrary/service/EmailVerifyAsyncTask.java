package com.schoolteacher.mylibrary.service;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;

import com.schoolteacher.mylibrary.interfaces.SendEmailInterface;
import com.schoolteacher.mylibrary.util.InputStreamToString;

public class EmailVerifyAsyncTask extends AsyncTask<String, Void, String> {
	Activity activity;
	private SendEmailInterface interfaceRef;
	static String json;

	public EmailVerifyAsyncTask(Activity activity, String type, String value) {
		super();
		this.activity = activity;
		interfaceRef = (SendEmailInterface) activity;
		json = value;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... urls) {
		return PostUserRegistrationInfo(urls[0]);
	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		if (result.equals("Service Error")) {
			interfaceRef.onEmailSuccess(result);

		} else if (result.equals("No Internet Connectivity")) {
			interfaceRef.onEmailSuccess(result);

		}

		else {

			interfaceRef.onEmailSuccess(result);

		}
	}

	private static JSONObject getPhoneJsonObject() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.accumulate("EmailOrPhone", json);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String PostUserRegistrationInfo(String url) {
		InputStream inputStream = null;
		String result = "";
		JSONObject jsonObject = null;

		try {

			// 1. create HttpClient

			HttpClient httpclient = new DefaultHttpClient();

			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);

			String json = "";

			// 3. build jsonObject

			jsonObject = getPhoneJsonObject();
			json = jsonObject.toString();
			// 5. set json to StringEntity

			StringEntity emailEntity = new StringEntity(json);

			httpPost.setEntity(emailEntity);

			// 7. Set some headers to inform server about the type of the
			// content

			httpPost.setHeader("Content-type", "application/json");

			// 8. Execute POST request to the given URL
			HttpResponse httpResponse = httpclient.execute(httpPost);

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
