package com.schoolteacher.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.InsuranceDetailsResult;
import com.schoolteacher.pojos.InsuranceRequest;
import com.schoolteacher.pojos.MemberInsuranceDetail;
import com.schoolteacher.pojos.MemberInsuranceDetails;
import com.schoolteacher.service.UpdateInsuranceDetails;

import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

public class InsuranceDetails extends ActionBarActivity implements
		OnItemSelectedListener, OnClickListener, OnDateSetListener {
	UserCurrentLocationManager locationManager;


	EditText tpa_email, tpa_phone_value, org_name_value, pri_ins_email,
			pri_ins_phone_value, pi_last_name_value, pi_first_name_value,
			provider_name, provider_url_name, policy_name, policy_no,
			coverage_amount_value, claimed_amount_value;
	TextView valid_from_value, valid_thru_value;
	ImageView valid_thru_image, valid_from_image;
	Spinner coverage_amount_spinner, claimed_amount_spinner,
			name_title_Spinner, pri_ins_phone_spinner, tpa_phone_spinner;
	String[] currencyType = { "INR" };
	String[] countryCode = { "+91" };
	List<String> titleSpinnerItems = new ArrayList<String>();
	String dateFormattedFrom, dateFormattedThrough, nameTitleValue,
			claimed_amount_currency_value, coverage_amount_currency_value,
			pri_ins_phone_code, tpa_phone_code;
	Button save;
	Activity context;
	public static final String DATEPICKER_TAG_VALID_FROM = "validFrom";
	public static final String DATEPICKER_TAG_VALID_THRU = "validThru";
	long dateValidFrom = 0l;
	long dateValidThru = 0l;
	private JeevomSession session;
	DialogFragment newFragment;
	GlobalAlert globalAlert;
	MemberInsuranceDetail insuranceDet;
	int existing_id;
	Toolbar toolbar;
	String authToken = null;

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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insurance_layout);
		toolbar = (Toolbar) findViewById(R.id.toolbar_insu);
		setSupportActionBar(toolbar);
		locationManager=new UserCurrentLocationManager(getApplicationContext());
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		getSupportActionBar().setTitle("Add Insurance Details");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		context = this;
		session = new JeevomSession(this);
		if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
			authToken = "Basic " + session.getAuthToken();
		}

		globalAlert = new GlobalAlert(context);

		insuranceDet = (MemberInsuranceDetail) getIntent()
				.getSerializableExtra("member_insurance_details");

		// get button reference
		save = (Button) findViewById(R.id.save);
		save.setOnClickListener(this);

		// get Edit Text References
		provider_name = (EditText) findViewById(R.id.provider_name);

		provider_url_name = (EditText) findViewById(R.id.provider_url_name);
		policy_name = (EditText) findViewById(R.id.policy_name);
		policy_no = (EditText) findViewById(R.id.policy_no);
		coverage_amount_value = (EditText) findViewById(R.id.coverage_amount_value);
		claimed_amount_value = (EditText) findViewById(R.id.claimed_amount_value);
		pi_first_name_value = (EditText) findViewById(R.id.pi_first_name_value);
		pi_last_name_value = (EditText) findViewById(R.id.pi_last_name_value);
		pri_ins_phone_value = (EditText) findViewById(R.id.pri_ins_phone_value);
		pri_ins_email = (EditText) findViewById(R.id.pri_ins_email);
		org_name_value = (EditText) findViewById(R.id.org_name_value);
		tpa_phone_value = (EditText) findViewById(R.id.tpa_phone_value);
		tpa_email = (EditText) findViewById(R.id.tpa_email);

		// get Text View References
		valid_from_value = (TextView) findViewById(R.id.valid_from_value);
		valid_thru_value = (TextView) findViewById(R.id.valid_thru_value);

		// get imageview references
		valid_thru_image = (ImageView) findViewById(R.id.valid_thru_image);
		valid_from_image = (ImageView) findViewById(R.id.valid_from_image);
		valid_thru_image.setOnClickListener(this);
		valid_from_image.setOnClickListener(this);

		// get spinner references
		coverage_amount_spinner = (Spinner) findViewById(R.id.coverage_amount_spinner);
		claimed_amount_spinner = (Spinner) findViewById(R.id.claimed_amount_spinner);
		pri_ins_phone_spinner = (Spinner) findViewById(R.id.pri_ins_phone_spinner);
		tpa_phone_spinner = (Spinner) findViewById(R.id.tpa_phone_spinner);

		name_title_Spinner = (Spinner) findViewById(R.id.name_title_Spinner);
		name_title_Spinner.setOnItemSelectedListener(this);

		// fill currency type spineers
		fillCurrencySpinner();

		// fill country code spinners
		fillCountryCodeSpinner();

		// fill name title spinner
		setTitleInSpinner();

		// fill details if opening in edit mode
		if (insuranceDet != null) {
			existing_id = insuranceDet.getId();
			// set providers name
			if (!CommonCode.isNullOrEmpty(insuranceDet.getProviderName())) {
				provider_name.setText(insuranceDet.getProviderName());
			}

			// set providers url
			if (!CommonCode.isNullOrEmpty(insuranceDet.getProviderUrl())) {
				provider_url_name.setText(insuranceDet.getProviderUrl());
			}

			// set policy name
			if (!CommonCode.isNullOrEmpty(insuranceDet.getPolicyName())) {
				policy_name.setText(insuranceDet.getPolicyName());
			}

			// set policy no
			if (!CommonCode.isNullOrEmpty(insuranceDet.getPolicyNumber())) {
				policy_no.setText(insuranceDet.getPolicyNumber());
			}

			// set valid from date
			if (!CommonCode.isNullOrEmpty(insuranceDet.getValidFrom())) {

				try {

					int[] formatDT = CommonCode.formatDT(insuranceDet
							.getValidFrom());
					valid_from_value.setText(CommonCode.monthYear(formatDT[0],
							formatDT[1], formatDT[2]));
					dateFormattedFrom = (formatDT[1] + 1) + "-" + formatDT[0]
							+ "-" + formatDT[2];
					dateValidFrom = CommonCode.convertToMilliseconds(
							formatDT[2], formatDT[1], formatDT[0]);
				} catch (ParseException e) {
				}

			}
			// set valid through date
			if (!CommonCode.isNullOrEmpty(insuranceDet.getValidTill())) {

				try {

					int[] formatDT = CommonCode.formatDT(insuranceDet
							.getValidTill());
					valid_thru_value.setText(CommonCode.monthYear(formatDT[0],
							formatDT[1], formatDT[2]));
					dateFormattedThrough = (formatDT[1] + 1) + "-"
							+ formatDT[0] + "-" + formatDT[2];
					dateValidThru = CommonCode.convertToMilliseconds(
							formatDT[2], formatDT[1], formatDT[0]);
				} catch (ParseException e) {
				}

			}

			// set coverage amount
			if (!CommonCode.isNullOrEmpty(insuranceDet.getCoverageAmount())) {
				coverage_amount_value.setText(insuranceDet.getCoverageAmount());
			}

			// set claimed Amount
			if (!CommonCode.isNullOrEmpty(insuranceDet.getClaimedAmount())) {
				claimed_amount_value.setText(insuranceDet.getClaimedAmount());
			}

			// set primary insurance/ first name
			if (!CommonCode
					.isNullOrEmpty(insuranceDet.getPrimaryInsFirstName())) {
				pi_first_name_value.setText(insuranceDet
						.getPrimaryInsFirstName());
			}
			// set primary insurance/ last name
			if (!CommonCode.isNullOrEmpty(insuranceDet.getPrimaryInsLastName())) {
				pi_last_name_value
						.setText(insuranceDet.getPrimaryInsLastName());
			}
			// set primary insurance/ email
			if (!CommonCode.isNullOrEmpty(insuranceDet.getPrimaryInsEmail())) {
				pri_ins_email.setText(insuranceDet.getPrimaryInsEmail());
			}
			// set primary insurance/ cell no
			if (!CommonCode.isNullOrEmpty(insuranceDet
					.getPrimaryInsCellNumber())) {
				pri_ins_phone_value.setText(insuranceDet
						.getPrimaryInsCellNumber());
			}

			// set tpa organisation name
			if (!CommonCode
					.isNullOrEmpty(insuranceDet.getTPAOrganisationName())) {
				org_name_value.setText(insuranceDet.getTPAOrganisationName());
			}

			// set phone
			if (!CommonCode.isNullOrEmpty(insuranceDet.getTPACellNumber())) {
				tpa_phone_value.setText(insuranceDet.getTPACellNumber());
			}
			// set tpa email
			if (!CommonCode.isNullOrEmpty(insuranceDet.getTPAEmail())) {
				tpa_email.setText(insuranceDet.getTPAEmail());
			}
		}

		tpa_email.clearFocus();
		tpa_phone_value.clearFocus();
		org_name_value.clearFocus();
		pri_ins_email.clearFocus();
		pri_ins_phone_value.clearFocus();
		pi_last_name_value.clearFocus();
		pi_first_name_value.clearFocus();
		provider_name.clearFocus();
		provider_url_name.clearFocus();
		policy_name.clearFocus();
		policy_no.clearFocus();
		coverage_amount_value.clearFocus();
		claimed_amount_value.clearFocus();
	}

	private void setTitleInSpinner() {
		titleSpinnerItems.add("Mr.");
		titleSpinnerItems.add("Mrs.");
		titleSpinnerItems.add("Dr.");
		titleSpinnerItems.add("Ms.");

		if (titleSpinnerItems.size() > 0) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					InsuranceDetails.this, R.layout.spinner_item,
					titleSpinnerItems);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			name_title_Spinner.setAdapter(adapter);

		}
	}

	private void fillCurrencySpinner() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				InsuranceDetails.this, R.layout.spinner_item,
				Arrays.asList(currencyType));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		claimed_amount_spinner.setAdapter(adapter);
		coverage_amount_spinner.setAdapter(adapter);

	}

	private void fillCountryCodeSpinner() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				InsuranceDetails.this, R.layout.spinner_item,
				Arrays.asList(countryCode));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tpa_phone_spinner.setAdapter(adapter);
		pri_ins_phone_spinner.setAdapter(adapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
		int id = parent.getId();
		if (id == R.id.name_title_Spinner) {
			if (titleSpinnerItems.size() > 0) {

				nameTitleValue = (titleSpinnerItems.get(position));

			}
		}
		if (id == R.id.claimed_amount_spinner) {
			if (titleSpinnerItems.size() > 0) {

				claimed_amount_currency_value = (Arrays.asList(currencyType)
						.get(position));

			}
		}

		if (id == R.id.coverage_amount_spinner) {
			if (titleSpinnerItems.size() > 0) {

				coverage_amount_currency_value = (Arrays.asList(currencyType)
						.get(position));

			}
		}
		if (id == R.id.pri_ins_phone_spinner) {
			if (titleSpinnerItems.size() > 0) {

				pri_ins_phone_code = (Arrays.asList(countryCode).get(position));

			}
		}

		if (id == R.id.tpa_phone_spinner) {
			if (titleSpinnerItems.size() > 0) {

				tpa_phone_code = (Arrays.asList(countryCode).get(position));

			}
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	private boolean checkClaimedCoverageAmount() {
		if (!CommonCode.isNullOrEmpty(coverage_amount_value.getText()
				.toString())
				&& !CommonCode.isNullOrEmpty(claimed_amount_value.getText()
						.toString().trim())) {
			return Long.valueOf(coverage_amount_value.getText().toString().trim()) < Long
					.valueOf(claimed_amount_value.getText().toString().trim());
		} else
			return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save:
			if (CommonCode.isNullOrEmpty(provider_name.getText().toString()
					.trim())) {
				Crouton.makeText(context, "Please enter provider's name",
						Style.ALERT).show();
				provider_name.requestFocus();
			} else if (!CommonCode.isNullOrEmpty(provider_url_name.getText()
					.toString().trim())
					&& !CommonCode.validateWebUrl(provider_url_name.getText()
							.toString().trim())) {
				Crouton.makeText(context, "Please provide valid Url",
						Style.ALERT).show();
				provider_url_name.requestFocus();
			} else if (!CommonCode.isNullOrEmpty(pi_first_name_value.getText()
					.toString().trim())
					&& !validateString(pi_first_name_value.getText().toString()
							.trim())) {

				Crouton.makeText(context,
						"Only Alphabets are allowed in first name", Style.ALERT)
						.show();
				pi_first_name_value.requestFocus();
			} else if (!CommonCode.isNullOrEmpty(pi_last_name_value.getText()
					.toString().trim())
					&& !validateString(pi_last_name_value.getText().toString()
							.trim())) {
				Crouton.makeText(context,
						"Only Alphabets are allowed in last name", Style.ALERT)
						.show();
				pi_last_name_value.requestFocus();
			} else if (!CommonCode.isNullOrEmpty(pri_ins_phone_value.getText()
					.toString())
					&& !(CommonCode.validateContactNo(pri_ins_phone_value
							.getText().toString().trim()))) {
				Crouton.makeText(context,
						"Please enter valid 10 digit contact number",
						Style.ALERT).show();
				pri_ins_phone_value.requestFocus();
			} else if (!CommonCode.isNullOrEmpty(tpa_phone_value.getText()
					.toString())
					&& !(CommonCode.validateContactNo(tpa_phone_value.getText()
							.toString().trim()))) {
				Crouton.makeText(context,
						"Please enter valid 10 digit contact number",
						Style.ALERT).show();
				tpa_phone_value.requestFocus();
			} else if (!CommonCode
					.isNullOrEmpty(tpa_email.getText().toString())
					&& !CommonCode
							.validateEmail(tpa_email.getText().toString())) {
				Crouton.makeText(context, "Please enter a valid email id",
						Style.ALERT).show();
				tpa_email.requestFocus();
			} else if (!CommonCode.isNullOrEmpty(pri_ins_email.getText()
					.toString())
					&& !CommonCode.validateEmail(pri_ins_email.getText()
							.toString())) {
				Crouton.makeText(context, "Please enter a valid email id",
						Style.ALERT).show();
				pri_ins_email.requestFocus();
			} else if (!CommonCode.isNullOrEmpty(coverage_amount_value
					.getText().toString())
					&& Long.valueOf(coverage_amount_value.getText().toString()
					.trim()) <= 0) {
				Crouton.makeText(
						context,
						"Minimum Value for coverage amount must be greater than 0",
						Style.ALERT).show();
			} else if (!CommonCode.isNullOrEmpty(claimed_amount_value.getText()
					.toString().trim())
					&& Long.valueOf(claimed_amount_value.getText().toString()
					.trim()) <= 0) {
				Crouton.makeText(
						context,
						"Minimum Value for claimed amount must be greater than 0",
						Style.ALERT).show();
			} else if (checkClaimedCoverageAmount()) {
				Crouton.makeText(context,
						"Claimed Amount Should be less than Coverage Amount.",
						Style.ALERT).show();
			} else if (!CommonCode.isNullOrEmpty(dateFormattedFrom)
					&& CommonCode.isNullOrEmpty(dateFormattedThrough)) {
				Crouton.makeText(context,
						"Please enter value for Valid Till date", Style.ALERT)
						.show();
			} else if (CommonCode.isNullOrEmpty(dateFormattedFrom)
					&& !CommonCode.isNullOrEmpty(dateFormattedThrough)) {
				Crouton.makeText(context,
						"Please enter value for Valid From date", Style.ALERT)
						.show();
			} else if (!CommonCode.isNullOrEmpty(dateFormattedFrom)
					&& !CommonCode.isNullOrEmpty(dateFormattedThrough)) {
				if (validateDate() >= 1) {
					Crouton.makeText(
							context,
							"Valid From date should be less than Valid Till date",
							Style.ALERT).show();
				} else {
					saveDetails();
				}
			} else {
				saveDetails();
			}

			break;

		case R.id.valid_from_image:
			showDatePickerDialog(DATEPICKER_TAG_VALID_FROM);
			break;
		case R.id.valid_thru_image:
			showDatePickerDialog(DATEPICKER_TAG_VALID_THRU);
			break;
		}

	}

	private boolean validateString(String value) {

		return value.matches("[a-zA-Z]+");
	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

	private void showDatePickerDialog(String value) {
		DatePickerDialog datePickerDialog = null;
		final Calendar calendar = Calendar.getInstance();

		if (value.equals(DATEPICKER_TAG_VALID_FROM)) {
			if (dateValidFrom == 0l) {
				datePickerDialog = DatePickerDialog.newInstance(this,
						calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), false);
			} else {
				calendar.setTimeInMillis(dateValidFrom);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				datePickerDialog = DatePickerDialog.newInstance(this, year,
						month, day, false);
			}
		} else if (value.equals(DATEPICKER_TAG_VALID_THRU)) {
			if (dateValidThru == 0l) {
				datePickerDialog = DatePickerDialog.newInstance(this,
						calendar.get(Calendar.YEAR) + 3,
						calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH), false);
			} else {
				calendar.setTimeInMillis(dateValidThru);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				datePickerDialog = DatePickerDialog.newInstance(this, year,
						month, day, false);
			}
		}
		datePickerDialog.setYearRange(1902, 2099);
		datePickerDialog.setCloseOnSingleTapDay(false);
		if (value.equals(DATEPICKER_TAG_VALID_FROM)) {
			datePickerDialog.show(getSupportFragmentManager(),
					DATEPICKER_TAG_VALID_FROM);
		} else if (value.equals(DATEPICKER_TAG_VALID_THRU)) {
			datePickerDialog.show(getSupportFragmentManager(),
					DATEPICKER_TAG_VALID_THRU);
		}

	}

	@Override
	public void onDateSet(DatePickerDialog datePickerDialog, int year,
			int month, int day) {

		if (datePickerDialog.getTag().equals(DATEPICKER_TAG_VALID_FROM)) {
			valid_from_value.setText(CommonCode.monthYear(day, month, year));
			// valid_from_value.setText(day + "/" + (month + 1) + "/" + year);
			dateFormattedFrom = (month + 1) + "-" + day + "-" + year;
			dateValidFrom = CommonCode.convertToMilliseconds(year, month, day);
		}
		if (datePickerDialog.getTag().equals(DATEPICKER_TAG_VALID_THRU)) {
			valid_thru_value.setText(CommonCode.monthYear(day, month, year));
			// valid_thru_value.setText(day + "/" + (month + 1) + "/" + year);
			dateFormattedThrough = (month + 1) + "-" + day + "-" + year;
			dateValidThru = CommonCode.convertToMilliseconds(year, month, day);
		}

	}

	private void saveDetails() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<MemberInsuranceDetails> memberInsuranceDetailsList = new LinkedList<MemberInsuranceDetails>();
		MemberInsuranceDetails memberInsuranceDetails = new MemberInsuranceDetails();
		if (existing_id > 0) {
			memberInsuranceDetails.setId(existing_id);
		} else {
			memberInsuranceDetails.setId(0);
		}
		memberInsuranceDetails
				.setMemberId(String.valueOf(session.getMemberId()));
		memberInsuranceDetails.setProviderName(provider_name.getText()
				.toString().trim());
		memberInsuranceDetails.setProviderURL(provider_url_name.getText()
				.toString().trim());
		memberInsuranceDetails.setPolicyName(policy_name.getText().toString()
				.trim());
		memberInsuranceDetails.setPolicyNumber(policy_no.getText().toString()
				.trim());
		memberInsuranceDetails.setValidFrom(dateFormattedFrom);
		memberInsuranceDetails.setValidTill(dateFormattedThrough);
		memberInsuranceDetails.setCoverageAmount(coverage_amount_value
				.getText().toString().trim());
		memberInsuranceDetails.setClaimedAmount(claimed_amount_value.getText()
				.toString().trim());
		memberInsuranceDetails.setPrimaryInsTitle(null);
		memberInsuranceDetails.setPrimaryInsFirstName(pi_first_name_value
				.getText().toString().trim());
		memberInsuranceDetails.setPrimaryInsLastName(pi_last_name_value
				.getText().toString().trim());
		memberInsuranceDetails.setPrimaryInsCellNumber(pri_ins_phone_value
				.getText().toString().trim());
		memberInsuranceDetails.setPrimaryInsEmail(pri_ins_email.getText()
				.toString().trim());

		memberInsuranceDetails.setTPAOrganisationName(org_name_value.getText()
				.toString().trim());
		memberInsuranceDetails.setTPACellNumber(tpa_phone_value.getText()
				.toString().trim());
		memberInsuranceDetails.setTPAEmail(tpa_email.getText().toString()
				.trim());
		memberInsuranceDetails.setEditEnabled(true);
		memberInsuranceDetailsList.add(memberInsuranceDetails);

		com.schoolteacher.pojos.InsuranceDetail insuraceDetails = new com.schoolteacher.pojos.InsuranceDetail();

		insuraceDetails.setMemberInsuranceDetails(gson.toJson(
				memberInsuranceDetailsList).toString());

		InsuranceRequest requestBody = new InsuranceRequest();
		requestBody.setId(session.getConsumerIds().get(
				JeevomSession.JEEVOM_CONSUMER_ID));
		requestBody.setType("Consumer");
		requestBody.setData(insuraceDetails);

		RestAdapter consumerInsuranceAdapter = new RestAdapter.Builder()
				.setClient(new MyUrlConnectionClient())
				.setLog(new AndroidLog("insurance")).setLogLevel(LogLevel.FULL)
				.setEndpoint(JeevOMUtil.baseUrl).build();
		UpdateInsuranceDetails updateInsurance = consumerInsuranceAdapter
				.create(UpdateInsuranceDetails.class);
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
		updateInsurance.updateInsurance(
				authToken, requestBody,
				new Callback<InsuranceDetailsResult>() {

					@Override
					public void success(InsuranceDetailsResult arg0,
							Response arg1) {
						newFragment.dismissAllowingStateLoss();
						if (arg0.getStatus().getCode().equals("Success")) {
							setResult(Activity.RESULT_OK);
							finish();
						}

					}

					@Override
					public void failure(RetrofitError arg0) {

						newFragment.dismissAllowingStateLoss();

						if (arg0.isNetworkError()) {
							if (!(Connectivity.checkConnectivity(context))) {
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
							InsuranceDetailsResult MembershipAuthenticateErrors = gson
									.fromJson(json,
											InsuranceDetailsResult.class);
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
				});

	}

	private int validateDate() {

		// if (!CommonCode.isNullOrEmpty(dateFormattedFrom) &&
		// !CommonCode.isNullOrEmpty(dateFormattedThrough)) {
		int i = 0;
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			Date date1 = sdf.parse(dateFormattedFrom);
			Date date2 = sdf.parse(dateFormattedThrough);

			System.out.println(sdf.format(date1));
			System.out.println(sdf.format(date2));

			if (date1.after(date2)) {
				i = 1;
			}

			if (date1.before(date2)) {
				i = -1;
			}

			if (date1.equals(date2)) {
				i = 0;
			}

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return i;
		// } else
		// return -2;
	}
}
