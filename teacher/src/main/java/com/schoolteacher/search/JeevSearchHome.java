package com.schoolteacher.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.JeevCriteria;
import com.schoolteacher.pojos.JeevSearchAvailability;
import com.schoolteacher.pojos.JeevSearchCategory;
import com.schoolteacher.pojos.JeevSearchFilter;
import com.schoolteacher.pojos.JeevSearchGender;
import com.schoolteacher.pojos.JeevSearchRequisition;
import com.schoolteacher.pojos.JeevSearchTiming;
import com.schoolteacher.serviceRequest.LabTest;
import com.schoolteacher.serviceRequest.PurchaseRequest;

public class JeevSearchHome extends Fragment implements OnClickListener {
    View view;
    Activity context;
    ImageView doctor_clinic_image, pharmacies_image, labs_image, fitness_image,
            audio_video_image, email_chat_image, care_image, order_image;
    EditText search_field;
    String memberId = "";
    JeevCriteria jeevCriteria;

    JeevomSession session;
    JeevSearchCategory jeevCategory;
    JeevSearchAvailability jeevAvailability;
    JeevSearchGender jeevGender;
    JeevSearchRequisition jeevRequistion;
    JeevSearchTiming jeevTiming;
    int callCount = 0;
    int NoOfItems = 30;

