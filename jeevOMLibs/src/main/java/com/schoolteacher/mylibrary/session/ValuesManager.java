package com.schoolteacher.mylibrary.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ValuesManager {
	// Shared Preferences
	SharedPreferences pref;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "Values";

	public static final String KEY_VERSION = "version";

	// Constructor
	public ValuesManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	// save version
	public void saveVersion(int version) {
		editor.putInt(KEY_VERSION, version);
		editor.commit();
	}

	public int getVersion() {
		return pref.getInt(KEY_VERSION, 0);
	}

}
