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

/**
 * Created by user on 9/23/2015.
 */
public class AddVitalReading extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    DialogFragment progressDialog;
    JeevomSession session;
    private Toolbar toolbar_reading_activity;
    HealthIndicator healthIndicator;
    Element element;
    StringBuilder datee = null;
    StringBuilder Timee = null;
    StringBuilder merg = null;
    String vital_name, vital_name_second;
    List<String> unitDropDownValues;
    Spinner reading_unit;
    int measurementUnitId;
    TextView reading_text, reading_text_second;
    Button date_button, time_button, add_button;
    EditText reading_value, reading_comment_value, reading_value_second;
    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;

    String date, time;
    StringBuilder dateTime = null;
    int indicatorElementId, indicatorElementIdSecond;
    private ArrayList<IndicatorReading> elementList;
    private boolean isTimeCalled = false, isDateCalled = false;
    int selectedYear, selectedMonth, selectedDay;
    private int defaultUnitId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_health_vital_reading);

        Intent intent = getIntent();
        healthIndicator = (HealthIndicator) intent.getSerializableExtra("elements");
        elementList = new ArrayList<>();
        session = new JeevomSession(getApplicationContext());
        dateTime = new StringBuilder();
        reading_unit = (Spinner) findViewById(R.id.reading_unit);
        reading_unit.setOnItemSelectedListener(this);
        reading_text = (TextView) findViewById(R.id.reading_text);
        date_button = (Button) findViewById(R.id.date_button);
        time_button = (Button) findViewById(R.id.time_button);
        add_button = (Button) findViewById(R.id.add_button);
        add_button.setOnClickListener(this);
        reading_value = (EditText) findViewById(R.id.reading_value_cms);



        datee = new StringBuilder();
        Timee = new StringBuilder();
        merg = new StringBuilder();
        reading_text_second = (TextView) findViewById(R.id.reading_text_second);
        reading_value_second = (EditText) findViewById(R.id.reading_value_second);

        if (healthIndicator.getName().trim().equalsIgnoreCase(getResources().getString(R.string.blood_pressure))) {
            reading_text_second.setVisibility(View.VISIBLE);
            reading_value_second.setVisibility(View.VISIBLE);
        } else {
            reading_text_second.setVisibility(View.GONE);
            reading_value_second.setVisibility(View.GONE);
        }

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
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                isDateCalled = false;

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(AddVitalReading.this,
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
                TimePickerDialog tpd = new TimePickerDialog(AddVitalReading.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                if (!isTimeCalled) {

                                    Timee.delete(0, Timee.length());
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

        time_button.setText(time);
        unitDropDownValues = new ArrayList<>();

        if (healthIndicator.getElements().size() > 0) {
            element = healthIndicator.getElements().get(0);
            indicatorElementId = element.getId();
            vital_name = element.getName();

            if (healthIndicator.getName().trim().equalsIgnoreCase(getResources().getString(R.string.blood_pressure))) {
                element = healthIndicator.getElements().get(1);
                indicatorElementIdSecond = element.getId();
                vital_name_second = element.getName();
            }

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
                AddVitalReading.this, R.layout.spinner_item, unitDropDownValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reading_unit.setAdapter(adapter);
    }

    private void setUpActionBar() {
        toolbar_reading_activity = (Toolbar) findViewById(R.id.toolbar_reading_activity);
        setSupportActionBar(toolbar_reading_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(healthIndicator.getName());
        reading_text.setText(vital_name);
        if (reading_text_second.getVisibility() == View.VISIBLE)
            reading_text_second.setText(vital_name_second);
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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if (CommonCode.isNullOrEmpty(date)) {
            Crouton.makeText(AddVitalReading.this, "Please Select Date", Style.ALERT)
                    .show();
        } else if (CommonCode.checkForFutureDate(selectedYear, selectedMonth, selectedDay)) {
            Crouton.makeText(AddVitalReading.this, "Reading Can't be Recorded with Future Date", Style.ALERT)
                    .show();

        } else if (CommonCode.isNullOrEmpty(time)) {
            Crouton.makeText(AddVitalReading.this, "Please Select Time", Style.ALERT)
                    .show();
        } else if (CommonCode.isNullOrEmpty(reading_value.getText().toString())) {
            Crouton.makeText(AddVitalReading.this, "Please Enter Reading Value", Style.ALERT)
                    .show();
        } else if (healthIndicator.getName().equalsIgnoreCase(getResources().getString(R.string.blood_pressure)) && CommonCode.isNullOrEmpty(reading_value_second.getText().toString())) {
            Crouton.makeText(AddVitalReading.this, "Please Enter Reading Value", Style.ALERT)
                    .show();
        } else {
            elementList.clear();
            IndicatorReading readingObject = new IndicatorReading();
            readingObject.setIndicatorElementId(indicatorElementId);
            double valueInDefaultUnit = getValueInDefaultUnit(Double.valueOf(reading_value.getText().toString()));
            readingObject.setMeasuredValue(valueInDefaultUnit);
            readingObject.setMeasurementUnitId(defaultUnitId);

            elementList.add(readingObject);

            if (healthIndicator.getName().equalsIgnoreCase(getResources().getString(R.string.blood_pressure))) {
                IndicatorReading readingObjectSecond = new IndicatorReading();
                readingObjectSecond.setIndicatorElementId(indicatorElementIdSecond);
                double valueInDefaultUnitSecond = getValueInDefaultUnit(Double.valueOf(reading_value_second.getText().toString()));
                readingObjectSecond.setMeasuredValue(valueInDefaultUnitSecond);
                readingObjectSecond.setMeasurementUnitId(defaultUnitId);
                elementList.add(readingObjectSecond);
            }



            merg.append(datee);
            merg.append(Timee);
            RecordHealthVitalModel model = new RecordHealthVitalModel();
            model.setComments(reading_comment_value.getText().toString());
            model.setElements(elementList);
            model.setIndicatorId(healthIndicator.getId());
            model.setMeasuredOn(merg.toString());
            model.setMemberId(session.getMemberId());
           // Toast.makeText(AddVitalReading.this,merg.toString(), Toast.LENGTH_SHORT).show();

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
                Toast.makeText(AddVitalReading.this, "Please try again..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public double getValueInDefaultUnit(double value) {
        double convertedValue;
        if (measurementUnitId != defaultUnitId) {
            String measuredUnitName = null, defaultUnitName = null;
            for (MeasurementUnit object : element.getMeasurementUnits()) {
                if (object.getId() == measurementUnitId) {
                    measuredUnitName = object.getName();
                }
                if (object.getId() == defaultUnitId) {
                    defaultUnitName = object.getName();
                }
            }
            convertedValue = getConvertedValue(value, measuredUnitName, defaultUnitName);
            return convertedValue;
        } else {
            return value;
        }
    }

    public double getConvertedValue(double inputValue, String fromUnit, String toUnit) {
        double outputValue = 0.0;
        if (fromUnit.equalsIgnoreCase(getResources().getString(R.string.lbs)) && toUnit.equalsIgnoreCase(getResources().getString(R.string.kg))) {
            outputValue = inputValue / 2.20;
        } else if (fromUnit.trim().equalsIgnoreCase(getResources().getString(R.string.fahrenheit)) && toUnit.trim().equalsIgnoreCase(getResources().getString(R.string.celsius))) {
            outputValue = (inputValue - 32) * 5 / 9;
        }
        return Math.round(outputValue);
    }

}
