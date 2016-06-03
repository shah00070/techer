package com.schoolteacher.appointment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolteacher.R;
import com.schoolteacher.main.BaseClass;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.service.ServiceRequisition;
import com.schoolteacher.util.JeevomUtilsClass;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class ServiceRequestsActivity extends BaseClass {
    private ProgressDialogFragment progressDialogBox;
    LinearLayout service_requests_container;
    JeevomSession session;
    String authToken = null;
    HashMap<String, ArrayList<ServiceRequisitionData>> hashMap;
    final static int RQS_1 = 1;
    UserCurrentLocationManager locationManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_requests);
        toolbar = (Toolbar) findViewById(R.id.toolbar_service_request);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.my_sr_request));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        service_requests_container = (LinearLayout) findViewById(R.id.service_requests_container);

        session = new JeevomSession(getApplicationContext());
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        getAllServiceRequestsOfConsumer();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    private void getAllServiceRequestsOfConsumer() {
        String publicId = session.getConsumerIds().get(
                JeevomSession.JEEVOM_MEMBER_UNIQUE_ID);
        RestAdapter serviceRequestAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("Appointment"))
                .setLogLevel(LogLevel.FULL).setEndpoint(JeevOMUtil.baseUrl)
                .build();
        progressDialogBox = ProgressDialogFragment.newInstance();
        progressDialogBox.show(getSupportFragmentManager(),
                "dialog");
        progressDialogBox.setCancelable(false);
        ServiceRequisition serviceRequestURL = serviceRequestAdapter
                .create(ServiceRequisition.class);

        serviceRequestURL.getAllEmailRequest(locationManager.getUserLocation(), publicId, authToken,
                new Callback<AppointmentRequestResponse>() {

                    @Override
                    public void success(AppointmentRequestResponse arg0,
                                        Response arg1) {
                        progressDialogBox.dismissAllowingStateLoss();
                        hashMap = new HashMap<String, ArrayList<ServiceRequisitionData>>();
                        List<ServiceRequisitionData> ServiceRequisitions = arg0
                                .getData().getServiceRequisitions();
                        if (arg0 != null) {
                            for (ServiceRequisitionData serviceRequisitionData : ServiceRequisitions) {
                                if (hashMap.get(serviceRequisitionData
                                        .getStatusText()) == null) {
                                    hashMap.put(
                                            serviceRequisitionData
                                                    .getStatusText(),
                                            new ArrayList<ServiceRequisitionData>());

                                }
                                hashMap.get(
                                        serviceRequisitionData.getStatusText())
                                        .add(serviceRequisitionData);

                            }
                        }

                        // show all entries to User Interface
                        showServiceRequests(hashMap);
                    }

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialogBox.dismissAllowingStateLoss();
                        Toast.makeText(getApplicationContext(), "Please Try Again..",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    protected void showServiceRequests(
            HashMap<String, ArrayList<ServiceRequisitionData>> serviceRequests) {

        // remove previous view if any available in the container
        if (service_requests_container.getChildCount() > 0) {
            service_requests_container.removeAllViews();
        }

        @SuppressWarnings("rawtypes")
        Iterator entries = serviceRequests.entrySet().iterator();

        while (entries.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            @SuppressWarnings("unchecked")
            ArrayList<ServiceRequisitionData> value = (ArrayList<ServiceRequisitionData>) entry
                    .getValue();
            // System.out.println("Key = " + key + ", Value = " + value);

            // add new header
            LayoutInflater inflator_row = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View header_view = inflator_row.inflate(
                    R.layout.service_request_status_type, null);

            TextView header_text = (TextView) header_view
                    .findViewById(R.id.service_request_status);
            header_text.setText(key);

            service_requests_container.addView(header_text);

            for (final ServiceRequisitionData serviceRequisitionData : value) {

                // set Alarm if Request is Video Consultation and payment status
                // is paid and service request status is active

                if (serviceRequisitionData.getServiceConfigurationCode()
                        .equals("SG3")
                        || serviceRequisitionData.getServiceConfigurationName()
                        .contains("Video")) {
                    int[] formatDT = null;
                    int[] formatTime = null;
                    // if (serviceRequisitionData.getStatusText()
                    // .equalsIgnoreCase("Active")
                    // && serviceRequisitionData.getPaymentStatusText()
                    // .equalsIgnoreCase("paid")) {

                    if (serviceRequisitionData.getStatusText()
                            .equalsIgnoreCase("Active")) {

                        try {
                            formatDT = CommonCode
                                    .formatDT(serviceRequisitionData
                                            .getAppointmentDate());

                            int day = formatDT[0];
                            int month = formatDT[1];
                            int year = formatDT[2];
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        try {
                            formatTime = CommonCode
                                    .formatTime(serviceRequisitionData
                                            .getAppointmentTime());
                            int hour = formatTime[0];
                            int minute = formatTime[1];
                            int second = formatTime[2];
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Calendar current = Calendar.getInstance();

                        Calendar cal = Calendar.getInstance();
                        cal.set(formatDT[2], formatDT[1], formatDT[0],
                                formatTime[0], formatTime[1] - 5, formatTime[2]);

                        if (cal.compareTo(current) <= 0) {
                            // The set Date/Time already passed
                            // Toast.makeText(ServiceRequestsActivity.this,
                            // "Invalid Date/Time", Toast.LENGTH_LONG).show();
                        } else {
                            setAlarm(cal, serviceRequisitionData);
                        }
                    }

                }

                // add service requests
                LayoutInflater inflator = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View view = inflator.inflate(R.layout.service_request_row_item,
                        null);

                ImageView sr_icon = (ImageView) view.findViewById(R.id.sr_icon);

                // set icon for service request

                switch (serviceRequisitionData.getServiceConfigurationCode()) {
                    case "SG1":
                        sr_icon.setImageResource(R.drawable.sr_inclinic);
                        break;

                    case "SG2":
                        sr_icon.setImageResource(R.drawable.sr_home);
                        break;
                    case "SG3":
                        sr_icon.setImageResource(R.drawable.sr_video_consult);
                        break;
                    case "SG4":
                        sr_icon.setImageResource(R.drawable.sr_audio);
                        break;
                    case "SG5":
                        sr_icon.setImageResource(R.drawable.sr_chat);
                        break;
                    case "SG6":
                        sr_icon.setImageResource(R.drawable.sr_email);
                        break;
                    case "SG7":
                        sr_icon.setImageResource(R.drawable.sr_adv_email);
                        break;
                    case "SG8":
                        sr_icon.setImageResource(R.drawable.sr_purchase);
                        break;
                    case "SG9":
                        sr_icon.setImageResource(R.drawable.sr_lab);
                        break;
                    case "SG10":
                        sr_icon.setImageResource(R.drawable.sr_request_quote);
                        break;
                    case "SG11":
                        sr_icon.setImageResource(R.drawable.sr_send_message);
                        break;
                    case "SG12":
                        sr_icon.setImageResource(R.drawable.sr_callback);
                        break;
                }

                // set date
                TextView date = (TextView) view.findViewById(R.id.date);

                if (!CommonCode.isNullOrEmpty(serviceRequisitionData
                        .getAppointmentDate())) {
                    String[] formattedDate = JeevomUtilsClass.formatDate(
                            serviceRequisitionData.getAppointmentDate()).split(
                            "-");
                    String dateValue = formattedDate[0] + "\n"
                            + formattedDate[1];
                    date.setText(dateValue);
                } else if (!CommonCode.isNullOrEmpty(serviceRequisitionData
                        .getCreatedOnUTC())) {
                    int[] formattedDate = null;
                    try {
                        formattedDate = CommonCode
                                .formatDT(serviceRequisitionData
                                        .getCreatedOnUTC());
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    String dateValue = CommonCode.serviceRequestDate(
                            formattedDate[0], formattedDate[1],
                            formattedDate[2]);
                    date.setText(dateValue);
                }

                // set name
                TextView doctor_name = (TextView) view
                        .findViewById(R.id.dr_name);
                String drName;
                if (serviceRequisitionData.getToProfessionalId() > 0) {
                    drName = "Dr."
                            + " "
                            + serviceRequisitionData
                            .getToProfessionalFirstName()
                            + " "
                            + serviceRequisitionData
                            .getToProfessionalLastName();
                } else {
                    drName = serviceRequisitionData.getToFacilityName();
                }
                doctor_name.setText(drName);
                String appointmentTypeName = serviceRequisitionData
                        .getServiceConfigurationName();

                TextView clinicName = (TextView) view
                        .findViewById(R.id.clinic_name);
                String appointmentTypeCode = serviceRequisitionData
                        .getServiceConfigurationCode();
                if (appointmentTypeCode.equals(AppData.SG1)) {
                    if (serviceRequisitionData.getToFacilityName() != null) {
                        clinicName.setText(serviceRequisitionData
                                .getToFacilityName());
                        clinicName.setVisibility(View.VISIBLE);
                    }
                }
                TextView appointmentType = (TextView) view
                        .findViewById(R.id.appointment_type);
                appointmentType.setText(appointmentTypeName);

                TextView status = (TextView) view.findViewById(R.id.status);
                status.setText(serviceRequisitionData.getPaymentStatusText());

                TextView time_address = (TextView) view
                        .findViewById(R.id.time_address);

                if (!CommonCode.isNullOrEmpty(serviceRequisitionData
                        .getAppointmentTime()))
                    time_address.setText(JeevomUtilsClass
                            .formatTime(serviceRequisitionData
                                    .getAppointmentTime()));

                service_requests_container.addView(view);

                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getActivity(),
                        // serviceRequisitionData.getPublicId(),
                        // Toast.LENGTH_SHORT).show();

                        viewServiceRequestDetails(serviceRequisitionData);

                    }
                });
            }

        }

    }

    protected void viewServiceRequestDetails(
            ServiceRequisitionData serviceRequisitionData) {
        Intent see_service_request_detail = new Intent(getApplicationContext(), ServiceRequestDetail.class);
        see_service_request_detail.putExtra("object", serviceRequisitionData);
        startActivity(see_service_request_detail);

    }

    private void setAlarm(Calendar targetCal,
                          ServiceRequisitionData serviceRequisitionData) {

        Intent intent = new Intent(getBaseContext(),
                AlarmReceiver.class);

        intent.putExtra("service_request_object", serviceRequisitionData);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

        // Toast.makeText(getActivity(), "Alarm is set@ " + targetCal.getTime(),
        // Toast.LENGTH_LONG).show();

    }
}
