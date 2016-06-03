package com.schoolteacher.mylibrary.dialog;

import com.schoolteacher.library.events.EventData;

import de.greenrobot.event.EventBus;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class PhoneEmailExistsWarningDialog {
	private static EventBus bus = EventBus.getDefault();
	private static EventData event;

	@SuppressWarnings("deprecation")
	public static void showAlert(String message, String title, Context context) {

		event = new EventData();
		final AlertDialog alertDialog = new AlertDialog.Builder(context)
				.create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting Icon to Dialog
		// alertDialog.setIcon(R.drawable.tick);

		// Setting OK Button
		alertDialog.setButton("Verify", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				event.setData("proceed");
				bus.post(event);
				alertDialog.dismiss();
			}
		});

		alertDialog.setButton2("Quit", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				alertDialog.dismiss();

			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
