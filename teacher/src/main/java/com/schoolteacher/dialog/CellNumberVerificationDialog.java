package com.schoolteacher.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.schoolteacher.R;
import com.schoolteacher.interfaces.CapturePhoneInterface;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.interfaces.VerificationListner;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.model.EmailAndTokenTypeDictionary;
import com.schoolteacher.mylibrary.model.Token;
import com.schoolteacher.mylibrary.service.PhoneNoVerificationAsyncTask;
import com.schoolteacher.mylibrary.service.PostAsyncTask;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class CellNumberVerificationDialog extends DialogFragment implements
		VerificationListner {
	private GlobalAlert globalAlert;
	private CapturePhoneInterface phoneCaptureCommunicator;
	private EditText code_field;
	private Button btn_verify_proceed, btn_skip;
	private TextView warning_phone;
	private JeevomSession jeevomSession;
	private String cellNumber;

	public static CellNumberVerificationDialog newInstance() {
		CellNumberVerificationDialog cellnumberDialogFragment = new CellNumberVerificationDialog();
		return cellnumberDialogFragment;
	}

	// make sure the Activity implemented it
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			this.phoneCaptureCommunicator = (CapturePhoneInterface) getActivity();
		} catch (final ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnCompleteListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mCustomView = inflater.inflate(R.layout.cellnumber_verification,
				null);
		cellNumber = getArguments().getString("cellNumber");
		globalAlert = new GlobalAlert(getActivity());
		jeevomSession = new JeevomSession(getActivity());
		code_field = (EditText) mCustomView.findViewById(R.id.code_field);
		btn_verify_proceed = (Button) mCustomView
				.findViewById(R.id.btn_verify_proceed);
		btn_skip = (Button) mCustomView.findViewById(R.id.btn_skip_proceed);

		warning_phone = (TextView) mCustomView.findViewById(R.id.warning_phone);
		btn_verify_proceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				verifyCellNumber();
			}
		});
		btn_skip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				phoneCaptureCommunicator.phoneNumberVerifiedSuccessfully(true);
				dismiss();
			}
		});
		return mCustomView;
	}

	protected void verifyCellNumber() {
		if (!(CommonCode.isNullOrEmpty(code_field.getText().toString()))) {
			List<EmailAndTokenTypeDictionary> emailAndTokenType = new ArrayList<EmailAndTokenTypeDictionary>();
			EmailAndTokenTypeDictionary phoneEmailToken = new EmailAndTokenTypeDictionary();

			phoneEmailToken.setTokenType(JeevOMUtil.TOKEN_VERIFICATION_PHONE);
			phoneEmailToken.setToken(code_field.getText().toString());
			phoneEmailToken.setCellNumber(cellNumber);

			emailAndTokenType.add(phoneEmailToken);
			Token token = new Token();
			token.setEmailAndTokenType(emailAndTokenType);
			Gson gson = new Gson();
			String jsonRequest = gson.toJson(token);
			PhoneNoVerificationAsyncTask verification = new PhoneNoVerificationAsyncTask(
					this, "verification_phone", jsonRequest);
			verification.execute(JeevOMUtil.baseUrl + JeevOMUtil.verification);

		} else {
			Crouton.makeText(getActivity(), "Please enter verification code",
					Style.ALERT).show();
		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sendTokenInSms(cellNumber);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		return dialog;
	}

	protected void sendTokenInSms(String phoneValue) {
		PostAsyncTask regenerate = new PostAsyncTask(this,
				"verification_regenerate", "\"" + phoneValue + "\"");

		String url = JeevOMUtil.baseUrl + JeevOMUtil.phoneResendCode;
		regenerate.execute(url);

		warning_phone.setText("Wait.. We are sending you the code.");

	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

	@Override
	public void onEmailVerification(String result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMissedCallVerification(String result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCodeRegenerate(String result) {

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
				warning_phone.setText("Code Sent");

			}

		}

	}

	@Override
	public void onAddAndLoadUser(String result) {

		if (result.equals("No Internet Connectivity")) {
			showAlert(JeevOMUtil.INTERNET_CONNECTION);
		} else if (result.equals("Service Error")) {
			showAlert(JeevOMUtil.SOMETHING_WRONG);

		} else {
			DataContainer data = CommonCode.returnDataContainerObject(result);
			String code = data.getStatus().getCode();
			String message = data.getStatus().getMessage();
			if (code.equals("BE-1003")) {
				showAlert(message);
			} else if (code.equals("Success")) {
				jeevomSession.setUserPhoneVerifyStatus(true);
				phoneCaptureCommunicator.phoneNumberVerifiedSuccessfully(true);
				dismiss();
			}
		}

	}

	@Override
	public void onPhoneCodeRegenerate(String result) {
		// TODO Auto-generated method stub

	}
}
