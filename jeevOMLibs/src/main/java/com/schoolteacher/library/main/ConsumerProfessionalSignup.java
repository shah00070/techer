package com.schoolteacher.library.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.library.apiInterfaces.ActivityListner;
import com.schoolteacher.library.apiInterfaces.GetMemberInformation;
import com.schoolteacher.library.apiInterfaces.MemberShipSignup;
import com.schoolteacher.library.apiInterfaces.SendSetPasswordRequest;
import com.schoolteacher.library.events.EventData;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.library.pojo.MemberInformation;
import com.schoolteacher.library.pojo.MembershipSignupResponse;
import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.PhoneEmailExistsWarningDialog;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.interfaces.MissedCallCodeListner;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.pojo.ConsumerProfiles;
import com.schoolteacher.mylibrary.pojo.ConsumerProfilesRespone;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateRequest;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.service.SimplePostAsyncTask;
import com.schoolteacher.mylibrary.service.interfaces.ConsumerIds;
import com.schoolteacher.mylibrary.service.interfaces.MemberShipAuthenticate;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class ConsumerProfessionalSignup extends ActionBarActivity implements
        OnClickListener, MissedCallCodeListner, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener, ActivityListner {
    public static final String DATEPICKER_TAG = "datepicker";
    int current_year, currentMonth, currentDay;
    private GlobalAlert globalAlert;
    ProgressDialogFragment progressBarFragment;
    LinearLayout healthcare_prof_checkbox_layout,
            facility_owner_checkbox_layout;

    EditText user_email_phone_value, password_value;
    ProgressBar email_phone_progress;
    ImageView pwd_image_hint;
    //CheckBox healthcare_prof_checkbox, facility_owner_checkbox;
    TextView terms_and_condition, btn_user_sign_up, login_btn;
    String consumer_type;
    private boolean isEmailEntered = false;
    private JeevomSession jeevomSession;

    private boolean isUserVerified = false;
    private Toolbar toolbar;
    private EventBus bus;
    private EventData event;
    TextView new_dob_value;
    LinearLayout back_btn_layout;
    private boolean isPasswordShown = false;
    long date = 0l;
    String newDobValue, dateFormatted, gender;
    String[] genders = {"Male", "Female", "Other"};

    //Spinner new_user_gender_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        bus = EventBus.getDefault();
        // Register as a subscriber
        bus.register(this);
        event = new EventData();
        // setActionBar
        setActionBar();
        // trace the intent
        Intent intent = getIntent();
        consumer_type = intent.getStringExtra("consumer_type");

        globalAlert = new GlobalAlert(ConsumerProfessionalSignup.this);
        jeevomSession = new JeevomSession(this);

        getViewElementsReferences();
    }

    private void setActionBar() {
        // set Action bar
        //   toolbar = (Toolbar) findViewById(R.id.signup_toolbar);
        //    setSupportActionBar(toolbar);
        // getSupportActionBar().setTitle("Become a Jeevom Member");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Applying Exit Animation;
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

    // get view element references
    private void getViewElementsReferences() {

//        new_dob_value = (TextView) findViewById(R.id.new_dob_value);
//        new_dob_image = (ImageView) findViewById(R.id.new_dob_image);
//        new_dob_image.setOnClickListener(this);
        //  new_user_gender_spinner = (Spinner) findViewById(R.id.new_user_gender_spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                ConsumerProfessionalSignup.this, R.layout.spinner_item,
//                Arrays.asList(genders));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        new_user_gender_spinner.setAdapter(adapter);
//        new_user_gender_spinner.setOnItemSelectedListener(this);
//        user_first_name_value = (EditText) findViewById(R.id.user_first_name_value);
//        user_last_name_value = (EditText) findViewById(R.id.user_last_name_value);
        back_btn_layout = (LinearLayout) findViewById(R.id.back_btn_layout);
        user_email_phone_value = (EditText) findViewById(R.id.user_email_phone_value);
        password_value = (EditText) findViewById(R.id.password_value);

        //   email_phone_progress = (ProgressBar) findViewById(R.id.email_phone_progress);
        pwd_image_hint = (ImageView) findViewById(R.id.pwd_image_hint);

        login_btn = (TextView) findViewById(R.id.login_btn);

//        healthcare_prof_checkbox = (CheckBox) findViewById(R.id.healthcare_prof_checkbox);
        //   facility_owner_checkbox = (CheckBox) findViewById(R.id.facility_owner_checkbox);
        //     healthcare_prof_checkbox_layout = (LinearLayout) findViewById(R.id.healthcare_prof_checkbox_layout);
        //    facility_owner_checkbox_layout = (LinearLayout) findViewById(R.id.facility_owner_checkbox_layout);

        //user_term_And_cond_checkbox = (CheckBox) findViewById(R.id.user_term_And_cond_checkbox);

        terms_and_condition = (TextView) findViewById(R.id.terms_and_conditions_text);

        // show hide checkbox for healthcare professinal and facility owner
        // based on consumer type
//        if ("provider".equals(consumer_type)) {
//            healthcare_prof_checkbox_layout.setVisibility(View.VISIBLE);
//            facility_owner_checkbox_layout.setVisibility(View.VISIBLE);
//        } else {
//            healthcare_prof_checkbox_layout.setVisibility(View.GONE);
//            facility_owner_checkbox_layout.setVisibility(View.GONE);
//        }

        // set user terms and password values
        SpannableString ss = new SpannableString(getResources().getString(R.string.terms_agree));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(ConsumerProfessionalSignup.this,
                        TermsAndConditions.class));
            }
        };
        ss.setSpan(clickableSpan, 39, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        terms_and_condition.setText(ss);
        terms_and_condition
                .setMovementMethod(LinkMovementMethod.getInstance());

        btn_user_sign_up = (TextView) findViewById(R.id.btn_user_sign_up);
        btn_user_sign_up.setOnClickListener(this);
        pwd_image_hint.setOnClickListener(this);
        back_btn_layout.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

//    private void showDatePickerDialog() {
//        final DatePickerDialog datePickerDialog;
//        calendar = Calendar.getInstance();
//        int i = calendar.get(Calendar.YEAR);
//        int year = 0, month, day;
//        if (date == 0l) {
//            datePickerDialog = DatePickerDialog.newInstance(
//                    ConsumerProfessionalSignup.this, calendar.get(Calendar.YEAR) - 18,
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH), false);
//        } else {
//            calendar.setTimeInMillis(date);
//            year = calendar.get(Calendar.YEAR);
//            month = calendar.get(Calendar.MONTH);
//            day = calendar.get(Calendar.DAY_OF_MONTH);
//            datePickerDialog = DatePickerDialog.newInstance(
//                    ConsumerProfessionalSignup.this, year, month, day, false);
//        }
//
//        datePickerDialog.setYearRange(1902, i);
//        datePickerDialog.setCloseOnSingleTapDay(false);
//        datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
//    }

    @Override
    public void onClick(View v) {
        int viewElementId = v.getId();

        if (viewElementId == R.id.btn_user_sign_up) {
            validateUserInputDetails();
            return;
        }

        if (viewElementId == R.id.pwd_image_hint) {
            // show hide password
            showHidePassword();
            return;
        }
        if (viewElementId == R.id.back_btn_layout) {
            onBackPressed();
        }

        if (viewElementId == R.id.login_btn) {
            if (!(jeevomSession.getLoggedInStatus())) {
                Intent signup_intent = new Intent(ConsumerProfessionalSignup.this, SignUpLoginActivity.class);
                jeevomSession.setAppType("jeevom");
                startActivityForResult(signup_intent, 10001);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);
            }
        }
