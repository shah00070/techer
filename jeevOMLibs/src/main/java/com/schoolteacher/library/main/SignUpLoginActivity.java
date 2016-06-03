package com.schoolteacher.library.main;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
//import com.facebook.model.GraphObject;
import com.facebook.widget.WebDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.library.events.EventData;
import com.schoolteacher.library.pojo.AddExternalUserResponse;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.library.pojo.ExternalLoginInfo;
import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.model.AddExternalUserRequest;
import com.schoolteacher.mylibrary.pojo.ConsumerProfiles;
import com.schoolteacher.mylibrary.pojo.ConsumerProfilesRespone;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateRequest;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.service.interfaces.AddExternalUser;
import com.schoolteacher.mylibrary.service.interfaces.ConsumerIds;
import com.schoolteacher.mylibrary.service.interfaces.ExternalMemberShipAuthenticate;
import com.schoolteacher.mylibrary.service.interfaces.MemberShipAuthenticate;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;

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
import retrofit.mime.TypedByteArray;

public class SignUpLoginActivity extends ActionBarActivity implements
        OnClickListener, ConnectionCallbacks, OnConnectionFailedListener {
    GlobalAlert globalAlert;
    AddExternalUserRequest addObject;
    //private TextView signup_text;
    ProgressDialogFragment progressBarFragment;
    //Button  btn_signup_provider;

    TextView btn_facebook_login, btn_google_login, signup, login, forget_password;
    private EventBus bus;
    // Google SignUp Variables
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    private boolean isPasswordShown = false;
    private boolean mIntentInProgress;
    String social_name, social_email, social_id, social_type;
    private JeevomSession jeevomSession;
    int social_gender = -1;
    private boolean isInContext;
    private boolean isSignupShown;
    private Toolbar signup_login_toolbar;
    private EditText editText_email, editText_password;
    String email, password;
    private LinearLayout back_imageview_layout;
    int instantAppointItemPos = -1;
    String profileIdValue = "";
   // public String ss="730888413665717";
    private boolean isFbLogin = false;
    private Activity mCtx;
    ImageView pwd_image_hint;
    private ShareItem mShareItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login);

        // set up action bar
        setUpActionBar();

        jeevomSession = new JeevomSession(this);
        bus = EventBus.getDefault();
        // Register as a subscriber
        bus.register(this);
        Intent intent = getIntent();
        if (intent.hasExtra("isInContext"))
            isInContext = intent.getBooleanExtra("isInContext", false);

        if (intent.hasExtra("isSignupShown")) {
            isSignupShown = intent.getBooleanExtra("isSignupShown", false);
        }

        if (intent.hasExtra("item_position")) {
            instantAppointItemPos = intent.getIntExtra("item_position", -1);
        }

        if (intent.hasExtra("profileId")) {
            profileIdValue = intent.getStringExtra("profileId");
        }

        globalAlert = new GlobalAlert(this);
        getViewElementsReferences();

        implementOnclickListners();

        // initialize google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();

        // set text change listener
        editText_email.addTextChangedListener(new GenericTextWatcher(
                editText_email));
        editText_password.addTextChangedListener(new GenericTextWatcher(
                editText_password));
    }

    private void setUpActionBar() {

        // signup_login_toolbar = (Toolbar) findViewById(R.id.signup_login_toolbar);
        //   setSupportActionBar(signup_login_toolbar);
        //   getSupportActionBar().setTitle("Sign Up/Login");
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }


    /* implementing on click listners on view elements */
    private void implementOnclickListners() {
      //  btn_facebook_login.setOnClickListener(this);
     //   btn_google_login.setOnClickListener(this);
        login.setOnClickListener(this);
       // signup.setOnClickListener(this);
        forget_password.setOnClickListener(this);
        back_imageview_layout.setOnClickListener(this);
        //btn_signup_provider.setOnClickListener(this);
        pwd_image_hint.setOnClickListener(this);
        // show hide provider signup button based on project type
        // if project is jeevom - hide provider signup button
        if ("jeevom".equals(jeevomSession.getAppType())) {
            //btn_signup_provider.setVisibility(View.GONE);
        }

    }






   /* private void postStatusUpdate() {


        if (user != null && hasPublishPermission()) {
            final String message = getString(R.string.status_update, user.getFirstName(), (new Date().toString()));

            Request request = Request.newStatusUpdateRequest(Session.getActiveSession(), message, new Request.Callback() {

                //Request request = Request.newPostRequest(Session.getActiveSession(), "1000001851621**",GraphObject??? , new Request.Callback() {
                @Override
                public void onCompleted(Response response) {
                    showPublishResult(message, response.getGraphObject(), response.getError());
                }
            });
            request.executeAsync();
        } else {
            pendingAction = PendingAction.POST_STATUS_UPDATE;
        }
    }*/
    /* getting view elements references */
    private void getViewElementsReferences() {

       // btn_facebook_login = (TextView) findViewById(R.id.btn_facebook_login);
       // btn_google_login = (TextView) findViewById(R.id.btn_google_login);
        login = (TextView) findViewById(R.id.login);
       // signup = (TextView) findViewById(R.id.signup);
        pwd_image_hint = (ImageView) findViewById(R.id.pwd_image_hint);
        back_imageview_layout = (LinearLayout) findViewById(R.id.back_btn_layout);
        forget_password = (TextView) findViewById(R.id.btn_forget_your_password);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_password = (EditText) findViewById(R.id.editText_password);
        //btn_signup_provider = (Button) findViewById(R.id.btn_signup_provider);
        //  signup_text = (TextView) findViewById(R.id.signup_text);
        if (isSignupShown) {
         //   signup.setVisibility(View.GONE);
            //btn_signup_provider.setVisibility(View.GONE);
            // signup_text.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Applying Exit Animation;
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

/*    public  void publishFeedDialog(final Activity current, final String title,
                                         final String caption, final String description, final String link,
                                         final String pictureUrl) {
        // start Facebook Login
        Session.openActiveSession(current, true, new Session.StatusCallback() {

            // callback when session changes state
            @Override
            public void call(Session session, SessionState state,
                             Exception exception) {
                if (session.isOpened()) {
                    Bundle params = new Bundle();
                    params.putString("name", title);
                    params.putString("caption", caption);
                    params.putString("description", description);
                    params.putString("link", link);
                    params.putString("picture", pictureUrl);

                    WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(
                            current, Session.getActiveSession(), params))
                            .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                                @Override
                                public void onComplete(Bundle values,
                                                       FacebookException error) {
                                    if (error == null) {
                                        // When the story is posted, echo the
                                        // success
                                        // and the post Id.
                                        final String postId = values
                                                .getString("post_id");
                                        if (postId != null) {
                                            //   ToastHelper.MakeShortText("Posted");
                                            Toast.makeText(SignUpLoginActivity.this, "Posted", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // User clicked the Cancel button


                                            Toast.makeText(SignUpLoginActivity.this, "Publish cancelled", Toast.LENGTH_SHORT).show();

                                        }
                                    } else if (error instanceof FacebookOperationCanceledException) {
                                        // User clicked the "x" button

                                        Toast.makeText(SignUpLoginActivity.this, " cancelled", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Generic, ex: network error

                                        Toast.makeText(SignUpLoginActivity.this, "error cancelled", Toast.LENGTH_SHORT).show();
                                    }
                                }

                }).build();
                feedDialog.show();
            }
        }
    });*/

    public <T> void onEvent(T event) {

        if (event.getClass().equals(EventData.class)) {
            if ("finish".equals(((EventData) event).getData())) {
                finish();
            }
        }
    }
    private void showHidePassword() {
        // TODO Auto-generated method stub
        if (isPasswordShown) {
            // password is already shown - hide the password
            editText_password
                    .setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());

            pwd_image_hint.setImageResource(R.drawable.hidepsd);//setCompoundDrawablesWithIntrinsicBounds(0, 0, , 0);
            isPasswordShown = false;
        } else {
            // show the password
            pwd_image_hint.setImageResource(R.drawable.eye_icon);
            editText_password.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
            isPasswordShown = true;
        }

    }
    @Override
    public void onClick(final View v) {
        int viewElementId = v.getId();

        if (viewElementId == R.id.pwd_image_hint) {
            // show hide password
            showHidePassword();
            return;
        }

        if (viewElementId == R.id.login) {

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
                        Crouton.makeText(SignUpLoginActivity.this,
                                JeevOMUtil.VALID_Student_Id, Style.ALERT).show();
                    }
                } catch (NumberFormatException e) {
                    if (CommonCode.validateEmail(email)) {
                        isValid = true;
                    } else {
                        Crouton.makeText(SignUpLoginActivity.this,
                                JeevOMUtil.VALID_EMAIL, Style.ALERT).show();
                    }
                }

                if (isValid) {
                    if (!(password.length() > 0)) {
                        Crouton.makeText(SignUpLoginActivity.this,
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
                                                    .checkConnectivity(SignUpLoginActivity.this))) {
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
                Crouton.makeText(SignUpLoginActivity.this,
                        JeevOMUtil.EMPTY_EMAIL_OR_PHONE, Style.ALERT).show();
            }

        }
        if (viewElementId == R.id.signup) {

          /*  // intent to start consumer professional signup activity
            Intent consumer_sign_up = new Intent(SignUpLoginActivity.this,
                    ConsumerProfessionalSignup.class);
            consumer_sign_up.putExtra("consumer_type", "consumer");// passing
            // value to
            // detect
            // consumer
            // type
            startActivityForResult(consumer_sign_up, 200);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);*/
        }

        if (viewElementId == R.id.btn_forget_your_password) {
            email = editText_email.getText().toString();
            if (email.length() > 0) {
                try {
                    // conversion throws exception is parsing fails from string
                    // to long
                    // if exception , means user trying to enter email else
                    // phone
                    long number = Long.parseLong(email);
                    if (CommonCode.validatePhone(email)) {
                        editText_password.setText("");

                        Intent forgot_pwd_intent = new Intent(
                                SignUpLoginActivity.this, ForgotPassword.class);
                        forgot_pwd_intent.putExtra("email_or_Student_Id", email);
                        startActivity(forgot_pwd_intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);

                    } else {
                        Crouton.makeText(SignUpLoginActivity.this,
                                JeevOMUtil.VALID_Student_Id, Style.ALERT).show();
                    }
                } catch (NumberFormatException e) {
                    if (CommonCode.validateEmail(email)) {
                        editText_password.setText("");

                        Intent forgot_pwd_intent = new Intent(
                                SignUpLoginActivity.this, ForgotPassword.class);
                        forgot_pwd_intent.putExtra("email_or_Student_Id", email);
                        startActivity(forgot_pwd_intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);


                    } else {
                        Crouton.makeText(SignUpLoginActivity.this,
                                JeevOMUtil.VALID_EMAIL, Style.ALERT).show();
                    }
                }
            } else {
                editText_password.setText("");

                Intent forgot_pwd_intent = new Intent(SignUpLoginActivity.this,
                        ForgotPassword.class);
                startActivity(forgot_pwd_intent);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);
            }
        }

        if (viewElementId == R.id.back_btn_layout) {
            onBackPressed();
        }
