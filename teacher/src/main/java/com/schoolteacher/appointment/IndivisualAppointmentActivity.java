package com.schoolteacher.appointment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.schoolteacher.R;
import com.schoolteacher.main.BaseClass;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.util.JeevomUtilsClass;
import com.schoolteacher.video.VideoActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class IndivisualAppointmentActivity extends BaseClass implements
		OnClickListener {
	private ImageView mProImage;
	private TextView mDrName;
	private TextView mTiming;
	private TextView mRemaining_time;
	private TextView mAddress;
	private TextView mApp_id;
	private ImageView mApp_img;
	private ImageView mTime_img;
	private ImageView mAddress_img;
	private ServiceRequisitionData mData;
	private Toolbar mToolbar;
	private ImageView mUser_img;
	private TextView mUser_name;
	private RelativeLayout clinicAddressLayout;
	private String appointmentDate;
	private String appointmentTime;
	private long millisecondsFromNow;
	private long millis;
	private GlobalAlert globalAlert;
	private TextView mAppType;
	private ImageButton mVideoCall;
	private List<Message> messages;
	private TextView mUserMessege;
	private RelativeLayout mUSerMsgContainer;

	private static final int VIDEO_CODE = 2;
	private static final int CONFIRMED = 12;
	private static final int ACTIVE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indivisual_appointment_layout);
		mToolbar = (Toolbar) findViewById(R.id.toolbar_appointment);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setTitle("Appointment Details");
		// getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		globalAlert = new GlobalAlert(this);
		mData = (ServiceRequisitionData) intent.getSerializableExtra("data");

		findViewById();

		appointmentDate = JeevomUtilsClass.formatDate(mData
				.getAppointmentDate());
		appointmentTime = JeevomUtilsClass.formatTime(mData
				.getAppointmentTime());
		mVideoCall.setOnClickListener(this);

		if (mData.getToProfessionalId() == 0) {
			setFacilityName();
		} else {
			setDoctorName();
		}
		setUserMsg();
		setAppointmentId();
		setAppointmentTime();
		setMemberName();
		if (mData.getServiceConfigurationCode().equals(AppData.SG1)) {
			setClinicAddress();
		}

		if (mData.getServiceConfigurationCode().equals(AppData.SG3)
				&& mData.getStatusId() == CONFIRMED) {
			dateToMillis(appointmentDate + " " + appointmentTime);

		}
	}

	private void setUserMsg() {
		messages = mData.getMessages();
		if (messages != null && messages.size() != 0) {
			String userMessege = messages.get(0).getMessage();
			mUSerMsgContainer.setVisibility(View.VISIBLE);
			mUserMessege.setText(userMessege);
		}
	}

	private void setFacilityName() {
		mDrName.setText(mData.getToFacilityName());
	}

	private void setClinicAddress() {
		String clinicAddress = null;
		if (mData.getToFacilityName() != null) {
			clinicAddressLayout.setVisibility(View.VISIBLE);
			clinicAddress = mData.getToFacilityName();
			if (mData.getToFacilityOwnerEmail() != null) {
				clinicAddress += "\n" + mData.getToFacilityOwnerEmail();
				if (mData.getToFacilityOwnerCellNumber() != null) {
					clinicAddress += "\n"
							+ mData.getToFacilityOwnerCellNumber();
				}
			}
		}
		mAddress.setText(clinicAddress);
	}

	private void setMemberName() {
		String userDetail = null;
		if (mData.getForMemberId() != 0) {
			userDetail = mData.getForMemberTitle() + " "
					+ mData.getForMemberFirstName() + " "
					+ mData.getForMemberLastName() + "\n"
					+ mData.getForMemberGender();
		} else {
			userDetail = mData.getByMemberTitle() + " "
					+ mData.getByMemberFirstName() + " "
					+ mData.getByMemberLastName() + "\n";
			if (mData.getByMemberCellNumber() != null) {
				userDetail += " " + mData.getByMemberCellNumber();
			}
		}
		mUser_name.setText(userDetail);

	}

	private void setAppointmentId() {
		String appId = mData.getPublicId();
		mApp_id.setText(appId);
		mAppType.setText(mData.getServiceConfigurationName() + ", "
				+ mData.getStatusText());
	}

	private void setDoctorName() {
		String drNameString = "Dr. " + mData.getToProfessionalFirstName() + " "
				+ mData.getToProfessionalLastName();
		mDrName.setText(drNameString);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	private void findViewById() {

		mProImage = (ImageView) findViewById(R.id.profile_img);
		mDrName = (TextView) findViewById(R.id.dr_name);

		mApp_img = (ImageView) findViewById(R.id.app_img);
		mApp_id = (TextView) findViewById(R.id.app_id);

		mTime_img = (ImageView) findViewById(R.id.time_img);
		mTiming = (TextView) findViewById(R.id.timing);
		mRemaining_time = (TextView) findViewById(R.id.remaining_time);

		mAddress_img = (ImageView) findViewById(R.id.address_img);
		mAddress = (TextView) findViewById(R.id.address);
		clinicAddressLayout = (RelativeLayout) findViewById(R.id.rl_app_type);

		mUser_img = (ImageView) findViewById(R.id.user_img);
		mUser_name = (TextView) findViewById(R.id.user_name);

		mAppType = (TextView) findViewById(R.id.app_type_n_status);
		mVideoCall = (ImageButton) findViewById(R.id.video_call);

		mUSerMsgContainer = (RelativeLayout) findViewById(R.id.rl_user_messege);
		mUserMessege = (TextView) findViewById(R.id.user_messege);

	}

	private void setAppointmentTime() {

		try {
			mTiming.setText(JeevomUtilsClass
					.formatToYesterdayOrToday(appointmentDate + " "
							+ appointmentTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long dateToMillis = JeevomUtilsClass.dateToMillis(appointmentDate + " "
				+ appointmentTime);
		if (dateToMillis > 0) {
			String hourFormatTime = JeevomUtilsClass.milliToDate(dateToMillis);
			mRemaining_time.setText("in " + hourFormatTime);
		} else {
			String hourFormatTime = JeevomUtilsClass.milliToDate(dateToMillis
					* (-1));

			mRemaining_time.setText(hourFormatTime + " ago");
		}
	}

	@Override
	public void onClick(View v) {

		int cameraId = 0;
		cameraId = JeevomUtilsClass.findFrontFacingCamera();
		if (cameraId < 0) {
			showAlert("No front facing camera found. Please login to www.jeevom.com");
		} else {
			Intent videoIntent = new Intent(this, VideoActivity.class);
			videoIntent
					.putExtra("appointmentId", String.valueOf(mData.getId()));
			videoIntent.putExtra("millis", millis);
			startActivityForResult(videoIntent, VIDEO_CODE);
		}
	}

	private void dateToMillis(String timeStamp) {
		// String input = "Sat Feb 17 2012";
		// String input = "DD-MMM_YYYY h:mm a";
		String input = timeStamp;
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MMM-yyyy h:mm a", Locale.ENGLISH)
					.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long milliseconds = date.getTime();
		millisecondsFromNow = milliseconds - (new Date()).getTime();
		if (millisecondsFromNow > 0) {
			startTimer(millisecondsFromNow);
		} else if (millisecondsFromNow + mData.getAppointmentInterval() * 60
				* 1000 > 0) {
			millis = millisecondsFromNow + mData.getAppointmentInterval() * 60
					* 1000;
			startVideoCountDown(millis);

		}
		{
		}
	}

	public void startTimer(long millisecondsFromNow2) {
		// mTimer.setVisibility(View.VISIBLE);
		CountDownTimer countDownTimer = new CountDownTimer(
				millisecondsFromNow2, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// payNow.setText("Your appointment will start in: " +
				// milliToDate(millisUntilFinished));
				mRemaining_time.setText("Starts in, "
						+ milliToDate(millisUntilFinished));

			}

			@Override
			public void onFinish() {
				startVideoCountDown(mData.getAppointmentInterval() * 60 * 1000);

			}
		};
		countDownTimer.start();
	}

	private void startVideoCountDown(long millisecondsFromNow) {
		// mTimer.setVisibility(View.VISIBLE);
		mVideoCall.setVisibility(View.VISIBLE);
		CountDownTimer countDownTimer = new CountDownTimer(millisecondsFromNow,
				1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// mTimer.setText("Remaining Time " + "\n"
				// + milliToMin(millisUntilFinished));
				mRemaining_time.setText("Remaining Time, "
						+ milliToMin(millisUntilFinished));
				millis = millisUntilFinished;
			}

			@Override
			public void onFinish() {
				mRemaining_time.setVisibility(View.GONE);
				mVideoCall.setVisibility(View.GONE);
			}
		};
		countDownTimer.start();
	}

	private String milliToDate(long timeInMilliSeconds) {
		// TODO Auto-generated method stub
		String time;
		long seconds = timeInMilliSeconds / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		if (days == 0) {
			time = hours % 24 + "h " + ":" + minutes % 60 + "m " + ":"
					+ seconds % 60 + "s ";
		} else {
			time = days + "d " + ":" + hours % 24 + "h " + ":" + minutes % 60
					+ "m " + ":" + seconds % 60 + "s ";

		}
		return time;

	}

	private String milliToMin(long timeInMilliSeconds) {
		// TODO Auto-generated method stub
		String time;
		long seconds = timeInMilliSeconds / 1000;
		long minutes = seconds / 60;
		time = minutes % 60 + "m " + ":" + seconds % 60 + "s ";
		return time;

	}

	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}
}
