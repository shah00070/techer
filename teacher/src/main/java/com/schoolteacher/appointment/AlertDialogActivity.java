package com.schoolteacher.appointment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.schoolteacher.R;

public class AlertDialogActivity extends Activity {

	private ServiceRequisitionData data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_alert_dialog);
		Intent intent = getIntent();
		data = (ServiceRequisitionData) intent.getSerializableExtra("data");
		String message = intent.getStringExtra("message");
		TextView message_text = (TextView) findViewById(R.id.message);
		message_text.setText(message);
		TextView cancel = (TextView) findViewById(R.id.appointment);
		cancel.setText("Cancel");
		TextView showMe = (TextView) findViewById(R.id.ok);
		showMe.setText("Show me");
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		showMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AlertDialogActivity.this,
						IndivisualAppointmentActivity.class);
				intent.putExtra("data", data);
				intent.putExtra("IS_FROM_NOTIFICATION", true);
				startActivity(intent);
				finish();
			}
		});
	}
}
