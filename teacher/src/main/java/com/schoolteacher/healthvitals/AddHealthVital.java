package com.schoolteacher.healthvitals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.schoolteacher.pojos.HealthVitalsData;
import com.schoolteacher.pojos.ResponseApi;
import com.schoolteacher.service.HealthVitalInterface;
import com.schoolteacher.util.JeevomUtilsClass;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class AddHealthVital extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialogFragment progressDialog;
    private Toolbar toolbar_add_healthvitals;
    private LinearLayout health_vital_container;
    JeevomSession jeevomSession;
    private List<HealthIndicator> healthIndicators;
    private HealthVitalsData healthVital;
    private Button update_dashboard;
    public static List<Integer> activatedIndicators = new LinkedList<>();
    UserCurrentLocationManager locationManager;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_vital);
        gson = new GsonBuilder().create();
        jeevomSession = new JeevomSession(getApplicationContext());
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        // set action bar
        setUpActionBar();
        update_dashboard = (Button) findViewById(R.id.update_dashboard);
        update_dashboard.setOnClickListener(this);
        health_vital_container = (LinearLayout) findViewById(R.id.health_vital_container);

        getHealthGroups();
    }

    private void setUpActionBar() {
        toolbar_add_healthvitals = (Toolbar) findViewById(R.id.toolbar_add_healthvitals);
        setSupportActionBar(toolbar_add_healthvitals);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Health Indicator");

    }

    @Override
    public void onClick(View v) {

        //  int id = jeevomSession.getAssociateOfId();
        //  List<Integer> activate = activatedIndicators;

        // get Activated health indicators
        RestAdapter healthIndicatorsAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("healthcards")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        HealthVitalInterface healthVitals = healthIndicatorsAdapter
                .create(HealthVitalInterface.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(this.getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);

        healthVitals.updateHealthIndicators(gson.toJson(locationManager.getUserLocation()).toString(), jeevomSession.getMemberId(), activatedIndicators, new Callback<ApiResponse<Boolean>>() {
            @Override
            public void success(ApiResponse<Boolean> booleanApiResponse, Response response) {
                progressDialog.dismissAllowingStateLoss();
                Toast.makeText(AddHealthVital.this, "Updated Dashboard", Toast.LENGTH_SHORT).show();
                finish();
                JeevomUtilsClass.isFreshLoad = true;
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismissAllowingStateLoss();
                Toast.makeText(AddHealthVital.this, "Failed to Update Dashboard.Try Again", Toast.LENGTH_SHORT).show();
                JeevomUtilsClass.isFreshLoad = true;
            }
        });
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
    }

    private void getHealthGroups() {

        RestAdapter getHealthGroupsAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("healthGroups")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        HealthVitalInterface getHealthGroups = getHealthGroupsAdapter
                .create(HealthVitalInterface.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(this.getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        getHealthGroups.getHealthGroups(gson.toJson(locationManager.getUserLocation()).toString(), new Callback<ResponseApi<List<HealthVitalsData>>>() {


            @Override
            public void success(ResponseApi<List<HealthVitalsData>> healthVitalsDataResponseApi, Response response) {
                progressDialog.dismissAllowingStateLoss();

                showHealthGroups(healthVitalsDataResponseApi.getData());
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismissAllowingStateLoss();
                Toast.makeText(AddHealthVital.this, "Something Went Wrong...Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showHealthGroups(List<HealthVitalsData> data) {
        if (health_vital_container.getChildCount() > 0) {
            health_vital_container.removeAllViews();
        }
        if (data.size() > 0) {
            for (HealthVitalsData object : data) {
                healthVital = object;
                healthIndicators = healthVital.getHealthIndicators();
                LayoutInflater inflator = (LayoutInflater) AddHealthVital.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // boolean added = false;
                View view = inflator.inflate(R.layout.healthvital_row,
                        null);

                final TextView vitalName = (TextView) view.findViewById(R.id.vital_name);

                vitalName.setText(object.getName());
                vitalName.setTag(healthIndicators);

                health_vital_container.addView(view);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent healthIndicatorsIntent = new Intent(AddHealthVital.this, HealthIndicators.class);
                        healthIndicatorsIntent.putExtra("healthIndicators", (Serializable) vitalName.getTag());
                        startActivity(healthIndicatorsIntent);
                        update_dashboard.setEnabled(true);
                    }
                });

            }


        }
    }


}
