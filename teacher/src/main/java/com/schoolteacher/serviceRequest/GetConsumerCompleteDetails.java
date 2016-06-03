package com.schoolteacher.serviceRequest;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.interfaces.SendConsumerDetails;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.BloodGroup;
import com.schoolteacher.pojos.BloodGroupResponse;
import com.schoolteacher.pojos.ConsumerDetailsResponse;
import com.schoolteacher.pojos.FamilyMembersResponse;
import com.schoolteacher.pojos.MemberAssociation;
import com.schoolteacher.pojos.MemberStatsResponse;
import com.schoolteacher.service.FamilyMemberGetService;
import com.schoolteacher.service.GetMemberStats;
import com.schoolteacher.service.Member;

import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class GetConsumerCompleteDetails {
    String authToken = null;
    String bloodGroup;
    int consumerId;
    ConsumerDetailsResponse consumer;
    String version;
    String message;
    Activity activity;
    List<BloodGroup> bloodGroups;
    JeevomSession session;
    FamilyMembersResponse familyDetails;
    List<MemberAssociation> memberAssociation;
    double getbMI, dailyCalorieRequirement;
    private SendConsumerDetails sendConsumerDetailsCallback;
    UserCurrentLocationManager locationManager;

    Gson gson;


    public GetConsumerCompleteDetails(int consumer_id, String version,
                                      Activity activity, JeevomSession session) {
        this.consumerId = consumer_id;
        this.version = version;
        this.activity = activity;
        gson = new GsonBuilder().create();
        bloodGroups = new LinkedList<BloodGroup>();
        memberAssociation = new LinkedList<>();
        this.session = session;
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        locationManager = new UserCurrentLocationManager(activity.getApplicationContext());
        sendConsumerDetailsCallback = (SendConsumerDetails) activity;
    }

    void getDetails() {
        getConsumerDetails(consumerId);
    }

    private void getConsumerDetails(int consumerId2) {
        RestAdapter consumerAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("consumer")).setLogLevel(LogLevel.FULL)
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = consumerAdapter
                .create(Member.class);
        getConsumerService.getConsumer(authToken, String.valueOf(consumerId2),
                version, new Callback<ConsumerDetailsResponse>() {

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
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(activity))) {
                                message = JeevOMUtil.INTERNET_CONNECTION;
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                message = JeevOMUtil.INTERNET_CONNECTION_SLOW;
                            } else if (arg0.getResponse() == null) {
                                message = JeevOMUtil.SOMETHING_WRONG;
                            }
                        } else if (arg0.getResponse().getStatus() == 426) {
                            message = "google update needed";

                        } else if (arg0.getResponse().getStatus() > 400) {
                            message = JeevOMUtil.SOMETHING_WRONG;
                        } else {
                            message = JeevOMUtil.SOMETHING_WRONG;
                        }
                    }
                });

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
                            getAllMembers();

                        }

                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        if (arg0.isNetworkError()) {

                            if (!(Connectivity.checkConnectivity(activity))) {
                                message = JeevOMUtil.INTERNET_CONNECTION;
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                message = JeevOMUtil.INTERNET_CONNECTION_SLOW;
                            } else if (arg0.getResponse() == null) {
                                message = JeevOMUtil.SOMETHING_WRONG;
                            }
                        } else if (arg0.getResponse().getStatus() == 426) {
                            message = "google update";

                        } else if (arg0.getResponse().getStatus() > 400) {
                            message = JeevOMUtil.SOMETHING_WRONG;
                        } else {

                            message = JeevOMUtil.SOMETHING_WRONG;
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

                        if (arg0.isNetworkError()) {

                            if (!(Connectivity.checkConnectivity(activity))) {
                                message = JeevOMUtil.INTERNET_CONNECTION;
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                message = JeevOMUtil.INTERNET_CONNECTION_SLOW;
                            } else if (arg0.getResponse() == null) {
                                message = JeevOMUtil.SOMETHING_WRONG;
                            }
                        } else if (arg0.getResponse().getStatus() == 426) {
                            message = "google update";

                        } else if (arg0.getResponse().getStatus() > 400) {
                            message = JeevOMUtil.SOMETHING_WRONG;
                        } else {

                            message = JeevOMUtil.SOMETHING_WRONG;
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

                        sendDetailsBack();
                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        if (arg0.isNetworkError()) {

                            if (!(Connectivity.checkConnectivity(activity))) {
                                message = JeevOMUtil.INTERNET_CONNECTION;
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                message = JeevOMUtil.INTERNET_CONNECTION_SLOW;
                            } else if (arg0.getResponse() == null) {
                                message = JeevOMUtil.SOMETHING_WRONG;
                            }
                        } else if (arg0.getResponse().getStatus() == 426) {
                            message = "google update";

                        } else if (arg0.getResponse().getStatus() > 400) {
                            message = JeevOMUtil.SOMETHING_WRONG;
                        } else {

                            message = JeevOMUtil.SOMETHING_WRONG;
                        }
                    }
                });
    }

    private void sendDetailsBack() {
        sendConsumerDetailsCallback.sendConsumerDetails(getbMI,
                dailyCalorieRequirement, familyDetails, bloodGroups, consumer);
    }
}
