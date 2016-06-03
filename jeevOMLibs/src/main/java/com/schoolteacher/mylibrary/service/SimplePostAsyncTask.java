package com.schoolteacher.mylibrary.service;

import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.os.AsyncTask;
import com.schoolteacher.mylibrary.interfaces.MissedCallCodeListner;
import com.schoolteacher.mylibrary.util.InputStreamToString;

public class SimplePostAsyncTask extends AsyncTask<String, Void, String> {
	private MissedCallCodeListner listner;

	public SimplePostAsyncTask(Activity activity) {
		super();
		listner = (MissedCallCodeListner) activity;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... urls) {
		return generateCode(urls[0]);
	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		if (result.equals("Service Error")) {

			listner.onCodeGenerated(result);

		} else if (result.equals("No Internet Connectivity")) {
			listner.onCodeGenerated(result);
		} else {
			listner.onCodeGenerated(result);
		}
	}

	// Method to Send User Information To Server
	public static String generateCode(String url) {
		InputStream inputStream = null;
		String result = "";
		try {
			// 1. create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/json");
			// 8. Execute POST request to the given URL
			HttpResponse httpResponse = httpclient.execute(httpPost);
			// 9. receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
			// 10. receiving the response code ( eg. 200(OK) )
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			// 11. convert inputstream to string
			if (inputStream != null && responseCode == 200
					|| responseCode == 400) {
				result = InputStreamToString
						.convertInputStreamToString(inputStream);
			} else {
				result = "Service Error";
			}
		} catch (Exception e) {
			result = "No Internet Connectivity";
		}
		return result;
	}

}
