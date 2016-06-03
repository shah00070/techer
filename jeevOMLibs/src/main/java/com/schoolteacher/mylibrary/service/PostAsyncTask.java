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
import android.support.v4.app.DialogFragment;

import com.schoolteacher.mylibrary.interfaces.AsyncTaskListner;
import com.schoolteacher.mylibrary.interfaces.SearchDoctorInterface;
import com.schoolteacher.mylibrary.interfaces.SendEmailInterface;
import com.schoolteacher.mylibrary.interfaces.ServiceRequestInterface;
import com.schoolteacher.mylibrary.interfaces.SignInInterface;
import com.schoolteacher.mylibrary.interfaces.VerificationListner;
import com.schoolteacher.mylibrary.util.InputStreamToString;

public class PostAsyncTask extends AsyncTask<String, Void, String> {
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

	public PostAsyncTask(Activity activity, String type, String value) {
		super();
		this.activity = activity;

		if (type.equals("service_request_create")
				|| type.equals("message_request_create")) {
			serviceRequest = (ServiceRequestInterface) activity;
		} else if (type.equals("verification")
				|| type.equals("verification_phone")
				|| type.equals("verification_regenerate")
				|| type.equals("verification_phone_regenerate")
				|| type.equals("addUser") || type.equals("loadUser")) {

			verificationListner = (VerificationListner) activity;
		} else if (type.equals("searchDoc") || type.equals("searchMoreDoc")) {
			searchDoctorListner = (SearchDoctorInterface) activity;
		} else {
			interfaceRef = (SendEmailInterface) activity;
		}
		PostAsyncTask.typeOfRequest = type;
		PostAsyncTask.value = value;
		postJson = value;
	}

	public PostAsyncTask(DialogFragment activity, String type, String value) {
		super();
		this.dialogFragment = activity;

		if (type.equals("verification_regenerate")) {

			verificationListner = (VerificationListner) activity;
		}
		PostAsyncTask.typeOfRequest = type;
		PostAsyncTask.value = value;
		postJson = value;
	}

	public PostAsyncTask(Activity activity, String type, String email,
			String password, String title, String firstName, String lastName,
			String mobile, String dob, String gender, String country,
			String state, String city, String area, String address,
			double lattitude, double longitude,
			boolean isVoluntaryBlooddonation, int bloodGroupType,
			boolean isHealthCareProfession, boolean isFacilityOwner) {

		this.activity = activity;
		PostAsyncTask.typeOfRequest = type;
		PostAsyncTask.email = email;
		PostAsyncTask.password = password;
		PostAsyncTask.title = title;
		PostAsyncTask.firstName = firstName;
		PostAsyncTask.lastName = lastName;
		PostAsyncTask.mobile = mobile;
		PostAsyncTask.dob = dob;
		PostAsyncTask.gender = gender;
		PostAsyncTask.country = country;
		PostAsyncTask.state = state;
		PostAsyncTask.city = city;
		PostAsyncTask.area = area;
		PostAsyncTask.address = address;
		PostAsyncTask.lattitude = lattitude;
		PostAsyncTask.longitude = longitude;
		PostAsyncTask.bloodGroupType = bloodGroupType;
		PostAsyncTask.IsVoulantryBloodDonation = isVoluntaryBlooddonation;
		PostAsyncTask.isHealthCareProfession = isHealthCareProfession;
		PostAsyncTask.isFacilityOwner = isFacilityOwner;
	}

