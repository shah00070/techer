package com.schoolteacher.wallet;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.RecentTransactionHistory;
import com.schoolteacher.service.Wallet;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class RecentTransactions extends ActionBarActivity {

    LinearLayout recent_transactions_container;
    JeevomSession jeevomSession;
    private ProgressDialogFragment newFragment;
    private Toolbar toolbar_transaction_history;
    UserCurrentLocationManager locationManager;
    Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_transactions);
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        setUpActionBar();
        gson = new GsonBuilder().create();

        jeevomSession = new JeevomSession(getApplicationContext());
        recent_transactions_container = (LinearLayout) findViewById(R.id.recent_transactions_container);
        getRecentTransactions();
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

    private void setUpActionBar() {
        toolbar_transaction_history = (Toolbar) findViewById(R.id.toolbar_transaction_history);
        setSupportActionBar(toolbar_transaction_history);
        getSupportActionBar().setTitle("Recent Transactions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void getRecentTransactions() {

        RestAdapter getTransactionsHistoryAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("trans")).setLogLevel(LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Wallet getWalletPackage = getTransactionsHistoryAdapter
                .create(Wallet.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(this.getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        getWalletPackage.getTransactionHistory(
                gson.toJson(locationManager.getUserLocation()).toString(),
                "Basic " + jeevomSession.getAuthToken(), "0",
                jeevomSession.getMemberId(),
                new Callback<ApiResponse<List<RecentTransactionHistory>>>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();
                        Toast.makeText(RecentTransactions.this,
                                "Something happened Unexpected..",
                                Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void success(
                            ApiResponse<List<RecentTransactionHistory>> arg0,
                            Response arg1) {
                        newFragment.dismissAllowingStateLoss();

                        showTransactions(arg0);

                    }
                });

    }

    protected void showTransactions(
            ApiResponse<List<RecentTransactionHistory>> arg0) {

        List<RecentTransactionHistory> data = arg0.getData();
        if (data.size() > 0) {

            for (RecentTransactionHistory recentTransactionHistory : data) {
                LayoutInflater inflator = (LayoutInflater) RecentTransactions.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // boolean added = false;
                View view = inflator.inflate(R.layout.recent_transaction_view,
                        null);
                TextView transaction_id = (TextView) view
                        .findViewById(R.id.transaction_id);
                TextView dateTxtVw = (TextView) view.findViewById(R.id.date);
                TextView amountTxtVw = (TextView) view.findViewById(R.id.amount);
                TextView pkgNameTxtVw = (TextView) view.findViewById(R.id.pkg_name);
                int[] formatDT = null;
                try {
                    formatDT = CommonCode.formatDT(recentTransactionHistory
                            .getUpdatedOnUtc());
                } catch (java.text.ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                dateTxtVw.setText(CommonCode.monthYear(formatDT[0], formatDT[1],
                        formatDT[2])
                        + " "
                        + CommonCode
                        .getTimeFromTimeStamp(recentTransactionHistory
                                .getUpdatedOnUtc()));

                String txnId = "Transaction Id - "
                        + recentTransactionHistory.getExternalPaymentTransactionId();

                String description = "";

                String transactionAmount = "";

                if (recentTransactionHistory.getTransactionType().equalsIgnoreCase("credit")) {

                    transactionAmount = recentTransactionHistory.getAmountAfterPromotion() + " Cr.";

                    if (recentTransactionHistory.getPromotionDetails() != null) {
                        description += "Promotion - " + recentTransactionHistory.getPromotionDetails().getPromotionName();
                        if (recentTransactionHistory.getCashbackAmount() > 0) {
                            transactionAmount = recentTransactionHistory.getCashbackAmount() + " Cr.";
                        }
                    } else if (recentTransactionHistory.getPackageDetails() != null) {
                        description += "Package - " + recentTransactionHistory.getPackageDetails().getPackageName();
                    } else {
                        description += "";
                    }
                } else if (recentTransactionHistory.getTransactionType().equalsIgnoreCase("debit")) {

                    transactionAmount = recentTransactionHistory.getAmountAfterPromotion() + " Dr.";

                    if (recentTransactionHistory.getSRDetails() != null) {

                        try {
                            description += "SR - " + recentTransactionHistory.getSRDetails().getServiceRequisitionType() + " (" + recentTransactionHistory.getSRDetails().getSRPublicId() + "), " + CommonCode.formatDT(recentTransactionHistory.getSRDetails().getSRCreatedOnUtc());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else if (recentTransactionHistory.getPackageDetails() != null) {
                        description += "Package - " + recentTransactionHistory.getPackageDetails().getPackageName();
                    } else {
                        description += "";
                    }
                }

                transaction_id.setText(txnId);

                amountTxtVw.setText(transactionAmount.toString());

                pkgNameTxtVw.setText(description);

//                if (CommonCode.isNullOrEmpty(recentTransactionHistory
//                        .getPackageDetails().getPackageName())) {
//                    pkgNameTxtVw.setVisibility(View.GONE);
//                } else {
//                    pkgNameTxtVw.setText("Package - "
//                            + recentTransactionHistory.getPackageDetails().getPackageName());
//                }
                recent_transactions_container.addView(view);

            }

        } else {
            Toast.makeText(RecentTransactions.this, "No Transaction Yet...",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
