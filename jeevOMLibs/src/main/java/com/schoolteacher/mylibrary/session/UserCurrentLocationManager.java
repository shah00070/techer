package com.schoolteacher.mylibrary.session;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.schoolteacher.mylibrary.pojo.AddressOfUser;

public class UserCurrentLocationManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "user_current_location";
    // postal code
    private static final String KEY_POSTAL_CODE = "postal_code";
    // Building No
    public static final String KEY_FEATURE = "building";
    // state
    public static final String KEY_STATE = "state";
    // district
    public static final String KEY_DISTRICT = "district";
    // city
    public static final String KEY_CITY = "city";
    // latitude
    public static final String KEY_LATITUDE = "latitude";
    // longitude
    public static final String KEY_LONGITUDE = "longitude";
    // address 0
    public static final String KEY_ADDRESS_ZERO = "address_zero";
    // address 1
    public static final String KEY_ADDRESS_ONE = "address_one";
    // address 2
    public static final String KEY_ADDRESS_TWO = "address_two";
    // address 3
    public static final String KEY_ADDRESS_THREE = "address_three";

    // Constructor
    public UserCurrentLocationManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void saveValue(String postalCode, String building, String state, String district, String city, double latitude, double longitude, String address_zero, String address_one,
                          String address_two, String address_three) {

        // Storing id in pref
        editor.putString(KEY_POSTAL_CODE, postalCode);
        editor.putString(KEY_FEATURE, building);
        editor.putString(KEY_STATE, state);
        editor.putString(KEY_DISTRICT, district);
        editor.putString(KEY_CITY, city);
        editor.putLong(KEY_LATITUDE, Double.doubleToRawLongBits(latitude));
        editor.putLong(KEY_LONGITUDE, Double.doubleToRawLongBits(longitude));
        editor.putString(KEY_ADDRESS_ZERO, address_zero);
        editor.putString(KEY_ADDRESS_ONE, address_one);
        editor.putString(KEY_ADDRESS_TWO, address_two);
        editor.putString(KEY_ADDRESS_THREE, address_three);

        // commit changes
        editor.commit();
    }

    /**
     * Get stored session data
     */
    public HashMap<String, Object> getUserLocationDetails() {
        HashMap<String, Object> user = new HashMap<String, Object>();

        user.put(KEY_FEATURE, pref.getString(KEY_POSTAL_CODE, null));
        user.put(KEY_FEATURE, pref.getString(KEY_FEATURE, null));
        user.put(KEY_STATE, pref.getString(KEY_STATE, null));
        user.put(KEY_DISTRICT, pref.getString(KEY_DISTRICT, null));
        user.put(KEY_CITY, pref.getString(KEY_CITY, null));
        user.put(KEY_LATITUDE, Double.longBitsToDouble(pref.getLong(KEY_LATITUDE, (long) 0d)));
        user.put(KEY_LONGITUDE, Double.longBitsToDouble(pref.getLong(KEY_LONGITUDE, (long) 0d)));
        user.put(KEY_ADDRESS_ZERO, pref.getString(KEY_ADDRESS_ZERO, null));
        user.put(KEY_ADDRESS_ONE, pref.getString(KEY_ADDRESS_ONE, null));
        user.put(KEY_ADDRESS_TWO, pref.getString(KEY_ADDRESS_TWO, null));
        user.put(KEY_ADDRESS_THREE, pref.getString(KEY_ADDRESS_THREE, null));
        // return user
        return user;
    }


    public void userLocationObject(AddressOfUser userAddress) {
        Gson gson = new Gson();
        String json = gson.toJson(userAddress);
        editor.putString("UserLocation", json);
        editor.commit();
    }


    public AddressOfUser getUserLocation() {
        Gson gson = new Gson();
        String json = pref.getString("UserLocation", "");
        AddressOfUser obj = gson.fromJson(json, AddressOfUser.class);
        return obj;
    }
}
