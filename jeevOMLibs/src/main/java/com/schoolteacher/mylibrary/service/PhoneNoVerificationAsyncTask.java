package com.schoolteacher.mylibrary.service;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;

import com.schoolteacher.mylibrary.interfaces.AsyncTaskListner;
import com.schoolteacher.mylibrary.interfaces.SearchDoctorInterface;
import com.schoolteacher.mylibrary.interfaces.SendEmailInterface;
import com.schoolteacher.mylibrary.interfaces.ServiceRequestInterface;
import com.schoolteacher.mylibrary.interfaces.SignInInterface;
import com.schoolteacher.mylibrary.interfaces.VerificationListner;
import com.schoolteacher.mylibrary.util.InputStreamToString;

public class PhoneNoVerificationAsyncTask extends
		AsyncTask<String, Void, String> {
	Activity activity;
	private AsyncTaskListner callback;
	private SendEmailInterface interfaceRef;
	private ServiceRequestInterface serviceRequest;
	private SignInInterface signInReference;
	static String typeOfRequest;
	static String value;
	static String postJson;
	static String email, password, firstName, lastName, mobile, dob, gender;
	static int bloodGroupType;
	static boolean IsVoulantryBloodDonation;
	private VerificationListner verificationListner;
	private SearchDoctorInterface searchDoctorListner;
	static boolean isHealthCareProfession;
	static boolean isFacilityOwner;
	static String title;
	static String country;
	static String state;
	static String city;
	static String area;
	static String address;
	static double lattitude;
	static double longitude;
	DialogFragment dialogFragment;

	public PhoneNoVerificationAsyncTask(DialogFragment activity, String type,
			String value) {
		super();
		this.dialogFragment = activity;

		verificationListner = (VerificationListner) activity;

		PostAsyncTask.typeOfRequest = type;
		PostAsyncTask.value = value;
		postJson = value;
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
			verificationListner.onAddAndLoadUser(result);
		} else if (result.equals("No Internet Connectivity")) {
			verificationListner.onAddAndLoadUser(result);
		} else {
			verificationListner.onAddAndLoadUser(result);
		}
	}

	// Method to Send User Information To Server
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

			json = postJson;

			// 4. convert JSONObject to JSON to String
			// 5. set json to StringEntity
			StringEntity se = new StringEntity(json);
			// 6. set httpPost Entity
			httpPost.setEntity(se);
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

	public void getActivityReference(AsyncTaskListner listener) {
		this.callback = listener;
	}

	public void getSignInReference(SignInInterface listener) {
		signInReference = listener;
	}
}
