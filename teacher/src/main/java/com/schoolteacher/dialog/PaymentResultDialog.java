package com.schoolteacher.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.PaymentResult;

public class PaymentResultDialog extends Dialog {

	Context context;
	private TextView message_text;
	PaymentResult payment;

	public PaymentResultDialog(Context context) {
		super(context);
		this.context = context;
		payment = (PaymentResult) context;
	}

	public void setMessage(String message) {
		message_text.setText(message);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.payment_result_layout);

		message_text = (TextView) findViewById(R.id.message);
		TextView ok = (TextView) findViewById(R.id.ok);
		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				payment.result("finish");
			}
		});

	}

}