//        if (viewElementId == R.id.new_dob_image) {
//            showDatePickerDialog();
//            return;
//        }

    }

    private void showHidePassword() {
        // TODO Auto-generated method stub
        if (isPasswordShown) {
            // password is already shown - hide the password
            password_value
                    .setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
            pwd_image_hint.setImageResource(R.drawable.hidepsd);
            isPasswordShown = false;
        } else {
            pwd_image_hint.setImageResource(R.drawable.eye_icon);
            // show the password
            password_value.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
            isPasswordShown = true;
        }

    }

    // validate all user inputs
    private void validateUserInputDetails() {

//        if (CommonCode.isNullOrEmpty(user_first_name_value.getText().toString()
//                .trim())) {
//            Crouton.makeText(ConsumerProfessionalSignup.this,
//                    JeevOMUtil.EMPTY_FIRST_NAME, Style.ALERT).show();
//            user_first_name_value.requestFocus();
//            return;
//        }
//        if (CommonCode.isNullOrEmpty(user_first_name_value.getText().toString()
//                .trim())) {
//            Crouton.makeText(ConsumerProfessionalSignup.this,
//                    JeevOMUtil.EMPTY_FIRST_NAME, Style.ALERT).show();
//            user_first_name_value.requestFocus();
//            return;
//        }
//        if (CommonCode.isNullOrEmpty(user_last_name_value.getText().toString()
//                .trim())) {
//            Crouton.makeText(ConsumerProfessionalSignup.this,
//                    JeevOMUtil.EMPTY_LAST_NAME, Style.ALERT).show();
//            user_last_name_value.requestFocus();
//            return;
//        }
//        if (CommonCode.isNullOrEmpty(new_dob_value.getText().toString()
//                .trim())) {
//
//            Crouton.makeText(ConsumerProfessionalSignup.this,
//                    JeevOMUtil.MANDATORY_DATE_OF_BIRTH, Style.ALERT).show();
//            return;
//        }
//        if (!CommonCode.validateBirthDate(date)) {
//
//            Crouton.makeText(ConsumerProfessionalSignup.this, JeevOMUtil.VALID_DOB,
//                    Style.ALERT).show();
//            return;
//        }
        if (CommonCode.isNullOrEmpty(user_email_phone_value.getText()
                .toString().trim())) {
            Crouton.makeText(ConsumerProfessionalSignup.this,
                    JeevOMUtil.EMPTY_PHONE_EMAIL, Style.ALERT).show();
            user_email_phone_value.requestFocus();
            return;
        }

        String emailOrPhoneValue = user_email_phone_value.getText().toString();
        if (user_email_phone_value.getText().toString().length() > 0) {

            // conversion throws exception is parsing fails from
            // string
            // to long
            // if exception , means user trying to enter email
            // else phone

            try {
                long number = Long.parseLong(emailOrPhoneValue);
                if (CommonCode.validatePhone(emailOrPhoneValue)) {
                    isEmailEntered = false;
                } else {
                    Crouton.makeText(ConsumerProfessionalSignup.this,
                            JeevOMUtil.VALID_Student_Id, Style.ALERT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                if (CommonCode.validateEmail(emailOrPhoneValue)) {
                    isEmailEntered = true;
                } else {
                    Crouton.makeText(ConsumerProfessionalSignup.this,
                            "Please enter a valid phone number or email id",
                            Style.ALERT).show();
                    return;
                }
            }

        }

        if (CommonCode
                .isNullOrEmpty(password_value.getText().toString().trim())) {
            Crouton.makeText(ConsumerProfessionalSignup.this,
                    JeevOMUtil.EMPTY_PASSWORD, Style.ALERT).show();
            password_value.requestFocus();
            return;
        }

        if (!(CommonCode.validatePassword(password_value.getText().toString()
                .trim()))) {
            Crouton.makeText(ConsumerProfessionalSignup.this,
                    JeevOMUtil.PASSWORD_VALID_MSG, Style.ALERT).show();
            password_value.requestFocus();
            return;
        }

//        if (!user_term_And_cond_checkbox.isChecked()) {
//            Toast.makeText(ConsumerProfessionalSignup.this,
//                    "You need to agree all terms and condition of Jeevom.",
//                    Toast.LENGTH_SHORT).show();
//            return;
//        }
        // every input by user is valid -- now proceed to do sign up
        doSignUp();
    }

    private void doSignUp() {

        MemberInformation memberInformation = new MemberInformation();
//        memberInformation.setFirstName(user_first_name_value.getText()
//                .toString().trim());
//        memberInformation
//                .setLastName(user_last_name_value.getText().toString());
        memberInformation.setPasswordHash(password_value.getText().toString());

        if (isEmailEntered)
            memberInformation.setEmail(user_email_phone_value.getText()
                    .toString());
        else
            memberInformation.setMobile(user_email_phone_value.getText()
                    .toString());

        if (!isUserVerified)
            memberInformation.setIsCheckAlreadyExists(true);
        else
            memberInformation.setIsCheckAlreadyExists(false);

//        if ("provider".equals(consumer_type)) {
//            if (healthcare_prof_checkbox.isChecked())
//                memberInformation.setIsHealthCareProfessional(true);
//            if (facility_owner_checkbox.isChecked())
//                memberInformation.setIsBusinessOwner(true);
//        }

        memberInformation.setDateOfBirth(newDobValue);
        memberInformation.setGender(gender);
        makeApiCallForSignUp(memberInformation);

    }

    private void makeApiCallForSignUp(MemberInformation memberInformation) {
        RestAdapter membershipRestAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLog(new AndroidLog("signup"))
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .build();
        MemberShipSignup membershipSignup = membershipRestAdapter
                .create(MemberShipSignup.class);
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);
        membershipSignup.memberShipSignUp(memberInformation,
                new Callback<ApiResponse<MembershipSignupResponse>>() {
                    @Override
                    public void success(
                            ApiResponse<MembershipSignupResponse> arg0,
                            Response arg1) {
                        progressBarFragment.dismissAllowingStateLoss();
                        if (arg0 != null)
                            if (arg0.getData().isIsLoggedInSuccessfully()) {
                                // sign up successfully done
                                // save member id in session for later use in
                                // application
                                jeevomSession.saveMemberId(arg0.getData()
                                        .getMemberId());

                                // do account login
                                doAccountLogin();
                            }

                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        progressBarFragment.dismissAllowingStateLoss();
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ConsumerProfessionalSignup.this))) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION);
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                            } else if (arg0.getCause() instanceof SocketException) {
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
                            ApiResponse<MembershipSignupResponse> MembershipAuthenticateErrors = gson
                                    .fromJson(json, ApiResponse.class);
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
                            } else if (code.equals("BE-1005")) {
                                showAlert(message);
                            } else if (code.equals("BE-1006")) {

                                // member record already exists , show warning
                                // dialog

                                PhoneEmailExistsWarningDialog.showAlert(
                                        getResources().getString(
                                                R.string.phone_email_exists),
                                        "Email/Phone Exists",
                                        ConsumerProfessionalSignup.this);
                            }
                        }

                    }
                });
    }

    // do account login
    protected void doAccountLogin() {

        // call to authenticate user

    /*    RestAdapter memberShipAuthenticRestAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("authentic"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        MemberShipAuthenticate memberShipAuthentic = memberShipAuthenticRestAdapter
                .create(MemberShipAuthenticate.class);
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);

        // class for member authentic data
        MembershipAuthenticateRequest membershipAuthenticateRequest = new MembershipAuthenticateRequest();
        membershipAuthenticateRequest.setEmailOrPhone(user_email_phone_value
                .getText().toString());
        membershipAuthenticateRequest.setPasswordHash(password_value.getText()
                .toString());
        membershipAuthenticateRequest.setDoNotCheckEmailPhoneVerified(true);

        memberShipAuthentic.onSuccessFullMemberSignIn(
                membershipAuthenticateRequest,
                new Callback<MembershipAuthenticateResponse>() {

                    @Override
                    public void success(MembershipAuthenticateResponse data,
                                        retrofit.client.Response arg1) {
                        progressBarFragment.dismissAllowingStateLoss();
                        String code = data.getStatus().getCode();
                        if (code.equals("Success")) {
                            progressBarFragment.dismissAllowingStateLoss();
                            Toast.makeText(getApplicationContext(),
                                    "You have successfully logged in",
                                    Toast.LENGTH_SHORT).show();

                            // setAlarm();

                            // save user details in session
                            saveUserDetails(data);

                            getConsumerProfiles(jeevomSession.getMemberId());

                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressBarFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ConsumerProfessionalSignup.this))) {
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
                });*/

    }

    protected void saveUserDetails(MembershipAuthenticateResponse data) {

        // save auth token
        if (!CommonCode.isNullOrEmpty(data.getData().getAuthToken())) {
            jeevomSession.setAuthToken(data.getData().getAuthToken());
        }
//save title
        if (!CommonCode.isNullOrEmpty(data.getData().getTitle())) {

            // save user name
            jeevomSession.setTitle(data.getData().getTitle());
        }
        // save user name
        if (!CommonCode.isNullOrEmpty(data.getData().getFullName())) {
            jeevomSession.setName(data.getData().getFullName());
        }

        // save age
        String dateOfBirth = data.getData().getDateOfBirth();

        // get Age of User
        long age;
        if (!CommonCode.isNullOrEmpty(dateOfBirth)) {
            age = CommonCode.getYearDifferenceBetweenDates(CommonCode
                    .getCurrentTimeStamp(), data.getData().getDateOfBirth());
        } else {
            age = 0;
        }
        jeevomSession.setAge(String.valueOf(age));

        // save email of user
        // email of user
        String email;
        if (!CommonCode.isNullOrEmpty(data.getData().getEmail())) {
            email = data.getData().getEmail();
        } else {
            email = null;
        }
        jeevomSession.setEmail(email);

        // cellnumber of
        // user
        String cellNumber;
        if (!CommonCode.isNullOrEmpty(data.getData().getCellNumber())) {
            cellNumber = data.getData().getCellNumber();
        } else {
            cellNumber = null;
        }
        jeevomSession.setCellNumber(cellNumber);

        // gender of user
        String gender;
        if (!CommonCode.isNullOrEmpty(data.getData().getGender())) {
            gender = data.getData().getGender();
        } else {
            gender = null;
        }
        jeevomSession.setGender(gender);

        // unique public id of user
        String uniquePublicID;
        if (!CommonCode.isNullOrEmpty(data.getData().getUniquePublicID())) {
            uniquePublicID = data.getData().getUniquePublicID();
        } else {
            uniquePublicID = null;
        }
        jeevomSession.setUniquePublicId(uniquePublicID);

        // set logged in status to true
        jeevomSession.setLoggedInStatus();

        // save user address
        if (data.getData().getContactInformation() != null)
            if (data.getData().getContactInformation().getAddress() != null)
                jeevomSession.saveUserAddress(data.getData()
                        .getContactInformation().getAddress());

        // save user phone no verification status
        jeevomSession.setUserPhoneVerifyStatus(data.getData()
                .isUserVerifiedViaMobile());

        // save user email no verification status
        jeevomSession.setUserEmailVerifyStatus(data.getData()
                .isUserVerifiedViaEmail());
    }

    // get member all profile ids // consumer id, professional id and facility
    // id
    protected void getConsumerProfiles(int id) {

        RestAdapter consumerProfiles = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ConsumerIds consumer = consumerProfiles.create(ConsumerIds.class);

        consumer.getConsumerProfileIds(id,
                new Callback<ConsumerProfilesRespone>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ConsumerProfessionalSignup.this))) {
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
                    public void success(ConsumerProfilesRespone arg0,
                                        Response arg1) {
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {

                            String member_unique_id;
                            int facility_id = 0;
                            int professional_id = 0;
                            int consumer_id = 0;
                            List<ConsumerProfiles> memberProfiles = arg0
                                    .getData().getMemberProfiles();

                            member_unique_id = arg0.getData()
                                    .getMemberUniqueId();
                            for (ConsumerProfiles consumerProfiles2 : memberProfiles) {

                                if (consumerProfiles2.getProfileType().equals(
                                        "Consumer")) {
                                    consumer_id = consumerProfiles2
                                            .getProfileId();
                                } else if (consumerProfiles2.getProfileType()
                                        .equals("Professional")) {
                                    professional_id = consumerProfiles2
                                            .getProfileId();
                                } else if (consumerProfiles2.getProfileType()
                                        .equals("Facility")) {
                                    facility_id = consumerProfiles2
                                            .getProfileId();
                                }
                            }
                            jeevomSession.saveConsumerIds(member_unique_id,
                                    consumer_id, professional_id, facility_id);
                        }

                    }
                });
