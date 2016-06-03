package com.schoolteacher.healthvitals;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.schoolteacher.R;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.Element;
import com.schoolteacher.pojos.HealthIndicator;
import com.schoolteacher.pojos.IndicatorReading;
import com.schoolteacher.pojos.MeasurementUnit;
import com.schoolteacher.pojos.RecordHealthVitalModel;
import com.schoolteacher.service.HealthVitalInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class AddHeightIndicatorValue extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    DialogFragment progressDialog;
    JeevomSession session;
    private Toolbar toolbar_reading_activity;
    HealthIndicator healthIndicator;
    Element element;
    String vital_name;
    DatePickerDialog dpd;
    List<String> unitDropDownValues;
    Spinner reading_unit;
    int measurementUnitId;
    TextView reading_text;
    Button date_button, time_button, add_button;
    EditText reading_value_cms, reading_comment_value, reading_value_ft, reading_value_inch;
    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;
    TimePickerDialog tpd;
    String date, time;
    StringBuilder datee = null;
    StringBuilder Timee = null;
    StringBuilder merg = null;
    int indicatorElementId;
    private ArrayList<IndicatorReading> elementList;
    private boolean isTimeCalled = false, isDateCalled = false;
    int selectedYear, selectedMonth, selectedDay;
    private long dateValue;

    private boolean isFtInch = false;
    private int defaultUnitId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_health_vital_reading);
        Intent intent = getIntent();
        healthIndicator = (HealthIndicator) intent.getSerializableExtra("elements");
        elementList = new ArrayList<>();
        session = new JeevomSession(getApplicationContext());
        datee = new StringBuilder();
        Timee = new StringBuilder();
        merg = new StringBuilder();
        reading_unit = (Spinner) findViewById(R.id.reading_unit);
        reading_unit.setOnItemSelectedListener(this);


        reading_text = (TextView) findViewById(R.id.reading_text);
        date_button = (Button) findViewById(R.id.date_button);
        time_button = (Button) findViewById(R.id.time_button);
        add_button = (Button) findViewById(R.id.add_button);

        add_button.setOnClickListener(this);
        reading_value_cms = (EditText) findViewById(R.id.reading_value_cms);
        reading_value_ft = (EditText) findViewById(R.id.reading_value_ft);
        reading_value_inch = (EditText) findViewById(R.id.reading_value_inch);
        reading_comment_value = (EditText) findViewById(R.id.reading_comment_value);


        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        date = (mMonth+1) + "/"
                +(mDay )  + "/" + mYear;

        isDateCalled = false;
        datee.append((mMonth + 1) + "/"
                + mDay + "/" + mYear);

        selectedYear = mYear;
        selectedMonth = mMonth + 1;
        selectedDay =mDay;
        date_button.setText(date);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Process to get Current Date


                // Launch Date Picker Dialog
                dpd = new DatePickerDialog(AddHeightIndicatorValue.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if (!isDateCalled) {

                                    datee.delete(0,datee.length());
                                    date = (monthOfYear + 1) + "/"
                                            + dayOfMonth + "/" + year;
                                    date_button.setText(date);

                                    datee.append((monthOfYear + 1) + "/"
                                            + dayOfMonth + "/" + year);
                                    isDateCalled = true;
                                }
                                selectedYear = year;
                                selectedMonth = monthOfYear + 1;
                                selectedDay = dayOfMonth;
                                //dateValue = CommonCode.convertToMilliseconds(year, monthOfYear, dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });




        final Calendar cc = Calendar.getInstance();
        mHour = cc.get(Calendar.HOUR_OF_DAY);
        mMinute = cc.get(Calendar.MINUTE);
        isTimeCalled = false;
        time = mHour + ":" + mMinute;
        Timee.append(" " + mHour + ":" + mMinute);
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Process to get Current Time

                // Launch Time Picker Dialog
                tpd = new TimePickerDialog(AddHeightIndicatorValue.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                if (!isTimeCalled) {

                                    Timee.delete(0,Timee.length());
                                    time = hourOfDay + ":" + minute;
                                    time_button.setText(time);
                                    Timee.append(" " + hourOfDay + ":" + minute);
                                    isTimeCalled = true;
                                }

                            }
                        }, mHour, mMinute, false);
                tpd.show();
            }
        });


       // time_button.setText(time = mHour + ":" + mMinute);
        time_button.setText(time);


      /*  SimpleTimeFormat sdff = new SimpleDateFormat( "yyyy/MM/dd" );
        time_button.setText(sdff.format(new Date()));
*/
        unitDropDownValues = new ArrayList<>();

        if (healthIndicator.getElements().size() > 0) {
            element = healthIndicator.getElements().get(0);
            indicatorElementId = element.getId();
            vital_name = element.getName();

            if (element.getMeasurementUnits().size() > 0) {
                for (MeasurementUnit object : element.getMeasurementUnits()) {
                    unitDropDownValues.add(object.getName());
                    if (object.isDefault()) {
                        defaultUnitId = object.getId();
                    }
                }
            }
        }
        // set action bar
        setUpActionBar();

        fillReadingUnitSpinner();
    }


    private void fillReadingUnitSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                AddHeightIndicatorValue.this, R.layout.spinner_item, unitDropDownValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reading_unit.setAdapter(adapter);
    }

    private void setUpActionBar() {
        toolbar_reading_activity = (Toolbar) findViewById(R.id.toolbar_reading_activity);
        setSupportActionBar(toolbar_reading_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(vital_name);

        // set value for reading text
        reading_text.setText(vital_name);

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (healthIndicator.getElements().size() > 0) {
            element = healthIndicator.getElements().get(0);
            vital_name = element.getName();

            if (element.getMeasurementUnits().size() > 0) {
                measurementUnitId = element.getMeasurementUnits().get(position).getId();

            }
        }

        if (element.getMeasurementUnits().get(position).getName().contains("ft.")) {
            reading_value_ft.setVisibility(View.VISIBLE);
            reading_value_inch.setVisibility(View.VISIBLE);

            reading_value_cms.setVisibility(View.GONE);

            isFtInch = true;

        } else {
            reading_value_ft.setVisibility(View.GONE);
            reading_value_inch.setVisibility(View.GONE);
            reading_value_cms.setVisibility(View.VISIBLE);

            isFtInch = false;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if (CommonCode.isNullOrEmpty(date)) {
            Crouton.makeText(AddHeightIndicatorValue.this, "Please Select Date", Style.ALERT)
                    .show();
        } else if (CommonCode.checkForFutureDate(selectedYear, selectedMonth, selectedDay)) {
            Crouton.makeText(AddHeightIndicatorValue.this, "Reading Can't be Recorded with Future Date", Style.ALERT)
                    .show();

        } else if (CommonCode.isNullOrEmpty(time)) {
            Crouton.makeText(AddHeightIndicatorValue.this, "Please Select Time", Style.ALERT)
                    .show();
        } else if (!isFtInch && CommonCode.isNullOrEmpty(reading_value_cms.getText().toString())) {
            Crouton.makeText(AddHeightIndicatorValue.this, "Please Enter Reading Value", Style.ALERT)
                    .show();
        } else if (isFtInch && CommonCode.isNullOrEmpty(reading_value_ft.getText().toString())) {
            Crouton.makeText(AddHeightIndicatorValue.this, "Please Enter Reading Value in FT.", Style.ALERT)
                    .show();

        } else if (isFtInch && CommonCode.isNullOrEmpty(reading_value_inch.getText().toString())) {
            Crouton.makeText(AddHeightIndicatorValue.this, "Please Enter Reading Value in inches.", Style.ALERT)
                    .show();

        } else {

            IndicatorReading readingObject = new IndicatorReading();
            readingObject.setIndicatorElementId(indicatorElementId);

            if (!isFtInch) {
                readingObject.setMeasuredValue(Double.valueOf(reading_value_cms.getText().toString()));
            } else {
                double footToInch = Double.valueOf(reading_value_ft.getText().toString()) * 12;
                double cmsValue = (footToInch + Double.valueOf(reading_value_inch.getText().toString())) * 2.54;
                readingObject.setMeasuredValue(cmsValue);
            }
            readingObject.setMeasurementUnitId(defaultUnitId);
            elementList.add(readingObject);


            merg.append(datee);
            merg.append(Timee);
            RecordHealthVitalModel model = new RecordHealthVitalModel();
            model.setComments(reading_comment_value.getText().toString());
            model.setElements(elementList);
            model.setIndicatorId(healthIndicator.getId());
            model.setMeasuredOn(merg.toString());
            model.setMemberId(session.getMemberId());
        //    Toast.makeText(AddHeightIndicatorValue.this,merg.toString(), Toast.LENGTH_SHORT).show();

            saveVitalReading(model);
        }
    }

    private void saveVitalReading(RecordHealthVitalModel model) {
        RestAdapter adapter = new RestAdapter.Builder().setLog(new AndroidLog("addvital")).setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(
                JeevOMUtil.baseUrl).build();
        HealthVitalInterface readingValueSave = adapter.create(HealthVitalInterface.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);

        readingValueSave.saveIndicatorReading(model, new Callback<ApiResponse<Boolean>>() {
            @Override
            public void success(ApiResponse<Boolean> booleanApiResponse, Response response) {
                progressDialog.dismissAllowingStateLoss();
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismissAllowingStateLoss();
                Toast.makeText(AddHeightIndicatorValue.this, "Please try again..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
