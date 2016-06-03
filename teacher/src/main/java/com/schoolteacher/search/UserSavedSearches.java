package com.schoolteacher.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.adapters.UserSavedSearchListAdapter;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.SavedSearch;
import com.schoolteacher.service.Search;

import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class UserSavedSearches extends ActionBarActivity {
	String authToken = null;
	private JeevomSession session;
	private Toolbar saved_search_toolbar;
	private Gson gson;
	private ProgressDialogFragment newFragment;
	UserSavedSearchListAdapter adapterList;
	private ListView saved_search_list;
	UserCurrentLocationManager locationManager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_search_layout);
		setUpActionBar();
		locationManager=new UserCurrentLocationManager(getApplicationContext());
		gson = new GsonBuilder().create();
		saved_search_list = (ListView) findViewById(R.id.saved_search_list);
		session = new JeevomSession(getApplicationContext());

		if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
			authToken = "Basic " + session.getAuthToken();
		}

		getSavedSearches();
	}

	private void setUpActionBar() {
		saved_search_toolbar = (Toolbar) findViewById(R.id.saved_search_toolbar);
		setSupportActionBar(saved_search_toolbar);
		getSupportActionBar().setTitle("Saved Search");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

	public void getSavedSearches() {
		RestAdapter saveSearchAdapter = new RestAdapter.Builder()
				.setClient(new MyUrlConnectionClient())
				.setLogLevel(LogLevel.FULL)
				.setLog(new AndroidLog("getsavesearch"))
				.setEndpoint(JeevOMUtil.baseUrl).build();
		Search getSaveSearch = saveSearchAdapter
				.create(Search.class);
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(this.getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
		getSaveSearch.getSavedSearch( gson.toJson(locationManager.getUserLocation()).toString(),authToken,
				String.valueOf(session.getMemberId()),
				new Callback<ApiResponse<List<SavedSearch>>>() {

					@Override
					public void success(ApiResponse<List<SavedSearch>> arg0,
							Response arg1) {
						newFragment.dismissAllowingStateLoss();
						if (arg0.getData().size() > 0) {
							List<SavedSearch> data = arg0.getData();

							Collections.reverse(data);
							adapterList = new UserSavedSearchListAdapter(
									UserSavedSearches.this, data);

							saved_search_list.setAdapter(adapterList);
						}
					}

					@Override
					public void failure(RetrofitError arg0) {

						newFragment.dismissAllowingStateLoss();
						Toast.makeText(UserSavedSearches.this, "Try Again..",
								Toast.LENGTH_SHORT).show();

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
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		getSavedSearches();
	}
}
