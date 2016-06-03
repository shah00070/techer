package com.schoolteacher.serviceRequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.CellNumberCaptureDialog;
import com.schoolteacher.dialog.CellNumberVerificationDialog;
import com.schoolteacher.dialog.DocumentOptionDialog;
import com.schoolteacher.interfaces.CapturePhoneInterface;
import com.schoolteacher.interfaces.DocumentOption;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.medvault.DocumentUploadActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.CallToActionMessage;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.DocumentList;
import com.schoolteacher.pojos.DocumentObject;
import com.schoolteacher.pojos.JeevFacilityInfo;
import com.schoolteacher.pojos.JeevProfileInfo;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceConfigInfo;
import com.schoolteacher.pojos.ServiceConfiguration;
import com.schoolteacher.pojos.ServiceRequisitionResult;
import com.schoolteacher.service.ServiceRequisition;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class RequestQuote extends ActionBarActivity implements OnClickListener,
		DocumentOption, CapturePhoneInterface {
	UserCurrentLocationManager locationManager;


	private DocumentList document;
	private List<DocumentList> selectedDocuments;
	private final int ATTACH = 200;
	private final int SELECT = 400;
	GlobalAlert globalAlert;
	private GlobelAlertWithFinish globalAlertFinish;
	int current_year, currentMonth, currentDay;
	int forMemberId;
	boolean bookedForSelf;
	JeevomSession session;
	ValuesManager valuesManager;
	CircleImageView prof_image;
	TextView prof_name;
	Button btn_message_send_message;
	private List<DocumentObject> documentList;
	EditText subject_value, message_message_value;
	RelativeLayout image_name;
	double fees;
	int service_id;
	DialogFragment newFragment;
	String requestor_type;
	JeevProfileInfo professional_profile;
	JeevFacilityInfo facility_profile;
	String referenceNo;

	Toolbar toolbar;
	String authToken = null;
	String imageUrl = null;
	CallToActionRequest requestSendMessage;

	RelativeLayout btn_attachment;
	LinearLayout documents_container;
	private String profile_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		setContentView(R.layout.jeev_request_quote);
		hideKeyboard();
		locationManager=new UserCurrentLocationManager(getApplicationContext());
		documentList = new ArrayList<>();
		selectedDocuments = new LinkedList<DocumentList>();
		globalAlert = new GlobalAlert(this);
		globalAlertFinish = new GlobelAlertWithFinish(this);
		globalAlertFinish.setCancelable(false);

		toolbar = (Toolbar) findViewById(R.id.toolbar_request_quote);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(
				Html.fromHtml("<font color='#ffffff'>Request Quote</font>"));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		requestor_type = intent.getStringExtra("service_requestor");

		if (requestor_type.equals("professional")) {
			professional_profile = (JeevProfileInfo) intent
					.getSerializableExtra("profile_object");
		} else {
			facility_profile = (JeevFacilityInfo) intent
					.getSerializableExtra("profile_object");
		}

		fees = intent.getDoubleExtra("fee", 0);
		service_id = intent.getIntExtra("service_id", 0);

		valuesManager = new ValuesManager(getApplicationContext());
		session = new JeevomSession(getApplicationContext());
		if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
			authToken = "Basic " + session.getAuthToken();
		}
		makeReferences();

		fillDetails();
		if (requestor_type.equalsIgnoreCase("professional")) {
			profile_id = professional_profile.getProfileId();
			getServiceConfigurations(profile_id);
		} else {
			profile_id = facility_profile.getFacilityProfile()
					.getUniquePublicID();
			getFacilityServiceConfigurations(profile_id);
		}

	}

	private void getFacilityServiceConfigurations(String pro_id) {
		RestAdapter serviceConfigAdapter = new RestAdapter.Builder()
				.setEndpoint(JeevOMUtil.baseUrl).build();
		ServiceRequisition service_config_interface = serviceConfigAdapter
				.create(ServiceRequisition.class);
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
		service_config_interface.getServiceConfigurations(
				locationManager.getUserLocation(),authToken,
				pro_id.trim(),

				new Callback<ServiceConfigInfo>() {

					@Override
					public void failure(RetrofitError arg0) {
						newFragment.dismissAllowingStateLoss();

						if (arg0.isNetworkError()) {
							if (!(Connectivity
									.checkConnectivity(RequestQuote.this))) {
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
							SearchResultsResponse searchResultsResponse = gson
									.fromJson(json, SearchResultsResponse.class);
							String code = searchResultsResponse.getStatus()
									.getCode();
							String message = searchResultsResponse.getStatus()
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
							} else if (code.equals("BE-1004")) {
								showAlert(message);
							}
						}
					}

					@Override
					public void success(ServiceConfigInfo arg0, Response arg1) {
						newFragment.dismissAllowingStateLoss();
						String code = arg0.getStatus().getCode();
						if (code.equals("Success")) {

							List<ServiceConfiguration> serviceConfigurations = arg0
									.getData().getServiceConfigurations();

							for (ServiceConfiguration serviceConfiguration : serviceConfigurations) {
								if (serviceConfiguration.getUniquePublicID()
										.equals("SG10")) {

									if (serviceConfiguration
											.isIsAttachDocumentAllowed()) {

										// code to show hide document attach
										// button
										btn_attachment
												.setVisibility(View.VISIBLE);
									}
								}
							}
						}
					}
				});

	}

	private void getServiceConfigurations(String pro_id) {
		RestAdapter serviceConfigAdapter = new RestAdapter.Builder()
				.setEndpoint(JeevOMUtil.baseUrl).build();
		ServiceRequisition service_config_interface = serviceConfigAdapter
				.create(ServiceRequisition.class);
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog_new");
		newFragment.setCancelable(false);
		service_config_interface.getServiceConfigurations(
				locationManager.getUserLocation(),authToken,
				pro_id.trim(),

				new Callback<ServiceConfigInfo>() {

					@Override
					public void failure(RetrofitError arg0) {
						newFragment.dismissAllowingStateLoss();

						if (arg0.isNetworkError()) {
							if (!(Connectivity
									.checkConnectivity(RequestQuote.this))) {
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
							SearchResultsResponse searchResultsResponse = gson
									.fromJson(json, SearchResultsResponse.class);
							String code = searchResultsResponse.getStatus()
									.getCode();
							String message = searchResultsResponse.getStatus()
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
							} else if (code.equals("BE-1004")) {
								showAlert(message);
							}
						}
					}

					@Override
					public void success(ServiceConfigInfo arg0, Response arg1) {
						newFragment.dismissAllowingStateLoss();
						String code = arg0.getStatus().getCode();
						if (code.equals("Success")) {

							List<ServiceConfiguration> serviceConfigurations = arg0
									.getData().getServiceConfigurations();

							for (ServiceConfiguration serviceConfiguration : serviceConfigurations) {
								if (serviceConfiguration.getUniquePublicID()
										.equals("SG10")) {

									if (serviceConfiguration
											.isIsAttachDocumentAllowed()) {

										// code to show hide document attach
										// button

										btn_attachment
												.setVisibility(View.VISIBLE);
									}
								}
							}
						}
					}
				});

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

	private void makeReferences() {
		image_name = (RelativeLayout) findViewById(R.id.image_name);
		image_name.setBackgroundColor(getResources().getColor(R.color.Gray));
		prof_name = (TextView) findViewById(R.id.prof_name);
		prof_name.setTextColor(getResources().getColor(R.color.White));
		prof_image = (CircleImageView) findViewById(R.id.prof_image);

		btn_message_send_message = (Button) findViewById(R.id.btn_message_send_message);
		if (session.getLoggedInStatus()) {
			btn_message_send_message.setText("Send");

		}
		btn_message_send_message.setOnClickListener(this);

		subject_value = (EditText) findViewById(R.id.subject_value);
		message_message_value = (EditText) findViewById(R.id.message_message_value);

		btn_attachment = (RelativeLayout) findViewById(R.id.btn_attachment);
		btn_attachment.setOnClickListener(this);
		documents_container = (LinearLayout) findViewById(R.id.documents_container);
	}

	private void fillDetails() {

		// set image

		if (requestor_type.equals("professional")) {
			if (!(CommonCode.isNullOrEmpty(professional_profile.getPhoto()))) {
				imageUrl = professional_profile.getPhoto();
			}
		} else {
			if (!(CommonCode.isNullOrEmpty(facility_profile
					.getFacilityProfile().getProfilePhoto()))) {
				imageUrl = facility_profile.getFacilityProfile()
						.getProfilePhoto();
			}
		}
		Picasso.with(RequestQuote.this).load(imageUrl)
				.placeholder(R.drawable.jeevom_back)
				.error(R.drawable.jeevom_back).into(prof_image);

		// set name
		StringBuilder name = new StringBuilder();
		if (requestor_type.equals("professional")) {
			if (!CommonCode.isNullOrEmpty(professional_profile.getTitle())) {
				name.append(professional_profile.getTitle() + " ");
			}
			if (!CommonCode.isNullOrEmpty(professional_profile.getFirstName())) {
				name.append(professional_profile.getFirstName() + " ");
			}
			if (!CommonCode.isNullOrEmpty(professional_profile.getLastName())) {
				name.append(professional_profile.getLastName());
			}

		} else {
			if (!CommonCode.isNullOrEmpty(facility_profile.getFacilityProfile()
					.getName())) {
				name.append(facility_profile.getFacilityProfile().getName());
			}
		}

		if (!CommonCode.isNullOrEmpty(name.toString())) {
			prof_name.setText(name.toString());
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if (arg0 == ATTACH && arg1 == ATTACH) {
			if (arg2.getExtras() != null) {
				if (session.getLoggedInStatus()) {
					document = (DocumentList) arg2
							.getSerializableExtra("document");
					selectedDocuments.add(document);
					showDocumentsOnScreen();
				} else {
					Bundle getBundle = arg2.getExtras();
					DocumentObject documentObject = (DocumentObject) getBundle
							.getSerializable("document");
					documentList.add(documentObject);
					showUploadedDocumentsOnScreen();
				}
			}

		}

		else if (arg0 == SELECT && arg1 == SELECT) {

			Bundle getBundle = arg2.getExtras();
			ArrayList<DocumentList> parcelableArrayList = getBundle
					.getParcelableArrayList("documents");
			selectedDocuments.addAll(parcelableArrayList);
			showDocumentsOnScreen();

		} else {

			finish();
		}

	}

	private void showUploadedDocumentsOnScreen() {

		if (documents_container.getChildCount() > 0) {
			documents_container.removeAllViews();
		}

		if (documentList.size() > 0) {
			for (int i = 0; i < documentList.size(); i++) {

				LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				final View view = inflator.inflate(
						R.layout.attach_document_row, null);
				TextView document_name = (TextView) view
						.findViewById(R.id.document_name);

				ImageView delete = (ImageView) view.findViewById(R.id.remove);

				document_name.setText(documentList.get(i).getName());
				final String id = documentList.get(i).getName();

				documents_container.addView(view);
				delete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						((LinearLayout) view.getParent()).removeView(view);
						documents_container.postInvalidate();

						for (int j = 0; j < documentList.size(); j++) {
							if (documentList.get(j).getName() == id) {
								documentList.remove(j);
							}
						}

					}
				});

			}
		}

	}

	private void showDocumentsOnScreen() {

		if (documents_container.getChildCount() > 0) {
			documents_container.removeAllViews();
		}

		if (selectedDocuments.size() > 0) {
			for (int i = 0; i < selectedDocuments.size(); i++) {

				LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				final View view = inflator.inflate(
						R.layout.attach_document_row, null);
				TextView document_name = (TextView) view
						.findViewById(R.id.document_name);

				ImageView delete = (ImageView) view.findViewById(R.id.remove);

				document_name.setText(selectedDocuments.get(i).getName());
				final int id = selectedDocuments.get(i).getId();

				documents_container.addView(view);
				delete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						((LinearLayout) view.getParent()).removeView(view);
						documents_container.postInvalidate();

						for (int j = 0; j < selectedDocuments.size(); j++) {
							if (selectedDocuments.get(j).getId() == id) {
								selectedDocuments.remove(j);
							}
						}

					}
				});

			}
		}

	}

	private void hideKeyboard() {
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_message_send_message:

			sendRequest();

			break;
		case R.id.btn_attachment:

			if (session.getLoggedInStatus()) {
				// user logged in -- Show user dialog to choose from device or
				// MedValut

				DocumentOptionDialog showDialog = new DocumentOptionDialog(this);
				showDialog.show();
			} else {
				// User is not logged in -- take him directly to Upload document
				// screen
				Intent attachIntent = new Intent(this,
						DocumentUploadActivity.class);
				startActivityForResult(attachIntent, ATTACH);
			}
			break;
		}
	}

	private void sendRequest() {

		if (CommonCode.isNullOrEmpty(subject_value.getText().toString().trim())) {
			Crouton.makeText(RequestQuote.this, "Please enter Subject",
					Style.ALERT).show();
			return;
		}

		if (CommonCode.isNullOrEmpty(message_message_value.getText().toString()
				.trim())) {
			Crouton.makeText(RequestQuote.this, "Please enter Message",
					Style.ALERT).show();
			return;
		}

		makeCall();

	}

	private void makeCall() {
		requestSendMessage = new CallToActionRequest();
		requestSendMessage.setId(0);
		requestSendMessage
				.setServiceConfigurationId(String.valueOf(service_id));
		requestSendMessage.setIsSupportRequest(false);
		if (requestor_type.equals("professional")) {
			requestSendMessage.setToProfessionalId(String
					.valueOf(professional_profile.getId()));
		} else {
			requestSendMessage.setToFacilityId(String.valueOf(facility_profile
					.getFacilityProfile().getId()));
		}
		requestSendMessage.setFees(String.valueOf(fees));

		if (selectedDocuments != null && selectedDocuments.size() > 0) {

			StringBuilder stringValue = new StringBuilder();
			for (int i = 0; i < selectedDocuments.size(); i++) {

				if (i == 0) {
					stringValue.append(selectedDocuments.get(i).getId());
				} else {
					stringValue.append(", " + selectedDocuments.get(i).getId());
				}
			}

			requestSendMessage.setDocumentIds(stringValue.toString());
		} else {
			requestSendMessage.setDocumentIds("");
		}

		// message object
		List<CallToActionMessage> messageList = new LinkedList<>();
		CallToActionMessage message = new CallToActionMessage();
		if (session.getLoggedInStatus()) {
			message.setFromMemberId(String.valueOf(session.getMemberId()));
		}
		if (requestor_type.equals("professional")) {
			message.setToProfessionalId(String.valueOf(professional_profile
					.getId()));
		} else {
			message.setToFacilityId(String.valueOf(facility_profile
					.getFacilityProfile().getId()));
		}
		message.setSubject(subject_value.getText().toString().trim());
		message.setMessage(message_message_value.getText().toString().trim());
		messageList.add(message);
		requestSendMessage.setMessages(messageList);
		if (btn_message_send_message.getText().toString()
				.equalsIgnoreCase("Send")) {

			requestSendMessage.setByMemberId(String.valueOf(session
					.getMemberId()));

			requestSendMessage.setForMemberId(String.valueOf(session
					.getMemberId()));
			requestSendMessage.setForAge(session.getAge());
			requestSendMessage.setForGender(session.getGender());
			requestSendMessage.setForName(session.getName());

			if (!CommonCode.isNullOrEmpty(session.getCellNumber())) {
				if (session.getUserPhoneVerifyStatus()) {
					// cell no is available and verified -- make service call
					makeServiceCall(requestSendMessage);
				} else {
					// cell no is available but not verified
					Toast.makeText(RequestQuote.this,
							"Cell no not verified , show verification screen",
							Toast.LENGTH_SHORT).show();
					goToCellVerificationScreen(session.getCellNumber());
				}
			} else {
				// cell no not available -- show the user cell no capture screen

				try {
					CellNumberCaptureDialog showDialog = CellNumberCaptureDialog
							.newInstance();
					showDialog.show(getSupportFragmentManager(),
							"context_dialog_frag");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			goToStepTwo();
		}

	}

	private void makeServiceCall(final CallToActionRequest requestSendMessage) {

		RestAdapter serviceRequisitionAdapter = new RestAdapter.Builder()
				.setLogLevel(LogLevel.FULL)
				.setLog(new AndroidLog("service_requisition"))
				.setEndpoint(JeevOMUtil.baseUrl).build();
		ServiceRequisition service_req_interface = serviceRequisitionAdapter
				.create(ServiceRequisition.class);
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
		service_req_interface.makeServiceRequest(
				locationManager.getUserLocation(),authToken, requestSendMessage,

		new Callback<ServiceRequisitionResult>() {

			private String referenceNo;

			@Override
			public void failure(RetrofitError arg0) {
				newFragment.dismissAllowingStateLoss();

				if (arg0.isNetworkError()) {
					if (!(Connectivity.checkConnectivity(RequestQuote.this))) {
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
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					SearchResultsResponse searchResultsResponse = gson
							.fromJson(json, SearchResultsResponse.class);
					String code = searchResultsResponse.getStatus().getCode();
					String message = searchResultsResponse.getStatus()
							.getMessage();

					if (arg0.getResponse().getStatus() == 400) {
						showAlert(message);
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
					} else if (code.equals("BE-1004")) {
						showAlert(message);
					}
				}
			}

			@Override
			public void success(ServiceRequisitionResult arg0, Response arg1) {
				newFragment.dismissAllowingStateLoss();
				String code = arg0.getStatus().getCode();
				if (code.equals("Success")) {

					referenceNo = arg0.getData();

					showAlertFinish("Thank you for Submitting your request."
							+ "\n" + "Your Reference Number is: " + referenceNo);

				}
			}
		});
	}

	private void goToStepTwo() {
		Intent stepTwoIntent = new Intent(this, SendMessageStepTwo.class);
		Bundle bundle = new Bundle();
		bundle.putString("img_url", imageUrl);
		bundle.putString("name", prof_name.getText().toString());
		bundle.putSerializable("requestObject", requestSendMessage);
		if (!session.getLoggedInStatus()) {
			bundle.putSerializable("documents", (Serializable) documentList);
		}
		stepTwoIntent.putExtras(bundle);
		startActivityForResult(stepTwoIntent, 1);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// Applying Exit Animation;
		overridePendingTransition(R.anim.trans_right_in,
				R.anim.trans_right_exit);
	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

	private void showAlertFinish(String message) {
		globalAlertFinish.show();
		globalAlertFinish.setMessage(message);
	}

	@Override
	public void optionChoosenForDocumentUpload(String value) {

		if (value.equals("device")) {
			Intent attachIntent = new Intent(this, DocumentUploadActivity.class);
			startActivityForResult(attachIntent, ATTACH);
		} else {
			Intent medvaultIntent = new Intent(this,
					SelectDocumentForAttachment.class);
			startActivityForResult(medvaultIntent, SELECT);
		}

	}

	private void goToCellVerificationScreen(String cellNumber) {
		// phone no captured successfully -- send verification code
		try {
			CellNumberVerificationDialog showDialog = CellNumberVerificationDialog
					.newInstance();
			Bundle args = new Bundle();
			args.putString("cellNumber", cellNumber);
			showDialog.setArguments(args);
			showDialog.show(getSupportFragmentManager(), "context_dialog_frag");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void phoneNumberCapturedSuccessfully(String cellNumber) {
		goToCellVerificationScreen(cellNumber);

	}

	@Override
	public void phoneNumberVerifiedSuccessfully(boolean value) {
		if (value || !value) {
			makeServiceCall(requestSendMessage);
		}
	}

}