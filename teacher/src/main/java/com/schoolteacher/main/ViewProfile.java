package com.schoolteacher.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.GooglePlayAlert;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.BloodGroup;
import com.schoolteacher.pojos.BloodGroupResponse;
import com.schoolteacher.pojos.ConsumerDetailsResponse;
import com.schoolteacher.pojos.FamilyMembersResponse;
import com.schoolteacher.pojos.MemberAssociation;
import com.schoolteacher.pojos.MemberEmergencyDetail;
import com.schoolteacher.pojos.MemberStatsResponse;
import com.schoolteacher.service.FamilyMemberGetService;
import com.schoolteacher.service.GetMemberStats;
import com.schoolteacher.service.Member;
import com.squareup.picasso.Picasso;

import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class ViewProfile extends BaseClass implements OnClickListener {
    ViewPager pager;
    private CustomPagerAdapter mAdapter;
    private CircleImageView profile_image;
    static String bloodGroup;
    private JeevomSession session;
    JeevomSession jeevom_session;
    FloatingActionButton fabButton;
    String memberId, consumerId;
    DialogFragment newFragment;
    GlobalAlert globalAlert;
    Gson gson;
    List<MemberAssociation> memberAssociation;
    static ConsumerDetailsResponse consumer;
    static List<BloodGroup> bloodGroups = new LinkedList<BloodGroup>();
    static FamilyMembersResponse familyDetails;
    // user details fields
    TextView bmi, bmr, name, dob, email, cell, weight, height, address_value,
            medical_value, allergy_value, marks_value, emergency_cell,
            emergency_email, emergency_name;
    List<MemberEmergencyDetail> emergencyDetails = new LinkedList<MemberEmergencyDetail>();
    LinearLayout insurance_details, family_details;
    static double getbMI, dailyCalorieRequirement;
    ValuesManager valuesManager;
    GooglePlayAlert googleAlert;
    Toolbar toolbar;

    String authToken = null;
    UserCurrentLocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar_view_profile);
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        valuesManager = new ValuesManager(getApplicationContext());
        googleAlert = new GooglePlayAlert(this);
        session = new JeevomSession(getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        jeevom_session = new JeevomSession(getApplicationContext());
        memberId = String.valueOf(session.getMemberId());


        if (getIntent().getStringExtra("member_profile_id") != null)
            consumerId = getIntent().getStringExtra("member_profile_id");
        else
            consumerId = jeevom_session.getConsumerIds().get(
                    JeevomSession.JEEVOM_CONSUMER_ID);

        globalAlert = new GlobalAlert(this);
        gson = new GsonBuilder().setPrettyPrinting().create();
        fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(
                        getResources().getDrawable(R.drawable.ic_action_edit))
                .withButtonColor(getResources().getColor(R.color.green))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16).create();
        fabButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent edit_profile = new Intent(ViewProfile.this,
                        EditMyProfile.class);
                startActivity(edit_profile);
                overridePendingTransition(R.anim.trans_left_in,
                        R.anim.trans_left_exit);
            }
        });

        // imageview
        profile_image = (CircleImageView) findViewById(R.id.profile_image);

        // get references for user details
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        cell = (TextView) findViewById(R.id.cell);
        pager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new CustomPagerAdapter(getSupportFragmentManager(),
                ViewProfile.this);


    }

    class CustomPagerAdapter extends FragmentPagerAdapter {

        Context mContext;

        public CustomPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    // Create fragment object
                    fragment = new IdentityInformationFragment();
                    break;

                case 1:
                    // Create fragment object
                    fragment = new EmergencyDetailsFragment();
                    break;
                case 2:
                    fragment = new MedicalProfileFragment();
                    break;
                case 3:
                    fragment = new FamilyInformationFragment();
                    break;
                case 4:
                    fragment = new InsuranceDetailsFragment();
                    break;

            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence value = null;
            switch (position) {

                case 0:
                    value = "Basic Details";
                    break;
                case 1:
                    value = "Emergency Details";
                    break;

                case 2:
                    value = "Medical Details";
                    break;

                case 3:
                    value = "Family Details";
                    break;

                case 4:
                    value = "Insurance Details";
                    break;

            }

            return value;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get consumer profile
        getConsumerDetails(consumerId);
    }

    public void getConsumerDetails(String consumerId2) {
        RestAdapter consumerAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("consumer")).setLogLevel(LogLevel.FULL)
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        Member getConsumerService = consumerAdapter
                .create(Member.class);
        getConsumerService.getConsumer(authToken, consumerId2,
                String.valueOf(valuesManager.getVersion()),
                new Callback<ConsumerDetailsResponse>() {

                    @Override
                    public void success(ConsumerDetailsResponse arg0,
                                        Response arg1) {

                        if (arg0.getData() != null) {
                            consumer = new ConsumerDetailsResponse();
                            consumer = arg0;
                            getBloodGroups();

                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ViewProfile.this))) {
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

    private void fillUserDetails(ConsumerDetailsResponse details) {

        if (details != null) {
            StringBuilder name_blood = new StringBuilder();
            if (!CommonCode.isNullOrEmpty(details.getData().getFullName())) {
                name_blood.append(details.getData().getFullName());
            }
            name.setText(name_blood.toString());

            if (!CommonCode.isNullOrEmpty(details.getData().getEmail())) {
                email.setText(details.getData().getEmail());
            } else {
                email.setText("");
            }

            // fill cell number
            if (!CommonCode.isNullOrEmpty(details.getData().getCellNumber())) {
                cell.setText(Html.fromHtml(details.getData().getCellNumber()));
            } else {
                cell.setText("");
            }

            // set image
            String img = null;
            if (!(CommonCode.isNullOrEmpty(details.getData().getPhotoURL()))) {
                img = details.getData().getPhotoURL().replace(" ", "%20");
            }
            Picasso.with(ViewProfile.this).load(img)
                    .placeholder(R.drawable.jeevom_back)
                    .error(R.drawable.jeevom_back)
                    .into(profile_image, new com.squareup.picasso.Callback() {

                        @Override
                        public void onSuccess() {
                            System.out.println("success");

                        }

                        @Override
                        public void onError() {
                            System.out.println("error");

                        }
                    });
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    private void getBloodGroups() {

        RestAdapter bloodAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("blood"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = bloodAdapter
                .create(Member.class);
        getConsumerService.getBloodGroup(authToken,
                (new Callback<BloodGroupResponse>() {

                    @Override
                    public void success(BloodGroupResponse arg0, Response arg1) {

                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            bloodGroups = arg0.getData().getBloodGroups();
                            if (!CommonCode.isNullOrEmpty(consumer.getData()
                                    .getGender())) {
                                if (consumer.getData().getBloodGroupType() > 0) {
                                    for (BloodGroup object : bloodGroups) {
                                        if (object.getId() == consumer
                                                .getData().getBloodGroupType()) {
                                            bloodGroup = object.getName();

                                        }
                                    }
                                }
                            }
                            getAllMembers();

                        }

                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ViewProfile.this))) {
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

    private void getAllMembers() {
        RestAdapter getFamilyMemberAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("family_members"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        FamilyMemberGetService getMemberService = getFamilyMemberAdapter
                .create(FamilyMemberGetService.class);

        getMemberService.getFamilyMembers(locationManager.getUserLocation(), authToken,
                String.valueOf(session.getMemberId()),
                new Callback<FamilyMembersResponse>() {

                    @Override
                    public void success(FamilyMembersResponse arg0,
                                        Response arg1) {

                        familyDetails = arg0;
                        memberAssociation = arg0.getData()
                                .getMemberAssociation();
                        getMemberStats();

                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ViewProfile.this))) {
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
                            FamilyMembersResponse responseValue = gson
                                    .fromJson(json, FamilyMembersResponse.class);
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

    private void getMemberStats() {
        RestAdapter getMemberStats = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("family_members"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        GetMemberStats getMemberSt = getMemberStats
                .create(GetMemberStats.class);

        getMemberSt.getMemberStatistics(authToken,
                String.valueOf(session.getMemberId()),
                new Callback<MemberStatsResponse>() {

                    @Override
                    public void success(MemberStatsResponse arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        if (arg0.getData().getBmiRange() != null) {
                            getbMI = arg0.getData().getBmiRange().getbMI();
                        } else {
                            getbMI = 0;
                        }
                        if (arg0.getData().getBmrRecommendation() != null) {
                            dailyCalorieRequirement = arg0.getData()
                                    .getBmrRecommendation()
                                    .getDailyCalorieRequirement();
                        } else {
                            dailyCalorieRequirement = 0;
                        }
                        fillUserDetails(consumer);
                        pager.setAdapter(mAdapter);
                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ViewProfile.this))) {
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
                            MemberStatsResponse responseValue = gson.fromJson(
                                    json, MemberStatsResponse.class);
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
//		MenuItem my_profile = menu.findItem(R.id.my_profile);
//		my_profile.setVisible(false);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Applying Exit Animation;
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        bloodGroup = null;
    }
}
