package com.schoolteacher.medvault;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.DocumentResult;
import com.schoolteacher.service.DownloadImageUrl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class DownloadDocument extends IntentService {
	private NotificationManager mNotifyManager;
	private Builder mBuilder;
	// int id = 1;
	String authToken, memberId, documentName;
	int documentId;
	UserCurrentLocationManager locationManager;

	@Override
	public void onCreate() {
		super.onCreate();
		locationManager=new UserCurrentLocationManager(getApplicationContext());
	}

	public DownloadDocument() {
		super(DownloadDocument.class.getName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		documentId = intent.getIntExtra("id", 0);
		authToken = intent.getStringExtra("authToken");
		memberId = intent.getStringExtra("memberId");
		documentName = intent.getStringExtra("name");

		getUrlOfDocument(documentId);

		mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(getApplicationContext());
		mBuilder.setContentTitle(documentName)
				.setContentText("Download in progress")
				.setSmallIcon(R.drawable.app_icon);
		// Displays the progress bar for the first time.
		mBuilder.setProgress(100, 0, true);
		mNotifyManager.notify(documentId, mBuilder.build());
	}

	private void getUrlOfDocument(int id) {
		RestAdapter getUrlAdapter = new RestAdapter.Builder()
				.setLogLevel(LogLevel.FULL).setLog(new AndroidLog("url"))
				.setEndpoint(JeevOMUtil.baseUrl).build();
		DownloadImageUrl url_interface = getUrlAdapter
				.create(DownloadImageUrl.class);
		url_interface.getDocumentUrl(locationManager.getUserLocation(),authToken, id, memberId,
				new Callback<DocumentResult>() {

					@Override
					public void failure(RetrofitError arg0) {

						if (arg0.isNetworkError()) {
							if (!(Connectivity
									.checkConnectivity(getApplicationContext()))) {
								showError(JeevOMUtil.INTERNET_CONNECTION);
							} else if (arg0.getCause() instanceof SocketTimeoutException) {
								showError(JeevOMUtil.INTERNET_CONNECTION_SLOW);
							} else if (arg0.getResponse() == null) {
								showError(JeevOMUtil.SOMETHING_WRONG);
							}
						} else if (arg0.getResponse().getStatus() > 400) {
							showError(JeevOMUtil.SOMETHING_WRONG);
						} else {
							String json = new String(((TypedByteArray) arg0
									.getResponse().getBody()).getBytes());
							Gson gson = new GsonBuilder().setPrettyPrinting()
									.create();
							DocumentResult searchResultsResponse = gson
									.fromJson(json, DocumentResult.class);
							String code = searchResultsResponse.getStatus()
									.getCode();
							String message = searchResultsResponse.getStatus()
									.getMessage();

							if (arg0.getResponse().getStatus() == 400) {
								showError(message);
							} else if (code.equals("BE-1001")) {
								showError(message);
							} else if (code.equals("BE-1000")) {
								showError(message);
							} else if (code.equals("DE-1001")) {
								showError(message);
							} else if (code.equals("BE-1002")) {
								showError(message);
							} else if (code.equals("DE-1000")) {
								showError(message);
							} else if (code.equals("BE-1004")) {
								showError(message);
							}
						}

					}

					@Override
					public void success(DocumentResult arg0, Response arg1) {
						String code = arg0.getStatus().getCode();
						if (code.equals("Success")) {

							new DownloadFileFromURL().execute(arg0.getData()
									.getFileUrl());
						}
					}
				});
	}

	/**
	 * Background Async Task to download file
	 * */
	class DownloadFileFromURL extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Bar Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		/**
		 * Downloading file in background thread
		 * */
		@Override
		protected String doInBackground(String... f_url) {
			int count;
			try {
				URL url = new URL(f_url[0]);
				URLConnection conection = url.openConnection();
				conection.connect();

				// getting type of document
				String type = getMimeType(f_url[0]);

				// get file name
				String guessName = URLUtil.guessFileName(f_url[0], null, type);
				int lenghtOfFile = conection.getContentLength();
				File externalStorageDirectory = android.os.Environment
						.getExternalStorageDirectory();
				File jeevomDirectory = new File(
						externalStorageDirectory.getAbsolutePath() + "/Jeevom/");

				// have the object build the directory structure, if needed.
				jeevomDirectory.mkdirs();
				File file = new File(jeevomDirectory, documentName);
				InputStream input = new BufferedInputStream(url.openStream(),
						8192);
				OutputStream output = new FileOutputStream(file);

				byte data[] = new byte[1024];

				long total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();

			} catch (Exception e) {
				Log.e("Error: ", e.getMessage());
			}

			return null;
		}

		protected void onProgressUpdate(String... progress) {
			mBuilder.setProgress(100, Integer.parseInt(progress[0]), false);
			mNotifyManager.notify(documentId, mBuilder.build());
			super.onProgressUpdate(progress);
		}

		@Override
		protected void onPostExecute(String file_url) {

			showError("Download complete");
			mNotifyManager.notify(documentId, mBuilder.build());
		}

	}

	private void showError(String message) {
		mBuilder.setContentText(message);
		mBuilder.setProgress(0, 0, false);
		mNotifyManager.notify(documentId, mBuilder.build());
	}

	public static String getMimeType(String url) {
		String type = null;
		String extension = MimeTypeMap.getFileExtensionFromUrl(url);
		if (extension != null) {
			MimeTypeMap mime = MimeTypeMap.getSingleton();
			type = mime.getMimeTypeFromExtension(extension);
		}
		return type;
	}
}
