package com.schoolteacher.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.dialog.GooglePlayAlert;
import com.schoolteacher.library.main.AddressBasedMap;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.location.GPSTracker;
import com.schoolteacher.mylibrary.location.SearchAddressByLatLng;
import com.schoolteacher.mylibrary.model.UserAddressData;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.session.Home;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.BloodGroup;
import com.schoolteacher.pojos.BloodGroupResponse;
import com.schoolteacher.pojos.Consumer;
import com.schoolteacher.pojos.ConsumerContactInfo;
import com.schoolteacher.pojos.ConsumerDetailsResponse;
import com.schoolteacher.pojos.ConsumerEmergencyDetails;
import com.schoolteacher.pojos.ConsumerMemberDetails;
import com.schoolteacher.pojos.ConsumerProfile;
import com.schoolteacher.pojos.ConsumerUpdateRequestModel;
import com.schoolteacher.pojos.DocumentBody;
import com.schoolteacher.pojos.DropDownObject;
import com.schoolteacher.pojos.DropDownResponse;
import com.schoolteacher.pojos.MemberEmergencyDetail;
import com.schoolteacher.pojos.UpdateConsumerResponse;
import com.schoolteacher.pojos.UploadImageResponse;
import com.schoolteacher.service.Member;
import com.schoolteacher.service.ServiceRequisition;
import com.schoolteacher.service.UploadProfImageInterface;
import com.schoolteacher.util.JeevomUtilsClass;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.ArrayList;
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

