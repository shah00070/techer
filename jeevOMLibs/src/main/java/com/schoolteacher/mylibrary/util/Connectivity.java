package com.schoolteacher.mylibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connectivity {
	public static boolean connected = true;

	public static boolean checkConnectivity(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		connected = cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isAvailable()
				&& cm.getActiveNetworkInfo().isConnected();

		return connected;
	}
}
