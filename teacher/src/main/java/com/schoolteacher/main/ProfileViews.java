package com.schoolteacher.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.schoolteacher.R;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.pojos.JeevSearchResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProfileViews extends BaseClass {
	DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
	ViewPager mViewPager;

	ArrayList<JeevSearchResult> dataList;
	String current_profile_id;
	String current_profile_type;
	Fragment fragment;

	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		setContentView(R.layout.doctor_profile_pager);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Jeevom Profile");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Intent profile_intent = getIntent();
		// dataList = (ArrayList<JeevSearchResult>) profile_intent
		// .getSerializableExtra("search_list");

		dataList = profile_intent.getParcelableArrayListExtra("search_list");
		current_profile_id = profile_intent.getStringExtra("id");
		current_profile_type = profile_intent.getStringExtra("type");
		Iterator<JeevSearchResult> iterator = dataList.iterator();
		int pos = 0, j = -1;
		while (iterator.hasNext()) {
			j++;
			JeevSearchResult searchResult = new JeevSearchResult();
			searchResult = iterator.next();
			if (searchResult.getType().equals("Professional")) {
				if (searchResult.getProfessionalProfileId().equals(
						current_profile_id)) {
					pos = j;
					break;
				}
			} else {
				if (searchResult.getFacilityProfileId().equals(
						current_profile_id)) {
					pos = j;
					break;
				}
			}
		}

		// ViewPager and its adapters use support library
		// fragments, so use getSupportFragmentManager.
		mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(
				getSupportFragmentManager(), dataList);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mDemoCollectionPagerAdapter);
		mViewPager.setCurrentItem(pos);
	}

	// Since this is an object collection, use a FragmentStatePagerAdapter,
	// and NOT a FragmentPagerAdapter.
	class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
		List<JeevSearchResult> listOfUser = new ArrayList<JeevSearchResult>();
		int id;

		public DemoCollectionPagerAdapter(FragmentManager fm,
				List<JeevSearchResult> userList) {
			super(fm);
			try {
				listOfUser.addAll(userList);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public Fragment getItem(int i) {
			String type = listOfUser.get(i).getType();
			if (type.equals("Professional")) {
				fragment = new DoctorProfileFragment();
				Bundle args = new Bundle();
				args.putString("ProfessionalProfileId", listOfUser.get(i)
						.getProfessionalProfileId());
				fragment.setArguments(args);
			} else if (type.equals("Facility")) {
				fragment = new FacilityProfileFragment();
				Bundle args = new Bundle();
				// Our object is just an integer :-P
				args.putString("FacilityProfileId", listOfUser.get(i)
						.getFacilityProfileId());
				fragment.setArguments(args);
			}

			return fragment;
		}

		@Override
		public int getCount() {
			return listOfUser.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return listOfUser.get(position).getFullName();
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

	@Override
	protected void onResume() {
		// if (Build.VERSION.SDK_INT >= 11) {
		// invalidateOptionsMenu();
		//
		// }
		super.onResume();

	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
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

}
