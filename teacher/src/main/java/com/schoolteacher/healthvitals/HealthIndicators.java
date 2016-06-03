package com.schoolteacher.healthvitals;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.HealthIndicator;
import com.schoolteacher.pojos.MemberHealthCardsData;
import com.schoolteacher.service.HealthVitalInterface;
import com.schoolteacher.util.JeevomUtilsClass;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class HealthIndicators extends AppCompatActivity {
    private List<HealthIndicator> healthIndicators;
    private Toolbar toolbar_add_healthindicators;
    private LinearLayout health_indicator_container;
    JeevomSession jeevomSession;
    private ProgressDialogFragment progressDialog;
    List<MemberHealthCardsData> healthCardList;
    UserCurrentLocationManager locationManager;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_indicators);
        jeevomSession = new JeevomSession(getApplicationContext());
        health_indicator_container = (LinearLayout) findViewById(R.id.health_indicator_container);
        healthIndicators = (List<HealthIndicator>) getIntent().getSerializableExtra("healthIndicators");
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        gson = new GsonBuilder().create();
        // set action bar
        setUpActionBar();
    }

    private void setUpActionBar() {
        toolbar_add_healthindicators = (Toolbar) findViewById(R.id.toolbar_add_healthindicators);
        setSupportActionBar(toolbar_add_healthindicators);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Health Indicators");

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

                // if (AddHealthVital.activatedIndicators.size() > 0) {
                //   AddHealthVital.activatedIndicators.clear();
                // }
                // add all activated indicators to list
                if (JeevomUtilsClass.isFreshLoad) {
                    JeevomUtilsClass.isFreshLoad = false;
                    for (MemberHealthCardsData card : healthCardList) {

                        if (!AddHealthVital.activatedIndicators.contains(card.getHealthIndicator().getId()))
                            AddHealthVital.activatedIndicators.add(card.getHealthIndicator().getId());
                    }
                }
                showIndicators();
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismissAllowingStateLoss();
                Toast.makeText(HealthIndicators.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showIndicators() {

        // show indicators on view

        if (health_indicator_container.getChildCount() > 0) {
            health_indicator_container.removeAllViews();
        }

        if (healthIndicators.size() > 0) {

            for (final HealthIndicator object : healthIndicators) {
                LayoutInflater inflator = (LayoutInflater) HealthIndicators.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // boolean added = false;
                View view = inflator.inflate(R.layout.health_indicator,
                        null);
                final TextView health_indicator_name = (TextView) view.findViewById(R.id.health_indicator_name);
                health_indicator_name.setText(object.getName());
                final Switch indicator_toggle = (Switch) view.findViewById(R.id.indicator_toggle);
                ImageView icon_indicator = (ImageView) view.findViewById(R.id.icon_indicator);
                if (object.getName().equalsIgnoreCase("CHOLESTEROL"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.cholestrol));
                if (object.getName().equalsIgnoreCase("TRIGLYCERIDES"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.triglycerdies));
                if (object.getName().equalsIgnoreCase("HDL"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.hdl));
                if (object.getName().equalsIgnoreCase("LDL"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.ldl));
                if (object.getName().equalsIgnoreCase("TEST"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.test));
                if (object.getName().equalsIgnoreCase("TSH"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.tsh));
                if (object.getName().equalsIgnoreCase("T3"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.t3));
                if (object.getName().equalsIgnoreCase("T4"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.t4));
                if (object.getName().equalsIgnoreCase("BLOOD PRESSURE"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.blood_pressure));
                if (object.getName().equalsIgnoreCase("TEMPRATURE"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.temprature));
                if (object.getName().equalsIgnoreCase("WEIGHT"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.weight_vital));
                if (object.getName().equalsIgnoreCase("HEIGHT"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.height_vital));
                if (object.getName().equalsIgnoreCase("BLOOD SUGAR"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.blood_sugar));
                if (object.getName().equalsIgnoreCase("HAEMOGLOBIN"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.heamoglobin));
                if (object.getName().equalsIgnoreCase("IRON"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.iron));
                if (object.getName().equalsIgnoreCase("URIC ACID"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.uric_acid));
                if (object.getName().equalsIgnoreCase("CALCIUM"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.calcium));
                if (object.getName().equalsIgnoreCase("VITAMIN D"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.vitamin_d));
                if (object.getName().equalsIgnoreCase("VITAMIN B-12"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.b_12));
                if (object.getName().equalsIgnoreCase("AVERAGE BLOOD GLUCOSE"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.blood_sugar));
                if (object.getName().equalsIgnoreCase("THYROID"))
                    icon_indicator.setImageDrawable(getResources().getDrawable(R.drawable.thyroid));

                for (MemberHealthCardsData card : healthCardList) {

                    if (card.getHealthIndicator().getId() == object.getId())
                        indicator_toggle.setChecked(true);
                }

                indicator_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!isChecked) {
                            if (AddHealthVital.activatedIndicators.contains(object.getId()))
                                AddHealthVital.activatedIndicators.remove(AddHealthVital.activatedIndicators.indexOf(object.getId()));
                            else
                                Toast.makeText(getApplicationContext(), "issue", Toast.LENGTH_LONG).show();
                        } else {
                            if (!(AddHealthVital.activatedIndicators.contains(object.getId())))
                                AddHealthVital.activatedIndicators.add(object.getId());
                        }
                    }
                });
                health_indicator_container.addView(view);
            }

        }
    }


}
