package com.schoolteacher.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.schoolteacher.R;
import com.schoolteacher.dialog.PaymentResultDialog;
import com.schoolteacher.interfaces.PaymentResult;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

import org.apache.http.util.EncodingUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PaymentActivity extends ActionBarActivity implements PaymentResult {
    Intent sending_intent;
    WebView payment_view;
    String postData, name, email, cellnumber, package_name;
    int memberId, promotionId;
    String randomUUID;
    double amount;
    String paymentStatus = null, referenceNo = null;
    PaymentResultDialog paymentDialog;
    // private String flag;
    private Toolbar toolbar_payment;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);
        sending_intent = new Intent();
        setUpActionBar();
        paymentDialog = new PaymentResultDialog(this);
        payment_view = (WebView) findViewById(R.id.payment_view);
        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        cellnumber = intent.getStringExtra("cellnumber");
        package_name = intent.getStringExtra("packageName");
        memberId = intent.getIntExtra("memberId", 0);
        promotionId = intent.getIntExtra("promotionId", 0);

        StringBuilder promotionCode = new StringBuilder();
        postData = "PackageName=" + package_name + "&FirstName="
                + name + "&Email="
                + email + "&CellNumber=" + cellnumber + "&MemberId="
                + memberId;

        if (promotionId > 0)
            postData = postData + "&PromotionId=" + promotionId;
        else
            postData = postData + "&PromotionId=" + null;
        payment_view.addJavascriptInterface(new WebPayInterface(this),
                "jsInterface");
        payment_view.getSettings().setJavaScriptEnabled(true);
        payment_view.getSettings().setLoadWithOverviewMode(true);
        payment_view.getSettings().setUseWideViewPort(true);
        payment_view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        payment_view.setScrollbarFadingEnabled(false);

        payment_view.getSettings().setDomStorageEnabled(true);
        payment_view.getSettings().setBuiltInZoomControls(true);
        payment_view.loadUrl(JeevOMUtil.PaymentUrl);

        payment_view.setWebViewClient(new Callback());

        payment_view.postUrl(JeevOMUtil.PaymentUrl, EncodingUtils.getBytes(postData, "base64"));

    }

    private void setUpActionBar() {
        toolbar_payment = (Toolbar) findViewById(R.id.toolbar_payment);
        setSupportActionBar(toolbar_payment);
        getSupportActionBar().setTitle("Pay");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

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

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString(
                    (arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }

    private static String hashString(String message, String algorithm) {
        byte[] hashedBytes = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            hashedBytes = digest.digest(message.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();

        }
        return convertByteArrayToHexString(hashedBytes);
    }

    // interface bridge between activity and webview
    public class WebPayInterface {
        Context mContext;

        WebPayInterface(Context c) {
            mContext = c;
        }

        // must apply this anotation above api level 17 , otherwise it will not
        // work
        @JavascriptInterface
        public void SendPaymentStatus(String status, String refNo) {

            paymentStatus = status;
            referenceNo = refNo;

            paymentDialog.show();
            paymentDialog.setCancelable(false);
            paymentDialog
                    .setMessage("Recharged successfully. Reference No. is "
                            + refNo);

        }

    }

    @Override
    public void onBackPressed() {

        String url = payment_view.getUrl();

        if (url.contains("termsandconditions")) {
            if (payment_view.canGoBack()) {
                payment_view.goBack();
            } else {
                super.onBackPressed();
            }
        } else {
            if (CommonCode.isNullOrEmpty(paymentStatus)
                    && CommonCode.isNullOrEmpty(referenceNo)) {
                sending_intent.putExtra("payment_status", "");
                sending_intent.putExtra("reference", "");
            }

            setResult(RESULT_OK, sending_intent);
            finish();
            overridePendingTransition(R.anim.trans_right_in,
                    R.anim.trans_right_exit);
        }
    }

    @Override
    public void result(String value) {

        if (value.equals("finish")) {
            sending_intent.putExtra("reference", referenceNo);
            sending_intent.putExtra("payment_status", paymentStatus);
            sending_intent.putExtra("ok", "ok");
            setResult(RESULT_OK, sending_intent);

        } else {
            sending_intent.putExtra("reference", "1");
            sending_intent.putExtra("payment_status", "1");
            setResult(RESULT_OK, sending_intent);
        }
        // paymentDialog.dismiss();
        finish();
    }

}
