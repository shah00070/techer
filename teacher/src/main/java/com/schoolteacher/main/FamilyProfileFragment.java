package com.schoolteacher.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.FamilyMembersResponse;
import com.schoolteacher.pojos.MemberAssociation;
import com.schoolteacher.service.FamilyMemberGetService;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class FamilyProfileFragment extends Fragment {
    Button btn_user_save, btn_user_save_skip;
    View rootView;
    DialogFragment newFragment;
    JeevomSession session;
    GlobalAlert globalAlert;
    List<MemberAssociation> memberAssociation;
    LinearLayout members;
    ViewPager viewPager;
    MenuItem add_more_menu_item;
    String authToken = null;
    UserCurrentLocationManager locationManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_family_information,
                container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Family Profile Fragment");
    }

    public FamilyProfileFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        session = new JeevomSession(getActivity());
        globalAlert = new GlobalAlert(getActivity());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        locationManager = new UserCurrentLocationManager(getActivity().getApplicationContext());
        btn_user_save = (Button) rootView.findViewById(R.id.btn_user_save);
        btn_user_save_skip = (Button) rootView
                .findViewById(R.id.btn_user_save_skip);
        btn_user_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2, true);

            }
        });
        btn_user_save_skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();

            }
        });
        members = (LinearLayout) rootView.findViewById(R.id.members);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            getAllMembers();
        }
    }

    private void getAllMembers() {
        RestAdapter getFamilyMemberAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("family_members"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        FamilyMemberGetService getMemberService = getFamilyMemberAdapter
                .create(FamilyMemberGetService.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getActivity().getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        getMemberService.getFamilyMembers(
                locationManager.getUserLocation(), authToken,
                String.valueOf(session.getMemberId()),
                new Callback<FamilyMembersResponse>() {

                    @Override
                    public void success(FamilyMembersResponse arg0,
                                        Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        memberAssociation = arg0.getData()
                                .getMemberAssociation();
                        addToLayout();
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

    protected void addToLayout() {
        if (members.getChildCount() > 0) {
            members.removeAllViews();
        }

        if (memberAssociation.size() > 0) {
            for (MemberAssociation object : memberAssociation) {
                LayoutInflater inflator = (LayoutInflater) getActivity()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View view = inflator.inflate(R.layout.family_member_row, null);
                if (members.getChildCount() % 2 == 0) {
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#efefef"));
                }
                TextView name = (TextView) view.findViewById(R.id.name);
                TextView relation = (TextView) view.findViewById(R.id.associationName);
                name.setText(object.getFirstName() + " " + object.getLastName());
                relation.setText(object.getAssociateNameString());
                final ImageView edit = (ImageView) view.findViewById(R.id.edit);
                edit.setTag(object);

                edit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        MemberAssociation tag = (MemberAssociation) edit
                                .getTag();
                        Intent addMoreIntent = new Intent(getActivity(),
                                FamilyInformation.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("member", tag);
                        addMoreIntent.putExtras(bundle);
                        startActivityForResult(addMoreIntent, 1);

                    }
                });
                members.addView(view);

            }
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // TODO Auto-generated method stub
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            getAllMembers();
            getActivity().invalidateOptionsMenu();
        }
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        add_more_menu_item = menu.add(" Add Family Member");

        add_more_menu_item.setIcon(R.drawable.family_add);
        add_more_menu_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
                | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        add_more_menu_item
                .setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent addMoreIntent = new Intent(getActivity(),
                                FamilyInformation.class);
                        startActivityForResult(addMoreIntent, 1);
                        return true;
                    }
                });
    }

}
