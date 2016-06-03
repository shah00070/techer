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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.DocumentOptionDialog;
import com.schoolteacher.dialog.GooglePlayAlert;
import com.schoolteacher.interfaces.DocumentOption;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.medvault.DocumentUploadActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.CallToActionAppointment;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.DocumentList;
import com.schoolteacher.pojos.DocumentObject;
import com.schoolteacher.pojos.JeevProfileAddress;
import com.schoolteacher.pojos.JeevSession;
import com.schoolteacher.pojos.MemberAssociation;
import com.schoolteacher.pojos.ProfessionalProfileAppointment;
import com.schoolteacher.pojos.ProfileFacility;
import com.schoolteacher.pojos.ProfileFacilityResult;
import com.schoolteacher.pojos.ProfileProfessionalResult;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceConfigInfo;
import com.schoolteacher.pojos.ServiceConfiguration;
import com.schoolteacher.service.FacilityProfile;
import com.schoolteacher.service.ProfessionalProfile;
import com.schoolteacher.service.ServiceRequisition;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

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

public class Appointments extends ActionBarActivity implements OnClickListener,
        OnItemSelectedListener, DocumentOption {
    private final int ATTACH = 200;
    private final int SELECT = 400;
    UserCurrentLocationManager locationManager;
    Gson gson;


    List<DocumentList> selectedDocuments;

    String authToken = null;

    // doctor image and name
    RelativeLayout image_name;
    CircleImageView img_doctor;
    TextView name;

    RelativeLayout btn_attachment;
    // selected Fields
    String selectedFacilityId;
    String serviceConfig;
    int service_id;
    // other fields
    int forMemberId;
    boolean bookedForSelf;
    String consult_for_type, gender, titleType = "Mr.";

    // alerts
    GlobalAlert globalAlert;
    GlobelAlertWithFinish globalAlertFinish;
    GooglePlayAlert googleAlert;

    TextView address, fee_value;
    Spinner appointment_type_spinner, facility_spinner;
    LinearLayout counts_bar;
    List<String> facilitiesList;
    List<ServiceConfiguration> serviceConfigurations;
    // sessions
    JeevomSession session;
    ValuesManager valuesManager;

    DialogFragment newFragment;
    List<MemberAssociation> memberAssociation;

    int professionalId, facilityId;

    String profileId, typeOfRequest, serviceRequisition, service_requestor;
    int id;
    List<String> appointment_type_list;
    List<JeevSession> sessions;

    // facilities list
    List<ProfileFacility> facilityProfiles;
    ProfessionalProfileAppointment professionalFacilities;

    String bookedTime;
    int sessionId, slotId;

    String name_professional_facility;

    int fees;
    String referenceNo;
    String url;
    Toolbar toolbar;
    Drawable d;

    Button btn_proceed;

    String selectedAppointmentTypeValue;
    CallToActionRequest requestSendMessage;

    boolean haveFacilities;

    RelativeLayout date_time_layout;
    TextView time_slot;
    String date, slotName;

    DocumentList document;

    LinearLayout documents_container;
    private List<DocumentObject> documentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_layout);
        documentList = new ArrayList<>();
        gson = new GsonBuilder().create();
        selectedDocuments = new LinkedList<DocumentList>();
        // alerts
        globalAlert = new GlobalAlert(this);
        globalAlertFinish = new GlobelAlertWithFinish(this);
        globalAlertFinish.setCancelable(false);
        googleAlert = new GooglePlayAlert(this);
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        // sessions
        session = new JeevomSession(getApplicationContext());
        valuesManager = new ValuesManager(getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        // initialize lists
        facilitiesList = new LinkedList<>();

        // get intent and get id and request type
        Intent intent = getIntent();

        url = intent.getStringExtra("image");
        profileId = intent.getStringExtra("profileId");
        id = intent.getIntExtra("id", 0);
        typeOfRequest = intent.getStringExtra("type");
        service_requestor = intent.getStringExtra("service_requestor");
        name_professional_facility = intent.getStringExtra("name");

        // set action bar
        toolbar = (Toolbar) findViewById(R.id.toolbar_appointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Book Appointment");

        if ("in_clinic".equals(typeOfRequest))
            serviceRequisition = "SG1";
        else if ("home".equals(typeOfRequest))
            serviceRequisition = "SG2";
        else if ("video".equals(typeOfRequest))
            serviceRequisition = "SG3";
        else if ("audio".equals(typeOfRequest))
            serviceRequisition = "SG4";
        else if ("text_chat".equals(typeOfRequest))
            serviceRequisition = "SG5";

        getViewReferences();

        if (service_requestor.equalsIgnoreCase("professional")) {
            getServiceConfigurations(profileId);
        } else {
            getFacilityServiceConfigurations(profileId);
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
                img_doctor.getLayoutParams().width = 48;
                img_doctor.getLayoutParams().height = 48;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                // d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
                // arg0, 96, 96, true));
                img_doctor.getLayoutParams().width = 48;
                img_doctor.getLayoutParams().height = 48;
                break;
        }

        Picasso.with(this).load(url).placeholder(R.drawable.jeevom_back)
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
    protected void onResume() {
        super.onResume();
    }

    private void getFacilityServiceConfigurations(String pro_id) {
        RestAdapter serviceConfigAdapter = new RestAdapter.Builder()
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_config_interface = serviceConfigAdapter
                .create(ServiceRequisition.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        service_config_interface.getFacilityServiceConfigurations(gson.toJson(locationManager.getUserLocation()).toString(), authToken,
                pro_id.trim(),

                new Callback<ServiceConfigInfo>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(Appointments.this))) {
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

                            fillAppointmentTypeSpinner();

                            address.setVisibility(View.VISIBLE);
                            getFacilityDetails();

                        }
                    }
                });

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

    private void showGooglePlayAlert(String message) {
        googleAlert.show();
        googleAlert.setMessage(message);
    }

    private void getViewReferences() {

        // 1. set name and image of doctor
        image_name = (RelativeLayout) findViewById(R.id.image_name);
        img_doctor = (CircleImageView) findViewById(R.id.img_doctor);
        name = (TextView) findViewById(R.id.name);

        // set name
        if (!CommonCode.isNullOrEmpty(name_professional_facility))
            name.setText(name_professional_facility);

        // set image
        Picasso.with(this).load(url).into(new Target() {

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
        name.setTextColor(getResources().getColor(R.color.White));

        // 1. end

        appointment_type_spinner = (Spinner) findViewById(R.id.appointment_type);
        facility_spinner = (Spinner) findViewById(R.id.facility);
        address = (TextView) findViewById(R.id.address);
        fee_value = (TextView) findViewById(R.id.fee_value);

        counts_bar = (LinearLayout) findViewById(R.id.counts_bar);
        documents_container = (LinearLayout) findViewById(R.id.documents_container);
        btn_attachment = (RelativeLayout) findViewById(R.id.btn_attachment);
        btn_attachment.setOnClickListener(this);

        appointment_type_spinner.setOnItemSelectedListener(this);
        facility_spinner.setOnItemSelectedListener(this);

        btn_proceed = (Button) findViewById(R.id.btn_proceed);
        btn_proceed.setOnClickListener(this);

        date_time_layout = (RelativeLayout) findViewById(R.id.date_time_layout);
        date_time_layout.setOnClickListener(this);
        time_slot = (TextView) findViewById(R.id.time_slot);

    }

    private void fillAppointmentTypeSpinner() {
        // fill appointment_type_spinner
        appointment_type_list = new LinkedList<>();
        for (ServiceConfiguration object : serviceConfigurations) {

            if (object.getUniquePublicID().equals("SG1")) {
                appointment_type_list.add(object.getName());
            }
            if (object.getUniquePublicID().equals("SG2")) {
                appointment_type_list.add(object.getName());
            }
            if (object.getUniquePublicID().equals("SG3")) {
                appointment_type_list.add(object.getName());
            }
            if (object.getUniquePublicID().equals("SG4")) {
                appointment_type_list.add(object.getName());
            }
            if (object.getUniquePublicID().equals("SG5")) {
                appointment_type_list.add(object.getName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                Appointments.this, R.layout.spinner_item, appointment_type_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appointment_type_spinner.setAdapter(adapter);

        // set current selection
        for (ServiceConfiguration object : serviceConfigurations) {
            if (object.getUniquePublicID().equals(serviceRequisition))
                for (int i = 0; i < appointment_type_list.size(); i++) {
                    if (object.getName().equals(appointment_type_list.get(i))) {
                        appointment_type_spinner.setSelection(i);
                        return;
                    }
                }
        }
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {

        super.onActivityResult(arg0, arg1, arg2);
        if (arg0 == JeevOMUtil.PHONE_AND_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_OK) {
            showAlertFinish("Thank you for Submitting your request." + "\n"
                    + "Your Reference Number is: " + referenceNo);
        } else if (arg0 == JeevOMUtil.PHONE_AND_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_CANCELED) {
            Crouton.makeText(Appointments.this, "Please try again", Style.ALERT)
                    .show();
        }

        if (arg0 == JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE) {
            showAlertFinish("Thank you for Submitting your request." + "\n"
                    + "Your Reference Number is: " + referenceNo);
        } else if (arg0 == JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_CANCELED) {
            Crouton.makeText(Appointments.this, "Please try again", Style.ALERT)
                    .show();
        }

        if (arg0 == 100 && arg1 == 100) {
            if (arg2.getExtras() != null) {
                slotName = arg2.getStringExtra("slotName");
                slotId = arg2.getIntExtra("slotId", 0);
                sessionId = arg2.getIntExtra("sessionId", 0);
                bookedTime = arg2.getStringExtra("bookedTime");
                date = arg2.getStringExtra("date");

                time_slot.setText("Slot: " + slotName + " - Date: " + date);
            }
        }

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
        if (arg0 == 1 && arg1 == 1) {

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
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long arg) {

        int id = parent.getId();
        // appointment type spinner
        if (id == R.id.appointment_type) {
            slotId = 0;
            sessionId = 0;
            bookedTime = null;
            date = null;
            time_slot.setText("See all available slots");

            selectedAppointmentTypeValue = appointment_type_list.get(position);
            for (ServiceConfiguration object : serviceConfigurations) {

                if (object.getName().equals(selectedAppointmentTypeValue)) {
                    if (object.isIsAttachDocumentAllowed()) {
                        btn_attachment.setVisibility(View.VISIBLE);
                    } else {
                        btn_attachment.setVisibility(View.GONE);
                    }
                    service_id = object.getId();
                    serviceConfig = object.getUniquePublicID();
                    fees = (int) object.getFees();
                    if (fees > 0)
                        fee_value.setText("Fees: "
                                + String.valueOf((int) (object.getFees())));
                    else
                        fee_value.setText("Fee: " + "N/A");
                }
            }

            if (service_requestor.equalsIgnoreCase("professional")) {
                if (selectedAppointmentTypeValue
                        .equals("In-clinic Consultation")) {
                    // make spinner for facility and address visible
                    facility_spinner.setVisibility(View.VISIBLE);
                    address.setVisibility(View.VISIBLE);
                    if (service_requestor.equalsIgnoreCase("professional")) {
                        if (!haveFacilities) {
                            getFacilities();
                        }
                    }
                } else {
                    // make spinner for facility and address invisible
                    facility_spinner.setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                }
            }

        }
        // facility spinner
        else if (id == R.id.facility) {
            slotId = 0;
            sessionId = 0;
            bookedTime = null;
            date = null;
            time_slot.setText("See all available slots");
            setAddressAndFee(position);
        }

    }

    private void setAddressAndFee(int position) {
        for (ProfileFacility object : facilityProfiles) {
            if (facilitiesList.get(position).equals(object.getName())) {
                selectedFacilityId = String.valueOf(object.getId());

                StringBuilder addressValue = new StringBuilder();

                if (object.getAddress() != null) {
                    if (!CommonCode.isNullOrEmpty(object.getAddress()
                            .getAddressLine1()))
                        ;
                    addressValue.append(object.getAddress().getAddressLine1()
                            + ", ");
                    if (!CommonCode
                            .isNullOrEmpty(object.getAddress().getArea()))
                        ;
                    addressValue.append(object.getAddress().getArea() + ", ");
                    if (!CommonCode
                            .isNullOrEmpty(object.getAddress().getCity()))
                        ;
                    addressValue.append(object.getAddress().getCity() + ", ");
                    if (!CommonCode.isNullOrEmpty(object.getAddress()
                            .getState()))
                        ;
                    addressValue.append(object.getAddress().getState() + ", ");
                    if (!CommonCode.isNullOrEmpty(object.getAddress()
                            .getZipCode()))
                        ;
                    addressValue
                            .append(object.getAddress().getZipCode() + ", ");
                    if (!CommonCode.isNullOrEmpty(object.getAddress()
                            .getCountry()))
                        ;
                    addressValue
                            .append(object.getAddress().getCountry() + ", ");
                    address.setText((addressValue.toString()).replaceAll(",$",
                            ""));
                }
            }

        }
    }

    // get Facility
    private void getFacilityDetails() {

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(
                JeevOMUtil.baseUrl).build();
        FacilityProfile profile = adapter.create(FacilityProfile.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        profile.getFacilityProfile(locationManager.getUserLocation(), authToken, profileId.trim(),

                new Callback<ProfileFacilityResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(Appointments.this))) {
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
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            SearchResultsResponse searchResultsResponse = gson
                                    .fromJson(json, SearchResultsResponse.class);
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
                    public void success(ProfileFacilityResult arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        facilityId = arg0.getData().getId();

                        JeevProfileAddress facilityAddress = arg0.getData()
                                .getAddress();
                        StringBuilder addressValue = new StringBuilder();

                        if (facilityAddress != null) {
                            if (!CommonCode.isNullOrEmpty(facilityAddress
                                    .getAddressLine1()))
                                ;
                            addressValue.append(facilityAddress.getAddressLine1()
                                    + ", ");
                            if (!CommonCode.isNullOrEmpty(facilityAddress.getArea()))
                                ;
                            addressValue.append(facilityAddress.getArea() + ", ");
                            if (!CommonCode.isNullOrEmpty(facilityAddress.getCity()))
                                ;
                            addressValue.append(facilityAddress.getCity() + ", ");
                            if (!CommonCode.isNullOrEmpty(facilityAddress.getState()))
                                ;
                            addressValue.append(facilityAddress.getState() + ", ");
                            if (!CommonCode.isNullOrEmpty(facilityAddress.getZipCode()))
                                ;
                            addressValue.append(facilityAddress.getZipCode() + ", ");
                            if (!CommonCode.isNullOrEmpty(facilityAddress.getCountry()))
                                ;
                            addressValue.append(facilityAddress.getCountry() + ", ");
                            address.setText((addressValue.toString()).replaceAll(",$",
                                    ""));
                        }
                    }
                });

    }

    private void getServiceConfigurations(String pro_id) {
        RestAdapter serviceConfigAdapter = new RestAdapter.Builder()
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_config_interface = serviceConfigAdapter
                .create(ServiceRequisition.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog_new");
        newFragment.setCancelable(false);
        service_config_interface.getServiceConfigurations(locationManager.getUserLocation(), authToken,
                pro_id.trim(),

                new Callback<ServiceConfigInfo>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(Appointments.this))) {
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

                            fillAppointmentTypeSpinner();

                        }
                    }
                });

    }

    // get professional facilities
    private void getFacilities() {

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(
                JeevOMUtil.baseUrl).build();
        ProfessionalProfile profile = adapter.create(ProfessionalProfile.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        profile.getProfessionalProfile(gson.toJson(locationManager.getUserLocation()).toString(), authToken, profileId.trim(),

                new Callback<ProfileProfessionalResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(Appointments.this))) {
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
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            SearchResultsResponse searchResultsResponse = gson
                                    .fromJson(json, SearchResultsResponse.class);
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
                    public void success(ProfileProfessionalResult arg0, Response arg1) {
                        haveFacilities = true;
                        newFragment.dismissAllowingStateLoss();
                        professionalId = arg0.getData().getId();
                        professionalFacilities = arg0.getData();
                        if (facilitiesList.size() > 0) {
                            facilitiesList.clear();
                        }
                        facilityProfiles = professionalFacilities.getFacilityProfiles();

                        for (ProfileFacility object : facilityProfiles) {

                            facilitiesList.add(object.getName());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                Appointments.this, R.layout.spinner_item,
                                facilitiesList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        facility_spinner.setAdapter(adapter);

                        // else if (service_requestor.equals("facility")) {
                        // getFacilityServiceConfigurations(profileId);
                        // }
                    }
                });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.date_time_layout:

                Intent slot_intent = new Intent(this, SlotsScreen.class);
                slot_intent.putExtra("profileId", profileId);
                slot_intent.putExtra("service_requestor", service_requestor);
                slot_intent.putExtra("serviceConfig", serviceConfig);
                slot_intent.putExtra("selectedFacilityId", selectedFacilityId);
                slot_intent.putExtra("authToken", authToken);
                slot_intent.putExtra("selectedAppointmentTypeValue",
                        selectedAppointmentTypeValue);

                startActivityForResult(slot_intent, 100);

                break;
            case R.id.btn_proceed:
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
        if (CommonCode.isNullOrEmpty(bookedTime)) {
            Crouton.makeText(Appointments.this, "Please Select a Slot",
                    Style.ALERT).show();
            return;
        }

        makeCall();

    }

    private void makeCall() {

        requestSendMessage = new CallToActionRequest();

        requestSendMessage.setIsAppointment(true);
        requestSendMessage.setId(0);
        requestSendMessage
                .setServiceConfigurationId(String.valueOf(service_id));
        requestSendMessage.setIsSupportRequest(false);
        requestSendMessage.setFees(String.valueOf(fees));

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

        if (service_requestor.equalsIgnoreCase("professional")) {
            requestSendMessage.setToProfessionalId(String.valueOf(id));
            if (serviceConfig.equals("SG1")) {
                requestSendMessage.setWhereFacilityId(selectedFacilityId);
            }
        } else {
            requestSendMessage.setToFacilityId(String.valueOf(facilityId));
        }

        // appointment object
        CallToActionAppointment appointment = new CallToActionAppointment();
        appointment.setId(0);
        appointment.setDate(date);
        appointment.setTime(bookedTime);
        appointment.setSessionId(String.valueOf(sessionId));
        appointment.setSlotId(String.valueOf(slotId));

        requestSendMessage.setAppointmentDetails(appointment);

        goToStepTwo();

    }

    private void goToStepTwo() {
        Intent stepTwoIntent = new Intent(this, AppointmentStepTwo.class);
        Bundle bundle = new Bundle();
        bundle.putString("name_appointment_type", selectedAppointmentTypeValue);
        bundle.putString("img_url", url);
        bundle.putString("name", name_professional_facility);
        bundle.putSerializable("requestObject", requestSendMessage);
        bundle.putString("date", date);
        bundle.putString("time", slotName);

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