package com.schoolteacher.appointment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.dialog.ServiceRequestCancelActionDialog;
import com.schoolteacher.dialog.ServiceRequestMessageActionDialog;
import com.schoolteacher.dialog.ServiceRequestPopUp;
import com.schoolteacher.interfaces.MessageCommunicator;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.main.PayNowWebViewActivity;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.AdditionalConsumerDetail;
import com.schoolteacher.pojos.Category;
import com.schoolteacher.pojos.ConfiguredAction;
import com.schoolteacher.pojos.ConfiguredStatu;
import com.schoolteacher.pojos.MemberPackageTransactionRequest;
import com.schoolteacher.pojos.PackageDetailsData;
import com.schoolteacher.pojos.PurchaseItems;
import com.schoolteacher.pojos.SRDocumentList;
import com.schoolteacher.pojos.Message;
import com.schoolteacher.pojos.ServiceRequestActionData;
import com.schoolteacher.pojos.ServiceRequestDetails;
import com.schoolteacher.pojos.ServiceRequestPaymentResponse;
import com.schoolteacher.pojos.ServiceRequisitionActionData;
import com.schoolteacher.pojos.Test;
import com.schoolteacher.service.ServiceRequisition;
import com.schoolteacher.util.JeevomUtilsClass;
import com.schoolteacher.util.Utils;
import com.schoolteacher.video.VideoActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.schoolteacher.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.ServiceRequestCancelActionDialog;
import com.schoolteacher.dialog.ServiceRequestMessageActionDialog;
import com.schoolteacher.dialog.ServiceRequestPopUp;
import com.schoolteacher.interfaces.MessageCommunicator;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.main.PayNowWebViewActivity;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.AdditionalConsumerDetail;
import com.schoolteacher.pojos.Category;
import com.schoolteacher.pojos.ConfiguredAction;
import com.schoolteacher.pojos.ConfiguredStatu;
import com.schoolteacher.pojos.MemberPackageTransactionRequest;
import com.schoolteacher.pojos.PackageDetailsData;
import com.schoolteacher.pojos.PurchaseItems;
import com.schoolteacher.pojos.SRDocumentList;
import com.schoolteacher.pojos.ServiceRequestActionData;
import com.schoolteacher.pojos.ServiceRequestDetails;
import com.schoolteacher.pojos.ServiceRequestPaymentResponse;
import com.schoolteacher.pojos.ServiceRequisitionActionData;
import com.schoolteacher.pojos.Test;
import com.schoolteacher.service.ServiceRequisition;
import com.schoolteacher.util.JeevomUtilsClass;
import com.schoolteacher.util.Utils;
import com.schoolteacher.video.VideoActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class ServiceRequestDetail extends ActionBarActivity implements
        MessageCommunicator {

    UserCurrentLocationManager locationManager;
    private ProgressDialogFragment progressDialogBox;
    ServiceRequisitionData serviceRequest;
    Toolbar toolbar_service_request;
    LinearLayout service_requests_container;
    JeevomSession session;
    String authToken = null;
    ServiceRequisitionActionData data;
    private TextView status_value, provider_name, provider_address,
            member_name, member_gender, member_address;
    private Button start_video_button;
    private static final int VIDEO_CODE = 2;
    private long millis;
    ServiceRequestDetails serviceRequestData;
    long millisecondsFromNow;
    List<ConfiguredAction> configuredActions;
    List<ConfiguredStatu> configuredStatus;
    String serviceConfigurationCode;
    int toStatusId;
    private ImageView image_view;
    private RelativeLayout messages, additional_details, medical_details;
    private LinearLayout documents_container;
    List<Message> messages_list;

    private TextView blood_group, specific_medical_condition,
            heriditary_condition, allergies, bmi, bmr, height, weight, msg,
            more_message, documents_list, message_list, subject_name,
            service_requistion_date;

    private RelativeLayout lab_test_details, provider_details;
    private TextView delivery_address_value, home_text_value, delivery_time;
    private LinearLayout lab_tests_container;

    private RelativeLayout purchase_details, sr_details;
    private TextView order_category_vlaue, medicine_delivery_time,
            general_items_value;
    private LinearLayout medicine_container;
    private MemberPackageTransactionRequest memberPkgeTransRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_request_detail);

        // application session object
        session = new JeevomSession(getApplicationContext());
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        // user identification Auth token
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }

        // get references of View Elements
        getViewElementsReferences();

        // getting reference of Container for action buttons
        service_requests_container = (LinearLayout) findViewById(R.id.service_requests_container);

        // object received in intent
        serviceRequest = (ServiceRequisitionData) getIntent()
                .getSerializableExtra("object");
        setUpActionBar();

    }

    private void dateToMillis(String timeStamp) {
        String input = timeStamp;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy h:mm a", Locale.ENGLISH)
                    .parse(input);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long milliseconds = date.getTime();
        millisecondsFromNow = milliseconds - (new Date()).getTime();
        if (millisecondsFromNow > 0) {
            startTimer(millisecondsFromNow);
        } else if (millisecondsFromNow
                + serviceRequest.getAppointmentInterval() * 60 * 1000 > 0) {
            millis = millisecondsFromNow
                    + serviceRequest.getAppointmentInterval() * 60 * 1000;
            startVideoCountDown(millis);

        }
        {
        }
    }

    private void getViewElementsReferences() {
        sr_details = (RelativeLayout) findViewById(R.id.sr_details);
        purchase_details = (RelativeLayout) findViewById(R.id.purchase_details);
        order_category_vlaue = (TextView) findViewById(R.id.order_category_value);
        medicine_delivery_time = (TextView) findViewById(R.id.medicine_delivery_time);
        medicine_container = (LinearLayout) findViewById(R.id.medicine_container);
        general_items_value = (TextView) findViewById(R.id.general_items_value);

        provider_details = (RelativeLayout) findViewById(R.id.provider_details);
        lab_test_details = (RelativeLayout) findViewById(R.id.lab_test_details);
        delivery_address_value = (TextView) findViewById(R.id.delivery_address_value);
        home_text_value = (TextView) findViewById(R.id.home_text_value);
        delivery_time = (TextView) findViewById(R.id.delivery_time);
        lab_tests_container = (LinearLayout) findViewById(R.id.lab_tests_container);

        blood_group = (TextView) findViewById(R.id.blood_group);
        specific_medical_condition = (TextView) findViewById(R.id.specific_medical_condition);
        heriditary_condition = (TextView) findViewById(R.id.heriditary_condition);
        allergies = (TextView) findViewById(R.id.allergies);
        medical_details = (RelativeLayout) findViewById(R.id.medical_details);

        bmi = (TextView) findViewById(R.id.bmi);
        bmr = (TextView) findViewById(R.id.bmr);
        height = (TextView) findViewById(R.id.height);
        weight = (TextView) findViewById(R.id.weight);
        additional_details = (RelativeLayout) findViewById(R.id.additional_details);

        documents_container = (LinearLayout) findViewById(R.id.documents_container);
        messages = (RelativeLayout) findViewById(R.id.messages);
        image_view = (ImageView) findViewById(R.id.image_view);
        msg = (TextView) findViewById(R.id.msg);
        more_message = (TextView) findViewById(R.id.more_message);

        documents_list = (TextView) findViewById(R.id.date_title);
        message_list = (TextView) findViewById(R.id.message_list);
        subject_name = (TextView) findViewById(R.id.subject_name);

        service_requistion_date = (TextView) findViewById(R.id.date_title);
        status_value = (TextView) findViewById(R.id.status_value);
        provider_name = (TextView) findViewById(R.id.provider_name);
        provider_address = (TextView) findViewById(R.id.provider_address);
        member_name = (TextView) findViewById(R.id.member_name);
        member_gender = (TextView) findViewById(R.id.member_gender);
        member_address = (TextView) findViewById(R.id.member_address);
        start_video_button = (Button) findViewById(R.id.start_video_button);
        start_video_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int cameraId = 0;
                cameraId = JeevomUtilsClass.findFrontFacingCamera();
                if (cameraId < 0) {
                    Toast.makeText(ServiceRequestDetail.this,
                            "No front facing camera found.", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Intent videoIntent = new Intent(ServiceRequestDetail.this,
                            VideoActivity.class);
                    videoIntent.putExtra("appointmentId",
                            String.valueOf(serviceRequest.getId()));
                    videoIntent.putExtra("millis", millis);
                    startActivityForResult(videoIntent, VIDEO_CODE);
                }
            }
        });
    }

    public void startTimer(long millisecondsFromNow2) {
        // mTimer.setVisibility(View.VISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(
                millisecondsFromNow2, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // payNow.setText("Your appointment will start in: " +
                // milliToDate(millisUntilFinished));
                // mRemaining_time.setText("Starts in, "
                // + milliToDate(millisUntilFinished));

            }

            @Override
            public void onFinish() {
                startVideoCountDown(serviceRequest.getAppointmentInterval() * 60 * 1000);

            }
        };
        countDownTimer.start();
    }

    private void startVideoCountDown(long millisecondsFromNow) {
        // mTimer.setVisibility(View.VISIBLE);
        start_video_button.setVisibility(View.VISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(millisecondsFromNow,
                1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // mTimer.setText("Remaining Time " + "\n"
                // + milliToMin(millisUntilFinished));
                // mRemaining_time.setText("Remaining Time, "
                // + milliToMin(millisUntilFinished));
                millis = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                // mRemaining_time.setVisibility(View.GONE);
                start_video_button.setVisibility(View.GONE);
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (serviceRequest.getServiceConfigurationCode().equals("SG3")
                || serviceRequest.getServiceConfigurationName().contains(
                "Video")) {
            if (serviceRequest.getStatusText().equalsIgnoreCase("Active")
                    && serviceRequest.getPaymentStatusText().equalsIgnoreCase(
                    "paid")) {
                dateToMillis(JeevomUtilsClass.formatDate(serviceRequest
                        .getAppointmentDate())
                        + " "
                        + JeevomUtilsClass.formatTime(serviceRequest
                        .getAppointmentTime()));
            }
        }
        // get service request details once activity enters in resume state
        getServiceRequestDetails();
    }

    private void getServiceRequestDetails() {

        RestAdapter serviceRequestAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("sr_details"))
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .build();
        progressDialogBox = ProgressDialogFragment.newInstance();
        progressDialogBox.show(getSupportFragmentManager(), "dialog");
        progressDialogBox.setCancelable(false);
        ServiceRequisition serviceRequestURL = serviceRequestAdapter
                .create(ServiceRequisition.class);
        serviceRequestURL.getServiceRequestDetail(locationManager.getUserLocation(),
                authToken,
                "consumer",
                session.getConsumerIds().get(
                        JeevomSession.JEEVOM_MEMBER_UNIQUE_ID),
                serviceRequest.getPublicId(), true,
                new Callback<ApiResponse<ServiceRequestDetails>>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialogBox.dismissAllowingStateLoss();
                    }

                    @Override
                    public void success(
                            ApiResponse<ServiceRequestDetails> arg0,
                            Response arg1) {
                        progressDialogBox.dismissAllowingStateLoss();
                        fillDetailsForServiceRequest(arg0);
                        serviceRequestData = arg0.getData();
                        serviceConfigurationCode = serviceRequestData
                                .getServiceConfigurationCode();

                        if (serviceRequestData.getSRStatusLog() != null) {
                            if (serviceRequestData.getSRStatusLog().size() > 0) {
                                toStatusId = serviceRequestData
                                        .getSRStatusLog().get(0)
                                        .getToStatusId();
                            }
                        }

                        if (arg0.getData().getSRStatusLog().size() > 0)
                            getActionsAgainstServiceRequisition(arg0.getData()
                                    .getSRStatusLog().get(0).getToStatusName());

                    }

                });

    }

    protected void fillDetailsForServiceRequest(
            ApiResponse<ServiceRequestDetails> data) {
        // fill details for purchase order


        // general_items_container
        if (data.getData().getServiceConfigurationCode().equals("SG8")) {

            if (data.getData().getToProfessionalId() > 0) {
                // set Provider name
                if (data.getData().getToProfessionalDetails() != null) {
                    provider_name.setText(data.getData()
                            .getToProfessionalDetails().getName());
                }

                // set Address
                StringBuilder addressValue = new StringBuilder();

                if (data.getData().getToProfessionalDetails().getAddress() != null) {
                    String line1 = data.getData().getToProfessionalDetails()
                            .getAddress().getLine1();
                    if (!CommonCode.isNullOrEmpty(line1)) {
                        addressValue.append(line1);
                    }
                    String area = data.getData().getToProfessionalDetails()
                            .getAddress().getArea();
                    if (!CommonCode.isNullOrEmpty(area)) {
                        addressValue.append("," + area);
                    }
                    String city = data.getData().getToProfessionalDetails()
                            .getAddress().getCity();
                    if (!CommonCode.isNullOrEmpty(city)) {
                        addressValue.append("," + city);
                    }
                    String state = data.getData().getToProfessionalDetails()
                            .getAddress().getState();

                    if (!CommonCode.isNullOrEmpty(state)) {
                        addressValue.append("," + state);
                    }
                    String zipCode = data.getData().getToProfessionalDetails()
                            .getAddress().getZipCode();
                    if (!CommonCode.isNullOrEmpty(zipCode)) {
                        addressValue.append("," + zipCode);
                    }

                    provider_address.setText(addressValue.toString());
                }
            } else if (data.getData().getToFacilityId() > 0) {

                // set Provider name
                if (data.getData().getToFacilityName() != null) {
                    provider_name.setText(data.getData().getToFacilityName());
                }

                // set Address
                StringBuilder addressValue = new StringBuilder();

                if (data.getData().getToFacilityDetails().getAddress() != null) {
                    String line1 = data.getData().getToFacilityDetails()
                            .getAddress().getAddressLine1();
                    if (!CommonCode.isNullOrEmpty(line1)) {
                        addressValue.append(line1);
                    }
                    String area = data.getData().getToFacilityDetails()
                            .getAddress().getArea();
                    if (!CommonCode.isNullOrEmpty(area)) {
                        addressValue.append("," + area);
                    }
                    String city = data.getData().getToFacilityDetails()
                            .getAddress().getCity();
                    if (!CommonCode.isNullOrEmpty(city)) {
                        addressValue.append("," + city);
                    }
                    String state = data.getData().getToFacilityDetails()
                            .getAddress().getState();

                    if (!CommonCode.isNullOrEmpty(state)) {
                        addressValue.append("," + state);
                    }
                    String zipCode = data.getData().getToFacilityDetails()
                            .getAddress().getZipCode();
                    if (!CommonCode.isNullOrEmpty(zipCode)) {
                        addressValue.append("," + zipCode);
                    }

                    provider_address.setText(addressValue.toString());
                }
            } else {
                provider_details.setVisibility(View.GONE);
            }

            sr_details.setVisibility(View.GONE);
            if (data.getData().getPurchaseRequest() != null) {
                if (!CommonCode.isNullOrEmpty(data.getData()
                        .getPurchaseRequest().getTimePreference())) {
                    medicine_delivery_time.setText(data.getData()
                            .getPurchaseRequest().getTimePreference());
                }

                if (data.getData().getPurchaseRequest().getCategories() != null) {
                    List<Category> categories = data.getData()
                            .getPurchaseRequest().getCategories();
                    if (categories.size() > 0) {
                        Category category = categories.get(0);
                        if (!CommonCode.isNullOrEmpty(category.getName())) {
                            order_category_vlaue.setText(category.getName());
                        }
                    }
                }
                // show medicines list
                List<PurchaseItems> items = data.getData().getPurchaseRequest()
                        .getItems();
                if (items != null && items.size() > 0) {
                    if (medicine_container.getChildCount() > 0)
                        medicine_container.removeAllViews();

                    for (int i = 0; i < items.size(); i++) {
                        LayoutInflater inflator_row = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                        View lab_test_view = inflator_row.inflate(
                                R.layout.lab_test_row, null);
                        TextView test_counter = (TextView) lab_test_view
                                .findViewById(R.id.test_counter);
                        TextView test_name = (TextView) lab_test_view
                                .findViewById(R.id.test_name);

                        test_counter.setText(String.valueOf(i + 1));
                        test_name
                                .setText(items.get(i).getName()
                                        + " - Quantity : "
                                        + items.get(i).getQuantity());

                        medicine_container.addView(lab_test_view);

                    }

                }

                if (!CommonCode.isNullOrEmpty(data.getData()
                        .getPurchaseRequest()
                        .getGeneralHealthCareRequirements())) {
                    general_items_value.setText(data.getData()
                            .getPurchaseRequest()
                            .getGeneralHealthCareRequirements());
                }
            } else
                purchase_details.setVisibility(View.GONE);
        } else {
            purchase_details.setVisibility(View.GONE);
        }
        // fill details if service request is a lab test request

        if (data.getData().getServiceConfigurationCode().equals("SG9")) {
            if (data.getData().getToProfessionalId() > 0) {
                // set Provider name
                if (data.getData().getToProfessionalDetails() != null) {
                    provider_name.setText(data.getData()
                            .getToProfessionalDetails().getName());
                }

                // set Address
                StringBuilder addressValue = new StringBuilder();

                if (data.getData().getToProfessionalDetails().getAddress() != null) {
                    String line1 = data.getData().getToProfessionalDetails()
                            .getAddress().getLine1();
                    if (!CommonCode.isNullOrEmpty(line1)) {
                        addressValue.append(line1);
                    }
                    String area = data.getData().getToProfessionalDetails()
                            .getAddress().getArea();
                    if (!CommonCode.isNullOrEmpty(area)) {
                        addressValue.append("," + area);
                    }
                    String city = data.getData().getToProfessionalDetails()
                            .getAddress().getCity();
                    if (!CommonCode.isNullOrEmpty(city)) {
                        addressValue.append("," + city);
                    }
                    String state = data.getData().getToProfessionalDetails()
                            .getAddress().getState();

                    if (!CommonCode.isNullOrEmpty(state)) {
                        addressValue.append("," + state);
                    }
                    String zipCode = data.getData().getToProfessionalDetails()
                            .getAddress().getZipCode();
                    if (!CommonCode.isNullOrEmpty(zipCode)) {
                        addressValue.append("," + zipCode);
                    }

                    provider_address.setText(addressValue.toString());
                }
            } else if (data.getData().getToFacilityId() > 0) {

                // set Provider name
                if (data.getData().getToFacilityName() != null) {
                    provider_name.setText(data.getData().getToFacilityName());
                }

                // set Address
                StringBuilder addressValue = new StringBuilder();

                if (data.getData().getToFacilityDetails().getAddress() != null) {
                    String line1 = data.getData().getToFacilityDetails()
                            .getAddress().getAddressLine1();
                    if (!CommonCode.isNullOrEmpty(line1)) {
                        addressValue.append(line1);
                    }
                    String area = data.getData().getToFacilityDetails()
                            .getAddress().getArea();
                    if (!CommonCode.isNullOrEmpty(area)) {
                        addressValue.append("," + area);
                    }
                    String city = data.getData().getToFacilityDetails()
                            .getAddress().getCity();
                    if (!CommonCode.isNullOrEmpty(city)) {
                        addressValue.append("," + city);
                    }
                    String state = data.getData().getToFacilityDetails()
                            .getAddress().getState();

                    if (!CommonCode.isNullOrEmpty(state)) {
                        addressValue.append("," + state);
                    }
                    String zipCode = data.getData().getToFacilityDetails()
                            .getAddress().getZipCode();
                    if (!CommonCode.isNullOrEmpty(zipCode)) {
                        addressValue.append("," + zipCode);
                    }

                    provider_address.setText(addressValue.toString());
                }
            } else {
                provider_details.setVisibility(View.GONE);
            }
            sr_details.setVisibility(View.GONE);
            if (data.getData().getLabTests() != null) {
                if (!CommonCode.isNullOrEmpty(data.getData().getLabTests()
                        .getIsTestAtHome())) {
                    home_text_value.setText(data.getData().getLabTests()
                            .getIsTestAtHome());
                } else {
                    home_text_value.setText("N/A");
                }

                if (!CommonCode.isNullOrEmpty(data.getData().getLabTests()
                        .getTimePreference())) {
                    delivery_time.setText(data.getData().getLabTests()
                            .getTimePreference());
                }

                if (data.getData().getLabTests().getDeliveryAddress() != null) {

                    StringBuilder deliveryAddress = new StringBuilder();
                    String line1 = data.getData().getLabTests()
                            .getDeliveryAddress().getLine1();
                    if (!CommonCode.isNullOrEmpty(line1)) {
                        deliveryAddress.append(line1);
                    }
                    String area = data.getData().getLabTests()
                            .getDeliveryAddress().getArea();
                    if (!CommonCode.isNullOrEmpty(area)) {
                        deliveryAddress.append("," + area);
                    }
                    String city = data.getData().getLabTests()
                            .getDeliveryAddress().getCity();
                    if (!CommonCode.isNullOrEmpty(city)) {
                        deliveryAddress.append("," + city);
                    }
                    String state = data.getData().getLabTests()
                            .getDeliveryAddress().getState();

                    if (!CommonCode.isNullOrEmpty(state)) {
                        deliveryAddress.append("," + state);
                    }
                    String zipCode = data.getData().getLabTests()
                            .getDeliveryAddress().getZipCode();
                    if (!CommonCode.isNullOrEmpty(zipCode)) {
                        deliveryAddress.append("," + zipCode);
                    }

                    delivery_address_value.setText(deliveryAddress.toString());
                } else {
                    delivery_address_value.setText("N/A");
                }

                // show tests list
                List<Test> test = data.getData().getLabTests().getTest();

                if (lab_tests_container.getChildCount() > 0)
                    lab_tests_container.removeAllViews();

                for (int i = 0; i < test.size(); i++) {
                    LayoutInflater inflator_row = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View lab_test_view = inflator_row.inflate(
                            R.layout.lab_test_row, null);
                    TextView test_counter = (TextView) lab_test_view
                            .findViewById(R.id.test_counter);
                    TextView test_name = (TextView) lab_test_view
                            .findViewById(R.id.test_name);

                    test_counter.setText(String.valueOf(i + 1));
                    test_name.setText(test.get(i).getName());

                    lab_tests_container.addView(lab_test_view);
                }
            }

        } else {
            lab_test_details.setVisibility(View.GONE);
        }

        // fill medical details
        AdditionalConsumerDetail additionalConsumerDetail = data.getData()
                .getAdditionalConsumerDetail();

        if (data.getData().isIsMedicalInfoShared()) {
            if (!CommonCode.isNullOrEmpty(additionalConsumerDetail
                    .getBloodGroup())) {
                blood_group.setText("Blood Group : "
                        + additionalConsumerDetail.getBloodGroup());
            } else if (!CommonCode.isNullOrEmpty(additionalConsumerDetail
                    .getSpecificMedicalCondition())) {
                specific_medical_condition
                        .setText("Specific Medical Condition : "
                                + additionalConsumerDetail
                                .getSpecificMedicalCondition());
            } else if (!CommonCode.isNullOrEmpty(additionalConsumerDetail
                    .getHeredityMedicalCondition())) {
                heriditary_condition.setText("Hereditary Medical Condition : "
                        + additionalConsumerDetail
                        .getHeredityMedicalCondition());
            } else if (!CommonCode.isNullOrEmpty(additionalConsumerDetail
                    .getAllergies())) {
                allergies.setText("Allergies : "
                        + additionalConsumerDetail.getAllergies());
            } else {
                medical_details.setVisibility(View.GONE);
            }
        } else {
            medical_details.setVisibility(View.GONE);
        }

        // fill shared details if shared
        if (data.getData().isIsHealthTrackShared()) {

            if (additionalConsumerDetail != null) {

                if (!CommonCode.isNullOrEmpty(additionalConsumerDetail
                        .getWeight())) {
                    weight.setText("Weight : "
                            + additionalConsumerDetail.getWeight());
                }
                if (!CommonCode.isNullOrEmpty(additionalConsumerDetail
                        .getHeight())) {
                    height.setText("Height : "
                            + additionalConsumerDetail.getHeight());
                }
                if (!CommonCode
                        .isNullOrEmpty(additionalConsumerDetail.getBMI())) {
                    bmi.setText("BMI : " + additionalConsumerDetail.getBMI());
                }
                if (!CommonCode
                        .isNullOrEmpty(additionalConsumerDetail.getBMR())) {
                    bmr.setText("BMR : " + additionalConsumerDetail.getBMR());
                }
            }

        } else {
            additional_details.setVisibility(View.GONE);
        }

        // set service request details
        if (documents_container.getChildCount() > 0) {

            if (documents_container.getChildCount() > 1) {
                for (int i = 1; i < documents_container.getChildCount(); i++) {
                    documents_container.removeViewAt(i);

                }
            }

        }

        if (data.getData().getSRDocumentList().size() > 0) {
            List<SRDocumentList> srDocumentList = data.getData()
                    .getSRDocumentList();
            for (SRDocumentList srDocumentList2 : srDocumentList) {
                LayoutInflater inflator_row = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View document_view = inflator_row.inflate(
                        R.layout.documents_list, null);
                TextView document_name = (TextView) document_view
                        .findViewById(R.id.document_name);
                TextView name = (TextView) document_view
                        .findViewById(R.id.name);
                document_name.setText(srDocumentList2.getName());
                name.setText("By : " + srDocumentList2.getCreatedByName());

                documents_container.addView(document_view);
            }

        } else {
            documents_container.setVisibility(View.GONE);
        }

        if (data.getData().getMessages() != null) {
            if (data.getData().getMessages().size() > 0) {
                messages_list = data.getData().getMessages();
                message_list.setText("Message : "
                        + data.getData().getMessages().get(0).getMessage());
            } else {
                messages.setVisibility(View.GONE);
            }

            // show all messages
            int size = data.getData().getMessages().size();

            if (size > 1)
                more_message.setText((size - 1) + " more messages");
            more_message.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent messages_list_intent = new Intent(
                            ServiceRequestDetail.this, MessagesListClass.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("messages_list",
                            (Serializable) messages_list);
                    messages_list_intent.putExtras(bundle);
                    startActivity(messages_list_intent);
                }
            });

            if (data.getData().getMessages().size() > 0) {
                if (data.getData().getMessages().get(0).getFromMemberId() > 0) {
                    if (!CommonCode.isNullOrEmpty(data.getData().getMessages()
                            .get(0).getFromConsumerPhoto()))
                        Picasso.with(this)
                                .load(data.getData().getMessages().get(0)
                                        .getFromConsumerPhoto())
                                .placeholder(R.drawable.jeevom_back)
                                .error(R.drawable.jeevom_back).into(image_view);

                } else if (data.getData().getMessages().get(0)
                        .getFromProfessionalId() > 0) {
                    if (!CommonCode.isNullOrEmpty(data.getData().getMessages()
                            .get(0).getFromProfessionalPhoto()))
                        Picasso.with(this)
                                .load(data.getData().getMessages().get(0)
                                        .getFromProfessionalPhoto())
                                .placeholder(R.drawable.jeevom_back)
                                .error(R.drawable.jeevom_back).into(image_view);

                } else if (data.getData().getMessages().get(0)
                        .getFromFacilityId() > 0) {
                    if (!CommonCode.isNullOrEmpty(data.getData().getMessages()
                            .get(0).getFromFacilityPhoto()))
                        Picasso.with(this)
                                .load(data.getData().getMessages().get(0)
                                        .getFromFacilityPhoto())
                                .placeholder(R.drawable.jeevom_back)
                                .error(R.drawable.jeevom_back).into(image_view);

                }
                msg.setText(data.getData().getMessages().get(0).getMessage());
                if (!CommonCode.isNullOrEmpty(data.getData().getMessages()
                        .get(0).getSubject()))
                    subject_name.setText("Subject : "
                            + data.getData().getMessages().get(0).getSubject());
                else
                    subject_name.setVisibility(View.GONE);
            }

        }

        // set date of Service Requisition
        int[] formatDate = null;

        if (!CommonCode.isNullOrEmpty(data.getData().getAppointmentDate())) {
            try {
                formatDate = CommonCode.formatDT(data.getData()
                        .getAppointmentDate());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            service_requistion_date.setText(CommonCode.monthYear(formatDate[0],
                    formatDate[1], formatDate[2]));
        }

        // set Status
        if (data.getData().getSRStatusLog().size() > 0)
            status_value.setText(data.getData().getSRStatusLog().get(0)
                    .getToStatusName());

        if (data.getData().getToProfessionalId() > 0) {
            // set Provider name
            if (data.getData().getToProfessionalDetails() != null) {
                provider_name.setText(data.getData().getToProfessionalDetails()
                        .getName());
            }

            // set Address
            StringBuilder addressValue = new StringBuilder();

            if (data.getData().getToProfessionalDetails().getAddress() != null) {
                String line1 = data.getData().getToProfessionalDetails()
                        .getAddress().getLine1();
                if (!CommonCode.isNullOrEmpty(line1)) {
                    addressValue.append(line1);
                }
                String area = data.getData().getToProfessionalDetails()
                        .getAddress().getArea();
                if (!CommonCode.isNullOrEmpty(area)) {
                    addressValue.append("," + area);
                }
                String city = data.getData().getToProfessionalDetails()
                        .getAddress().getCity();
                if (!CommonCode.isNullOrEmpty(city)) {
                    addressValue.append("," + city);
                }
                String state = data.getData().getToProfessionalDetails()
                        .getAddress().getState();

                if (!CommonCode.isNullOrEmpty(state)) {
                    addressValue.append("," + state);
                }
                String zipCode = data.getData().getToProfessionalDetails()
                        .getAddress().getZipCode();
                if (!CommonCode.isNullOrEmpty(zipCode)) {
                    addressValue.append("," + zipCode);
                }

                provider_address.setText(addressValue.toString());
            }
        } else if (data.getData().getToFacilityId() > 0) {

            // set Provider name
            if (data.getData().getToFacilityName() != null) {
                provider_name.setText(data.getData().getToFacilityName());
            }

            // set Address
            StringBuilder addressValue = new StringBuilder();

            if (data.getData().getToFacilityDetails().getAddress() != null) {
                String line1 = data.getData().getToFacilityDetails()
                        .getAddress().getAddressLine1();
                if (!CommonCode.isNullOrEmpty(line1)) {
                    addressValue.append(line1);
                }
                String area = data.getData().getToFacilityDetails()
                        .getAddress().getArea();
                if (!CommonCode.isNullOrEmpty(area)) {
                    addressValue.append("," + area);
                }
                String city = data.getData().getToFacilityDetails()
                        .getAddress().getCity();
                if (!CommonCode.isNullOrEmpty(city)) {
                    addressValue.append("," + city);
                }
                String state = data.getData().getToFacilityDetails()
                        .getAddress().getState();

                if (!CommonCode.isNullOrEmpty(state)) {
                    addressValue.append("," + state);
                }
                String zipCode = data.getData().getToFacilityDetails()
                        .getAddress().getZipCode();
                if (!CommonCode.isNullOrEmpty(zipCode)) {
                    addressValue.append("," + zipCode);
                }

                provider_address.setText(addressValue.toString());
            }
        }

        // set Name of member
        long yearDifferenceBetweenDates = 0;
        if (!CommonCode.isNullOrEmpty(data.getData().getByMemberDateOfBirth())) {
            yearDifferenceBetweenDates = CommonCode
                    .getYearDifferenceBetweenDates(CommonCode
                            .getCurrentTimeStamp(), data.getData()
                            .getByMemberDateOfBirth());
        }

        if (data.getData().getForMemberId() > 0) {
            StringBuilder nameString = new StringBuilder();
            if (!CommonCode.isNullOrEmpty(data.getData().getForMemberTitle()))
                nameString.append(data.getData().getForMemberTitle() + " ");
            if (!CommonCode.isNullOrEmpty(data.getData().getForMemberTitle()))
                nameString.append(data.getData().getForMemberFirstName() + " ");
            if (!CommonCode
                    .isNullOrEmpty(data.getData().getForMemberLastName()))
                nameString.append(data.getData().getForMemberLastName());
            member_name.setText(nameString.toString() + " " + "("
                    + yearDifferenceBetweenDates + " years old )");

            // set member gender
            member_gender.setText(data.getData().getForMemberGender());

            // set member address
            StringBuilder memberAddressValue = new StringBuilder();
            if (data.getData().getForMemberAddress() != null) {
                String line1 = data.getData().getForMemberAddress().getLine1();
                if (!CommonCode.isNullOrEmpty(line1)) {
                    memberAddressValue.append(line1);
                }
                String area = data.getData().getForMemberAddress().getArea();
                if (!CommonCode.isNullOrEmpty(area)) {
                    memberAddressValue.append("," + area);
                }
                String city = data.getData().getForMemberAddress().getCity();
                if (!CommonCode.isNullOrEmpty(city)) {
                    memberAddressValue.append("," + city);
                }
                String state = data.getData().getForMemberAddress().getState();

                if (!CommonCode.isNullOrEmpty(state)) {
                    memberAddressValue.append("," + state);
                }
                String zipCode = data.getData().getForMemberAddress()
                        .getZipCode();
                if (!CommonCode.isNullOrEmpty(zipCode)) {
                    memberAddressValue.append("," + zipCode);
                }

                member_address.setText(memberAddressValue.toString());

            }
        } else {
            if (!CommonCode.isNullOrEmpty(data.getData().getForName())) {
                member_name.setText(data.getData().getForName() + " " + "("
                        + data.getData().getForAge() + " years old )");

                // set member gender
                if (!CommonCode.isNullOrEmpty(data.getData().getForGender())) {
                    member_gender.setText(data.getData().getForGender());
                }

            }
        }
        if (data.getData().getServiceConfigurationCode().equals("SG8")) {

            if (data.getData().getPurchaseRequest() != null) {

                if (data.getData().getPurchaseRequest().getDeliveryAddress() != null) {

                    StringBuilder deliveryAddressValue = new StringBuilder();
                    String addLine1 = data.getData().getPurchaseRequest()
                            .getDeliveryAddress().getLine1();
                    if (!CommonCode.isNullOrEmpty(addLine1)) {
                        deliveryAddressValue.append(addLine1);
                    }
                    String addArea = data.getData().getPurchaseRequest()
                            .getDeliveryAddress().getArea();
                    if (!CommonCode.isNullOrEmpty(addArea)) {
                        deliveryAddressValue.append("," + addArea);
                    }
                    String addCity = data.getData().getPurchaseRequest()
                            .getDeliveryAddress().getCity();
                    if (!CommonCode.isNullOrEmpty(addCity)) {
                        deliveryAddressValue.append("," + addCity);
                    }
                    String addState = data.getData().getPurchaseRequest()
                            .getDeliveryAddress().getState();

                    if (!CommonCode.isNullOrEmpty(addState)) {
                        deliveryAddressValue.append("," + addState);
                    }
                    String addZipCode = data.getData().getPurchaseRequest()
                            .getDeliveryAddress().getZipCode();
                    if (!CommonCode.isNullOrEmpty(addZipCode)) {
                        deliveryAddressValue.append("," + addZipCode);
                    }

                    member_address.setText(deliveryAddressValue.toString());
                }
            }

        }
        if (data.getData().getServiceConfigurationCode().equals("SG9")) {

            if (data.getData().getLabTests() != null) {

                if (data.getData().getLabTests().getDeliveryAddress() != null) {

                    StringBuilder deliveryAddressValue = new StringBuilder();
                    String addLine1 = data.getData().getLabTests()
                            .getDeliveryAddress().getLine1();
                    if (!CommonCode.isNullOrEmpty(addLine1)) {
                        deliveryAddressValue.append(addLine1);
                    }
                    String addArea = data.getData().getLabTests()
                            .getDeliveryAddress().getArea();
                    if (!CommonCode.isNullOrEmpty(addArea)) {
                        deliveryAddressValue.append("," + addArea);
                    }
                    String addCity = data.getData().getLabTests()
                            .getDeliveryAddress().getCity();
                    if (!CommonCode.isNullOrEmpty(addCity)) {
                        deliveryAddressValue.append("," + addCity);
                    }
                    String addState = data.getData().getLabTests()
                            .getDeliveryAddress().getState();

                    if (!CommonCode.isNullOrEmpty(addState)) {
                        deliveryAddressValue.append("," + addState);
                    }
                    String addZipCode = data.getData().getLabTests()
                            .getDeliveryAddress().getZipCode();
                    if (!CommonCode.isNullOrEmpty(addZipCode)) {
                        deliveryAddressValue.append("," + addZipCode);
                    }

                    member_address.setText(deliveryAddressValue.toString());
                }
            }

        }

        if (!(data.getData().getToProfessionalId() > 0) && !(data.getData().getToFacilityId() > 0)) {
            //  purchase_details.setVisibility(View.GONE);
            provider_details.setVisibility(View.GONE);
            additional_details.setVisibility(View.GONE);
        }
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

    private void getActionsAgainstServiceRequisition(final String statusType) {
        RestAdapter serviceRequestAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("actions")).setLogLevel(LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        progressDialogBox = ProgressDialogFragment.newInstance();
        progressDialogBox.show(getSupportFragmentManager(), "dialog");
        progressDialogBox.setCancelable(false);
        ServiceRequisition serviceRequestURL = serviceRequestAdapter
                .create(ServiceRequisition.class);
        serviceRequestURL.getServiceRequestActions(locationManager.getUserLocation(),
                serviceRequest.getServiceConfigurationCode(), true, 1,
                new Callback<ApiResponse<List<ServiceRequestActionData>>>() {

                    @Override
                    public void success(
                            ApiResponse<List<ServiceRequestActionData>> arg0,
                            Response arg1) {
                        progressDialogBox.dismissAllowingStateLoss();
                        showActionButtons(arg0, statusType);
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialogBox.dismissAllowingStateLoss();
                        Toast.makeText(ServiceRequestDetail.this,
                                "Please Try Again..", Toast.LENGTH_SHORT)
                                .show();

                    }
                });
    }

    protected void showActionButtons(ApiResponse<List<ServiceRequestActionData>> arg0, String statusType) {
        int serviceConfigurationStatusId;
        if (arg0.getData() != null) {

            configuredActions = arg0.getData().get(0).getConfiguredActions();
            configuredStatus = arg0.getData().get(0).getConfiguredStatus();

            for (final ConfiguredStatu configuredStatusObject : configuredStatus) {
                if (configuredStatusObject != null) {
                    if (statusType.equals(configuredStatusObject.getName())) {
                        serviceConfigurationStatusId = configuredStatusObject
                                .getServiceConfigurationStatusId();

                        // clear the action button container first time if it
                        // contains views
                        if (service_requests_container.getChildCount() > 0) {
                            service_requests_container.removeAllViews();
                        }

                        int count = 0;
                        for (final ConfiguredAction configuredActionObj : configuredActions) {

                            if (configuredActionObj
                                    .getServiceConfigurationStatusId() == serviceConfigurationStatusId) {

                                count++;
                            }
                        }

                        for (final ConfiguredAction configuredActionObject : configuredActions) {

                            if (configuredActionObject
                                    .getServiceConfigurationStatusId() == serviceConfigurationStatusId) {
                                LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                                View view = inflator.inflate(
                                        R.layout.action_button, null);

                                final TextView action_button = (TextView) view
                                        .findViewById(R.id.action_button);

                                action_button.setText(configuredActionObject
                                        .getAction().getName());

                                action_button
                                        .setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                data = new ServiceRequisitionActionData();
                                                for (ConfiguredAction configuredAction : configuredActions) {

                                                    if (configuredAction
                                                            .getAction()
                                                            .getName()
                                                            .equals(configuredActionObject
                                                                    .getAction()
                                                                    .getName())) {

                                                        data.setActionId(configuredAction
                                                                .getAction()
                                                                .getId());

                                                    }
                                                }

                                                // data.setActionId(configuredActionObject
                                                // .getActionId());
                                                data.setFromStatus(toStatusId);
                                                data.setChangedBy(String
                                                        .valueOf(session
                                                                .getMemberId()));
                                                data.setChangedByUserType(1);
                                                data.setChangeType("Self");
                                                data.setServiceConfigurationCode(serviceConfigurationCode);
                                                data.setServiceRequisitionPublicId(serviceRequest
                                                        .getPublicId());

                                                if (action_button
                                                        .getText()
                                                        .toString()
                                                        .equalsIgnoreCase(
                                                                "Cancel")) {
                                                    try {
                                                        ServiceRequestCancelActionDialog showDialog = ServiceRequestCancelActionDialog
                                                                .newInstance("You are about to Cancel the Appointment.");
                                                        showDialog
                                                                .show(getSupportFragmentManager(),
                                                                        "context_dialog_frag");
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } else if (action_button
                                                        .getText()
                                                        .toString()
                                                        .equalsIgnoreCase(
                                                                "Message")) {
                                                    try {
                                                        ServiceRequestMessageActionDialog showDialog = ServiceRequestMessageActionDialog
                                                                .newInstance("You are about to Send a Message.");
                                                        showDialog
                                                                .show(getSupportFragmentManager(),
                                                                        "context_dialog_frag");
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } else if (action_button
                                                        .getText()
                                                        .toString()
                                                        .equalsIgnoreCase(
                                                                "Follow-Up")
                                                        || action_button
                                                        .getText()
                                                        .toString()
                                                        .equalsIgnoreCase(
                                                                "confirm")) {
                                                    try {
                                                        ServiceRequestMessageActionDialog showDialog = ServiceRequestMessageActionDialog
                                                                .newInstance("You are about to Update the status of Service Request.");
                                                        showDialog
                                                                .show(getSupportFragmentManager(),
                                                                        "context_dialog_frag");
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    try {
                                                        ServiceRequestPopUp showDialog = ServiceRequestPopUp
                                                                .newInstance("You are about to "
                                                                        + configuredActionObject
                                                                        .getAction()
                                                                        .getName()
                                                                        + " the Appointment.");
                                                        showDialog
                                                                .show(getSupportFragmentManager(),
                                                                        "context_dialog_frag");
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                            }
                                        });

                                //String statusTxt = serviceRequest.getPaymentStatusText();

                                if (!action_button.getText().toString().equalsIgnoreCase("Pay Offline"))
                                    service_requests_container
                                            .addView(action_button);
                            }

                        }

                    }
                }
            }
        }

    }

    protected void updateStatus(ServiceRequisitionActionData data) {
        RestAdapter serviceRequestAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("update_service_request"))
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .build();

        progressDialogBox = ProgressDialogFragment.newInstance();
        progressDialogBox.show(getSupportFragmentManager(), "dialog");
        progressDialogBox.setCancelable(false);
        ServiceRequisition serviceRequestURL = serviceRequestAdapter
                .create(ServiceRequisition.class);
        serviceRequestURL.changeServiceRequestStatus(locationManager.getUserLocation(), authToken, data,
                new Callback<ApiResponse<String>>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialogBox.dismissAllowingStateLoss();

                    }

                    @Override
                    public void success(ApiResponse<String> arg0, Response arg1) {
                        progressDialogBox.dismissAllowingStateLoss();

                        getServiceRequestDetails();
                    }
                });

    }

    private void setUpActionBar() {
        toolbar_service_request = (Toolbar) findViewById(R.id.toolbar_service_request);
        setSupportActionBar(toolbar_service_request);
        getSupportActionBar().setTitle(
                serviceRequest.getServiceConfigurationName());
        getSupportActionBar().setSubtitle(serviceRequest.getPublicId());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void sendMessage(String message) {

        if (data.getActionId() == 33) {

            /////// PAY OFFLINE  /////////////

            MemberPackageTransactionRequest srCriteria = new MemberPackageTransactionRequest();
            srCriteria.setEmail(session.getEmail());
            srCriteria.setCellNumber(session.getCellNumber());
            srCriteria.setFirstName(session.getName());
            srCriteria.setMemberId(session.getUniquePublicId());
            srCriteria.setMemberIP(Utils.getIPAddress(true));
            srCriteria.setQuantity(1);
            srCriteria.setProfileId(null);
            srCriteria.setPackageId(null);
            srCriteria.setPaymentSource("Offline");
            srCriteria.setServiceRequisitionid(data.getServiceRequisitionPublicId());

            if (serviceRequestData.getPaymentAmount() != null) {
                srCriteria.setAmount(serviceRequestData.getPaymentAmount().toString());
            }

            UUID uniqueKey = UUID.randomUUID();
            srCriteria.setTxnId(uniqueKey.toString());
            beginPayOfflineFunction(srCriteria);

        } else if (data.getActionId() == 30) {

            /////////// PAY NOW   /////////////////

            Intent intent = new Intent(this, PayNowWebViewActivity.class);
            intent.putExtra("mpubId", session.getUniquePublicId());
            intent.putExtra("spubId", data.getServiceRequisitionPublicId());
            startActivity(intent);

        } else {
            if (message.equals("yes")) {
                updateStatus(data);
            } else {
                data.setMessage(message);
                updateStatus(data);
            }
        }
    }

    protected void beginPayOfflineFunction(MemberPackageTransactionRequest data) {

        Gson gson = new GsonBuilder().create();

        RestAdapter serviceRequestAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("update_service_request"))
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .build();

        progressDialogBox = ProgressDialogFragment.newInstance();
        progressDialogBox.show(getSupportFragmentManager(), "dialog");
        progressDialogBox.setCancelable(false);
        ServiceRequisition serviceRequestURL = serviceRequestAdapter
                .create(ServiceRequisition.class);

        serviceRequestURL.beginPayNowTransaction(authToken, gson.toJson(data).toString(),
                new Callback<ServiceRequestPaymentResponse>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialogBox.dismissAllowingStateLoss();
                    }

                    @Override
                    public void success(ServiceRequestPaymentResponse arg0, Response arg1) {

                        PackageDetailsData packageDetailsData = arg0.getData();
                    }
                });

    }
}
