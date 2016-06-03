package com.schoolteacher.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;

import java.net.SocketTimeoutException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class Invitefriend extends BaseClass   {
    EditText mobile,email;
    Gson gson;   Toolbar toolbar;
    public boolean invoke=false;
    // public boolean emailfound=false;
    Button invite; JeevomSession session;
    String authToken = null;
    public  int tag1=0,tag11=0;
    public EditText e;
    public ProgressDialogFragment progressBarFragment;
    public boolean finalmobile=false,finalemail=false;
    private GlobalAlert globalAlert;
    boolean x; String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitefnd);

        session = new JeevomSession(getApplicationContext());
        //  System.out.print(session.getAuthToken());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        globalAlert = new GlobalAlert(Invitefriend.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_view_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Invite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






        email=(EditText)findViewById(R.id.mail);
        mobile=(EditText)findViewById(R.id.mobile);




       /* e=(EditText)findViewById(R.id.header);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Invitefriend.this, HomeActivity.class);
                startActivity(myIntent);
                overridePendingTransition(com.jeevom.mylibrary.R.anim.trans_left_in, com.jeevom.mylibrary.R.anim.trans_left_exit);
                finish();
            }
        });*/


        invite=(Button)findViewById(R.id.invite);
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                if(email.getText().toString().isEmpty() &&  !mobile.getText().toString().isEmpty()){
//
//                    showAlert("Please Enter Email Id Or Phone Nummber");
//                }
//else if(email.getText().toString().isEmpty() &&  !mobile.getText().toString().isEmpty()){
//    both(email.getText().toString(), mobile.getText().toString());
//}
//                else if(email.getText().toString().isEmpty() &&  !mobile.getText().toString().isEmpty()){
//    both(email.getText().toString(), mobile.getText().toString());
//}
//                else if(email.getText().toString().isEmpty() &&  !mobile.getText().toString().isEmpty()){
//
//    both(email.getText().toString(), mobile.getText().toString());
//}

               if(email.getText().toString().isEmpty() && mobile.getText().toString().isEmpty()){
showAlert("Please Enter Email Id Or Phone Nummber");
               }
               else if (email.getText().length() != 0 && mobile.getText().toString().isEmpty()) {

                   both(email.getText().toString(), mobile.getText().toString());

                } else if (mobile.getText().length() != 0 && email.getText().toString().isEmpty()) {

                   both(email.getText().toString(), mobile.getText().toString());

                } else {
                   both(email.getText().toString(), mobile.getText().toString());

                }
            }
        });

    }

    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }


    public void both(String email,String mobile){


        RestAdapter saveSearchAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("emailverify"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);
        Invite doctor_profile_interface = saveSearchAdapter
                .create(Invite.class);
        doctor_profile_interface.invitefnd(email, mobile, new Callback<Inviterequest>() {

          /*  @Override
            public void success(ApiResponse<Inviterequest> s, Response response) {
                progressBarFragment.dismissAllowingStateLoss();

                try {

                    if (s.getStatus().getCode().toString().equals("True")) {
                        Toast.makeText(Invitefriend.this, "User Already exist", Toast.LENGTH_SHORT).show();
                    } else if (s.getStatus().getCode().toString().equals("False")) {
                        Toast.makeText(Invitefriend.this, "not exist", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(Invitefriend.this, "", Toast.LENGTH_SHORT).show();


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }*/

            @Override
            public void success(Inviterequest s, Response response) {
                progressBarFragment.dismissAllowingStateLoss();
                try {

                    if (s.getStatus().getCode().toString().equals("True")) {

                        Toast.makeText(Invitefriend.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    } else if (s.getStatus().getCode().toString().equals("False")) {
                        //       Toast.makeText(Invitefriend.this, "yess", Toast.LENGTH_SHORT).show();

                        callmobile1();
                    }

                    //     Toast.makeText(Invitefriend.this, "", Toast.LENGTH_SHORT).show();


                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

            @Override
            public void failure(RetrofitError arg0) {
                //  Toast.makeText(Invitefriend.this, "failure Email", Toast.LENGTH_SHORT).show();
                progressBarFragment.dismissAllowingStateLoss();

                if (arg0.isNetworkError()) {
                    if (!(Connectivity
                            .checkConnectivity(Invitefriend.this))) {
                        showAlert(JeevOMUtil.INTERNET_CONNECTION);
                    } else if (arg0.getCause() instanceof SocketTimeoutException) {
                        showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                    } else if (arg0.getResponse() == null) {
                        showAlert(JeevOMUtil.SOMETHING_WRONG);
                    }
                } else if (arg0.getResponse().getStatus() > 400) {
                    showAlert(JeevOMUtil.SOMETHING_WRONG);
                } else {
                    showAlert("Please Enter Correct Email Id");

                }


            }
        });


    }
/*

    public void callemail(String name,final int tag1){


        RestAdapter saveSearchAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("emailverify"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);
  Invite doctor_profile_interface = saveSearchAdapter
                .create(Invite.class);
        doctor_profile_interface.saveSearch(name, authToken, new Callback<Inviterequest>() {
            @Override
            public void success(Inviterequest s, Response response) {
                progressBarFragment.dismissAllowingStateLoss();

                if (tag1 == 0) {

                    try {
                        if (s.getData().equals("true")) {
                            Toast.makeText(Invitefriend.this, "User Email id Already Exist", Toast.LENGTH_LONG).show();

                        } else if (s.getData().equals("false")) {
                            //     Toast.makeText(Invitefriend.this, "Email Invite Friend Send", Toast.LENGTH_LONG).show();
                            callemail1();
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if (tag1 == 1) {

                    try {
                        if (s.getData().equals("true")) {
                            //   Toast.makeText(Invitefriend.this, "inner Email id Already Exist", Toast.LENGTH_LONG).show();
                            callmethod();
                            //    finalemail=true;
                            //   invoke=true;
                        } else if (s.getData().equals("false")) {
                            //       Toast.makeText(Invitefriend.this, "Email Friend Send", Toast.LENGTH_LONG).show();
                            //   finalemail=false;
                            callemail1();
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            }

            @Override
            public void failure(RetrofitError arg0) {
                //  Toast.makeText(Invitefriend.this, "failure Email", Toast.LENGTH_SHORT).show();
                progressBarFragment.dismissAllowingStateLoss();

                if (arg0.isNetworkError()) {
                    if (!(Connectivity
                            .checkConnectivity(Invitefriend.this))) {
                        showAlert(JeevOMUtil.INTERNET_CONNECTION);
                    } else if (arg0.getCause() instanceof SocketTimeoutException) {
                        showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                    } else if (arg0.getResponse() == null) {
                        showAlert(JeevOMUtil.SOMETHING_WRONG);
                    }
                } else if (arg0.getResponse().getStatus() > 400) {
                    showAlert(JeevOMUtil.SOMETHING_WRONG);
                } else {
                    showAlert("Please Enter Correct Email Id");

                }


            }
        });


    }

    public void callmobile(String name,final int tag11){


        RestAdapter saveSearchAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("mobile verify"))
                .setEndpoint(JeevOMUtil.baseUrl).build();

        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);

        InviteBymobile doctor_profile_interface = saveSearchAdapter
                .create(InviteBymobile.class);
        doctor_profile_interface.saveSearch(name, authToken, new Callback<InviterequestBymobile>() {
            @Override
            public void success(InviterequestBymobile s, Response response) {
                progressBarFragment.dismissAllowingStateLoss();

                if (tag11 == 0) {

                    try {

                        if (s.getData().equals("true")) {
                            Toast.makeText(Invitefriend.this, "User Mobile  Already Exist", Toast.LENGTH_LONG).show();
                        } else if (s.getData().equals("false")) {
                            //     Toast.makeText(Invitefriend.this, "Mobile Invite Friend Send", Toast.LENGTH_LONG).show();
                            callmobile1();
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else if (tag11 == 1) {
                    try {

                        if (s.getData().equals("true")) {
                            Toast.makeText(Invitefriend.this, "Mobile  Already Exist", Toast.LENGTH_LONG).show();
                            //    finalmobile = true;
                        } else if (s.getData().equals("false")) {
                            //  Toast.makeText(Invitefriend.this, "Mobile Invite Friend Send", Toast.LENGTH_LONG).show();
                            callmobile1();
                            //        finalmobile = false;
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                finalmobile = false;
            }

            @Override
            public void failure(RetrofitError arg0) {
                //    Toast.makeText(Invitefriend.this, "failure Mobile", Toast.LENGTH_SHORT).show();
                progressBarFragment.dismissAllowingStateLoss();

                if (arg0.isNetworkError()) {
                    if (!(Connectivity
                            .checkConnectivity(Invitefriend.this))) {
                        showAlert(JeevOMUtil.INTERNET_CONNECTION);
                    } else if (arg0.getCause() instanceof SocketTimeoutException) {
                        showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                    } else if (arg0.getResponse() == null) {
                        showAlert(JeevOMUtil.SOMETHING_WRONG);
                    }
                } else if (arg0.getResponse().getStatus() > 400) {
                    showAlert(JeevOMUtil.SOMETHING_WRONG);
                } else {
                    showAlert("Please Enter Correct Email Id");

                } }

        });


    }


public void callmethod() {

    callmobile(mobile.getText().toString(), 1);


}
*/

    void callmobile1(){
        Intent myIntent = new Intent(Invitefriend.this, Invitefndreq.class);
        myIntent.putExtra("mobile", mobile.getText().toString());
        myIntent.putExtra("email",email.getText().toString());
        startActivity(myIntent);
        overridePendingTransition(com.schoolteacher.mylibrary.R.anim.trans_left_in, com.schoolteacher.mylibrary.R.anim.trans_left_exit);

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



}