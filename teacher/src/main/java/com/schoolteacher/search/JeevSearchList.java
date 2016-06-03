package com.schoolteacher.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.SaveSearchDialog;
import com.schoolteacher.interfaces.SaveSearchCommunicator;
import com.schoolteacher.library.main.SignUpLoginActivity;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.main.BaseClass;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.JeevCriteria;
import com.schoolteacher.pojos.JeevSearchFilter;
import com.schoolteacher.pojos.SaveSearchRequest;
import com.schoolteacher.service.Search;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class JeevSearchList extends BaseClass implements SaveSearchCommunicator {
    UserCurrentLocationManager locationManager;


    Fragment fragment = null;
    private Toolbar toolbar, save_and_filter_option;
    JeevCriteria jeevCriteria;
    JeevSearchFilter filter;
    boolean isNearMeChecked;
    private RelativeLayout filter_layout, save_search_layout;
    Gson gson;
    String authToken = null;
    private JeevomSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeev_search_list_layout);
        session = new JeevomSession(getApplicationContext());

        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }

        gson = new GsonBuilder().create();
        save_and_filter_option = (Toolbar) findViewById(R.id.save_and_filter_option);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        filter_layout = (RelativeLayout) findViewById(R.id.filter_layout);
        save_search_layout = (RelativeLayout) findViewById(R.id.save_search_layout);
        getSupportActionBar().setTitle("Jeevom Search List");
        // .setTitle(
        // Html.fromHtml("<font color='#ffffff'>Jeevom Search List</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get search Results
        Intent intent = getIntent();

        jeevCriteria = (JeevCriteria) intent.getSerializableExtra("object");
        filter = (JeevSearchFilter) intent.getSerializableExtra("filter");
        isNearMeChecked = intent.getBooleanExtra("isNearMeChecked", false);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", jeevCriteria);
        bundle.putSerializable("filter", filter);
        bundle.putBoolean("isNearMeChecked", isNearMeChecked);
        // set Fragmentclass Arguments
        fragment = new JeevSearchFragment();
        fragment.setArguments(bundle);
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.list_map_container, fragment).commit();

        }
/*

        filter_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((JeevSearchFragment) fragment).openFilterActivity();
            }
        });
*/

        save_search_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (session.getLoggedInStatus()) {
                    SaveSearchDialog showDialog = SaveSearchDialog
                            .newInstance();
                    showDialog.show(getSupportFragmentManager(),
                            "context_dialog_frag");
                } else {
                    callLogIn();
                }
            }
        });
    }

    private void callLogIn() {
        Intent i = new Intent(JeevSearchList.this, SignUpLoginActivity.class);
        startActivityForResult(i, 100);
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        // TODO Auto-generated method stub
        super.onActivityResult(arg0, arg1, arg2);

        if (arg0 == 100 && arg1 == RESULT_OK) {
            SaveSearchDialog showDialog = SaveSearchDialog.newInstance();
            showDialog.show(getSupportFragmentManager(), "context_dialog_frag");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        // Applying Exit Animation;
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }

    public void scrollState(boolean scrollUp, boolean scrollDown) {

        if (scrollUp) {
            save_and_filter_option.setVisibility(View.VISIBLE);

        } else {
            save_and_filter_option.setVisibility(View.GONE);

        }

    }


    @Override
    public void onClickSaveSearch(boolean value, String name) {

        if (value) {
            String criteria = gson.toJson(
                    ((JeevSearchFragment) fragment).jeevSearchCriteria)
                    .toString();
            String filter = gson.toJson(((JeevSearchFragment) fragment).filter)
                    .toString();

            SaveSearchRequest request = new SaveSearchRequest();
            request.setCriteriaJson(criteria);
            request.setFilterJson(filter);
            request.setMemberId(String.valueOf(session.getMemberId()));
            request.setName(name);

            RestAdapter saveSearchAdapter = new RestAdapter.Builder()
                    .setClient(new MyUrlConnectionClient())
                    .setLogLevel(LogLevel.FULL)
                    .setLog(new AndroidLog("savesearch"))
                    .setEndpoint(JeevOMUtil.baseUrl).build();
            Search saveSearch = saveSearchAdapter.create(Search.class);
            saveSearch.saveSearch(gson.toJson(locationManager.getUserLocation()).toString(), authToken, request,
                    new Callback<ApiResponse<Boolean>>() {

                        @Override
                        public void success(ApiResponse<Boolean> arg0,
                                            Response arg1) {
                            Toast.makeText(JeevSearchList.this, "Saved",
                                    Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void failure(RetrofitError arg0) {
                            Toast.makeText(JeevSearchList.this, "Try Again..",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }
}
