package com.schoolteacher.mylibrary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.schoolteacher.mylibrary.R;

public class GlobelAlertWithFinish extends Dialog {
	private Activity activity;
	private TextView message_text;

	public GlobelAlertWithFinish(Activity activity) {
		super(activity);
		this.activity = activity;
	}

	public void setMessage(String message) {
		message_text.setText(message);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alert_dialog);
		message_text = (TextView) findViewById(R.id.message);
		TextView ok = (TextView) findViewById(R.id.ok);

		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				activity.finish();
			}
		});

	}
}
