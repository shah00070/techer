package com.schoolteacher.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.GooglePlayAlert;
import com.schoolteacher.library.main.SignUpLoginActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.AwardsAndMention;
import com.schoolteacher.pojos.DealAndOffer;
import com.schoolteacher.pojos.InstantAppointmentData;
import com.schoolteacher.pojos.JeevAddress;
import com.schoolteacher.pojos.JeevFacilityContactInformation;
import com.schoolteacher.pojos.JeevFacilityConvenience;
import com.schoolteacher.pojos.JeevFacilityInfo;
import com.schoolteacher.pojos.JeevFacilityResult;
import com.schoolteacher.pojos.JeevFacilityService;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceConfigInfo;
import com.schoolteacher.pojos.ServiceConfiguration;
import com.schoolteacher.pojos.ServiceRequisitionResult;
import com.schoolteacher.pojos.Stats;
import com.schoolteacher.service.GetFacilityProfile;
import com.schoolteacher.service.ServiceRequisition;
import com.schoolteacher.serviceRequest.Appointments;
import com.schoolteacher.serviceRequest.EmailConsultation;
import com.schoolteacher.serviceRequest.LabTest;
import com.schoolteacher.serviceRequest.PurchaseRequest;
import com.schoolteacher.serviceRequest.RequestCallback;
import com.schoolteacher.serviceRequest.RequestQuote;
import com.schoolteacher.serviceRequest.SendMessage;
import com.squareup.picasso.Picasso;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class FacilityProfile extends BaseClass implements OnClickListener {
    Gson gson;


    private Toolbar toolbar_facility;
    private static final String SG1 = "SG1";
    private static final String SG2 = "SG2";
    private static final String SG3 = "SG3";
    private static final String SG4 = "SG4";
    private static final String SG5 = "SG5";
    private static final String SG6 = "SG6";
    private static final String SG7 = "SG7";
    private static final String SG8 = "SG8";
    private static final String SG9 = "SG9";
    private static final String SG10 = "SG10";
    private static final String SG11 = "SG11";
    private static final String SG12 = "SG12";

    View view;
    Bundle bundle;
    String profileId;
    // private JeevomSession session;
    DialogFragment newFragment;
    GlobalAlert globalAlert;
    ValuesManager valuesManager;
    GooglePlayAlert googleAlert;
    JeevFacilityInfo data;
    List<ServiceConfiguration> serviceConfigurations;

    // View Elements
    CircleImageView profile_image;
    TextView facility_name, facility_services, facility_convenience;

    TextView address_one, address_two, address_three;
    RelativeLayout clinics_layout;

    LinearLayout in_clinic, home_visit, video, audio, text_chat, email_basic,
            email_advance, purchase, lab, quotes, callback, message;

    RelativeLayout services_container;
    LinearLayout ser_container, conv_container;
    TextView ser_value, conv_value;

    RelativeLayout about_container;
    ExpandableTextView summary_value;

    RelativeLayout awards_container;
    TextView awards_value;

    RelativeLayout deals_container;
    TextView deals_value;

    RelativeLayout profile_layout;
    int vibrant, vibrantDark, muted, mutedLight, mutedDark, vibrantLight;

    // like
    ImageView like_image;
    TextView like_count, like_text;

    // profile views
    ImageView profile_view_image;
    TextView profile_view_count, profile_view_text, profile_view_text_one;

    // connections
    ImageView connection_view_image;
    TextView connection_count, connection_text;

    String imageUrl = null;
    String authToken = null;

    private JeevomSession session;
    UserCurrentLocationManager locationManager;

    private LinearLayout instant_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_profile);
        gson = new GsonBuilder().create();
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        // set toolbar as Action Bar
        setUpActionBar();
        session = new JeevomSession(getApplicationContext());
        valuesManager = new ValuesManager(getApplicationContext());
        // session = new SessionManager(getApplicationContext());
        globalAlert = new GlobalAlert(this);
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        profileId = getIntent().getStringExtra("id");

        initiateViewElements();
        getFacilityProfile(profileId);

    }

    void getFacilityProfile(final String pro_id) {

        RestAdapter getFacilityProfileAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("doctorDetails"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        GetFacilityProfile facility_profile_interface = getFacilityProfileAdapter
                .create(GetFacilityProfile.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        facility_profile_interface.getFacilityDetails(
//				gson.toJson(locationManager.getUserLocation()).toString(),pro_id.trim(),
                pro_id.trim(),
                String.valueOf(valuesManager.getVersion()), authToken,
                new Callback<JeevFacilityResult>() {

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
                        } else if (arg0.getResponse().getStatus() == 426) {
                            showGooglePlayAlert(getResources().getString(
                                    R.string.google_update));
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
                    public void success(JeevFacilityResult arg0, Response arg1) {

                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            data = arg0.getData();

                            getServiceConfigurations(pro_id);

                        }
                    }
                });

    }

    private void getServiceConfigurations(String pro_id) {
        RestAdapter serviceConfigAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("config"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_config_interface = serviceConfigAdapter
                .create(ServiceRequisition.class);

        service_config_interface.getFacilityServiceConfigurations(
                gson.toJson(locationManager.getUserLocation()).toString(), authToken,
                pro_id.trim(),

                new Callback<ServiceConfigInfo>() {

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
                        } else if (arg0.getResponse().getStatus() == 426) {
                            showGooglePlayAlert(getResources().getString(
                                    R.string.google_update));
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
                            serviceConfigurations = arg0.getData()
                                    .getServiceConfigurations();

                            fillFacilityProfile();

                        }
                    }
                });

    }

    private void fillFacilityProfile() {
        // set profile views
        Stats stats = data.getFacilityProfile().getStats();
        if (stats != null) {

            if (!CommonCode.isNullOrEmpty(stats.getViewCount())) {

                if (Integer.valueOf(stats.getViewCount()) > 0
                        && Integer.valueOf(stats.getViewCount()) > 50) {

                    profile_view_count.setText(stats.getViewCount());
                } else if (Integer.valueOf(stats.getViewCount()) > 0
                        && Integer.valueOf(stats.getViewCount()) < 50) {

                    profile_view_count.setText("<50");
                } else {
                    profile_view_count.setText("N/A");
                }
            }
        }

        // set profile picture

        if (!(CommonCode.isNullOrEmpty(data.getFacilityProfile()
                .getProfilePhoto()))) {
            imageUrl = data.getFacilityProfile().getProfilePhoto();
        }
        Picasso.with(this).load(imageUrl).placeholder(R.drawable.jeevom_back)
                .error(R.drawable.jeevom_back).into(profile_image);

        // set facility name
        if (!CommonCode.isNullOrEmpty(data.getFacilityProfile().getName())) {
            facility_name.setText(data.getFacilityProfile().getName());
        }

        // set services
        List<JeevFacilityService> services = data.getFacilityProfile()
                .getServices();

        if (services != null && services.size() > 0) {
            StringBuilder commaSepratedValue = new StringBuilder();

            for (int i = 0; i < services.size(); i++) {

                if (i == 0) {
                    commaSepratedValue.append(services.get(i).getName());
                } else {
                    commaSepratedValue.append(", " + services.get(i).getName());
                }
            }

            facility_services.setText(commaSepratedValue.toString());
            ser_value.setText(commaSepratedValue.toString());
        } else {
            ser_container.setVisibility(View.GONE);
            facility_services.setVisibility(View.GONE);
        }

        // set conveniences
        List<JeevFacilityConvenience> convenience = data.getFacilityProfile()
                .getConvenience();

        if (convenience != null && convenience.size() > 0) {
            StringBuilder commaSepratedValue = new StringBuilder();

            for (int i = 0; i < convenience.size(); i++) {

                if (i == 0) {
                    commaSepratedValue.append(convenience.get(i).getName());
                } else {
                    commaSepratedValue.append(", "
                            + convenience.get(i).getName());
                }
            }
            conv_value.setText(commaSepratedValue.toString());
            facility_convenience.setText(commaSepratedValue.toString());
        } else {
            conv_container.setVisibility(View.GONE);
            facility_convenience.setVisibility(View.GONE);
        }

        if ((convenience == null || convenience.size() == 0)
                && (services == null || services.size() == 0)) {
            services_container.setVisibility(View.GONE);
        }

        // set address
        JeevFacilityContactInformation contactInformation = data
                .getFacilityProfile().getContactInformation();
        if (contactInformation != null) {

            JeevAddress facilityAdd = contactInformation.getAddress();
            if (facilityAdd != null) {
                if (!CommonCode.isNullOrEmpty(facilityAdd.getLine1()))
                    address_one.setText(facilityAdd.getLine1());
                else
                    address_one.setVisibility(View.GONE);

                StringBuilder addressTwo = new StringBuilder();
                if (!CommonCode.isNullOrEmpty(facilityAdd.getArea()))
                    addressTwo.append(facilityAdd.getArea());
                if (!CommonCode.isNullOrEmpty(facilityAdd.getCity()))
                    addressTwo.append(", " + facilityAdd.getCity());

                if (!CommonCode.isNullOrEmpty(addressTwo.toString()))
                    address_two.setText(addressTwo.toString());
                else
                    address_two.setVisibility(View.GONE);

                StringBuilder addressThree = new StringBuilder();
                if (!CommonCode.isNullOrEmpty(facilityAdd.getZipCode()))
                    addressThree.append(facilityAdd.getZipCode());
                if (!CommonCode.isNullOrEmpty(facilityAdd.getCountry()))
                    addressThree.append(", " + facilityAdd.getCountry());

                if (!CommonCode.isNullOrEmpty(addressThree.toString()))
                    address_three.setText(addressThree.toString());
                else
                    address_three.setVisibility(View.GONE);
            } else {
                clinics_layout.setVisibility(View.GONE);
            }
        } else {
            clinics_layout.setVisibility(View.GONE);
        }

        // set call to action buttons
        if (serviceConfigurations != null && serviceConfigurations.size() > 0) {
            for (ServiceConfiguration object : serviceConfigurations) {
                if (object.getUniquePublicID().equals(SG1))
                    in_clinic.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG2))
                    home_visit.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG3))
                    video.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG4))
                    audio.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG5))
                    text_chat.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG6))
                    email_basic.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG7))
                    email_advance.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG8))
                    purchase.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG9))
                    lab.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG10))
                    quotes.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG11))
                    callback.setVisibility(View.VISIBLE);
                else if (object.getUniquePublicID().equals(SG12))
                    message.setVisibility(View.VISIBLE);

            }
        }

        // set summary
        if (!CommonCode.isNullOrEmpty(data.getFacilityProfile().getAbout())) {
            summary_value.setText(data.getFacilityProfile().getAbout());
        } else {
            about_container.setVisibility(View.GONE);
        }

        // set awards

        List<AwardsAndMention> awardsAndMentions = data.getFacilityProfile()
                .getAwardsAndMentions();
        if (awardsAndMentions != null && awardsAndMentions.size() > 0) {
            StringBuilder commaSepratedValue = new StringBuilder();
            for (int i = 0; i < awardsAndMentions.size(); i++) {

                if (i == 0) {
                    commaSepratedValue.append(awardsAndMentions.get(i)
                            .getName());
                } else {
                    commaSepratedValue.append(", "
                            + awardsAndMentions.get(i).getName());
                }
            }
            awards_value.setText(commaSepratedValue.toString());
        } else {
            awards_container.setVisibility(View.GONE);
        }

        // set deals

        List<DealAndOffer> dealAndOffers = data.getFacilityProfile()
                .getDealAndOffers();
        if (dealAndOffers != null && dealAndOffers.size() > 0) {
            StringBuilder commaSepratedValue = new StringBuilder();
            for (int i = 0; i < dealAndOffers.size(); i++) {

                if (i == 0) {
                    commaSepratedValue.append(dealAndOffers.get(i).getName());
                } else {
                    commaSepratedValue.append(", "
                            + dealAndOffers.get(i).getName());
                }
            }
            deals_value.setText(commaSepratedValue.toString());
        } else {
            deals_container.setVisibility(View.GONE);
        }

        setTextColors();

        boolean isInclinicAllow = false;

        for (int i = 0; i < serviceConfigurations.size(); i++) {

            if (serviceConfigurations.get(i).getUniquePublicID().equals("SG1")) {
                isInclinicAllow = true;
            }
        }

        if (isInclinicAllow)
            instant_layout.setVisibility(View.VISIBLE);
        else
            instant_layout.setVisibility(View.GONE);
    }

    private void initiateViewElements() {
        profile_layout = (RelativeLayout) findViewById(R.id.profile_layout);

        profile_image = (CircleImageView) findViewById(R.id.profile_image);

        facility_name = (TextView) findViewById(R.id.facility_name);
        facility_services = (TextView) findViewById(R.id.facility_services);
        facility_convenience = (TextView) findViewById(R.id.facility_convenience);

        like_count = (TextView) findViewById(R.id.like_count);
        // profile view counts
        profile_view_count = (TextView) findViewById(R.id.profile_view_count);
        profile_view_text = (TextView) findViewById(R.id.profile_view_text);
        profile_view_text_one = (TextView) findViewById(R.id.profile_view_text_one);
        profile_view_image = (ImageView) findViewById(R.id.profile_view_image);

        // like
        like_count = (TextView) findViewById(R.id.like_count);
        like_image = (ImageView) findViewById(R.id.like_image);
        like_text = (TextView) findViewById(R.id.like_text);

        // connections
        connection_view_image = (ImageView) findViewById(R.id.connection_view_image);
        connection_count = (TextView) findViewById(R.id.connection_count);
        connection_text = (TextView) findViewById(R.id.connection_text);
        connection_count = (TextView) findViewById(R.id.connection_count);

        address_one = (TextView) findViewById(R.id.address_one);
        address_two = (TextView) findViewById(R.id.address_two);
        address_three = (TextView) findViewById(R.id.address_three);
        clinics_layout = (RelativeLayout) findViewById(R.id.clinics_layout);

        // call to action button
        in_clinic = (LinearLayout) findViewById(R.id.in_clinc_consultation);
        home_visit = (LinearLayout) findViewById(R.id.home_consultation);
        video = (LinearLayout) findViewById(R.id.video_consultation);
        audio = (LinearLayout) findViewById(R.id.audio_consultation);
        text_chat = (LinearLayout) findViewById(R.id.text_consultation);
        email_basic = (LinearLayout) findViewById(R.id.basic_email_consultation);
        email_advance = (LinearLayout) findViewById(R.id.advance_email_consultation);
        purchase = (LinearLayout) findViewById(R.id.purchase_request);
        lab = (LinearLayout) findViewById(R.id.lab_test);
        quotes = (LinearLayout) findViewById(R.id.request_quote);
        callback = (LinearLayout) findViewById(R.id.request_call);
        message = (LinearLayout) findViewById(R.id.send_message);
        // onclick listners
        in_clinic.setOnClickListener(this);
        home_visit.setOnClickListener(this);
        video.setOnClickListener(this);
        audio.setOnClickListener(this);
        text_chat.setOnClickListener(this);
        email_basic.setOnClickListener(this);
        email_advance.setOnClickListener(this);
        purchase.setOnClickListener(this);
        lab.setOnClickListener(this);
        quotes.setOnClickListener(this);
        callback.setOnClickListener(this);
        message.setOnClickListener(this);

        services_container = (RelativeLayout) findViewById(R.id.services_container);
        ser_container = (LinearLayout) findViewById(R.id.ser_container);
        conv_container = (LinearLayout) findViewById(R.id.conv_container);
        ser_value = (TextView) findViewById(R.id.ser_value);
        conv_value = (TextView) findViewById(R.id.conv_value);

        about_container = (RelativeLayout) findViewById(R.id.about_container);
        summary_value = (ExpandableTextView) findViewById(R.id.summary_value);

        awards_container = (RelativeLayout) findViewById(R.id.awards_container);
        awards_value = (TextView) findViewById(R.id.awards_value);

        deals_container = (RelativeLayout) findViewById(R.id.deals_container);
        deals_value = (TextView) findViewById(R.id.deals_value);


        instant_layout = (LinearLayout) findViewById(R.id.instant_layout);
        instant_layout.setOnClickListener(this);
    }

    private void setUpActionBar() {
        toolbar_facility = (Toolbar) findViewById(R.id.toolbar_facility);
        setSupportActionBar(toolbar_facility);
        getSupportActionBar().setTitle("Jeevom Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        super.onPrepareOptionsMenu(menu);
        return true;
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

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    private void showGooglePlayAlert(String message) {
        googleAlert.show();
        googleAlert.setMessage(message);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.in_clinc_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG1")) {

                        performAppointmentAction(profileId, "in_clinic");
                        break;
                    }
                }
                break;

            case R.id.home_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG2")) {

                        performAppointmentAction(profileId, "home");
                        break;
                    }
                }
                break;
            case R.id.video_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG3")) {

                        performAppointmentAction(profileId, "video");
                        break;
                    }
                }
                break;
            case R.id.audio_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG4")) {

                        performAppointmentAction(profileId, "audio");
                        break;
                    }
                }
                break;
            case R.id.text_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG5")) {

                        performAppointmentAction(profileId, "text_chat");
                        break;
                    }
                }
                break;
            case R.id.basic_email_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG6")) {
                        goToBasicEmail(object.getId(), object.getFees());
                        break;
                    }
                }

                break;
            case R.id.advance_email_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG7")) {
                        goToAdvanceEmail(object.getId(), object.getFees());
                        break;
                    }
                }
                break;
            case R.id.purchase_request:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG8")) {

                        Intent intent = new Intent(FacilityProfile.this,
                                PurchaseRequest.class);
                        intent.putExtra("profile_object", data);
                        intent.putExtra("service_id", object.getId());
                        intent.putExtra("fee", object.getFees());
                        intent.putExtra("service_requestor", "facility");
                        startActivity(intent);
                        break;
                    }
                }
                break;
            case R.id.lab_test:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG9")) {

                        Intent intent = new Intent(FacilityProfile.this,
                                LabTest.class);
                        intent.putExtra("profile_object", data);
                        intent.putExtra("service_id", object.getId());
                        intent.putExtra("fee", object.getFees());
                        intent.putExtra("service_requestor", "facility");
                        startActivity(intent);
                        break;
                    }
                }
                break;
            case R.id.request_quote:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG10")) {
                        performActionBasicEmail(object.getId(), object.getFees(),
                                "request_quote");
                        break;
                    }
                }

                break;
            case R.id.request_call:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG11")) {
                        performActionBasicEmail(object.getId(), object.getFees(),
                                "callback");
                        break;
                    }
                }
                break;
            case R.id.send_message:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG12")) {
                        performActionBasicEmail(object.getId(), object.getFees(),
                                "message");
                        break;
                    }
                }
                break;
            case R.id.instant_layout:

                if (session.getLoggedInStatus())
                    initiateInstantAppointment(profileId);
                else {
                    Intent signup_intent = new Intent(FacilityProfile.this, SignUpLoginActivity.class);
                    session.setAppType("jeevom");
                    signup_intent.putExtra("profileId", profileId);
                    signup_intent.putExtra("isInContext", true);
                    startActivityForResult(signup_intent, 1011);
                    overridePendingTransition(com.schoolteacher.mylibrary.R.anim.trans_left_in, com.schoolteacher.mylibrary.R.anim.trans_left_exit);
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1011 && resultCode == Activity.RESULT_OK) {
            String profileIdVal = data.getStringExtra("profileId");
            if (profileIdVal != null)
                initiateInstantAppointment(profileIdVal);
        }
    }

    public void initiateInstantAppointment(String profileIdVal) {

        if (authToken == null && !CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }

        InstantAppointmentData appointmentData = new InstantAppointmentData();
        appointmentData.setMemberPublicId(session.getUniquePublicId());
        appointmentData.setFacilityPublicId(profileIdVal);
        appointmentData.setServiceRequisitionType("SG1");
        appointmentData.setProfessionalPubicId("");
        makeServiceCall(appointmentData);
    }

    private void performActionBasicEmail(int id, double fee, String type) {
        Intent intent = null;
        if (type.equalsIgnoreCase("email")) {
            intent = new Intent(FacilityProfile.this, EmailConsultation.class);
        } else if (type.equalsIgnoreCase("request_quote")) {
            intent = new Intent(FacilityProfile.this, RequestQuote.class);
        } else if (type.equalsIgnoreCase("message")) {
            intent = new Intent(FacilityProfile.this, SendMessage.class);
        } else if (type.equalsIgnoreCase("callback")) {
            intent = new Intent(FacilityProfile.this, RequestCallback.class);
        }
        intent.putExtra("profile_object", data);
        intent.putExtra("service_id", id);
        intent.putExtra("fee", fee);
        intent.putExtra("service_requestor", "facility");
        startActivity(intent);
    }

    private void goToBasicEmail(int id, double fee) {
        Intent intent = new Intent(FacilityProfile.this,
                EmailConsultation.class);

        intent.putExtra("profile_object", data);
        intent.putExtra("service_id", id);
        intent.putExtra("fee", fee);
        intent.putExtra("email_consultation_type", "basic");
        intent.putExtra("service_requestor", "facility");
        startActivity(intent);
    }

    private void goToAdvanceEmail(int id, double fee) {
        Intent intent = new Intent(FacilityProfile.this,
                EmailConsultation.class);
        intent.putExtra("profile_object", data);
        intent.putExtra("service_id", id);
        intent.putExtra("fee", fee);
        intent.putExtra("email_consultation_type", "advance");
        intent.putExtra("service_requestor", "facility");
        startActivity(intent);
    }

    private void performAppointmentAction(String id, String type) {
        Intent intent = null;
        intent = new Intent(FacilityProfile.this, Appointments.class);
        intent.putExtra("profileId", id);
        intent.putExtra("type", type);
        intent.putExtra("image", imageUrl);
        intent.putExtra("name", facility_name.getText().toString());
        intent.putExtra("service_requestor", "facility");
        startActivity(intent);
    }

    private void setTextColors() {
        // profile_layout.setBackgroundColor(getResources().getColor(
        // R.color.colorPrimary));
        // ((TextView) view.findViewById(R.id.summary_heading))
        // .setTextColor(getResources().getColor(R.color.colorPrimary));
        // ((TextView) view.findViewById(R.id.services_heading))
        // .setTextColor(getResources().getColor(R.color.colorPrimary));
        // ((TextView) view.findViewById(R.id.awards_heading))
        // .setTextColor(getResources().getColor(R.color.colorPrimary));
        // ((TextView) view.findViewById(R.id.deals_heading))
        // .setTextColor(getResources().getColor(R.color.colorPrimary));

        like_count.setTextColor(getResources().getColor(R.color.grey_dark));
        like_image.setColorFilter(getResources().getColor(R.color.grey_dark));
        like_text.setTextColor(getResources().getColor(R.color.grey_dark));

        profile_view_count.setTextColor(getResources().getColor(
                R.color.grey_dark));
        profile_view_text.setTextColor(getResources().getColor(
                R.color.grey_dark));
        profile_view_text_one.setTextColor(getResources().getColor(
                R.color.grey_dark));
        profile_view_image.setColorFilter(getResources().getColor(
                R.color.grey_dark));

        connection_view_image.setColorFilter(getResources().getColor(
                R.color.grey_dark));
        connection_count.setTextColor(getResources()
                .getColor(R.color.grey_dark));
        connection_text
                .setTextColor(getResources().getColor(R.color.grey_dark));

        // set colors
        facility_name.setTextColor(getResources().getColor(R.color.grey_dark));
        facility_services.setTextColor(getResources().getColor(
                R.color.grey_dark));
        facility_convenience.setTextColor(getResources().getColor(
                R.color.grey_dark));
    }

    ///////////////// FOR INSTANT APPOINTMENT BOOK  //////////////

    private void makeServiceCall(final InstantAppointmentData instantAppointmentData) {

        RestAdapter serviceRequisitionAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("service_requisition"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_req_interface = serviceRequisitionAdapter
                .create(ServiceRequisition.class);

        final DialogFragment progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        service_req_interface.makeInstantServiceRequest(locationManager.getUserLocation(), authToken, instantAppointmentData,

                new Callback<ServiceRequisitionResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialog.dismissAllowingStateLoss();

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
                        progressDialog.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {

                            // String referenceNo = arg0.getData();

                            showAlertFinish(getResources().getString(R.string.instant_appoint_done));

                        }
                    }
                });
    }

    private void showAlertFinish(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

}
