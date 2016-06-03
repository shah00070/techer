package com.schoolteacher.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;

public class personal extends FragmentActivity /*implements OnDateSetListener */ {
/*    public long date = 0l;
    public Button dob;
    String memberId, consumerId, dateFormatted;
    boolean FLAG=false;
    ConsumerDetailsResponse consumerDetails;
    String addressLine1, country, state, city, area, address_value;
    private double latitude, longitude;
    ValuesManager valuesManager;
    Gson gson;
    public GlobalAlert globalAlert;
    private GPSTracker gpsTracker;
    public static final String DATEPICKER_TAG = "datepicker";
    // public  String name,pic,email,mobile,address,gender,submit;
    public EditText name,addpic,email,mobile,address;
    DialogFragment newFragment;
    ProgressDialogFragment progressBarFragment;
    private UserAddressData mapAddress;
    JeevomSession jeevom_session;
    String authToken = null;
    GooglePlayAlert googleAlert;
    ToggleButton gender;
    String gender1="Female";

    boolean FINAL_FLAG=false;
    @Override*/
private JeevomSession session;
    private GlobalAlert globalAlert;
    String authToken = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.per);


        session = new JeevomSession(this);
        globalAlert = new GlobalAlert(this);
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
/*

        gson = new GsonBuilder().create();

        name=(EditText)findViewById(R.id.namee);
        addpic=(EditText)findViewById(R.id.addpicc);
        email=(EditText)findViewById(R.id.Emailaddress);
        mobile=(EditText)findViewById(R.id.mobilee);
        address=(EditText)findViewById(R.id.addresss);
        gender=(ToggleButton)findViewById(R.id.toggleButton);

        gender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(gender.isChecked()){
                    //Button is ON
                    // Do Something
                    Toast.makeText(personal.this, "male", Toast.LENGTH_SHORT).show();
                    gender1="Male";                }
                else{
                    Toast.makeText(personal.this, "female", Toast.LENGTH_SHORT).show();
                    gender1="Female";
                }
                //Button is OFF
                // Do Something
            }
        });


        googleAlert = new GooglePlayAlert(personal.this);
        memberId = String.valueOf(jeevom_session.getMemberId());
        consumerId = jeevom_session.getConsumerIds().get(
                JeevomSession.JEEVOM_CONSUMER_ID);


        valuesManager = new ValuesManager(this.getApplicationContext());
        if (!CommonCode.isNullOrEmpty(jeevom_session.getAuthToken())) {
            authToken = "Basic " + jeevom_session.getAuthToken();
        }



        getConsumerDetails(consumerId);




        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });








*/

        final TextView txt=(TextView)findViewById(R.id.skip);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent myIntent = new Intent(personal.this, HomeActivity.class);
                startActivity(myIntent);
                overridePendingTransition(com.schoolteacher.mylibrary.R.anim.trans_left_in, com.schoolteacher.mylibrary.R.anim.trans_left_exit);
                finish();


            }
        });
/*
        dob=(Button)findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){



                showDatePickerDialog();


            }
        });

        final Button date1=(Button)findViewById(R.id.submitt);
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                validateinput();


                if(FLAG==true) {


                    dataupload();

                }


            }
        });






*/
    }