    JeevSearchFilter filter;
    boolean isNearMeChecked;
    UserCurrentLocationManager locationManager;
    String locationFromGps;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.jeev_search_home_layout, container,
                false);
        context = getActivity();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        filter = new JeevSearchFilter();
        session = new JeevomSession(getActivity().getApplicationContext());
        if (session.getLoggedInStatus()) {
            memberId = session.getConsumerIds().get(
                    JeevomSession.JEEVOM_CONSUMER_ID);
        }

        locationManager = new UserCurrentLocationManager(getActivity());

        if (locationManager.getUserLocation().getAddress0() != null && locationManager.getUserLocation().getAddress1() != null && locationManager.getUserLocation().getAddress2() != null)
            locationFromGps = locationManager.getUserLocation().getAddress0() + "," + locationManager.getUserLocation().getAddress1() + "," + locationManager.getUserLocation().getAddress2();

        doctor_clinic_image = (ImageView) view
                .findViewById(R.id.option_one_image);
        pharmacies_image = (ImageView) view.findViewById(R.id.option_two_image);
        labs_image = (ImageView) view.findViewById(R.id.option_three_image);
        fitness_image = (ImageView) view.findViewById(R.id.option_four_image);
        audio_video_image = (ImageView) view
                .findViewById(R.id.option_five_image);
        email_chat_image = (ImageView) view.findViewById(R.id.option_six_image);
        care_image = (ImageView) view.findViewById(R.id.option_seven_image);
        order_image = (ImageView) view.findViewById(R.id.option_eight_image);

        // Top search edit text
        search_field = (EditText) view.findViewById(R.id.search_field);
        search_field.setOnClickListener(this);

        // getActivity().findViewById(R.id.upper_container).startAnimation(
        // AnimationUtils.loadAnimation(getActivity(),
        // R.anim.scale_up_down));
        // implement on click listners on images
        doctor_clinic_image.setOnClickListener(this);
        pharmacies_image.setOnClickListener(this);
        labs_image.setOnClickListener(this);
        fitness_image.setOnClickListener(this);
        audio_video_image.setOnClickListener(this);
        email_chat_image.setOnClickListener(this);
        care_image.setOnClickListener(this);
        order_image.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MyApplication.getInstance().trackScreenView("Search Home Fragment");

        jeevCriteria = new JeevCriteria();

        jeevCategory = new JeevSearchCategory();
        jeevAvailability = new JeevSearchAvailability();
        jeevGender = new JeevSearchGender();
        jeevRequistion = new JeevSearchRequisition();
        jeevTiming = new JeevSearchTiming();

        makeCategoryObject("any");
        makeAvailabilityObject();
        makeGenderObject();
        makeRequisitionObject("any");
        makeTimingObject();
        makeCriteriaObject();
        makeFilterObject();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_field:
              //  openFilterActivity();
                break;

            case R.id.option_one_image:
                jeevCategory.setDoctorClinic(true);
                // openFilterActivity();
                callSearch();
                break;

            case R.id.option_two_image:
                jeevCategory.setPharmacies(true);
                callSearch();
                // openFilterActivity();
                break;
            case R.id.option_three_image:
                // jeevCategory.setLabDiagnostic(true);
                // openFilterActivity();

                Intent intent = new Intent(getActivity(), LabTest.class);

                startActivity(intent);
                break;
            case R.id.option_four_image:
                jeevCategory.setGymFitness(true);
                jeevCategory.setSpaWellness(true);
                callSearch();
                // openFilterActivity();
                break;
            case R.id.option_five_image:
                jeevRequistion.setPhone(true);
                jeevRequistion.setVideo(true);
                callSearch();
                // openFilterActivity();
                break;
            case R.id.option_six_image:
                jeevRequistion.setChat(true);
                jeevRequistion.setEmail(true);
                callSearch();
                // openFilterActivity();
                break;
            case R.id.option_seven_image:
                jeevRequistion.setHome(true);
                callSearch();
                // openFilterActivity();
                break;
            case R.id.option_eight_image:
                Intent purchaseRequestIntent = new Intent(getActivity(),
                        PurchaseRequest.class);

                startActivity(purchaseRequestIntent);
                // jeevCategory.setPharmacies(true);
                // jeevCategory.setLabDiagnostic(true);
                // openFilterActivity();
                break;
        }

    }

   /* private void openFilterActivity() {
        Intent openFilterActivity = new Intent(context, JeevSearchFilters.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("criteria", jeevCriteria);
        bundle.putSerializable("filter", filter);
        bundle.putBoolean("isNearMeChecked", isNearMeChecked);
        bundle.putInt("activity_type", 0); // 0 for advance search and 1 for
        // fiter
        openFilterActivity.putExtras(bundle);
        startActivity(openFilterActivity);
        getActivity().overridePendingTransition(R.anim.trans_down_in,
                R.anim.trans_static);
    }*/

    private void makeCriteriaObject() {
        jeevCriteria.setSearchString(null);
        jeevCriteria.setCategory(jeevCategory);
        jeevCriteria.setAvailability(jeevAvailability);
        jeevCriteria.setGender(jeevGender);
        jeevCriteria.setRequisition(jeevRequistion);
        jeevCriteria.setTiming(jeevTiming);
    }

    private void makeFilterObject() {
        filter = new JeevSearchFilter();
        filter.setDistance(5);
        filter.setIsPremium(jeevCriteria.isValued());
        filter.setIsRecommended(false);
        filter.setIsVerified(jeevCriteria.isVerified());
        filter.setLatitude(0.0);
        filter.setLocation(getLocationFromAddressOrGps());
        filter.setLongitude(0.0);
        filter.setSkip(callCount);
        filter.setTop(NoOfItems);
        filter.setIsDiscountOffered(jeevCriteria.isDiscount());
        isNearMeChecked = true;
    }

    public String getLocationFromAddressOrGps() {
        String userLocation;
        if (session.getLoggedInStatus()) {

            if (session.getUserAddress() != null) {
                if (!CommonCode.isNullOrEmpty(session.getUserAddress().getCity())) {
                    userLocation = session.getUserAddress().getArea() + "," + session.getUserAddress().getCity();
                } else if (!CommonCode.isNullOrEmpty(locationFromGps) && locationManager.getUserLocation().getAddress1() != null) {
                    userLocation = locationFromGps;
                } else {
                    userLocation = "Delhi";
                }
            } else if (!CommonCode.isNullOrEmpty(locationFromGps) && locationManager.getUserLocation().getAddress1() != null) {
                userLocation = locationFromGps;
            } else {
                userLocation = "Delhi";
            }

        } else if (!CommonCode.isNullOrEmpty(locationFromGps) && locationManager.getUserLocation().getAddress1() != null) {
            userLocation = locationFromGps;
        } else {
            userLocation = "Delhi";
        }
        return userLocation;
    }

    private void makeCategoryObject(String value) {

        if (value.equalsIgnoreCase("labs_pharmacies")) {
            jeevCategory.setAlternateMedicine(false);
            jeevCategory.setDoctorClinic(false);
            jeevCategory.setGymFitness(false);
            jeevCategory.setHealing(false);
            jeevCategory.setHealthcareSupport(false);
            jeevCategory.setHospitalNursing(false);
            jeevCategory.setLabDiagnostic(true);
            jeevCategory.setPharmacies(true);
            jeevCategory.setSpaWellness(true);
        }

    }

    private void makeAvailabilityObject() {
        jeevAvailability.setFriday(false);
        jeevAvailability.setMonday(false);
        jeevAvailability.setSaturday(false);
        jeevAvailability.setSunday(false);
        jeevAvailability.setThrusday(false);
        jeevAvailability.setTuesday(false);
        jeevAvailability.setWednesday(false);
    }

    private void makeGenderObject() {
        jeevGender.setBoth(true);
        jeevGender.setFemale(false);
        jeevGender.setMale(false);
    }

    private void makeRequisitionObject(String value) {
        jeevRequistion.setChat(false);
        jeevRequistion.setClinic(false);
        jeevRequistion.setEmail(false);
        jeevRequistion.setHome(false);
        jeevRequistion.setPhone(false);
        jeevRequistion.setVideo(false);
    }

    private void makeTimingObject() {
        jeevTiming.setAfternoon(false);
        jeevTiming.setEvening(false);
        jeevTiming.setMorning(false);
    }

    private void callSearch() {
        Intent searchIntent = new Intent(getActivity(), JeevSearchList.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", jeevCriteria);
        bundle.putSerializable("filter", filter);
        bundle.putBoolean("isNearMeChecked", isNearMeChecked);
        searchIntent.putExtras(bundle);
        startActivity(searchIntent);
    }
}
