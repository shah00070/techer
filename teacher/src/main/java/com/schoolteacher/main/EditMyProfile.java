package com.schoolteacher.main;

import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.interfaces.LocationTrackListener;

public class EditMyProfile extends ActionBarActivity implements
		LocationTrackListener {
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	Fragment fragmentBasic, fragmentFamily, fragmentConsumer;
	Toolbar toolbar;

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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_my_profile);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setTitle("Edit Profile");
		viewPager = (ViewPager) findViewById(R.id.pager);
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);

	}

	@Override
	public void respondCurrentLocationAddress(Address address,
			LatLng userLocation) {

		if (fragmentBasic instanceof BasicInformationFragment) {
			((BasicInformationFragment) fragmentBasic).respondCurrentLocation(
					address, userLocation);
		}

	}

	@Override
	public void searchLatLngByAddressTaskComplete(Address address,
			int responseCode) {

	}

	public class TabsPagerAdapter extends FragmentPagerAdapter {

		public TabsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {

			switch (index) {
			case 0:
				fragmentBasic = new BasicInformationFragment(viewPager);
				return fragmentBasic;
			case 1:
				fragmentFamily = new FamilyProfileFragment(viewPager);
				fragmentFamily.setMenuVisibility(true);
				return fragmentFamily;
			case 2:
				fragmentConsumer = new ConsumerInsuranceDetails();
				return fragmentConsumer;

			}

			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			CharSequence value = null;
			switch (position) {

			case 0:
				value = "Consumer Details";
				break;
			case 1:
				value = "Family Setup";
				break;

			case 2:
				value = "Insurance Details";
				break;

			}

			return value;
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
