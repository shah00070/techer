package com.schoolteacher.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.dialog.GooglePlayAlert;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.JeevAddress;
import com.schoolteacher.pojos.JeevProfileInfo;
import com.schoolteacher.pojos.JeevProfileResult;
import com.schoolteacher.pojos.ProfileContactInformation;
import com.schoolteacher.pojos.ProfileDealAndOffer;
import com.schoolteacher.pojos.ProfileEducationDetail;
import com.schoolteacher.pojos.ProfileExpertise;
import com.schoolteacher.pojos.ProfileLanguage;
import com.schoolteacher.pojos.ProfileMembership;
import com.schoolteacher.pojos.ProfileService;
import com.schoolteacher.pojos.ProfileSpecialityList;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceConfigInfo;
import com.schoolteacher.pojos.ServiceConfiguration;
import com.schoolteacher.service.GetDoctorProfile;
import com.schoolteacher.service.ServiceRequisition;
import com.schoolteacher.serviceRequest.Appointments;
import com.schoolteacher.serviceRequest.EmailConsultation;
import com.schoolteacher.serviceRequest.LabTest;
import com.schoolteacher.serviceRequest.PurchaseRequest;
import com.schoolteacher.serviceRequest.RequestQuote;
import com.schoolteacher.serviceRequest.SendMessage;
import com.squareup.picasso.Picasso;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class DoctorProfileFragment extends Fragment implements OnClickListener {

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
    UserCurrentLocationManager locationManager;


    List<ServiceConfiguration> serviceConfigurations;
    String profile_id;
    JeevomSession session;
    Bundle bundle;
    DialogFragment newFragment;
    GlobalAlert globalAlert;
    JeevProfileInfo profileInfo;
    View view;
    ValuesManager valuesManager;
    GooglePlayAlert googleAlert;

    // like
    ImageView like_image;
    TextView like_count, like_text;

    // profile views
    ImageView profile_view_image;
    TextView profile_view_count, profile_view_text, profile_view_text_one;

    // connections
    ImageView connection_view_image;
    TextView connection_count, connection_text;

    // View Elements
    CircleImageView profile_image;
    TextView doctor_name, doctor_specialities, doctor_exp, doctor_education;
    // clinic text views
    TextView clinic_name, address_one, address_two, address_three,
            more_clinics, fee_value;

    LinearLayout in_clinic, home_visit, video, audio, text_chat, email_basic,
            email_advance, purchase, lab, quotes, callback, message;

    TextView experties_value, specialies_value, service_value;
    RelativeLayout services_container;
    LinearLayout specialites_container, expertise_container, service_container,
            language_container;

    RelativeLayout track_record_container;
    TextView track_value;

    RelativeLayout summary_container;
    TextView summary_value;

    RelativeLayout deal_container;
    TextView deal_value;

    RelativeLayout qualification_container;
    TextView qualification_value;

    LinearLayout membership_container;
    TextView membership_value;
    Gson gson;


    TextView language_value;

    RelativeLayout more_clinics_layout;
    TextView show_more_clinic;
    LinearLayout add_more_clinics;

    ImageView direction_image;
    List<ProfileContactInformation> listOfValidFacilities;

    RelativeLayout profile_layout;
    int vibrant, vibrantDark, muted, mutedLight, mutedDark, vibrantLight;
    String imageUrl = null;

    String authToken = null;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_doctor_profile, container,
                false);
        gson = new GsonBuilder().create();
        valuesManager = new ValuesManager(getActivity().getApplicationContext());
        googleAlert = new GooglePlayAlert(getActivity());
        bundle = getArguments();
        profile_id = getArguments().getString("ProfessionalProfileId");
        session = new JeevomSession(getActivity().getApplicationContext());
        globalAlert = new GlobalAlert(getActivity());
        locationManager = new UserCurrentLocationManager(getActivity().getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Doctor Profile Detail Fragment");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initiateViewElements();

        getDoctorProfile(profile_id);

    }

    void getDoctorProfile(final String pro_id) {

        RestAdapter getDoctorProfileAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("doctorDetails"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        GetDoctorProfile doctor_profile_interface = getDoctorProfileAdapter
                .create(GetDoctorProfile.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getActivity().getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        doctor_profile_interface.getProfessionalDetails(
                pro_id.trim(),
                String.valueOf(valuesManager.getVersion()), authToken,
                new Callback<JeevProfileResult>() {

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
                    public void success(JeevProfileResult arg0, Response arg1) {

                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            profileInfo = arg0.getData();
                            id = profileInfo.getId();
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

        service_config_interface.getServiceConfigurations(
                locationManager.getUserLocation(), authToken,
                pro_id.trim(),

                new Callback<ServiceConfigInfo>() {

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

                            fillDoctorProfile();

                        }
                    }
                });

    }

    private void fillDoctorProfile() {

        if (!(CommonCode.isNullOrEmpty(profileInfo.getPhoto()))) {
            imageUrl = profileInfo.getPhoto();

        }

        // set profile views
        com.schoolteacher.pojos.Stats stats = profileInfo.getStats();
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

        // fill doctor name
        StringBuilder name = new StringBuilder();
        if (!CommonCode.isNullOrEmpty(profileInfo.getTitle())) {
            name.append(profileInfo.getTitle() + " ");
        }
        if (!CommonCode.isNullOrEmpty(profileInfo.getFirstName())) {
            name.append(profileInfo.getFirstName() + " ");
        }
        if (!CommonCode.isNullOrEmpty(profileInfo.getLastName())) {
            name.append(profileInfo.getLastName());
        }

        if (!CommonCode.isNullOrEmpty(name.toString())) {
            doctor_name.setText(name.toString().trim());

        }

        // set profile picture
        Picasso.with(getActivity()).load(imageUrl)
                .placeholder(R.drawable.jeevom_back)
                .error(R.drawable.jeevom_back).into(profile_image);

        // set doctor specialities
        List<ProfileSpecialityList> specialityList = profileInfo
                .getSpecialityList();
        if (specialityList != null && specialityList.size() > 0) {
            StringBuilder commaSepratedValue = new StringBuilder();

            for (int i = 0; i < specialityList.size(); i++) {

                if (i == 0) {
                    commaSepratedValue.append(specialityList.get(i).getName());
                } else {
                    commaSepratedValue.append(", "
                            + specialityList.get(i).getName());
                }
            }

            doctor_specialities.setText(commaSepratedValue.toString());
        } else {
            doctor_specialities.setVisibility(View.GONE);
        }

        // set Education details
        List<ProfileEducationDetail> educationDetails = profileInfo
                .getEducationDetails();
        if (educationDetails != null && educationDetails.size() > 0) {
            StringBuilder commaSepratedValue = new StringBuilder();

            for (int i = 0; i < educationDetails.size(); i++) {

                if (i == 0) {
                    commaSepratedValue
                            .append(educationDetails.get(i).getName());
                } else {
                    commaSepratedValue.append(", "
                            + educationDetails.get(i).getName());
                }
            }

            doctor_education.setText(commaSepratedValue.toString());
            qualification_value.setText(commaSepratedValue.toString());
        } else {
            doctor_education.setVisibility(View.GONE);
            qualification_container.setVisibility(View.GONE);
        }

        // set experience
        if (!CommonCode.isNullOrEmpty(profileInfo.getExperience()))
            doctor_exp.setText(profileInfo.getExperience() + " Experience");
        else
            doctor_exp.setText("Experience : N/A ");

        // add clinics
        listOfValidFacilities = new ArrayList<>();
        List<ProfileContactInformation> contactInformations2 = profileInfo
                .getContactInformations();
        if (profileInfo.getContactInformations() != null
                && profileInfo.getContactInformations().size() > 0) {
            for (ProfileContactInformation profileContactInformation : contactInformations2) {
                if (profileContactInformation.getFacilityID() > 0) {
                    listOfValidFacilities.add(profileContactInformation);
                }
            }
        }
        if (listOfValidFacilities != null && listOfValidFacilities.size() > 0) {

            if (listOfValidFacilities.size() > 1) {
                more_clinics.setText("+"
                        + String.valueOf(listOfValidFacilities.size() - 1));

                setAllClinics();

            } else {
                more_clinics.setVisibility(View.GONE);
            }

            ProfileContactInformation profileContactInformation = listOfValidFacilities
                    .get(0);
            if (profileContactInformation != null) {

                if (!CommonCode.isNullOrEmpty(profileContactInformation
                        .getFacilityName()))
                    clinic_name.setText(profileContactInformation
                            .getFacilityName());
                else
                    clinic_name.setVisibility(View.GONE);

                final JeevAddress address = profileContactInformation
                        .getAddress();
                if (address != null) {

                    direction_image
                            .setOnClickListener(new OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    showMap(address.getLatitude(),
                                            address.getLongitude());

                                }
                            });

                    if (!CommonCode.isNullOrEmpty(address.getLine1()))
                        address_one.setText(address.getLine1());
                    else
                        address_one.setVisibility(View.GONE);

                    StringBuilder addressTwo = new StringBuilder();
                    if (!CommonCode.isNullOrEmpty(address.getArea()))
                        addressTwo.append(address.getArea());
                    if (!CommonCode.isNullOrEmpty(address.getCity()))
                        addressTwo.append(", " + address.getCity());

                    if (!CommonCode.isNullOrEmpty(addressTwo.toString()))
                        address_two.setText(addressTwo.toString());
                    else
                        address_two.setVisibility(View.GONE);

                    StringBuilder addressThree = new StringBuilder();
                    if (!CommonCode.isNullOrEmpty(address.getZipCode()))
                        addressThree.append(address.getZipCode());
                    if (!CommonCode.isNullOrEmpty(address.getCountry()))
                        addressThree.append(", " + address.getCountry());

                    if (!CommonCode.isNullOrEmpty(addressThree.toString()))
                        address_three.setText(addressThree.toString());
                    else
                        address_three.setVisibility(View.GONE);

                }
            }

        }

        // set fee
        if (!CommonCode.isNullOrEmpty(profileInfo.getFees()))
            fee_value.setText(profileInfo.getFees());
        else
            fee_value.setText("N/A");

        // set call to action buttons
        if (serviceConfigurations != null && serviceConfigurations.size() > 0) {
            for (ServiceConfiguration object : serviceConfigurations) {
                if (object.getUniquePublicID().equals(SG1)) {
                    in_clinic.setVisibility(View.VISIBLE);
                    in_clinic.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG2)) {
                    home_visit.setVisibility(View.VISIBLE);
                    home_visit.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG3)) {
                    video.setVisibility(View.VISIBLE);
                    video.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG4)) {
                    audio.setVisibility(View.VISIBLE);
                    audio.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG5)) {
                    text_chat.setVisibility(View.VISIBLE);
                    audio.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG6)) {
                    email_basic.setVisibility(View.VISIBLE);
                    email_basic.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG7)) {
                    // make visibility visible once integrate document upload
                    email_advance.setVisibility(View.VISIBLE);
                    email_advance.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG8)) {
                    purchase.setVisibility(View.VISIBLE);
                    purchase.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG9)) {
                    lab.setVisibility(View.VISIBLE);
                    lab.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG10)) {
                    quotes.setVisibility(View.VISIBLE);
                    quotes.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG11)) {
                    callback.setVisibility(View.VISIBLE);
                    callback.setTag(object.getUniquePublicID());
                } else if (object.getUniquePublicID().equals(SG12)) {
                    message.setVisibility(View.VISIBLE);
                    message.setTag(object.getUniquePublicID());
                }

            }
        }

        // set specialities
        if ((profileInfo.getSpecialityList() == null || profileInfo
                .getSpecialityList().size() == 0)
                && (profileInfo.getExpertise() == null || profileInfo
                .getExpertise().size() == 0)) {
            services_container.setVisibility(View.GONE);

        } else {
            if (profileInfo.getSpecialityList() == null
                    || profileInfo.getSpecialityList().size() == 0) {
                specialites_container.setVisibility(View.GONE);
            } else {
                List<ProfileSpecialityList> specialities = profileInfo
                        .getSpecialityList();
                StringBuilder speciality = new StringBuilder();

                for (int i = 0; i < specialities.size(); i++) {
                    if (i == 0) {
                        speciality.append(specialities.get(i).getName());
                    } else {
                        speciality.append(", " + specialities.get(i).getName());
                    }
                }
                specialies_value.setText(speciality.toString());
            }

            if (profileInfo.getExpertise() == null
                    || profileInfo.getExpertise().size() == 0) {
                expertise_container.setVisibility(View.GONE);
            } else {
                List<ProfileExpertise> expertise = profileInfo.getExpertise();
                StringBuilder exp = new StringBuilder();
                for (int i = 0; i < expertise.size(); i++) {
                    if (i == 0) {
                        exp.append(expertise.get(i).getName());
                    } else {
                        exp.append(", " + expertise.get(i).getName());
                    }
                }

                experties_value.setText((exp.toString()).replaceAll(",$", ""));
            }
        }

        // set experience
        if (!CommonCode.isNullOrEmpty(profileInfo.getExperience()))
            track_value.setText(profileInfo.getExperience());
        else
            track_record_container.setVisibility(View.GONE);

        // set services

        if (profileInfo.getServices() != null
                && profileInfo.getServices().size() > 0) {
            List<ProfileService> services = profileInfo.getServices();

            StringBuilder service = new StringBuilder();
            for (int i = 0; i < services.size(); i++) {
                if (i == 0) {
                    service.append(services.get(i).getName());
                } else {
                    service.append(", " + services.get(i).getName());
                }
            }

            service_value.setText((service.toString()).replaceAll(",$", ""));
        } else {
            service_container.setVisibility(View.GONE);
        }

        // set language
        if (profileInfo.getLanguages() != null
                && profileInfo.getLanguages().size() > 0) {
            List<ProfileLanguage> languages = profileInfo.getLanguages();

            StringBuilder service = new StringBuilder();
            for (int i = 0; i < languages.size(); i++) {
                if (i == 0) {
                    service.append(languages.get(i).getName());
                } else {
                    service.append(", " + languages.get(i).getName());
                }
            }

            language_value.setText((service.toString()).replaceAll(",$", ""));
        } else {
            language_container.setVisibility(View.GONE);
        }

        // set membership
        if (profileInfo.getMemberships() != null
                && profileInfo.getMemberships().size() > 0) {
            List<ProfileMembership> memberships = profileInfo.getMemberships();

            StringBuilder service = new StringBuilder();
            for (int i = 0; i < memberships.size(); i++) {
                if (i == 0) {
                    service.append(memberships.get(i).getName());
                } else {
                    service.append(", " + memberships.get(i).getName());
                }
            }

            membership_value.setText((service.toString()).replaceAll(",$", ""));
        } else {
            membership_container.setVisibility(View.GONE);
        }
        // set deals
        if (profileInfo.getDealAndOffer() != null
                && profileInfo.getDealAndOffer().size() > 0) {
            List<ProfileDealAndOffer> dealAndOffer = profileInfo
                    .getDealAndOffer();

            StringBuilder service = new StringBuilder();
            for (int i = 0; i < dealAndOffer.size(); i++) {
                if (i == 0) {
                    service.append(dealAndOffer.get(i).getName());
                } else {
                    service.append(", " + dealAndOffer.get(i).getName());
                }
            }

            deal_value.setText((service.toString()).replaceAll(",$", ""));
        } else {
            deal_container.setVisibility(View.GONE);
        }
        // fill summary
        if (!CommonCode.isNullOrEmpty(profileInfo.getAbout())) {
            summary_value.setText(profileInfo.getAbout());
        } else {
            summary_container.setVisibility(View.GONE);
        }

        setTextColors();
    }

    private void initiateViewElements() {
        // like
        like_count = (TextView) view.findViewById(R.id.like_count);
        like_image = (ImageView) view.findViewById(R.id.like_image);
        like_text = (TextView) view.findViewById(R.id.like_text);

        profile_layout = (RelativeLayout) view
                .findViewById(R.id.profile_layout);

        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        doctor_name = (TextView) view.findViewById(R.id.doctor_name);
        doctor_specialities = (TextView) view
                .findViewById(R.id.doctor_specialities);
        doctor_exp = (TextView) view.findViewById(R.id.doctor_exp);
        doctor_education = (TextView) view.findViewById(R.id.doctor_education);

        // clinic references
        clinic_name = (TextView) view.findViewById(R.id.clinic_name);
        address_one = (TextView) view.findViewById(R.id.address_one);
        address_two = (TextView) view.findViewById(R.id.address_two);
        address_three = (TextView) view.findViewById(R.id.address_three);
        more_clinics = (TextView) view.findViewById(R.id.more_clinics);

        // fee field reference
        fee_value = (TextView) view.findViewById(R.id.fee_value);

        // call to action button
        in_clinic = (LinearLayout) view
                .findViewById(R.id.in_clinc_consultation);
        home_visit = (LinearLayout) view.findViewById(R.id.home_consultation);
        video = (LinearLayout) view.findViewById(R.id.video_consultation);
        audio = (LinearLayout) view.findViewById(R.id.audio_consultation);
        text_chat = (LinearLayout) view.findViewById(R.id.text_consultation);
        email_basic = (LinearLayout) view
                .findViewById(R.id.basic_email_consultation);
        email_advance = (LinearLayout) view
                .findViewById(R.id.advance_email_consultation);
        purchase = (LinearLayout) view.findViewById(R.id.purchase_request);
        lab = (LinearLayout) view.findViewById(R.id.lab_test);
        quotes = (LinearLayout) view.findViewById(R.id.request_quote);
        callback = (LinearLayout) view.findViewById(R.id.request_call);
        message = (LinearLayout) view.findViewById(R.id.send_message);

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

        // services
        experties_value = (TextView) view.findViewById(R.id.experties_value);
        specialies_value = (TextView) view.findViewById(R.id.specialies_value);
        service_value = (TextView) view.findViewById(R.id.service_value);
        services_container = (RelativeLayout) view
                .findViewById(R.id.services_container);
        specialites_container = (LinearLayout) view
                .findViewById(R.id.specialites_container);
        expertise_container = (LinearLayout) view
                .findViewById(R.id.expertise_container);
        service_container = (LinearLayout) view
                .findViewById(R.id.service_container);
        language_container = (LinearLayout) view
                .findViewById(R.id.language_container);
        membership_container = (LinearLayout) view
                .findViewById(R.id.membership_container);
        // track record
        track_record_container = (RelativeLayout) view
                .findViewById(R.id.track_record_container);
        track_value = (TextView) view.findViewById(R.id.track_value);

        summary_container = (RelativeLayout) view
                .findViewById(R.id.summary_container);
        summary_value = (TextView) view.findViewById(R.id.summary_value);

        deal_container = (RelativeLayout) view
                .findViewById(R.id.deal_container);
        deal_value = (TextView) view.findViewById(R.id.deal_value);

        language_value = (TextView) view.findViewById(R.id.language_value);
        qualification_container = (RelativeLayout) view
                .findViewById(R.id.qualification_container);
        qualification_value = (TextView) view
                .findViewById(R.id.qualification_value);
        membership_value = (TextView) view.findViewById(R.id.membership_value);

        // profile view counts
        profile_view_count = (TextView) view
                .findViewById(R.id.profile_view_count);
        profile_view_text = (TextView) view
                .findViewById(R.id.profile_view_text);
        profile_view_text_one = (TextView) view
                .findViewById(R.id.profile_view_text_one);
        profile_view_image = (ImageView) view
                .findViewById(R.id.profile_view_image);

        // connections
        connection_view_image = (ImageView) view
                .findViewById(R.id.connection_view_image);
        connection_count = (TextView) view.findViewById(R.id.connection_count);
        connection_text = (TextView) view.findViewById(R.id.connection_text);

        more_clinics_layout = (RelativeLayout) view
                .findViewById(R.id.more_clinics_layout);
        more_clinics_layout.setOnClickListener(this);

        show_more_clinic = (TextView) view.findViewById(R.id.show_more_clinic);

        add_more_clinics = (LinearLayout) view
                .findViewById(R.id.add_more_clinics);
        direction_image = (ImageView) view.findViewById(R.id.direction_image);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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
            case R.id.more_clinics_layout:

                if (add_more_clinics.getVisibility() == View.VISIBLE) {
                    add_more_clinics.setVisibility(View.GONE);
                    show_more_clinic.setText("Show All");
                } else {
                    add_more_clinics.setVisibility(View.VISIBLE);
                    show_more_clinic.setText("Hide All");
                }
                break;

            case R.id.in_clinc_consultation:

                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG1")) {

                        performAppointmentAction(profile_id, "in_clinic");
                        break;
                    }
                }
                break;

            case R.id.home_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG2")) {

                        performAppointmentAction(profile_id, "home");
                        break;
                    }
                }
                break;
            case R.id.video_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG3")) {

                        performAppointmentAction(profile_id, "video");
                        break;
                    }
                }
                break;
            case R.id.audio_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG4")) {

                        performAppointmentAction(profile_id, "audio");
                        break;
                    }
                }
                break;
            case R.id.text_consultation:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG5")) {

                        performAppointmentAction(profile_id, "text_chat");
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

                        Intent intent = new Intent(getActivity(),
                                PurchaseRequest.class);
                        intent.putExtra("profile_object", profileInfo);
                        intent.putExtra("service_id", object.getId());
                        intent.putExtra("fee", object.getFees());
                        intent.putExtra("service_requestor", "professional");
                        startActivity(intent);
                        break;
                    }
                }

                break;
            case R.id.lab_test:
                for (ServiceConfiguration object : serviceConfigurations) {

                    if (object.getUniquePublicID().equals("SG9")) {

                        Intent intent = new Intent(getActivity(), LabTest.class);
                        intent.putExtra("profile_object", profileInfo);
                        intent.putExtra("service_id", object.getId());
                        intent.putExtra("fee", object.getFees());
                        intent.putExtra("service_requestor", "professional");
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
        }

    }

    private void setAllClinics() {
        more_clinics_layout.setVisibility(View.VISIBLE);
        if (add_more_clinics.getChildCount() > 0) {
            add_more_clinics.removeAllViews();
        }
        for (int i = 1; i < listOfValidFacilities.size(); i++) {
            final ProfileContactInformation profileContactInformation = listOfValidFacilities
                    .get(i);
            LayoutInflater inflator = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflator.inflate(R.layout.clinics_layout, null);

            TextView clinic_name = (TextView) view
                    .findViewById(R.id.clinic_name);
            TextView address_one = (TextView) view
                    .findViewById(R.id.address_one);
            TextView address_two = (TextView) view
                    .findViewById(R.id.address_two);
            TextView address_three = (TextView) view
                    .findViewById(R.id.address_three);
            ImageView directin_image = (ImageView) view
                    .findViewById(R.id.directin_image);
            directin_image.setVisibility(View.GONE);
            directin_image.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    showMap(profileContactInformation.getAddress()
                            .getLatitude(), profileContactInformation
                            .getAddress().getLongitude());

                }
            });

            if (profileContactInformation != null) {

                if (!CommonCode.isNullOrEmpty(profileContactInformation
                        .getFacilityName()))
                    clinic_name.setText(profileContactInformation
                            .getFacilityName());
                else
                    clinic_name.setVisibility(View.GONE);

                JeevAddress address = profileContactInformation.getAddress();
                if (address != null) {

                    if (!CommonCode.isNullOrEmpty(address.getLine1()))
                        address_one.setText(address.getLine1());
                    else
                        address_one.setVisibility(View.GONE);

                    StringBuilder addressTwo = new StringBuilder();
                    if (!CommonCode.isNullOrEmpty(address.getArea()))
                        addressTwo.append(address.getArea());
                    if (!CommonCode.isNullOrEmpty(address.getCity()))
                        addressTwo.append(", " + address.getCity());

                    if (!CommonCode.isNullOrEmpty(addressTwo.toString()))
                        address_two.setText(addressTwo.toString());
                    else
                        address_two.setVisibility(View.GONE);

                    StringBuilder addressThree = new StringBuilder();
                    if (!CommonCode.isNullOrEmpty(address.getZipCode()))
                        addressThree.append(address.getZipCode());
                    if (!CommonCode.isNullOrEmpty(address.getCountry()))
                        addressThree.append(", " + address.getCountry());

                    if (!CommonCode.isNullOrEmpty(addressThree.toString()))
                        address_three.setText(addressThree.toString());
                    else
                        address_three.setVisibility(View.GONE);
                }
            }

            add_more_clinics.addView(view);
        }
    }

    private void performActionBasicEmail(int id, double fee, String type) {
        Intent intent = null;
        if (type.equalsIgnoreCase("email")) {
            intent = new Intent(getActivity(), EmailConsultation.class);
        } else if (type.equalsIgnoreCase("request_quote")) {
            intent = new Intent(getActivity(), RequestQuote.class);
        } else if (type.equalsIgnoreCase("message")) {
            intent = new Intent(getActivity(), SendMessage.class);
        } else if (type.equalsIgnoreCase("callback")) {
            // intent = new Intent(getActivity(), RequestCallback.class);
        }
        intent.putExtra("profile_object", profileInfo);
        intent.putExtra("service_id", id);
        intent.putExtra("fee", fee);
        intent.putExtra("service_requestor", "professional");
        startActivity(intent);
    }

    private void goToBasicEmail(int id, double fee) {
        Intent intent = new Intent(getActivity(), EmailConsultation.class);

        intent.putExtra("profile_object", profileInfo);
        intent.putExtra("service_id", id);
        intent.putExtra("fee", fee);
        intent.putExtra("email_consultation_type", "basic");
        intent.putExtra("service_requestor", "professional");
        startActivity(intent);
    }

    private void goToAdvanceEmail(int id, double fee) {
        Intent intent = new Intent(getActivity(), EmailConsultation.class);
        intent.putExtra("profile_object", profileInfo);
        intent.putExtra("service_id", id);
        intent.putExtra("fee", fee);
        intent.putExtra("email_consultation_type", "advance");
        intent.putExtra("service_requestor", "professional");
        startActivity(intent);
    }

    private void performAppointmentAction(String profile_id, String type) {
        Intent intent = null;
        intent = new Intent(getActivity(), Appointments.class);
        intent.putExtra("profileId", profile_id);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("image", imageUrl);
        intent.putExtra("name", doctor_name.getText().toString());
        intent.putExtra("service_requestor", "professional");
        startActivity(intent);
    }

    private void showMap(double latitude, double longitude) {
        Intent mapIntent = new Intent(getActivity(), FacilityMapView.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("latitude", latitude);
        bundle.putDouble("longitude", longitude);
        mapIntent.putExtras(bundle);
        startActivity(mapIntent);
    }

    // private class LoadImage extends AsyncTask<String, Void, Bitmap> {
    //
    // @Override
    // protected Bitmap doInBackground(String... params) {
    //
    // try {
    // URL url = new URL(params[0]);
    // HttpURLConnection connection = (HttpURLConnection) url
    // .openConnection();
    // connection.setDoInput(true);
    // connection.connect();
    // InputStream input = connection.getInputStream();
    // Bitmap myBitmap = BitmapFactory.decodeStream(input);
    // return myBitmap;
    // } catch (IOException e) {
    // e.printStackTrace();
    // return null;
    // }
    // }
    //
    // @Override
    // protected void onPostExecute(Bitmap result) {
    // setColors(result);
    // }
    //
    // }

    // public void setColors(Bitmap result) {
    //
    // if (result != null) {
    // Palette.generateAsync(result, new PaletteAsyncListener() {
    //
    // @Override
    // public void onGenerated(Palette palette) {
    // vibrant = palette.getVibrantColor(0x000000);
    // vibrantLight = palette.getLightVibrantColor(0x000000);
    // vibrantDark = palette.getDarkVibrantColor(0x000000);
    // muted = palette.getMutedColor(0x000000);
    // mutedLight = palette.getLightMutedColor(0x000000);
    // mutedDark = palette.getDarkMutedColor(0x000000);
    //
    // }
    // });
    // }
    //
    // }

    private void setTextColors() {

        doctor_education.setTextColor(getResources()
                .getColor(R.color.grey_dark));
        doctor_specialities.setTextColor(getResources().getColor(
                R.color.grey_dark));

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

        doctor_name.setTextColor(getResources().getColor(R.color.grey_dark));
        doctor_exp.setTextColor(getResources().getColor(R.color.grey_dark));
    }

}
