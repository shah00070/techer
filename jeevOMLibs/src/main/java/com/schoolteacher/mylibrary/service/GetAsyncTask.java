package com.schoolteacher.mylibrary.service;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.mylibrary.dialog.ShowAlertClass;
import com.schoolteacher.mylibrary.interfaces.AsyncTaskListner;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.util.InputStreamToString;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class GetAsyncTask extends AsyncTask<String, Void, String> {
	Activity activity;
	private AsyncTaskListner callback;
	AsyncTaskListner callbackDoctor;
	private DialogFragment newFragment;
	FragmentActivity fa;

	public GetAsyncTask(Activity activity, DialogFragment newFragment) {
		super();
		this.activity = activity;
		this.newFragment = newFragment;

	}

	public GetAsyncTask(FragmentActivity activity, DialogFragment newFragment) {
		super();
		this.fa = activity;
		this.newFragment = newFragment;
		callback = (AsyncTaskListner) activity;
	}

	public GetAsyncTask(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

	}

	@Override
	protected String doInBackground(String... urls) {
		return getDoctorTypes(urls[0]);
	}

	// onPostExecute displays the results of the AsyncTask.
	@Override
	protected void onPostExecute(String result) {
		if (result.equals("Service Error") || result.equals("") || result.equals(null)) {
			dismissProgressDialog();
			ShowAlertClass.showAlert("Service Not Responding Well !", "Service Error", activity);
		} else if (result.equals("No Internet Connectivity")) {
			{
				dismissProgressDialog();
				showInternetCheckDialog();
			}
		} else {
			dismissProgressDialog();
			// Get Gson object
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			// parse json string to object
			DataContainer data = gson.fromJson(result, DataContainer.class);
			String statusCode = data.getStatus().getCode();
			if (statusCode.equals("Success")) {
				callback.onTaskComplete(result);
			}
		}
	}

	private void showInternetCheckDialog() {
		Crouton.makeText(fa, JeevOMUtil.INTERNET_CONNECTION, Style.ALERT).show();
	}

	private void dismissProgressDialog() {
		if (newFragment != null && newFragment.isVisible()) {
			newFragment.dismissAllowingStateLoss();
		}
	}

	// Method to get Doctor Types
	public String getDoctorTypes(String url) {
		InputStream inputStream = null;
		String result = "";
		try {
			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
			int responseCode = httpResponse.getStatusLine().getStatusCode();
			// convert inputstream to string
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

	public void setOnXMLLoadFinishedListener(AsyncTaskListner listener) {
		this.callback = listener;
	}

}
