package com.schoolteacher.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.pojo.Status;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.AddFamilyMember;
import com.schoolteacher.pojos.Association;
import com.schoolteacher.pojos.AssociationResponse;
import com.schoolteacher.pojos.BloodGroup;
import com.schoolteacher.pojos.BloodGroupResponse;
import com.schoolteacher.pojos.FamilyMember;
import com.schoolteacher.pojos.FamilyMemberDetails;
import com.schoolteacher.pojos.MemberAssociation;
import com.schoolteacher.service.AssociationService;
import com.schoolteacher.service.FamilyMemberSave;
import com.schoolteacher.service.Member;

import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

public class FamilyInformation extends ActionBarActivity implements
        OnClickListener, OnDateSetListener, OnItemSelectedListener {
    public static final String DATEPICKER_TAG = "datepicker";
    List<String> titleSpinnerItems = new ArrayList<String>();
    List<String> relationSpinnerItems = new ArrayList<String>();
    List<String> bloodSpinnerItems = new ArrayList<String>();
    static int see=0;
    View rootView;
    String gender, dateFormatted, titleType = "Mr.", relationshipValue;
    Spinner title_spinner, relation_spinner, blood_spinner, gender_spinner;
    EditText first_name_value, last_name_value;
    GlobalAlert globalAlert;
    DialogFragment newFragment;
    List<Association> association;
    List<BloodGroup> bloodGroups;
    ImageView dob_image;
    long date = 0l;
    TextView dob_value;
    Button addMember;
    Calendar calendar;
    int current_year, currentMonth, currentDay, relationship_id, bloodValue;
    JeevomSession session;
    MemberAssociation member;
    CheckBox login_id, emergencyContact;
    EditText user_phone_value, user_email_value;
    String[] genders = {"Male", "Female", "Other"};
    boolean isEmergency;
    Toolbar toolbar;
    String authToken = null;
    UserCurrentLocationManager locationManager;
    Gson gson;


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
        setContentView(R.layout.family_information);
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar_family);
        setSupportActionBar(toolbar);
        gson = new GsonBuilder().create();
        member = (MemberAssociation) getIntent().getSerializableExtra("member");
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getSupportActionBar().setTitle("Add Family Member");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        globalAlert = new GlobalAlert(this);
        session = new JeevomSession(this);
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        title_spinner = (Spinner) findViewById(R.id.title_spinner);
        relation_spinner = (Spinner) findViewById(R.id.relation_spinner);
        blood_spinner = (Spinner) findViewById(R.id.blood_spinner);
        gender_spinner = (Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                FamilyInformation.this, R.layout.spinner_item,
                Arrays.asList(genders));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_spinner.setAdapter(adapter);
        gender_spinner.setOnItemSelectedListener(this);
        title_spinner.setOnItemSelectedListener(this);
        relation_spinner.setOnItemSelectedListener(this);
        blood_spinner.setOnItemSelectedListener(this);

        first_name_value = (EditText) findViewById(R.id.first_name_value);
        last_name_value = (EditText) findViewById(R.id.last_name_value);
        user_phone_value = (EditText) findViewById(R.id.user_phone_value);
        user_email_value = (EditText) findViewById(R.id.user_email_value);
        login_id = (CheckBox) findViewById(R.id.login_id);
        emergencyContact = (CheckBox) findViewById(R.id.emergencyContact);
        dob_value = (TextView) findViewById(R.id.dob_value);
        addMember = (Button) findViewById(R.id.save);
        addMember.setOnClickListener(this);
        dob_image = (ImageView) findViewById(R.id.dob_image);
        dob_image.setOnClickListener(this);

        if (member != null) {
            first_name_value.setText(member.getFirstName());
            last_name_value.setText(member.getLastName());
            login_id.setVisibility(View.GONE);

            if (!CommonCode.isNullOrEmpty(member.getCellNumber())) {
                if (member.isIsMemberRegisteredSucessFully()) {
                    user_phone_value.setEnabled(false);
                    user_phone_value.setFocusable(false);
                    user_phone_value.setFocusableInTouchMode(false);
                }
                user_phone_value.setText(member.getCellNumber());
            }

            if (!CommonCode.isNullOrEmpty(member.getEmail())) {
                if (member.isIsMemberRegisteredSucessFully()) {
                    user_email_value.setEnabled(false);
                    user_email_value.setFocusable(false);
                    user_email_value.setFocusableInTouchMode(false);
                }
                user_email_value.setText(member.getEmail());
            }
            // set date of birth

            int[] formatDT;
            if (!CommonCode.isNullOrEmpty(member.getDob())) {
                try {
                    formatDT = CommonCode.formatDT(member.getDob());
                    current_year = formatDT[2];
                    currentDay = formatDT[0];
                    currentMonth = formatDT[1] + 1;
                    dob_value.setText(CommonCode.monthYear(currentDay,
                            currentMonth - 1, current_year));
                    dateFormatted = currentMonth + "/" + currentDay + "/"
                            + current_year;
                    date = CommonCode.convertToMilliseconds(formatDT[2],
                            formatDT[1], formatDT[0]);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (member.isIsEmergencyContact()) {
                emergencyContact.setChecked(true);
                isEmergency = true;
                emergencyContact.setVisibility(View.VISIBLE);
            } else {
                emergencyContact.setChecked(false);
                isEmergency = false;
                emergencyContact.setVisibility(View.GONE);
            }

            if (!CommonCode.isNullOrEmpty(member.getGender())) {
                setGender(member.getGender());
            }
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        RestAdapter associationAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("relation"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        AssociationService getConsumerService = associationAdapter
                .create(AssociationService.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        getConsumerService.getMemberAssociations(
                locationManager.getUserLocation(), authToken,
                new Callback<AssociationResponse>() {

                    @Override
                    public void success(AssociationResponse arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            association = arg0.getData().getAssociation();
                        }
                        if (association.size() > 0) {
                            for (Association associationObject : association) {
                                relationSpinnerItems.add(associationObject
                                        .getAssociationName().substring(0, 1)
                                        .toUpperCase()
                                        + associationObject.getAssociationName()
                                        .substring(1));
                            }
                        }
                        // populate relation Spinner
                        populateRelationSpinner(relationSpinnerItems);
                        // Populate Title Spinner
                        setTitleInSpinner();
                        // populate blood group Spinner
                        populateBloodGroupSpinner();
                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(FamilyInformation.this))) {
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
                            AssociationResponse responseValue = gson.fromJson(
                                    json, AssociationResponse.class);
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

    // populate relation spinner method
    private void populateBloodGroupSpinner() {

        RestAdapter bloodAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("blood"))
                .setClient(new MyUrlConnectionClient())
                .setEndpoint(JeevOMUtil.baseUrl).build();
        Member getConsumerService = bloodAdapter
                .create(Member.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        getConsumerService.getBloodGroup(
                authToken,
                (new Callback<BloodGroupResponse>() {

                    @Override
                    public void success(BloodGroupResponse arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            bloodGroups = arg0.getData().getBloodGroups();
                        }
                        if (bloodGroups.size() > 0) {
                            for (BloodGroup bloodObject : bloodGroups) {
                                bloodSpinnerItems.add(bloodObject.getName());
                            }
                        }
                        if (bloodSpinnerItems.size() > 0) {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                    FamilyInformation.this,
                                    R.layout.spinner_item, bloodSpinnerItems);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            blood_spinner.setAdapter(adapter);
                        }
                        for (int i = 0; i < bloodGroups.size(); i++) {

                            if (member != null) {
                                if (bloodGroups.get(i).getId() == member
                                        .getBloodGroupType()) {
                                    blood_spinner.setSelection(i);
                                }
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError arg0) {

                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(FamilyInformation.this))) {
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
                            BloodGroupResponse responseValue = gson.fromJson(
                                    json, BloodGroupResponse.class);
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
                }));

    }

    // populate relation spinner method
    private void populateRelationSpinner(List<String> relations) {

        if (relations.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    FamilyInformation.this, R.layout.spinner_item, relations);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            relation_spinner.setAdapter(adapter);
        }

        if (member != null) {
            for (int i = 0; i < association.size(); i++) {
                if (association.get(i).getAssociationIdKey() == member
                        .getAssociationIdKey()) {
                    relation_spinner.setSelection(i);
                    break;
                }
            }
        }

    }

    // Populate Title Spinner Method
    private void setTitleInSpinner() {
        titleSpinnerItems.add("Mr.");
        titleSpinnerItems.add("Ms.");
        titleSpinnerItems.add("Mrs.");
        titleSpinnerItems.add("Dr.");

        if (titleSpinnerItems.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    FamilyInformation.this, R.layout.spinner_item,
                    titleSpinnerItems);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            title_spinner.setAdapter(adapter);
        }

        if (member != null) {
            for (int i = 0; i < titleSpinnerItems.size(); i++) {
                if (titleSpinnerItems.get(i)
                        .equalsIgnoreCase(member.getTitle())) {
                    title_spinner.setSelection(i);
                }

            }
        }
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dob_image:
                showDatePickerDialog();
                break;
            case R.id.save:

                if (CommonCode.isNullOrEmpty(first_name_value.getText().toString()
                        .trim())) {
                    Crouton.makeText(FamilyInformation.this,
                            "Please enter first name", Style.ALERT).show();
                    first_name_value.requestFocus();
                } else if (CommonCode.isNullOrEmpty(last_name_value.getText()
                        .toString().trim())) {
                    Crouton.makeText(FamilyInformation.this,
                            "Please enter last name", Style.ALERT).show();
                    last_name_value.requestFocus();
                } else if (CommonCode.isNullOrEmpty(dob_value.getText().toString()
                        .trim())) {
                    Crouton.makeText(FamilyInformation.this,
                            JeevOMUtil.MANDATORY_DATE_OF_BIRTH, Style.ALERT).show();
                } else if (validateDOB()) {
                    Crouton.makeText(FamilyInformation.this,
                            "Date Can't be Future Date", Style.ALERT).show();
                } else if (!(CommonCode.isNullOrEmpty(user_email_value.getText()
                        .toString().trim()))
                        && !CommonCode.validateEmail(user_email_value.getText()
                        .toString())) {
                    Crouton.makeText(FamilyInformation.this,
                            JeevOMUtil.VALID_EMAIL, Style.ALERT).show();

                } else if (!(CommonCode.isNullOrEmpty(user_phone_value.getText()
                        .toString().trim()))
                        && !(CommonCode.validateContactNo(user_phone_value
                        .getText().toString().trim()))) {

                    Crouton.makeText(FamilyInformation.this,
                            JeevOMUtil.VALID_Student_Id, Style.ALERT).show();

                } else if (validateEmergency()) {
                    Crouton.makeText(FamilyInformation.this,
                            JeevOMUtil.VALID_Student_Id, Style.ALERT).show();
                } else if (validateJeevomId()) {
                    Crouton.makeText(FamilyInformation.this,
                            "Enter valid email or phone number", Style.ALERT)
                            .show();
                } else {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    List<FamilyMemberDetails> details = new ArrayList<FamilyMemberDetails>();
                    int idMember;
                    if (member != null) {
                        idMember = member.getId();
                    } else {
                        idMember = 0;
                    }
                    FamilyMemberDetails memberObject = new FamilyMemberDetails(
                            String.valueOf(idMember),
                            String.valueOf(relationship_id),
                            String.valueOf(bloodValue), titleType, first_name_value
                            .getText().toString().trim(), last_name_value
                            .getText().toString().trim(), dateFormatted,
                            user_email_value.getText().toString(), user_phone_value
                            .getText().toString(), isEmergency,
                            login_id.isChecked(), gender);
                    details.add(memberObject);
                    FamilyMember memberFamily = new FamilyMember();
                    memberFamily.setValue(gson.toJson(details).toString());

                    AddFamilyMember familyMemberObject = new AddFamilyMember();
                    familyMemberObject.setId(String.valueOf(session.getMemberId()));
                    familyMemberObject.setType("Member");
                    familyMemberObject.setData(memberFamily);

                    // save family member
                    RestAdapter saveFamilyMemberAdapter = new RestAdapter.Builder()
                            .setLogLevel(LogLevel.FULL)
                            .setLog(new AndroidLog("family"))
                            .setClient(new MyUrlConnectionClient())
                            .setEndpoint(JeevOMUtil.baseUrl).build();
                    FamilyMemberSave saveMemberService = saveFamilyMemberAdapter
                            .create(FamilyMemberSave.class);
                    newFragment = ProgressDialogFragment.newInstance();
                    newFragment.show(getSupportFragmentManager(), "dialog");
                    newFragment.setCancelable(false);
                    saveMemberService.addFamilyMember(
                            locationManager.getUserLocation(), authToken,
                            familyMemberObject, new Callback<Status>() {



                                @Override
                                public void failure(RetrofitError arg0) {

                                    newFragment.dismissAllowingStateLoss();

                                    if (arg0.isNetworkError()) {
                                        if (!(Connectivity
                                                .checkConnectivity(FamilyInformation.this))) {
                                            showAlert(JeevOMUtil.INTERNET_CONNECTION);
                                        } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                            showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                                        } else if (arg0.getResponse() == null) {
                                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                                        }
                                    } else if (arg0.getResponse().getStatus() > 400) {
                                        showAlert(JeevOMUtil.SOMETHING_WRONG);
                                    } else if (arg0.getResponse().getStatus() == 400) {
                                        showAlert("A user already exists with this email id/phone no.");
                                    } else {
                                        String json = new String(
                                                ((TypedByteArray) arg0
                                                        .getResponse().getBody())
                                                        .getBytes());
                                        Gson gson = new GsonBuilder()
                                                .setPrettyPrinting().create();
                                        Status responseValue = gson.fromJson(json,
                                                Status.class);
                                        String code = responseValue.getCode();
                                        String message = responseValue.getMessage();
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
                                public void success(Status arg0, Response arg1) {
                                    newFragment.dismissAllowingStateLoss();


/*
                                    setResult(RESULT_OK);
*/
                                    HomeActivity.s=1;
                                    Intent i=new Intent(FamilyInformation.this,HomeActivity.class);

                                    startActivity(i);
                                  //  onBackPressed();

                                    finish();
                                }
                            });
                }
                break;
        }

    }

    private boolean validateEmergency() {
        if (emergencyContact.isChecked()) {
            if (CommonCode.isNullOrEmpty(user_phone_value.getText().toString()
                    .trim())) {
                return true;
            } else {
                return !CommonCode.validateContactNo(user_phone_value.getText()
                        .toString().trim());
            }
        } else {
            return false;
        }

    }

    private boolean validateJeevomId() {
        boolean value = false;
        if (login_id.isChecked()) {
            if (CommonCode.isNullOrEmpty(user_phone_value.getText().toString()
                    .trim())
                    && CommonCode.isNullOrEmpty(user_email_value.getText()
                    .toString().trim())) {
                value = true;
            } else if (!CommonCode.isNullOrEmpty(user_email_value.getText()
                    .toString().trim())) {
                if (!CommonCode.validateEmail(user_email_value.getText()
                        .toString().trim())) {
                    value = true;
                }
            } else if (!CommonCode.isNullOrEmpty(user_email_value.getText()
                    .toString().trim())) {
                if (!CommonCode.validateContactNo(user_phone_value.getText()
                        .toString().trim())) {
                    value = true;
                }
            }
        } else {
            value = false;
        }
        return value;

    }

    private void showDatePickerDialog() {
        final DatePickerDialog datePickerDialog;
        calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.YEAR);
        int year = 0, month, day;
        if (date == 0l) {
            datePickerDialog = DatePickerDialog.newInstance(
                    FamilyInformation.this, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), false);
        } else {
            calendar.setTimeInMillis(date);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = DatePickerDialog.newInstance(this, year, month,
                    day, false);
        }

        datePickerDialog.setYearRange(1902, i);
        datePickerDialog.setCloseOnSingleTapDay(false);
        datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year,
                          int month, int day) {
        current_year = year;
        currentDay = day;
        currentMonth = month + 1;
        dob_value.setText(CommonCode.monthYear(currentDay, currentMonth - 1,
                current_year));
        // dob_value.setText(currentDay + "/" + currentMonth + "/" +
        // current_year);
        dateFormatted = currentMonth + "/" + currentDay + "/" + current_year;
        date = CommonCode.convertToMilliseconds(year, month, day);
        if (!CommonCode.validateBirthDate(date)) {
            login_id.setVisibility(View.GONE);
            login_id.setChecked(false);
            emergencyContact.setVisibility(View.GONE);
            emergencyContact.setChecked(false);
        } else {
            if (member != null) {
                login_id.setVisibility(View.GONE);
            } else {
                login_id.setVisibility(View.VISIBLE);
            }
            emergencyContact.setVisibility(View.VISIBLE);
        }

    }

    // validateDateOfBirth
    public boolean validateDOB() {
        calendar = Calendar.getInstance();
        boolean value = false;
        if (current_year > calendar.get(Calendar.YEAR)) {
            value = true;
        } else if (current_year < calendar.get(Calendar.YEAR)) {
            value = false;
        } else if ((currentMonth - 1) > calendar.get(Calendar.MONTH)) {
            value = true;
        } else if ((currentMonth - 1) < calendar.get(Calendar.MONTH)) {
            value = false;
        } else if (currentDay > calendar.get(Calendar.DAY_OF_MONTH)) {
            value = true;
        } else if (currentDay <= calendar.get(Calendar.DAY_OF_MONTH)) {
            value = false;
        }
        return value;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long arg3) {
        int id = parent.getId();
        if (id == R.id.title_spinner) {
            if (titleSpinnerItems.size() > 0) {

                titleType = titleSpinnerItems.get(position);

            }
        } else if (id == R.id.blood_spinner) {
            if (bloodSpinnerItems.size() > 0) {
                for (BloodGroup blood : bloodGroups) {
                    if (blood.getName().equalsIgnoreCase(
                            bloodSpinnerItems.get(position))) {
                        bloodValue = blood.getId();
                    }

                }

            }
        } else if (id == R.id.gender_spinner) {
            if (Arrays.asList(genders).size() > 0) {
                gender = Arrays.asList(genders).get(position);
            }
        } else if (id == R.id.relation_spinner) {
            if (relationSpinnerItems.size() > 0) {
                relationshipValue = relationSpinnerItems.get(position);
                for (Association associationObject : association) {
                    if (associationObject.getAssociationName().equalsIgnoreCase(
                            relationshipValue)) {
                        relationship_id = associationObject
                                .getAssociationIdKey();
                    }
                }
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    // set Gender
    private void setGender(String gender) {
        // set gender
        if (gender.equalsIgnoreCase("male")) {
            gender_spinner.setSelection(0);
            // ((RadioButton) gender_group.getChildAt(0)).setChecked(true);

        } else if (gender.equalsIgnoreCase("female")) {
            gender_spinner.setSelection(1);
        } else {
            gender_spinner.setSelection(2);
        }
    }
}