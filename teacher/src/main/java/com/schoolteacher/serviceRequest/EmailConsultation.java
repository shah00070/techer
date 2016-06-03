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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.dialog.DocumentOptionDialog;
import com.schoolteacher.interfaces.DocumentOption;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.medvault.DocumentUploadActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.CallToActionMessage;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.DocumentList;
import com.schoolteacher.pojos.DocumentObject;
import com.schoolteacher.pojos.JeevFacilityInfo;
import com.schoolteacher.pojos.JeevProfileInfo;
import com.schoolteacher.pojos.MemberAssociation;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class EmailConsultation extends ActionBarActivity implements
		OnClickListener, DocumentOption {
	private final int ATTACH = 200;
	private final int SELECT = 400;
	private GlobalAlert globalAlert;
	private GlobelAlertWithFinish globalAlertFinish;
	private int forMemberId,service_id;
	private JeevomSession session;
	private ValuesManager valuesManager;
	private List<MemberAssociation> memberAssociation;
	private CircleImageView prof_image;
	private TextView prof_name, prof_fee;
	private Button btn_message_send_message;
	private CallToActionRequest requestSendMessage;
	private EditText subject_value, message_message_value;
	private List<DocumentObject> documentList;
	private double fees;
	private DialogFragment progressDialog;
	private DocumentList document;
	private List<DocumentList> selectedDocuments;
	private JeevProfileInfo professional_profile;
	private JeevFacilityInfo facility_profile;
	private Toolbar toolbar;
	private String authToken = null,imageUrl = null,email_consultation_type,referenceNo,requestor_type,consult_for_type;
	private RelativeLayout btn_attachment,image_name;
	private LinearLayout documents_container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		setContentView(R.layout.jeev_email_consult);
		hideKeyboard();
		selectedDocuments = new LinkedList<DocumentList>();
		documentList = new ArrayList<>();


		// get alert window dialog references
		getAlertWindowDialogReferences();

		//set up action bar
		setUpActionBar();
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
		email_consultation_type = intent
				.getStringExtra("email_consultation_type");
		valuesManager = new ValuesManager(getApplicationContext());
		session = new JeevomSession(getApplicationContext());
		if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
			authToken = "Basic " + session.getAuthToken();
		}

		makeReferences();

		fillDetails();

	}
	private void getAlertWindowDialogReferences() {
		globalAlert = new GlobalAlert(this);
		globalAlertFinish = new GlobelAlertWithFinish(this);
		globalAlertFinish.setCancelable(false);
	}

	private void setUpActionBar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar_email);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Email Consultation");
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

	private void makeReferences() {
		image_name = (RelativeLayout) findViewById(R.id.image_name);

		image_name.setBackgroundColor(getResources().getColor(R.color.Gray));

		btn_attachment = (RelativeLayout) findViewById(R.id.btn_attachment);
		btn_attachment.setOnClickListener(this);

		if (email_consultation_type.equals("advance")) {
			btn_attachment.setVisibility(View.VISIBLE);
		}

		documents_container = (LinearLayout) findViewById(R.id.documents_container);

		prof_fee = (TextView) findViewById(R.id.prof_fee);
		prof_name = (TextView) findViewById(R.id.prof_name);

		prof_fee.setTextColor(getResources().getColor(R.color.White));
		prof_name.setTextColor(getResources().getColor(R.color.White));

		prof_image = (CircleImageView) findViewById(R.id.prof_image);

		btn_message_send_message = (Button) findViewById(R.id.btn_message_send_message);
		btn_message_send_message.setOnClickListener(this);

		subject_value = (EditText) findViewById(R.id.subject_value);
		message_message_value = (EditText) findViewById(R.id.message_message_value);
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
		Picasso.with(EmailConsultation.this).load(imageUrl)
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

		if (!CommonCode.isNullOrEmpty(name.toString()))
			prof_name.setText(name.toString());

		// set fee

		prof_fee.setText(String.valueOf(fees));



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

		} else if (arg0 == SELECT && arg1 == SELECT) {

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

				DocumentOptionDialog showDialog = new DocumentOptionDialog(
						EmailConsultation.this);
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

		if (isAllValid) {
			if (CommonCode.isNullOrEmpty(subject_value.getText().toString()
					.trim())) {
				Crouton.makeText(EmailConsultation.this,
						"Please enter Subject", Style.ALERT).show();
				return;
			}

			if (CommonCode.isNullOrEmpty(message_message_value.getText()
					.toString().trim())) {
				Crouton.makeText(EmailConsultation.this,
						"Please enter Message", Style.ALERT).show();
				return;
			}

			makeCall();
		}
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

		if (email_consultation_type.equals("advance")) {
			if (selectedDocuments != null && selectedDocuments.size() > 0) {

				StringBuilder stringValue = new StringBuilder();
				for (int i = 0; i < selectedDocuments.size(); i++) {

					if (i == 0) {
						stringValue.append(selectedDocuments.get(i).getId());
					} else {
						stringValue.append(", "
								+ selectedDocuments.get(i).getId());
					}
				}

				requestSendMessage.setDocumentIds(stringValue.toString());
			}
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

		goToStepTwo();
	}

	private void goToStepTwo() {
		Intent stepTwoIntent = new Intent(this, EmailconsultationStepTwo.class);
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