//        event.setData("finish");
//        bus.post(event);


        try {
            RoleSelectionDialog showDialog = RoleSelectionDialog
                    .newInstance();
            showDialog.setCancelable(false);
            showDialog.show(getSupportFragmentManager(),
                    "context_dialog_frag");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //finish();
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    public <T> void onEvent(T event) {

        if (event.getClass().equals(EventData.class)) {
            if ("proceed".equals(((EventData) event).getData())) {

                // user record already exists in database -- show user a popup
                // to
                // set password
                sendSetPasswordRequest();
            } else if ("email_phone_verified".equals(((EventData) event)
                    .getData())) {

                // get member details and fill the form with fetched records
                getMemberInformation();

            }

        }

    }

    private void getMemberInformation() {

        // set isUserverified - true -- because user has verified itself
        isUserVerified = true;

        String email;
        String phoneNo;
        RestAdapter memberInformationAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLog(new AndroidLog("member_info"))
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .build();
        GetMemberInformation memberInformation = memberInformationAdapter
                .create(GetMemberInformation.class);

        if (isEmailEntered) {
            email = user_email_phone_value.getText().toString();
            phoneNo = "";
        } else {
            email = "";
            phoneNo = user_email_phone_value.getText().toString();
        }
        memberInformation.getMemberInformation(email, phoneNo,
                new Callback<ApiResponse<MemberInformation>>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ConsumerProfessionalSignup.this))) {
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
                            ApiResponse<MemberInformation> MembershipAuthenticateErrors = gson
                                    .fromJson(json, ApiResponse.class);
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
                    public void success(ApiResponse<MemberInformation> arg0,
                                        Response arg1) {
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {

                            // fill signup form based upon fetched information
                            fillMemberDetails(arg0);
                        }

                    }
                });

    }

    protected void fillMemberDetails(
            ApiResponse<MemberInformation> memberInformation) {

        // set first name
//        if (!CommonCode.isNullOrEmpty(memberInformation.getData()
//                .getFirstName()))
//            user_first_name_value.setText(memberInformation.getData()
//                    .getFirstName());
//
//        // set last name
//        if (!CommonCode
//                .isNullOrEmpty(memberInformation.getData().getLastName()))
//            user_last_name_value.setText(memberInformation.getData()
//                    .getLastName());

        // set email or phone
        if (isEmailEntered)
            if (!CommonCode.isNullOrEmpty(memberInformation.getData()
                    .getEmail()))
                user_email_phone_value.setText(memberInformation.getData()
                        .getEmail());

            else if (!CommonCode.isNullOrEmpty(memberInformation.getData()
                    .getMobile()))
                user_email_phone_value.setText(memberInformation.getData()
                        .getMobile());

    }

    private void sendSetPasswordRequest() {
        String email;
        String phoneNo;
        RestAdapter sendSetPasswordAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLog(new AndroidLog("setPassword"))
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .build();
        SendSetPasswordRequest sendSetPasswordRequest = sendSetPasswordAdapter
                .create(SendSetPasswordRequest.class);
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);

        if (isEmailEntered) {
            email = user_email_phone_value.getText().toString();
            phoneNo = "";
        } else {
            email = "";
            phoneNo = user_email_phone_value.getText().toString();
        }
        sendSetPasswordRequest.sendSetPasswordRequest(email, phoneNo,
                new Callback<ApiResponse<Boolean>>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressBarFragment.dismissAllowingStateLoss();
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ConsumerProfessionalSignup.this))) {
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
                            ApiResponse<Boolean> MembershipAuthenticateErrors = gson
                                    .fromJson(json, ApiResponse.class);
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
                    public void success(ApiResponse<Boolean> arg0, Response arg1) {
                        progressBarFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            if (arg0.getData()) {

                                // show popup to enter code
                                if (isEmailEntered) {
                                    Intent phoneEmailIntent = new Intent(
                                            ConsumerProfessionalSignup.this,
                                            PhoneEmailVerification.class);
                                    phoneEmailIntent.putExtra("type", "email");
                                    phoneEmailIntent.putExtra(
                                            "isSignUpTimeVerification", true);
                                    phoneEmailIntent.putExtra("email",
                                            user_email_phone_value.getText()
                                                    .toString());
                                    startActivity(phoneEmailIntent);
                                } else {
                                    SimplePostAsyncTask codeAsyncTask = new SimplePostAsyncTask(
                                            ConsumerProfessionalSignup.this);
                                    codeAsyncTask.execute(JeevOMUtil.baseUrl
                                            + JeevOMUtil.missedCallFirst
                                            + user_email_phone_value.getText()
                                            .toString()
                                            + JeevOMUtil.missedCallSecond);
                                }

                            }
                        }

                    }
                });

    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }

    @Override
    public void onCodeGenerated(String result) {

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
            } else if (code.equals("Success")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                DataContainer codeValues = gson.fromJson(result,
                        DataContainer.class);
                String UniqueRequestId = codeValues.getData()
                        .getUniqueRequestId();
                String CallToVerifyNumberAsText = codeValues.getData()
                        .getCallToVerifyNumberAsText();
                Intent phoneEmailIntent = new Intent(
                        ConsumerProfessionalSignup.this,
                        PhoneEmailVerification.class);
                phoneEmailIntent.putExtra("uniqueRequestId", UniqueRequestId);
                phoneEmailIntent.putExtra("CallToVerifyNumberAsText",
                        CallToVerifyNumberAsText);

                phoneEmailIntent.putExtra("isSignUpTimeVerification", true);
                phoneEmailIntent.putExtra("type", "phone");
                phoneEmailIntent.putExtra("phone", user_email_phone_value
                        .getText().toString());
                phoneEmailIntent.putExtra("email", "");
                startActivity(phoneEmailIntent);

            }
        }

    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year,
                          int month, int day) {

        current_year = year;
        currentDay = day;
        currentMonth = month + 1;
        newDobValue = currentMonth + "/" + currentDay + "/" + current_year;

        new_dob_value.setText(CommonCode.monthYear(currentDay,
                currentMonth - 1, current_year));
        dateFormatted = currentMonth + "/" + currentDay + "/" + current_year;
        date = CommonCode.convertToMilliseconds(year, month, day);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position,
                               long arg3) {

        int id = parent.getId();
//        if (id == R.id.new_user_gender_spinner) {
//            gender = genders[position];
//        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void callFinishOnActivity() {

        setResult(RESULT_OK);
        finish();

       /* try {
            Intent i=new Intent(ConsumerProfessionalSignup.this,Class.forName("com.jeevom.main.personal"));
            startActivity(i);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10001 && resultCode == RESULT_OK) {
            openHomeScreen();
        }
    }

    public void openHomeScreen() {
        try {
            Intent myIntent = new Intent(this, Class.forName("com.school.main.HomeActivity"));
            startActivity(myIntent);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