/*

    public void dataupload(){
        ConsumerUpdateRequestModel finalUpdateObject = new ConsumerUpdateRequestModel();
        finalUpdateObject.setId(consumerId);
        finalUpdateObject.setType("Consumer");

        ConsumerMemberDetails memberDetails = new ConsumerMemberDetails();
        memberDetails.setFirstName(name.getText().toString()
                .trim());
        memberDetails.setCellNumber(mobile.getText().toString()
                .trim());
        memberDetails.setDateOfBirth(dateFormatted);
        memberDetails.setGender(gender1);
        memberDetails.setEmail(email.getText().toString().trim());
        memberDetails.setLastName("");
        memberDetails.setTitle("");
        memberDetails.setBloodGroupType(1);




      *//*  ConsumerContactInfo contactObject = new ConsumerContactInfo();

        contactObject.setAddressLine1(address_value);
        contactObject.setArea(area);
        contactObject.setCity(city);
        contactObject.setCountry(country);
        contactObject.setLatitude(String.valueOf(latitude));
        contactObject.setLongitude(String.valueOf(longitude));
        contactObject.setState(state);
        contactObject.setZipCode(null);
   *//*
      *//*  if (contactId > 0) {
            contactObject.setId(String.valueOf(contactId));
        } else {
            contactObject.setId("0");
        }*//*

        // String contact = gson.toJson(contactObject).toString();

        Consumer consumer = new Consumer();
//        consumer.setConsumerContactInfo(gson.toJson(contactObject).toString());
//        consumer.setConsumerProfile(gson.toJson(profileObject).toString());
        consumer.setMemberDetails(gson.toJson(memberDetails).toString());
//        consumer.setMemberEmergencyDetails(gson.toJson(emergencyDetails)
        //              .toString());
        finalUpdateObject.setData(consumer);

        RestAdapter consumerUpdateAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLog(new AndroidLog("update")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = consumerUpdateAdapter
                .create(Member.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(this.getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);

        getConsumerService.updateConsumerProfile(
                authToken, finalUpdateObject,
                new Callback<UpdateConsumerResponse>() {

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();
                        Toast.makeText(personal.this, "fail", Toast.LENGTH_SHORT).show();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(personal.this))) {
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
                            MembershipAuthenticateResponse MembershipAuthenticateErrors = gson
                                    .fromJson(
                                            json,
                                            MembershipAuthenticateResponse.class);
                            String code = MembershipAuthenticateErrors
                                    .getStatus().getCode();
                            String message = MembershipAuthenticateErrors
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
                            } else if (code.equals("BE-1004")) {
                                showAlert(message);
                            }
                        }

                    }

                    @Override
                    public void success(UpdateConsumerResponse arg0,
                                        Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        Toast.makeText(personal.this, "sucess", Toast.LENGTH_SHORT).show();

                        if (arg0.getStatus().getCode().equals("Success")) {


                            // save name of user
                            jeevom_session.setName(name
                                    .getText().toString().trim());

                            // save email
                            jeevom_session.setEmail(email.getText()
                                    .toString().trim());

                            // set phone no
                            jeevom_session.setCellNumber(mobile
                                    .getText().toString().trim());

                            // set gender
                            jeevom_session.setGender(gender1);

                      *//*      com.jeevom.mylibrary.pojo.Address address = new com.jeevom.mylibrary.pojo.Address();
                            address.setArea(area);
                            address.setCity(city);
                            address.setCountry(country);
                            address.setState(state);
                            address.setLine1(address_value);
                            address.setZipCode(null);

                            jeevom_session.saveUserAddress(address);
*//*
                            jeevom_session.setAge(String.valueOf(CommonCode
                                    .birthDate(date)));
                            //
                            Intent myIntent = new Intent(personal.this, HomeActivity.class);
                            startActivity(myIntent);
                            overridePendingTransition(com.jeevom.mylibrary.R.anim.trans_left_in, com.jeevom.mylibrary.R.anim.trans_left_exit);
                            finish();

                        }
                    }

                });

    }



















    public void verifyEmailphone(ConsumerDetailsResponse arg0){
        if (!CommonCode.isNullOrEmpty(arg0.getData().getEmail())) {
            boolean userEmailVerifyStatus = jeevom_session
                    .getUserEmailVerifyStatus();
            // if (userEmailVerifyStatus) {
            email.setEnabled(false);
            email.setFocusable(false);
            email.setFocusableInTouchMode(false);

            //}
            email.setText(arg0.getData().getEmail());
            email.clearFocus();
        }
        if (!CommonCode.isNullOrEmpty(arg0.getData().getCellNumber())) {
            boolean userPhoneVerifyStatus = jeevom_session
                    .getUserPhoneVerifyStatus();
            if (userPhoneVerifyStatus) {
                mobile.setEnabled(false);
                mobile.setFocusable(false);
                mobile.setFocusableInTouchMode(false);
            }
            mobile.setText(arg0.getData().getCellNumber());
            mobile.clearFocus();
        }

        if ((!CommonCode.isNullOrEmpty(arg0.getData().getFirstName())&& (!(arg0.getData().getFirstName().equals("-"))) )) {
            name.setEnabled(false);
            name.setFocusable(false);
            name.setFocusableInTouchMode(false);
            name.setText(arg0.getData().getFirstName());
            name.clearFocus();
        }
        if (!CommonCode.isNullOrEmpty(arg0.getData().getDateOfBirth())) {
            dob.setEnabled(false);
            dob.setFocusable(false);
            dob.setFocusableInTouchMode(false);
            dob.setText(arg0.getData().getDateOfBirth());
            dob.clearFocus();
        }

        if (!CommonCode.isNullOrEmpty(arg0.getData().getGender())) {
            if(arg0.getData().getGender().equals("Male")){
                gender.setChecked(true);
            }else if(arg0.getData().getGender().equals("Female")){
                gender.setChecked(false);
            }else{
                gender.setChecked(false);

            }
            gender.setEnabled(false);
            gender.setFocusable(false);
            gender.setFocusableInTouchMode(false);
            gender.clearFocus();
        }

    }


    private void getConsumerDetails(String consumerId2) {
        RestAdapter consumerAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("consumer")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = consumerAdapter
                .create(Member.class);
        progressBarFragment = ProgressDialogFragment.newInstance();
        progressBarFragment.show(getSupportFragmentManager(), "dialog");
        progressBarFragment.setCancelable(false);

        getConsumerService.getConsumer(
                authToken, consumerId2,
                String.valueOf(valuesManager.getVersion()),
                new Callback<ConsumerDetailsResponse>() {
                    @Override
                    public void success(ConsumerDetailsResponse arg0,
                                        Response arg1) {
                        progressBarFragment.dismissAllowingStateLoss();

                        if (arg0.getData() != null) {
                            // populate blood group Spinner
                            //  populateBloodGroupSpinner();
                            consumerDetails = arg0;
                            verifyEmailphone(consumerDetails);


                      *//*      Picasso.with(getActivity()).load(img)
                                    .placeholder(R.drawable.jeevom_back)
                                    .error(R.drawable.jeevom_back).into(consumer_image);
*//*
                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressBarFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(personal.this))) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION);
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                            } else if (arg0.getResponse() == null) {
                                showAlert(JeevOMUtil.SOMETHING_WRONG);
                            }
                        } else if (arg0.getResponse().getStatus() == 426) {
                            showGooglePlayAlert(getResources().getString(
                                    R.string.google_update));
                        } else if (arg0.getResponse().getStatus() > 400) {
                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                        } else {
                            String json = new String(((TypedByteArray) arg0
                                    .getResponse().getBody()).getBytes());

                            MembershipAuthenticateResponse MembershipAuthenticateErrors = gson
                                    .fromJson(
                                            json,
                                            MembershipAuthenticateResponse.class);
                            String code = MembershipAuthenticateErrors
                                    .getStatus().getCode();
                            String message = MembershipAuthenticateErrors
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
                            } else if (code.equals("BE-1004")) {
                                showAlert(message);
                            }
                        }
                    }
                });

    }











    private void showGooglePlayAlert(String message) {
        googleAlert.show();
        googleAlert.setMessage(message);
    }





    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }
    public void validateinput(){
        if (CommonCode.isNullOrEmpty(name.getText().toString()
                .trim())) {
            Crouton.makeText(personal.this, JeevOMUtil.EMPTY_FIRST_NAME,
                    Style.ALERT).show();
            FLAG=false;
            return;
        }

        else if (CommonCode.isNullOrEmpty(address.getText().toString()
                .trim())) {
            Crouton.makeText(personal.this, JeevOMUtil.EMPTY_ADDRESS,
                    Style.ALERT).show();
            FLAG=false;
            return;
        }
        else if (CommonCode.isNullOrEmpty(email.getText().toString()
                .trim())) {
            Crouton.makeText(personal.this, JeevOMUtil.EMPTY_EMAIL,
                    Style.ALERT).show();
            FLAG=false;
            return;
        }
        else if (CommonCode.isNullOrEmpty(mobile.getText().toString()
                .trim())) {
            Crouton.makeText(personal.this, JeevOMUtil.EMPTY_PHONE,
                    Style.ALERT).show();
            FLAG=false;
            return;
        }

        else if (CommonCode.isNullOrEmpty(dob.getText().toString()
                .trim())) {
            Crouton.makeText(personal.this, JeevOMUtil.EMPTY_DOB,
                    Style.ALERT).show();
            FLAG=false;
            return;
        }else {
            FLAG=true;
        }


    }



    private void showDatePickerDialog() {
        final DatePickerDialog datePickerDialog;
        final Calendar calendar = Calendar.getInstance();

        if (date == 0l) {
            datePickerDialog = DatePickerDialog.newInstance(this,
                    calendar.get(Calendar.YEAR) - 18,
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), false);
        } else {
            calendar.setTimeInMillis(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = DatePickerDialog.newInstance(this, year, month,
                    day, false);
        }
        datePickerDialog.setYearRange(1902, 2099);
        datePickerDialog.setCloseOnSingleTapDay(false);
        datePickerDialog.show(getSupportFragmentManager(),
                DATEPICKER_TAG);
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

        dob.setText(CommonCode.monthYear(day, month, year));
        dateFormatted = (month + 1) + "/" + day + "/" + year;

        date = CommonCode.convertToMilliseconds(year, month, day);

    }*/
}
