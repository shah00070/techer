package com.schoolteacher.serviceRequest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.interfaces.SendConsumerDetails;
import com.schoolteacher.library.main.SignUpLoginActivity;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.BloodGroup;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.Consumer;
import com.schoolteacher.pojos.ConsumerDetailsResponse;
import com.schoolteacher.pojos.ConsumerMemberDetails;
import com.schoolteacher.pojos.ConsumerUpdateRequestModel;
import com.schoolteacher.pojos.DocumentObject;
import com.schoolteacher.pojos.FamilyMembersResponse;
import com.schoolteacher.pojos.MemberAssociation;
import com.schoolteacher.pojos.UpdateConsumerResponse;
import com.schoolteacher.service.Member;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.Calendar;
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

public class AppointmentStepTwo extends ActionBarActivity implements
		OnClickListener, OnItemSelectedListener, SendConsumerDetails,
		OnDateSetListener {
	UserCurrentLocationManager locationManager;


	GlobalAlert globalAlert;
	public static final String DATEPICKER_TAG = "datepicker";
	int current_year, currentMonth, currentDay;
	String[] genders = { "Male", "Female", "Other" };
	Calendar calendar;
	long date = 0l;
	List<String> titleSpinnerItems = new LinkedList<>();
	List<MemberAssociation> memberAssociation;
	List<String> memberSpinnerList = new LinkedList<>();

	RelativeLayout image_name;
	CircleImageView img_doctor;
	TextView name_step_two, mode_step_two, select_date_time;
	ImageView date_time_selected_image, age_image;
	Button btn_step_two_submit;
	CallToActionRequest requestSendMessage;
	String newDobValue, appointment_type, name_value, image_url, date_value,
			time_value;

	Toolbar toolbar;
	TextView login_potential_message;
	LinearLayout action_container;
	RelativeLayout login_option;

	JeevomSession jeevom_session;
	ValuesManager valuesManager;

	String dateFormatted, consult_for_type, typeOfUser = null, gender,
			titleType = "Mr.";

	// new user
	Spinner new_title_spinner, new_user_gender_spinner;
	EditText new_first_name;
	EditText new_last_name;
	EditText new_phone;
	EditText new_email;
	TextView new_dob_value;
	ImageView new_dob_image;

	// logged in user views
	Spinner consult_for_value_spinner, gender_spinner;
	EditText name_consumer, age_consumer;
	CheckBox basic_info_checkbox, medical_info_checkbox, health_info_checkbox;
	RelativeLayout shared_info;

	DialogFragment newFragment;

	int forMemberId;
	boolean bookedForSelf;
	List<DocumentObject> documentList;
    private String serviceRequisition;
	boolean isPotentialDateOfBirth = false, isLoggedInDateOfBirth = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appointment_step_two);
		documentList = new LinkedList<>();
		globalAlert = new GlobalAlert(this);
		locationManager=new UserCurrentLocationManager(getApplicationContext());
		// set action bar
		toolbar = (Toolbar) findViewById(R.id.toolbar_step_two);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Provide Details");
		hideKeyboard();
		jeevom_session = new JeevomSession(getApplicationContext());
		valuesManager = new ValuesManager(getApplicationContext());

		Intent intent = getIntent();
		appointment_type = intent.getStringExtra("name_appointment_type");
		image_url = intent.getStringExtra("img_url");
		name_value = intent.getStringExtra("name");
		requestSendMessage = (CallToActionRequest) intent
				.getSerializableExtra("requestObject");
		date_value = intent.getStringExtra("date");
		time_value = intent.getStringExtra("time");
		serviceRequisition=intent.getStringExtra("service_request_type");
		if (!jeevom_session.getLoggedInStatus()) {
			documentList = (List<DocumentObject>) intent
					.getSerializableExtra("documents");
		}
		getViewReferences();

		fillInitialDetails();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return super.onCreateOptionsMenu(menu);
	}

	private void getViewReferences() {

		btn_step_two_submit = (Button) findViewById(R.id.btn_step_two_submit);
		btn_step_two_submit.setOnClickListener(this);

		login_potential_message = (TextView) findViewById(R.id.login_potential_message);
		login_option = (RelativeLayout) findViewById(R.id.login_option);
		login_option.setOnClickListener(this);

		action_container = (LinearLayout) findViewById(R.id.action_container);

		image_name = (RelativeLayout) findViewById(R.id.image_name);
		img_doctor = (CircleImageView) findViewById(R.id.img_doctor);

		mode_step_two = (TextView) findViewById(R.id.mode_step_two);
		name_step_two = (TextView) findViewById(R.id.name_step_two);
		select_date_time = (TextView) findViewById(R.id.select_date_time);
	}

	private void fillInitialDetails() {

		mode_step_two.setText(appointment_type);

		select_date_time.setText(date_value + " - " + time_value);

		// set name
		if (!CommonCode.isNullOrEmpty(name_value))
			name_step_two.setText(name_value);

		// set image
		Picasso.with(this).load(image_url).into(new Target() {

			@Override
			public void onPrepareLoad(Drawable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onBitmapLoaded(Bitmap arg0, LoadedFrom arg1) {
				setImage(arg0);
			}

			@Override
			public void onBitmapFailed(Drawable arg0) {
				// TODO Auto-generated method stub

			}
		});
		// set image and name layout background
		image_name.setBackgroundColor(getResources().getColor(R.color.Gray));
		name_step_two.setTextColor(getResources().getColor(R.color.White));
		mode_step_two.setTextColor(getResources().getColor(R.color.White));
		select_date_time.setTextColor(getResources().getColor(R.color.White));

		// set container if user is logged in or not
		if (!jeevom_session.getLoggedInStatus()) {
			login_potential_message.setVisibility(View.VISIBLE);
			login_option.setVisibility(View.VISIBLE);
			addPotentialMemberLayout();
		} else {
			getConsumerDetails();

		}

	}

	private void getConsumerDetails() {
		login_potential_message.setVisibility(View.GONE);
		login_option.setVisibility(View.GONE);
		GetConsumerCompleteDetails obj = new GetConsumerCompleteDetails(
				Integer.valueOf(jeevom_session.getConsumerIds().get(
						JeevomSession.JEEVOM_CONSUMER_ID)),
				String.valueOf(valuesManager.getVersion()),
				AppointmentStepTwo.this, jeevom_session);
		obj.getDetails();
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
	}

	private void addPotentialMemberLayout() {
		typeOfUser = "potential_member";

		LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflator.inflate(R.layout.potential_member, null);
		if (action_container.getChildCount() > 0) {
			action_container.removeAllViews();
		}
		action_container.addView(view);

		new_title_spinner = (Spinner) view.findViewById(R.id.new_title_spinner);
		new_user_gender_spinner = (Spinner) view
				.findViewById(R.id.new_user_gender_spinner);
		new_first_name = (EditText) view.findViewById(R.id.new_first_name);
		new_last_name = (EditText) view.findViewById(R.id.new_last_name);
		new_phone = (EditText) view.findViewById(R.id.new_phone);
		new_email = (EditText) view.findViewById(R.id.new_email);
		new_dob_value = (TextView) view.findViewById(R.id.new_dob_value);
		new_dob_image = (ImageView) view.findViewById(R.id.new_dob_image);
		new_dob_image.setOnClickListener(this);

		setTitleInSpinner();
		new_title_spinner.setOnItemSelectedListener(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				AppointmentStepTwo.this, R.layout.spinner_item,
				Arrays.asList(genders));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		new_user_gender_spinner.setAdapter(adapter);
		new_user_gender_spinner.setOnItemSelectedListener(this);

	}

	// Populate Title Spinner Method
	private void setTitleInSpinner() {
		titleSpinnerItems.add("Mr.");
		titleSpinnerItems.add("Ms.");
		titleSpinnerItems.add("Mrs.");
		titleSpinnerItems.add("Dr.");

		if (titleSpinnerItems.size() > 0) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					AppointmentStepTwo.this, R.layout.spinner_item,
					titleSpinnerItems);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			new_title_spinner.setAdapter(adapter);
		}

	}

	private void setImage(Bitmap arg0) {
		switch (getResources().getDisplayMetrics().densityDpi) {
		case DisplayMetrics.DENSITY_LOW:
			// d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
			// arg0, 32, 32, true));
			img_doctor.getLayoutParams().width = 32;
			img_doctor.getLayoutParams().height = 32;
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			// d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
			// arg0, 48, 48, true));
			img_doctor.getLayoutParams().width = 48;
			img_doctor.getLayoutParams().height = 48;
			break;
		case DisplayMetrics.DENSITY_HIGH:
			// d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
			// arg0, 72, 72, true));
			img_doctor.getLayoutParams().width = 72;
			img_doctor.getLayoutParams().height = 72;
			break;
		case DisplayMetrics.DENSITY_XHIGH:
			// d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
			// arg0, 96, 96, true));
			img_doctor.getLayoutParams().width = 96;
			img_doctor.getLayoutParams().height = 96;
			break;
		}

		Picasso.with(this).load(image_url).placeholder(R.drawable.jeevom_back)
				.error(R.drawable.jeevom_back).into(img_doctor);
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
	public void onBackPressed() {
		super.onBackPressed();
		// Applying Exit Animation;
		overridePendingTransition(R.anim.trans_right_in,
				R.anim.trans_right_exit);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.new_dob_image:
			isLoggedInDateOfBirth = false;
			isPotentialDateOfBirth = true;
			showDatePickerDialog();
			break;
		case R.id.age_image:
			isLoggedInDateOfBirth = true;
			isPotentialDateOfBirth = false;
			showDatePickerDialog();
			break;
		case R.id.login_option:
			callLogIn();
			break;

		case R.id.btn_step_two_submit:
			sendRequest();
			break;
		}
	}

	private void sendRequest() {
		boolean isAllValid = true;

		if ("potential_member".equals(typeOfUser)) {

			if (CommonCode.isNullOrEmpty(new_first_name.getText().toString())) {
				isAllValid = false;
				Crouton.makeText(AppointmentStepTwo.this,
						"Please enter First Name", Style.ALERT).show();
				return;
			}
			if (CommonCode.isNullOrEmpty(new_last_name.getText().toString())) {
				isAllValid = false;
				Crouton.makeText(AppointmentStepTwo.this,
						"Please enter Last Name", Style.ALERT).show();
				return;
			}
			if (CommonCode.isNullOrEmpty(new_dob_value.getText().toString()
					.trim())) {
				isAllValid = false;
				Crouton.makeText(AppointmentStepTwo.this,
						JeevOMUtil.MANDATORY_DATE_OF_BIRTH, Style.ALERT).show();
				return;
			}
			if (!CommonCode.validateBirthDate(date)) {
				isAllValid = false;
				Crouton.makeText(AppointmentStepTwo.this, JeevOMUtil.VALID_DOB,
						Style.ALERT).show();
				return;
			}
			if (CommonCode.isNullOrEmpty(new_phone.getText().toString().trim())) {
				Crouton.makeText(AppointmentStepTwo.this,
						"Please enter valid Phone No.", Style.ALERT).show();
				return;
			} else {

				if (!CommonCode.isNullOrEmpty(new_phone.getText().toString())) {
					if (!CommonCode.validatePhone(new_phone.getText()
							.toString())) {
						isAllValid = false;
						Crouton.makeText(AppointmentStepTwo.this,
								"Please enter valid Phone No.", Style.ALERT)
								.show();
						return;
					}
				}

				if (!CommonCode.isNullOrEmpty(new_email.getText().toString()
						.trim())) {
					if (!CommonCode.validateEmail(new_email.getText()
							.toString().trim())) {
						isAllValid = false;
						Crouton.makeText(AppointmentStepTwo.this,
								"Please enter Valid Email", Style.ALERT).show();
						return;

					}
				}
			}
		} else {

			if (!("Self".equalsIgnoreCase(consult_for_type))) {

				if (CommonCode.isNullOrEmpty(name_consumer.getText().toString()
						.trim())) {
					isAllValid = false;
					Crouton.makeText(AppointmentStepTwo.this,
							"Please enter Name", Style.ALERT).show();
					name_consumer.requestFocus();
					return;
				}

				if (CommonCode.isNullOrEmpty(age_consumer.getText().toString()
						.trim())) {
					isAllValid = false;
					Crouton.makeText(AppointmentStepTwo.this,
							"Please enter Age", Style.ALERT).show();
					age_consumer.requestFocus();
					return;
				}

				if (Integer.valueOf(age_consumer.getText().toString().trim()) > 150) {
					isAllValid = false;
					Crouton.makeText(AppointmentStepTwo.this,
							"Age should be less than 150", Style.ALERT).show();
					age_consumer.requestFocus();
					return;
				}
			} else if ("member".equals(typeOfUser)) {

				if (CommonCode.isNullOrEmpty(age_consumer.getText().toString())) {
					isAllValid = false;
					Crouton.makeText(AppointmentStepTwo.this,
							"Please provide date of birth", Style.ALERT).show();
					return;
				}

				if (!CommonCode.validateBirthDate(date)) {
					Crouton.makeText(AppointmentStepTwo.this,
							JeevOMUtil.VALID_DOB, Style.ALERT).show();
					return;
				}
			}
		}

		if (isAllValid) {

			if (!CommonCode.isNullOrEmpty(jeevom_session.getAge())) {
				if (Integer.valueOf(jeevom_session.getAge()) <= 0
						|| jeevom_session.getGender().equals("-")) {

					if (consult_for_type.equals("Self"))
						updateConsumerProfile();
					else
						makeCall();
				} else {
					makeCall();
				}
			} else {
				makeCall();
			}

		}
	}

	private void makeCall() {

		// if user is potential member
		if (typeOfUser.equals("potential_member")) {
			requestSendMessage.setIsBasicDetailsShared(true);
			requestSendMessage.setIsRequestedByVisitor(true);
			requestSendMessage.setVisitorTitle(titleType);
			requestSendMessage.setVisitorFirstName(new_first_name.getText()
					.toString().trim());
			requestSendMessage.setVisitorLastName(new_last_name.getText()
					.toString().trim());
			requestSendMessage.setVisitorGender(gender);
			requestSendMessage.setVisitorDateOfBirth(newDobValue);
			requestSendMessage.setVisitorEmail(new_email.getText().toString()
					.trim());
			requestSendMessage.setVisitorCellNumber(new_phone.getText()
					.toString().trim());
			requestSendMessage.setVisitorGender(gender);
		} else {
			requestSendMessage.setByMemberId(String.valueOf(jeevom_session
					.getMemberId()));
			if (consult_for_type.equalsIgnoreCase("Self")) {
				requestSendMessage.setForMemberId(String.valueOf(jeevom_session
						.getMemberId()));
				requestSendMessage.setForAge(jeevom_session.getAge());
				requestSendMessage.setForGender(jeevom_session.getGender());
				requestSendMessage.setForName(jeevom_session.getName());

				requestSendMessage.setIsBasicDetailsShared(true);
				requestSendMessage.setIsMedicalInfoShared(medical_info_checkbox
						.isChecked());
				requestSendMessage.setIsHealthTrackShared(health_info_checkbox
						.isChecked());
			} else {
				if (forMemberId > 0) {
					requestSendMessage.setForMemberId(String
							.valueOf(forMemberId));
					requestSendMessage
							.setIsBasicDetailsShared(basic_info_checkbox
									.isChecked());
					requestSendMessage
							.setIsMedicalInfoShared(medical_info_checkbox
									.isChecked());
					requestSendMessage
							.setIsHealthTrackShared(health_info_checkbox
									.isChecked());
				} else {
					requestSendMessage.setIsBasicDetailsShared(true);
				}
				requestSendMessage.setForAge(age_consumer.getText().toString());
				requestSendMessage.setForGender(gender);
				requestSendMessage.setForName(name_consumer.getText()
						.toString().trim());
			}
		}

		goToStepThree();
	}

	private void goToStepThree() {
		Intent stepTwoIntent = new Intent(this, AppointmentStepThree.class);
		Bundle bundle = new Bundle();
		bundle.putString("name_appointment_type", appointment_type);
		bundle.putString("service_request_type",serviceRequisition);
		bundle.putString("img_url", image_url);
		bundle.putString("name", name_value);
		bundle.putSerializable("requestObject", requestSendMessage);
		bundle.putString("date", date_value);
		bundle.putString("time", time_value);
		if (documentList.size() > 0) {
			bundle.putSerializable("documents", (Serializable) documentList);
		}
		stepTwoIntent.putExtras(bundle);
		startActivity(stepTwoIntent);
		setResult(1);
		finish();

	}

	private void callLogIn() {
		// Intent i = new Intent(AppointmentStepTwo.this, LogIn.class);
		// startActivityForResult(i, 1);

		Intent signInInContext = new Intent(AppointmentStepTwo.this,
				SignUpLoginActivity.class);
		signInInContext.putExtra("isInContext", true);
		signInInContext.putExtra("isSignupShown", true);
		startActivityForResult(signInInContext, 1);
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);

		if (arg0 == 1 && arg1 == RESULT_OK) {
			getConsumerDetails();
		}

	}

	private void showDatePickerDialog() {
		final DatePickerDialog datePickerDialog;
		calendar = Calendar.getInstance();
		int i = calendar.get(Calendar.YEAR);
		int year = 0, month, day;
		if (date == 0l) {
			datePickerDialog = DatePickerDialog.newInstance(
					AppointmentStepTwo.this, calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), false);
		} else {
			calendar.setTimeInMillis(date);
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DAY_OF_MONTH);
			datePickerDialog = DatePickerDialog.newInstance(
					AppointmentStepTwo.this, year, month, day, false);
		}

		datePickerDialog.setYearRange(1902, i);
		datePickerDialog.setCloseOnSingleTapDay(false);
		datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {

		int id = parent.getId();
		if (id == R.id.consult_for_value_spinner) {

			if (memberSpinnerList.get(position).equals("Self")) {

				shared_info.setVisibility(View.VISIBLE);

				gender_spinner.getSelectedView();
				gender_spinner.setEnabled(false);
				name_consumer.setText(CommonCode
						.makeCallBackEmailName(jeevom_session.getName()));

				if (Integer.valueOf(jeevom_session.getAge()) > 0) {
					age_consumer.setText(jeevom_session.getAge());
					age_consumer.setEnabled(true);
					age_consumer.setFocusable(false);
					age_consumer.setFocusableInTouchMode(false);
				} else {
					age_consumer.setText("");
					age_image.setVisibility(View.VISIBLE);
					age_consumer.setEnabled(true);
					age_consumer.setFocusable(false);
					age_consumer.setFocusableInTouchMode(false);
				}
				String name=jeevom_session.getName();
				if(CommonCode
						.isNullOrEmpty(jeevom_session.getName()))
				{
					name_consumer.setEnabled(true);
					name_consumer.setFocusable(true);
					name_consumer.setFocusableInTouchMode(true);
				}else {
					name_consumer.setEnabled(false);
					name_consumer.setFocusable(false);
					name_consumer.setFocusableInTouchMode(false);
				}
				consult_for_type = "Self";
				bookedForSelf = true;
				forMemberId = jeevom_session.getMemberId();

				setGender(jeevom_session.getGender());

			} else if (memberSpinnerList.get(position).equals("Other")) {
				shared_info.setVisibility(View.GONE);
				name_consumer.setEnabled(true);
				name_consumer.setFocusable(true);
				name_consumer.setFocusableInTouchMode(true);

				gender_spinner.setEnabled(true);

				name_consumer.setText("");
				age_consumer.setText("");
				age_consumer.setEnabled(true);
				age_consumer.setFocusable(true);
				age_consumer.setFocusableInTouchMode(true);
				age_image.setVisibility(View.GONE);
				bookedForSelf = false;
				consult_for_type = "Other";
				forMemberId = 0;
			} else {
				shared_info.setVisibility(View.VISIBLE);
				consult_for_type = "other";
				for (MemberAssociation obj : memberAssociation) {
					if (!CommonCode.isNullOrEmpty(obj.getFirstName())) {
						if (memberSpinnerList.get(position).contains(
								obj.getFirstName())) {

							// set name value and make it not editable
							name_consumer.setText(memberSpinnerList
									.get(position));
							name_consumer.setEnabled(false);
							name_consumer.setFocusable(false);
							name_consumer.setFocusableInTouchMode(false);

							// set age value and make it not editable
							String valueOf = String.valueOf(CommonCode
									.getAge(obj.getDob()));
							age_consumer.setText(valueOf);
							age_consumer.setEnabled(false);
							age_consumer.setFocusable(false);
							age_consumer.setFocusableInTouchMode(false);

							if (obj.getGender() != null) {
								setGender(obj.getGender());
							}
							gender_spinner.getSelectedView();
							gender_spinner.setEnabled(false);

							bookedForSelf = false;
							forMemberId = obj.getAssociateOfId();
							break;

						}

					}
				}

			}
		} else if (id == R.id.gender_spinner) {
			gender = genders[position];
		} else if (id == R.id.guest_gender_spinner) {
			gender = genders[position];
		} else if (id == R.id.title_spinner) {
			if (titleSpinnerItems.size() > 0) {

				titleType = titleSpinnerItems.get(position);

			}
		} else if (id == R.id.new_user_gender_spinner) {
			gender = genders[position];
		}

	}

	// set Gender
	private void setGender(String gender) {
		if (CommonCode.isNullOrEmpty(gender) || gender.equals("-")) {
			gender_spinner.setEnabled(true);
		} else {
			gender_spinner.setEnabled(false);
		}

		// set gender
		if ("male".equalsIgnoreCase(gender)) {
			gender_spinner.setSelection(0);

		} else if ("female".equalsIgnoreCase(gender)) {
			gender_spinner.setSelection(1);
		} else {
			gender_spinner.setSelection(2);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendConsumerDetails(double bmi, double bmr,
			FamilyMembersResponse familyDetails, List<BloodGroup> bloodGroups,
			ConsumerDetailsResponse consumer) {

		newFragment.dismissAllowingStateLoss();
		memberAssociation = familyDetails.getData().getMemberAssociation();
		addLoggedinLayout();
	}

	private void addLoggedinLayout() {

		typeOfUser = "member";
		LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflator.inflate(R.layout.user_logged_in, null);
		if (action_container.getChildCount() > 0) {
			action_container.removeAllViews();
		}
		action_container.addView(view);
		age_image = (ImageView) view.findViewById(R.id.age_image);
		consult_for_value_spinner = (Spinner) view
				.findViewById(R.id.consult_for_value_spinner);
		consult_for_value_spinner.setOnItemSelectedListener(this);
		name_consumer = (EditText) view.findViewById(R.id.name_value);
		age_consumer = (EditText) view.findViewById(R.id.age_value);
		gender_spinner = (Spinner) view.findViewById(R.id.gender_spinner);
		gender_spinner.setOnItemSelectedListener(this);
		basic_info_checkbox = (CheckBox) view
				.findViewById(R.id.basic_info_checkbox);
		medical_info_checkbox = (CheckBox) view
				.findViewById(R.id.medical_info_checkbox);
		health_info_checkbox = (CheckBox) view
				.findViewById(R.id.health_info_checkbox);
		shared_info = (RelativeLayout) view.findViewById(R.id.shared_info);
		basic_info_checkbox.setChecked(true);
		age_image.setOnClickListener(this);

		if (Integer.valueOf(jeevom_session.getAge()) <= 0) {
			age_image.setVisibility(View.VISIBLE);
		} else {
			age_image.setVisibility(View.GONE);
		}

		fillLoggedInUserDetails();

	}

	private void fillLoggedInUserDetails() {
		initializeGenderSpinner();
		fillSpinnerForMembers();
	}

	private void fillSpinnerForMembers() {
		if (memberSpinnerList.size() > 0) {
			memberSpinnerList.clear();
		}
		memberSpinnerList.add("Self");

		if (memberAssociation != null) {
			if (memberAssociation.size() > 0) {
				for (MemberAssociation obj : memberAssociation) {
					if (!CommonCode.isNullOrEmpty(obj.getFirstName())
							&& !CommonCode.isNullOrEmpty(obj.getLastName())) {
						memberSpinnerList.add(CommonCode
								.makeCallBackEmailName(obj.getFirstName() + " "
										+ obj.getLastName()));
					} else if (!CommonCode.isNullOrEmpty(obj.getFirstName())) {
						memberSpinnerList.add(obj.getFirstName());
					} else if (!CommonCode.isNullOrEmpty(obj.getLastName())) {
						memberSpinnerList.add(obj.getLastName());
					}
				}
			}
		}
		memberSpinnerList.add("Other");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				AppointmentStepTwo.this, R.layout.spinner_item,
				memberSpinnerList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		consult_for_value_spinner.setAdapter(adapter);
	}

	private void initializeGenderSpinner() {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				AppointmentStepTwo.this, R.layout.spinner_item,
				Arrays.asList(genders));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gender_spinner.setAdapter(adapter);

	}

	@Override
	public void onDateSet(DatePickerDialog datePickerDialog, int year,
			int month, int day) {

		current_year = year;
		currentDay = day;
		currentMonth = month + 1;
		newDobValue = currentMonth + "/" + currentDay + "/" + current_year;
		if (isPotentialDateOfBirth)
			new_dob_value.setText(CommonCode.monthYear(currentDay,
					currentMonth - 1, current_year));
		if (isLoggedInDateOfBirth)
			age_consumer.setText(CommonCode.monthYear(currentDay,
					currentMonth - 1, current_year));
		dateFormatted = currentMonth + "/" + currentDay + "/" + current_year;
		date = CommonCode.convertToMilliseconds(year, month, day);

	}

	private void hideKeyboard() {
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	private void updateConsumerProfile() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ConsumerUpdateRequestModel finalUpdateObject = new ConsumerUpdateRequestModel();
		finalUpdateObject.setId(jeevom_session.getConsumerIds().get(
				JeevomSession.JEEVOM_CONSUMER_ID));
		finalUpdateObject.setType("Consumer");

		ConsumerMemberDetails memberDetails = new ConsumerMemberDetails();

		memberDetails.setDateOfBirth(dateFormatted);
		memberDetails.setGender(gender);

		Consumer consumer = new Consumer();

		consumer.setMemberDetails(gson.toJson(memberDetails).toString());

		finalUpdateObject.setData(consumer);

		RestAdapter consumerUpdateAdapter = new RestAdapter.Builder()
				.setClient(new MyUrlConnectionClient())
				.setLog(new AndroidLog("update")).setLogLevel(LogLevel.FULL)
				.setEndpoint(JeevOMUtil.baseUrl).build();
		Member getConsumerService = consumerUpdateAdapter
				.create(Member.class);
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);

		getConsumerService.updateConsumerProfile(
				"Basic " + jeevom_session.getAuthToken(), finalUpdateObject,
				new Callback<UpdateConsumerResponse>() {

					@Override
					public void failure(RetrofitError arg0) {

						newFragment.dismissAllowingStateLoss();

						if (arg0.isNetworkError()) {
							if (!(Connectivity
									.checkConnectivity(AppointmentStepTwo.this))) {
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
							MembershipAuthenticateResponse MembershipAuthenticateErrors = gson
									.fromJson(
											json,
											MembershipAuthenticateResponse.class);
							String code = MembershipAuthenticateErrors
									.getStatus().getCode();
							String message = MembershipAuthenticateErrors
									.getStatus().getMessage();
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
					public void success(UpdateConsumerResponse arg0,
							Response arg1) {
						newFragment.dismissAllowingStateLoss();
						if (arg0.getStatus().getCode().equals("Success")) {

							// save user information
							jeevom_session.setGender(gender);
							// save age
							String dateOfBirth = dateFormatted;

							// get Age of User
							long age;
							if (!CommonCode.isNullOrEmpty(dateOfBirth)) {
								age = CommonCode.getUserAge(
										CommonCode.getCurrentTimeStamp(),
										dateFormatted);
							} else {
								age = 0;
							}
							jeevom_session.setAge(String.valueOf(age));
							makeCall();
						}
					}

				});
	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}
}