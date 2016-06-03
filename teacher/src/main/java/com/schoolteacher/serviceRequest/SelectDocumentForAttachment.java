package com.schoolteacher.serviceRequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.adapters.SelectDocumentsAdapter;
import com.schoolteacher.interfaces.CheckUncheckDocument;
import com.schoolteacher.main.BaseClass;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.DocumentList;
import com.schoolteacher.pojos.Documents;
import com.schoolteacher.service.GetDocumentListOfUser;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class SelectDocumentForAttachment extends BaseClass implements
		OnClickListener, CheckUncheckDocument {
	ListView listView;
	Button btn_attach;
	ProgressBar progressBar;
	GlobalAlert globalAlert;
	JeevomSession session;
	List<DocumentList> documentList;
	SelectDocumentsAdapter adapterList;
	List<DocumentList> selectedDocuments;
	String authToken;
	UserCurrentLocationManager locationManager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_document);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_med);
		setSupportActionBar(toolbar);
		locationManager=new UserCurrentLocationManager(getApplicationContext());
		getSupportActionBar().setTitle("Medvault Documents");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		documentList = new ArrayList<>();
		selectedDocuments = new ArrayList<>();
		globalAlert = new GlobalAlert(this);
		session = new JeevomSession(getApplicationContext());
		if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
			authToken = "Basic " + session.getAuthToken();
		}
		// references
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		listView = (ListView) findViewById(R.id.listView_documents);
		listView.setDividerHeight(10);
		listView.setDivider(null);
		btn_attach = (Button) findViewById(R.id.btn_attach);
		btn_attach.setOnClickListener(this);
		getUserDocuments();
	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

	private void getUserDocuments() {

		RestAdapter getDocumentAdapter = new RestAdapter.Builder()
				.setLogLevel(LogLevel.FULL).setLog(new AndroidLog("documents"))
				.setClient(new MyUrlConnectionClient())
				.setEndpoint(JeevOMUtil.baseUrl).build();
		GetDocumentListOfUser documentService = getDocumentAdapter
				.create(GetDocumentListOfUser.class);
		progressBar.setVisibility(View.VISIBLE);
		documentService.getDocumentList(
				locationManager.getUserLocation(), String.valueOf(session.getMemberId()),
				new Callback<Documents>() {

					@Override
					public void failure(RetrofitError arg0) {

						progressBar.setVisibility(View.GONE);

						if (arg0.isNetworkError()) {
							if (!(Connectivity
									.checkConnectivity(getApplicationContext()))) {
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
							Documents responseValue = gson.fromJson(json,
									Documents.class);
							String code = responseValue.getStatus().getCode();
							String message = responseValue.getStatus()
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
							}
						}

					}

					@Override
					public void success(Documents arg0, Response arg1) {

						progressBar.setVisibility(View.GONE);
						String code = arg0.getStatus().getCode();
						if (code.equals("Success")) {
							documentList = arg0.getData().getDocumentList();
							if (documentList == null || documentList.size() < 1) {
								listView.setEmptyView(noItems("No Document in Med Vault"));
							} else
								fillListView(documentList);
						}
					}
				});

	}

	protected void fillListView(List<DocumentList> documentList2) {
		adapterList = new SelectDocumentsAdapter(
				SelectDocumentForAttachment.this, documentList2);
		listView.setAdapter(adapterList);
	}

	@Override
	public void onClick(View v) {
if(selectedDocuments.size()==0){
	showAlert("Please select at least one document...");
}else{
		Intent documentList = new Intent();
		Bundle bundle = new Bundle();
		bundle.putParcelableArrayList("documents",
				(ArrayList<DocumentList>) selectedDocuments);
		documentList.putExtras(bundle);
		setResult(400, documentList);
		finish();}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		super.onPrepareOptionsMenu(menu);
		return true;
	}

	@Override
	protected void onActivityResult(int request, int response, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(request, response, arg2);

		getUserDocuments();

	}

	private TextView noItems(String text) {
		TextView emptyView = new TextView(this);
		// Make sure you import android.widget.LinearLayout.LayoutParams;
		emptyView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		emptyView.setTextColor(getResources().getColor(R.color.grey_dark));
		emptyView.setText(text);
		emptyView.setTextSize(16);
		emptyView.setVisibility(View.GONE);
		emptyView.setGravity(Gravity.CENTER_VERTICAL
				| Gravity.CENTER_HORIZONTAL);

		// Add the view to the list view. This might be what you are missing
		((ViewGroup) listView.getParent()).addView(emptyView);

		return emptyView;
	}

	@Override
	public void setCheckUncheckDocument(DocumentList object, boolean isChecked) {
		boolean isAlreadyExists = false;
		if (selectedDocuments.size() > 0) {

			if (isChecked) {

				for (int i = 0; i < selectedDocuments.size(); i++) {

					if (selectedDocuments.get(i).getId() == object.getId())
						isAlreadyExists = true;
				}

				if (!isAlreadyExists)
					selectedDocuments.add(object);
			} else {
				for (int i = 0; i < selectedDocuments.size(); i++) {

					if (selectedDocuments.get(i).getId() == object.getId()) {
						selectedDocuments.remove(i);
						break;
					}

				}

			}

		} else {
			selectedDocuments.add(object);
		}

	}
}
