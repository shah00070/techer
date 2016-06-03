package com.schoolteacher.wallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.main.PaymentActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.BeginTransactionRequest;
import com.schoolteacher.pojos.GetPackageResponse;
import com.schoolteacher.pojos.PackageDetails;
import com.schoolteacher.pojos.Packages;
import com.schoolteacher.pojos.PkgPromotion;
import com.schoolteacher.pojos.WalletAmountResponse;
import com.schoolteacher.service.GetPackageByName;
import com.schoolteacher.service.Wallet;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class WalletActivity extends ActionBarActivity implements
        OnClickListener {
    UserCurrentLocationManager locationManager;

    Gson gson;


    JeevomSession jeevomSession;
    TextView amount_text, amount_value, recent_transactions;
    Toolbar wallet_toolbar;
    LinearLayout package_container;
    String uuid;
    private DialogFragment newFragment;
    BeginTransactionRequest beginTransaction;
    GlobalAlert globalAlert;
    private GlobelAlertWithFinish globalAlertFinish;
    protected String txnId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet);
        globalAlert = new GlobalAlert(this);
        globalAlertFinish = new GlobelAlertWithFinish(this);
        jeevomSession = new JeevomSession(getApplicationContext());
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        setUpActionBar();
        gson = new GsonBuilder().create();
        getViewElementsReferences();

        getPackages();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserAmount();
    }

    private void getPackages() {

        RestAdapter getPackageAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("wallet_package"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Wallet getWalletPackage = getPackageAdapter
                .create(Wallet.class);

        getWalletPackage.getPackageTypes(
                gson.toJson(locationManager.getUserLocation()).toString(), "2",
                new Callback<ApiResponse<List<Packages>>>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        Toast.makeText(
                                WalletActivity.this,
                                "Something unexpected happened ..Please try later",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void success(ApiResponse<List<Packages>> arg0,
                                        Response arg1) {

                        showPackages(arg0);
                    }
                });

    }

    protected void showPackages(ApiResponse<List<Packages>> arg0) {

        if (arg0 != null) {
            if (arg0.getData() != null) {

                List<Packages> data = arg0.getData();
                for (final Packages object : data) {
                    LayoutInflater inflator = (LayoutInflater) WalletActivity.this
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    // boolean added = false;
                    View view = inflator
                            .inflate(R.layout.packages_layout, null);
                    TextView package_type_name = (TextView) view
                            .findViewById(R.id.package_type_name);
                    TextView pay_text = (TextView) view
                            .findViewById(R.id.pay_text);

                    TextView get_text = (TextView) view
                            .findViewById(R.id.get_text);
                    TextView get_text2 = (TextView) view
                            .findViewById(R.id.get_text2);

                    TextView bottom_text = (TextView) view
                            .findViewById(R.id.bottom_text);

                    TextView offer_text1 = (TextView) view.findViewById(R.id.offer_text1);

                    TextView offer_text2 = (TextView) view.findViewById(R.id.offer_text2);
                    offer_text2.setPaintFlags(bottom_text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                    Button buy_option = (Button) view
                            .findViewById(R.id.buy_option);
                    if (!CommonCode.isNullOrEmpty(object.getPackageName()))
                        package_type_name.setText(object.getPackageName());

                    if (object.getPackagePrice() > 0)
                        pay_text.setText("PAY: INR "
                                + (int) (object.getPackagePrice()) + "/- ");

                    if (object.getPkgPromotion() != null) {
                        List<PkgPromotion> pkgPromotion = object
                                .getPkgPromotion();
                        if (pkgPromotion.size() > 0) {
                            PkgPromotion pkgPromotion2 = pkgPromotion.get(0);


                            offer_text2.setText((int) ((object.getPackagePrice())) + "/- ");






                            bottom_text.setText("You Get INR "
                                    + (int) (pkgPromotion2.getAmount())
                                    + "/- Extra");
                            get_text2.setText((int) ((object.getPackagePrice() + pkgPromotion2
                                              .getAmount())) + "/- ");
                            if (!(((int) pkgPromotion2
                                    .getAmount()) > 0)) {
                                // hide offer lable
                                get_text.setVisibility(View.INVISIBLE);
                            }
                        }
                    } else {
                        get_text.setVisibility(View.INVISIBLE);
                    }

                    buy_option.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            int id = 0;
                            if (object.getPkgPromotion() != null) {
                                List<PkgPromotion> pkgPromotion = object
                                        .getPkgPromotion();
                                if (pkgPromotion.size() > 0) {
                                    PkgPromotion pkgPromotion2 = pkgPromotion
                                            .get(0);

                                    id = pkgPromotion2.getId();

                                }
                            }

                            getPackageByName(object.getPackageName(), id);
                        }
                    });

                    package_container.addView(view);
                }
            }
        }

    }

    // Method to get Package By Name, Called Once Service request for Email
    // Consultation Successfully executes
    private void getPackageByName(final String packageName, final int id) {
        RestAdapter getPackageAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("getPackage"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        GetPackageByName getPackage = getPackageAdapter
                .create(GetPackageByName.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        getPackage.getPackageByName(
                locationManager.getUserLocation(), packageName,
                new Callback<GetPackageResponse>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(WalletActivity.this))) {
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

                    @Override
                    public void success(GetPackageResponse result, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = result.getStatus().getCode();
                        if (code.equals("Success")) {
                            PackageDetails packageDetails = result.getData()
                                    .getPackageDetails();
                            if (packageDetails != null) {

                                Intent paymentIntent = new Intent(WalletActivity.this, PaymentActivity.class);
                                paymentIntent.putExtra("packageName", packageDetails.getPackageName());
                                paymentIntent.putExtra("memberId", jeevomSession.getMemberId());
                                paymentIntent.putExtra("name", jeevomSession.getName());
                                if (id > 0) {
                                    paymentIntent.putExtra("promotionId", id);
                                }
                                if (!CommonCode.isNullOrEmpty(jeevomSession
                                        .getEmail())) {
                                    paymentIntent.putExtra("email", jeevomSession.getEmail());

                                }
                                if (!CommonCode.isNullOrEmpty(jeevomSession
                                        .getCellNumber())) {
                                    paymentIntent.putExtra("cellnumber", jeevomSession.getCellNumber());

                                }
                                startActivity(paymentIntent);

                            }
//                                txnId = UUID.randomUUID().toString();
//                                beginTransaction = new BeginTransactionRequest();
//                                if (id > 0) {
//                                    beginTransaction.setPromotionId(id);
//                                }
//                                beginTransaction
//                                        .setTransactionAmount(packageDetails
//                                                .getPackagePrice() * 1);
//                                beginTransaction
//                                        .setAmountPayable(packageDetails
//                                                .getPackagePrice() * 1);
//                                beginTransaction.setPackageId(packageDetails
//                                        .getId());
//                                beginTransaction.setQuantity(1);
//                                beginTransaction.setPaymentSourceText("PayU");
//                                if (!CommonCode.isNullOrEmpty(jeevomSession
//                                        .getEmail())) {
//                                    beginTransaction.setEmail(jeevomSession
//                                            .getEmail());
//                                }
//                                if (!CommonCode.isNullOrEmpty(jeevomSession
//                                        .getCellNumber())) {
//                                    beginTransaction
//                                            .setCellNumber(jeevomSession
//                                                    .getCellNumber());
//                                }
//
//                                beginTransaction.setFirstName(jeevomSession
//                                        .getName());
//                                beginTransaction.setAssociateOfId(jeevomSession
//                                        .getAssociateOfId());
//
//                                beginTransaction
//                                        .setExternalTransactionId(txnId);
//
//                                beginTransaction.setMemberIPAddress(CommonCode
//                                        .getLocalIpAddress());
//
//                            }
//                            try {
//                                TransactionDialog showDialog = TransactionDialog
//                                        .newInstance(beginTransaction,
//                                                packageName);
//                                showDialog.show(getSupportFragmentManager(),
//                                        "context_dialog_frag");
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }

                        }
                    }
                });
    }

    private void showAlertFinish(String message) {
        globalAlertFinish.show();
        globalAlertFinish.setMessage(message);
    }

    private void getUserAmount() {
        RestAdapter getAmountAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("wallet_amount"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Wallet getWalletAmount = getAmountAdapter
                .create(Wallet.class);
        amount_text.setText("Wait...Getting your Jeevom Wallet amount..");
        amount_value.setVisibility(View.GONE);
        getWalletAmount.getWalletAmount(gson.toJson(locationManager.getUserLocation()).toString(),
                "Basic " + jeevomSession.getAuthToken(),
                jeevomSession.getMemberId(),
                new Callback<ApiResponse<WalletAmountResponse>>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        amount_text
                                .setText("Error Getting Your Wallet Amount.. try again later.");
                    }

                    @Override
                    public void success(ApiResponse<WalletAmountResponse> arg0,
                                        Response arg1) {
                        if (arg0.getData() != null) {
                            amount_text.setText("Your Wallet Balance");
                            amount_value.setVisibility(View.VISIBLE);
                            amount_value.setText(Html.fromHtml("<b>"
                                    + arg0.getData().getCurrency() + " "
                                    + "</b>" + arg0.getData().getBalance()));
                        } else {
                            amount_text.setText("Your Wallet Balance");
                            amount_value.setVisibility(View.VISIBLE);
                            amount_value.setText(Html.fromHtml("<b>" + "INR "
                                    + "</b>" + "0"));
                        }

                    }
                });
    }

    private void getViewElementsReferences() {
        amount_text = (TextView) findViewById(R.id.amount_text);
        amount_value = (TextView) findViewById(R.id.amount_value);
        package_container = (LinearLayout) findViewById(R.id.package_container);
        recent_transactions = (TextView) findViewById(R.id.recent_transactions);
        recent_transactions.setOnClickListener(this);
    }

    private void setUpActionBar() {
        wallet_toolbar = (Toolbar) findViewById(R.id.wallet_toolbar);
        setSupportActionBar(wallet_toolbar);
        getSupportActionBar().setTitle(jeevomSession.getName() + " Wallet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recent_transactions:

                Intent recent_transactions = new Intent(WalletActivity.this,
                        RecentTransactions.class);
                startActivity(recent_transactions);
                break;

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            String statusValue = data.getExtras().getString("payment_status");
            String referenceNo = data.getExtras().getString("reference");
            if (statusValue.equals("") && referenceNo.equals("")) {
                showAlert("Payment has been cancelled.");
            } else {
                if (statusValue.equalsIgnoreCase("true")) {
                    getUserAmount();
                    // showAlertFinish("Recharged successfully.");
                } else {
                    showAlert("Your transaction  was not processes successfully. Please try again.");

                }
            }
        }
    }
}
