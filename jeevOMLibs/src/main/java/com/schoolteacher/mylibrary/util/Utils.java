package com.schoolteacher.mylibrary.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;
import android.view.Display;

public class Utils {
	public static float SCREEN_WIDTH;
	public static float SCREEN_HEIGHT;
	public static float BASE_WIDTH = 540;
	public static float BASE_HEIGHT = 960;

	public static void initializeScreenDisplay(Activity activity) {
		final DisplayMetrics metrics = new DisplayMetrics();
		Display display = activity.getWindowManager().getDefaultDisplay();
		Method mGetRawH = null, mGetRawW = null;

		// For JellyBeans and onward
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {

			display.getRealMetrics(metrics);
			SCREEN_WIDTH = metrics.widthPixels;
			SCREEN_HEIGHT = metrics.heightPixels;
		} else {
			// Below Jellybeans you can use reflection method

			try {
				mGetRawH = Display.class.getMethod("getRawHeight");
				mGetRawW = Display.class.getMethod("getRawWidth");
				SCREEN_WIDTH = (Integer) mGetRawW.invoke(display);
				SCREEN_HEIGHT = (Integer) mGetRawH.invoke(display);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public static float getScaleX(float x) {
		return x * (SCREEN_WIDTH / BASE_WIDTH);
	}

	public static float getScaleY(float y) {
		return y * (SCREEN_HEIGHT / BASE_HEIGHT);
	}

	public static String getInfoFromMetaData(Context context, String name) {
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 1);
			if (ai.metaData != null) {
				return ai.metaData.getString(name);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDateTime(String date) {
		String[] time = date.split("T");
		// DateFormat inputTime = new SimpleDateFormat("HH:mm:ss");
		DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outputDate = new SimpleDateFormat("dd-MMM-yyyy");
		// DateFormat outputTime = new SimpleDateFormat("KK:mm a");
		String Ftime;
		String FDate = null;
		try {
			FDate = outputDate.format(inputDate.parse(date));
			// Ftime = outputTime.format(inputTime.parse(time[1]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Ftime = Ftime.replace("am", "AM").replace("pm", "PM");
		return FDate;

	}

	public static String formatTime(String time) {
		DateFormat inputTime = new SimpleDateFormat("HH:mm:ss");
		DateFormat outputTime = new SimpleDateFormat("KK:mm a");
		String Ftime = null;
		try {
			Ftime = outputTime.format(inputTime.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Ftime.replace("am", "AM").replace("pm", "PM");
	}

	public static String formatDate(String date) {

		DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outputDate = new SimpleDateFormat("dd-MMM-yyyy");
		String FDate = null;
		try {
			FDate = outputDate.format(inputDate.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FDate;
	}

}