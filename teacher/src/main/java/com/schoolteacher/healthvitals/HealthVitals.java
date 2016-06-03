package com.schoolteacher.healthvitals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.main.FloatingActionButton;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.Element2;
import com.schoolteacher.pojos.HealthVitalTag;
import com.schoolteacher.pojos.Measurement;
import com.schoolteacher.pojos.MemberHealthCardsData;
import com.schoolteacher.service.HealthVitalInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class HealthVitals extends AppCompatActivity {
    private ProgressDialogFragment progressDialog;
    private Toolbar toolbar_healthvitals;
    private FloatingActionButton fabButton;
    JeevomSession jeevomSession;
    private LinearLayout graph_container;
    List<MemberHealthCardsData> healthCardList;
    UserCurrentLocationManager locationManager;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_health_vitals);
        gson = new GsonBuilder().create();
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        // set action bar
        setUpActionBar();
        jeevomSession = new JeevomSession(getApplicationContext());
        graph_container = (LinearLayout) findViewById(R.id.graph_container);
        // floting action button
        fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(
                        getResources().getDrawable(R.drawable.ic_action_new))
                .withButtonColor(getResources().getColor(R.color.colorOrange))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16).create();
        fabButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent edit_profile = new Intent(HealthVitals.this,
                        AddHealthVital.class);
                startActivity(edit_profile);
                overridePendingTransition(R.anim.trans_left_in,
                        R.anim.trans_left_exit);

            }
        });
    }

    private void setUpActionBar() {
        toolbar_healthvitals = (Toolbar) findViewById(R.id.toolbar_healthvitals);
        setSupportActionBar(toolbar_healthvitals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.health_analyzer));

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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get Activated health indicators
        RestAdapter healthIndicatorsAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("healthcards")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        HealthVitalInterface healthVitals = healthIndicatorsAdapter
                .create(HealthVitalInterface.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(this.getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);

        healthVitals.getMemberAvailableHealthCards(gson.toJson(locationManager.getUserLocation()).toString(), jeevomSession.getMemberId(), new Callback<ApiResponse<List<MemberHealthCardsData>>>() {
            @Override
            public void success(ApiResponse<List<MemberHealthCardsData>> listApiResponse, Response response) {
                progressDialog.dismissAllowingStateLoss();
                healthCardList = listApiResponse.getData();
                List<Element2> elements = null;

                //remove if container has any other view
                if (graph_container.getChildCount() > 0) {
                    graph_container.removeAllViews();
                }


                for (MemberHealthCardsData object :
                        healthCardList) {

                    ArrayList<String> listOfMeasurements = new ArrayList<String>();

                    List<Measurement> measurementList = object.getMeasurement();

                    for (Measurement measurementObject :
                            measurementList) {
                        elements = measurementObject.getElements();


                        for (Element2 elementObject : elements) {
                            listOfMeasurements.add(elementObject.getMeasuredValue());
                        }
                    }


                    // code to show rows

                    LayoutInflater inflator = (LayoutInflater) HealthVitals.this
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    // boolean added = false;
                    View view = inflator.inflate(R.layout.health_vital_row,
                            null);

                    ImageView icon_vital = (ImageView) view.findViewById(R.id.icon_vital);

                    TextView indicator_name = (TextView) view.findViewById(R.id.indicator_name);
                    final ImageView add_new_reading = (ImageView) view.findViewById(R.id.add_new_reading);
                    add_new_reading.setTag(object.getHealthIndicator());

                    final ImageView view_health_vital_info = (ImageView) view.findViewById(R.id.view_health_vital_info);
                    view_health_vital_info.setTag(new HealthVitalTag(listOfMeasurements, object.getHealthIndicator().getName(), (ArrayList<Measurement>) measurementList));

                    indicator_name.setText(object.getHealthIndicator().getName());
//                    name = object.getHealthIndicator().getName();


                    if (object.getHealthIndicator().getName().equalsIgnoreCase("CHOLESTEROL"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.cholestrol));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("TRIGLYCERIDES"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.triglycerdies));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("HDL"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.hdl));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("LDL"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.ldl));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("TEST"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.test));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("TSH"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.tsh));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("T3"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.t3));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("T4"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.t4));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("BLOOD PRESSURE"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.blood_pressure));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("TEMPRATURE"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.temprature));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("WEIGHT"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.weight_vital));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("HEIGHT"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.height_vital));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("BLOOD SUGAR"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.blood_sugar));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("HAEMOGLOBIN"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.heamoglobin));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("IRON"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.iron));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("URIC ACID"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.uric_acid));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("CALCIUM"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.calcium));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("VITAMIN D"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.vitamin_d));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("VITAMIN B-12"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.b_12));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("AVERAGE BLOOD GLUCOSE"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.blood_sugar));
                    if (object.getHealthIndicator().getName().equalsIgnoreCase("THYROID"))
                        icon_vital.setImageDrawable(getResources().getDrawable(R.drawable.thyroid));


                    add_new_reading.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (((HealthVitalTag) view_health_vital_info.getTag()).getName().equalsIgnoreCase("height")) {
                                Intent view_health_vital_intent = new Intent(HealthVitals.this, AddHeightIndicatorValue.class);
                                view_health_vital_intent.putExtra("elements", (Serializable) (add_new_reading.getTag()));
                                startActivity(view_health_vital_intent);
                            }
//                            else if (((HealthVitalTag) view_health_vital_info.getTag()).getName().equalsIgnoreCase("blood pressure")) {
//
//                            }
                            else {
                                Intent view_health_vital_intent = new Intent(HealthVitals.this, AddVitalReading.class);
                                view_health_vital_intent.putExtra("elements", (Serializable) (add_new_reading.getTag()));
                                startActivity(view_health_vital_intent);
                            }
                        }
                    });

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent view_health_vital_intent = new Intent(HealthVitals.this, ViewHealthVitalInformation.class);
                            view_health_vital_intent.putStringArrayListExtra("indicatorsValue", ((HealthVitalTag) view_health_vital_info.getTag()).getListOfMeasurements());
                            view_health_vital_intent.putExtra("elements", ((HealthVitalTag) view_health_vital_info.getTag()).getElements());
                            view_health_vital_intent.putExtra("header_name", ((HealthVitalTag) view_health_vital_info.getTag()).getName());
                            startActivity(view_health_vital_intent);
                        }
                    });

                    graph_container.addView(view);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismissAllowingStateLoss();
                Toast.makeText(HealthVitals.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