public class BasicInformationFragment extends Fragment implements
        OnClickListener, OnItemSelectedListener, OnDateSetListener {
    UserCurrentLocationManager locationManager;

    public Home hsession;
    private ProgressBar upload_image_progressbar;
    private AlertDialog.Builder builder;
    private EditText emergency_phone_value, emergency_email_value,
            emergency_first_name_value, emergency_last_name_value,
            address_edit_text, user_first_name_value, user_last_name_value,
            weight_value, height_feet, height_inch, height_cms,
            user_phone_value, user_email_value, identity_mark, address;
    MultiAutoCompleteTextView medical_condition, allergies;
    CircleImageView consumer_image;
    TextView user_dob_value;
    Button btn_user_save_skip, btn_user_save;
    View rootView;
    private GPSTracker gpsTracker;
    DialogFragment newFragment;
    private UserAddressData mapAddress;
    String addressLine1, country, state, city, area, address_value;
    private double latitude, longitude;
    static Address address_map;
    static LatLng userLocation;
    Spinner blood_spinner, title_first_name_spinner, weight_unit, height_unit,
            emergency_title_spinner;
    RadioGroup gender_group;
    String gender, nameTitleValue, weightUnitValue, heightUnitValue,
            emergencyTitleValue, emergencySecondTitleValue;
    List<String> titleSpinnerItems = new ArrayList<String>();
    List<String> weightSpinnerItems = new ArrayList<String>();
    List<String> heightSpinnerItems = new ArrayList<String>();
    List<String> bloodSpinnerItems = new ArrayList<String>();
    // SessionManager session;
    JeevomSession jeevom_session;
    String memberId, consumerId, dateFormatted;
    GlobalAlert globalAlert;
    MemberEmergencyDetail memberEmergencyDetail;
    ImageView dob_image, edit_image;
    long date = 0l;
    public static final String DATEPICKER_TAG = "datepicker";
    Gson gson;
    List<DropDownObject> allergiesList = new ArrayList<DropDownObject>();
    List<String> allegiesNames = new ArrayList<String>();
    List<DropDownObject> medicalConditionsList = new ArrayList<DropDownObject>();
    List<String> medicalConditionNames = new ArrayList<String>();
    List<ConsumerEmergencyDetails> emergencyDetails = new LinkedList<ConsumerEmergencyDetails>();
    int contactId, bloodValue;
    ViewPager viewPager;
    ConsumerDetailsResponse consumerDetails;
    List<BloodGroup> bloodGroups;
    ValuesManager valuesManager;
    GooglePlayAlert googleAlert;

    String authToken = null;

    public BasicInformationFragment() {

    }

    public BasicInformationFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_basic_information,
                container, false);
        jeevom_session = new JeevomSession(getActivity()
                .getApplicationContext());
        hsession = new Home(getActivity()
                .getApplicationContext());
        gson = new GsonBuilder().create();
        locationManager = new UserCurrentLocationManager(getActivity().getApplicationContext());
        valuesManager = new ValuesManager(getActivity().getApplicationContext());
        googleAlert = new GooglePlayAlert(getActivity());
        if (!CommonCode.isNullOrEmpty(jeevom_session.getAuthToken())) {
            authToken = "Basic " + jeevom_session.getAuthToken();
        }




        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Basic Information Fragment");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        globalAlert = new GlobalAlert(getActivity());
        gson = new GsonBuilder().setPrettyPrinting().create();

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // memberId =
        // jeevom_session.getUserDetails().get(SessionManager.KEY_ID);
        memberId = String.valueOf(jeevom_session.getMemberId());
        consumerId = jeevom_session.getConsumerIds().get(
                JeevomSession.JEEVOM_CONSUMER_ID);

        // imageview
        consumer_image = (CircleImageView) rootView
                .findViewById(R.id.consumer_image);
        edit_image = (ImageView) rootView.findViewById(R.id.edit_image);
        edit_image.setOnClickListener(this);
        upload_image_progressbar = (ProgressBar) rootView
                .findViewById(R.id.image_progress);
        // Button to save details
        btn_user_save = (Button) rootView.findViewById(R.id.btn_user_save);
        btn_user_save.setOnClickListener(this);
        btn_user_save_skip = (Button) rootView
                .findViewById(R.id.btn_user_save_skip);
        btn_user_save_skip.setOnClickListener(this);

        // address field
        address_edit_text = (EditText) rootView.findViewById(R.id.address);
        address_edit_text.setOnClickListener(this);

        emergency_first_name_value = (EditText) rootView
                .findViewById(R.id.emergency_first_name_value);
        emergency_last_name_value = (EditText) rootView
                .findViewById(R.id.emergency_last_name_value);
        emergency_email_value = (EditText) rootView
                .findViewById(R.id.emergency_email_value);

        emergency_phone_value = (EditText) rootView
                .findViewById(R.id.emergency_phone_value);

        user_first_name_value = (EditText) rootView
                .findViewById(R.id.user_first_name_value);
        user_last_name_value = (EditText) rootView
                .findViewById(R.id.user_last_name_value);
        weight_value = (EditText) rootView.findViewById(R.id.weight_value);
        height_feet = (EditText) rootView.findViewById(R.id.height_feet);
        height_inch = (EditText) rootView.findViewById(R.id.height_inch);
        height_cms = (EditText) rootView.findViewById(R.id.height_cms);
        user_phone_value = (EditText) rootView
                .findViewById(R.id.user_phone_value);
        user_phone_value.setEnabled(false);
        user_email_value = (EditText) rootView
                .findViewById(R.id.user_email_value);
        user_email_value.setEnabled(false);

        // Multi Auto complete text view
        medical_condition = (MultiAutoCompleteTextView) rootView
                .findViewById(R.id.medical_condition);
        medical_condition.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

            }
        });
        // Multi Auto complete text view
        allergies = (MultiAutoCompleteTextView) rootView
                .findViewById(R.id.allergies);
        allergies.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

            }
        });

        identity_mark = (EditText) rootView.findViewById(R.id.identity_mark);
        dob_image = (ImageView) rootView.findViewById(R.id.dob_image);
        dob_image.setOnClickListener(this);
        address = (EditText) rootView.findViewById(R.id.address);

        // gender
        gender_group = (RadioGroup) rootView.findViewById(R.id.gender_group);

        gender_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button_male) {
                    gender = "Male";
                } else if (checkedId == R.id.radio_button_female) {
                    gender = "Female";
                } else if (checkedId == R.id.radio_button_other) {
                    gender = "Other";
                }
            }
        });
        user_dob_value = (TextView) rootView.findViewById(R.id.user_dob_value);
        // title spinner
        title_first_name_spinner = (Spinner) rootView
                .findViewById(R.id.title_first_name_spinner);
        emergency_title_spinner = (Spinner) rootView
                .findViewById(R.id.emergency_title_spinner);
        blood_spinner = (Spinner) rootView.findViewById(R.id.blood_spinner);
        blood_spinner.setOnItemSelectedListener(this);
        emergency_title_spinner.setOnItemSelectedListener(this);
        title_first_name_spinner.setOnItemSelectedListener(this);
        // set title items in spinner
        setTitleInSpinner();

        weight_unit = (Spinner) rootView.findViewById(R.id.weight_unit);
        weight_unit.setOnItemSelectedListener(this);
        // set title items in spinner
        setWeightInSpinner();

        height_unit = (Spinner) rootView.findViewById(R.id.height_unit);
        height_unit.setOnItemSelectedListener(this);
        // set title items in spinner
        setHeightUnitInSpinner();
        gpsTracker = new GPSTracker(getActivity());




        // get specific medical conditions
        getMedicalConditions();



    }

    // populate relation spinner method
    private void populateBloodGroupSpinner() {

        RestAdapter bloodAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("blood"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = bloodAdapter

                .create(Member.class);
        getConsumerService.getBloodGroup(
                authToken,
                (new Callback<BloodGroupResponse>() {

                    @Override
                    public void success(BloodGroupResponse arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            bloodGroups = arg0.getData().getBloodGroups();
                        }
                        if (bloodGroups.size() > 0) {
                            for (BloodGroup bloodObject : bloodGroups) {
                                bloodSpinnerItems.add(bloodObject.getName());
                            }
                        }
                        if (bloodSpinnerItems.size() > 0) {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                    getActivity(), R.layout.spinner_item,
                                    bloodSpinnerItems);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            blood_spinner.setAdapter(adapter);
                        }

                        fillUserDetails(consumerDetails);
                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(getActivity()))) {
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
                            BloodGroupResponse responseValue = gson.fromJson(
                                    json, BloodGroupResponse.class);
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
                }));

    }

    private void getConsumerDetails(String consumerId2) {
        RestAdapter consumerAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("consumer")).setLogLevel(LogLevel.FULL)
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = consumerAdapter
                .create(Member.class);
        getConsumerService.getConsumer(
                authToken, consumerId2,
                String.valueOf(valuesManager.getVersion()),
                new Callback<ConsumerDetailsResponse>() {

                    @Override
                    public void success(ConsumerDetailsResponse arg0,
                                        Response arg1) {
                        if (arg0.getData() != null) {
                            // populate blood group Spinner
                            populateBloodGroupSpinner();
                            consumerDetails = arg0;

                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(getActivity()))) {
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

    private void showGooglePlayAlert(String message) {
        googleAlert.show();
        googleAlert.setMessage(message);
    }

    private void getSpecificAllergies() {

        RestAdapter allergiesAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("allergies"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition getConsumerService = allergiesAdapter
                .create(ServiceRequisition.class);
        getConsumerService.getAllergiesForDropDown(
                authToken,
                new Callback<DropDownResponse>() {

                    @Override
                    public void success(DropDownResponse arg0, Response arg1) {
                        if (arg0.getData().getDataDictionary().size() > 0)

                            allergiesList.addAll(arg0.getData()
                                    .getDataDictionary());

                        for (DropDownObject object : allergiesList) {
                            allegiesNames.add(object.getTerm());
                        }

                        allergies
                                .setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                        ArrayAdapter<String> allergiesAdapter = new ArrayAdapter<String>(
                                getActivity(),
                                android.R.layout.simple_dropdown_item_1line,
                                allegiesNames);
                        allergiesAdapter
                                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        allergies.setThreshold(1);
                        allergies.setAdapter(allergiesAdapter);

                        // get consumer profile
                        getConsumerDetails(consumerId);
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(getActivity()))) {
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

    private void getMedicalConditions() {

        RestAdapter medicalAllergiesAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("medical"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition getConsumerService = medicalAllergiesAdapter
                .create(ServiceRequisition.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getActivity().getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        getConsumerService.getMedicalConditionsForDropDown(
                authToken,
                new Callback<DropDownResponse>() {

                    @Override
                    public void success(DropDownResponse arg0, Response arg1) {

                        if (arg0.getData().getDataDictionary().size() > 0)
                            medicalConditionsList.addAll(arg0.getData()
                                    .getDataDictionary());

                        for (DropDownObject object : medicalConditionsList) {
                            medicalConditionNames.add(object.getTerm());
                        }

                        medical_condition
                                .setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                        ArrayAdapter<String> medicalConditionsAdapter = new ArrayAdapter<String>(
                                getActivity(),
                                android.R.layout.simple_dropdown_item_1line,
                                medicalConditionNames);
                        medicalConditionsAdapter
                                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        medical_condition.setThreshold(1);
                        medical_condition.setAdapter(medicalConditionsAdapter);

                        // get specific medical conditions autocomplete values
                        getSpecificAllergies();
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(getActivity()))) {
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
                            DropDownResponse MembershipAuthenticateErrors = gson
                                    .fromJson(json, DropDownResponse.class);
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

    private void setHeightUnitInSpinner() {
        // heightSpinnerItems.add("Cms");
        if (heightSpinnerItems.size() > 0) {
            heightSpinnerItems.clear();
        }
        heightSpinnerItems.add("Ft. Inch");

        if (titleSpinnerItems.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(), R.layout.spinner_item, heightSpinnerItems);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            height_unit.setAdapter(adapter);
        }
    }

    private void setTitleInSpinner() {
        titleSpinnerItems.add("Mr.");
        titleSpinnerItems.add("Mrs.");
        titleSpinnerItems.add("Dr.");
        titleSpinnerItems.add("Ms.");

        if (titleSpinnerItems.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(), R.layout.spinner_item, titleSpinnerItems);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            title_first_name_spinner.setAdapter(adapter);
            emergency_title_spinner.setAdapter(adapter);

        }
    }

    private void setWeightInSpinner() {
        if (weightSpinnerItems.size() > 0) {
            weightSpinnerItems.clear();
        }
        weightSpinnerItems.add("Kgs");
        // weightSpinnerItems.add("Pound");

        if (weightSpinnerItems.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(), R.layout.spinner_item, weightSpinnerItems);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            weight_unit.setAdapter(adapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long arg3) {
        int id = parent.getId();
        if (id == R.id.title_first_name_spinner) {
            if (titleSpinnerItems.size() > 0) {
                nameTitleValue = (titleSpinnerItems.get(position));

            }
        }
        if (id == R.id.blood_spinner) {
            if (bloodSpinnerItems.size() > 0) {
                for (BloodGroup blood : bloodGroups) {
                    if (blood.getName().equals(bloodSpinnerItems.get(position))) {
                        bloodValue = blood.getId();
                    }

                }

            }
        }
        if (id == R.id.emergency_title_spinner) {
            if (titleSpinnerItems.size() > 0) {

                emergencyTitleValue = (titleSpinnerItems.get(position));

            }
        }

        if (id == R.id.weight_unit) {
            if (weightSpinnerItems.size() > 0) {

                weightUnitValue = (weightSpinnerItems.get(position));

            }
        }
        if (id == R.id.height_unit) {
            if (heightSpinnerItems.size() > 0) {

                heightUnitValue = (heightSpinnerItems.get(position));

                if (heightUnitValue.equals("Cms")) {
                    height_cms.setVisibility(View.VISIBLE);
                    height_feet.setVisibility(View.GONE);
                    height_inch.setVisibility(View.GONE);
                } else {
                    height_cms.setVisibility(View.GONE);
                    height_feet.setVisibility(View.VISIBLE);
                    height_inch.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_image:
                selectImage();
                break;
            case R.id.btn_user_save_skip:
                SaveInformation("skip");
                break;
            case R.id.btn_user_save:
                SaveInformation("continue");
                break;
            case R.id.address:
                if (Connectivity.checkConnectivity(getActivity())) {

                    // if (!gpsTracker.canGetLocation()) {
                    // gpsTracker.showSettingsAlert();
                    // } else {
                    newFragment = ProgressDialogFragment.newInstance();
                    newFragment.show(getActivity().getSupportFragmentManager(),
                            "dialog");
                    newFragment.setCancelable(false);
                    LatLng myLocation = new LatLng(gpsTracker.getLatitude(),
                            gpsTracker.getLongitude());

                    SearchAddressByLatLng addressByLatLng = new SearchAddressByLatLng(
                            getActivity());
                    addressByLatLng.execute(myLocation);
                    // }
                } else {
                    Crouton.makeText(getActivity(), JeevOMUtil.INTERNET_CONNECTION,
                            Style.ALERT).show();
                }
                break;

            case R.id.dob_image:
                showDatePickerDialog();
                break;
        }

    }

    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery",
                "Cancel"};

        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent,
                            JeevOMUtil.CAMERA_REQUEST_CODE);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent,
                            JeevOMUtil.GALLERY_REQUEST_CODE);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private String checkEmergencyFirst() {
        String result = null;

        if (!CommonCode.isNullOrEmpty(emergency_first_name_value.getText()
                .toString().trim())
                && CommonCode.isNullOrEmpty(emergency_phone_value.getText()
                .toString().trim())) {
            result = "Please enter valid 10 digit contact number for emergency contact";
        } else if (!CommonCode.isNullOrEmpty(emergency_phone_value.getText()
                .toString().trim())
                && !(CommonCode.validateContactNo(emergency_phone_value
                .getText().toString().trim()))) {
            result = "Please enter valid 10 digit contact number for emergency contact";
        } else if (!CommonCode.isNullOrEmpty(emergency_last_name_value
                .getText().toString())
                && CommonCode.isNullOrEmpty(emergency_first_name_value
                .getText().toString().trim())) {
            result = "Please enter first name of emergency contact";
        } else if (!CommonCode.isNullOrEmpty(emergency_phone_value.getText()
                .toString().trim())
                && CommonCode.isNullOrEmpty(emergency_first_name_value
                .getText().toString().trim())) {
            result = "Please enter first name of emergency contact";
        } else {
            if (memberEmergencyDetail != null) {
                ConsumerEmergencyDetails obj = new ConsumerEmergencyDetails(
                        memberEmergencyDetail.getId(),
                        Integer.valueOf(memberId), emergencyTitleValue,
                        emergency_first_name_value.getText().toString().trim(),
                        emergency_last_name_value.getText().toString().trim(),
                        emergency_email_value.getText().toString().trim(),
                        emergency_phone_value.getText().toString().trim(), true);
                emergencyDetails.add(obj);
            } else {

                ConsumerEmergencyDetails obj = new ConsumerEmergencyDetails(0,
                        Integer.valueOf(memberId), emergencyTitleValue,
                        emergency_first_name_value.getText().toString().trim(),
                        emergency_last_name_value.getText().toString().trim(),
                        emergency_email_value.getText().toString().trim(),
                        emergency_phone_value.getText().toString().trim(), true);
                emergencyDetails.add(obj);
            }

        }
        return result;

    }

    public void respondCurrentLocation(Address address_map, LatLng userLocation) {

        gpsTracker.stopUsingGPS();
        newFragment.dismissAllowingStateLoss();
        launchMapScreen(address_map, userLocation);
    }

    private void launchMapScreen(Address address_map, LatLng userLocation) {
        Intent addmapIntent = new Intent(getActivity(), AddressBasedMap.class);
        UserAddressData userAddressData = null;
        if (mapAddress == null) {
            userAddressData = new UserAddressData();
            addmapIntent.putExtra("user_address_data", userAddressData);
        } else {
            addmapIntent.putExtra("user_address_data", mapAddress);
        }
        addmapIntent.putExtra("address", address_map);
        addmapIntent.putExtra("latlng", userLocation);
        startActivityForResult(addmapIntent, AddressBasedMap.REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AddressBasedMap.REQUEST_CODE
                && resultCode == Activity.RESULT_OK) {
            mapAddress = (UserAddressData) data
                    .getSerializableExtra("map_address");
            country = mapAddress.getCountry();

            state = mapAddress.getState();

            city = mapAddress.getCity();

            area = mapAddress.getArea();

            address_value = mapAddress.getAddress();
            latitude = mapAddress.getLattitude();
            longitude = mapAddress.getLongitude();
            try {
                address_edit_text.setText(address_value + "," + area + ","
                        + city + "," + state + "," + country);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == JeevOMUtil.CAMERA_REQUEST_CODE) {
                    uploadImageFromCamera();
                } else if (requestCode == JeevOMUtil.GALLERY_REQUEST_CODE) {
                    UploadImageFromGallery(data);
                }
            }

        }
    }

    private void UploadImageFromGallery(Intent data) {
        Uri currImageURI = data.getData();
        if (currImageURI != null) {
            uploadImageProcess(currImageURI);
        }
    }

    // Convert the image URI to the direct file system path of the image file
    public String getRealPathFromURI(Uri contentUri) {
        String result = null;
        Cursor cursor = getActivity().getContentResolver().query(contentUri,
                null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file
            // path
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor
                    .getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            if (idx != -1) {
                result = cursor.getString(idx);
            }
            cursor.close();
        }
        return result;
    }

    private void uploadImageProcess(Uri currImageURI) {
        if (currImageURI != null) {
            String realPathFromURI = getRealPathFromURI(currImageURI);
            if (realPathFromURI == null) {
                showAlert("image not accessible");
            } else {
                String documentContent = JeevomUtilsClass.getDocumentContent(realPathFromURI);
                if (documentContent == null) {
                    showAlert("image not accessible");
                } else {
                    postCallForUploadImage(documentContent);
                }
            }
        } else {
            showAlert(JeevOMUtil.IMAGE_NOT_SELECTEDE);
        }
    }

    private void uploadImageFromCamera() {
        File f = new File(Environment.getExternalStorageDirectory().toString());
        for (File temp : f.listFiles()) {
            if (temp.getName().equals("temp.jpg")) {
                f = temp;
                break;
            }
        }
        String absolutePath = f.getAbsolutePath();
        if (absolutePath != null) {
            String documentContent = JeevomUtilsClass.getDocumentContent(absolutePath);
            if (documentContent == null) {
                showAlert("image not accessible");
            } else {
                postCallForUploadImage(documentContent);
            }
        }

        try {
            f.delete();
        } catch (Exception e) {
        }
    }

    private void postCallForUploadImage(String documentContent) {
        RestAdapter uploadRestAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("upload_image"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        upload_image_progressbar.setVisibility(View.VISIBLE);
        DocumentBody documentBody = new DocumentBody();
        documentBody.setBinaryContent(documentContent);
        documentBody.setDocumentFileName("profile_pic.jpg");
        // documentBody.setUserId(Integer.valueOf(jeevom_session.getConsumerIds().get(JeevomLocalSession.JEEVOM_CONSUMER_ID)));
        documentBody.setUserId(jeevom_session.getMemberId());

        UploadProfImageInterface uploadProfImageInterface = uploadRestAdapter
                .create(UploadProfImageInterface.class);
        uploadProfImageInterface.upload(
                authToken, documentBody,
                new Callback<UploadImageResponse>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        upload_image_progressbar.setVisibility(View.GONE);
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(getActivity()))) {
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
                            UploadImageResponse uploadImageErrors = gson
                                    .fromJson(json, UploadImageResponse.class);
                            String code = uploadImageErrors.getStatus()
                                    .getCode();
                            String message = uploadImageErrors.getStatus()
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
                            } else if (code.equals("BE-1003")) {
                                showAlert(message);
                            }
                        }

                    }

                    @Override
                    public void success(UploadImageResponse arg0, Response arg1) {
                        upload_image_progressbar.setVisibility(View.GONE);

                        if (!CommonCode.isNullOrEmpty(arg0.getData()
                                .getFileUrl())) {
                            Picasso.with(getActivity())
                                    .load(arg0.getData().getFileUrl())
                                    .placeholder(R.drawable.jeevom_back)
                                    .error(R.drawable.jeevom_back)
                                    .into(consumer_image);
                            hsession.setKEY_PhotoURL(arg0.getData().getFileUrl());
                        }
                    }
                });
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    private double convertToCentimeter(int feet, int inch) {
        return ((feet * 12) + inch) * 2.54;
    }

    private void showDatePickerDialog() {
        final DatePickerDialog datePickerDialog;
        final Calendar calendar = Calendar.getInstance();

        if (date == 0l) {
            datePickerDialog = DatePickerDialog.newInstance(this,
                    calendar.get(Calendar.YEAR) - 18,
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), false);
        } else {
            calendar.setTimeInMillis(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = DatePickerDialog.newInstance(this, year, month,
                    day, false);
        }
        datePickerDialog.setYearRange(1902, 2099);
        datePickerDialog.setCloseOnSingleTapDay(false);
        datePickerDialog.show(getActivity().getSupportFragmentManager(),
                DATEPICKER_TAG);
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year,
                          int month, int day) {

        user_dob_value.setText(CommonCode.monthYear(day, month, year));
        dateFormatted = (month + 1) + "/" + day + "/" + year;
        date = CommonCode.convertToMilliseconds(year, month, day);

    }

    private String cmsToFeet(String height) {
        Double heightInCms = (Double.valueOf(height)) / 2.54;
        String value = String.valueOf(heightInCms.intValue() / 12);
        return value;

    }

    private String cmsToInch(String height) {
        Double heightInCms = (double) Math
                .round(((Double.valueOf(height)) / 2.54));
        String value = String.valueOf(heightInCms.intValue() % 12);
        return value;

    }

    private void fillUserDetails(ConsumerDetailsResponse arg0) {

        String img = null;
        if (!(CommonCode.isNullOrEmpty(arg0.getData().getPhotoURL()))) {
            img = arg0.getData().getPhotoURL().replace(" ", "%20");
        }

        Picasso.with(getActivity()).load(img)
                .placeholder(R.drawable.jeevom_back)
                .error(R.drawable.jeevom_back).into(consumer_image);

        // set first name
        if (!CommonCode.isNullOrEmpty(arg0.getData().getFirstName())) {
            user_first_name_value.setText(arg0.getData().getFirstName());
            user_first_name_value.clearFocus();
            user_first_name_value.setSelected(false);
        }

        // set last name
        if (!CommonCode.isNullOrEmpty(arg0.getData().getLastName())) {
            user_last_name_value.setText(arg0.getData().getLastName());
            user_last_name_value.clearFocus();
        }




        // set cell number
        if (!CommonCode.isNullOrEmpty(arg0.getData().getCellNumber())) {
            boolean userPhoneVerifyStatus = jeevom_session
                    .getUserPhoneVerifyStatus();
            if (userPhoneVerifyStatus) {
                user_phone_value.setEnabled(false);
                user_phone_value.setFocusable(false);
                user_phone_value.setFocusableInTouchMode(false);



          }
        user_phone_value.setText(arg0.getData().getCellNumber());
            user_phone_value.clearFocus();
        }else{
            user_phone_value.setEnabled(true);
            user_phone_value.setFocusable(true);
            user_phone_value.setFocusableInTouchMode(true);

        }
        // set email
        if (!CommonCode.isNullOrEmpty(arg0.getData().getEmail())) {
            boolean userEmailVerifyStatus = jeevom_session
                    .getUserEmailVerifyStatus();
            if (userEmailVerifyStatus) {
                user_email_value.setEnabled(false);
                user_email_value.setFocusable(false);
                user_email_value.setFocusableInTouchMode(false);


       }

            user_email_value.setText(arg0.getData().getEmail());
            user_email_value.clearFocus();
        }else{
            user_email_value.setEnabled(true);
            user_email_value.setFocusable(true);
            user_email_value.setFocusableInTouchMode(true);

        }

        // set name title
        if (!CommonCode.isNullOrEmpty(arg0.getData().getTitle())) {
            title_first_name_spinner.setSelection(titleSpinnerItems
                    .indexOf(arg0.getData().getTitle()));
        }
        for (int i = 0; i < bloodGroups.size(); i++) {

            if (arg0 != null) {
                if (bloodGroups.get(i).getId() == arg0.getData()
                        .getBloodGroupType()) {
                    blood_spinner.setSelection(i);
                }
            }
        }
        // set height spinner
        if (!CommonCode.isNullOrEmpty(arg0.getData().getHeightUnit())) {

            if (arg0.getData().getHeightUnit().trim().equalsIgnoreCase("Cms")) {

                height_unit.setSelection(0);
                height_cms.setVisibility(View.GONE);
                height_feet.setVisibility(View.VISIBLE);
                height_inch.setVisibility(View.VISIBLE);
                // set height in feet
                if (!CommonCode.isNullOrEmpty(arg0.getData().getHeight())) {

                    if (arg0.getData().getHeight().equals("0")) {
                        height_feet.setText("");
                    } else {
                        String cmsToFeet = cmsToFeet(arg0.getData().getHeight());
                        height_feet.setText(cmsToFeet);
                        height_feet.clearFocus();

                        String cmsToInch = cmsToInch(arg0.getData().getHeight());
                        // set height in inches
                        height_inch.setText(cmsToInch);
                        height_inch.clearFocus();
                    }
                }

            } else {
                height_unit.setSelection(0);
                height_cms.setVisibility(View.GONE);
                height_feet.setVisibility(View.VISIBLE);
                height_inch.setVisibility(View.VISIBLE);
                // set height in feet
                if (!CommonCode.isNullOrEmpty(arg0.getData().getHeight())) {
                    if (arg0.getData().getHeight().equals("0")) {
                        height_feet.setText("");
                        height_feet.clearFocus();
                    } else {
                        height_feet.setText(cmsToFeet(arg0.getData()
                                .getHeight()));
                        height_feet.clearFocus();
                        // set height in inches
                        height_inch.setText(cmsToInch(arg0.getData()
                                .getHeight()));
                        height_inch.clearFocus();
                    }
                }
            }
        }

        // set weight
        if (!CommonCode.isNullOrEmpty(arg0.getData().getWeight())) {
            weight_value.setText(arg0.getData().getWeight());
            weight_value.clearFocus();
        }

        // set medical conditions

        // medical_value.setText(ViewProfile.consumer.getData().getMedicalConditions().replace("|",
        // ","));

        if (!CommonCode.isNullOrEmpty(arg0.getData().getMedicalConditions())) {
            List<String> splitStringByPipe = CommonCode.splitStringByPipe(arg0
                    .getData().getMedicalConditions());
            List<String> list = new ArrayList<>();
            if (splitStringByPipe.size() > 0) {
                for (String split : splitStringByPipe) {
                    if (!(list.contains(split.trim()))) {
                        list.add(split.trim());
                    }
                }
            }
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    builder.append(list.get(i));
                } else {
                    builder.append("," + list.get(i));
                }
            }
            medical_condition.setText(builder.toString());
            medical_condition.clearFocus();
        }

        // set allergies
        if (!CommonCode.isNullOrEmpty(arg0.getData().getAllergies())) {

            List<String> splitStringByPipe = CommonCode.splitStringByPipe(arg0
                    .getData().getAllergies());
            List<String> list = new ArrayList<>();
            if (splitStringByPipe.size() > 0) {
                for (String split : splitStringByPipe) {
                    if (!(list.contains(split.trim()))) {
                        list.add(split.trim());
                    }
                }
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    builder.append(list.get(i));
                } else {
                    builder.append("," + list.get(i));
                }

            }
            allergies.setText(builder.toString());
            allergies.clearFocus();

        }

        // set Address
        if (arg0.getData().getMemberContactInformation() != null) {

            contactId = arg0.getData().getMemberContactInformation().getId();
            StringBuilder addressValue = new StringBuilder();
            addressLine1 = arg0.getData().getMemberContactInformation()
                    .getAddressLine1();
            if (!CommonCode.isNullOrEmpty(addressLine1)) {
                addressValue.append(addressLine1);
            }

            area = arg0.getData().getMemberContactInformation().getArea();
            if (!CommonCode.isNullOrEmpty(area)) {
                addressValue.append(area);
            }
            city = arg0.getData().getMemberContactInformation().getCity();
            if (!CommonCode.isNullOrEmpty(city)) {
                addressValue.append("," + city);
            }
            state = arg0.getData().getMemberContactInformation().getState();
            if (!CommonCode.isNullOrEmpty(state)) {
                addressValue.append("," + state);
            }
            country = arg0.getData().getMemberContactInformation().getCountry();
            if (!CommonCode.isNullOrEmpty(country)) {
                addressValue.append("," + country);
            }

            address_value = arg0.getData().getMemberContactInformation()
                    .getLandmark();
            latitude = arg0.getData().getMemberContactInformation()
                    .getLatitude();
            longitude = arg0.getData().getMemberContactInformation()
                    .getLongitude();

            if (!CommonCode.isNullOrEmpty(area + "," + city + "," + state + ","
                    + country)) {
                address_edit_text.setText(addressValue.toString());
                address_edit_text.clearFocus();
            }
        }

        // set gender
        if (!CommonCode.isNullOrEmpty(arg0.getData().getGender())) {
            if (arg0.getData().getGender().equalsIgnoreCase("male")) {
                ((RadioButton) gender_group.getChildAt(0)).setChecked(true);
                // gender_group.check(R.id.radio_button_male);
            } else if (arg0.getData().getGender().equalsIgnoreCase("female")) {
                ((RadioButton) gender_group.getChildAt(1)).setChecked(true);
                // gender_group.check(R.id.radio_button_female);
            } else {
                ((RadioButton) gender_group.getChildAt(2)).setChecked(true);
                // gender_group.check(R.id.radio_button_female);
            }
        }

        // set identity marks
        if (!CommonCode.isNullOrEmpty(arg0.getData().getIdentityMarks())) {
            identity_mark.setText(arg0.getData().getIdentityMarks());
            identity_mark.clearFocus();
        }
        // set date
        if (!CommonCode.isNullOrEmpty(arg0.getData().getDateOfBirth())) {

            try {

                int[] formatDT = CommonCode.formatDT(arg0.getData()
                        .getDateOfBirth());
                // int month = formatDT[1];
                // int day = formatDT[0];
                // int year = formatDT[2];
                user_dob_value.setText(CommonCode.monthYear(formatDT[0],
                        formatDT[1], formatDT[2]));
                date = CommonCode.convertToMilliseconds(formatDT[2],
                        formatDT[1], formatDT[0]);
            } catch (ParseException e) {
            }

        }

        // fill emergency details

        if (arg0.getData().getMemberEmergencyDetails() != null) {
            if (arg0.getData().getMemberEmergencyDetails().size() > 0) {
                memberEmergencyDetail = arg0.getData()
                        .getMemberEmergencyDetails().get(0);

                if (!CommonCode.isNullOrEmpty(memberEmergencyDetail
                        .getFirstName())) {
                    emergency_first_name_value.setText(memberEmergencyDetail
                            .getFirstName());
                    emergency_first_name_value.clearFocus();
                }
                if (!CommonCode.isNullOrEmpty(memberEmergencyDetail
                        .getLastName())) {
                    emergency_last_name_value.setText(memberEmergencyDetail
                            .getLastName());
                    emergency_last_name_value.clearFocus();
                }
                if (!CommonCode.isNullOrEmpty(memberEmergencyDetail.getEmail())) {
                    emergency_email_value.setText(memberEmergencyDetail
                            .getEmail());
                    emergency_email_value.clearFocus();
                }
                if (!CommonCode.isNullOrEmpty(memberEmergencyDetail
                        .getCellNumber())) {
                    emergency_phone_value.setText(memberEmergencyDetail
                            .getCellNumber());
                    emergency_phone_value.clearFocus();
                }

                if (!CommonCode.isNullOrEmpty(memberEmergencyDetail.getTitle())) {
                    emergency_title_spinner.setSelection(titleSpinnerItems
                            .indexOf(memberEmergencyDetail.getTitle()));
                }
            }
        }
    }

    private void SaveInformation(final String value) {
        // create update object

        if (CommonCode.isNullOrEmpty(user_first_name_value.getText().toString()
                .trim())) {
            Crouton.makeText(getActivity(), JeevOMUtil.EMPTY_FIRST_NAME,
                    Style.ALERT).show();
            return;
        }
        if (CommonCode.isNullOrEmpty(user_last_name_value.getText().toString()
                .trim())) {
            Crouton.makeText(getActivity(), JeevOMUtil.EMPTY_LAST_NAME,
                    Style.ALERT).show();
            return;
        }

        if (CommonCode
                .isNullOrEmpty(user_dob_value.getText().toString().trim())) {
            Crouton.makeText(getActivity(), JeevOMUtil.MANDATORY_DATE_OF_BIRTH,
                    Style.ALERT).show();
            return;
        }
        // else if (Strings.isNullOrEmpty(weight_value.getText().toString())) {
        // Crouton.makeText(getActivity(), "Please enter Weight",
        // Style.ALERT).show();
        // }

        if (!CommonCode.isNullOrEmpty(weight_value.getText().toString())) {
            if (Integer.valueOf(weight_value.getText().toString()) < 0) {
                Crouton.makeText(getActivity(),
                        "Please enter valid weight value ", Style.ALERT).show();
                return;
            }
        }

        if (!CommonCode.isNullOrEmpty(weight_value.getText().toString())) {
            if (Integer.valueOf(weight_value.getText().toString()) > 200) {
                Crouton.makeText(getActivity(),
                        "Weight can't be more than 200 kgs.", Style.ALERT)
                        .show();
                return;
            }
        }
        // else if (Strings.isNullOrEmpty(height_feet.getText().toString())) {
        // Crouton.makeText(getActivity(), "Please enter height in Feet",
        // Style.ALERT).show();
        // }

        if (!CommonCode.isNullOrEmpty(height_feet.getText().toString())) {
            if (Integer.valueOf(height_feet.getText().toString()) < 1) {
                Crouton.makeText(getActivity(),
                        "Minimum height should be 1 Foot", Style.ALERT).show();
                return;
            }
        }

        if (!CommonCode.isNullOrEmpty(height_feet.getText().toString())) {
            if (Integer.valueOf(height_feet.getText().toString()) > 10) {

                Crouton.makeText(getActivity(),
                        "Feet value can not be more then 10", Style.ALERT)
                        .show();
                return;
            }
        }

        if (!CommonCode.isNullOrEmpty(height_feet.getText().toString())) {
            if (!(CommonCode.isNullOrEmpty(height_inch.getText().toString()))
                    && Integer.parseInt(height_inch.getText().toString()) > 11) {

                Crouton.makeText(getActivity(),
                        "Inch. value should be maximum 11", Style.ALERT).show();
                return;
            }
        }
        if (!CommonCode.validateBirthDate(date)) {
            Crouton.makeText(getActivity(), JeevOMUtil.VALID_DOB, Style.ALERT)
                    .show();
            return;
        }

        if (CommonCode.isNullOrEmpty(gender)) {
            Crouton.makeText(getActivity(), JeevOMUtil.SELECT_GENDER,
                    Style.ALERT).show();
            return;
        }

        // if (CommonCode.isNullOrEmpty(address.getText().toString())) {
        // Crouton.makeText(getActivity(), "Address is Mandatory", Style.ALERT)
        // .show();
        // return;
        // }
        if (!CommonCode.isNullOrEmpty(checkEmergencyFirst())) {
            Crouton.makeText(getActivity(), checkEmergencyFirst().trim(),
                    Style.ALERT).show();
            return;
        }

        ConsumerUpdateRequestModel finalUpdateObject = new ConsumerUpdateRequestModel();
        finalUpdateObject.setId(consumerId);
        finalUpdateObject.setType("Consumer");

        ConsumerMemberDetails memberDetails = new ConsumerMemberDetails();
        memberDetails.setFirstName(user_first_name_value.getText().toString()
                .trim());
        memberDetails.setLastName(user_last_name_value.getText().toString()
                .trim());
        memberDetails.setCellNumber(user_phone_value.getText().toString()
                .trim());
        memberDetails.setDateOfBirth(dateFormatted);
        memberDetails.setGender(gender);
        memberDetails.setBloodGroupType(bloodValue);
        memberDetails.setTitle(nameTitleValue);
        memberDetails.setEmail(user_email_value.getText().toString().trim());

        ConsumerProfile profileObject = new ConsumerProfile();
        profileObject.setAllergies(allergies.getText().toString()
                .replace(",", "|"));

        if (heightUnitValue.equals("Cms")) {
            profileObject.setHeight(height_cms.getText().toString().trim());
        } else {

            if (!(CommonCode.isNullOrEmpty(height_feet.getText().toString()
                    .trim()))) {
                if (!(CommonCode.isNullOrEmpty(height_inch.getText().toString()
                        .trim()))) {
                    profileObject.setHeight(String.valueOf(convertToCentimeter(
                            Integer.valueOf(height_feet.getText().toString()
                                    .trim()),
                            Integer.valueOf(height_inch.getText().toString()
                                    .trim()))));
                } else {
                    profileObject.setHeight(String.valueOf(convertToCentimeter(
                            Integer.valueOf(height_feet.getText().toString()
                                    .trim()), 0)));
                }
            } else {
                profileObject.setHeight("0");
            }

        }

        profileObject.setHeightUnit(heightUnitValue);
        profileObject.setIdenitityMarks(identity_mark.getText().toString()
                .trim());
        profileObject.setMedicalConditions(medical_condition.getText()
                .toString().replace(",", "|"));
        profileObject.setWeight(weight_value.getText().toString().trim());

        ConsumerContactInfo contactObject = new ConsumerContactInfo();

        contactObject.setAddressLine1(address_value);
        contactObject.setArea(area);
        contactObject.setCity(city);
        contactObject.setCountry(country);
        contactObject.setLatitude(String.valueOf(latitude));
        contactObject.setLongitude(String.valueOf(longitude));
        contactObject.setState(state);
        contactObject.setZipCode(null);
        if (contactId > 0) {
            contactObject.setId(String.valueOf(contactId));
        } else {
            contactObject.setId("0");
        }

        // String contact = gson.toJson(contactObject).toString();

        Consumer consumer = new Consumer();
        consumer.setConsumerContactInfo(gson.toJson(contactObject).toString());
        consumer.setConsumerProfile(gson.toJson(profileObject).toString());
        consumer.setMemberDetails(gson.toJson(memberDetails).toString());
        consumer.setMemberEmergencyDetails(gson.toJson(emergencyDetails)
                .toString());
        finalUpdateObject.setData(consumer);

        RestAdapter consumerUpdateAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLog(new AndroidLog("update")).setLogLevel(LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = consumerUpdateAdapter
                .create(Member.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getActivity().getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);

        getConsumerService.updateConsumerProfile(
                authToken, finalUpdateObject,
                new Callback<UpdateConsumerResponse>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(getActivity()))) {
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

                            //save title
                            if (!CommonCode.isNullOrEmpty(nameTitleValue)) {

                                // save user name
                                jeevom_session.setTitle(nameTitleValue);
                                hsession.setKEY_Title(nameTitleValue);
                            }

                            // save name of user
                            jeevom_session.setName(user_first_name_value
                                    .getText().toString().trim()
                                    + " "
                                    + user_last_name_value.getText().toString()
                                    .trim());
                            hsession.setKEY_FirstName(user_first_name_value.getText().toString());
                            hsession.setKEY_LastName(user_last_name_value.getText().toString());
                            // save email
                            jeevom_session.setEmail(user_email_value.getText()
                                    .toString().trim());
                            hsession.setKEY_Email(user_email_value.getText()
                                    .toString().trim());
                            // set phone no
                            jeevom_session.setCellNumber(user_phone_value
                                    .getText().toString().trim());
                            hsession.setKEY_CellNumber(user_phone_value
                                    .getText().toString().trim());
                            // set gender
                            jeevom_session.setGender(gender);
                            hsession.setKEY_Gender(gender);
                            com.schoolteacher.mylibrary.pojo.Address address = new com.schoolteacher.mylibrary.pojo.Address();
                            address.setArea(area);
                            address.setCity(city);
                            address.setCountry(country);
                            address.setState(state);
                            address.setLine1(address_value);
                            address.setZipCode(null);

                            jeevom_session.saveUserAddress(address);

                            jeevom_session.setAge(String.valueOf(CommonCode
                                    .birthDate(date)));
                            hsession.setKey_age(String.valueOf(CommonCode.birthDate((date))));
                            if (value.equals("skip")) {
                                getActivity().finish();
                            } else {
                                viewPager.setCurrentItem(1, true);
                            }

                        }
                    }

                });

    }

}
