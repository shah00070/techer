package com.schoolteacher.appointment;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.schoolteacher.R;

public class AlarmReceiver extends BroadcastReceiver {
	int mId = 0;
	ServiceRequisitionData object;

	@Override
	public void onReceive(Context arg0, Intent arg1) {

		if (arg1.hasExtra("service_request_object")) {
			object = (ServiceRequisitionData) arg1
					.getSerializableExtra("service_request_object");
		}
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				arg0)
				.setSmallIcon(R.drawable.ic_launcher)
				// notification icon
				.setContentTitle("Video Appointment")
				// title for notification
				.setContentText(
						"Your Video Appointment is about to start in 5 minutes") // message
																					// for
																					// notification
				.setAutoCancel(true); // clear notification after click
		// Intent intent = new Intent();
		Intent intent = new Intent(arg0, ServiceRequestDetail.class);
		intent.putExtra("object", object);
		PendingIntent pi = PendingIntent.getActivity(arg0, 0, intent,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		mBuilder.setContentIntent(pi);
		NotificationManager mNotificationManager = (NotificationManager) arg0
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
	}

}