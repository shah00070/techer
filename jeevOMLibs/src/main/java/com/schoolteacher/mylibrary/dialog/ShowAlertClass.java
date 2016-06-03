package com.schoolteacher.mylibrary.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ShowAlertClass {
	@SuppressWarnings("deprecation")
	public static void showAlert(String message, String title, Context context) {
		final AlertDialog alertDialog = new AlertDialog.Builder(context)
				.create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting Icon to Dialog
		// alertDialog.setIcon(R.drawable.tick);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Write your code here to execute after dialog closed
				alertDialog.dismiss();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

}
