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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.interfaces.CapturePhoneInterface;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.pojo.PhoneEmaiExistsResponse;
import com.schoolteacher.mylibrary.pojo.PhoneEmailExistsRequest;
import com.schoolteacher.mylibrary.pojo.Status;
import com.schoolteacher.mylibrary.service.interfaces.PhoneExists;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.UpdateCellEmailInfo;
import com.schoolteacher.service.UpdateCellEmail;

import java.net.SocketTimeoutException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.mime.TypedByteArray;

public class CellNumberCaptureDialog extends DialogFragment {
	private GlobalAlert globalAlert;
	private CapturePhoneInterface phoneCaptureCommunicator;
	private EditText phone_no_edit_text;
	private Button btn_send_verification_code;
	private ProgressBar phone_progress;
	private TextView warning_phone;
	private JeevomSession jeevomSession;
	Gson gson;
	UserCurrentLocationManager locationManager;
	public static CellNumberCaptureDialog newInstance() {
		CellNumberCaptureDialog cellnumberDialogFragment = new CellNumberCaptureDialog();
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
		View mCustomView = inflater.inflate(R.layout.cellnumber_capture, null);
		globalAlert = new GlobalAlert(getActivity());
		gson = new GsonBuilder().create();
		locationManager=new UserCurrentLocationManager(getActivity().getApplicationContext());
		jeevomSession = new JeevomSession(getActivity());
		phone_no_edit_text = (EditText) mCustomView
				.findViewById(R.id.user_phone_value);
		btn_send_verification_code = (Button) mCustomView
				.findViewById(R.id.btn_send_verification_code);
		phone_progress = (ProgressBar) mCustomView
				.findViewById(R.id.phone_progress);
		warning_phone = (TextView) mCustomView.findViewById(R.id.warning_phone);
		btn_send_verification_code
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {

						if (CommonCode.validatePhone(phone_no_edit_text
								.getText().toString().trim())) {
							verifiedPhone(phone_no_edit_text.getText()
									.toString().trim());
						} else {
							Crouton.makeText(getActivity(),
									JeevOMUtil.VALID_Student_Id, Style.ALERT).show();
							return;
						}
					}
				});

		return mCustomView;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		return dialog;
	}

	public void verifiedPhone(final String phoneValue) {

		RestAdapter phoneExistsRestAdapter = new RestAdapter.Builder()
				.setClient(new MyUrlConnectionClient())
				.setLog(new AndroidLog("cell_error"))
				.setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
				.build();
		PhoneExists phoneexists = phoneExistsRestAdapter
				.create(PhoneExists.class);
		warning_phone.setVisibility(View.VISIBLE);
		warning_phone.setText("Wait..");
		phone_progress.setVisibility(View.VISIBLE);
		phoneexists.isPhoneExists("Basic " + jeevomSession.getAuthToken(),
				new PhoneEmailExistsRequest(phoneValue),
				new Callback<PhoneEmaiExistsResponse>() {

					@Override
					public void failure(RetrofitError arg0) {
						phone_no_edit_text.setEnabled(true);
						phone_progress.setVisibility(View.GONE);
						btn_send_verification_code.setEnabled(true);
						if (arg0.isNetworkError()) {
							if (!(Connectivity.checkConnectivity(getActivity()))) {
								showAlert(JeevOMUtil.INTERNET_CONNECTION);
							} else if (arg0.getCause() instanceof SocketTimeoutException) {
								showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
							} else if (arg0.getResponse() == null) {
								showAlert(JeevOMUtil.SOMETHING_WRONG);
							}
						} else if (arg0.getResponse().getStatus() > 400) {
							showAlert(JeevOMUtil.SOMETHING_WRONG);
						} else {
							String json = new String(((TypedByteArray) arg0
									.getResponse().getBody()).getBytes());
							Gson gson = new GsonBuilder().setPrettyPrinting()
									.create();
							PhoneEmaiExistsResponse phoneEmailExistsErrors = gson
									.fromJson(json,
											PhoneEmaiExistsResponse.class);
							String code = phoneEmailExistsErrors.getStatus()
									.getCode();
							String message = phoneEmailExistsErrors.getStatus()
									.getMessage();
							if (code.equals("IOE-1000")) {
								phone_no_edit_text.setEnabled(true);
								btn_send_verification_code.setEnabled(true);
								warning_phone.setVisibility(View.VISIBLE);
								warning_phone.setText(message);
							} else if (code.equals("BE-1001")) {
								showAlert(message);
							} else if (code.equals("BE-1000")) {
								showAlert(message);
							} else if (code.equals("DE-1001")) {
								showAlert(message);
							} else if (code.equals("BE-1002")) {
								showAlert(message);
							} else if (code.equals("DE-1000")) {
								showAlert(message);
							} else {
								showAlert(JeevOMUtil.SOMETHING_WRONG);
							}
						}
					}

					@Override
					public void success(PhoneEmaiExistsResponse arg0,
							retrofit.client.Response arg1) {
						phone_progress.setVisibility(View.GONE);
						String code = arg0.getStatus().getCode();
						if (code.equals("Success")) {
							boolean flag = arg0.isData();
							if (flag) {
								// Phone error message
								phone_no_edit_text.setEnabled(true);
								btn_send_verification_code.setEnabled(true);
								warning_phone.setVisibility(View.VISIBLE);
								warning_phone
										.setText("Phone no. already exists");
							} else {

								updateCellEmailInfo(phoneValue);
							}

						}
					}
				});
	}

	protected void updateCellEmailInfo(final String phoneValue) {

		UpdateCellEmailInfo object = new UpdateCellEmailInfo();
		object.setCellNumber(phoneValue);
		object.setUserId(jeevomSession.getMemberId());

		RestAdapter updateCellEmailAdapter = new RestAdapter.Builder()
				.setClient(new MyUrlConnectionClient())
				.setLog(new AndroidLog("add phone")).setLogLevel(LogLevel.FULL)
				.setEndpoint(JeevOMUtil.baseUrl).build();
		UpdateCellEmail updatePhone = updateCellEmailAdapter
				.create(UpdateCellEmail.class);
		updatePhone.updateCellEmail(gson.toJson(locationManager.getUserLocation()).toString(),"Basic " + jeevomSession.getAuthToken(),
				object, new Callback<ApiResponse<Status>>() {

					@Override
					public void failure(RetrofitError arg0) {
						phone_no_edit_text.setEnabled(true);
						btn_send_verification_code.setEnabled(true);
						warning_phone.setVisibility(View.VISIBLE);
						warning_phone.setText("Phone no. already exists");
						if (arg0.isNetworkError()) {
							if (!(Connectivity.checkConnectivity(getActivity()))) {
								showAlert(JeevOMUtil.INTERNET_CONNECTION);
							} else if (arg0.getCause() instanceof SocketTimeoutException) {
								showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
							} else if (arg0.getResponse() == null) {
								showAlert(JeevOMUtil.SOMETHING_WRONG);
							}
						} else if (arg0.getResponse().getStatus() > 400) {
							showAlert(JeevOMUtil.SOMETHING_WRONG);
						} else {
							String json = new String(((TypedByteArray) arg0
									.getResponse().getBody()).getBytes());
							Gson gson = new GsonBuilder().setPrettyPrinting()
									.create();
							ApiResponse phoneEmailExistsErrors = gson.fromJson(
									json, ApiResponse.class);
							String code = phoneEmailExistsErrors.getStatus()
									.getCode();
							String message = phoneEmailExistsErrors.getStatus()
									.getMessage();
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
							} else {
								showAlert(JeevOMUtil.SOMETHING_WRONG);
							}
						}
					}

					@Override
					public void success(ApiResponse<Status> arg0,
							retrofit.client.Response arg1) {

						jeevomSession.setCellNumber(phoneValue);
						phoneCaptureCommunicator
								.phoneNumberCapturedSuccessfully(phone_no_edit_text
										.getText().toString().trim());
						dismiss();

					}
				});

	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}
}
