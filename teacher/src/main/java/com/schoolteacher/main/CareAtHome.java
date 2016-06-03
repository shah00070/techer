package com.schoolteacher.main;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.CallToActionMessage;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceRequisitionResult;
import com.schoolteacher.service.ServiceRequisition;

import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class CareAtHome extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private Spinner title_spinner;
    private EditText new_first_name, last_name, phone, email, message_value;
    private Button send_careathome_request;
    private List<String> titleSpinnerItems = new LinkedList<>();
    private JeevomSession session;
    private CallToActionRequest requestSendMessage;
    private String titleType, referenceNo, authToken;
    private GlobelAlertWithFinish globalAlertFinish;
    private GlobalAlert globalAlert;
    UserCurrentLocationManager locationManager;
    private DialogFragment progressDialog;
    private Toolbar toolbar_careathome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_at_home);
        setUpActionBar();
        globalAlert = new GlobalAlert(this);
        globalAlertFinish = new GlobelAlertWithFinish(this);
        session = new JeevomSession(getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        title_spinner = (Spinner) findViewById(R.id.new_title_spinner);
        new_first_name = (EditText) findViewById(R.id.new_first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        phone = (EditText) findViewById(R.id.new_phone);
        email = (EditText) findViewById(R.id.new_email);
        message_value = (EditText) findViewById(R.id.message_value);
        send_careathome_request = (Button) findViewById(R.id.send_careathome_request);


        // fill spinner
        titleSpinnerItems.add("Mr.");
        titleSpinnerItems.add("Ms.");
        titleSpinnerItems.add("Mrs.");
        titleSpinnerItems.add("Dr.");

        if (titleSpinnerItems.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    CareAtHome.this, R.layout.spinner_item,
                    titleSpinnerItems);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            title_spinner.setAdapter(adapter);
        }

        title_spinner.setOnItemSelectedListener(this);

        if (session.getLoggedInStatus()) {
            if (!CommonCode.isNullOrEmpty(session.getName())) {
                String[] splited = session.getName().split("\\s+");

                if (splited.length > 1) {
                    if (!CommonCode.isNullOrEmpty(splited[0])) {
                        new_first_name.setText(splited[0]);
                    }

                    if (!CommonCode.isNullOrEmpty(splited[1])) {
                        last_name.setText(splited[1]);
                    }


                } else if (splited.length == 1) {
                    if (!CommonCode.isNullOrEmpty(splited[0])) {
                        new_first_name.setText(splited[0]);
                    }
                }
                //  new_first_name.setText(session.getName());
            }

            if (!CommonCode.isNullOrEmpty(session.getEmail())) {
                email.setText(session.getEmail());
            }

            if (!CommonCode.isNullOrEmpty(session.getCellNumber())) {
                phone.setText(session.getCellNumber());
            }


            if (!CommonCode.isNullOrEmpty(session.getTitle())) {


                title_spinner.setSelection(titleSpinnerItems.indexOf(session.getTitle()));
            }
        }


        send_careathome_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (CommonCode.isNullOrEmpty(new_first_name.getText().toString())) {

                    Crouton.makeText(CareAtHome.this,
                            "Please enter First Name", Style.ALERT).show();
                    return;
                }
                if (CommonCode.isNullOrEmpty(last_name.getText().toString())) {

                    Crouton.makeText(CareAtHome.this,
                            "Please enter Last Name", Style.ALERT).show();
                    return;
                }

                if (CommonCode.isNullOrEmpty(phone.getText().toString().trim())) {
                    Crouton.makeText(CareAtHome.this,
                            "Please enter valid Phone No.", Style.ALERT).show();
                    return;
                } else {

                    if (!CommonCode.isNullOrEmpty(phone.getText().toString())) {
                        if (!CommonCode.validatePhone(phone.getText()
                                .toString())) {

                            Crouton.makeText(CareAtHome.this,
                                    "Please enter valid Phone No.", Style.ALERT)
                                    .show();
                            return;
                        }
                    }
                    if (CommonCode.isNullOrEmpty(email.getText().toString()
                            .trim())) {
                        Crouton.makeText(CareAtHome.this,
                                "Please enter Valid Email", Style.ALERT).show();
                        return;

                    }
                    if (!CommonCode.isNullOrEmpty(email.getText().toString()
                            .trim())) {
                        if (!CommonCode.validateEmail(email.getText()
                                .toString().trim())) {

                            Crouton.makeText(CareAtHome.this,
                                    "Please enter Valid Email", Style.ALERT).show();
                            return;

                        }
                    }

                    if (CommonCode.isNullOrEmpty(message_value.getText().toString()
                            .trim())) {
                        Crouton.makeText(CareAtHome.this,
                                "Please enter Message", Style.ALERT).show();
                        return;

                    }
                }


                requestSendMessage = new CallToActionRequest();

                requestSendMessage.setDefaultServiceConfigurationId("2");
                // if user is potential member
                if (!session.getLoggedInStatus()) {
                    requestSendMessage.setIsBasicDetailsShared(true);
                    requestSendMessage.setIsMedicalInfoShared(true);
                    requestSendMessage.setIsHealthTrackShared(true);

                    requestSendMessage.setIsRequestedByVisitor(true);
                    requestSendMessage.setVisitorTitle(titleType);
                    requestSendMessage.setVisitorFirstName(new_first_name.getText()
                            .toString().trim());
                    requestSendMessage.setVisitorLastName(last_name.getText()
                            .toString().trim());

                    requestSendMessage.setIsSupportRequest(true);
                    requestSendMessage.setVisitorEmail(email.getText().toString()
                            .trim());
                    requestSendMessage.setVisitorCellNumber(phone.getText()
                            .toString().trim());

                } else {
                    requestSendMessage.setByMemberId(String.valueOf(session
                            .getMemberId()));
                    requestSendMessage.setIsSupportRequest(true);
                    requestSendMessage.setForMemberId(String.valueOf(session
                            .getMemberId()));
                    requestSendMessage.setForAge(session.getAge());
                    requestSendMessage.setForGender(session.getGender());
                    requestSendMessage.setForName(session.getName());

                    requestSendMessage.setIsBasicDetailsShared(true);
                    requestSendMessage.setIsMedicalInfoShared(true);
                    requestSendMessage.setIsHealthTrackShared(true);


                }

                requestSendMessage.setIsIndirectRequest(true);
                if (!CommonCode
                        .isNullOrEmpty(message_value.getText().toString().trim())) {
                    // message object
                    List<CallToActionMessage> messageList = new LinkedList<>();
                    CallToActionMessage message = new CallToActionMessage();
                    if (session.getLoggedInStatus()) {
                        message.setFromMemberId(String.valueOf(session.getMemberId()));
                    }
                    if (!CommonCode.isNullOrEmpty(requestSendMessage
                            .getToProfessionalId())) {
                        message.setToProfessionalId(requestSendMessage
                                .getToProfessionalId());
                    } else {
                        message.setToFacilityId(requestSendMessage.getToFacilityId());
                    }

                    message.setMessage(message_value.getText().toString().trim());
                    messageList.add(message);
                    requestSendMessage.setMessages(messageList);
                }

                makeServiceCall(requestSendMessage);
            }

        });

    }

    private void setUpActionBar() {
        toolbar_careathome = (Toolbar) findViewById(R.id.toolbar_careathome);
        setSupportActionBar(toolbar_careathome);
        getSupportActionBar().setTitle("Care @ Home");
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

    private void makeServiceCall(final CallToActionRequest requestSendMessage) {

        RestAdapter serviceRequisitionAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("care"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_req_interface = serviceRequisitionAdapter
                .create(ServiceRequisition.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        service_req_interface.makeServiceRequest(locationManager.getUserLocation(), authToken, requestSendMessage,

                new Callback<ServiceRequisitionResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialog.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(CareAtHome.this))) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION);
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                            } else if (arg0.getResponse() == null) {
                                showAlert(JeevOMUtil.SOMETHING_WRONG);
                            }
                        } else if (arg0.getResponse().getStatus() > 400) {
                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                        } else {
                            String json = new String(((TypedByteArray) arg0
                                    .getResponse().getBody()).getBytes());
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            SearchResultsResponse searchResultsResponse = gson
                                    .fromJson(json, SearchResultsResponse.class);
                            String code = searchResultsResponse.getStatus().getCode();
                            String message = searchResultsResponse.getStatus()
                                    .getMessage();

                            if (code.equals("IOE-1000")) {
                                showAlert(message);
                            } else if (code.equals("BE-1001")) {
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

                    @Override
                    public void success(ServiceRequisitionResult arg0, Response arg1) {
                        progressDialog.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {

                            referenceNo = arg0.getData();

                            showAlertFinish("Thank you for Submitting your request."
                                    + "\n" + "Your Reference Number is: " + referenceNo);

                        }
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (titleSpinnerItems.size() > 0) {

            titleType = titleSpinnerItems.get(position);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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