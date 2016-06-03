package com.schoolteacher.serviceRequest;

import android.app.Activity;
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
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.CellNumberCaptureDialog;
import com.schoolteacher.dialog.CellNumberVerificationDialog;
import com.schoolteacher.interfaces.CapturePhoneInterface;
import com.schoolteacher.interfaces.SendConsumerDetails;
import com.schoolteacher.library.main.PhoneEmailVerification;
import com.schoolteacher.library.main.SignUpLoginActivity;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.interfaces.MissedCallCodeListner;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.pojo.Address;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.service.SimplePostAsyncTask;
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
import com.schoolteacher.pojos.DocumentList;
import com.schoolteacher.pojos.DocumentObject;
import com.schoolteacher.pojos.Documents;
import com.schoolteacher.pojos.FamilyMembersResponse;
import com.schoolteacher.pojos.MemberAssociation;
import com.schoolteacher.pojos.OrderAddress;
import com.schoolteacher.pojos.ResponseApi;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceRequisitionResult;
import com.schoolteacher.pojos.UpdateConsumerResponse;
import com.schoolteacher.pojos.UploadDocumentObject;
import com.schoolteacher.service.Member;
import com.schoolteacher.service.ServiceRequisition;
import com.schoolteacher.service.UploadDocuments;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

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

