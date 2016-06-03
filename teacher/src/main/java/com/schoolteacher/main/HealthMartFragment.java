package com.schoolteacher.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import com.schoolteacher.search.JeevSearchList;
import com.schoolteacher.serviceRequest.LabTest;
import com.schoolteacher.serviceRequest.PurchaseRequest;


/**
 * Created by chandan on 16/12/15.
 */
public class HealthMartFragment extends Fragment implements View.OnClickListener {
    public View rootView;
    public JeevomSession session;
    UserCurrentLocationManager currentLocationManager;
    String currentLocationFromGps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new JeevomSession(getActivity());

        currentLocationManager = new UserCurrentLocationManager(getActivity());

        if (currentLocationManager.getUserLocation().getAddress0() != null && currentLocationManager.getUserLocation().getAddress1() != null && currentLocationManager.getUserLocation().getAddress2() != null)
            currentLocationFromGps = currentLocationManager.getUserLocation().getAddress0() + "," + currentLocationManager.getUserLocation().getAddress1() + "," + currentLocationManager.getUserLocation().getAddress2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.health_mart_layout, container,
                false);
        setUiOnScreen();
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Mart Fragment");
    }

    public void setUiOnScreen() {

        LinearLayout book_appoint_layout = (LinearLayout) rootView.findViewById(R.id.book_appoint_layout);
        LinearLayout online_consult_layout = (LinearLayout) rootView.findViewById(R.id.online_consult_layout);
        LinearLayout lab_test_layout = (LinearLayout) rootView.findViewById(R.id.lab_test_layout);
        LinearLayout order_medicine_layout = (LinearLayout) rootView.findViewById(R.id.order_medicine_layout);
        LinearLayout yoga_fitness_layout = (LinearLayout) rootView.findViewById(R.id.yoga_fitness_layout);
        LinearLayout beauty_wellness_layout = (LinearLayout) rootView.findViewById(R.id.beauty_wellness_layout);
        LinearLayout care_home_layout = (LinearLayout) rootView.findViewById(R.id.care_home_layout);
        LinearLayout health_insurance_layout = (LinearLayout) rootView.findViewById(R.id.health_insurance_layout);

        book_appoint_layout.setOnClickListener(this);
        online_consult_layout.setOnClickListener(this);
        lab_test_layout.setOnClickListener(this);
        order_medicine_layout.setOnClickListener(this);
        yoga_fitness_layout.setOnClickListener(this);
        beauty_wellness_layout.setOnClickListener(this);
        care_home_layout.setOnClickListener(this);
        health_insurance_layout.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.book_appoint_layout:
                callBookAppointmentSearch();
                break;

            case R.id.online_consult_layout:
                consultOnline();
                break;

            case R.id.lab_test_layout:
                Intent intentMyProfile = new Intent(getActivity(), LabTest.class);
                getActivity().startActivity(intentMyProfile);
                break;

            case R.id.order_medicine_layout:
                Intent purchaseRequestIntent = new Intent(getActivity(),
                        PurchaseRequest.class);
                getActivity().startActivity(purchaseRequestIntent);
                break;

            case R.id.yoga_fitness_layout:
                openYogaFitness();
                break;

            case R.id.beauty_wellness_layout:
                openWellnessBeauty();
                break;

            case R.id.care_home_layout:
                Intent careAtHomeIntent = new Intent(getActivity(), CareAtHome.class);
                getActivity().startActivity(careAtHomeIntent);
                break;

            case R.id.health_insurance_layout:
                ((HomeActivity) getActivity()).showToastMessage();
                break;

            default:
                break;


        }

    }

    private void callBookAppointmentSearch() {
        JeevCriteria jeevCriteria = new JeevCriteria();


        JeevSearchCategory jeevCategory = new JeevSearchCategory();
        JeevSearchAvailability jeevAvailability = new JeevSearchAvailability();
        JeevSearchGender jeevGender = new JeevSearchGender();
        JeevSearchRequisition jeevRequistion = new JeevSearchRequisition();
        JeevSearchTiming jeevTiming = new JeevSearchTiming();

        int callCount = 0;
        int NoOfItems = 30;

        JeevSearchFilter filter;
        boolean isNearMeChecked;


        // availability object
        jeevAvailability.setFriday(false);
        jeevAvailability.setMonday(false);
        jeevAvailability.setSaturday(false);
        jeevAvailability.setSunday(false);
        jeevAvailability.setThrusday(false);
        jeevAvailability.setTuesday(false);
        jeevAvailability.setWednesday(false);

        // category object
        jeevCategory.setAlternateMedicine(false);
        jeevCategory.setDoctorClinic(false);
        jeevCategory.setGymFitness(false);
        jeevCategory.setHealing(false);
        jeevCategory.setHealthcareSupport(false);
        jeevCategory.setHospitalNursing(false);
        jeevCategory.setLabDiagnostic(false);
        jeevCategory.setPharmacies(false);
        jeevCategory.setSpaWellness(false);

        // make requisition object
        jeevRequistion.setChat(true);
        jeevRequistion.setClinic(true);
        jeevRequistion.setEmail(true);
        jeevRequistion.setHome(true);
        jeevRequistion.setPhone(true);
        jeevRequistion.setVideo(true);

        // timing object
        jeevTiming.setAfternoon(false);
        jeevTiming.setEvening(false);
        jeevTiming.setMorning(false);

        // filter object
        filter = new JeevSearchFilter();
        filter.setDistance(5);
        filter.setIsPremium(jeevCriteria.isValued());
        filter.setIsRecommended(false);
        filter.setIsVerified(jeevCriteria.isVerified());
        if (session.getLoggedInStatus()) {

            filter.setLatitude(0.0);

            if (session.getUserAddress() != null) {
                if (!CommonCode.isNullOrEmpty(session.getUserAddress().getCity())) {
                    filter.setLocation(session.getUserAddress().getArea() + "," + session.getUserAddress().getCity());
                } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
                    filter.setLocation(currentLocationFromGps);
                } else {
                    filter.setLocation("Delhi");
                }
            } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
                filter.setLocation(currentLocationFromGps);
            } else {
                filter.setLocation("Delhi");
            }
            filter.setLongitude(0.0);

        } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
            filter.setLocation(currentLocationFromGps);
        } else {
            filter.setLatitude(0.0);
            filter.setLocation("Delhi");
            filter.setLongitude(0.0);
        }
        filter.setSkip(callCount);
        filter.setTop(NoOfItems);
        filter.setIsDiscountOffered(jeevCriteria.isDiscount());
        isNearMeChecked = true;

        // criteria object
        jeevCriteria.setSearchString(null);
        jeevCriteria.setCategory(jeevCategory);
        jeevCriteria.setAvailability(jeevAvailability);
        jeevCriteria.setGender(jeevGender);
        jeevCriteria.setRequisition(jeevRequistion);
        jeevCriteria.setTiming(jeevTiming);


        // gender object
        jeevGender.setBoth(true);
        jeevGender.setFemale(false);
        jeevGender.setMale(false);

        Intent searchIntent = new Intent(getActivity(), JeevSearchList.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", jeevCriteria);
        bundle.putSerializable("filter", filter);
        bundle.putBoolean("isNearMeChecked", isNearMeChecked);
        searchIntent.putExtras(bundle);
        getActivity().startActivity(searchIntent);
    }

    private void consultOnline() {
        JeevCriteria jeevCriteria = new JeevCriteria();


        JeevSearchCategory jeevCategory = new JeevSearchCategory();
        JeevSearchAvailability jeevAvailability = new JeevSearchAvailability();
        JeevSearchGender jeevGender = new JeevSearchGender();
        JeevSearchRequisition jeevRequistion = new JeevSearchRequisition();
        JeevSearchTiming jeevTiming = new JeevSearchTiming();

        int callCount = 0;
        int NoOfItems = 30;

        JeevSearchFilter filter;
        boolean isNearMeChecked;


        // availability object
        jeevAvailability.setFriday(false);
        jeevAvailability.setMonday(false);
        jeevAvailability.setSaturday(false);
        jeevAvailability.setSunday(false);
        jeevAvailability.setThrusday(false);
        jeevAvailability.setTuesday(false);
        jeevAvailability.setWednesday(false);

        // category object
        jeevCategory.setAlternateMedicine(false);
        jeevCategory.setDoctorClinic(true);
        jeevCategory.setGymFitness(false);
        jeevCategory.setHealing(false);
        jeevCategory.setHealthcareSupport(false);
        jeevCategory.setHospitalNursing(true);
        jeevCategory.setLabDiagnostic(false);
        jeevCategory.setPharmacies(false);
        jeevCategory.setSpaWellness(false);

        // make requisition object
        jeevRequistion.setChat(true);
        jeevRequistion.setClinic(false);
        jeevRequistion.setEmail(true);
        jeevRequistion.setHome(false);
        jeevRequistion.setPhone(true);
        jeevRequistion.setVideo(true);

        // timing object
        jeevTiming.setAfternoon(false);
        jeevTiming.setEvening(false);
        jeevTiming.setMorning(false);

        // filter object
        filter = new JeevSearchFilter();
        filter.setDistance(5);
        filter.setIsPremium(jeevCriteria.isValued());
        filter.setIsRecommended(false);
        filter.setIsVerified(jeevCriteria.isVerified());
        if (session.getLoggedInStatus()) {

            filter.setLatitude(0.0);
            if (session.getUserAddress() != null) {
                if (!CommonCode.isNullOrEmpty(session.getUserAddress().getCity())) {
                    filter.setLocation(session.getUserAddress().getArea() + "," + session.getUserAddress().getCity());
                } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
                    filter.setLocation(currentLocationFromGps);
                } else {
                    filter.setLocation("Delhi");
                }
            } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
                filter.setLocation(currentLocationFromGps);
            } else {
                filter.setLocation("Delhi");
            }
            filter.setLongitude(0.0);

        } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
            filter.setLocation(currentLocationFromGps);
        } else {
            filter.setLatitude(0.0);
            filter.setLocation("Delhi");
            filter.setLongitude(0.0);
        }
        filter.setSkip(callCount);
        filter.setTop(NoOfItems);
        filter.setIsDiscountOffered(jeevCriteria.isDiscount());
        isNearMeChecked = true;

        // criteria object
        jeevCriteria.setSearchString(null);
        jeevCriteria.setCategory(jeevCategory);
        jeevCriteria.setAvailability(jeevAvailability);
        jeevCriteria.setGender(jeevGender);
        jeevCriteria.setRequisition(jeevRequistion);
        jeevCriteria.setTiming(jeevTiming);

        // gender object
        jeevGender.setBoth(true);
        jeevGender.setFemale(false);
        jeevGender.setMale(false);

        Intent searchIntent = new Intent(getActivity(), JeevSearchList.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", jeevCriteria);
        bundle.putSerializable("filter", filter);
        bundle.putBoolean("isNearMeChecked", isNearMeChecked);
        searchIntent.putExtras(bundle);
        getActivity().startActivity(searchIntent);
    }

    private void openWellnessBeauty() {
        JeevCriteria jeevCriteria = new JeevCriteria();

        JeevSearchCategory jeevCategory = new JeevSearchCategory();
        JeevSearchAvailability jeevAvailability = new JeevSearchAvailability();
        JeevSearchGender jeevGender = new JeevSearchGender();
        JeevSearchRequisition jeevRequistion = new JeevSearchRequisition();
        JeevSearchTiming jeevTiming = new JeevSearchTiming();

        int callCount = 0;
        int NoOfItems = 30;

        JeevSearchFilter filter;
        boolean isNearMeChecked;


        // availability object
        jeevAvailability.setFriday(false);
        jeevAvailability.setMonday(false);
        jeevAvailability.setSaturday(false);
        jeevAvailability.setSunday(false);
        jeevAvailability.setThrusday(false);
        jeevAvailability.setTuesday(false);
        jeevAvailability.setWednesday(false);

        // category object
        jeevCategory.setAlternateMedicine(false);
        jeevCategory.setDoctorClinic(false);
        jeevCategory.setGymFitness(false);
        jeevCategory.setHealing(true);
        jeevCategory.setHealthcareSupport(false);
        jeevCategory.setHospitalNursing(false);
        jeevCategory.setLabDiagnostic(false);
        jeevCategory.setPharmacies(false);
        jeevCategory.setSpaWellness(true);

        // make requisition object
        jeevRequistion.setChat(false);
        jeevRequistion.setClinic(false);
        jeevRequistion.setEmail(false);
        jeevRequistion.setHome(false);
        jeevRequistion.setPhone(false);
        jeevRequistion.setVideo(false);

        // timing object
        jeevTiming.setAfternoon(false);
        jeevTiming.setEvening(false);
        jeevTiming.setMorning(false);

        // filter object
        filter = new JeevSearchFilter();
        filter.setDistance(5);
        filter.setIsPremium(jeevCriteria.isValued());
        filter.setIsRecommended(false);
        filter.setIsVerified(jeevCriteria.isVerified());

        if (session.getLoggedInStatus()) {

            filter.setLatitude(0.0);
            if (session.getUserAddress() != null) {
                if (!CommonCode.isNullOrEmpty(session.getUserAddress().getCity())) {
                    filter.setLocation(session.getUserAddress().getArea() + "," + session.getUserAddress().getCity());
                } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
                    filter.setLocation(currentLocationFromGps);
                } else {
                    filter.setLocation("Delhi");
                }
            } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
                filter.setLocation(currentLocationFromGps);
            } else {
                filter.setLocation("Delhi");
            }
            filter.setLongitude(0.0);

        } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
            filter.setLocation(currentLocationFromGps);
        } else {
            filter.setLatitude(0.0);
            filter.setLocation("Delhi");
            filter.setLongitude(0.0);
        }


        filter.setSkip(callCount);
        filter.setTop(NoOfItems);
        filter.setIsDiscountOffered(jeevCriteria.isDiscount());
        isNearMeChecked = true;

        // criteria object
        jeevCriteria.setSearchString("Spa Salon Naturopath Massage");
        jeevCriteria.setCategory(jeevCategory);
        jeevCriteria.setAvailability(jeevAvailability);
        jeevCriteria.setGender(jeevGender);
        jeevCriteria.setRequisition(jeevRequistion);
        jeevCriteria.setTiming(jeevTiming);


        // gender object
        jeevGender.setBoth(true);
        jeevGender.setFemale(false);
        jeevGender.setMale(false);

        Intent searchIntent = new Intent(getActivity(), JeevSearchList.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", jeevCriteria);
        bundle.putSerializable("filter", filter);
        bundle.putBoolean("isNearMeChecked", isNearMeChecked);
        searchIntent.putExtras(bundle);
        getActivity().startActivity(searchIntent);
    }

    private void openYogaFitness() {
        JeevCriteria jeevCriteria = new JeevCriteria();
        JeevSearchCategory jeevCategory = new JeevSearchCategory();
        JeevSearchAvailability jeevAvailability = new JeevSearchAvailability();
        JeevSearchGender jeevGender = new JeevSearchGender();
        JeevSearchRequisition jeevRequistion = new JeevSearchRequisition();
        JeevSearchTiming jeevTiming = new JeevSearchTiming();

        int callCount = 0;
        int NoOfItems = 30;

        JeevSearchFilter filter;
        boolean isNearMeChecked;


        // availability object
        jeevAvailability.setFriday(false);
        jeevAvailability.setMonday(false);
        jeevAvailability.setSaturday(false);
        jeevAvailability.setSunday(false);
        jeevAvailability.setThrusday(false);
        jeevAvailability.setTuesday(false);
        jeevAvailability.setWednesday(false);

        // category object
        jeevCategory.setAlternateMedicine(false);
        jeevCategory.setDoctorClinic(false);
        jeevCategory.setGymFitness(true);
        jeevCategory.setHealing(false);
        jeevCategory.setHealthcareSupport(false);
        jeevCategory.setHospitalNursing(false);
        jeevCategory.setLabDiagnostic(false);
        jeevCategory.setPharmacies(false);
        jeevCategory.setSpaWellness(true);

        // make requisition object
        jeevRequistion.setChat(false);
        jeevRequistion.setClinic(false);
        jeevRequistion.setEmail(false);
        jeevRequistion.setHome(false);
        jeevRequistion.setPhone(false);
        jeevRequistion.setVideo(false);

        // timing object
        jeevTiming.setAfternoon(false);
        jeevTiming.setEvening(false);
        jeevTiming.setMorning(false);

        // filter object
        filter = new JeevSearchFilter();
        filter.setDistance(5);
        filter.setIsPremium(jeevCriteria.isValued());
        filter.setIsRecommended(false);
        filter.setIsVerified(jeevCriteria.isVerified());

        if (session.getLoggedInStatus()) {

            filter.setLatitude(0.0);
            if (session.getUserAddress() != null) {
                if (!CommonCode.isNullOrEmpty(session.getUserAddress().getCity())) {
                    filter.setLocation(session.getUserAddress().getArea() + "," + session.getUserAddress().getCity());
                } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
                    filter.setLocation(currentLocationFromGps);
                } else {
                    filter.setLocation("Delhi");
                }
            } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
                filter.setLocation(currentLocationFromGps);
            } else {
                filter.setLocation("Delhi");
            }
            filter.setLongitude(0.0);

        } else if (!CommonCode.isNullOrEmpty(currentLocationFromGps) && currentLocationManager.getUserLocation().getAddress1() != null) {
            filter.setLocation(currentLocationFromGps);
        } else {
            filter.setLatitude(0.0);
            filter.setLocation("Delhi");
            filter.setLongitude(0.0);
        }

        filter.setSkip(callCount);
        filter.setTop(NoOfItems);
        filter.setIsDiscountOffered(jeevCriteria.isDiscount());
        isNearMeChecked = true;

        // criteria object
        jeevCriteria.setSearchString("Yoga Fitness");
        jeevCriteria.setCategory(jeevCategory);
        jeevCriteria.setAvailability(jeevAvailability);
        jeevCriteria.setGender(jeevGender);
        jeevCriteria.setRequisition(jeevRequistion);
        jeevCriteria.setTiming(jeevTiming);


        // gender object
        jeevGender.setBoth(true);
        jeevGender.setFemale(false);
        jeevGender.setMale(false);

        Intent searchIntent = new Intent(getActivity(), JeevSearchList.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", jeevCriteria);
        bundle.putSerializable("filter", filter);
        bundle.putBoolean("isNearMeChecked", isNearMeChecked);
        searchIntent.putExtras(bundle);
        getActivity().startActivity(searchIntent);
    }
}
