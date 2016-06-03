package com.schoolteacher.video;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.Connection;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Session.SignalListener;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;
import com.schoolteacher.R;
import com.schoolteacher.broadcast.HeadsetStateReceiver;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.main.ClearNotificationService;
import com.schoolteacher.main.ClearNotificationService.ClearBinder;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.VideoRequestData;
import com.schoolteacher.pojos.VideoResponse;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class VideoActivity extends FragmentActivity implements
		Session.SessionListener, Publisher.PublisherListener,
		Subscriber.VideoListener, SignalListener, OnClickListener {
	private GlobalAlert globalAlert;
	JeevomSession session;
	boolean microphoneState = false;
	boolean speakerState = true;
	private DialogFragment newFragment;
	private String SESSION_ID = "";
	// Replace with a generated token (from the dashboard or using an OpenTok
	// server SDK)
	private String TOKEN = "";
	// Replace with your OpenTok API key
	private String API_KEY = "";

	// Subscribe to a stream published by this client. Set to false to subscribe
	// to other clients' streams only.
	private final boolean SUBSCRIBE_TO_SELF = false;

	private static final String LOGTAG = "demo-hello-world";
	private Session mSession;
	private Publisher mPublisher;
	private Subscriber mSubscriber;
	private ArrayList<Stream> mStreams;
	protected Handler mHandler = new Handler();

	private RelativeLayout mPublisherViewContainer;
	private RelativeLayout mSubscriberViewContainer;

	// Spinning wheel for loading subscriber view
	private ProgressBar mLoadingSub;

	private boolean resumeHasRun = false;

	private boolean mIsBound = false;
	private NotificationCompat.Builder mNotifyBuilder;
	private NotificationManager mNotificationManager;
	private ServiceConnection mConnection;
	private TextView timer;
	private String temp = "";
	private TextView textView;
	ImageView mute, speaker;
	private HeadsetStateReceiver receiver;
	private String authToken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_layout);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		Log.i(LOGTAG, "ONCREATE");
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		setContentView(R.layout.video_layout);
		IntentFilter receiverFilter = new IntentFilter(
				Intent.ACTION_HEADSET_PLUG);
		receiver = new HeadsetStateReceiver();
		registerReceiver(receiver, receiverFilter);

		globalAlert = new GlobalAlert(this);
		// ActionBar actionBar = getActionBar();
		// actionBar.setTitle(Html
		// .fromHtml("<font color='#ffffff'>Video Consultation</font>"));
		mPublisherViewContainer = (RelativeLayout) findViewById(R.id.publisherview);
		mSubscriberViewContainer = (RelativeLayout) findViewById(R.id.subscriberview);
		mLoadingSub = (ProgressBar) findViewById(R.id.loadingSpinner);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		session = new JeevomSession(this);
		mStreams = new ArrayList<Stream>();
		Intent intent = getIntent();
		long remainingMillis = intent.getLongExtra("millis", 0);
		timer = (TextView) findViewById(R.id.tv_timer);
		startVideoCountDown(remainingMillis);
		String appointmentId = intent.getStringExtra("appointmentId");
		ImageView endCall = (ImageView) findViewById(R.id.end_call);
		mute = (ImageView) findViewById(R.id.mute);
		speaker = (ImageView) findViewById(R.id.speaker);
		endCall.setOnClickListener(this);
		mute.setOnClickListener(this);
		speaker.setOnClickListener(this);
		getVideoDetails(appointmentId);

		// sessionConnect();
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
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		// Remove publisher & subscriber views because we want to reuse them
		if (mSubscriber != null) {
			mSubscriberViewContainer.removeView(mSubscriber.getView());
		}
		// reloadInterface();
	}

	@Override
	public void onPause() {
		super.onPause();

		if (mSession != null) {
			mSession.onPause();

			if (mSubscriber != null) {
				mSubscriberViewContainer.removeView(mSubscriber.getView());
			}
		}

		mNotifyBuilder = new NotificationCompat.Builder(this)
				.setContentTitle(this.getTitle())
				.setContentText("Ongoing call")
				.setSmallIcon(R.drawable.ic_launcher).setOngoing(true);

		Intent notificationIntent = new Intent(this, VideoActivity.class);
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);

		mNotifyBuilder.setContentIntent(intent);
		if (mConnection == null) {
			mConnection = new ServiceConnection() {
				@Override
				public void onServiceConnected(ComponentName className,
						IBinder binder) {
					((ClearBinder) binder).service
							.startService(new Intent(VideoActivity.this,
									ClearNotificationService.class));
					NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
					mNotificationManager.notify(
							ClearNotificationService.NOTIFICATION_ID,
							mNotifyBuilder.build());
				}

				@Override
				public void onServiceDisconnected(ComponentName className) {
					mConnection = null;
				}

			};
		}

		if (!mIsBound) {
			bindService(new Intent(VideoActivity.this,
					ClearNotificationService.class), mConnection,
					Context.BIND_AUTO_CREATE);
			mIsBound = true;
		}

	}

	@Override
	public void onResume() {
		super.onResume();

		if (mIsBound) {
			unbindService(mConnection);
			mIsBound = false;
		}

		if (!resumeHasRun) {
			resumeHasRun = true;
			return;
		} else {
			if (mSession != null) {
				mSession.onResume();
			}
		}
		mNotificationManager.cancel(ClearNotificationService.NOTIFICATION_ID);

		reloadInterface();
	}

	@Override
	public void onStop() {
		super.onStop();

		if (mIsBound) {
			unbindService(mConnection);
			mIsBound = false;
		}

		if (mIsBound) {
			unbindService(mConnection);
			mIsBound = false;
		}
		if (isFinishing()) {
			mNotificationManager
					.cancel(ClearNotificationService.NOTIFICATION_ID);
			if (mSession != null) {
				mSession.disconnect();
			}
		}
	}

	@Override
	public void onDestroy() {
		mNotificationManager.cancel(ClearNotificationService.NOTIFICATION_ID);
		if (mIsBound) {
			unbindService(mConnection);
			mIsBound = false;
		}

		if (mSession != null) {
			mSession.disconnect();
		}

		restartAudioMode();

		super.onDestroy();
		finish();
	}

	@Override
	public void onBackPressed() {
		// if (mSession != null) {
		// mSession.disconnect();
		// }
		//
		// restartAudioMode();
		//
		// super.onBackPressed();
	}

	public void reloadInterface() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (mSubscriber != null) {
					attachSubscriberView(mSubscriber);
				}
			}
		}, 500);
	}

	public void restartAudioMode() {
		AudioManager Audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		Audio.setMode(AudioManager.MODE_NORMAL);
		Audio.setSpeakerphoneOn(true);
		this.setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
	}

	private void sessionConnect() {
		if (mSession == null) {
			mSession = new Session(VideoActivity.this, API_KEY, SESSION_ID);
			mSession.setSessionListener(this);
			mSession.connect(TOKEN);
			mSession.setSignalListener(this);
		}
	}

	@Override
	public void onConnected(Session session) {
		Log.i(LOGTAG, "Connected to the session.");
		if (mPublisher == null) {
			mPublisher = new Publisher(VideoActivity.this, "publisher");
			mPublisher.setPublisherListener(this);
			attachPublisherView(mPublisher);
			mSession.publish(mPublisher);
			restartAudioMode();
		}
	}

	@Override
	public void onDisconnected(Session session) {
		Log.i(LOGTAG, "Disconnected from the session.");
		if (mPublisher != null) {
			mPublisherViewContainer.removeView(mPublisher.getView());
		}

		if (mSubscriber != null) {
			mSubscriberViewContainer.removeView(mSubscriber.getView());
		}

		mPublisher = null;
		mSubscriber = null;
		mStreams.clear();
		mSession = null;
	}

	private void subscribeToStream(Stream stream) {

		mSubscriber = new Subscriber(VideoActivity.this, stream);
		mSubscriber.setVideoListener(this);
		mSession.subscribe(mSubscriber);
		// start loading spinning
		mLoadingSub.setVisibility(View.VISIBLE);
	}

	private void unsubscribeFromStream(Stream stream) {
		mStreams.remove(stream);
		if (mSubscriber.getStream().equals(stream)) {
			mSubscriberViewContainer.removeView(mSubscriber.getView());
			mSubscriber = null;
			if (!mStreams.isEmpty()) {
				subscribeToStream(mStreams.get(0));
			}
		}
	}

	private void attachSubscriberView(Subscriber subscriber) {
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				getResources().getDisplayMetrics().widthPixels, getResources()
						.getDisplayMetrics().heightPixels);
		mSubscriberViewContainer.addView(mSubscriber.getView(), layoutParams);
		subscriber.setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE,
				BaseVideoRenderer.STYLE_VIDEO_FILL);
	}

	private void attachPublisherView(Publisher publisher) {
		mPublisher.setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE,
				BaseVideoRenderer.STYLE_VIDEO_FILL);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				320, 240);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				RelativeLayout.TRUE);
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				RelativeLayout.TRUE);
		layoutParams.bottomMargin = dpToPx(8);
		layoutParams.rightMargin = dpToPx(8);
		mPublisherViewContainer.addView(mPublisher.getView(), layoutParams);
	}

	@Override
	public void onError(Session session, OpentokError exception) {
		Log.i(LOGTAG, "Session exception: " + exception.getMessage());
	}

	@Override
	public void onStreamReceived(Session session, Stream stream) {

		// if (!OpenTokConfig.SUBSCRIBE_TO_SELF) {
		if (!SUBSCRIBE_TO_SELF) {
			mStreams.add(stream);
			if (mSubscriber == null) {
				subscribeToStream(stream);
			}
		}
	}

	@Override
	public void onStreamDropped(Session session, Stream stream) {
		if (!SUBSCRIBE_TO_SELF) {
			if (mSubscriber != null) {
				unsubscribeFromStream(stream);
			}
		}
	}

	@Override
	public void onStreamCreated(PublisherKit publisher, Stream stream) {
		if (SUBSCRIBE_TO_SELF) {
			mStreams.add(stream);
			if (mSubscriber == null) {
				subscribeToStream(stream);
			}
		}
	}

	@Override
	public void onStreamDestroyed(PublisherKit publisher, Stream stream) {
		if ((SUBSCRIBE_TO_SELF && mSubscriber != null)) {
			unsubscribeFromStream(stream);
		}
	}

	@Override
	public void onError(PublisherKit publisher, OpentokError exception) {
		Log.i(LOGTAG, "Publisher exception: " + exception.getMessage());
	}

	@Override
	public void onVideoDataReceived(SubscriberKit subscriber) {
		Log.i(LOGTAG, "First frame received");

		// stop loading spinning
		mLoadingSub.setVisibility(View.GONE);
		attachSubscriberView(mSubscriber);
	}

	/**
	 * Converts dp to real pixels, according to the screen density.
	 * 
	 * @param dp
	 *            A number of density-independent pixels.
	 * @return The equivalent number of real pixels.
	 */
	private int dpToPx(int dp) {
		double screenDensity = this.getResources().getDisplayMetrics().density;
		return (int) (screenDensity * (double) dp);
	}

	@Override
	public void onVideoDisabled(SubscriberKit subscriber, String reason) {
		Log.i(LOGTAG, "Video disabled:" + reason);
	}

	@Override
	public void onVideoEnabled(SubscriberKit subscriber, String reason) {
		Log.i(LOGTAG, "Video enabled:" + reason);
	}

	@Override
	public void onVideoDisableWarning(SubscriberKit subscriber) {
		Log.i(LOGTAG,
				"Video may be disabled soon due to network quality degradation. Add UI handling here.");
	}

	@Override
	public void onVideoDisableWarningLifted(SubscriberKit subscriber) {
		Log.i(LOGTAG,
				"Video may no longer be disabled as stream quality improved. Add UI handling here.");
	}

	private void getVideoDetails(String appointmentId) {
		if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
			authToken = "Basic " + session.getAuthToken();
		}

		VideoRequestData videoRequestData = new VideoRequestData();
		videoRequestData.setIPAddress(CommonCode.getLocalIpAddress());
		videoRequestData.setMemberId(session.getMemberId());

		RestAdapter videoAdapter = new RestAdapter.Builder()
				.setClient(new MyUrlConnectionClient())
				.setLogLevel(LogLevel.FULL).setLog(new AndroidLog("video"))
				.setEndpoint(JeevOMUtil.baseUrl).build();
		GetVideoDetails video = videoAdapter.create(GetVideoDetails.class);
		newFragment = ProgressDialogFragment.newInstance();
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
		video.getVideoDetails(appointmentId, authToken, videoRequestData,
				new Callback<ApiResponse<VideoResponse>>() {

					@Override
					public void success(ApiResponse<VideoResponse> arg0,
							Response arg1) {
						newFragment.dismissAllowingStateLoss();
						String code = arg0.getStatus().getCode();
						if (code.equals("Success")) {

							API_KEY = arg0.getData().getOTAPIKey();
							// arg0.getData().getApiSecret();
							SESSION_ID = arg0.getData().getSessionId();
							TOKEN = arg0.getData().getOTAPITokenKey();
							sessionConnect();

						}

					}

					@Override
					public void failure(RetrofitError arg0) {
						newFragment.dismissAllowingStateLoss();

						if (arg0.isNetworkError()) {
							if (!(Connectivity
									.checkConnectivity(VideoActivity.this))) {
								showAlert(JeevOMUtil.INTERNET_CONNECTION);
							} else if (arg0.getCause() instanceof SocketTimeoutException) {
								showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
							} else if (arg0.getResponse() == null) {
								showAlert(JeevOMUtil.SOMETHING_WRONG);
							}
						} else if (arg0.getResponse().getStatus() > 400) {
							showAlert(JeevOMUtil.SOMETHING_WRONG);
						} else {
							String json = new String(((TypedByteArray) arg0
									.getResponse().getBody()).getBytes());
							Gson gson = new GsonBuilder().setPrettyPrinting()
									.create();
							VideoResult searchResultsResponse = gson.fromJson(
									json, VideoResult.class);
							String code = searchResultsResponse.getStatus()
									.getCode();
							String message = searchResultsResponse.getStatus()
									.getMessage();
							if (code.equals("BE-1001")) {
								showAlert(message);
							} else if (code.equals("BE-1000")) {
								showAlert(message);
							} else if (code.equals("DE-1001")) {
								showAlert(message);
							} else if (code.equals("BE-1002")) {
								showAlert(message);
							} else if (code.equals("DE-1000")) {
								showAlert(message);
							} else if (code.equals("BE-1004")) {
								showAlert(message);
							} else if (code.equals("IOE-1000")) {
								showAlert("No Results Found");
							}
						}
					}
				});
	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

	private void startVideoCountDown(long millisecondsFromNow) {
		// timer.setVisibility(View.VISIBLE);
		CountDownTimer countDownTimer = new CountDownTimer(millisecondsFromNow,
				1000) {
			String message = "Remaining Time ";

			@Override
			public void onTick(long millisUntilFinished) {
				if (millisUntilFinished <= 2 * 60 * 1000) {
					timer.setTextColor(Color.RED);
					if (millisUntilFinished <= 1 * 60 * 1000) {
						message = "Call will be end in ";
					}
				}
				timer.setText(message + "\n" + milliToMin(millisUntilFinished));

			}

			@Override
			public void onFinish() {
				timer.setVisibility(View.GONE);
				finish();
			}
		};
		countDownTimer.start();
	}

	private String milliToMin(long timeInMilliSeconds) {
		// TODO Auto-generated method stub
		String time;
		long seconds = timeInMilliSeconds / 1000;
		long minutes = seconds / 60;
		time = minutes % 60 + "m " + ":" + seconds % 60 + "s ";
		return time;

	}

	@Override
	public void onSignalReceived(Session session, String type, String data,
			Connection connection) {
		String mycid = session.getConnection().getConnectionId();
		String cid = connection.getConnectionId();
		if (!cid.equals(mycid)) {
			textView = (TextView) findViewById(R.id.prescription);
			textView.setText(data);
		}
		if (type != null && type.equals("PrescriptionSent")) {
			textView.setText("PrescriptionSent : " + data);
			setResult(RESULT_OK);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.end_call:
			unregisterReceiver(receiver);
			finish();
			break;
		case R.id.speaker:
			AudioManager AudioSpeaker = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			if (speakerState) {
				AudioSpeaker.setSpeakerphoneOn(false);
				speaker.setImageResource(R.drawable.mute_sub);
				speakerState = false;
			} else {
				AudioSpeaker.setSpeakerphoneOn(true);
				speaker.setImageResource(R.drawable.unmute_sub);
				speakerState = true;
			}
			break;
		case R.id.mute:
			AudioManager Audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
			if (!microphoneState) {
				Audio.setMicrophoneMute(true);
				microphoneState = true;
				mute.setImageResource(R.drawable.mute_pub);

			} else {
				Audio.setMicrophoneMute(false);
				microphoneState = false;
				mute.setImageResource(R.drawable.unmute_pub);
			}
			break;
		}

	}
}