public class PurchaseRequestStepTwo extends ActionBarActivity implements
        OnClickListener, OnItemSelectedListener, SendConsumerDetails,
        OnDateSetListener, MissedCallCodeListner, CapturePhoneInterface {
    UserCurrentLocationManager locationManager;

    Gson gson;


    public static final String DATEPICKER_TAG = "datepicker";
    private int current_year, currentMonth, currentDay;
    private String[] genders = {"Male", "Female", "Other"};
    private Calendar calendar;
    private long date = 0l;
    private List<String> titleSpinnerItems = new LinkedList<>();
    private List<MemberAssociation> memberAssociation;
    private List<String> memberSpinnerList = new LinkedList<>();
    private String referenceNo;
    private RelativeLayout image_name;
    private CircleImageView img_doctor;
    private TextView name_step_two;
    private ImageView date_time_selected_image, age_image;
    private Button btn_step_two_submit;
    private CallToActionRequest requestSendMessage;
    private String newDobValue, name_value, image_url;
    private RelativeLayout shared_info;
    private Toolbar toolbar;
    private TextView login_potential_message;
    private LinearLayout action_container;
    private RelativeLayout login_option;

    private JeevomSession session;
    private ValuesManager valuesManager;

    private String dateFormatted, consult_for_type, typeOfUser = null, gender,
            titleType = "Mr.";

    // new user
    private Spinner new_title_spinner, new_user_gender_spinner;
    private EditText new_first_name, new_last_name, new_phone, new_email, new_user_address_value;
    TextView new_dob_value;
    LinearLayout new_user_address_layout;
    ImageView new_dob_image;

    // logged in user views
    Spinner consult_for_value_spinner, gender_spinner;
    EditText name_consumer, age_consumer;
    CheckBox basic_info_checkbox, medical_info_checkbox, health_info_checkbox;
    LinearLayout address_layout;
    EditText address_value;

    DialogFragment newFragment;

    int forMemberId;
    boolean bookedForSelf;

    GlobalAlert globalAlert;
    private GlobelAlertWithFinish globalAlertFinish;

    String authToken;
    boolean isDirectRequest;
    List<DocumentObject> documentList;
    boolean isPotentialDateOfBirth = false, isLoggedInDateOfBirth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message_step_two);
        globalAlert = new GlobalAlert(this);
        globalAlertFinish = new GlobelAlertWithFinish(this);
        globalAlertFinish.setCancelable(false);
        documentList = new LinkedList<>();
        gson = new GsonBuilder().create();
        // set action bar
        setUpActionBar();
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        hideKeyboard();

        session = new JeevomSession(getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        valuesManager = new ValuesManager(getApplicationContext());

        Intent intent = getIntent();
        if (intent.hasExtra("isDirectRequest")) {
            isDirectRequest = intent.getBooleanExtra("isDirectRequest", false);
        } else {
            image_url = intent.getStringExtra("img_url");
            name_value = intent.getStringExtra("name");
        }
        requestSendMessage = (CallToActionRequest) intent
                .getSerializableExtra("requestObject");

        if (!session.getLoggedInStatus()) {
            documentList = (List<DocumentObject>) intent
                    .getSerializableExtra("documents");
        }
        getViewReferences();

        fillInitialDetails();

    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_step_two);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Provide Details");
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

        name_step_two = (TextView) findViewById(R.id.name_step_two);

    }

    private void fillInitialDetails() {

        // set name
        if (!CommonCode.isNullOrEmpty(name_value))
            name_step_two.setText(name_value);

        // set image
        Picasso.with(this).load(image_url).into(new Target() {

            @Override
            public void onPrepareLoad(Drawable arg0) {

            }

            @Override
            public void onBitmapLoaded(Bitmap arg0, LoadedFrom arg1) {
                setImage(arg0);
            }

            @Override
            public void onBitmapFailed(Drawable arg0) {

            }
        });
        if (isDirectRequest) {
            image_name.setVisibility(View.GONE);
        }
        // set image and name layout background
        image_name.setBackgroundColor(getResources().getColor(R.color.Gray));
        name_step_two.setTextColor(getResources().getColor(R.color.White));

        // set container if user is logged in or not
        if (!session.getLoggedInStatus()) {
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
                Integer.valueOf(session.getConsumerIds().get(
                        JeevomSession.JEEVOM_CONSUMER_ID)),
                String.valueOf(valuesManager.getVersion()),
                PurchaseRequestStepTwo.this, session);
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
        new_user_address_layout = (LinearLayout) view
                .findViewById(R.id.address_layout);
        new_user_address_layout.setVisibility(View.VISIBLE);
        new_user_address_value = (EditText) view
                .findViewById(R.id.address_value);
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
                PurchaseRequestStepTwo.this, R.layout.spinner_item,
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
                    PurchaseRequestStepTwo.this, R.layout.spinner_item,
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
                if (documentList.size() > 0) {
                    uploadImage(documentList);
                } else {
                    sendRequest();
                }

                break;
        }
    }

    private void uploadImage(List<DocumentObject> documentList) {

        for (DocumentObject documentObject : documentList) {
            if (session.getLoggedInStatus()) {
                documentObject
                        .setOwnerId(String.valueOf(session.getMemberId()));
            }

        }
        UploadDocumentObject object = new UploadDocumentObject();
        object.setDocumentList(documentList);
        RestAdapter uploadAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("upload"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        UploadDocuments uplaodService = uploadAdapter
                .create(UploadDocuments.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(this.getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        uplaodService.startUploadingDocuments(
                gson.toJson(locationManager.getUserLocation()).toString(), object,
                new Callback<Documents>() {

                    @Override
                    public void success(Documents arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            List<DocumentList> documentList2 = arg0.getData()
                                    .getDocumentList();

                            if (documentList2 != null
                                    && documentList2.size() > 0) {

                                // for (DocumentList documentObject :
                                // documentList2) {
                                // documentObject.setId(Integer
                                // .valueOf(session.getUserDetails()
                                // .get(SessionManager.KEY_ID)));
                                // }

                                StringBuilder stringValue = new StringBuilder();
                                for (int i = 0; i < documentList2.size(); i++) {

                                    if (i == 0) {
                                        stringValue.append(documentList2.get(i)
                                                .getId());
                                    } else {
                                        stringValue.append(", "
                                                + documentList2.get(i).getId());
                                    }
                                }

                                requestSendMessage.setDocumentIds(stringValue
                                        .toString());
                            } else {
                                requestSendMessage.setDocumentIds("");
                            }

                            sendRequest();
                        }

                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(getApplicationContext()))) {
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
                            Documents responseValue = gson.fromJson(json,
                                    Documents.class);
                            String code = responseValue.getStatus().getCode();
                            String message = responseValue.getStatus()
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
                });

    }

    private void sendRequest() {
        boolean isAllValid = true;
        if ("potential_member".equals(typeOfUser)) {

            if (CommonCode.isNullOrEmpty(new_first_name.getText().toString())) {
                isAllValid = false;
                Crouton.makeText(PurchaseRequestStepTwo.this,
                        "Please enter First Name", Style.ALERT).show();
                return;
            } else if (CommonCode.isNullOrEmpty(new_last_name.getText()
                    .toString())) {
                isAllValid = false;
                Crouton.makeText(PurchaseRequestStepTwo.this,
                        "Please enter Last Name", Style.ALERT).show();
                return;
            } else if (CommonCode.isNullOrEmpty(new_dob_value.getText()
                    .toString().trim())) {
                isAllValid = false;
                Crouton.makeText(PurchaseRequestStepTwo.this,
                        JeevOMUtil.MANDATORY_DATE_OF_BIRTH, Style.ALERT).show();
                return;
            } else if (!CommonCode.validateBirthDate(date)) {
                isAllValid = false;
                Crouton.makeText(PurchaseRequestStepTwo.this,
                        JeevOMUtil.VALID_DOB, Style.ALERT).show();
                return;
            }
            if (CommonCode.isNullOrEmpty(new_user_address_value.getText()
                    .toString().trim())) {
                Crouton.makeText(PurchaseRequestStepTwo.this,
                        "Please provide address", Style.ALERT).show();
                return;
            }
            // if
            // (CommonCode.isNullOrEmpty(new_email.getText().toString().trim())
            // && CommonCode.isNullOrEmpty(new_phone.getText().toString()
            // .trim())) {
            // Crouton.makeText(PurchaseRequestStepTwo.this,
            // "Please enter valid Phone No./ Email Id", Style.ALERT)
            // .show();
            // return;
            // }

            if (CommonCode.isNullOrEmpty(new_phone.getText().toString().trim())) {
                Crouton.makeText(PurchaseRequestStepTwo.this,
                        "Please enter valid Phone No.", Style.ALERT).show();
                return;
            } else {

                if (!CommonCode.isNullOrEmpty(new_phone.getText().toString())) {
                    if (!CommonCode.validatePhone(new_phone.getText()
                            .toString())) {
                        isAllValid = false;
                        Crouton.makeText(PurchaseRequestStepTwo.this,
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
                        Crouton.makeText(PurchaseRequestStepTwo.this,
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
                    Crouton.makeText(PurchaseRequestStepTwo.this,
                            "Please enter Name", Style.ALERT).show();
                    name_consumer.requestFocus();
                    return;
                }

                if (CommonCode.isNullOrEmpty(age_consumer.getText().toString()
                        .trim())) {
                    isAllValid = false;
                    Crouton.makeText(PurchaseRequestStepTwo.this,
                            "Please enter Age", Style.ALERT).show();
                    age_consumer.requestFocus();
                    return;
                }

                if (Integer.valueOf(age_consumer.getText().toString().trim()) > 150) {
                    isAllValid = false;
                    Crouton.makeText(PurchaseRequestStepTwo.this,
                            "Age should be less than 150", Style.ALERT).show();
                    age_consumer.requestFocus();
                    return;
                }

                if (CommonCode.isNullOrEmpty(address_value.getText().toString()
                        .trim())) {
                    Crouton.makeText(PurchaseRequestStepTwo.this,
                            "Please provide address", Style.ALERT).show();
                    address_value.requestFocus();
                    return;
                }
            } else if ("member".equals(typeOfUser)) {

                if (CommonCode.isNullOrEmpty(age_consumer.getText().toString())) {
                    isAllValid = false;
                    Crouton.makeText(PurchaseRequestStepTwo.this,
                            "Please provide date of birth", Style.ALERT).show();
                    return;
                }

                if (!CommonCode.validateBirthDate(date)) {
                    Crouton.makeText(PurchaseRequestStepTwo.this,
                            JeevOMUtil.VALID_DOB, Style.ALERT).show();
                    return;
                }
            }

            if (CommonCode.isNullOrEmpty(address_value.getText().toString()
                    .trim())) {
                Crouton.makeText(PurchaseRequestStepTwo.this,
                        "Please provide address", Style.ALERT).show();
                address_value.requestFocus();
                return;
            }

        }

        if (isAllValid) {
            if (!CommonCode.isNullOrEmpty(session.getAge())) {
                if (Integer.valueOf(session.getAge()) <= 0
                        || session.getGender().equals("-")) {
                    if (consult_for_type.equals("Self"))
                        updateConsumerProfile();
                    else
                        makeCall();
                } else {
                    makeCall();
                }
            } else
                makeCall();
        }
    }

    private void makeCall() {

        // if user is potential member
        if (typeOfUser.equals("potential_member")) {
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
            OrderAddress orderAddress = new OrderAddress();
            orderAddress.setAddressLine1(new_user_address_value.getText()
                    .toString().trim());

            requestSendMessage.setDeliveryRequestedAt(orderAddress);
        } else {
            // remove all values of visitor
            requestSendMessage.setIsBasicDetailsShared(false);

            requestSendMessage.setIsRequestedByVisitor(false);
            requestSendMessage.setVisitorTitle(null);
            requestSendMessage.setVisitorFirstName(null);
            requestSendMessage.setVisitorLastName(null);
            requestSendMessage.setVisitorGender(null);
            requestSendMessage.setVisitorDateOfBirth(null);
            requestSendMessage.setVisitorEmail(null);
            requestSendMessage.setVisitorCellNumber(null);
            requestSendMessage.setVisitorGender(null);

            // end


            requestSendMessage.setByMemberId(String.valueOf(session
                    .getMemberId()));
            if (consult_for_type.equalsIgnoreCase("Self")) {
                requestSendMessage.setForMemberId(String.valueOf(session
                        .getMemberId()));
                requestSendMessage.setForAge(session.getAge());
                requestSendMessage.setForGender(session.getGender());
                requestSendMessage.setForName(session.getName());

                requestSendMessage.setIsBasicDetailsShared(basic_info_checkbox
                        .isChecked());
                requestSendMessage.setIsMedicalInfoShared(medical_info_checkbox
                        .isChecked());
                requestSendMessage.setIsHealthTrackShared(health_info_checkbox
                        .isChecked());

                OrderAddress orderAddress = new OrderAddress();
                orderAddress.setAddressLine1(address_value.getText().toString()
                        .trim());

                requestSendMessage.setDeliveryRequestedAt(orderAddress);
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
                    OrderAddress orderAddress = new OrderAddress();
                    orderAddress.setAddressLine1(address_value.getText()
                            .toString().trim());

                    requestSendMessage.setDeliveryRequestedAt(orderAddress);
                } else {
                    OrderAddress orderAddress = new OrderAddress();
                    orderAddress.setAddressLine1(address_value.getText()
                            .toString().trim());

                    requestSendMessage.setDeliveryRequestedAt(orderAddress);
                    requestSendMessage.setIsBasicDetailsShared(true);
                }
                requestSendMessage.setForAge(age_consumer.getText().toString());
                requestSendMessage.setForGender(gender);
                requestSendMessage.setForName(name_consumer.getText()
                        .toString().trim());
            }
        }

        if (session.getLoggedInStatus()) {
            if (!CommonCode.isNullOrEmpty(session.getCellNumber())) {
                if (session.getUserPhoneVerifyStatus()) {
                    // cell no is available and verified -- make service call
                    makeServiceCall(requestSendMessage);
                } else {
                    // cell no is available but not verified
                    Toast.makeText(PurchaseRequestStepTwo.this,
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
            makeServiceCall(requestSendMessage);
        }
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
                locationManager.getUserLocation(), authToken, requestSendMessage,

                new Callback<ServiceRequisitionResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(PurchaseRequestStepTwo.this))) {
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

                            // if (requestSendMessage.isIsRequestedByVisitor()) {
                            // goToVerifyScreen();
                            // } else {
                            showAlertFinish("Thank you for Submitting your request."
                                    + "\n" + "Your Reference Number is: " + referenceNo);
                            // }

                        }
                    }
                });
    }

    private void goToVerifyScreen() {

        if (!(CommonCode.isNullOrEmpty(requestSendMessage
                .getVisitorCellNumber()))
                && !(CommonCode.isNullOrEmpty(requestSendMessage
                .getVisitorEmail()))) {
            SimplePostAsyncTask codeAsyncTask = new SimplePostAsyncTask(this);
            codeAsyncTask.execute(JeevOMUtil.baseUrl
                    + JeevOMUtil.missedCallFirst
                    + requestSendMessage.getVisitorCellNumber()
                    + JeevOMUtil.missedCallSecond);

        } else if (!(CommonCode.isNullOrEmpty(requestSendMessage
                .getVisitorEmail()))) {
            Intent phoneEmailIntent = new Intent(PurchaseRequestStepTwo.this,
                    PhoneEmailVerification.class);
            phoneEmailIntent.putExtra("type", "email");
            phoneEmailIntent.putExtra("isSignUpTimeVerification", true);
            phoneEmailIntent.putExtra("email",
                    requestSendMessage.getVisitorEmail());
            startActivityForResult(phoneEmailIntent,
                    JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE);

        } else if (!(CommonCode.isNullOrEmpty(requestSendMessage
                .getVisitorCellNumber()))) {
            SimplePostAsyncTask codeAsyncTask = new SimplePostAsyncTask(this);
            codeAsyncTask.execute(JeevOMUtil.baseUrl
                    + JeevOMUtil.missedCallFirst
                    + requestSendMessage.getVisitorCellNumber()
                    + JeevOMUtil.missedCallSecond);
        }

    }

    private void callLogIn() {
        Intent signInInContext = new Intent(PurchaseRequestStepTwo.this,
                SignUpLoginActivity.class);
        signInInContext.putExtra("isInContext", true);
        signInInContext.putExtra("isSignupShown", true);
        startActivityForResult(signInInContext, 1);
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        if (arg0 == JeevOMUtil.PHONE_AND_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_OK) {
            setResult(RESULT_OK);
            showAlertFinish("Thank you for Submitting your request." + "\n"
                    + "Your Reference Number is: " + referenceNo);
        } else if (arg0 == JeevOMUtil.PHONE_AND_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_CANCELED) {
            Crouton.makeText(PurchaseRequestStepTwo.this, "Please try again",
                    Style.ALERT).show();
        }

        if (arg0 == JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE) {
            updateServiceRequisitionStatus(referenceNo);

        } else if (arg0 == JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_CANCELED) {
            Crouton.makeText(PurchaseRequestStepTwo.this, "Please try again",
                    Style.ALERT).show();
        } else {
            if (arg0 == 1 && arg1 == RESULT_OK) {
                getConsumerDetails();
            }

        }

    }

    private void updateServiceRequisitionStatus(String referenceNo2) {
        RestAdapter updateStatusAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .setLog(new AndroidLog("update")).setLogLevel(LogLevel.FULL)
                .build();
        ServiceRequisition update_service_req_interface = updateStatusAdapter
                .create(ServiceRequisition.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);

        update_service_req_interface.updateServiceRequisitionStatus(
                locationManager.getUserLocation(), "Basic "
                        + session.getAuthToken(), referenceNo2, "Active",
                new Callback<ResponseApi<String>>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();
                        // loadToast.error();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(PurchaseRequestStepTwo.this))) {
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
                            ResponseApi<String> searchResultsResponse = gson
                                    .fromJson(json, ResponseApi.class);
                            String code = searchResultsResponse.getStatus()
                                    .getCode();
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
                    public void success(ResponseApi<String> arg0, Response arg1) {
                        setResult(RESULT_OK);
                        showAlertFinish("Thank you for Submitting your request."
                                + "\n"
                                + "Your Reference Number is: "
                                + referenceNo);
                    }
                });
    }

    private void showDatePickerDialog() {
        final DatePickerDialog datePickerDialog;
        calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.YEAR);
        int year = 0, month, day;
        if (date == 0l) {
            datePickerDialog = DatePickerDialog.newInstance(
                    PurchaseRequestStepTwo.this, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), false);
        } else {
            calendar.setTimeInMillis(date);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = DatePickerDialog.newInstance(
                    PurchaseRequestStepTwo.this, year, month, day, false);
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

                // set address of user
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
                        address_value.setText(CommonCode.trimCommas(
                                addressValue.toString()).trim());
                }

                shared_info.setVisibility(View.GONE);
                gender_spinner.getSelectedView();
                gender_spinner.setEnabled(false);
                name_consumer.setText(CommonCode.makeCallBackEmailName(session
                        .getName()));
                if (Integer.valueOf(session.getAge()) > 0) {
                    age_consumer.setText(session.getAge());
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

                if (CommonCode
                        .isNullOrEmpty(session.getName())) {
                    name_consumer.setEnabled(true);
                    name_consumer.setFocusable(true);
                    name_consumer.setFocusableInTouchMode(true);
                } else {
                    name_consumer.setEnabled(false);
                    name_consumer.setFocusable(false);
                    name_consumer.setFocusableInTouchMode(false);
                }
                consult_for_type = "Self";
                bookedForSelf = true;
                forMemberId = session.getMemberId();

                setGender(session.getGender());

            } else if (memberSpinnerList.get(position).equals("Other")) {

                address_value.setText("");
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
                shared_info.setVisibility(View.GONE);
                consult_for_type = "other";
                for (MemberAssociation obj : memberAssociation) {

                    address_value.setText("");

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
        address_layout = (LinearLayout) view.findViewById(R.id.address_layout);
        address_layout.setVisibility(View.VISIBLE);
        address_value = (EditText) view.findViewById(R.id.address_value);

        basic_info_checkbox.setChecked(true);
        shared_info = (RelativeLayout) findViewById(R.id.shared_info);

        age_image.setOnClickListener(this);

        if (Integer.valueOf(session.getAge()) <= 0) {
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
                PurchaseRequestStepTwo.this, R.layout.spinner_item,
                memberSpinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        consult_for_value_spinner.setAdapter(adapter);
    }

    private void initializeGenderSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                PurchaseRequestStepTwo.this, R.layout.spinner_item,
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
                        PurchaseRequestStepTwo.this,
                        PhoneEmailVerification.class);
                phoneEmailIntent.putExtra("uniqueRequestId", UniqueRequestId);
                phoneEmailIntent.putExtra("CallToVerifyNumberAsText",
                        CallToVerifyNumberAsText);

                phoneEmailIntent.putExtra("isSignUpTimeVerification", true);
                phoneEmailIntent.putExtra("type", "phone");
                phoneEmailIntent.putExtra("phone",
                        requestSendMessage.getVisitorCellNumber());
                phoneEmailIntent.putExtra("email",
                        requestSendMessage.getVisitorEmail());
                startActivityForResult(
                        phoneEmailIntent,
                        JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE);
            }
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

    private void updateConsumerProfile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ConsumerUpdateRequestModel finalUpdateObject = new ConsumerUpdateRequestModel();
        finalUpdateObject.setId(session.getConsumerIds().get(
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

                "Basic " + session.getAuthToken(), finalUpdateObject,
                new Callback<UpdateConsumerResponse>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(PurchaseRequestStepTwo.this))) {
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
                            session.setGender(gender);
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
                            session.setAge(String.valueOf(age));
                            makeCall();
                        }
                    }

                });
    }

}