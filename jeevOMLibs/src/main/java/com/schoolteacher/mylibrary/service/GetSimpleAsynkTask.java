package com.schoolteacher.mylibrary.service;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;

import com.schoolteacher.mylibrary.interfaces.ServiceRequestInterface;
import com.schoolteacher.mylibrary.interfaces.VerificationListner;
import com.schoolteacher.mylibrary.util.InputStreamToString;

public class GetSimpleAsynkTask extends AsyncTask<String, Void, String> {
    Activity activity;
    private ServiceRequestInterface simpleGetRequest;
    private String type;
    private VerificationListner listner;

    public GetSimpleAsynkTask(Activity activity) {
        super();
        this.simpleGetRequest = (ServiceRequestInterface) activity;

    }

    public GetSimpleAsynkTask(Activity activity, String type) {
        super();
        this.listner = (VerificationListner) activity;
        this.type = type;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
        return makeRequest(urls[0]);
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Service Error") || result.equals("") || result.equals(null)) {
            if (type.equals("missedCallVerify")) {
                listner.onMissedCallVerification(result);
            } else {
                simpleGetRequest.onGetRequestResult(result);
            }
        } else if (result.equals("No Internet Connectivity")) {
            if (type.equals("missedCallVerify")) {
                listner.onMissedCallVerification(result);
            } else {
                simpleGetRequest.onGetRequestResult(result);
            }
        } else {
            if (type.equals("missedCallVerify")) {
                listner.onMissedCallVerification(result);
            } else {
                simpleGetRequest.onGetRequestResult(result);
            }
        }
    }

    // Method to get Doctor Types
    @SuppressWarnings("deprecation")
    public String makeRequest(String url) {
        InputStream inputStream = null;
        String result = "";
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            // convert inputstream to string
            if (inputStream != null && responseCode == 200) {
                result = InputStreamToString.convertInputStreamToString(inputStream);
            } else {
                result = "Service Error";
            }
        } catch (Exception e) {
            result = "No Internet Connectivity";
        }
        return result;
    }
}
