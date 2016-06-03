package com.schoolteacher.library.main;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.interfaces.AsyncTaskListner;
import com.schoolteacher.mylibrary.interfaces.SendEmailInterface;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.model.EmailAndTokenTypeDictionary;
import com.schoolteacher.mylibrary.model.Token;
import com.schoolteacher.mylibrary.service.PostAsyncTask;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class ForgotPassword extends ActionBarActivity implements
        AsyncTaskListner, SendEmailInterface {
    private DialogFragment newFragment;
    EditText email_phone, code_field, newPassword, confirmNewPassword;
    TextView message_text;
    Button send, btn_change_password;
    RelativeLayout first_step_layout, second_step_layout;
    boolean isEmailEntered = false;
    private CheckBox check_show_password;
    String url = null, emailOrPhoneValue;
    LinearLayout back_button_layout;
    GlobalAlert globalAlert;
    private GlobelAlertWithFinish globalAlertFinish;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Forgot Password");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        globalAlert = new GlobalAlert(ForgotPassword.this);
        globalAlertFinish = new GlobelAlertWithFinish(ForgotPassword.this);

        back_button_layout = (LinearLayout) findViewById(R.id.back_btn_layout);
        code_field = (EditText) findViewById(R.id.code_field);
        newPassword = (EditText) findViewById(R.id.edit_text_new_password);
        confirmNewPassword = (EditText) findViewById(R.id.edit_text_confirm_new_password);
        check_show_password = (CheckBox) findViewById(R.id.check_show_password);

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

        btn_change_password = (Button) findViewById(R.id.btn_change_password);
        btn_change_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                check_show_password.setChecked(false);
                if (v.getId() == R.id.btn_change_password) {
                    String smsCode = code_field.getText().toString();
                    String password = newPassword.getText().toString();
                    String confirmPassword = confirmNewPassword.getText()
                            .toString();
                    if (CommonCode.isNullOrEmpty(smsCode)) {
                        Crouton.makeText(ForgotPassword.this,
                                JeevOMUtil.EMPTY_CODE, Style.ALERT).show();
                    } // Passwords Validation
                    else if (CommonCode.isNullOrEmpty(password)) {
                        Crouton.makeText(ForgotPassword.this,
                                JeevOMUtil.EMPTY_PASSWORD, Style.ALERT).show();
                    } else if (!(CommonCode.validatePassword(password))) {
                        Crouton.makeText(ForgotPassword.this,
                                JeevOMUtil.PASSWORD_VALID_MSG, Style.ALERT)
                                .show();
                    } else if (CommonCode.isNullOrEmpty(confirmPassword)) {
                        Crouton.makeText(ForgotPassword.this,
                                JeevOMUtil.EMPTY_CONFIRM_PASSWORD, Style.ALERT)
                                .show();
                    } else if (!(confirmPassword.equals(password))) {
                        Crouton.makeText(ForgotPassword.this,
                                JeevOMUtil.PASSWORD_MATCH, Style.ALERT).show();
                    } else if (!(CommonCode.validatePassword(confirmPassword))) {
                        Crouton.makeText(ForgotPassword.this,
                                JeevOMUtil.PASSWORD_VALID_MSG, Style.ALERT)
                                .show();
                    } else {

                        url = JeevOMUtil.baseUrl + JeevOMUtil.resetPassword;
                        JSONObject jsonObject = new JSONObject();

                        try {

                            if (isValidEmail(emailOrPhoneValue)) {
                                jsonObject.accumulate("Email", emailOrPhoneValue);
                            } else {
                                jsonObject.accumulate("CellNumber", emailOrPhoneValue);
                            }
                            jsonObject.accumulate("NewPassword", password);
                            if (isEmailEntered) {
                                jsonObject.accumulate("TokenType",
                                        JeevOMUtil.TOKEN_PASSWORD_CHANGE_EMAIL);
                            } else {
                                jsonObject.accumulate("TokenType",
                                        JeevOMUtil.TOKEN_PASSWORD_CHANGE_PHONE);
                            }
                            jsonObject.accumulate("ResetPasswordTokenCode",
                                    code_field.getText().toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        PostAsyncTask forgetPasswordValidity = new PostAsyncTask(
                                ForgotPassword.this, "update_password",
                                jsonObject.toString());
                        forgetPasswordValidity
                                .getActivityReference(ForgotPassword.this);
                        forgetPasswordValidity.execute(url);
                        newFragment = ProgressDialogFragment.newInstance();
                        newFragment.show(getSupportFragmentManager(), "dialog");
                        newFragment.setCancelable(false);
                    }
                }
            }
        });

        first_step_layout = (RelativeLayout) findViewById(R.id.first_step_layout);
        second_step_layout = (RelativeLayout) findViewById(R.id.second_step_layout);

        message_text = (TextView) findViewById(R.id.message_text);

        // set forget password message
        if (first_step_layout.getVisibility() == View.VISIBLE) {
            message_text.setText(JeevOMUtil.FORGET_PASSWORD_MESSAGE);
        }

        email_phone = (EditText) findViewById(R.id.email_phone);
        String preEmailOrPhone = getIntent().getStringExtra("email_or_phone");
        if (!CommonCode.isNullOrEmpty(preEmailOrPhone)) {
            email_phone.setText(preEmailOrPhone);
        }

        send = (Button) findViewById(R.id.reset_button);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.reset_button) {
                    emailOrPhoneValue = email_phone.getText().toString();
                    EmailAndTokenTypeDictionary emailAndTokenTypeDictionary = new EmailAndTokenTypeDictionary();
                    boolean isValid = false;
                    if (emailOrPhoneValue.length() > 0) {
                        try {
                            // conversion throws exception is parsing fails from
                            // string
                            // to long
                            // if exception , means user trying to enter email
                            // else phone
                            long number = Long.parseLong(emailOrPhoneValue);
                            if (CommonCode.validatePhone(emailOrPhoneValue)) {
                                isEmailEntered = false;
                                url = JeevOMUtil.baseUrl
                                        + JeevOMUtil.ForgetPhonePassword;
                                emailAndTokenTypeDictionary
                                        .setCellNumber(emailOrPhoneValue);
                                isValid = true;
                            } else {
                                Crouton.makeText(ForgotPassword.this,
                                        JeevOMUtil.VALID_Student_Id, Style.ALERT)
                                        .show();
                            }
                        } catch (NumberFormatException e) {
                            if (CommonCode.validateEmail(emailOrPhoneValue)) {
                                isEmailEntered = true;
                                url = JeevOMUtil.baseUrl
                                        + JeevOMUtil.ForgetPassword;
                                emailAndTokenTypeDictionary
                                        .setEmail(emailOrPhoneValue);
                                emailAndTokenTypeDictionary
                                        .setTokenType(JeevOMUtil.TOKEN_FORGET_PASSWORD);
                                isValid = true;
                            } else {
                                Crouton.makeText(
                                        ForgotPassword.this,
                                        "Please enter a valid phone number or email id",
                                        Style.ALERT).show();
                            }
                        }

                        if (isValid) {
                            ArrayList<EmailAndTokenTypeDictionary> emailAndTokenType = new ArrayList<EmailAndTokenTypeDictionary>();
                            emailAndTokenType.add(emailAndTokenTypeDictionary);
                            Token token = new Token();
                            token.setEmailAndTokenType(emailAndTokenType);
                            Gson gsonObject = new Gson();
                            String object = gsonObject.toJson(token);
                            PostAsyncTask forgetPasswordValidity = new PostAsyncTask(
                                    ForgotPassword.this, "forget_password",
                                    object);
                            forgetPasswordValidity
                                    .getActivityReference(ForgotPassword.this);
                            forgetPasswordValidity.execute(url);
                            newFragment = ProgressDialogFragment.newInstance();
                            newFragment.show(getSupportFragmentManager(),
                                    "dialog");
                            newFragment.setCancelable(false);
                        }
                    } else {
                        Crouton.makeText(ForgotPassword.this,
                                JeevOMUtil.EMPTY_EMAIL_OR_PHONE, Style.ALERT)
                                .show();
                    }

                }
            }

        });

        back_button_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

    @Override
    public void onTaskComplete(String result) {
        newFragment.dismissAllowingStateLoss();

        if (result.equals("No Internet Connectivity")) {
            showAlert(JeevOMUtil.INTERNET_CONNECTION);
        } else if (result.equals("Service Error")) {
            showAlert(JeevOMUtil.SOMETHING_WRONG);
        } else {
            DataContainer data = CommonCode.returnDataContainerObject(result);
            String code = data.getStatus().getCode();
            String message = data.getStatus().getMessage();
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
            } else if (code.equals("Success")) {
                showAlertFinish(JeevOMUtil.PASSWORD_CHANGED_SUCCESSFULLY_MESSAGE);
            }
        }

    }

    private void showAlertFinish(String message) {
        globalAlertFinish.show();
        globalAlertFinish.setMessage(message);
    }

    @Override
    public void onMemberSignUp(String result) {

        newFragment.dismissAllowingStateLoss();

        if (result.equals("No Internet Connectivity")) {
            showAlert(JeevOMUtil.INTERNET_CONNECTION);

        } else if (result.equals("Service Error")) {
            showAlert(JeevOMUtil.SOMETHING_WRONG);
        } else {
            DataContainer data = CommonCode.returnDataContainerObject(result);
            String code = data.getStatus().getCode();
            String message = data.getStatus().getMessage();
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
            } else if (code.equals("Success")) {

                Animation animationFadeOut = AnimationUtils.loadAnimation(
                        ForgotPassword.this, R.anim.fade_out);
                first_step_layout.startAnimation(animationFadeOut);
                first_step_layout.setVisibility(View.GONE);

                message_text.setText(JeevOMUtil.RESET_PASSWORD_MESSAGE);

                Animation animationFadeIn = AnimationUtils.loadAnimation(
                        ForgotPassword.this, R.anim.fade_in);

                second_step_layout.startAnimation(animationFadeIn);
                second_step_layout.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public void onClickContinue(String name, String email, String contact) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onEmailSuccess(String result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClickCancel() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPhoneSucess(String result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFacilitySignUp(String result) {
        // TODO Auto-generated method stub

    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
