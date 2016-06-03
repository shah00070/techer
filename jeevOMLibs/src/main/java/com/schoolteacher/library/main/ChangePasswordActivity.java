package com.schoolteacher.library.main;

import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.pojo.ChangePasswordAuthenticateRequest;
import com.schoolteacher.mylibrary.pojo.ChangePasswordAuthenticateResponse;
import com.schoolteacher.mylibrary.service.interfaces.ChangePasswordAuthenticate;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class ChangePasswordActivity extends ActionBarActivity implements
        OnClickListener, OnFocusChangeListener {

    private EditText currentPassword;
    private EditText newPassword;
    private EditText confirmNewPassword;
    private CheckBox check_show_password;

    private JeevomSession session;
    DialogFragment newFragment;
    GlobalAlert globalAlert;
    private GlobelAlertWithFinish globalAlertFinish;
    Toolbar toolbar;
    String authToken;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.change_password);

        // hide the Keyboard when screen is shown to user first time
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setUpActionBar();

        globalAlert = new GlobalAlert(ChangePasswordActivity.this);
        globalAlertFinish = new GlobelAlertWithFinish(ChangePasswordActivity.this);
        currentPassword = (EditText) findViewById(R.id.editText_current_password);
        newPassword = (EditText) findViewById(R.id.edit_text_new_password);
        confirmNewPassword = (EditText) findViewById(R.id.edit_text_confirm_new_password);
        check_show_password = (CheckBox) findViewById(R.id.check_show_password);

        currentPassword.setOnFocusChangeListener(this);
        newPassword.setOnFocusChangeListener(this);
        confirmNewPassword.setOnFocusChangeListener(this);
        Button changePassword = (Button) findViewById(R.id.btn_change_password);
        changePassword.setOnClickListener(this);
        check_show_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        if (isChecked) {
                            newPassword
                                    .setTransformationMethod(HideReturnsTransformationMethod
                                            .getInstance());
                            confirmNewPassword
                                    .setTransformationMethod(HideReturnsTransformationMethod
                                            .getInstance());
                        } else {
                            confirmNewPassword
                                    .setTransformationMethod(PasswordTransformationMethod
                                            .getInstance());
                            newPassword
                                    .setTransformationMethod(PasswordTransformationMethod
                                            .getInstance());
                        }

                    }
                });
    }

    // Setting up ActionBar
    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        currentPassword.clearFocus();
        newPassword.clearFocus();
        confirmNewPassword.clearFocus();
        check_show_password.setChecked(false);
        if (id == R.id.btn_change_password) {
            if (CommonCode.isNullOrEmpty(currentPassword.getText().toString()
                    .trim())) {
                Crouton.makeText(ChangePasswordActivity.this,
                        JeevOMUtil.EMPTY_CURRENT_PASSWORD, Style.ALERT).show();
            } else if (CommonCode.isNullOrEmpty(newPassword.getText()
                    .toString().trim())) {
                Crouton.makeText(ChangePasswordActivity.this,
                        JeevOMUtil.EMPTY_NEW_PASSWORD, Style.ALERT).show();
            } else if (CommonCode.isNullOrEmpty(confirmNewPassword.getText()
                    .toString().trim())) {
                Crouton.makeText(ChangePasswordActivity.this,
                        JeevOMUtil.EMPTY_CONFIRM_PASSWORD, Style.ALERT).show();
            } else if (!(confirmNewPassword.getText().toString().trim()
                    .equals(newPassword.getText().toString().trim()))) {
                Crouton.makeText(ChangePasswordActivity.this,
                        JeevOMUtil.PASSWORD_MATCH, Style.ALERT).show();
            } else if (!(CommonCode.validatePassword(newPassword.getText()
                    .toString().trim()))) {
                Crouton.makeText(ChangePasswordActivity.this,
                        JeevOMUtil.PASSWORD_VALID_MSG, Style.ALERT).show();
            } else if (!(CommonCode.validatePassword(confirmNewPassword
                    .getText().toString().trim()))) {
                Crouton.makeText(ChangePasswordActivity.this,
                        JeevOMUtil.PASSWORD_VALID_MSG, Style.ALERT).show();
            } else if (currentPassword.getText().toString().trim()
                    .equals(newPassword.getText().toString().trim())) {
                Crouton.makeText(ChangePasswordActivity.this,
                        JeevOMUtil.OLD_NEW_PASSWORD_SAME, Style.ALERT).show();
            } else {
                postCallForChangePasswordAuthenticate();
            }
        }

    }

    private void postCallForChangePasswordAuthenticate() {
        RestAdapter changePasswordAuthenticateRA = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).setLogLevel(LogLevel.FULL)
                .build();
        ChangePasswordAuthenticate changePasswordAuthenticate = changePasswordAuthenticateRA
                .create(ChangePasswordAuthenticate.class);
        session = new JeevomSession(getApplicationContext());

        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String currentDate = sdf.format(new Date());

        SimpleDateFormat sdfrmt = new SimpleDateFormat("hh:mm a");
        String currentTime = sdfrmt.format(new Date());

        int userID = session.getMemberId();
        ChangePasswordAuthenticateRequest changePasswordAuthenticateRequest = new ChangePasswordAuthenticateRequest();
        changePasswordAuthenticateRequest.setUserId(userID);
        changePasswordAuthenticateRequest.setChangeDate(currentDate);
        changePasswordAuthenticateRequest.setChangeTime(currentTime);
        changePasswordAuthenticateRequest.setOldPassword(currentPassword
                .getText().toString().trim());
        changePasswordAuthenticateRequest.setNewPassword(newPassword.getText()
                .toString().trim());
        changePasswordAuthenticate.onSuccessFullChangePassword(authToken,
                changePasswordAuthenticateRequest,
                new Callback<ChangePasswordAuthenticateResponse>() {

                    @Override
                    public void success(
                            ChangePasswordAuthenticateResponse data,
                            Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = data.getStatus().getCode();

                        if (code.equals("Success")) {

                            currentPassword.setText("");
                            newPassword.setText("");
                            confirmNewPassword.setText("");
                            showAlertFinish(JeevOMUtil.PASSWORD_CHANGED_SUCCESSFULLY_MESSAGE);
                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(ChangePasswordActivity.this))) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION);
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                            }
                        } else if (arg0.getResponse().getStatus() > 400) {
                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                        } else {
                            String json = new String(((TypedByteArray) arg0
                                    .getResponse().getBody()).getBytes());
                            Gson gson = new GsonBuilder().setPrettyPrinting()
                                    .create();
                            ChangePasswordAuthenticateResponse changePasswordAuthenticateErrors = gson
                                    .fromJson(
                                            json,
                                            ChangePasswordAuthenticateResponse.class);
                            String code = changePasswordAuthenticateErrors
                                    .getStatus().getCode();
                            String message = changePasswordAuthenticateErrors
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
                            } else if (code.equals("BE-1003")) {
                                showAlert(message);
                            } else if (code.equals("BE-1004")) {
                                showAlert(message);
                            }
                        }
                    }
                });

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        int id = view.getId();
        if (id == R.id.editText_current_password) {
            if (!hasFocus) {
                if (CommonCode.isNullOrEmpty(currentPassword.getText()
                        .toString().trim())) {
                    currentPassword.setHintTextColor(getResources().getColor(
                            R.color.warning));
                }

            }
        } else if (id == R.id.edit_text_new_password) {
            if (!hasFocus) {
                if (CommonCode.isNullOrEmpty(newPassword.getText().toString()
                        .trim())) {
                    newPassword.setHintTextColor(getResources().getColor(
                            R.color.warning));
                }
                if (!(CommonCode.validatePassword(newPassword.getText()
                        .toString().trim()))) {

                    newPassword.setHintTextColor(getResources().getColor(
                            R.color.warning));
                }
            }
        } else if (id == R.id.edit_text_confirm_new_password) {
            if (!hasFocus) {
                if (CommonCode.isNullOrEmpty(confirmNewPassword.getText()
                        .toString().trim())) {
                    confirmNewPassword.setHintTextColor(getResources()
                            .getColor(R.color.warning));
                }
                if (!(CommonCode.validatePassword(confirmNewPassword.getText()
                        .toString().trim()))) {
                    confirmNewPassword.setHintTextColor(getResources()
                            .getColor(R.color.warning));
                }
            }
        }
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    private void showAlertFinish(String message) {
        globalAlertFinish.show();
        globalAlertFinish.setMessage(message);
    }
}