	public PostAsyncTask(Activity activity, String type, String email,
			String password) {

		this.activity = activity;
		PostAsyncTask.typeOfRequest = type;
		PostAsyncTask.email = email;
		PostAsyncTask.password = password;

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
			if (typeOfRequest.equals("member_sign_up")
					|| typeOfRequest.equals("forget_password")
					|| typeOfRequest.equals("doctor_sign_up")) {
				callback.onMemberSignUp(result);
			} else if (typeOfRequest.equals("update_password")) {
				callback.onTaskComplete(result);
			} else if (typeOfRequest.equals("facility_sign_up")) {
				callback.onFacilitySignUp(result);
			} else if (typeOfRequest.equals("sign_in")) {
				signInReference.onSuccessfulSignIn(result);
			} else if (typeOfRequest.equals("service_request_create")) {
				serviceRequest.onSuccessfulRequestCreate(result);
			} else if (typeOfRequest.equals("message_request_create")) {
				serviceRequest.onSuccessfulMessageCreate(result);
			} else if (typeOfRequest.equals("claim_profile")) {
				interfaceRef.onEmailSuccess(result);
			} else if (typeOfRequest.equals("verification")) {
				verificationListner.onEmailVerification(result);
			} else if (typeOfRequest.equals("verification_phone")) {

				verificationListner.onAddAndLoadUser(result);

			} else if (typeOfRequest.equals("verification_regenerate")) {
				if (verificationListner != null)
					verificationListner.onCodeRegenerate(result);
				else
					verificationListner.onCodeRegenerate(result);
			} else if (typeOfRequest.equals("verification_phone_regenerate")) {
				verificationListner.onPhoneCodeRegenerate(result);
			} else if (typeOfRequest.equals("searchDoc")) {
				searchDoctorListner.returnDoctorList(result);
			} else if (typeOfRequest.equals("searchMoreDoc")) {
				searchDoctorListner.returnMoreDoctorResults(result);
			} else if (typeOfRequest.equals("addUser")) {
				verificationListner.onAddAndLoadUser(result);
			} else if (typeOfRequest.equals("loadUser")) {
				verificationListner.onAddAndLoadUser(result);
			}
		} else if (result.equals("No Internet Connectivity")) {
			if (typeOfRequest.equals("member_sign_up")
					|| typeOfRequest.equals("forget_password")
					|| typeOfRequest.equals("doctor_sign_up")) {
				callback.onMemberSignUp(result);
			} else if (typeOfRequest.equals("update_password")) {
				callback.onTaskComplete(result);
			} else if (typeOfRequest.equals("facility_sign_up")) {
				callback.onFacilitySignUp(result);
			} else if (typeOfRequest.equals("service_request_create")) {
				serviceRequest.onSuccessfulRequestCreate(result);
			} else if (typeOfRequest.equals("sign_in")) {
				signInReference.onSuccessfulSignIn(result);
			} else if (typeOfRequest.equals("message_request_create")) {
				serviceRequest.onSuccessfulMessageCreate(result);
			} else if (typeOfRequest.equals("claim_profile")) {
				interfaceRef.onEmailSuccess(result);
			} else if (typeOfRequest.equals("verification")) {
				verificationListner.onEmailVerification(result);
			} else if (typeOfRequest.equals("verification_phone")) {
				verificationListner.onAddAndLoadUser(result);
			} else if (typeOfRequest.equals("verification_regenerate")) {
				verificationListner.onCodeRegenerate(result);
			} else if (typeOfRequest.equals("verification_phone_regenerate")) {
				verificationListner.onPhoneCodeRegenerate(result);
			} else if (typeOfRequest.equals("searchDoc")) {
				searchDoctorListner.returnDoctorList(result);
			} else if (typeOfRequest.equals("searchMoreDoc")) {
				searchDoctorListner.returnMoreDoctorResults(result);
			} else if (typeOfRequest.equals("addUser")) {
				verificationListner.onAddAndLoadUser(result);
			} else if (typeOfRequest.equals("loadUser")) {
				verificationListner.onAddAndLoadUser(result);
			}
		} else {
			if (typeOfRequest.equals("doctor_sign_up")
					|| typeOfRequest.equals("forget_password")
					|| typeOfRequest.equals("member_sign_up")) {
				callback.onMemberSignUp(result);
			} else if (typeOfRequest.equals("update_password")) {
				callback.onTaskComplete(result);
			} else if (typeOfRequest.equals("facility_sign_up")) {
				callback.onFacilitySignUp(result);
			} else if (typeOfRequest.equalsIgnoreCase("email")) {
				interfaceRef.onEmailSuccess(result);
			} else if (typeOfRequest.equalsIgnoreCase("phone")) {
				interfaceRef.onPhoneSucess(result);
			} else if (typeOfRequest.equals("claim_profile")) {
				interfaceRef.onEmailSuccess(result);
			} else if (typeOfRequest.equals("service_request_create")) {
				serviceRequest.onSuccessfulRequestCreate(result);
			} else if (typeOfRequest.equals("message_request_create")) {
				serviceRequest.onSuccessfulMessageCreate(result);
			} else if (typeOfRequest.equals("verification")) {
				verificationListner.onEmailVerification(result);
			} else if (typeOfRequest.equals("verification_phone")) {
				verificationListner.onAddAndLoadUser(result);
			} else if (typeOfRequest.equals("verification_regenerate")) {
				verificationListner.onCodeRegenerate(result);
			} else if (typeOfRequest.equals("verification_phone_regenerate")) {
				verificationListner.onPhoneCodeRegenerate(result);
			} else if (typeOfRequest.equals("searchDoc")) {
				searchDoctorListner.returnDoctorList(result);
			} else if (typeOfRequest.equals("searchMoreDoc")) {
				searchDoctorListner.returnMoreDoctorResults(result);
			} else if (typeOfRequest.equals("addUser")) {
				verificationListner.onAddAndLoadUser(result);
			} else if (typeOfRequest.equals("loadUser")) {
				verificationListner.onAddAndLoadUser(result);
			} else {
				signInReference.onSuccessfulSignIn(result);
			}
		}
	}

	private static JSONObject getEmailJsonObject() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.accumulate("EmailOrPhone", value);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	private static JSONObject getPhoneJsonObject() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.accumulate("EmailOrPhone", value);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	private static JSONObject getMemberJsonObject() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.accumulate("Email", email);
			jsonObject.accumulate("PasswordHash", password);
			jsonObject.accumulate("FirstName", firstName);
			jsonObject.accumulate("LastName", lastName);
			jsonObject.accumulate("Mobilenumber", mobile);
			jsonObject.accumulate("DateOfBirth", dob);
			jsonObject.accumulate("Gender", gender);
			jsonObject.accumulate("Country", country);
			jsonObject.accumulate("State", state);
			jsonObject.accumulate("City", city);
			jsonObject.accumulate("Area", area);
			jsonObject.accumulate("latitude", lattitude);
			jsonObject.accumulate("longitude", longitude);
			jsonObject.accumulate("AddressLine1", address);
			jsonObject.accumulate("BloodGroupType", bloodGroupType);
			jsonObject.accumulate("Title", title);
			jsonObject.accumulate("IsVoulantryBloodDonation",
					IsVoulantryBloodDonation);
			jsonObject.accumulate("isFacilityOwner", isFacilityOwner);
			jsonObject.accumulate("isHealthCareProfessional",
					isHealthCareProfession);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	private static JSONObject getSignInJsonObject() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.accumulate("EmailOrPhone", email);
			jsonObject.accumulate("PasswordHash", password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
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
			if (typeOfRequest.equalsIgnoreCase("email")) {
				jsonObject = getEmailJsonObject();
				json = jsonObject.toString();
			}
			if (typeOfRequest.equalsIgnoreCase("phone")) {
				jsonObject = getPhoneJsonObject();
				json = jsonObject.toString();
			}
			if (typeOfRequest.equalsIgnoreCase("member_sign_up")) {
				jsonObject = getMemberJsonObject();
				json = jsonObject.toString();
			}
			if (typeOfRequest.equalsIgnoreCase("sign_in")) {
				jsonObject = getSignInJsonObject();
				json = jsonObject.toString();
			}
			if (typeOfRequest.equals("forget_password")
					|| typeOfRequest.equals("update_password")
					|| typeOfRequest.equals("claim_profile")
					|| typeOfRequest.equals("facility_sign_up")
					|| typeOfRequest.equals("service_request_create")
					|| typeOfRequest.equals("message_request_create")
					|| typeOfRequest.equals("doctor_sign_up")
					|| typeOfRequest.equals("verification")
					|| typeOfRequest.equals("verification_phone")
					|| typeOfRequest.equals("verification_regenerate")
					|| typeOfRequest.equals("verification_phone_regenerate")
					|| typeOfRequest.equals("searchDoc")
					|| typeOfRequest.equals("searchMoreDoc")
					|| typeOfRequest.equals("addUser")
					|| typeOfRequest.equals("loadUser")) {
				json = postJson;
			}
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
