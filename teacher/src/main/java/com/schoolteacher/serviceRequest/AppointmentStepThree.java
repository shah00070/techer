package com.schoolteacher.serviceRequest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.CellNumberCaptureDialog;
import com.schoolteacher.dialog.CellNumberVerificationDialog;
import com.schoolteacher.interfaces.CapturePhoneInterface;
import com.schoolteacher.library.main.PhoneEmailVerification;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.interfaces.MissedCallCodeListner;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.CallToActionMessage;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.DocumentList;
import com.schoolteacher.pojos.DocumentObject;
import com.schoolteacher.pojos.Documents;
import com.schoolteacher.pojos.ResponseApi;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceRequisitionResult;
import com.schoolteacher.pojos.UploadDocumentObject;
import com.schoolteacher.service.ServiceRequisition;
import com.schoolteacher.service.UploadDocuments;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class AppointmentStepThree extends ActionBarActivity implements
        OnClickListener, MissedCallCodeListner, CapturePhoneInterface {
    private RelativeLayout image_name;
    private CircleImageView img_doctor;
    private TextView name_step_three, mode_step_three, select_date_time,
            appointment_for_value, select_fee, more_voucher_codes;
    private CallToActionRequest requestSendMessage;
    private Toolbar toolbar;
    Gson gson;


    private DialogFragment progressDialog;
    private Button btn_step_three;
    private JeevomSession session;
    private String referenceNo, authToken, appointment_type, name_value, image_url, date_value, time_value;
    private EditText message_value, voucher_code_value;
    private GlobelAlertWithFinish globalAlertFinish;
    private GlobalAlert globalAlert;
    private List<DocumentObject> documentList;
    private JeevomSession jeevomSession;
    UserCurrentLocationManager locationManager;


    private String serviceRequisition;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
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
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_step_three);
        hideKeyboard();
        gson = new GsonBuilder().create();
        jeevomSession = new JeevomSession(getApplicationContext());
        documentList = new LinkedList<>();
        locationManager=new UserCurrentLocationManager(getApplicationContext());
        // get alert window dialogs references
        getAlertWindowDialogReferences();

        // set action bar
        setUpActionBar();

        // sessions
        session = new JeevomSession(getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }

        Intent intent = getIntent();
        serviceRequisition=intent.getStringExtra("service_request_type");
        appointment_type = intent.getStringExtra("name_appointment_type");
        image_url = intent.getStringExtra("img_url");
        name_value = intent.getStringExtra("name");
        requestSendMessage = (CallToActionRequest) intent
                .getSerializableExtra("requestObject");
        date_value = intent.getStringExtra("date");
        time_value = intent.getStringExtra("time");
        if (intent.hasExtra("documents")) {
            documentList = (List<DocumentObject>) intent
                    .getSerializableExtra("documents");
        }
        btn_step_three = (Button) findViewById(R.id.btn_step_three);
        btn_step_three.setOnClickListener(this);


        // voucher code fields

        message_value = (EditText) findViewById(R.id.message_value);
        image_name = (RelativeLayout) findViewById(R.id.image_name);
        img_doctor = (CircleImageView) findViewById(R.id.img_doctor);
        name_step_three = (TextView) findViewById(R.id.name_step_three);
        mode_step_three = (TextView) findViewById(R.id.mode_step_three);
        select_date_time = (TextView) findViewById(R.id.select_date_time);
        appointment_for_value = (TextView) findViewById(R.id.appointment_for_value);
        select_fee = (TextView) findViewById(R.id.select_fee);

        mode_step_three.setText(appointment_type);

        if (Integer.valueOf(requestSendMessage.getFees()) > 0)
            select_fee.setText("Rs. " + requestSendMessage.getFees());
        else
            select_fee.setText("N/A");

        select_date_time
                .setText("Date: " + date_value + " Time: " + time_value);

        if (requestSendMessage.isIsRequestedByVisitor()) {
            appointment_for_value.setText("Booking For: "
                    + requestSendMessage.getVisitorTitle() + " "
                    + requestSendMessage.getVisitorFirstName() + " "
                    + requestSendMessage.getVisitorLastName());
        } else {
            appointment_for_value.setText("Booking For: "
                    + requestSendMessage.getForName());
        }

        // set name
        if (!CommonCode.isNullOrEmpty(name_value))
            name_step_three.setText(name_value);

        // set image
        Picasso.with(this).load(image_url).into(new Target() {

            @Override
            public void onPrepareLoad(Drawable arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onBitmapLoaded(Bitmap arg0, LoadedFrom arg1) {
                setImage(arg0);
            }

            @Override
            public void onBitmapFailed(Drawable arg0) {
                // TODO Auto-generated method stub

            }
        });
        // set image and name layout background
        image_name.setBackgroundColor(getResources().getColor(R.color.Gray));
        name_step_three.setTextColor(getResources().getColor(R.color.White));
        mode_step_three.setTextColor(getResources().getColor(R.color.White));
        select_fee.setTextColor(getResources().getColor(R.color.White));
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_step_two);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Book Appointment");
    }

    private void getAlertWindowDialogReferences() {
        globalAlert = new GlobalAlert(this);
        globalAlertFinish = new GlobelAlertWithFinish(this);
        globalAlertFinish.setCancelable(false);
    }

    private void setImage(Bitmap arg0) {
        switch (getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                // d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
                // arg0, 32, 32, true));
                img_doctor.getLayoutParams().width = 32;
                img_doctor.getLayoutParams().height = 32;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                // d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
                // arg0, 48, 48, true));
                img_doctor.getLayoutParams().width = 48;
                img_doctor.getLayoutParams().height = 48;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                // d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
                // arg0, 72, 72, true));
                img_doctor.getLayoutParams().width = 72;
                img_doctor.getLayoutParams().height = 72;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                // d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
                // arg0, 96, 96, true));
                img_doctor.getLayoutParams().width = 96;
                img_doctor.getLayoutParams().height = 96;
                break;
        }

        Picasso.with(this).load(image_url).placeholder(R.drawable.jeevom_back)
                .error(R.drawable.jeevom_back).into(img_doctor);
    }

    private void makeServiceCall(final CallToActionRequest requestSendMessage) {

        RestAdapter serviceRequisitionAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("service_requisition"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_req_interface = serviceRequisitionAdapter
                .create(ServiceRequisition.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        service_req_interface.makeServiceRequest(locationManager.getUserLocation(),authToken, requestSendMessage,

                new Callback<ServiceRequisitionResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialog.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(AppointmentStepThree.this))) {
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

                            if (arg0.getResponse().getStatus() == 400) {
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
    public void onClick(View v) {


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

            if (documentList.size() > 0) {
                uploadImage(documentList);
            }
            // check for user cell no -- if cell no is available and not
            // verified then show verification screen
            // if cell no not available -- show cell no capture screen and then
            // screen to verify screen
            // else make direct service call
            if (jeevomSession.getLoggedInStatus()) {
                if (!CommonCode.isNullOrEmpty(jeevomSession.getCellNumber())) {
                    if (jeevomSession.getUserPhoneVerifyStatus()) {
                        // cell no is available and verified -- make service
                        // call
                        makeServiceCall(requestSendMessage);
                    } else {
                        // cell no is available but not verified
                        Toast.makeText(AppointmentStepThree.this,
                                "Cell no not verified , show verification screen",
                                Toast.LENGTH_SHORT).show();
                        goToCellVerificationScreen(session.getCellNumber());
                    }
                } else {
                    // cell no not available -- show the user cell no capture
                    // screen

                    try {
                        CellNumberCaptureDialog showDialog = CellNumberCaptureDialog
                                .newInstance();
                        showDialog.show(getSupportFragmentManager(),
                                "context_dialog_frag");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                makeServiceCall(requestSendMessage);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Applying Exit Animation;
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

    @Override
    public void onCodeGenerated(String result) {
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
            } else if (code.equals("Success")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                DataContainer codeValues = gson.fromJson(result,
                        DataContainer.class);
                String UniqueRequestId = codeValues.getData()
                        .getUniqueRequestId();
                String CallToVerifyNumberAsText = codeValues.getData()
                        .getCallToVerifyNumberAsText();

                Intent phoneEmailIntent = new Intent(AppointmentStepThree.this,
                        PhoneEmailVerification.class);
                phoneEmailIntent.putExtra("uniqueRequestId", UniqueRequestId);
                phoneEmailIntent.putExtra("CallToVerifyNumberAsText",
                        CallToVerifyNumberAsText);

                phoneEmailIntent.putExtra("isSignUpTimeVerification", true);
                phoneEmailIntent.putExtra("type", "phone");
                phoneEmailIntent.putExtra("phone",
                        requestSendMessage.getVisitorCellNumber());
                phoneEmailIntent.putExtra("email",
                        requestSendMessage.getVisitorEmail());
                startActivityForResult(
                        phoneEmailIntent,
                        JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE);
            }
        }
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {

        super.onActivityResult(arg0, arg1, arg2);

        if (arg0 == JeevOMUtil.PHONE_AND_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_OK) {
            showAlertFinish("Thank you for Submitting your request." + "\n"
                    + "Your Reference Number is: " + referenceNo);
        } else if (arg0 == JeevOMUtil.PHONE_AND_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_CANCELED) {
            Crouton.makeText(AppointmentStepThree.this, "Please try again",
                    Style.ALERT).show();
        }

        if (arg0 == JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE) {

            // make call to update service requisition status

            updateServiceRequisitionStatus(referenceNo);

        } else if (arg0 == JeevOMUtil.PHONE_EMAIL_VER_REQUEST_CODE_FROM_SIGNUP_PAGE
                && arg1 == Activity.RESULT_CANCELED) {
            Crouton.makeText(AppointmentStepThree.this, "Please try again",
                    Style.ALERT).show();
        }

    }

    private void updateServiceRequisitionStatus(String referenceNo2) {
        RestAdapter updateStatusAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .setLog(new AndroidLog("update")).setLogLevel(LogLevel.FULL)
                .build();
        ServiceRequisition update_service_req_interface = updateStatusAdapter
                .create(ServiceRequisition.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);

        update_service_req_interface.updateServiceRequisitionStatus(locationManager.getUserLocation(),"Basic "
                        + session.getAuthToken(), referenceNo2, "Active",
                new Callback<ResponseApi<String>>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        progressDialog.dismissAllowingStateLoss();
                        // loadToast.error();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(AppointmentStepThree.this))) {
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
                            Gson gson = new GsonBuilder().setPrettyPrinting()
                                    .create();
                            ResponseApi<String> searchResultsResponse = gson
                                    .fromJson(json, ResponseApi.class);
                            String code = searchResultsResponse.getStatus()
                                    .getCode();
                            String message = searchResultsResponse.getStatus()
                                    .getMessage();

                            if (arg0.getResponse().getStatus() == 400) {
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
                    public void success(ResponseApi<String> arg0, Response arg1) {
                        showAlertFinish("Thank you for Submitting your request."
                                + "\n"
                                + "Your Reference Number is: "
                                + referenceNo);
                    }
                });
    }

    private void hideKeyboard() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void uploadImage(List<DocumentObject> documentList) {

        for (DocumentObject documentObject : documentList) {
            if (session.getLoggedInStatus()) {
                documentObject
                        .setOwnerId(String.valueOf(session.getMemberId()));
            }

        }
        UploadDocumentObject object = new UploadDocumentObject();
        object.setDocumentList(documentList);
        RestAdapter uploadAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("upload"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        UploadDocuments uplaodService = uploadAdapter
                .create(UploadDocuments.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(this.getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        uplaodService.startUploadingDocuments( gson.toJson(locationManager.getUserLocation()).toString(),object,
                new Callback<Documents>() {

                    @Override
                    public void success(Documents arg0, Response arg1) {
                        progressDialog.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            List<DocumentList> documentList2 = arg0.getData()
                                    .getDocumentList();

                            if (documentList2 != null
                                    && documentList2.size() > 0) {

                                StringBuilder stringValue = new StringBuilder();
                                for (int i = 0; i < documentList2.size(); i++) {

                                    if (i == 0) {
                                        stringValue.append(documentList2.get(i)
                                                .getId());
                                    } else {
                                        stringValue.append(", "
                                                + documentList2.get(i).getId());
                                    }
                                }

                                requestSendMessage.setDocumentIds(stringValue
                                        .toString());
                            } else {
                                requestSendMessage.setDocumentIds("");
                            }

                            // makeServiceCall(requestSendMessage);
                        }

                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        progressDialog.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(getApplicationContext()))) {
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
                            Gson gson = new GsonBuilder().setPrettyPrinting()
                                    .create();
                            Documents responseValue = gson.fromJson(json,
                                    Documents.class);
                            String code = responseValue.getStatus().getCode();
                            String message = responseValue.getStatus()
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
                            } else if (code.equals("BE-1004")) {
                                showAlert(message);
                            }
                        }

                    }
                });

    }

    @Override
    public void phoneNumberCapturedSuccessfully(String cellNumber) {

        goToCellVerificationScreen(cellNumber);
    }

    private void goToCellVerificationScreen(String cellNumber) {
        // phone no captured successfully -- send verification code
        try {
            CellNumberVerificationDialog showDialog = CellNumberVerificationDialog
                    .newInstance();
            Bundle args = new Bundle();
            args.putString("cellNumber", cellNumber);
            showDialog.setArguments(args);
            showDialog.show(getSupportFragmentManager(), "context_dialog_frag");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void phoneNumberVerifiedSuccessfully(boolean value) {
        // TODO Auto-generated method stub
        if (value || !value) {
            makeServiceCall(requestSendMessage);
        }

    }
}