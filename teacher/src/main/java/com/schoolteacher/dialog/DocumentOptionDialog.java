package com.schoolteacher.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.DocumentOption;

public class DocumentOptionDialog extends Dialog {

	Activity activity;
	DocumentOption callback;

	public DocumentOptionDialog(Activity context) {
		super(context);
		this.activity = context;
		callback = (DocumentOption) context;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.document_choose_dialog);

		TextView device_option = (TextView) findViewById(R.id.device_option);
		TextView medvault_option = (TextView) findViewById(R.id.medvault_option);
		device_option.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				callback.optionChoosenForDocumentUpload("device");
			}
		});
		medvault_option.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				callback.optionChoosenForDocumentUpload("vault");
			}
		});

	}
}