//        if (viewElementId == R.id.btn_signup_provider) {
//
//            // intent to start consumer professional signup activity
//            Intent consumer_sign_up = new Intent(SignUpLoginActivity.this,
//                    ConsumerProfessionalSignup.class);
//            consumer_sign_up.putExtra("consumer_type", "provider");// passing
//            // value to
//            // detect
//            // consumer
//            // type
//            startActivity(consumer_sign_up);
//            return;
//        }

    }









   /* public  void publishFeedDialog(final Activity current) {
        // start Facebook Login
        Session.openActiveSession(current, true, new Session.StatusCallback() {

            // callback when session changes state
            @Override
            public void call(Session session, SessionState state,
                             Exception exception) {
                if (session.isOpened()) {




                   Bundle params = new Bundle();
                  *//*  params.putString("name", "test-name");
                    params.putString("caption", "test-caption");
                    params.putString("description", "test-description");
                    params.putString("link", "http://curious-blog.blogspot.com");
                    params.putString("picture",
                            "http://somewebsite/test.jpg");
                  *//*  WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(
                            current, Session.getActiveSession(), params))
                            .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                                @Override
                                public void onComplete(Bundle values,
                                                       FacebookException error) {
                                    if (error == null) {
                                        // When the story is posted, echo the
                                        // success
                                        // and the post Id.
                                        final String postId = values
                                                .getString("post_id");
                                        if (postId != null) {

                                            addExternalUser(addObject);
                                            Toast.makeText(SignUpLoginActivity.this, "pppppp", Toast.LENGTH_SHORT).show();
                                        } else {
                                                  }
                                    } else if (error instanceof FacebookOperationCanceledException) {
                                        } else {
                                        }
                                }

                            }).build();
                    feedDialog.show();
                }
            }
        });
    }
*/


    private void publishFeedDialog(final Activity current) {


        Session.openActiveSession(current, true, new Session.StatusCallback() {

            // callback when session changes state
            @Override
            public void call(Session session, SessionState state,
                             Exception exception) {
                if (session.isOpened()) {


                    Bundle params = new Bundle();
                /*    params.putString("caption", "To get exclusive deals, download now!");
                    params.putString("description", "I just downloaded Jeevom Healthcare App! \n" +
                            "Download Jeevom for efficiently managing all your healthcare needs.");
*/
                    params.putString("link", "https://www.facebook.com/JeevOM/");
                    params.putString("name", "Jeevom Healthcare Application ");
                    params.putString("picture", "https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAAReAAAAJGU0MGRmNDA1LWI3ZjYtNDE0My1hZGEwLWQ1ODAxMzNmMjVmOA.png");


        WebDialog feedDialog = (
                new WebDialog.FeedDialogBuilder(SignUpLoginActivity.this,
                        Session.getActiveSession(),
                        params))
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error == null) {
                            // When the story is posted, echo the
                            // success
                            // and the post Id.
                            final String postId = values
                                    .getString("post_id");
                            if (postId != null) {

                                addExternalUser(addObject);
                              //  Toast.makeText(SignUpLoginActivity.this, "posted done ", Toast.LENGTH_SHORT).show();
                            } else {
                             //  Toast.makeText(SignUpLoginActivity.this, "posted cancel", Toast.LENGTH_SHORT).show();
                             //   Toast.makeText(SignUpLoginActivity.this, "cancle", Toast.LENGTH_SHORT).show();

                                addExternalUser(addObject);

                            }
                        } else if (error instanceof FacebookOperationCanceledException) {
                          //  Toast.makeText(SignUpLoginActivity.this, "e1", Toast.LENGTH_SHORT).show();

                        } else {
                            //Toast.makeText(SignUpLoginActivity.this, "e2", Toast.LENGTH_SHORT).show();
                            addExternalUser(addObject);

                        }
                    }

                })
                .build();
        feedDialog.show();


            }
        }
    });
}






    public void addExternalUser(final AddExternalUserRequest addObject) {
        RestAdapter addExternalUser = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLog(new AndroidLog("social")).setLogLevel(LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        AddExternalUser addExternal = addExternalUser
                .create(AddExternalUser.class);
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);
        addExternal.addExternal(addObject,
                new Callback<ApiResponse<AddExternalUserResponse>>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressBarFragment.dismissAllowingStateLoss();
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(SignUpLoginActivity.this))) {
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
                            ApiResponse<AddExternalUserResponse> MembershipAuthenticateErrors = gson
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
                    public void success(
                            ApiResponse<AddExternalUserResponse> arg0,
                            retrofit.client.Response arg1) {
                        progressBarFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {

                            if (arg0.getData().isIsLoggedInSuccessfully()) {

                                // save member id in session for later use in
                                // application
                                jeevomSession.saveMemberId(arg0.getData()
                                        .getMemberId());

                                // user successfully logged in -- just make sign
                                // in request

                                ExternalLoginInfo externalLoginInfo = new ExternalLoginInfo();
                                externalLoginInfo.setEmailOrPhone(addObject
                                        .getEmail());
                                externalLoginInfo.setExternalProvider(true);

                                doAccountLogin(externalLoginInfo);
                            }

                        }

                    }
                });

    }

    // do account login
    protected void doAccountLogin(ExternalLoginInfo externalLoginInfo) {

        // Gson gson = new GsonBuilder().create();
        // String data = gson.toJson(externalLoginInfo).toString();

        // call to authenticate user

        RestAdapter memberShipAuthenticRestAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("authentic"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ExternalMemberShipAuthenticate memberShipAuthentic = memberShipAuthenticRestAdapter
                .create(ExternalMemberShipAuthenticate.class);
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);

        memberShipAuthentic.onSuccessFullMemberSignIn(externalLoginInfo,
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
                                    .checkConnectivity(SignUpLoginActivity.this))) {
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
                });

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
                                    .checkConnectivity(SignUpLoginActivity.this))) {
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
                                        retrofit.client.Response arg1) {

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

//                        if (isFbLogin) {
//
//                            new AlertDialog.Builder(SignUpLoginActivity.this)
//                                    .setTitle("Post on Facebook")
//                                    .setMessage("Do you want to post it on Facebook wall?")
//                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            postToWall();
//                                        }
//                                    })
//                                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                        }
//                                    })
//                                    .setIcon(android.R.drawable.ic_dialog_alert)
//                                    .show();
//                        }


                        if (isInContext && instantAppointItemPos != -1) {
                            Intent intent = new Intent();
                            intent.putExtra("item_position", instantAppointItemPos);
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }

                        if (isInContext && !profileIdValue.equals("")) {
                            Intent intent = new Intent();
                            intent.putExtra("profileId", profileIdValue);
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        } else if (isInContext) {
                            setResult(Activity.RESULT_OK);
                            finish();
                        } else {
                            setResult(Activity.RESULT_OK);
                            finish();
                        }
                    }
                });

    }

