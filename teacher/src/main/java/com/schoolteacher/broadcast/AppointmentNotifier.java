package com.schoolteacher.broadcast;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.schoolteacher.R;
import com.schoolteacher.appointment.AlertDialogActivity;
import com.schoolteacher.appointment.AppData;
import com.schoolteacher.appointment.AppointmentRequestResponse;
import com.schoolteacher.appointment.AppointmentURL;
import com.schoolteacher.appointment.IndivisualAppointmentActivity;
import com.schoolteacher.appointment.ServiceRequisitionData;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.util.JeevomUtilsClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class AppointmentNotifier extends BroadcastReceiver {
	private static final int CONFIRMED = 12;
	private static final long FIVE_MINUTE = 5 * 60 * 1000;
	private static final long TEN_MINUTE = 10 * 60 * 1000;
	private static final long FIFTEEN_MINUTE = 15 * 60 * 1000;
	private static final int flag_before_One_minute = 2;
	private static final int flag_before_five_minute = 1;
	private String authToken = null;

	@Override
	public void onReceive(Context context, Intent intent) {

		getAllAppointment(context);
	}

	private void getAllAppointment(final Context context) {
		JeevomSession sessionManager = new JeevomSession(
				context.getApplicationContext());
		if (!CommonCode.isNullOrEmpty(sessionManager.getAuthToken())) {
			authToken = "Basic " + sessionManager.getAuthToken();
		}
		String publicId = sessionManager.getConsumerIds().get(
				JeevomSession.JEEVOM_MEMBER_UNIQUE_ID);
		RestAdapter emailRequestAdapter = new RestAdapter.Builder()
				.setLog(new AndroidLog("Appointment"))
				.setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
				.build();
		AppointmentURL emailURL = emailRequestAdapter
				.create(AppointmentURL.class);
		emailURL.getAllEmailRequest(publicId, "true", authToken,
				new Callback<AppointmentRequestResponse>() {

					@Override
					public void success(AppointmentRequestResponse result,
							Response arg1) {
						String code = result.getStatus().getCode();
						if (code.equals("Success")) {
							List<ServiceRequisitionData> serviceRequisitionsList = result
									.getData().getServiceRequisitions();
							if (serviceRequisitionsList != null
									&& serviceRequisitionsList.size() != 0) {
								for (ServiceRequisitionData serviceRequisitions : serviceRequisitionsList) {
									if (serviceRequisitions
											.getServiceConfigurationCode()
											.equals(AppData.SG3)
											&& serviceRequisitions
													.getStatusId() == CONFIRMED) {
										String appointmentTime = JeevomUtilsClass
												.formatTime(serviceRequisitions
														.getAppointmentTime());
										String appointmentDate = JeevomUtilsClass
												.formatDate(serviceRequisitions
														.getAppointmentDate());

										long dateToMillis = dateToMillis(appointmentDate
												+ " " + appointmentTime);

										if (dateToMillis >= 0
												&& dateToMillis <= FIVE_MINUTE) {
											String message = "Video chat appointment is now active in your Service Request queue.";
											if (isAcitivityFind(context)) {
												Intent resultIntent = new Intent(
														context,
														AlertDialogActivity.class);
												resultIntent.putExtra("data",
														serviceRequisitions);
												resultIntent.putExtra(
														"message", message);
												resultIntent
														.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
												context.startActivity(resultIntent);
											} else {
												createNotification(context,
														serviceRequisitions,
														message);
											}
										}
									}
								}
							}
						}
					}

					private boolean isAcitivityFind(final Context context) {
						ActivityManager activityManager = (ActivityManager) context
								.getSystemService(Context.ACTIVITY_SERVICE);
						List<RunningTaskInfo> services = activityManager
								.getRunningTasks(Integer.MAX_VALUE);
						boolean isActivityFound = false;

						if (services.size() > 0
								&& services.get(0).topActivity
										.getPackageName()
										.toString()
										.equalsIgnoreCase(
												context.getPackageName()
														.toString())) {
							isActivityFound = true;
						}
						return isActivityFound;
					}

					@Override
					public void failure(RetrofitError arg0) {

					}
				});
	}

	private void createNotification(Context context,
			ServiceRequisitionData data, String message) {
		String contentTitle = "Jeevom";
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		int notificationId = 001;
		int cameraId = 0;
		String contentText = null;
		cameraId = JeevomUtilsClass.findFrontFacingCamera();
		if (cameraId < 0) {
			contentText = "No front facing camera found. Please login to www.jeevom.com";
		} else {
			contentText = message;
		}
		Intent resultIntent = new Intent(context,
				IndivisualAppointmentActivity.class);
		resultIntent.putExtra("data", data);
		resultIntent.putExtra("IS_FROM_NOTIFICATION", true);
		PendingIntent resultPendingIntent = PendingIntent
				.getActivity(context, notificationId, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(resultPendingIntent);
		builder.setDefaults(Notification.DEFAULT_ALL);
		builder.setAutoCancel(true);
		builder.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(contentTitle)
				.setContentText(contentText)
				.setDefaults(Notification.DEFAULT_ALL)
				.setStyle(
						new NotificationCompat.BigTextStyle()
								.bigText(contentText));
		NotificationManager notifyMgr = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notifyMgr.notify(notificationId, builder.build());
	}

	private long dateToMillis(String timeStamp) {
		String input = timeStamp;
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MMM-yyyy h:mm a", Locale.ENGLISH)
					.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long milliseconds = date.getTime();
		long millisecondsFromNow = milliseconds - (new Date()).getTime();
		return millisecondsFromNow;
	}

	private String milliToMin(long timeInMilliSeconds) {
		// TODO Auto-generated method stub
		String time;
		long seconds = timeInMilliSeconds / 1000;
		long minutes = seconds / 60;
		time = minutes % 60 + "m " + ":" + seconds % 60 + "s ";
		return time;

	}

	private void setAlarm(Context context,
			ServiceRequisitionData serviceRequestAppointmentData,
			long dateToMillis) {
		Intent intent = new Intent(context, AppointmentNotifier.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("data", serviceRequestAppointmentData);
		bundle.putInt("flag", flag_before_One_minute);
		intent.putExtras(bundle);
		PendingIntent pendingintent = PendingIntent.getBroadcast(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ dateToMillis - 60 * 1000, pendingintent);
	}

}
