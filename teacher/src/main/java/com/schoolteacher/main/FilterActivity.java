package com.schoolteacher.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.pojos.Filter;

public class FilterActivity extends Activity implements
		CompoundButton.OnCheckedChangeListener, OnClickListener {
	TextView distance_text;
	SeekBar distance_seek_bar;
	CheckBox all, recommended, premium, verified, fee, online;
	RadioGroup type_radio_group;
	String type;
	double seekBarProgress;
	Button reset, apply;
	Filter filter = new Filter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		setContentView(R.layout.filter_layout);
		//
		// // distance text view
		// distance_text = (TextView) findViewById(R.id.distance_text);
		// // seek bar
		// distance_seek_bar = (SeekBar) findViewById(R.id.distance_seek_bar);
		// distance_seek_bar.setOnSeekBarChangeListener(new
		// OnSeekBarChangeListener() {
		//
		// @Override
		// public void onStopTrackingTouch(SeekBar seekBar) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onStartTrackingTouch(SeekBar seekBar) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onProgressChanged(SeekBar seekBar, int progress, boolean
		// fromUser) {
		// seekBarProgress = (double) progress;
		// distance_text.setText("Distance from your location" + " " +
		// seekBarProgress + " " + "Km");
		// }
		// });
		//
		// // get filter object
		// filter = (Filter) getIntent().getExtras().get("filterObject");
		//
		// // button references
		// reset = (Button) findViewById(R.id.reset);
		// apply = (Button) findViewById(R.id.apply);
		//
		// // checkBoxes
		// all = (CheckBox) findViewById(R.id.all);
		// recommended = (CheckBox) findViewById(R.id.recommended);
		// premium = (CheckBox) findViewById(R.id.premium);
		// verified = (CheckBox) findViewById(R.id.verified);
		// fee = (CheckBox) findViewById(R.id.fee);
		// online = (CheckBox) findViewById(R.id.online);
		//
		// // Implement onchecked change listners
		// all.setOnCheckedChangeListener(this);
		// recommended.setOnCheckedChangeListener(this);
		// premium.setOnCheckedChangeListener(this);
		// verified.setOnCheckedChangeListener(this);
		// fee.setOnCheckedChangeListener(this);
		// online.setOnCheckedChangeListener(this);
		//
		// // type radio group
		// type_radio_group = (RadioGroup) findViewById(R.id.type_radio_group);
		//
		// type_radio_group.setOnCheckedChangeListener(new
		// OnCheckedChangeListener() {
		//
		// @Override
		// public void onCheckedChanged(RadioGroup group, int checkedId) {
		// if (checkedId == R.id.radio_button_none) {
		// type = "None";
		// } else if (checkedId == R.id.radio_button_facility) {
		// type = "Professional";
		// } else if (checkedId == R.id.radio_button_professional) {
		// type = "Facility";
		// }
		// }
		// });
		//
		// // populate filters values
		// // populate seek bar
		// type = filter.getType();
		// distance_seek_bar.setProgress((int) filter.getDistance());
		// if (filter.getRecommended() == 1) {
		// recommended.setChecked(true);
		// }
		// if (filter.getPremium() == 1) {
		// premium.setChecked(true);
		// }
		// if (filter.getVerified() == 1) {
		// verified.setChecked(true);
		// }
		// if (filter.getFeeAvailable() == 1) {
		// fee.setChecked(true);
		// }
		// if (filter.getOnline() == 1) {
		// online.setChecked(true);
		// }
		//
		// if (filter.getType().equals("None")) {
		// type_radio_group.check(R.id.radio_button_none);
		// } else if (filter.getType().equals("Facility")) {
		// type_radio_group.check(R.id.radio_button_facility);
		// } else {
		// type_radio_group.check(R.id.radio_button_professional);
		// }
		//
		// // button on click listeners
		// reset.setOnClickListener(this);
		// apply.setOnClickListener(this);
		// }
		//
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView, boolean
		// isChecked) {
		// switch (buttonView.getId()) {
		// case R.id.all:
		//
		// if (isChecked) {
		// recommended.setChecked(true);
		// premium.setChecked(true);
		// verified.setChecked(true);
		// fee.setChecked(true);
		// online.setChecked(true);
		// }
		// break;
		//
		// case R.id.recommended:
		//
		// if (!isChecked) {
		// if (all.isChecked()) {
		// all.setChecked(false);
		// }
		// }
		//
		// break;
		// case R.id.premium:
		// if (!isChecked) {
		// if (all.isChecked()) {
		// all.setChecked(false);
		// }
		// }
		// break;
		// case R.id.verified:
		// if (!isChecked) {
		// if (all.isChecked()) {
		// all.setChecked(false);
		// }
		// }
		// break;
		// case R.id.fee:
		// if (!isChecked) {
		// if (all.isChecked()) {
		// all.setChecked(false);
		// }
		// }
		// break;
		// case R.id.online:
		// if (!isChecked) {
		// if (all.isChecked()) {
		// all.setChecked(false);
		// }
		// }
		// break;
		// }
		//
		// }
		//
		// @Override
		// public void onClick(View v) {
		// switch (v.getId()) {
		// case R.id.reset:
		//
		// break;
		//
		// case R.id.apply:
		// int feeValue,
		// recommendedValue,
		// premiumValue,
		// verifiedValue,
		// onlineValue;
		//
		// if (all.isChecked()) {
		// feeValue = 1;
		// recommendedValue = 1;
		// premiumValue = 1;
		// verifiedValue = 1;
		// onlineValue = 1;
		// } else {
		// // fee
		// if (fee.isChecked()) {
		// feeValue = 1;
		// } else {
		// feeValue = 0;
		// }
		//
		// // recommended
		// if (recommended.isChecked()) {
		// recommendedValue = 1;
		// } else {
		// recommendedValue = 0;
		// }
		// // recommended
		// if (premium.isChecked()) {
		// premiumValue = 1;
		// } else {
		// premiumValue = 0;
		// }
		// // recommended
		// if (verified.isChecked()) {
		// verifiedValue = 1;
		// } else {
		// verifiedValue = 0;
		// }
		// // recommended
		// if (online.isChecked()) {
		// onlineValue = 1;
		// } else {
		// onlineValue = 0;
		// }
		// }
		// filter.setDistance(seekBarProgress);
		// filter.setFeeAvailable(feeValue);
		// filter.setOnline(onlineValue);
		// filter.setPremium(premiumValue);
		// filter.setRecommended(recommendedValue);
		// filter.setType(type);
		// filter.setRating(0);
		//
		// Intent i = new Intent();
		// Bundle b = new Bundle();
		// b.putSerializable("filteredValues", filter);
		// i.putExtras(b);
		// setResult(RESULT_OK, i);
		// finish();
		// break;
		// }

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub

	}
}
