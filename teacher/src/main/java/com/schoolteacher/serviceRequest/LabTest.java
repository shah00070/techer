package com.schoolteacher.serviceRequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.DocumentOptionDialog;
import com.schoolteacher.interfaces.DocumentOption;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.medvault.DocumentUploadActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.pojo.Address;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.DocumentList;
import com.schoolteacher.pojos.DocumentObject;
import com.schoolteacher.pojos.JeevFacilityInfo;
import com.schoolteacher.pojos.JeevProfileInfo;
import com.schoolteacher.pojos.LabTestFilterResults;
import com.schoolteacher.pojos.Medicine;
import com.schoolteacher.pojos.OrderAddress;
import com.schoolteacher.pojos.OrderAttributes;
import com.schoolteacher.pojos.SystemServiceConfigurationResult;
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
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class LabTest extends ActionBarActivity implements OnClickListener,
		OnCheckedChangeListener,
		RadioGroup.OnCheckedChangeListener, DocumentOption {
	CallToActionRequest requestSendMessage;
	private final int ATTACH = 200;
	private final int SELECT = 400;
	private DocumentList document;
	private List<DocumentList> selectedDocuments;
	private List<DocumentObject> documentList;
	private Button btn_submit;
	private GlobalAlert globalAlert;
	private boolean bookedForSelf;
	private int forMemberId;
	// sessions
	private JeevomSession session;
	private ValuesManager valuesManager;
	UserCurrentLocationManager locationManager;

	Gson gson;


	private CheckBox basic_info_checkbox, medical_info_checkbox, health_info_checkbox,home_address_text;
	private LinearLayout address_layout;
	private EditText address_value,general_message, home_address;
	private TextView shared_text,prof_name;
	private LinearLayout medicine_order_layout, container_order,documents_container,home_address_container;
	private Button add_more;
	private DialogFragment progressDialog;

	private JeevProfileInfo professional_profile;
	private JeevFacilityInfo facility_profile;
	private double fees;
	private int service_id;
	private String requestor_type;
	private CircleImageView prof_image;


	private RadioGroup time_group;
	private String timePreference,authToken = null,referenceNo,imageUrl = null;

	private boolean isDirectLabTest;
	private Toolbar toolbar;

	private RelativeLayout btn_attachment,image_name;
	private ArrayList<String> labTestHintsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.jeev_lab_test);
		labTestHintsList = new ArrayList<>();
		selectedDocuments = new LinkedList<DocumentList>();
		globalAlert = new GlobalAlert(this);
		gson = new GsonBuilder().create();
		documentList = new ArrayList<>();
		locationManager=new UserCurrentLocationManager(getApplicationContext());
		// sessions
		session = new JeevomSession(getApplicationContext());
		if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
			authToken = "Basic " + session.getAuthToken();
		}

		valuesManager = new ValuesManager(getApplicationContext());

		setUpActionBar();

		Intent intent = getIntent();
		if (this.getIntent().getExtras() != null) {
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
		} else {
			isDirectLabTest = true;
		}

		makeViewReferences();
		setInitialView();

	}
	private void setUpActionBar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar_lab);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Book a Lab Test");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

	private void makeViewReferences() {
		prof_name = (TextView) findViewById(R.id.prof_name);
		prof_image = (CircleImageView) findViewById(R.id.prof_image);

		home_address_container = (LinearLayout) findViewById(R.id.home_address_container);
		home_address = (EditText) findViewById(R.id.home_address);

		btn_submit = (Button) findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);

		time_group = (RadioGroup) findViewById(R.id.time_group);
		time_group.setOnCheckedChangeListener(this);
		home_address_text = (CheckBox) findViewById(R.id.home_address_text);
		home_address_text.setOnCheckedChangeListener(this);
		image_name = (RelativeLayout) findViewById(R.id.image_name);
		medicine_order_layout = (LinearLayout) findViewById(R.id.medicine_order_layout);
		container_order = (LinearLayout) findViewById(R.id.container_order);
		setOrderLayout();

		add_more = (Button) findViewById(R.id.add_more);
		add_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (container_order.getChildCount() < 10)
					setOrderLayout();

			}
		});

		btn_attachment = (RelativeLayout) findViewById(R.id.btn_attachment);
		btn_attachment.setOnClickListener(this);
		documents_container = (LinearLayout) findViewById(R.id.documents_container);

	}

	private void setOrderLayout() {
		LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		final View view = inflator.inflate(R.layout.lab_test_order, null);
		TextView serial_no = (TextView) view.findViewById(R.id.serial_no);
		ImageView delete = (ImageView) view.findViewById(R.id.delete);
		AutoCompleteTextView item_name = (AutoCompleteTextView) view
				.findViewById(R.id.item_name);
		if (container_order.getChildCount() == 0) {
			delete.setVisibility(View.INVISIBLE);
		}

		if (container_order.getChildCount() % 2 == 0) {
			Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
					R.anim.trans_left_in);
			view.startAnimation(fadeInAnimation);
		} else {
			Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
					R.anim.trans_right_in);
			view.startAnimation(fadeInAnimation);
		}
		container_order.addView(view);
		delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				((LinearLayout) view.getParent()).removeView(view);
				container_order.postInvalidate();

				for (int i = 0; i < container_order.getChildCount(); i++) {
					RelativeLayout childAt = (RelativeLayout) container_order
							.getChildAt(i);

					View childAt2 = childAt.getChildAt(0);
					if (childAt2 instanceof TextView) {
						((TextView) childAt2).setText(String.valueOf(i + 1));
					}

				}
			}
		});
		serial_no.setText(String.valueOf(container_order.getChildCount()));

		ArrayAdapter<String> hintsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, labTestHintsList);
		item_name.setThreshold(1);// will start working from first character
		item_name.setAdapter(hintsAdapter);
	}

	private void setInitialView() {

		// set image
		if (!isDirectLabTest) {

			if ("professional".equalsIgnoreCase(requestor_type)) {
				if (!(CommonCode.isNullOrEmpty(professional_profile.getPhoto()))) {
					imageUrl = professional_profile.getPhoto();
				}
			} else if ("facility".equalsIgnoreCase(requestor_type)) {
				if (!(CommonCode.isNullOrEmpty(facility_profile
						.getFacilityProfile().getProfilePhoto()))) {
					imageUrl = facility_profile.getFacilityProfile()
							.getProfilePhoto();
				}
			}

			Picasso.with(LabTest.this).load(imageUrl)
					.placeholder(R.drawable.jeevom_back)
					.error(R.drawable.jeevom_back).into(prof_image);

			// set name
			StringBuilder name = new StringBuilder();
			if ("professional".equalsIgnoreCase(requestor_type)) {
				if (!CommonCode.isNullOrEmpty(professional_profile.getTitle())) {
					name.append(professional_profile.getTitle() + " ");
				}
				if (!CommonCode.isNullOrEmpty(professional_profile
						.getFirstName())) {
					name.append(professional_profile.getFirstName() + " ");
				}
				if (!CommonCode.isNullOrEmpty(professional_profile
						.getLastName())) {
					name.append(professional_profile.getLastName());
				}

			} else if ("facility".equalsIgnoreCase(requestor_type)) {
				if (!CommonCode.isNullOrEmpty(facility_profile
						.getFacilityProfile().getName())) {
					name.append(facility_profile.getFacilityProfile().getName());
				}
			}

			if (!CommonCode.isNullOrEmpty(name.toString())) {
				prof_name.setText(name.toString().trim());
			}
		} else {
			image_name.setVisibility(View.GONE);
		}

		getSystemServiceConfigurations();
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
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

		if (arg0 == SELECT && arg1 == SELECT) {

			Bundle getBundle = arg2.getExtras();
			ArrayList<DocumentList> parcelableArrayList = getBundle
					.getParcelableArrayList("documents");
			selectedDocuments.addAll(parcelableArrayList);
			showDocumentsOnScreen();

		}

		if (arg0 == 1) {
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_submit:
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
		boolean isAllValid = true;

		for (int i = 0; i < container_order.getChildCount(); i++) {
			RelativeLayout childAt = (RelativeLayout) container_order
					.getChildAt(i);

			EditText childAt2 = (EditText) childAt.getChildAt(1);

			if (CommonCode.isNullOrEmpty(childAt2.getText().toString())) {
				isAllValid = false;
				Crouton.makeText(LabTest.this, "Please enter Test Name",
						Style.ALERT).show();
				childAt2.requestFocus();
				return;
			}

		}

		if (home_address_text.isChecked()) {
			if (CommonCode.isNullOrEmpty(home_address.getText().toString()
					.trim())) {
				isAllValid = false;
				Crouton.makeText(LabTest.this, "Please enter address",
						Style.ALERT).show();
				home_address.requestFocus();
			}
		}

		if (CommonCode.isNullOrEmpty(timePreference)) {
			isAllValid = false;
			Crouton.makeText(LabTest.this,
					"Please select delivery time preference", Style.ALERT)
					.show();
		}
		if (isAllValid) {
			makeCall();
		}
	}

	private void makeCall() {
		requestSendMessage = new CallToActionRequest();
		requestSendMessage.setId(0);
		if (!isDirectLabTest) {
			requestSendMessage.setServiceConfigurationId(String
					.valueOf(service_id));
		}
		requestSendMessage.setIsSupportRequest(false);

		if (!isDirectLabTest) {

			requestSendMessage.setFees(String.valueOf(fees));

			if ("professional".equalsIgnoreCase(requestor_type)) {
				requestSendMessage.setToProfessionalId(String
						.valueOf(professional_profile.getId()));
			} else if ("facility".equalsIgnoreCase(requestor_type)) {
				requestSendMessage
						.setToFacilityId(String.valueOf(facility_profile
								.getFacilityProfile().getId()));
			}

		} else {
			requestSendMessage.setIsSupportRequest(true);
			requestSendMessage.setDefaultServiceConfigurationId("9");
		}
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

		// order attributes
		OrderAttributes attr = new OrderAttributes();

		for (int i = 0; i < container_order.getChildCount(); i++) {
			RelativeLayout childAt = (RelativeLayout) container_order
					.getChildAt(i);

			AutoCompleteTextView childAt2 = (AutoCompleteTextView) childAt
					.getChildAt(1);
			if (i == 0) {
				attr.setTest1Name(childAt2.getText().toString().trim());
			}
			if (i == 1) {
				attr.setTest2Name(childAt2.getText().toString().trim());
			}
			if (i == 2) {
				attr.setTest3Name(childAt2.getText().toString().trim());
			}
			if (i == 3) {
				attr.setTest4Name(childAt2.getText().toString().trim());
			}
			if (i == 4) {
				attr.setTest5Name(childAt2.getText().toString().trim());
			}
			if (i == 5) {
				attr.setTest6Name(childAt2.getText().toString().trim());
			}
			if (i == 6) {
				attr.setTest7Name(childAt2.getText().toString().trim());
			}
			if (i == 7) {
				attr.setTest8Name(childAt2.getText().toString().trim());
			}
			if (i == 8) {
				attr.setTest9Name(childAt2.getText().toString().trim());
			}
			if (i == 9) {
				attr.setTest10Name(childAt2.getText().toString().trim());
			}
		}

		if (home_address_text.isChecked()) {
			attr.setIsLabTestAtHome("Yes");

			OrderAddress address = new OrderAddress();
			address.setAddressLine1(home_address.getText().toString().trim());
			requestSendMessage.setDeliveryRequestedAt(address);

		}
		attr.setTimePreference(timePreference);
		requestSendMessage.setAttributes(attr);

		goToStepTwo();
	}

	private void goToStepTwo() {
		Intent stepTwoIntent = new Intent(this, SendMessageStepTwo.class);
		Bundle bundle = new Bundle();
		if (isDirectLabTest) {
			bundle.putBoolean("isDirectRequest", true);
			bundle.putString("img_url", null);
			bundle.putString("name", null);
		} else {
			bundle.putString("img_url", imageUrl);
			bundle.putString("name", prof_name.getText().toString());
		}
		bundle.putSerializable("requestObject", requestSendMessage);
		if (!session.getLoggedInStatus()) {
			bundle.putSerializable("documents", (Serializable) documentList);
		}
		stepTwoIntent.putExtras(bundle);
		startActivityForResult(stepTwoIntent, 1);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.radio_button_immediate)
			timePreference = "Immediate";
		if (checkedId == R.id.radio_button_morning)
			timePreference = "Morning";
		if (checkedId == R.id.radio_button_afternoon)
			timePreference = "Afternoon";
		if (checkedId == R.id.radio_button_evening)
			timePreference = "Evening";

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		switch (buttonView.getId()) {
		case R.id.general_medicine:
			if (isChecked) {
				medicine_order_layout.setVisibility(View.VISIBLE);
				Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
						R.anim.fade_in);
				medicine_order_layout.startAnimation(fadeInAnimation);
			} else
				medicine_order_layout.setVisibility(View.GONE);

			break;

		case R.id.home_address_text:

			if (isChecked) {
				home_address.setVisibility(View.VISIBLE);
				Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
						R.anim.fade_in);
				home_address.startAnimation(fadeInAnimation);
				home_address_container.setVisibility(View.VISIBLE);
				if (session.getLoggedInStatus()) {
					if (session.getUserAddress() != null) {
						StringBuilder addressValue = new StringBuilder();
						Address userAddress = session.getUserAddress();

						if (!CommonCode.isNullOrEmpty(userAddress.getLine1()))
							addressValue.append("," + userAddress.getLine1());
						if (!CommonCode.isNullOrEmpty(userAddress.getArea()))
							addressValue.append("," + userAddress.getArea());
						if (!CommonCode.isNullOrEmpty(userAddress.getCity()))
							addressValue.append("," + userAddress.getCity());
						if (!CommonCode.isNullOrEmpty(userAddress.getState()))
							addressValue.append("," + userAddress.getState());
						if (!CommonCode.isNullOrEmpty(userAddress.getCountry()))
							addressValue.append("," + userAddress.getCountry());

						if (!CommonCode.isNullOrEmpty(addressValue.toString()))
							home_address.setText(CommonCode.trimCommas(
									addressValue.toString()).trim());
					}
				}
			} else {
				home_address.setVisibility(View.GONE);
				home_address_container.setVisibility(View.GONE);
			}

			break;
		}
	}

	private void getSystemServiceConfigurations() {
		RestAdapter serviceConfigAdapter = new RestAdapter.Builder()
				.setEndpoint(JeevOMUtil.baseUrl).build();
		ServiceRequisition service_config_interface = serviceConfigAdapter
				.create(ServiceRequisition.class);
		progressDialog = ProgressDialogFragment.newInstance();
		progressDialog.show(getSupportFragmentManager(), "dialog");
		progressDialog.setCancelable(false);
		service_config_interface.getSystemServiceConfigurations(
				locationManager.getUserLocation(),"SG9",

		new Callback<SystemServiceConfigurationResult>() {

			@Override
			public void failure(RetrofitError arg0) {
				progressDialog.dismissAllowingStateLoss();

				if (arg0.isNetworkError()) {
					if (!(Connectivity.checkConnectivity(LabTest.this))) {
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
					SystemServiceConfigurationResult searchResultsResponse = gson
							.fromJson(json,
									SystemServiceConfigurationResult.class);
					String code = searchResultsResponse.getStatus().getCode();
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
			public void success(SystemServiceConfigurationResult arg0,
					Response arg1) {
				progressDialog.dismissAllowingStateLoss();
				String code = arg0.getStatus().getCode();
				if (code.equals("Success")) {

					if (arg0.getData() != null || arg0.getData().size() > 0) {
						// service_id = arg0.getData().get(0).getId();

						if (arg0.getData().get(0).isIsAttachDocumentAllowed())
							btn_attachment.setVisibility(View.VISIBLE);
					}

					getTestsHintValues();
				}
			}
		});

	}

	protected void getTestsHintValues() {

		RestAdapter slabTestAdapter = new RestAdapter.Builder().setEndpoint(
				JeevOMUtil.baseUrl).build();
		ServiceRequisition test_interface = slabTestAdapter
				.create(ServiceRequisition.class);
		progressDialog = ProgressDialogFragment.newInstance();
		progressDialog.show(getSupportFragmentManager(), "dialog");
		progressDialog.setCancelable(false);
		test_interface.getLabTestHints(
				new Callback<LabTestFilterResults>() {

			@Override
			public void failure(RetrofitError arg0) {
				progressDialog.dismissAllowingStateLoss();

				if (arg0.isNetworkError()) {
					if (!(Connectivity.checkConnectivity(LabTest.this))) {
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
					LabTestFilterResults searchResultsResponse = gson.fromJson(
							json, LabTestFilterResults.class);
					String code = searchResultsResponse.getStatus().getCode();
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
			public void success(LabTestFilterResults arg0, Response arg1) {
				progressDialog.dismissAllowingStateLoss();
				String code = arg0.getStatus().getCode();
				if (code.equals("Success")) {

					List<Medicine> medicine = arg0.getData().getMedicine();

					if (medicine != null && medicine.size() > 0) {
						for (Medicine medicineObject : medicine) {

							labTestHintsList.add(medicineObject
									.getDisplayName());

						}
					}
				}
			}
		});

	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
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
}