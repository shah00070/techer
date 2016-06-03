package com.schoolteacher.library.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.adapter.CustomViewPagerAdapter;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateRequest;
import com.schoolteacher.mylibrary.pojo.MembershipAuthenticateResponse;
import com.schoolteacher.mylibrary.service.interfaces.MemberShipAuthenticate;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;

import java.net.SocketTimeoutException;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.mime.TypedByteArray;


/**
 * Created by Chandan kumar on 26/11/15.
 */
public class IntroScreenActivity extends AppCompatActivity implements CirclePageIndicator.RefreshCalledListener {
    GlobalAlert globalAlert;
    private boolean isPasswordShown = false;
    ProgressDialogFragment progressBarFragment;
    private ViewPager viewPager;
    private CustomViewPagerAdapter adapter;
    private CirclePageIndicator indicator;
    private JeevomSession session;
    private EditText editText_email, editText_password;
    public static  refresh r;
    String email, password;
    ImageView pwd_image_hint;
    int[] mResources = {
            R.drawable.health_mart_intro,
            R.drawable.health_cloud_intro,
            R.drawable.health_social_intro
    };

    int[] iconResources = {
            R.drawable.health_mart_icon,
            R.drawable.health_cloud_icon,
            R.drawable.health_social_icon
    };

    int[] introStringResIds = {
            R.string.health_mart_text,
            R.string.health_cloud_text,
            R.string.health_social_text
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_screen);
        session = new JeevomSession(getApplicationContext());

        initUI();
    }

    public void initUI() {
        pwd_image_hint = (ImageView) findViewById(R.id.pwd_image_hintt);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (CirclePageIndicator) findViewById(R.id.page_indicator);
        indicator.setIsRefreshCalledListener(this);
        adapter = new CustomViewPagerAdapter(this, mResources, iconResources, introStringResIds);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        globalAlert = new GlobalAlert(this);

        editText_email = (EditText) findViewById(R.id.editText_emaill);
        editText_password = (EditText) findViewById(R.id.editText_passwordd);
        pwd_image_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHidePassword();
            }
        });



        TextView guest= (TextView) findViewById(R.id.explere_as_guest);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guestlog();
            }
        });
        LinearLayout loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                email = editText_email.getText().toString();
                password = editText_password.getText().toString();
                boolean isValid = false;


               if(!(email.length() > 0)) {
                   Crouton.makeText(IntroScreenActivity.this,
                           JeevOMUtil.EMPTY_EMAIL_OR_PHONE, Style.ALERT).show();
               }else if (!(password.length() > 0)) {
                       Crouton.makeText(IntroScreenActivity.this,
                               JeevOMUtil.EMPTY_PASSWORD, Style.ALERT).show();


               }else {

                    // call to authenticate user

                    RestAdapter memberShipAuthenticRestAdapter = new RestAdapter.Builder()
                            .setClient(new MyUrlConnectionClient())
                            .setLogLevel(RestAdapter.LogLevel.FULL)
                            .setLog(new AndroidLog("authentic"))
                            .setEndpoint(JeevOMUtil.baseurl).build();
                    MemberShipAuthenticate memberShipAuthentic = memberShipAuthenticRestAdapter
                            .create(MemberShipAuthenticate.class);
                    progressBarFragment = ProgressDialogFragment
                            .newInstance();
                    progressBarFragment.show(getSupportFragmentManager(),
                            "dialog");
                    progressBarFragment.setCancelable(false);


                    memberShipAuthentic.onSuccessFullMemberSignIn(editText_email.getText()
                                    .toString(), editText_password.getText()
                                    .toString()
                            ,
                            new Callback<MembershipAuthenticateResponse>() {

                                @Override
                                public void success(
                                        MembershipAuthenticateResponse data,
                                        retrofit.client.Response arg1) {


                                    String code = data.getStatus()
                                            .getCode();
                                    if (code.equals("Success")) {
                                        progressBarFragment
                                                .dismissAllowingStateLoss();
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "You have successfully logged in",
                                                Toast.LENGTH_SHORT).show();
                                        openHomeScreen();
                                    }

                                }

                                @Override
                                public void failure(RetrofitError arg0) {
                                    progressBarFragment
                                            .dismissAllowingStateLoss();

                                    if (arg0.isNetworkError()) {
                                        if (!(Connectivity
                                                .checkConnectivity(IntroScreenActivity.this))) {
                                            showAlert(JeevOMUtil.INTERNET_CONNECTION);
                                        } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                            showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                                        } else if (arg0.getResponse() == null) {
                                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                                        }
                                    } else if (arg0.getResponse()
                                            .getStatus() > 400) {
                                        showAlert(JeevOMUtil.SOMETHING_WRONG);
                                    } else {
                                        String json = new String(
                                                ((TypedByteArray) arg0
                                                        .getResponse()
                                                        .getBody())
                                                        .getBytes());
                                        Gson gson = new GsonBuilder()
                                                .setPrettyPrinting()
                                                .create();
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

            }

        });


    }
    public void guestlog() {
        try {
            //    Intent myIntent = new Intent(this, Class.forName("com.jeevom.main.MainActivity"));schoolteacher
            Intent myIntent = new Intent(this, Class.forName("com.schoolteacher.main.GuestLogin"));
            startActivity(myIntent);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void showHidePassword() {
        // TODO Auto-generated method stub
        if (isPasswordShown) {
            // password is already shown - hide the password
            editText_password
                    .setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());

            pwd_image_hint.setImageResource(R.drawable.hidepsd);//setCompoundDrawablesWithIntrinsicBounds(0, 0, , 0);
            isPasswordShown = false;
        } else {
            // show the password
            pwd_image_hint.setImageResource(R.drawable.eye_icon);
            editText_password.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
            isPasswordShown = true;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200 && resultCode == RESULT_OK) {
            openHomeScreen();
        }

        if (requestCode == 1001 && resultCode == RESULT_OK) {
            openHomeScreen();
        }
    }

    public void openHomeScreen() {
        try {
            //    Intent myIntent = new Intent(this, Class.forName("com.jeevom.main.MainActivity"));
            Intent myIntent = new Intent(this, Class.forName("com.schoolteacher.main.HomeActivity"));
            startActivity(myIntent);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }
    @Override
    public boolean onRefreshcalled() {
        return false;
    }

}
