package com.schoolteacher.main;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.schoolteacher.R;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

/**
 * Created by chandan on 5/1/16.
 */
public class PayNowWebViewActivity extends AppCompatActivity {

    private WebView paynow_webview;
    private ProgressBar progressBar;
    String paymentStatus = null, referenceNo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_now_layout);

        Bundle bundle = getIntent().getExtras();
        String servicePubId = null, memberPubId = null;
        if (bundle != null) {
            servicePubId = bundle.getString("spubId");
            memberPubId = bundle.getString("mpubId");
        }

        final String payUrl = JeevOMUtil.PayNowUrl + "RequestProcess?spub=" + servicePubId + "&mpub=" + memberPubId + "&isSourceMobile=true";

        paynow_webview = (WebView) findViewById(R.id.paynow_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        paynow_webview.setWebChromeClient(new WebChromeClient());
        paynow_webview.getSettings().setJavaScriptEnabled(true);
        paynow_webview.getSettings().setLoadWithOverviewMode(true);
        paynow_webview.addJavascriptInterface(new WebPayInterface(this), "jsInterface");
        paynow_webview.getSettings().setUseWideViewPort(true);
        paynow_webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        paynow_webview.getSettings().setAllowFileAccess(true);
        paynow_webview.getSettings().setDomStorageEnabled(true);
        paynow_webview.clearCache(true);

        paynow_webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                return true;
            }
        });

        paynow_webview.loadUrl(payUrl);
    }


    // interface bridge between activity and webview

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

            new AlertDialog.Builder(PayNowWebViewActivity.this)
                    .setTitle("Payment Status")
                    .setMessage("Recharged successfully. Reference No. is "
                            + refNo)
                    .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }
}
