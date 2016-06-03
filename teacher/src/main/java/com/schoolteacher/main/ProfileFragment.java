package com.schoolteacher.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.interfaces.AppConstants;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.model.BasicProfile;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.session.Home;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.ConsumerDetailsResponse;
import com.schoolteacher.pojos.FamilyMemberData;
import com.schoolteacher.pojos.UserFamilyDetailsResult;
import com.schoolteacher.service.Member;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


/**
 * Created by chandan on 16/12/15.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, AppConstants {

    LinearLayout memberOneLayout,memberTwoLayout,memberThreeLayout,membernineLayout,member2Layout,member3Layout,
            member4Layout,  member5Layout,membereightLayout,membersevenLayout,membersixLayout,memberFourLayout,memberFiveLayout;
    TextView memberTwoName,memberOneName,membernineName,membereightName,membersevenName,membersixName,
            memberFiveName,memberThreeName,memberFourName;


    private View rootView;
    private JeevomSession session;
    SharedPreferences settings;
    private String memberId,consumerId;
    private String authToken;
    public ProgressDialogFragment progressBarFragment;
    ValuesManager valuesManager;
    CircleImageView memberprofile;
    public Home hsession;
    public List<FamilyMemberData> memberDataList;
    private GlobalAlert globalAlert;

    private List<BasicProfile> basicdata;
    //  public ImageView memberprofile;
    ConsumerDetailsResponse details;
    //  private ProgressBar upload_image_progressbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new JeevomSession(getActivity());
        hsession=new Home(getActivity());

        if(session.getLoggedInStatus()==true){
            call();
        }
    }


    public void call(){
        //  session = new JeevomSession(getActivity());
        memberId = String.valueOf(session.getMemberId());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }



        globalAlert = new GlobalAlert(getActivity());




    }


    public void save(ConsumerDetailsResponse data){
        hsession.setKEY_FirstName(data.getData().getFirstName());
        // save age
        String dateOfBirth = data.getData().getDateOfBirth();

        // get Age of User
        long age;
        if (!CommonCode.isNullOrEmpty(dateOfBirth)) {
            age = CommonCode.getYearDifferenceBetweenDates(CommonCode
                    .getCurrentTimeStamp(), data.getData().getDateOfBirth());
        } else {
            age = 0;
        }
        hsession.setKey_age(String.valueOf(age));
        hsession.setKEY_PhotoURL(data.getData().getPhotoURL());
        hsession.setKEY_DateOfBirth(data.getData().getDateOfBirth());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.health_profile_layout, container,
                false);

        memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
        memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
        memberTwoName = (TextView) rootView.findViewById(R.id.second_member_name);
        memberThreeLayout= (LinearLayout) rootView.findViewById(R.id.family_member_three);
        memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);

        membernineName = (TextView) rootView.findViewById(R.id.nine_member_name);

        membereightName = (TextView) rootView.findViewById(R.id.eight_member_name);
        membernineLayout = (LinearLayout) rootView.findViewById(R.id.family_member_nine);
        member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);

        member3Layout = (LinearLayout) rootView.findViewById(R.id.member_three_layout);

        member4Layout = (LinearLayout) rootView.findViewById(R.id.member_four_layout);

        member5Layout = (LinearLayout) rootView.findViewById(R.id.member_five_layout);


        membereightLayout = (LinearLayout) rootView.findViewById(R.id.family_member_eight);

        membersevenName = (TextView) rootView.findViewById(R.id.seven_member_name);

        membersevenLayout = (LinearLayout) rootView.findViewById(R.id.family_member_seven);
        membersixName = (TextView) rootView.findViewById(R.id.six_member_name);
        membersixLayout = (LinearLayout) rootView.findViewById(R.id.family_member_six);
        memberFiveName = (TextView) rootView.findViewById(R.id.fifth_member_name);

        memberFiveLayout = (LinearLayout) rootView.findViewById(R.id.family_member_five);

        memberThreeName = (TextView) rootView.findViewById(R.id.third_member_name);
        memberFourLayout = (LinearLayout) rootView.findViewById(R.id.family_member_four);
        memberFourName = (TextView) rootView.findViewById(R.id.four_member_name);

      /*  upload_image_progressbar = (ProgressBar) rootView
                .findViewById(R.id.image_progress);
*/
        if(session.getLoggedInStatus()==true){
            check();}


        return rootView;
    }
    public void check(){  StringBuilder name_blood = new StringBuilder();

        LinearLayout health_profile_layout = (LinearLayout) rootView.findViewById(R.id.health_profile_button);

        // memberprofile = (ImageView) rootView.findViewById(R.id.memberprofile);

        memberprofile = (CircleImageView) rootView
                .findViewById(R.id.memberprofile);

        valuesManager = new ValuesManager(getActivity().getApplicationContext());
        memberId = String.valueOf(session.getMemberId());
        consumerId = session.getConsumerIds().get(
                JeevomSession.JEEVOM_CONSUMER_ID);

        if(hsession.getKey_profile().equals("true")){
          //  Toast.makeText(getActivity(), "hit", Toast.LENGTH_SHORT).show();
            getFamilyMemberDetails(memberId);

            hsession.setKey_Profile("false");
        }
        else if(hsession.getKey_profile().equals("false")) {
           // Toast.makeText(getActivity(), "ENTER", Toast.LENGTH_SHORT).show();
            setMemberProfileOnScreen();
        }

        if(hsession.getKey_profile().equals("true")){
         //   Toast.makeText(getActivity(), "hit", Toast.LENGTH_SHORT).show();
            getConsumerDetails(consumerId);

            hsession.setKey_Profile("false");
        }else if(hsession.getKEY_PhotoURL()!=null){
            String imgUrl = hsession.getKEY_PhotoURL().replace(" ", "%20");

            Picasso.with(getActivity()).load(imgUrl)
                    .placeholder(R.drawable.jeevom_back)
                    .error(R.drawable.jeevom_back).into(memberprofile, new com.squareup.picasso.Callback() {

                @Override
                public void onSuccess() {
                    System.out.println("success");
                }

                @Override
                public void onError() {
                    System.out.println("error");
                }
            });

            String img = null;

        }


        TextView memberOneName = (TextView) rootView.findViewById(R.id.username);

 /* if((session.getName().toString()).isEmpty() || session.getName().toString().equals("- -")){

        }*/

        if ((session.getName().toString()).equals("-")||(session.getName().toString()).equals("--")||(session.getName().toString()).isEmpty()||(session.getName().toString()).equals("")||(session.getName().toString()).equals("- -")|| (session.getName().toString()).equals(" - -")||(session.getName().toString()).equals(" - - ")||(session.getName().toString()).equals("- - ")) {
            memberOneName.setText("My Profile");

        }else{
            memberOneName.setText(session.getName().toString());
        }


        health_profile_layout.setVisibility(View.VISIBLE);


        health_profile_layout.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Profile Fragment");
    }
    private void getConsumerDetails(String consumerId2) {
        RestAdapter consumerAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("consumer")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = consumerAdapter
                .create(Member.class);
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);

        getConsumerService.getConsumer(
                authToken, consumerId2,
                String.valueOf(valuesManager.getVersion()),
                new Callback<ConsumerDetailsResponse>() {
                    @Override
                    public void success(ConsumerDetailsResponse arg0,
                                        Response arg1) {
                        progressBarFragment.dismissAllowingStateLoss();

                        if (arg0.getData() != null) {
                            save(arg0);
                            // populate blood group Spinner
                            //  populateBloodGroupSpinner();
                            //  consumerDetails = arg0;
                            //verifyEmailphone(consumerDetails);
                            String img = null;
                            if (!(CommonCode.isNullOrEmpty(arg0.getData().getPhotoURL()))) {
                                img = arg0.getData().getPhotoURL().replace(" ", "%20");
                            }

                            Picasso.with(getActivity()).load(img)
                                    .placeholder(R.drawable.jeevom_back)
                                    .error(R.drawable.jeevom_back).into(memberprofile);

                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressBarFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(getActivity()))) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION);
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                            } else if (arg0.getResponse() == null) {
                                showAlert(JeevOMUtil.SOMETHING_WRONG);
                            }
                        } /*else if (arg0.getResponse().getStatus() == 426) {
                            showGooglePlayAlert(getResources().getString(
                                    R.string.google_update));
                        } */ else if (arg0.getResponse().getStatus() > 400) {
                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                        } else {
                            String json = new String(((TypedByteArray) arg0
                                    .getResponse().getBody()).getBytes());
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();


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



    public void getFamilyMemberDetails(String memberId) {

        RestAdapter consumerAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("consumer")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();

        final DialogFragment newFragment = ProgressDialogFragment.newInstance();
        newFragment.setCancelable(false);
        newFragment.show(getActivity().getSupportFragmentManager(), "dialog");

        Member getConsumerService = consumerAdapter
                .create(Member.class);
        getConsumerService.getFamilyMemberList(
                authToken, memberId,
                new Callback<UserFamilyDetailsResult>() {

                    @Override
                    public void success(UserFamilyDetailsResult arg0,
                                        Response arg1) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.getData() != null) {
                            // populate blood group Spinner'

                            memberDataList = arg0.getData().getMemberAssociation();
                       //     Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                            saveSharedPreferencesLogList(getActivity(), memberDataList);
                            setMemberProfileOnScreen();
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
                        } else {
                            showAlert(JeevOMUtil.SOMETHING_WRONG);

                        }
                    }
                });


    }

    public static void saveSharedPreferencesLogList(Context context, List<FamilyMemberData> callLog) {
        SharedPreferences mPrefs = context.getSharedPreferences("load", context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(callLog);
        prefsEditor.putString("myJson", json);
        prefsEditor.commit();
    }

    public static List<FamilyMemberData> loadSharedPreferencesLogList(Context context) {
        List<FamilyMemberData> callLog = new ArrayList<FamilyMemberData>();
        SharedPreferences mPrefs = context.getSharedPreferences("load", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("myJson", null);
        if (json.isEmpty()) {
            callLog = new ArrayList<FamilyMemberData>();
        } else {
            Type type = new TypeToken<List<FamilyMemberData>>() {
            }.getType();
            callLog = gson.fromJson(json, type);
        }
        return callLog;
    }
    public void setMemberProfileOnScreen() {



        if (loadSharedPreferencesLogList(getActivity()).size() >= 0 && loadSharedPreferencesLogList(getActivity()).size()  <= 9) {
            int count = loadSharedPreferencesLogList(getActivity()).size();

            if (count == 0) {


                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberTwoLayout.setVisibility(View.GONE);

                LinearLayout onetoaddfamily1 = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily1);
                onetoaddfamily1.setVisibility(View.VISIBLE);
                TextView memberTwoName1 = (TextView) rootView.findViewById(R.id.addfamily1);
                memberTwoName1.setText("Add Member");
                onetoaddfamily1.setVisibility(View.VISIBLE);
                onetoaddfamily1.setOnClickListener(this);
            } else if (count == 1) {
                LinearLayout memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberOneLayout.setVisibility(View.VISIBLE);
                TextView memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);
                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);


                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
                memberTwoLayout.setVisibility(View.GONE);


                LinearLayout member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);
                member2Layout.setVisibility(View.VISIBLE);


                LinearLayout onetoaddfamily = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily2);
                onetoaddfamily.setVisibility(View.VISIBLE);
                TextView memberTwoName = (TextView) rootView.findViewById(R.id.addfamily);
                memberTwoName.setText("Add Member");
                onetoaddfamily.setVisibility(View.VISIBLE);
                onetoaddfamily.setOnClickListener(this);

            } else if (count == 2) {
                LinearLayout memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberOneLayout.setVisibility(View.VISIBLE);
                TextView memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);
                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);

                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
                memberTwoLayout.setVisibility(View.VISIBLE);
                TextView memberTwoName = (TextView) rootView.findViewById(R.id.second_member_name);
                memberTwoName.setText(loadSharedPreferencesLogList(getActivity()).get(1).getFirstName().toString());
                memberTwoLayout.setVisibility(View.VISIBLE);
                memberTwoLayout.setOnClickListener(this);


                LinearLayout member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);
                member2Layout.setVisibility(View.VISIBLE);


                LinearLayout memberTwoLayoutt = (LinearLayout) rootView.findViewById(R.id.family_member_three);
                memberTwoLayoutt.setVisibility(View.GONE);

                LinearLayout onetoaddfamily = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily3);
                onetoaddfamily.setVisibility(View.VISIBLE);
                TextView memberTwoName3 = (TextView) rootView.findViewById(R.id.addfamily3);
                memberTwoName3.setText("Add Member");
                onetoaddfamily.setVisibility(View.VISIBLE);
                onetoaddfamily.setOnClickListener(this);


            } else if (count == 3) {
                LinearLayout memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberOneLayout.setVisibility(View.VISIBLE);
                TextView memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);
                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);

                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
                memberTwoLayout.setVisibility(View.VISIBLE);
                TextView memberTwoName = (TextView) rootView.findViewById(R.id.second_member_name);
                memberTwoName.setText(loadSharedPreferencesLogList(getActivity()).get(1).getFirstName().toString());
                memberTwoLayout.setVisibility(View.VISIBLE);
                memberTwoLayout.setOnClickListener(this);


                LinearLayout memberThreeLayout = (LinearLayout) rootView.findViewById(R.id.family_member_three);
                memberThreeLayout.setVisibility(View.VISIBLE);
                TextView memberThreeName = (TextView) rootView.findViewById(R.id.third_member_name);
                memberThreeName.setText(loadSharedPreferencesLogList(getActivity()).get(2).getFirstName().toString());
                memberThreeLayout.setVisibility(View.VISIBLE);
                memberThreeLayout.setOnClickListener(this);


                LinearLayout member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);
                member2Layout.setVisibility(View.VISIBLE);
                LinearLayout member3Layout = (LinearLayout) rootView.findViewById(R.id.member_three_layout);
                member3Layout.setVisibility(View.VISIBLE);


                LinearLayout memberTwoLayoutt = (LinearLayout) rootView.findViewById(R.id.family_member_four);
                memberTwoLayoutt.setVisibility(View.GONE);

                LinearLayout onetoaddfamily = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily4);
                onetoaddfamily.setVisibility(View.VISIBLE);
                TextView memberTwoName4 = (TextView) rootView.findViewById(R.id.addfamily4);
                memberTwoName4.setText("Add Member");
                onetoaddfamily.setVisibility(View.VISIBLE);
                onetoaddfamily.setOnClickListener(this);

            } else if (count == 4) {

                LinearLayout memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberOneLayout.setVisibility(View.VISIBLE);
                TextView memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);
                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);

                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
                memberTwoLayout.setVisibility(View.VISIBLE);
                TextView memberTwoName = (TextView) rootView.findViewById(R.id.second_member_name);
                memberTwoName.setText(loadSharedPreferencesLogList(getActivity()).get(1).getFirstName().toString());
                memberTwoLayout.setVisibility(View.VISIBLE);
                memberTwoLayout.setOnClickListener(this);

                LinearLayout memberThreeLayout = (LinearLayout) rootView.findViewById(R.id.family_member_three);
                memberThreeLayout.setVisibility(View.VISIBLE);
                TextView memberThreeName = (TextView) rootView.findViewById(R.id.third_member_name);
                memberThreeName.setText(loadSharedPreferencesLogList(getActivity()).get(2).getFirstName().toString());
                memberThreeLayout.setVisibility(View.VISIBLE);
                memberThreeLayout.setOnClickListener(this);

                LinearLayout memberFourLayout = (LinearLayout) rootView.findViewById(R.id.family_member_four);
                memberFourLayout.setVisibility(View.VISIBLE);
                TextView memberFourName = (TextView) rootView.findViewById(R.id.four_member_name);
                memberFourName.setText(loadSharedPreferencesLogList(getActivity()).get(3).getFirstName().toString());
                memberFourLayout.setVisibility(View.VISIBLE);
                memberFourLayout.setOnClickListener(this);

                LinearLayout member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);
                member2Layout.setVisibility(View.VISIBLE);
                LinearLayout member3Layout = (LinearLayout) rootView.findViewById(R.id.member_three_layout);
                member3Layout.setVisibility(View.VISIBLE);


                LinearLayout memberTwoLayoutt = (LinearLayout) rootView.findViewById(R.id.family_member_five);
                memberTwoLayoutt.setVisibility(View.GONE);

                LinearLayout onetoaddfamily = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily5);
                onetoaddfamily.setVisibility(View.VISIBLE);
                TextView memberTwoName5 = (TextView) rootView.findViewById(R.id.addfamily5);
                memberTwoName5.setText("Add Member");
                onetoaddfamily.setVisibility(View.VISIBLE);
                onetoaddfamily.setOnClickListener(this);

            } else if (count == 5) {
                LinearLayout memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberOneLayout.setVisibility(View.VISIBLE);
                TextView memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);
                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);

                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
                memberTwoLayout.setVisibility(View.VISIBLE);
                TextView memberTwoName = (TextView) rootView.findViewById(R.id.second_member_name);
                memberTwoName.setText(loadSharedPreferencesLogList(getActivity()).get(1).getFirstName().toString());
                memberTwoLayout.setVisibility(View.VISIBLE);
                memberTwoLayout.setOnClickListener(this);

                LinearLayout memberThreeLayout = (LinearLayout) rootView.findViewById(R.id.family_member_three);
                memberThreeLayout.setVisibility(View.VISIBLE);
                TextView memberThreeName = (TextView) rootView.findViewById(R.id.third_member_name);
                memberThreeName.setText(loadSharedPreferencesLogList(getActivity()).get(2).getFirstName().toString());
                memberThreeLayout.setVisibility(View.VISIBLE);
                memberThreeLayout.setOnClickListener(this);

                LinearLayout memberFourLayout = (LinearLayout) rootView.findViewById(R.id.family_member_four);
                memberFourLayout.setVisibility(View.VISIBLE);
                TextView memberFourName = (TextView) rootView.findViewById(R.id.four_member_name);
                memberFourName.setText(loadSharedPreferencesLogList(getActivity()).get(3).getFirstName().toString());
                memberFourLayout.setVisibility(View.VISIBLE);
                memberFourLayout.setOnClickListener(this);

                LinearLayout memberFiveLayout = (LinearLayout) rootView.findViewById(R.id.family_member_five);
                memberFiveLayout.setVisibility(View.VISIBLE);
                TextView memberFiveName = (TextView) rootView.findViewById(R.id.fifth_member_name);
                memberFiveName.setText(loadSharedPreferencesLogList(getActivity()).get(4).getFirstName().toString());
                memberFiveLayout.setVisibility(View.VISIBLE);
                memberFiveLayout.setOnClickListener(this);


                LinearLayout member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);
                member2Layout.setVisibility(View.VISIBLE);
                LinearLayout member3Layout = (LinearLayout) rootView.findViewById(R.id.member_three_layout);
                member3Layout.setVisibility(View.VISIBLE);
                LinearLayout member4Layout = (LinearLayout) rootView.findViewById(R.id.member_four_layout);
                member4Layout.setVisibility(View.VISIBLE);

                LinearLayout memberTwoLayoutt = (LinearLayout) rootView.findViewById(R.id.family_member_six);
                memberTwoLayoutt.setVisibility(View.GONE);

                LinearLayout onetoaddfamily6 = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily6);
                onetoaddfamily6.setVisibility(View.VISIBLE);
                TextView memberTwoName6 = (TextView) rootView.findViewById(R.id.addfamily6);
                memberTwoName6.setText("Add Member");
                onetoaddfamily6.setVisibility(View.VISIBLE);
                onetoaddfamily6.setOnClickListener(this);

            } else if (count == 6) {
                LinearLayout memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberOneLayout.setVisibility(View.VISIBLE);
                TextView memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);
                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);

                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
                memberTwoLayout.setVisibility(View.VISIBLE);
                TextView memberTwoName = (TextView) rootView.findViewById(R.id.second_member_name);
                memberTwoName.setText(loadSharedPreferencesLogList(getActivity()).get(1).getFirstName().toString());
                memberTwoLayout.setVisibility(View.VISIBLE);
                memberTwoLayout.setOnClickListener(this);

                LinearLayout memberThreeLayout = (LinearLayout) rootView.findViewById(R.id.family_member_three);
                memberThreeLayout.setVisibility(View.VISIBLE);
                TextView memberThreeName = (TextView) rootView.findViewById(R.id.third_member_name);
                memberThreeName.setText(loadSharedPreferencesLogList(getActivity()).get(2).getFirstName().toString());
                memberThreeLayout.setVisibility(View.VISIBLE);
                memberThreeLayout.setOnClickListener(this);

                LinearLayout memberFourLayout = (LinearLayout) rootView.findViewById(R.id.family_member_four);
                memberFourLayout.setVisibility(View.VISIBLE);
                TextView memberFourName = (TextView) rootView.findViewById(R.id.four_member_name);
                memberFourName.setText(loadSharedPreferencesLogList(getActivity()).get(3).getFirstName().toString());
                memberFourLayout.setVisibility(View.VISIBLE);
                memberFourLayout.setOnClickListener(this);

                LinearLayout memberFiveLayout = (LinearLayout) rootView.findViewById(R.id.family_member_five);
                memberFiveLayout.setVisibility(View.VISIBLE);
                TextView memberFiveName = (TextView) rootView.findViewById(R.id.fifth_member_name);
                memberFiveName.setText(loadSharedPreferencesLogList(getActivity()).get(4).getFirstName().toString());
                memberFiveLayout.setVisibility(View.VISIBLE);
                memberFiveLayout.setOnClickListener(this);


                LinearLayout membersixLayout = (LinearLayout) rootView.findViewById(R.id.family_member_six);
                membersixLayout.setVisibility(View.VISIBLE);
                TextView membersixName = (TextView) rootView.findViewById(R.id.six_member_name);
                membersixName.setText(loadSharedPreferencesLogList(getActivity()).get(5).getFirstName().toString());
                membersixLayout.setVisibility(View.VISIBLE);
                membersixLayout.setOnClickListener(this);


                LinearLayout member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);
                member2Layout.setVisibility(View.VISIBLE);
                LinearLayout member3Layout = (LinearLayout) rootView.findViewById(R.id.member_three_layout);
                member3Layout.setVisibility(View.VISIBLE);
                LinearLayout member4Layout = (LinearLayout) rootView.findViewById(R.id.member_four_layout);
                member4Layout.setVisibility(View.VISIBLE);

                LinearLayout memberTwoLayouttt = (LinearLayout) rootView.findViewById(R.id.family_member_seven);
                memberTwoLayouttt.setVisibility(View.GONE);

                LinearLayout onetoaddfamily7 = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily7);
                onetoaddfamily7.setVisibility(View.VISIBLE);
                TextView memberTwoName7 = (TextView) rootView.findViewById(R.id.addfamily7);
                memberTwoName7.setText("Add Member");
                onetoaddfamily7.setVisibility(View.VISIBLE);
                onetoaddfamily7.setOnClickListener(this);

            } else if (count == 7) {
                LinearLayout memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberOneLayout.setVisibility(View.VISIBLE);
                TextView memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);
                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);

                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
                memberTwoLayout.setVisibility(View.VISIBLE);
                TextView memberTwoName = (TextView) rootView.findViewById(R.id.second_member_name);
                memberTwoName.setText(loadSharedPreferencesLogList(getActivity()).get(1).getFirstName().toString());
                memberTwoLayout.setVisibility(View.VISIBLE);
                memberTwoLayout.setOnClickListener(this);

                LinearLayout memberThreeLayout = (LinearLayout) rootView.findViewById(R.id.family_member_three);
                memberThreeLayout.setVisibility(View.VISIBLE);
                TextView memberThreeName = (TextView) rootView.findViewById(R.id.third_member_name);
                memberThreeName.setText(loadSharedPreferencesLogList(getActivity()).get(2).getFirstName().toString());
                memberThreeLayout.setVisibility(View.VISIBLE);
                memberThreeLayout.setOnClickListener(this);

                LinearLayout memberFourLayout = (LinearLayout) rootView.findViewById(R.id.family_member_four);
                memberFourLayout.setVisibility(View.VISIBLE);
                TextView memberFourName = (TextView) rootView.findViewById(R.id.four_member_name);
                memberFourName.setText(loadSharedPreferencesLogList(getActivity()).get(3).getFirstName().toString());
                memberFourLayout.setVisibility(View.VISIBLE);
                memberFourLayout.setOnClickListener(this);

                LinearLayout memberFiveLayout = (LinearLayout) rootView.findViewById(R.id.family_member_five);
                memberFiveLayout.setVisibility(View.VISIBLE);
                TextView memberFiveName = (TextView) rootView.findViewById(R.id.fifth_member_name);
                memberFiveName.setText(loadSharedPreferencesLogList(getActivity()).get(4).getFirstName().toString());
                memberFiveLayout.setVisibility(View.VISIBLE);
                memberFiveLayout.setOnClickListener(this);


                LinearLayout membersixLayout = (LinearLayout) rootView.findViewById(R.id.family_member_six);
                membersixLayout.setVisibility(View.VISIBLE);
                TextView membersixName = (TextView) rootView.findViewById(R.id.six_member_name);
                membersixName.setText(loadSharedPreferencesLogList(getActivity()).get(5).getFirstName().toString());
                membersixLayout.setVisibility(View.VISIBLE);
                membersixLayout.setOnClickListener(this);


                LinearLayout membersevenLayout = (LinearLayout) rootView.findViewById(R.id.family_member_seven);
                membersevenLayout.setVisibility(View.VISIBLE);
                TextView membersevenName = (TextView) rootView.findViewById(R.id.seven_member_name);
                membersevenName.setText(loadSharedPreferencesLogList(getActivity()).get(6).getFirstName().toString());
                membersevenLayout.setVisibility(View.VISIBLE);
                membersevenLayout.setOnClickListener(this);

                LinearLayout member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);
                member2Layout.setVisibility(View.VISIBLE);
                LinearLayout member3Layout = (LinearLayout) rootView.findViewById(R.id.member_three_layout);
                member3Layout.setVisibility(View.VISIBLE);
                LinearLayout member4Layout = (LinearLayout) rootView.findViewById(R.id.member_four_layout);
                member4Layout.setVisibility(View.VISIBLE);
                LinearLayout member5Layout = (LinearLayout) rootView.findViewById(R.id.member_five_layout);
                member5Layout.setVisibility(View.VISIBLE);

                LinearLayout memberTwoLayoutttt = (LinearLayout) rootView.findViewById(R.id.family_member_eight);
                memberTwoLayoutttt.setVisibility(View.GONE);

                LinearLayout onetoaddfamily7 = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily8);
                onetoaddfamily7.setVisibility(View.VISIBLE);
                TextView memberTwoName7 = (TextView) rootView.findViewById(R.id.addfamily8);
                memberTwoName7.setText("Add Member");
                onetoaddfamily7.setVisibility(View.VISIBLE);
                onetoaddfamily7.setOnClickListener(this);

            } else if (count == 8) {
                LinearLayout memberOneLayout = (LinearLayout) rootView.findViewById(R.id.family_member_one);
                memberOneLayout.setVisibility(View.VISIBLE);
                TextView memberOneName = (TextView) rootView.findViewById(R.id.first_member_name);
                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);

                LinearLayout memberTwoLayout = (LinearLayout) rootView.findViewById(R.id.family_member_two);
                memberTwoLayout.setVisibility(View.VISIBLE);
                TextView memberTwoName = (TextView) rootView.findViewById(R.id.second_member_name);
                memberTwoName.setText(loadSharedPreferencesLogList(getActivity()).get(1).getFirstName().toString());
                memberTwoLayout.setVisibility(View.VISIBLE);
                memberTwoLayout.setOnClickListener(this);

                LinearLayout memberThreeLayout = (LinearLayout) rootView.findViewById(R.id.family_member_three);
                memberThreeLayout.setVisibility(View.VISIBLE);
                TextView memberThreeName = (TextView) rootView.findViewById(R.id.third_member_name);
                memberThreeName.setText(loadSharedPreferencesLogList(getActivity()).get(2).getFirstName().toString());
                memberThreeLayout.setVisibility(View.VISIBLE);
                memberThreeLayout.setOnClickListener(this);

                LinearLayout memberFourLayout = (LinearLayout) rootView.findViewById(R.id.family_member_four);
                memberFourLayout.setVisibility(View.VISIBLE);
                TextView memberFourName = (TextView) rootView.findViewById(R.id.four_member_name);
                memberFourName.setText(loadSharedPreferencesLogList(getActivity()).get(3).getFirstName().toString());
                memberFourLayout.setVisibility(View.VISIBLE);
                memberFourLayout.setOnClickListener(this);

                LinearLayout memberFiveLayout = (LinearLayout) rootView.findViewById(R.id.family_member_five);
                memberFiveLayout.setVisibility(View.VISIBLE);
                TextView memberFiveName = (TextView) rootView.findViewById(R.id.fifth_member_name);
                memberFiveName.setText(loadSharedPreferencesLogList(getActivity()).get(4).getFirstName().toString());
                memberFiveLayout.setVisibility(View.VISIBLE);
                memberFiveLayout.setOnClickListener(this);


                LinearLayout membersixLayout = (LinearLayout) rootView.findViewById(R.id.family_member_six);
                membersixLayout.setVisibility(View.VISIBLE);
                TextView membersixName = (TextView) rootView.findViewById(R.id.six_member_name);
                membersixName.setText(loadSharedPreferencesLogList(getActivity()).get(5).getFirstName().toString());
                membersixLayout.setVisibility(View.VISIBLE);
                membersixLayout.setOnClickListener(this);


                LinearLayout membersevenLayout = (LinearLayout) rootView.findViewById(R.id.family_member_seven);
                membersevenLayout.setVisibility(View.VISIBLE);
                TextView membersevenName = (TextView) rootView.findViewById(R.id.seven_member_name);
                membersevenName.setText(loadSharedPreferencesLogList(getActivity()).get(6).getFirstName().toString());
                membersevenLayout.setVisibility(View.VISIBLE);
                membersevenLayout.setOnClickListener(this);


                LinearLayout membereightLayout = (LinearLayout) rootView.findViewById(R.id.family_member_eight);
                membereightLayout.setVisibility(View.VISIBLE);
                TextView membereightName = (TextView) rootView.findViewById(R.id.eight_member_name);
                membereightName.setText(loadSharedPreferencesLogList(getActivity()).get(7).getFirstName().toString());
                membereightLayout.setVisibility(View.VISIBLE);
                membereightLayout.setOnClickListener(this);


                LinearLayout member2Layout = (LinearLayout) rootView.findViewById(R.id.member_two_layout);
                member2Layout.setVisibility(View.VISIBLE);
                LinearLayout member3Layout = (LinearLayout) rootView.findViewById(R.id.member_three_layout);
                member3Layout.setVisibility(View.VISIBLE);
                LinearLayout member4Layout = (LinearLayout) rootView.findViewById(R.id.member_four_layout);
                member4Layout.setVisibility(View.VISIBLE);
                LinearLayout member5Layout = (LinearLayout) rootView.findViewById(R.id.member_five_layout);
                member5Layout.setVisibility(View.VISIBLE);


                LinearLayout memberTwoLayouttttt = (LinearLayout) rootView.findViewById(R.id.family_member_nine);
                memberTwoLayouttttt.setVisibility(View.GONE);

                LinearLayout onetoaddfamily8 = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily9);
                onetoaddfamily8.setVisibility(View.VISIBLE);
                TextView memberTwoName8 = (TextView) rootView.findViewById(R.id.addfamily9);
                memberTwoName8.setText("Add Member");
                onetoaddfamily8.setVisibility(View.VISIBLE);
                onetoaddfamily8.setOnClickListener(this);
            } else if (count == 9) {

                memberOneLayout.setVisibility(View.VISIBLE);

                memberOneName.setText(loadSharedPreferencesLogList(getActivity()).get(0).getFirstName().toString());
                memberOneLayout.setVisibility(View.VISIBLE);
                memberOneLayout.setOnClickListener(this);
                memberTwoLayout.setVisibility(View.VISIBLE);

                memberTwoName.setText(loadSharedPreferencesLogList(getActivity()).get(1).getFirstName().toString());
                memberTwoLayout.setVisibility(View.VISIBLE);
                memberTwoLayout.setOnClickListener(this);


                memberThreeLayout.setVisibility(View.VISIBLE);

                memberThreeName.setText(loadSharedPreferencesLogList(getActivity()).get(2).getFirstName().toString());
                memberThreeLayout.setVisibility(View.VISIBLE);
                memberThreeLayout.setOnClickListener(this);


                memberFourLayout.setVisibility(View.VISIBLE);

                memberFourName.setText(loadSharedPreferencesLogList(getActivity()).get(3).getFirstName().toString());
                memberFourLayout.setVisibility(View.VISIBLE);
                memberFourLayout.setOnClickListener(this);


                memberFiveLayout.setVisibility(View.VISIBLE);

                memberFiveName.setText(loadSharedPreferencesLogList(getActivity()).get(4).getFirstName().toString());
                memberFiveLayout.setVisibility(View.VISIBLE);
                memberFiveLayout.setOnClickListener(this);


                membersixLayout.setVisibility(View.VISIBLE);

                membersixName.setText(loadSharedPreferencesLogList(getActivity()).get(5).getFirstName().toString());
                membersixLayout.setVisibility(View.VISIBLE);
                membersixLayout.setOnClickListener(this);


                membersevenLayout.setVisibility(View.VISIBLE);

                membersevenName.setText(loadSharedPreferencesLogList(getActivity()).get(6).getFirstName().toString());
                membersevenLayout.setVisibility(View.VISIBLE);
                membersevenLayout.setOnClickListener(this);


                membereightLayout.setVisibility(View.VISIBLE);

                membereightName.setText(loadSharedPreferencesLogList(getActivity()).get(7).getFirstName().toString());
                membereightLayout.setVisibility(View.VISIBLE);
                membereightLayout.setOnClickListener(this);
                membernineLayout.setVisibility(View.VISIBLE);
                membernineName.setText(loadSharedPreferencesLogList(getActivity()).get(8).getFirstName().toString());
                membernineLayout.setVisibility(View.VISIBLE);
                membernineLayout.setOnClickListener(this);


                member2Layout.setVisibility(View.VISIBLE);
                member3Layout.setVisibility(View.VISIBLE);
                member4Layout.setVisibility(View.VISIBLE);

                member5Layout.setVisibility(View.VISIBLE);



              /*  LinearLayout memberTwoLayouttttt = (LinearLayout) rootView.findViewById(R.id.family_member_nine);
                memberTwoLayouttttt.setVisibility(View.GONE);

                LinearLayout onetoaddfamily8 = (LinearLayout) rootView.findViewById(R.id.onetoaddfamily9);
                onetoaddfamily8.setVisibility(View.VISIBLE);
                TextView memberTwoName8 = (TextView) rootView.findViewById(R.id.addfamily9);
                memberTwoName8.setText("Add Member");
                onetoaddfamily8.setVisibility(View.VISIBLE);
                onetoaddfamily8.setOnClickListener(this);*//**//*

            }
*/
            }

        }
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.health_profile_button:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(null);
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.family_member_one:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(0).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.family_member_two:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(1).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.family_member_three:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(2).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.family_member_four:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(3).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.family_member_five:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(4).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.family_member_six:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(5).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;
            case R.id.family_member_seven:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(6).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.family_member_eight:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(7).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.family_member_nine:
                if (session.getLoggedInStatus()) {
                    ((HomeActivity) getActivity()).callHealthProfileScreen(loadSharedPreferencesLogList(getActivity()).get(8).getMemberBasicProfileId());
                } else {
                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;

            case R.id.onetoaddfamily1:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;


            case R.id.onetoaddfamily2:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }
                break;


            case R.id.onetoaddfamily3:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }


           /*     session = new JeevomSession(getActivity());
                memberId = String.valueOf(session.getMemberId());
                if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
                    authToken = "Basic " + session.getAuthToken();
                }

                globalAlert = new GlobalAlert(getActivity());
                memberDataList = new ArrayList<>();
           */
                break;



            case R.id.onetoaddfamily4:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }

                break;




            case R.id.onetoaddfamily5:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }

                break;



            case R.id.onetoaddfamily6:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }

                break;

            case R.id.onetoaddfamily7:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }

                break;

            case R.id.onetoaddfamily8:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }

                break;


            case R.id.onetoaddfamily9:
                if(session.getLoggedInStatus()){
                    ((HomeActivity) getActivity()).calladdfamily();

                }else{

                    ((HomeActivity) getActivity()).callLogIn(HEALTH_PROFILE_REQUEST_CODE);
                }

                break;





            default:
                break;
        }

    }

}