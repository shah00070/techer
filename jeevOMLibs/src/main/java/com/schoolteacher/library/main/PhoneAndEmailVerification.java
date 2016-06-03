package com.schoolteacher.library.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.interfaces.VerificationListner;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.model.EmailAndTokenTypeDictionary;
import com.schoolteacher.mylibrary.model.SignUpData;
import com.schoolteacher.mylibrary.model.Token;
import com.schoolteacher.mylibrary.service.GetSimpleAsynkTask;
import com.schoolteacher.mylibrary.service.PostAsyncTask;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class PhoneAndEmailVerification extends ActionBarActivity implements
		OnClickListener, VerificationListner {
	EditText ep_email_code_field, ep_phone_code_field;
	Button ep_email_verify_btn, ep_phone_verify_btn,
			ep_phone_email_regenerate_btn, btn_register;
	TextView ep_phone_toll_free_text, ep_email_successful_msg,
			ep_phone_successful_msg, ep_seconds;
	String email, phone, UniqueRequestId, CallToVerifyNumberAsText;
	ProgressBar ep_email_verify_progress, ep_phone_verify_progress;
	boolean isEmailVerified, isPhoneVerified;
	DialogFragment newFragment;
	LinearLayout text_call_remaining;
	boolean isMissedCallVerified;
	private CountDownTimer countDownTimer;
	CheckBox emailCheckBox, phoneCheckBox;
	boolean isEmailChecked = false, isPhoneChecked = false;
	GetSimpleAsynkTask missedCallVerify;
	boolean isTimerRunning = false;
	GlobalAlert globalAlert;
	RelativeLayout email_container, phone_container;
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		setContentView(R.layout.phoneandemail_verification);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setTitle(
				getResources().getString(R.string.verify_top));
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		globalAlert = new GlobalAlert(PhoneAndEmailVerification.this);
		// changed on 2/3/2015
		email_container = (RelativeLayout) findViewById(R.id.email_container);
		phone_container = (RelativeLayout) findViewById(R.id.phoneContainer);
		// end
		// fab button
		Intent intent = getIntent();
		email = intent.getStringExtra("email");
		phone = intent.getStringExtra("phone");

		UniqueRequestId = intent.getStringExtra("uniqueRequestId");
		CallToVerifyNumberAsText = intent
				.getStringExtra("CallToVerifyNumberAsText");

		// set email and phone value for regenerate code
		emailCheckBox = (CheckBox) findViewById(R.id.checkbox_email);
		phoneCheckBox = (CheckBox) findViewById(R.id.checkbox_phone);
		emailCheckBox.setText(email);
		phoneCheckBox.setText(phone);
		ep_seconds = (TextView) findViewById(R.id.ep_seconds);
		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(this);

		ep_phone_verify_progress = (ProgressBar) findViewById(R.id.ep_phone_verify_progress);
		ep_email_verify_progress = (ProgressBar) findViewById(R.id.ep_email_verify_progress);
		ep_email_code_field = (EditText) findViewById(R.id.ep_email_code_field);
		ep_phone_code_field = (EditText) findViewById(R.id.ep_phone_code_field);
		ep_email_verify_btn = (Button) findViewById(R.id.ep_email_verify_btn);
		ep_phone_verify_btn = (Button) findViewById(R.id.ep_phone_verify_btn);
		ep_phone_email_regenerate_btn = (Button) findViewById(R.id.ep_phone_email_regenerate_btn);
		ep_phone_toll_free_text = (TextView) findViewById(R.id.ep_phone_toll_free_text);

		ep_email_successful_msg = (TextView) findViewById(R.id.ep_email_successful_verify_text);
		ep_phone_successful_msg = (TextView) findViewById(R.id.ep_phone_successful_verify_text);
		text_call_remaining = (LinearLayout) findViewById(R.id.text_call_remaining);

		ep_email_verify_btn.setOnClickListener(this);
		ep_phone_verify_btn.setOnClickListener(this);
		ep_phone_email_regenerate_btn.setOnClickListener(this);
		String sourceString = "<b>" + CallToVerifyNumberAsText + "</b> ";
		ep_phone_toll_free_text.setText(Html.fromHtml(sourceString));
		ep_phone_toll_free_text.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String uri = "tel:" + CallToVerifyNumberAsText.trim();

				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse(uri));
				startActivity(intent);

			}
		});
		ep_phone_code_field.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				if (arg0.toString().trim().length() > 0) {
					ep_phone_verify_btn.setEnabled(true);
				} else {
					ep_phone_verify_btn.setEnabled(false);
				}

			}
		});
		ep_email_code_field.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				if (arg0.toString().trim().length() > 0) {
					ep_email_verify_btn.setEnabled(true);
				} else {
					ep_email_verify_btn.setEnabled(false);
				}

			}
		});
	}

	@Override
	protected void onResume() {

		text_call_remaining.setVisibility(View.VISIBLE);

		if (countDownTimer == null) {
			countDownTimer = new MyCountDownTimer(180000, 1000);
			countDownTimer.start();
		}
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.ep_phone_email_regenerate_btn) {
			String url = null;

			if (!emailCheckBox.isChecked() && !phoneCheckBox.isChecked()) {
				Crouton.makeText(PhoneAndEmailVerification.this,
						"Select either email/phone or both", Style.ALERT)
						.show();
			} else if (emailCheckBox.isChecked()) {
				url = JeevOMUtil.baseUrl + JeevOMUtil.regenerateCode;
				regenerateEmailCode(url, email);
			} else if (phoneCheckBox.isChecked()) {
				url = JeevOMUtil.baseUrl + JeevOMUtil.phoneResendCode;
				regeneratePhoneCode(url, phone);
			}

		} else if (id == R.id.ep_email_verify_btn) {

			if (ep_phone_verify_progress.getVisibility() != View.VISIBLE) {
				if (!(CommonCode.isNullOrEmpty(ep_email_code_field.getText()
						.toString()))) {
					List<EmailAndTokenTypeDictionary> emailAndTokenType = new ArrayList<EmailAndTokenTypeDictionary>();
					EmailAndTokenTypeDictionary emailToken = new EmailAndTokenTypeDictionary();
					emailToken.setTokenType(JeevOMUtil.TOKEN_VERIFICATION);
					emailToken.setToken(ep_email_code_field.getText()
							.toString());
					emailToken.setEmail(email);
					emailAndTokenType.add(emailToken);
					Token token = new Token();
					token.setEmailAndTokenType(emailAndTokenType);
					Gson gson = new Gson();
					String jsonRequest = gson.toJson(token);
					PostAsyncTask verification = new PostAsyncTask(
							PhoneAndEmailVerification.this, "verification",
							jsonRequest);
					verification.execute(JeevOMUtil.baseUrl
							+ JeevOMUtil.verification);
					ep_email_verify_progress.setVisibility(View.VISIBLE);
					ep_email_verify_btn.setEnabled(false);
					// disable regenerate button while email verification is in
					// progress
					ep_phone_email_regenerate_btn.setEnabled(false);
					ep_phone_email_regenerate_btn.setClickable(false);
				} else {
					Crouton.makeText(PhoneAndEmailVerification.this,
							"Please enter verification code", Style.ALERT)
							.show();
				}
			} else {
				showAlert("Wait! Phone no. verification in progress");
			}
		} else if (id == R.id.ep_phone_verify_btn) {
			if (ep_email_verify_progress.getVisibility() != View.VISIBLE) {
				if (!(CommonCode.isNullOrEmpty(ep_phone_code_field.getText()
						.toString()))) {
					List<EmailAndTokenTypeDictionary> emailAndTokenType = new ArrayList<EmailAndTokenTypeDictionary>();
					EmailAndTokenTypeDictionary phoneEmailToken = new EmailAndTokenTypeDictionary();

					phoneEmailToken
							.setTokenType(JeevOMUtil.TOKEN_VERIFICATION_PHONE);
					phoneEmailToken.setToken(ep_phone_code_field.getText()
							.toString());
					phoneEmailToken.setCellNumber(phone);

					emailAndTokenType.add(phoneEmailToken);
					Token token = new Token();
					token.setEmailAndTokenType(emailAndTokenType);
					Gson gson = new Gson();
					String jsonRequest = gson.toJson(token);
					PostAsyncTask verification = new PostAsyncTask(
							PhoneAndEmailVerification.this,
							"verification_phone", jsonRequest);
					verification.execute(JeevOMUtil.baseUrl
							+ JeevOMUtil.verification);
					ep_phone_verify_progress.setVisibility(View.VISIBLE);
					ep_phone_verify_btn.setEnabled(false);
					// disable regenerate button while email verification is in
					// progress
					ep_phone_email_regenerate_btn.setEnabled(false);
					ep_phone_email_regenerate_btn.setClickable(false);
				} else {
					Crouton.makeText(PhoneAndEmailVerification.this,
							"Please enter verification code", Style.ALERT)
							.show();
				}
			} else {
				showAlert("Wait! Email verification in progress");
			}
		} else if (id == R.id.btn_register) {
			// SignUp();
		}
	}

	public void regenerateEmailCode(String url, String value) {
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
		PostAsyncTask regenerate = null;
		regenerate = new PostAsyncTask(PhoneAndEmailVerification.this,
				"verification_regenerate", "\"" + value + "\"");
		regenerate.execute(url);

	}

	public void regeneratePhoneCode(String url, String value) {

		PostAsyncTask regenerate = null;
		regenerate = new PostAsyncTask(PhoneAndEmailVerification.this,
				"verification_phone_regenerate", "\"" + value + "\"");
		regenerate.execute(url);
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);

	}

	// private void SignUp() {
	// SignUpData signUpdata = getIntentValues();
	// Intent signupIntent = new Intent(PhoneAndEmailVerification.this,
	// SignUp.class);
	// signupIntent.putExtra("data", signUpdata);
	// startActivityForResult(signupIntent,
	// JeevOMUtil.SIGNUP_REQUEST_CODE_FROM_PHONE_AND_EMAIL_VER_PAGE);
	// }

	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == JeevOMUtil.SIGNUP_REQUEST_CODE_FROM_PHONE_AND_EMAIL_VER_PAGE
				&& responseCode == Activity.RESULT_OK) {
			setResult(RESULT_OK);
			finish();
		} else if (requestCode == JeevOMUtil.SIGNUP_REQUEST_CODE_FROM_PHONE_AND_EMAIL_VER_PAGE) {
			finish();
		}
	}

	public SignUpData getIntentValues() {
		SignUpData signUp = new SignUpData();

		if (!CommonCode.isNullOrEmpty(email)) {
			signUp.setEmail(email);
		} else {
			signUp.setEmail("");
		}
		if (!CommonCode.isNullOrEmpty(phone)) {
			signUp.setPhoneNo(phone);
		} else {
			signUp.setPhoneNo("");
		}

		return signUp;
	}

	@Override
	public void onEmailVerification(String result) {
		ep_email_verify_btn.setEnabled(true);
		ep_email_verify_progress.setVisibility(View.GONE);
		// enable regenerate button
		ep_phone_email_regenerate_btn.setEnabled(true);
		ep_phone_email_regenerate_btn.setClickable(true);
		if (result.equals("No Internet Connectivity")) {
			showAlert(JeevOMUtil.INTERNET_CONNECTION);
		} else if (result.equals("Service Error")) {
			showAlert(JeevOMUtil.SOMETHING_WRONG);
		} else {

			DataContainer data = CommonCode.returnDataContainerObject(result);
			String code = data.getStatus().getCode();
			String message = data.getStatus().getMessage();
			if (code.equals("BE-1001")) {
				showAlert(message);
			} else if (code.equals("BE-1000")) {
				showAlert(message);
			} else if (code.equals("DE-1001")) {
				showAlert(message);
			} else if (code.equals("BE-1002")) {
				showAlert(message);
			} else if (code.equals("DE-1000")) {
				showAlert(message);
			} else if (code.equals("BE-1003")) {
				showAlert(message);
			} else if (code.equals("Success")) {

				ep_email_successful_msg.setVisibility(View.VISIBLE);
				ep_email_verify_btn.setEnabled(false);
				ep_email_code_field.setEnabled(false);
				ep_email_successful_msg.setText("Verified Successfully");
				if (btn_register.getVisibility() == View.GONE) {
					btn_register.setVisibility(View.VISIBLE);
				}
				emailCheckBox.setEnabled(false);
			}

		}

	}

	public class MyCountDownTimer extends CountDownTimer {

		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			isTimerRunning = false;
			ep_seconds.setText("Not Verified !");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			isTimerRunning = true;
			ep_seconds.setText("" + millisUntilFinished / 1000);
			if ((millisUntilFinished / 1000) % 2 == 0) {
				if (!isMissedCallVerified) {
					missedCallVerify = new GetSimpleAsynkTask(
							PhoneAndEmailVerification.this, "missedCallVerify");
					missedCallVerify.execute(JeevOMUtil.baseUrl
							+ JeevOMUtil.missedCallFirst + UniqueRequestId
							+ JeevOMUtil.callVerificationStatus);
				}
			}
		}
	}

	@Override
	public void onMissedCallVerification(String result) {
		if (result.equals("No Internet Connectivity")) {
			showAlert(JeevOMUtil.INTERNET_CONNECTION);
		} else if (result.equals("Service Error")) {
			// showMessage =
			// MessageDialogFragment.newInstance(JeevOMUtil.SOMETHING_WRONG);
			// showMessage.setCancelable(false);
			// showMessage.show(getSupportFragmentManager(), "dialog");
		} else {
			DataContainer data = CommonCode.returnDataContainerObject(result);
			String code = data.getStatus().getCode();
			if (code.equals("Success")) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				DataContainer codeValues = gson.fromJson(result,
						DataContainer.class);
				isMissedCallVerified = codeValues.getData().isIsVerified();

				if (isMissedCallVerified) {
					btn_register.setVisibility(View.VISIBLE);
					countDownTimer.cancel();
					ep_phone_successful_msg.setVisibility(View.VISIBLE);
					ep_phone_successful_msg.setText("Verified Successfully");
					text_call_remaining.setVisibility(View.GONE);
				}
			}
		}

	}

	@Override
	public void onCodeRegenerate(String result) {

		if (result.equals("No Internet Connectivity")) {
			newFragment.dismissAllowingStateLoss();
			showAlert(JeevOMUtil.INTERNET_CONNECTION);
		} else if (result.equals("Service Error")) {
			newFragment.dismissAllowingStateLoss();
			showAlert(JeevOMUtil.SOMETHING_WRONG);
		} else {

			DataContainer data = CommonCode.returnDataContainerObject(result);
			String code = data.getStatus().getCode();
			String message = data.getStatus().getMessage();
			if (code.equals("BE-1001")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("BE-1000")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("DE-1001")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("BE-1002")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("DE-1000")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("BE-1004")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("BE-1003")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("Success")) {
				if (phoneCheckBox.isChecked()) {
					newFragment.dismissAllowingStateLoss();
					String phoneUrl = JeevOMUtil.baseUrl
							+ JeevOMUtil.phoneResendCode;
					regeneratePhoneCode(phoneUrl, phone);
				} else {

					newFragment.dismissAllowingStateLoss();
					TextView ep_regenerate_code_successful = (TextView) findViewById(R.id.ep_regenerate_code_successful);
					ep_regenerate_code_successful.setVisibility(View.VISIBLE);
					ep_regenerate_code_successful
							.setText(JeevOMUtil.verification_code_regenerate);
				}
			}

		}
	}

	@Override
	public void onAddAndLoadUser(String result) {
		// enable regenerate button
		ep_phone_email_regenerate_btn.setEnabled(true);
		ep_phone_email_regenerate_btn.setClickable(true);
		if (result.equals("No Internet Connectivity")) {
			showAlert(JeevOMUtil.INTERNET_CONNECTION);
		} else if (result.equals("Service Error")) {
			showAlert(JeevOMUtil.SOMETHING_WRONG);

		} else {
			DataContainer data = CommonCode.returnDataContainerObject(result);
			String code = data.getStatus().getCode();
			String message = data.getStatus().getMessage();
			if (code.equals("BE-1003")) {
				ep_phone_verify_progress.setVisibility(View.GONE);
				ep_phone_verify_btn.setEnabled(true);
				showAlert(message);
			} else if (code.equals("Success")) {

				if (isTimerRunning) {
					countDownTimer.cancel();
				}
				ep_phone_successful_msg.setVisibility(View.VISIBLE);
				ep_phone_successful_msg.setText("Verified Successfully");
				ep_phone_verify_progress.setVisibility(View.GONE);
				ep_phone_code_field.setEnabled(false);
				ep_phone_verify_btn.setEnabled(false);
				text_call_remaining.setVisibility(View.GONE);
				if (btn_register.getVisibility() == View.GONE) {
					btn_register.setVisibility(View.VISIBLE);
				}

				// phoneCheckBox.setVisibility(View.GONE);
				phoneCheckBox.setEnabled(false);
			}
		}
	}

	@Override
	public void onPhoneCodeRegenerate(String result) {

		if (result.equals("No Internet Connectivity")) {
			newFragment.dismissAllowingStateLoss();
			showAlert(JeevOMUtil.INTERNET_CONNECTION);
		} else if (result.equals("Service Error")) {
			newFragment.dismissAllowingStateLoss();
			showAlert(JeevOMUtil.SOMETHING_WRONG);
		} else {

			DataContainer data = CommonCode.returnDataContainerObject(result);
			String code = data.getStatus().getCode();
			String message = data.getStatus().getMessage();
			if (code.equals("BE-1001")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("BE-1000")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("DE-1001")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("BE-1002")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("DE-1000")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("BE-1004")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("BE-1003")) {
				newFragment.dismissAllowingStateLoss();
				showAlert(message);
			} else if (code.equals("Success")) {

				newFragment.dismissAllowingStateLoss();

				TextView ep_regenerate_code_successful = (TextView) findViewById(R.id.ep_regenerate_code_successful);
				ep_regenerate_code_successful.setVisibility(View.VISIBLE);
				ep_regenerate_code_successful
						.setText(JeevOMUtil.verification_code_regenerate);

			}

		}

	}

	@Override
	protected void onStop() {
		if (missedCallVerify.getStatus() == Status.RUNNING) {
			missedCallVerify.cancel(true);
		}
		super.onStop();
	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

}
