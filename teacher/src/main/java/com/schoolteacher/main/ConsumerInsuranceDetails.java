package com.schoolteacher.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.dialog.GooglePlayAlert;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.ConsumerDetailsResponse;
import com.schoolteacher.pojos.MemberInsuranceDetail;
import com.schoolteacher.service.Member;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class ConsumerInsuranceDetails extends Fragment implements
        OnClickListener {

    View view;
    Button add_more_insurance, save;
    String consumerId;
    GlobalAlert globalAlert;
    Gson gson;
    DialogFragment newFragment;
    List<MemberInsuranceDetail> memberInsuranceDetails;
    LinearLayout insurance_details;
    MenuItem add_more_menu_item;
    ValuesManager valuesManager;
    JeevomSession session;
    GooglePlayAlert googleAlert;
    String authToken = null;
    UserCurrentLocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Consumer Insurance Detail Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_insurance, container, false);
        valuesManager = new ValuesManager(getActivity().getApplicationContext());
        googleAlert = new GooglePlayAlert(getActivity());
        session = new JeevomSession(getActivity().getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        locationManager = new UserCurrentLocationManager(getActivity().getApplicationContext());
        consumerId = session.getConsumerIds().get(
                JeevomSession.JEEVOM_CONSUMER_ID);
        globalAlert = new GlobalAlert(getActivity());
        gson = new GsonBuilder().setPrettyPrinting().create();
        insurance_details = (LinearLayout) view
                .findViewById(R.id.insurance_details);
        save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        });
        // get consumer profile
        getConsumerDetails(consumerId);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            getConsumerDetails(consumerId);
        }
    }

    private void getConsumerDetails(String consumerId2) {
        RestAdapter consumerAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("consumer")).setLogLevel(LogLevel.FULL)
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = consumerAdapter
                .create(Member.class);
        getConsumerService.getConsumer(authToken, consumerId2,
                String.valueOf(valuesManager.getVersion()),
                new Callback<ConsumerDetailsResponse>() {

                    @Override
                    public void success(ConsumerDetailsResponse arg0,
                                        Response arg1) {
                        if (arg0.getData() != null) {

                            memberInsuranceDetails = arg0.getData()
                                    .getMemberInsuranceDetails();
                            addToLayout();
                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {

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

    protected void addToLayout() {
        if (insurance_details.getChildCount() > 0) {
            insurance_details.removeAllViews();
        }
        if (memberInsuranceDetails != null) {
            if (memberInsuranceDetails.size() > 0) {
                for (MemberInsuranceDetail object : memberInsuranceDetails) {
                    LayoutInflater inflator = (LayoutInflater) getActivity()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View view = inflator.inflate(R.layout.family_member_row,
                            null);
                    if (insurance_details.getChildCount() % 2 == 0) {
                        view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else {
                        view.setBackgroundColor(Color.parseColor("#efefef"));
                    }
                    TextView name = (TextView) view.findViewById(R.id.name);
                    TextView relation = (TextView) view
                            .findViewById(R.id.associationName);

                    if (!CommonCode.isNullOrEmpty(object.getProviderName())) {
                        name.setText(object.getProviderName());
                    }
                    if (!CommonCode.isNullOrEmpty(object.getPolicyName())) {
                        relation.setText(object.getPolicyName());

                    }

                    final ImageView edit = (ImageView) view
                            .findViewById(R.id.edit);
                    edit.setTag(object);

                    edit.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            MemberInsuranceDetail tag = (MemberInsuranceDetail) edit
                                    .getTag();
                            Intent addMoreIntent = new Intent(getActivity(),
                                    InsuranceDetails.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("member_insurance_details",
                                    tag);
                            addMoreIntent.putExtras(bundle);
                            startActivityForResult(addMoreIntent, 1);

                        }
                    });
                    insurance_details.addView(view);

                }
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;
        }

    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        add_more_menu_item = menu.add(" Add Insurance");
        add_more_menu_item.setIcon(R.drawable.insurance_add);
        add_more_menu_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_WITH_TEXT
                | MenuItem.SHOW_AS_ACTION_ALWAYS);

        add_more_menu_item
                .setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Intent add_more_insurance = new Intent(getActivity(),
                                InsuranceDetails.class);
                        startActivityForResult(add_more_insurance, 1);
                        return true;
                    }
                });
    }
}