//
//    private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
//    private boolean pendingPublishReauthorization = false;
//
//    public void postToWall() {
//        final Session session = Session.getActiveSession();
//        final ProgressDialog shareDialog = new ProgressDialog(SignUpLoginActivity.this);
//        shareDialog.setMessage("Please wait...");
//        shareDialog.setCancelable(false);
//        shareDialog.show();
//        if (session != null) {
//            List<String> permissions = session.getPermissions();
//            if (!isSubsetOf(PERMISSIONS, permissions)) {
//                if (shareDialog.isShowing()) {
//                    shareDialog.dismiss();
//                }
//                final AlertDialog.Builder alert = new AlertDialog.Builder(this);
//                alert.setMessage("please give permission by log in.");
//                alert.setPositiveButton("Login", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        pendingPublishReauthorization = true;
//                        Session.NewPermissionsRequest newPermissionsRequest = new Session
//                                .NewPermissionsRequest(SignUpLoginActivity.this, PERMISSIONS);
//                        session.requestNewPublishPermissions(newPermissionsRequest);
//                    }
//                });
//                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                        return;
//                    }
//                });
//                final AlertDialog alertDialog = alert.create();
//                alertDialog.show();
//                alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//
//                    @Override
//                    public boolean onKey(DialogInterface dialog, int keyCode,
//                                         KeyEvent event) {
//                        if (keyCode == KeyEvent.KEYCODE_BACK &&
//                                event.getAction() == KeyEvent.ACTION_UP &&
//                                !event.isCanceled()) {
//                            alertDialog.cancel();
//                            finish();
//                            return true;
//                        }
//                        return false;
//                    }
//
//                });
//
//
//                return;
//            }
//            if (shareDialog.isShowing()) {
//                shareDialog.setMessage("Sharing");
//
//            } else {
//                try {
//                    shareDialog.setMessage("Sharing...");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            Bundle postParams = new Bundle();
//
//
//            postParams.putString("name", "Jeevom");
//            postParams.putString("caption", "Jeevom");
//            postParams.putString("description", "Jeevom");
//            postParams.putString("link", "https://jeevom.com");
//
//            Request.Callback callback = new Request.Callback() {
//                public void onCompleted(Response response) {
//                    FacebookRequestError error = response.getError();
//                    if (error != null) {
//                        if (shareDialog.isShowing()) {
//                            shareDialog.dismiss();
//                        }
//
//                        showAlert("" + error.getErrorMessage());
//                    } else {
//                        if (shareDialog.isShowing()) {
//                            shareDialog.dismiss();
//
//                        }
//                        showAlert("Shared on Facebook");
//
//
//                    }
//                }
//            };
//
//            Request request = new Request(session, "1481597430/feed", postParams,
//                    HttpMethod.POST, callback);
//
//            RequestAsyncTask task = new RequestAsyncTask(request);
//            task.execute();
//
//        } else {
//            if (shareDialog.isShowing()) {
//                shareDialog.dismiss();
//            }
//        }
//    }
//
//
//    /**
//     * Checking Permissions for Facebook sharing
//     */
//    private boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
//        for (String string : subset) {
//            if (!superset.contains(string)) {
//                return false;
//            }
//        }
//        return true;
//    }

    protected void saveUserDetails(MembershipAuthenticateResponse data) {

        // save auth token
        if (!CommonCode.isNullOrEmpty(data.getData().getAuthToken())) {
            jeevomSession.setAuthToken(data.getData().getAuthToken());
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

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {

        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }

    }

    @Override
    public void onConnected(Bundle arg0) {
        mSignInClicked = false;

        // Get user's information
        getProfileInformation();

    }

    /**
     * Fetching user's information name, email, profile pic
     */
    private void getProfileInformation() {
        String firstName = null;
        String lastName = null;
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {

                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                social_id = currentPerson.getId();
                social_type = "google";
                social_gender = currentPerson.getGender();
                social_name = currentPerson.getDisplayName();
                social_email = Plus.AccountApi.getAccountName(mGoogleApiClient);
                String[] splittedName = social_name.split("\\s+");

                if (splittedName.length > 0) {
                    if (!CommonCode.isNullOrEmpty(splittedName[0]))
                        firstName = splittedName[0];
                    if (!CommonCode.isNullOrEmpty(splittedName[1]))
                        lastName = splittedName[1];
                }

                AddExternalUserRequest addObject = new AddExternalUserRequest(
                        firstName, lastName, social_email, "true", social_id,
                        social_type);
                addExternalUser(addObject);

                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                            .setResultCallback(new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status arg0) {
                                    Log.d("gg", "User access revoked!");
                                    mGoogleApiClient.connect();

                                }

                            });
                }

            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    // Google Code
    /* A helper method to resolve the current ConnectionResult error. */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (SendIntentException e) {
                mIntentInProgress = false;
                Toast.makeText(SignUpLoginActivity.this, "dd", Toast.LENGTH_SHORT).show();
                mGoogleApiClient.connect();
                Toast.makeText(SignUpLoginActivity.this, "ss", Toast.LENGTH_SHORT).show();


            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);

        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        } else if (requestCode == 1 && responseCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        } else if (requestCode == 200 && responseCode == RESULT_OK) {
            finish();
        } else if (requestCode == JeevOMUtil.SIGNUP_MAIN_REQUEST_CODE_FROM_SIGNIN_PAGE
                && responseCode == Activity.RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        } else if (requestCode == JeevOMUtil.SIGNUP_MAIN_REQUEST_CODE_FROM_SIGNIN_PAGE) {
            finish();
        } else {
            if (intent != null)
                Session.getActiveSession().onActivityResult(this, requestCode,
                        responseCode, intent);
        }
    }


    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
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


}