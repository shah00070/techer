package com.schoolteacher.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.main.PaymentActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.BeginTransactionRequest;
import com.schoolteacher.pojos.GetPackageResponse;
import com.schoolteacher.service.BeginTransactionInterface;

import java.net.SocketTimeoutException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class TransactionDialog extends DialogFragment implements
        OnClickListener {
    GlobalAlert globalAlert;
    private EditText user_email_value, user_phone_value;
    TextView pkg_detail, transaction_detail;
    Button btn_proceed, btn_cancel;
    BeginTransactionRequest beginTransactionObject;
    private ProgressDialogFragment newFragment;
    JeevomSession jeevomSession;
    String packageName;
    private String txnId;
    UserCurrentLocationManager locationManager;

    public static TransactionDialog newInstance(
            BeginTransactionRequest beginTransaction, String packageName) {
        TransactionDialog transactionDialog = new TransactionDialog();

        Bundle args = new Bundle();
        args.putSerializable("beginTransactionObject", beginTransaction);
        args.putString("package", packageName);
        transactionDialog.setArguments(args);
        return transactionDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        beginTransactionObject = (BeginTransactionRequest) getArguments()
                .getSerializable("beginTransactionObject");
        packageName = getArguments().getString("package");
        jeevomSession = new JeevomSession(getActivity().getApplicationContext());
        View mCustomView = inflater.inflate(R.layout.transaction_detail_layout,
                null);
        globalAlert = new GlobalAlert(getActivity());

        locationManager = new UserCurrentLocationManager(getActivity().getApplicationContext());
        user_email_value = (EditText) mCustomView
                .findViewById(R.id.user_email_value);
        user_phone_value = (EditText) mCustomView
                .findViewById(R.id.user_phone_value);
        pkg_detail = (TextView) mCustomView.findViewById(R.id.pkg_detail);
        transaction_detail = (TextView) mCustomView
                .findViewById(R.id.transaction_detail);
        btn_proceed = (Button) mCustomView.findViewById(R.id.btn_proceed);
        btn_cancel = (Button) mCustomView.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        btn_proceed.setOnClickListener(this);

        if (!CommonCode.isNullOrEmpty(jeevomSession.getEmail())) {
            user_email_value.setText(jeevomSession.getEmail());
        }

        if (!CommonCode.isNullOrEmpty(jeevomSession.getCellNumber())) {
            user_phone_value.setText(jeevomSession.getCellNumber());
        }

        pkg_detail.setText("You have selected "
                + Html.fromHtml("<b>" + packageName + "</b>"));
        transaction_detail.setText("Your total transaction amount is INR "
                + Html.fromHtml("<b>"
                + beginTransactionObject.getTransactionAmount()
                + "</b>"));
        return mCustomView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_proceed:

                if (CommonCode.isNullOrEmpty(user_email_value.getText().toString())) {
                    showAlert("Please provide email id");
                } else {
                    makeRequestForBeginTransaction(beginTransactionObject);
                }
                break;

            case R.id.btn_cancel:
                dismiss();
                break;
        }

    }

    protected void makeRequestForBeginTransaction(
            final BeginTransactionRequest beginTransactionObject) {

        txnId = beginTransactionObject.getExternalTransactionId();
        RestAdapter beginTransactionAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("beginTransaction"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        BeginTransactionInterface beginTransaction = beginTransactionAdapter
                .create(BeginTransactionInterface.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getActivity().getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        beginTransaction.beginTransaction(locationManager.getUserLocation(), beginTransactionObject,
                new Callback<ApiResponse<Integer>>() {

                    @Override
                    public void success(ApiResponse<Integer> arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            //
                            // callPaymentActivity(Integer.valueOf(txnId),
                            // packageName);

                            callPaymentActivity(packageName);
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
                        } else if (arg0.getResponse().getStatus() > 400) {
                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                        } else {

                            try {
                                String json = new String(((TypedByteArray) arg0
                                        .getResponse().getBody()).getBytes());
                                Gson gson = new GsonBuilder()
                                        .setPrettyPrinting().create();
                                GetPackageResponse getPackageObject = gson
                                        .fromJson(json,
                                                GetPackageResponse.class);
                                String code = getPackageObject.getStatus()
                                        .getCode();
                                String message = getPackageObject.getStatus()
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
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
    }

    // protected void callPaymentActivity(int transactionId, String packageName)
    // {
    protected void callPaymentActivity(String packageName) {

        Intent paymentIntent = new Intent(getActivity(), PaymentActivity.class);
        paymentIntent.putExtra("name", beginTransactionObject.getFirstName());
        paymentIntent.putExtra("email", user_email_value.getText().toString());
        paymentIntent.putExtra("txnid", txnId);
        paymentIntent.putExtra("amount",
                beginTransactionObject.getTransactionAmount());
        paymentIntent.putExtra("phone", beginTransactionObject.getCellNumber());
        paymentIntent.putExtra("package_info", packageName);
        getActivity().startActivityForResult(paymentIntent, 1);
        dismiss();
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

}