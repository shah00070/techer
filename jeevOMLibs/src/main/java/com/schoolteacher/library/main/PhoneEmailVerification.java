package com.schoolteacher.library.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.library.events.EventData;
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
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

import de.greenrobot.event.EventBus;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class PhoneEmailVerification extends ActionBarActivity implements
		OnClickListener, VerificationListner {
	private EventBus bus = EventBus.getDefault();
	private EventData event;

	TextView toll_free_text, phone_no, phone_verify_text, sms_text,
			ep_phone_toll_free_text, ep_seconds, ep_phone_toll_free_text_first,
			email_message, successful_verify_text;
	Button regenerate_btn, register_button;

	String phoneNo, email, type, UniqueRequestId, CallToVerifyNumberAsText;
	EditText code_field, ep_email_code_field, ep_phone_code_field;
	Button verify_btn, ep_email_verify_btn, ep_phone_verify_btn;
	ProgressBar verify_progress;
	DialogFragment newFragment;
	GlobalAlert globalAlert;
	LinearLayout text_call_remaining;
	boolean isMissedCallVerified;
	private CountDownTimer countDownTimer;
	RelativeLayout code_layout, regenerate_layout;
	boolean isTimerRunning = false, isSignUpTimeVerification = true;
	private JeevomSession session;
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		event = new EventData();

		Intent intent = getIntent();

		type = intent.getStringExtra("type");
		isSignUpTimeVerification = intent.getBooleanExtra(
				"isSignUpTimeVerification", true);

		setContentView(R.layout.phoneoremail_verification);
		globalAlert = new GlobalAlert(PhoneEmailVerification.this);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(
				getResources().getString(R.string.verify_top));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		session = new JeevomSession(getApplicationContext());

		if (type.equals("phone")) {
			UniqueRequestId = intent.getStringExtra("uniqueRequestId");
			CallToVerifyNumberAsText = intent
					.getStringExtra("CallToVerifyNumberAsText");
			phoneNo = intent.getStringExtra("phone");
		} else if (type.equals("email")) {
			email = intent.getStringExtra("email");
		}

		code_layout = (RelativeLayout) findViewById(R.id.code_layout);
		regenerate_layout = (RelativeLayout) findViewById(R.id.regenerate_layout);
		text_call_remaining = (LinearLayout) findViewById(R.id.text_call_remaining);
		toll_free_text = (TextView) findViewById(R.id.toll_free_text);

		ep_phone_toll_free_text_first = (TextView) findViewById(R.id.ep_phone_toll_free_text_first);

		successful_verify_text = (TextView) findViewById(R.id.successful_verify_text);

		phone_no = (TextView) findViewById(R.id.phone_no);
		phone_verify_text = (TextView) findViewById(R.id.phone_verify_text);
		sms_text = (TextView) findViewById(R.id.sms_text);
		verify_progress = (ProgressBar) findViewById(R.id.verify_progress);
		code_field = (EditText) findViewById(R.id.code_field);
		verify_btn = (Button) findViewById(R.id.verify_btn);
		verify_btn.setOnClickListener(this);
		register_button = (Button) findViewById(R.id.register_button);
		register_button.setOnClickListener(this);
		if (type.equals("email")) {
			toll_free_text.setVisibility(View.GONE);
			ep_phone_toll_free_text_first.setVisibility(View.GONE);
			email_message = (TextView) findViewById(R.id.email_message);
			email_message.setVisibility(View.VISIBLE);
			phone_verify_text.setText(JeevOMUtil.emailVerification);
			sms_text.setText(JeevOMUtil.emailText);
			phone_no.setText(email);
		}

		if (type.equals("phone")) {
			String sourceString = "<b>" + CallToVerifyNumberAsText + "</b> ";
			toll_free_text.setText(Html.fromHtml(sourceString));
			toll_free_text.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String uri = "tel:" + CallToVerifyNumberAsText.trim();

					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse(uri));
					startActivity(intent);

				}
			});
			phone_verify_text.setText(JeevOMUtil.phoneVerification);
			sms_text.setText(JeevOMUtil.phoneText);
			phone_no.setText(phoneNo);

		}

		regenerate_btn = (Button) findViewById(R.id.regenerate_btn);
		regenerate_btn.setOnClickListener(this);

		code_field.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				if (arg0.toString().trim().length() > 0) {
					verify_btn.setEnabled(true);
				} else {
					verify_btn.setEnabled(false);
				}
			}
		});
		setHeader();
	}

	private void setHeader() {
		TextView header = (TextView) findViewById(R.id.thanks);
		if (!isSignUpTimeVerification) {
			if (type.equals("phone")) {
				header.setText(R.string.verify_thanks_user_cell_exist);
			} else if (type.equals("email")) {
				header.setText(R.string.verify_thanks_user_email_exist);
			}
			register_button.setText("Next");
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		if (type.equals("phone")) {
			text_call_remaining.setVisibility(View.VISIBLE);
			ep_seconds = (TextView) findViewById(R.id.ep_seconds);
			if (countDownTimer == null) {
				countDownTimer = new MyCountDownTimer(180000, 1000);
				countDownTimer.start();
			}

		}
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		if (id == R.id.register_button) {
			if (!isSignUpTimeVerification) {
				setResult(Activity.RESULT_OK);
				finish();
			} else {
				// SignUp();
			}

		} else if (id == R.id.regenerate_btn) {
			setGoneDisplayForRegenerateBtn();
			String url = null;
			PostAsyncTask regenerate = new PostAsyncTask(
					PhoneEmailVerification.this, "verification_regenerate",
					"\"" + phone_no.getText().toString() + "\"");
			if (type.equals("email")) {
				url = JeevOMUtil.baseUrl + JeevOMUtil.regenerateCode;
				regenerate.execute(url);

			} else if (type.equals("phone")) {
				url = JeevOMUtil.baseUrl + JeevOMUtil.phoneResendCode;
				regenerate.execute(url);
			}
			newFragment = ProgressDialogFragment.newInstance();
			newFragment.show(getSupportFragmentManager(), "dialog");
			newFragment.setCancelable(false);
		} else if (id == R.id.verify_btn) {
			if (!CommonCode.isNullOrEmpty(code_field.getText().toString()
					.trim())) {
				List<EmailAndTokenTypeDictionary> emailAndTokenType = new ArrayList<EmailAndTokenTypeDictionary>();
				EmailAndTokenTypeDictionary phoneEmailToken = new EmailAndTokenTypeDictionary();
				if (type.equals("phone")) {

					phoneEmailToken
							.setTokenType(JeevOMUtil.TOKEN_VERIFICATION_PHONE);
					phoneEmailToken.setToken(code_field.getText().toString());
					phoneEmailToken.setCellNumber(phoneNo);
				}

				if (type.equals("email")) {

					phoneEmailToken.setTokenType(JeevOMUtil.TOKEN_VERIFICATION);
					phoneEmailToken.setToken(code_field.getText().toString());
					phoneEmailToken.setEmail(email);
				}
				emailAndTokenType.add(phoneEmailToken);
				Token token = new Token();
				token.setEmailAndTokenType(emailAndTokenType);
				Gson gson = new Gson();
				String jsonRequest = gson.toJson(token);
				PostAsyncTask verification = new PostAsyncTask(
						PhoneEmailVerification.this, "verification",
						jsonRequest);
				verification.execute(JeevOMUtil.baseUrl
						+ JeevOMUtil.verification);
				verify_progress.setVisibility(View.VISIBLE);
				verify_btn.setEnabled(false);

				regenerate_btn.setEnabled(false);
				regenerate_btn.setClickable(false);
			}
		} else {
			Crouton.makeText(PhoneEmailVerification.this,
					"Please enter verification code", Style.ALERT).show();
		}
	}

	private void setGoneDisplayForRegenerateBtn() {
		if (!isSignUpTimeVerification) {
			regenerate_layout.setVisibility(View.GONE);
		}
	}

	@Override
	public void onEmailVerification(String result) {
		regenerate_btn.setEnabled(true);
		regenerate_btn.setClickable(true);

		verify_btn.setEnabled(true);
		verify_progress.setVisibility(View.GONE);
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

				// old code
				// successful_verify_text.setVisibility(View.VISIBLE);
				// successful_verify_text.setText("Verified Successfully");
				updateSessionForEmailAndPhone();

				// once verified -- go back and fetch data
				event.setData("email_phone_verified");
				bus.post(event);
				finish();
			}

		}

	}

	private void updateSessionForEmailAndPhone() {
		if (!isSignUpTimeVerification) {
			if (type.equals("phone")) {
				session.setUserPhoneVerifyStatus(true);
			} else if (type.equals("email")) {
				session.setUserEmailVerifyStatus(true);
			}

		}
	}

	public void showRegisterButton() {
		// hide code layout ,regenerate layout and activate register
		// button
		regenerate_layout.setVisibility(View.GONE);
		code_layout.setVisibility(View.GONE);
		register_button.setVisibility(View.VISIBLE);
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
			if ((millisUntilFinished / 1000) % 5 == 0) {
				if (!isMissedCallVerified) {
					GetSimpleAsynkTask missedCallVerify = new GetSimpleAsynkTask(
							PhoneEmailVerification.this, "missedCallVerify");
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
					successful_verify_text.setVisibility(View.VISIBLE);
					successful_verify_text.setText("Verified Successfully");
					text_call_remaining.setVisibility(View.GONE);
					countDownTimer.cancel();
					showRegisterButton();
				}
			}
		}
	}

	@Override
	public void onCodeRegenerate(String result) {
		newFragment.dismissAllowingStateLoss();

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
			} else if (code.equals("BE-1004")) {
				showAlert(message);
			} else if (code.equals("BE-1003")) {
				showAlert(message);
			} else if (code.equals("Success")) {
				if (isTimerRunning) {
					countDownTimer.cancel();
				}
				TextView regenerate_code_successful = (TextView) findViewById(R.id.regenerate_code_successful);
				regenerate_code_successful.setVisibility(View.VISIBLE);
				regenerate_code_successful
						.setText(JeevOMUtil.verification_code_regenerate);
			}

		}
	}

	@Override
	public void onAddAndLoadUser(String result) {
		// TODO Auto-generated method stub

	}

	// private void SignUp() {
	// SignUpData signUpdata = getIntentValues();
	// Intent signupIntent = new Intent(PhoneEmailVerification.this,
	// SignUp.class);
	// signupIntent.putExtra("data", signUpdata);
	// startActivityForResult(signupIntent,
	// JeevOMUtil.SIGNUP_REQUEST_CODE_FROM_PHONE_EMAIL_VER_PAGE);
	// }

	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == JeevOMUtil.SIGNUP_REQUEST_CODE_FROM_PHONE_EMAIL_VER_PAGE
				&& responseCode == Activity.RESULT_OK) {
			setResult(RESULT_OK);
			finish();
		} else if (requestCode == JeevOMUtil.SIGNUP_REQUEST_CODE_FROM_PHONE_EMAIL_VER_PAGE) {
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
		if (!CommonCode.isNullOrEmpty(phoneNo)) {
			signUp.setPhoneNo(phoneNo);
		} else {
			signUp.setPhoneNo("");
		}

		return signUp;
	}

	@Override
	public void onPhoneCodeRegenerate(String result) {
		// TODO Auto-generated method stub

	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

}
