package com.schoolteacher.library.main;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.library.events.EventData;
import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.pojo.ConsumerProfiles;
import com.schoolteacher.mylibrary.pojo.ConsumerProfilesRespone;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateRequest;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.service.interfaces.ConsumerIds;
import com.schoolteacher.mylibrary.service.interfaces.MemberShipAuthenticate;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;

import de.greenrobot.event.EventBus;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SignInActivity extends ActionBarActivity implements
        OnClickListener {
    GlobalAlert globalAlert;
    String email, password;
    Button btn_signin;
    EditText editText_email, editText_password;
    DialogFragment progressBarFragment, showMessage;
    TextView signin_text;
    private JeevomSession jeevomSession;
    private EventBus bus;
    private EventData event;
    private boolean isInContext;
    private Toolbar signin_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.signin_demo);
        setUpActionBar();
        bus = EventBus.getDefault();
        event = new EventData();

        Intent intent = getIntent();
        if (intent.hasExtra("isInContext"))
            isInContext = intent.getBooleanExtra("isInContext", false);

        globalAlert = new GlobalAlert(SignInActivity.this);
        jeevomSession = new JeevomSession(this);
        signin_text = (TextView) findViewById(R.id.signin_text);
        signin_text.setText(Html.fromHtml("<b>" + " Welcome Back " + "</b>"));
        Button btn_signin = (Button) findViewById(R.id.btn_signin);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_password = (EditText) findViewById(R.id.editText_password);

        btn_signin.setOnClickListener(this);

        TextView btnJeevOmSignUp = (TextView) findViewById(R.id.btn_signup);
        TextView forget_password = (TextView) findViewById(R.id.btn_forget_your_password);
        btnJeevOmSignUp.setOnClickListener(this);
        forget_password.setOnClickListener(this);

        // set text change listener
        editText_email.addTextChangedListener(new GenericTextWatcher(
                editText_email));
        editText_password.addTextChangedListener(new GenericTextWatcher(
                editText_password));

    }

    private void setUpActionBar() {

        signin_toolbar = (Toolbar) findViewById(R.id.signin_toolbar);
        setSupportActionBar(signin_toolbar);
        getSupportActionBar().setTitle("Login");
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

    @Override
    public void onClick(View view) {
        // please remove this condition
        if (view.getId() == R.id.btn_signup) {
            Intent signup_intent = new Intent(SignInActivity.this,
                    SignUpLoginActivity.class);
            // session.setProjectType("jeevom");
            startActivity(signup_intent);
            finish();

        }

        if (view.getId() == R.id.btn_signin) {
            email = editText_email.getText().toString();
            password = editText_password.getText().toString();
            boolean isValid = false;
            if (email.length() > 0) {
                try {
                    // conversion throws exception is parsing fails from string
                    // to long
                    // if exception , means user trying to enter email else
                    // phone
                    long number = Long.parseLong(email);
                    if (CommonCode.validatePhone(email)) {
                        isValid = true;
                    } else {
                        Crouton.makeText(SignInActivity.this,
                                JeevOMUtil.VALID_Student_Id, Style.ALERT).show();
                    }
                } catch (NumberFormatException e) {
                    if (CommonCode.validateEmail(email)) {
                        isValid = true;
                    } else {
                        Crouton.makeText(SignInActivity.this,
                                JeevOMUtil.VALID_EMAIL, Style.ALERT).show();
                    }
                }

                if (isValid) {
                    if (!(password.length() > 0)) {
                        Crouton.makeText(SignInActivity.this,
                                JeevOMUtil.EMPTY_PASSWORD, Style.ALERT).show();

                    } else {

                        // call to authenticate user

                      /*  RestAdapter memberShipAuthenticRestAdapter = new RestAdapter.Builder()
                                .setClient(new MyUrlConnectionClient())
                                .setLogLevel(LogLevel.FULL)
                                .setLog(new AndroidLog("authentic"))
                                .setEndpoint(JeevOMUtil.baseUrl).build();
                        MemberShipAuthenticate memberShipAuthentic = memberShipAuthenticRestAdapter
                                .create(MemberShipAuthenticate.class);
                        progressBarFragment = ProgressDialogFragment
                                .newInstance();
                        progressBarFragment.show(getSupportFragmentManager(),
                                "dialog");
                        progressBarFragment.setCancelable(false);
                        MembershipAuthenticateRequest membershipAuthenticateRequest = new MembershipAuthenticateRequest();
                        membershipAuthenticateRequest
                                .setEmailOrPhone(editText_email.getText()
                                        .toString());
                        membershipAuthenticateRequest
                                .setPasswordHash(editText_password.getText()
                                        .toString());
                        membershipAuthenticateRequest
                                .setDoNotCheckEmailPhoneVerified(true);

                        memberShipAuthentic.onSuccessFullMemberSignIn(
                                membershipAuthenticateRequest,
                                new Callback<MembershipAuthenticateResponse>() {

                                    @Override
                                    public void success(
                                            MembershipAuthenticateResponse data,
                                            retrofit.client.Response arg1) {
                                        progressBarFragment
                                                .dismissAllowingStateLoss();
                                        String code = data.getStatus()
                                                .getCode();
                                        if (code.equals("Success")) {
                                            progressBarFragment
                                                    .dismissAllowingStateLoss();
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "You have successfully logged in",
                                                    Toast.LENGTH_SHORT).show();
                                            jeevomSession.saveMemberId(data
                                                    .getData().getId());
                                            // save user details in session
                                            saveUserDetails(data);
                                            getConsumerProfiles(jeevomSession
                                                    .getMemberId());
                                        }
                                    }

                                    @Override
                                    public void failure(RetrofitError arg0) {
                                        progressBarFragment
                                                .dismissAllowingStateLoss();

                                        if (arg0.isNetworkError()) {
                                            if (!(Connectivity
                                                    .checkConnectivity(SignInActivity.this))) {
                                                showAlert(JeevOMUtil.INTERNET_CONNECTION);
                                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                                showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                                            } else if (arg0.getResponse() == null) {
                                                showAlert(JeevOMUtil.SOMETHING_WRONG);
                                            }
                                        } else if (arg0.getResponse()
                                                .getStatus() > 400) {
                                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                                        } else {
                                            String json = new String(
                                                    ((TypedByteArray) arg0
                                                            .getResponse()
                                                            .getBody())
                                                            .getBytes());
                                            Gson gson = new GsonBuilder()
                                                    .setPrettyPrinting()
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
                }
            } else {
                Crouton.makeText(SignInActivity.this,
                        JeevOMUtil.EMPTY_EMAIL_OR_PHONE, Style.ALERT).show();
            }
        }

        if (view.getId() == R.id.btn_forget_your_password) {
            email = editText_email.getText().toString();
            if (email.length() > 0) {
                try {
                    // conversion throws exception is parsing fails from string
                    // to long
                    // if exception , means user trying to enter email else
                    // phone
                    long number = Long.parseLong(email);
                    if (CommonCode.validatePhone(email)) {
                        Intent forgot_pwd_intent = new Intent(
                                SignInActivity.this, ForgotPassword.class);
                        forgot_pwd_intent.putExtra("email_or_phone", email);
                        startActivity(forgot_pwd_intent);
                        setEmptyValueInEditText();
                    } else {
                        Crouton.makeText(SignInActivity.this,
                                JeevOMUtil.VALID_Student_Id, Style.ALERT).show();
                    }
                } catch (NumberFormatException e) {
                    if (CommonCode.validateEmail(email)) {
                        Intent forgot_pwd_intent = new Intent(
                                SignInActivity.this, ForgotPassword.class);
                        forgot_pwd_intent.putExtra("email_or_phone", email);
                        startActivity(forgot_pwd_intent);
                        setEmptyValueInEditText();
                    } else {
                        Crouton.makeText(SignInActivity.this,
                                JeevOMUtil.VALID_EMAIL, Style.ALERT).show();
                    }
                }
            } else {
                Intent forgot_pwd_intent = new Intent(SignInActivity.this,
                        ForgotPassword.class);
                startActivity(forgot_pwd_intent);
                setEmptyValueInEditText();
            }
        }
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

    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        if (requestCode == JeevOMUtil.SIGNUP_MAIN_REQUEST_CODE_FROM_SIGNIN_PAGE
                && responseCode == Activity.RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        } else if (requestCode == JeevOMUtil.SIGNUP_MAIN_REQUEST_CODE_FROM_SIGNIN_PAGE) {

            finish();
        }
    }

    private class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1,
                                      int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1,
                                  int i2) {
        }

        public void afterTextChanged(Editable editable) {
            int id = view.getId();
            if (id == R.id.editText_email) {
                editText_email.setError(null);
            } else if (id == R.id.editText_password) {
                editText_password.setError(null);
            }
        }
    }

    private void setEmptyValueInEditText() {
        editText_password.setText("");
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
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
                                    .checkConnectivity(SignInActivity.this))) {
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
                            if (isInContext) {
                                setResult(Activity.RESULT_OK);
                            } else {
                                setResult(Activity.RESULT_OK);
                                // event.setData("logged In");
                                // bus.post(event);
                            }
                            finish();
                        }

                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Applying Exit Animation;
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

    // private void setAlarm() {
    // final long FIVE_MINUTE = 5 * 60 * 1000;
    // Intent intent = new Intent(SignInActivity.this,
    // AppointmentNotifier.class);
    // PendingIntent pendingintent = PendingIntent.getBroadcast(
    // SignInActivity.this, 0, intent, 0);
    // AlarmManager alarmManager = (AlarmManager) SignInActivity.this
    // .getSystemService(Context.ALARM_SERVICE);
    // alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
    // System.currentTimeMillis(), FIVE_MINUTE, pendingintent);
    // }
}
