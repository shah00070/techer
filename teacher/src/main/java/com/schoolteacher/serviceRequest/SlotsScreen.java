package com.schoolteacher.serviceRequest;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.adapters.AppointmentSlotsAdapter;
import com.schoolteacher.dialog.DateDialog;
import com.schoolteacher.dialog.GooglePlayAlert;
import com.schoolteacher.main.ExpandableHeightGridView;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.JeevSession;
import com.schoolteacher.pojos.JeevSlot;
import com.schoolteacher.pojos.JeevSlotsResult;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.service.ServiceRequisition;

import java.net.SocketTimeoutException;
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

public class SlotsScreen extends ActionBarActivity implements
        OnDateSetListener, OnClickListener {
    int selectedYear, selectedMonth, selectedDay;
    // date time fields
    RelativeLayout time_date;
    int timesCalled = 0;
    TextView select_date, noSlots;
    UserCurrentLocationManager locationManager;
    Gson gson;


    int current_year, currentMonth, currentDay;
    int currentYear, currenMonth, currenDay;
    LinearLayout slots_container;

    Calendar calendar;
    long date = 0l;

    GlobalAlert globalAlert;
    GlobelAlertWithFinish globalAlertFinish;
    GooglePlayAlert googleAlert;
    private JeevomSession session;

    String profileId, service_requestor, serviceConfig, selectedFacilityId,
            authToken, selectedAppointmentTypeValue;
    DialogFragment newFragment;
    List<JeevSession> sessions;

    Toolbar toolbar;
    String bookedTime, slotName;
    int slotId, sessionId;

    private boolean isFacilitySlots = false, isProfessionalSlots = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slots_screen);
        Intent intent = getIntent();
        gson = new GsonBuilder().create();
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_slots);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Slot");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // alerts
        globalAlert = new GlobalAlert(this);
        globalAlertFinish = new GlobelAlertWithFinish(this);
        globalAlertFinish.setCancelable(false);
        googleAlert = new GooglePlayAlert(this);

        // sessions
        session = new JeevomSession(getApplicationContext());
        calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH) + 1;
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        currenMonth = calendar.get(Calendar.MONTH);
        currenDay = calendar.get(Calendar.DAY_OF_MONTH);

        // 1. date and time of appointment
        select_date = (TextView) findViewById(R.id.select_date);
        select_date.setText((currenMonth + 1) + "/" + currenDay + "/"
                + currentYear);
        time_date = (RelativeLayout) findViewById(R.id.time_date);
        time_date.setOnClickListener(this);
        // 1. end

        slots_container = (LinearLayout) findViewById(R.id.slots_container);

        noSlots = (TextView) findViewById(R.id.noSlots);

        profileId = intent.getStringExtra("profileId");
        service_requestor = intent.getStringExtra("service_requestor");
        serviceConfig = intent.getStringExtra("serviceConfig");
        selectedFacilityId = intent.getStringExtra("selectedFacilityId");
        authToken = intent.getStringExtra("authToken");
        selectedAppointmentTypeValue = authToken = intent
                .getStringExtra("selectedAppointmentTypeValue");

        if (service_requestor.equalsIgnoreCase("professional")) {
            getSlots();
        } else {
            getFacilitySlots();
        }

    }

    @Override
    public void onDateSet(DatePicker view, int yearValue, int monthOfYear,
                          int dayOfMonth) {

        select_date.setText((monthOfYear + 1) + "/" + dayOfMonth + "/"
                + yearValue);
        selectedDay = dayOfMonth;
        selectedMonth = monthOfYear + 1;
        selectedYear = yearValue;

        currentYear = yearValue;
        currenMonth = monthOfYear;
        currenDay = dayOfMonth;
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
            if (service_requestor.equalsIgnoreCase("professional")) {
                getSlots();
            } else {
                getFacilitySlots();
            }
        } else {
            timesCalled += 1;
            if ((timesCalled % 2) == 0) {
                if (service_requestor.equalsIgnoreCase("professional")) {
                    getSlots();
                } else {
                    getFacilitySlots();
                }
                timesCalled = 0;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_date:
                Bundle args = new Bundle();
                args.putInt("year", currentYear);
                args.putInt("month", currenMonth);
                args.putInt("day", currenDay);
                DateDialog dateDialog = new DateDialog();
                dateDialog.setArguments(args);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(dateDialog, "date_picker");
                ft.commit();

                break;
        }

    }

    private void getFacilitySlots() {
        isFacilitySlots = true;
        isProfessionalSlots = false;
        RestAdapter adapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("slots")).setLogLevel(LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition slot_interface = adapter.create(ServiceRequisition.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);

        slot_interface.getSlots(gson.toJson(locationManager.getUserLocation()).toString(), authToken, profileId.trim(), selectedMonth
                        + "/" + selectedDay + "/" + selectedYear, true, serviceConfig,
                selectedFacilityId,

                new Callback<JeevSlotsResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(SlotsScreen.this))) {
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
                            Gson gson = new GsonBuilder().setPrettyPrinting()
                                    .create();
                            SearchResultsResponse searchResultsResponse = gson
                                    .fromJson(json, SearchResultsResponse.class);
                            String code = searchResultsResponse.getStatus()
                                    .getCode();
                            String message = searchResultsResponse.getStatus()
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

                    @Override
                    public void success(JeevSlotsResult arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            sessions = arg0.getData().getSessions();

                            setSlots(sessions);
                        }
                    }
                });

    }

    private void getSlots() {
        isFacilitySlots = false;
        isProfessionalSlots = true;
        RestAdapter adapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("slots")).setLogLevel(LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition slot_interface = adapter.create(ServiceRequisition.class);
        newFragment = ProgressDialogFragment.newInstance();
        newFragment.show(getSupportFragmentManager(), "dialog");
        newFragment.setCancelable(false);
        if (!(serviceConfig.equals("SG1"))) {
            selectedFacilityId = null;
        }
        slot_interface.getProfessionalSlots(gson.toJson(locationManager.getUserLocation()).toString(), authToken, profileId.trim(), selectedMonth
                        + "/" + selectedDay + "/" + selectedYear, true, serviceConfig,
                selectedFacilityId,

                new Callback<JeevSlotsResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        newFragment.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(SlotsScreen.this))) {
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
                            Gson gson = new GsonBuilder().setPrettyPrinting()
                                    .create();
                            SearchResultsResponse searchResultsResponse = gson
                                    .fromJson(json, SearchResultsResponse.class);
                            String code = searchResultsResponse.getStatus()
                                    .getCode();
                            String message = searchResultsResponse.getStatus()
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

                    @Override
                    public void success(JeevSlotsResult arg0, Response arg1) {
                        newFragment.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {
                            sessions = arg0.getData().getSessions();
                            setSlots(sessions);
                        }
                    }
                });

    }

    protected void setSlots(List<JeevSession> sessionValues) {

        slots_container.removeAllViews();

        if (sessionValues != null && sessionValues.size() > 0) {
            slots_container.setVisibility(View.VISIBLE);
            noSlots.setVisibility(View.GONE);

            for (int i = 0; i < sessionValues.size(); i++) {
                final List<JeevSlot> slots = sessionValues.get(i).getSlots();
                sessionId = sessionValues.get(i).getId();
                LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflator.inflate(R.layout.slots_layout, null);

                TextView session_name = (TextView) view
                        .findViewById(R.id.session_name);
                final ExpandableHeightGridView gridView = (ExpandableHeightGridView) view
                        .findViewById(R.id.gridview);
                gridView.setExpanded(true);
                session_name.setText(sessionValues.get(i).getName() + " Slots");

                AppointmentSlotsAdapter adapter = null;

                adapter = new AppointmentSlotsAdapter(this, slots, currentYear,
                        currenDay, currenMonth);

                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        View selected = gridView.getChildAt(position);
                        CheckedTextView selectedCheck = (CheckedTextView) selected
                                .findViewById(R.id.checkedTextView1);

                        if (selectedCheck.isEnabled()) {
                            if (!selectedCheck.isChecked()) {
                                selectedCheck.setChecked(true);
                                slotName = selectedCheck.getText().toString();
                                bookedTime = (String) selectedCheck.getTag();

                                for (JeevSlot jeevSlot : slots) {

                                    if (jeevSlot.getFromTime().equals(
                                            bookedTime)) {
                                        slotId = jeevSlot.getId();
                                        break;
                                    }
                                }

                                goToAppointmentScreen();
                            }
                        } else {
                            Crouton.makeText(SlotsScreen.this,
                                    "Slot not available", Style.ALERT).show();
                        }

                    }
                });
                slots_container.addView(view);
            }

        } else {
            slots_container.setVisibility(View.GONE);
            noSlots.setVisibility(View.VISIBLE);
        }
    }

    protected void goToAppointmentScreen() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("slotId", slotId);
        resultIntent.putExtra("sessionId", sessionId);
        resultIntent.putExtra("bookedTime", bookedTime);
        resultIntent.putExtra("date", select_date.getText().toString());
        resultIntent.putExtra("slotName", slotName);
        setResult(100, resultIntent);
        finish();
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

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Applying Exit Animation;
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

    private void showGooglePlayAlert(String message) {
        googleAlert.show();
        googleAlert.setMessage(message);
    }

}
