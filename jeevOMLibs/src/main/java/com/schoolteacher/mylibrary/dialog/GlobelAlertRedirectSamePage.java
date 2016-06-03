package com.schoolteacher.mylibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.interfaces.RedirectPageChangeListener;

public class GlobelAlertRedirectSamePage extends Dialog {
	private TextView message_text;
	private RedirectPageChangeListener redirectListener;

	public GlobelAlertRedirectSamePage(Context context,
			RedirectPageChangeListener redirectListener) {
		super(context);
		this.redirectListener = redirectListener;
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
				redirectListener.redirectPageChangeListener();
			}
		});

	}

}
