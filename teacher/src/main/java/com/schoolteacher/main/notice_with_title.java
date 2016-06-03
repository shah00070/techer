package com.schoolteacher.main;

/*
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
import com.school.R;
import com.school.mylibrary.dialog.GlobalAlert;
import com.school.mylibrary.dialog.ProgressDialogFragment;
import com.school.mylibrary.pojo.MembershipAuthenticateResponse;
import com.school.mylibrary.session.JeevomSession;
import com.school.mylibrary.util.CommonCode;
import com.school.mylibrary.util.Connectivity;
import com.school.mylibrary.util.JeevOMUtil;
import com.school.mylibrary.util.MyUrlConnectionClient;

import java.net.SocketTimeoutException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
*/

/**
 * Created by chandan on 11/5/16.
 */
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.schoolteacher.R;

public class notice_with_title extends BaseClass   {
    /*EditText mobile,email;
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

    String firstname1,lastname1,email1,textt1,phone1;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitefndreq);
    /*    Intent intent = getIntent();
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


        globalAlert = new GlobalAlert(notice_with_title.this);


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
              //  callemail(firstname1,lastname1,email1,phone1,textt1,username,"",InvitingMemberId,1);
            }
        });*/

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
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }



}
