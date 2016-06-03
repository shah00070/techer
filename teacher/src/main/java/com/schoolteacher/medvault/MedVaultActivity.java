package com.schoolteacher.medvault;

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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.adapters.DocumentsAdapter;
import com.schoolteacher.interfaces.ImageDownloadOptionClick;
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

public class MedVaultActivity extends BaseClass implements OnClickListener,
        ImageDownloadOptionClick {
    ListView listView;
    Button btn_upload;
    private final int ATTACH = 100;
    ProgressBar progressBar;
    GlobalAlert globalAlert;
    private JeevomSession session;
    List<DocumentList> documentList;
    boolean isListLoadingFirstTime = true;
    DocumentsAdapter adapterList;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    UserCurrentLocationManager locationManager;

    FrameLayout empty_medvault;
    String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.med_valut);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_med);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.health_locker));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        documentList = new ArrayList<>();
        globalAlert = new GlobalAlert(this);
        session = new JeevomSession(getApplicationContext());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        locationManager = new UserCurrentLocationManager(getApplicationContext());
        // references
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listView_documents);
        listView.setDividerHeight(10);
        listView.setDivider(null);
        btn_upload = (Button) findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(this);
        empty_medvault = (FrameLayout) findViewById(R.id.empty_medvault);
        getUserDocuments();
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    public void getUserDocuments() {

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
                                empty_medvault.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
//								listView.setEmptyView(noItems("No Document in Med Vault"));
                            } else {
                                fillListView(documentList);
                                empty_medvault.setVisibility(View.GONE);
                                listView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });

    }

    protected void fillListView(List<DocumentList> documentList2) {
        if (isListLoadingFirstTime) {
            adapterList = new DocumentsAdapter(MedVaultActivity.this,
                    documentList2);
            listView.setAdapter(adapterList);
        } else {
            adapterList.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        Intent attachIntent = new Intent(this, DocumentUploadActivity.class);
        startActivityForResult(attachIntent, ATTACH);

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
    public void getIndexOfRow(int position) {

        Intent downloadService = new Intent(this, DownloadDocument.class);
        downloadService.putExtra("id", documentList.get(position).getId());
        downloadService.putExtra("name", documentList.get(position).getName());
        downloadService.putExtra("authToken", authToken);
        downloadService.putExtra("memberId",
                String.valueOf(session.getMemberId()));

        startService(downloadService);

    }
}
