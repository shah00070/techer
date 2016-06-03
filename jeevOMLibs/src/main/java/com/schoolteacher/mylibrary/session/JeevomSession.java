package com.schoolteacher.mylibrary.session;

import java.util.HashMap;

import com.google.gson.Gson;
import com.schoolteacher.mylibrary.pojo.Address;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class JeevomSession {
	// Shared Preferences
	SharedPreferences pref;
	// Editor for Shared preferences
	Editor editor;
	// Context
	Context _context;
	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Shared Pref file name
	private static final String PREF_NAME = "jeevom";

	public   String Key_ISEMAILENTER="isemailenter";
	private static final String MEMBER_ID = "member_id";
	public static final String KEY_AUTH_TOKEN = "token";
	public static final String KEY_NAME = "name";
	public static final String KEY_TITLE = "title";
	public static final String KEY_AGE = "age";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_CONTACT = "contact";
	public static final String KEY_GENDER = "gender";
	public static final String KEY_UNIQUE_ID = "unique";
	private static final String IS_LOGIN = "IsLoggedIn";
	public static final String KEY_USER_PHONE_VERIFIED = "phone_verified";
	public static final String KEY_USER_EMAIL_VERIFIED = "email verified";
	public static final String JEEVOM_CONSUMER_ID = "jeevom_consumer";
	public static final String JEEVOM_PROFESSIONAL_ID = "jeevom_professional";
	public static final String JEEVOM_FACILITY_ID = "jeevom_facility";
	public static final String JEEVOM_MEMBER_UNIQUE_ID = "jeevom_member_unique_id";
	public static final String KEY_APP = "app_type";

	// Constructor
	public JeevomSession(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	// save member id
	public void saveMemberId(int member_id) {
		editor.putInt(MEMBER_ID, member_id);
		editor.commit();
	}

	// get member id
	public int getMemberId() {
		return pref.getInt(MEMBER_ID, 0);
	}

	public  String getKey_ISEMAILENTER() {
		return pref.getString(Key_ISEMAILENTER, null);
	}

	public void setKey_ISEMAILENTER(String ISEMAILENTER) {


		editor.putString(Key_ISEMAILENTER,ISEMAILENTER);
		editor.commit();
	}

	/**
	 * set app type
	 * */
	public void setAppType(String appType) {

		editor.putString(KEY_APP, appType);
		editor.commit();
	}

	/* get app type */
	public String getAppType() {
		return pref.getString(KEY_APP, null);
	}

	/**
	 * set auth token
	 * */
	public void setAuthToken(String authToken) {

		editor.putString(KEY_AUTH_TOKEN, authToken);
		editor.commit();
	}

	/* get auth token */
	public String getAuthToken() {
		return pref.getString(KEY_AUTH_TOKEN, null);
	}

	/**
	 * set name
	 * */
	public void setName(String name) {

		editor.putString(KEY_NAME, name);
		editor.commit();
	}

	/* get name */
	public String getName() {
		return pref.getString(KEY_NAME, null);
	}


	//set title
	public void setTitle(String title) {

		editor.putString(KEY_TITLE, title);
		editor.commit();
	}

	/* get name */
	public String getTitle() {
		return pref.getString(KEY_TITLE, null);
	}
	/**
	 * set age
	 * */
	public void setAge(String age) {

		editor.putString(KEY_AGE, age);
		editor.commit();
	}

	/* get age */
	public String getAge() {
		return pref.getString(KEY_AGE, null);
	}

	/**
	 * set email
	 * */
	public void setEmail(String email) {

		editor.putString(KEY_EMAIL, email);
		editor.commit();
	}

	/* get email */
	public String getEmail() {
		return pref.getString(KEY_EMAIL, null);
	}

	/**
	 * set cellnumber
	 * */
	public void setCellNumber(String cellNumber) {

		editor.putString(KEY_CONTACT, cellNumber);
		editor.commit();
	}

	/* get cellnumber */
	public String getCellNumber() {
		return pref.getString(KEY_CONTACT, null);
	}

	/**
	 * set gender
	 * */
	public void setGender(String gender) {

		editor.putString(KEY_GENDER, gender);
		editor.commit();
	}

	/* get gender */
	public String getGender() {
		return pref.getString(KEY_GENDER, null);
	}

	/**
	 * set unique public id
	 * */
	public void setUniquePublicId(String uniqueId) {

		editor.putString(KEY_UNIQUE_ID, uniqueId);
		editor.commit();
	}

	/* get unique public id */
	public String getUniquePublicId() {
		return pref.getString(KEY_UNIQUE_ID, null);
	}

	/**
	 * set logged in status
	 * */
	public void setLoggedInStatus() {

		editor.putBoolean(IS_LOGIN, true);
		editor.commit();
	}

	/* get logged in status */
	public boolean getLoggedInStatus() {
		return pref.getBoolean(IS_LOGIN, false);
	}

	/**
	 * Clear session details
	 * */
	public void logoutUser() {
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();
	}

	// set phone verification status
	public void setUserPhoneVerifyStatus(boolean value) {
		// Storing login value as TRUE
		editor.putBoolean(KEY_USER_PHONE_VERIFIED, value);
		// commit changes
		editor.commit();
	}

	// get phone verification status
	public boolean getUserPhoneVerifyStatus() {
		return pref.getBoolean(KEY_USER_PHONE_VERIFIED, false);

	}

	// set phone verification status
	public void setUserEmailVerifyStatus(boolean value) {
		// Storing login value as TRUE
		editor.putBoolean(KEY_USER_EMAIL_VERIFIED, value);
		// commit changes
		editor.commit();
	}

	// get phone verification status
	public boolean getUserEmailVerifyStatus() {
		return pref.getBoolean(KEY_USER_EMAIL_VERIFIED, false);
		// commit changes
	}

	// save user address
	public void saveUserAddress(Address address) {
		Gson gsonObject = new Gson();
		String addressValue = gsonObject.toJson(address);
		editor.putString("user_address", addressValue);
		editor.commit();
	}

	// retrieve user address
	public Address getUserAddress() {
		Gson gsonObject = new Gson();
		String addressValue = pref.getString("user_address", null);
		Address address = gsonObject.fromJson(addressValue, Address.class);
		return address;
	}

	// save user consumer,professional and facility id
	public void saveConsumerIds(String member_unique_id, int consumer_id,
			int professional_id, int facility_id) {

		// storing consumer id
		editor.putString(JEEVOM_MEMBER_UNIQUE_ID, member_unique_id);
		// storing consumer id
		editor.putInt(JEEVOM_CONSUMER_ID, consumer_id);
		// storing professional id
		editor.putInt(JEEVOM_PROFESSIONAL_ID, professional_id);
		// storing facility id
		editor.putInt(JEEVOM_PROFESSIONAL_ID, facility_id);

		// commit changes
		editor.commit();
	}

	// get consumer ids
	public HashMap<String, String> getConsumerIds() {
		HashMap<String, String> consumerIds = new HashMap<String, String>();
		// member unique id
		consumerIds.put(JEEVOM_MEMBER_UNIQUE_ID,
				pref.getString(JEEVOM_MEMBER_UNIQUE_ID, null));
		// consumer id
		consumerIds.put(JEEVOM_CONSUMER_ID,
				String.valueOf(pref.getInt(JEEVOM_CONSUMER_ID, 0)));

		// professional id
		consumerIds.put(JEEVOM_PROFESSIONAL_ID,
				String.valueOf(pref.getInt(JEEVOM_PROFESSIONAL_ID, 0)));

		// faciliy id
		consumerIds.put(JEEVOM_FACILITY_ID,
				String.valueOf(pref.getInt(JEEVOM_FACILITY_ID, 0)));

		// return user
		return consumerIds;
	}
}
