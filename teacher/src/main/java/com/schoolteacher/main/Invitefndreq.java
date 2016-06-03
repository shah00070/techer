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
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
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

public class Invitefndreq extends BaseClass   {
    EditText mobile,email;
    Gson gson;   Toolbar toolbar;
    public boolean invoke=false;
    public ProgressDialogFragment progressBarFragment;
    // public boolean emailfound=false;
    Button invite; JeevomSession session;
    private GlobalAlert globalAlert;
    String authToken = null;
    public  int tag1=0,tag11=0;

    Button e;
    EditText firstname,lastname,emaill,phone,textt;

    String firstname1,lastname1,email1,textt1,phone1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitefndreq);
        Intent intent = getIntent();
        String emaillast = intent.getExtras().getString("email");
        String mobilelast = intent.getExtras().getString("mobile");

        session = new JeevomSession(getApplicationContext());
        //  System.out.print(session.getAuthToken());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar_view_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Invite Friend");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        globalAlert = new GlobalAlert(Invitefndreq.this);


        firstname=(EditText)findViewById(R.id.fnamee);
        lastname=(EditText)findViewById(R.id.lnamee);
        emaill=(EditText)findViewById(R.id.Emailaddresss);
        phone=(EditText)findViewById(R.id.mobilee);
        textt=(EditText)findViewById(R.id.custommsg);


phone.setText(mobilelast);

        emaill.setText(emaillast);

        e=(Button)findViewById(R.id.butto1);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstname1=firstname.getText().toString();
                lastname1=lastname.getText().toString();
                email1=emaill.getText().toString();
                //  int n = Integer.parseInt(x.toString());
                phone1=phone.getText().toString();
                textt1=textt.getText().toString();
                int InvitingMemberId=session.getMemberId();//"210879";//;MemberId();
                int SourceId=1;
                String username=session.getName();
                callemail(firstname1,lastname1,email1,phone1,textt1,username,"",InvitingMemberId,1);
            }
        });

    }



    public void callemail(String FirstName,String LastName,String Email,String CellNumber,String InvitationText,String NameOfInvitingMember,String NameOfInviteeMember,int InvitingMemberId,int SourceId){
 RestAdapter memberShipAuthenticRestAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("authentic"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        finalInvite memberShipAuthentic = memberShipAuthenticRestAdapter
                .create(finalInvite.class);
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);

        finalInviterequestle membershipAuthenticateRequest = new finalInviterequestle();
        membershipAuthenticateRequest.setFirstName(FirstName);//setEmailOrPhone(user_email_phone_value
        membershipAuthenticateRequest.setLastName(LastName);
        membershipAuthenticateRequest.setEmail(Email);
        membershipAuthenticateRequest.setCellNumber(CellNumber);
        membershipAuthenticateRequest.setInvitationText(InvitationText);
        membershipAuthenticateRequest.setNameOfInvitingMember(NameOfInvitingMember);
        membershipAuthenticateRequest.setNameOfInviteeMember(NameOfInviteeMember);
        membershipAuthenticateRequest.setInvitingMemberId(InvitingMemberId);
        membershipAuthenticateRequest.setSourceId(SourceId);

        memberShipAuthentic.onSuccessFullMemberSignIn(
                membershipAuthenticateRequest, authToken,
                new Callback<MembershipAuthenticateResponse>() {


                    @Override
                    public void success(MembershipAuthenticateResponse data,
                                        Response arg1) {

                        progressBarFragment.dismissAllowingStateLoss();

                        if (data.getData().getIsMemberinvitedSuccessfully().equals("true")) {
                            Toast.makeText(Invitefndreq.this, "Invite Friend Sent"
                                    , Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressBarFragment.dismissAllowingStateLoss();
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(Invitefndreq.this))) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION);
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                            } else if (arg0.getResponse() == null) {
                                showAlert(JeevOMUtil.SOMETHING_WRONG);
                            }
                        } else if (arg0.getResponse().getStatus() > 400) {
                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                        }else{
                            showAlert("Please Enter Correct Email Id");

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
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
          overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }



}
