package com.schoolteacher.serviceRequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.dialog.DocumentOptionDialog;
import com.schoolteacher.interfaces.DocumentOption;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.medvault.DocumentUploadActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.GlobelAlertWithFinish;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.CallToActionRequest;
import com.schoolteacher.pojos.DocumentList;
import com.schoolteacher.pojos.DocumentObject;
import com.schoolteacher.pojos.JeevFacilityInfo;
import com.schoolteacher.pojos.JeevProfileInfo;
import com.schoolteacher.pojos.LabTestData;
import com.schoolteacher.pojos.LabTestFilterResults;
import com.schoolteacher.pojos.Medicine;
import com.schoolteacher.pojos.OrderAttributes;
import com.schoolteacher.pojos.ResponseApi;
import com.schoolteacher.pojos.SystemServiceConfigurationResult;
import com.schoolteacher.service.ServiceRequisition;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class PurchaseRequest extends ActionBarActivity implements
        OnClickListener, OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener, DocumentOption {
    UserCurrentLocationManager locationManager;


    CallToActionRequest requestSendMessage;
    private RelativeLayout image_name;
    private final int ATTACH = 200;
    private final int SELECT = 400;
    boolean isDirectPurchaseRequest;
    private DocumentList document;
    private List<DocumentList> selectedDocuments;
    private ArrayList<String> medicineHintsList;
    private RelativeLayout btn_attachment;
    private LinearLayout action_container, documents_container;
    private Button btn_submit;
    private GlobalAlert globalAlert;
    private GlobelAlertWithFinish globalAlertFinish;
    private String imageUrl = null, requestor_type, titleType = "Mr.", timePreference, referenceNo;
    // sessions
    private JeevomSession session;
    private ValuesManager valuesManager;

    private LinearLayout address_layout;
    private EditText address_value;
    private TextView shared_text;

    private CheckBox general_medicine, general_health;
    private LinearLayout medicine_order_layout, general_healthcare_layout,
            container_order;
    private Button add_more;
    private DialogFragment progressDialog;

    private JeevProfileInfo professional_profile;
    private JeevFacilityInfo facility_profile;
    private double fees;
    private int service_id;
    private CircleImageView prof_image;
    private TextView prof_name;
    private EditText general_message;

    private RadioGroup time_group;
    private Toolbar toolbar;
    private List<DocumentObject> documentList;
    Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeev_purchase_request);
        selectedDocuments = new LinkedList<DocumentList>();
        documentList = new ArrayList<>();
        getAlertDialogReferences();
        gson = new GsonBuilder().create();
        // sessions
        session = new JeevomSession(getApplicationContext());
        medicineHintsList = new ArrayList<>();
        valuesManager = new ValuesManager(getApplicationContext());
        locationManager=new UserCurrentLocationManager(getApplicationContext());
        setUpActionBar();

        Intent intent = getIntent();
        if (this.getIntent().getExtras() != null) {
            requestor_type = intent.getStringExtra("service_requestor");

            if (requestor_type.equals("professional")) {
                professional_profile = (JeevProfileInfo) intent
                        .getSerializableExtra("profile_object");
            } else {
                facility_profile = (JeevFacilityInfo) intent
                        .getSerializableExtra("profile_object");
            }

            fees = intent.getDoubleExtra("fee", 0);
            service_id = intent.getIntExtra("service_id", 0);
        } else {
            isDirectPurchaseRequest = true;
        }

        makeViewReferences();
        setInitialView();

    }

    private void getAlertDialogReferences() {
        globalAlert = new GlobalAlert(this);
        globalAlertFinish = new GlobelAlertWithFinish(this);
        globalAlertFinish.setCancelable(false);
    }

    private void setUpActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_purchase);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Purchase Request");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void makeViewReferences() {
        image_name = (RelativeLayout) findViewById(R.id.image_name);
        btn_attachment = (RelativeLayout) findViewById(R.id.btn_attachment);
        btn_attachment.setOnClickListener(this);
        documents_container = (LinearLayout) findViewById(R.id.documents_container);

        prof_name = (TextView) findViewById(R.id.prof_name);
        prof_image = (CircleImageView) findViewById(R.id.prof_image);

        action_container = (LinearLayout) findViewById(R.id.action_container);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        time_group = (RadioGroup) findViewById(R.id.time_group);
        time_group.setOnCheckedChangeListener(this);
        general_medicine = (CheckBox) findViewById(R.id.general_medicine);
        general_health = (CheckBox) findViewById(R.id.general_health);
        general_medicine.setOnCheckedChangeListener(this);
        general_health.setOnCheckedChangeListener(this);

        medicine_order_layout = (LinearLayout) findViewById(R.id.medicine_order_layout);
        general_healthcare_layout = (LinearLayout) findViewById(R.id.general_healthcare_layout);
        general_message = (EditText) findViewById(R.id.general_message);
        container_order = (LinearLayout) findViewById(R.id.container_order);
        setOrderLayout();

        add_more = (Button) findViewById(R.id.add_more);
        add_more.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (container_order.getChildCount() < 15)
                    setOrderLayout();

            }
        });

    }

    private void setOrderLayout() {
        LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = inflator.inflate(R.layout.medical_order_form, null);
        TextView serial_no = (TextView) view.findViewById(R.id.serial_no);
        AutoCompleteTextView item_name = (AutoCompleteTextView) view
                .findViewById(R.id.item_name);
        ImageView delete = (ImageView) view.findViewById(R.id.delete);

        if (container_order.getChildCount() == 0) {
            delete.setVisibility(View.INVISIBLE);
        }

        if (container_order.getChildCount() % 2 == 0) {
            Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
                    R.anim.trans_left_in);
            view.startAnimation(fadeInAnimation);
        } else {
            Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
                    R.anim.trans_right_in);
            view.startAnimation(fadeInAnimation);
        }
        container_order.addView(view);
        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                ((LinearLayout) view.getParent()).removeView(view);
                container_order.postInvalidate();

                for (int i = 0; i < container_order.getChildCount(); i++) {
                    LinearLayout childAt = (LinearLayout) container_order
                            .getChildAt(i);

                    View childAt2 = childAt.getChildAt(0);
                    if (childAt2 instanceof TextView) {
                        ((TextView) childAt2).setText(String.valueOf(i + 1));
                    }

                }
            }
        });
        serial_no.setText(String.valueOf(container_order.getChildCount()));

        ArrayAdapter<String> hintsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, medicineHintsList);
        item_name.setThreshold(1);// will start working from first character
        item_name.setAdapter(hintsAdapter);
    }

    private void setInitialView() {

        // set image
        if (!isDirectPurchaseRequest) {
            if (requestor_type.equals("professional")) {
                if (!(CommonCode.isNullOrEmpty(professional_profile.getPhoto()))) {
                    imageUrl = professional_profile.getPhoto();
                }
            } else {
                if (!(CommonCode.isNullOrEmpty(facility_profile
                        .getFacilityProfile().getProfilePhoto()))) {
                    imageUrl = facility_profile.getFacilityProfile()
                            .getProfilePhoto();
                }
            }

            Picasso.with(PurchaseRequest.this).load(imageUrl)
                    .placeholder(R.drawable.jeevom_back)
                    .error(R.drawable.jeevom_back).into(prof_image);

            // set name
            StringBuilder name = new StringBuilder();
            if (requestor_type.equals("professional")) {
                if (!CommonCode.isNullOrEmpty(professional_profile.getTitle())) {
                    name.append(professional_profile.getTitle() + " ");
                }
                if (!CommonCode.isNullOrEmpty(professional_profile
                        .getFirstName())) {
                    name.append(professional_profile.getFirstName() + " ");
                }
                if (!CommonCode.isNullOrEmpty(professional_profile
                        .getLastName())) {
                    name.append(professional_profile.getLastName());
                }

            } else {
                if (!CommonCode.isNullOrEmpty(facility_profile
                        .getFacilityProfile().getName())) {
                    name.append(facility_profile.getFacilityProfile().getName());
                }
            }

            if (!CommonCode.isNullOrEmpty(name.toString())) {
                prof_name.setText(name.toString().trim());
            }
        } else {
            image_name.setVisibility(View.GONE);
        }
        getSystemServiceConfigurations();
    }

    private void getSystemServiceConfigurations() {
        RestAdapter serviceConfigAdapter = new RestAdapter.Builder()
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_config_interface = serviceConfigAdapter
                .create(ServiceRequisition.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        service_config_interface.getSystemServiceConfigurations(locationManager.getUserLocation(),"SG9",

                new Callback<SystemServiceConfigurationResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialog.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(PurchaseRequest.this))) {
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
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            SystemServiceConfigurationResult searchResultsResponse = gson
                                    .fromJson(json,
                                            SystemServiceConfigurationResult.class);
                            String code = searchResultsResponse.getStatus().getCode();
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
                            }
                        }
                    }

                    @Override
                    public void success(SystemServiceConfigurationResult arg0,
                                        Response arg1) {
                        progressDialog.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {

                            if (arg0.getData() != null || arg0.getData().size() > 0) {
                                // service_id = arg0.getData().get(0).getId();

                                if (arg0.getData().get(0).isIsAttachDocumentAllowed())
                                    btn_attachment.setVisibility(View.VISIBLE);
                            }

                            getTestsHintValues();
                        }
                    }
                });

    }

    protected void getTestsHintValues() {

        RestAdapter medicineListAdapter = new RestAdapter.Builder()
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition test_interface = medicineListAdapter
                .create(ServiceRequisition.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        test_interface
                .getMedicinesList( new Callback<ResponseApi<LabTestData>>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialog.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(PurchaseRequest.this))) {
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
                            LabTestFilterResults searchResultsResponse = gson
                                    .fromJson(json, LabTestFilterResults.class);
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
                            }
                        }
                    }

                    @Override
                    public void success(ResponseApi<LabTestData> arg0,
                                        Response arg1) {
                        progressDialog.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();
                        if (code.equals("Success")) {

                            List<Medicine> medicine = arg0.getData()
                                    .getMedicine();

                            if (medicine != null && medicine.size() > 0) {
                                for (Medicine medicineObject : medicine) {

                                    medicineHintsList.add(medicineObject
                                            .getDisplayName());

                                }
                            }
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        // TODO Auto-generated method stub
        super.onActivityResult(arg0, arg1, arg2);
        if (arg0 == ATTACH && arg1 == ATTACH) {
            if (arg2.getExtras() != null) {
                if (session.getLoggedInStatus()) {
                    document = (DocumentList) arg2
                            .getSerializableExtra("document");
                    selectedDocuments.add(document);
                    showDocumentsOnScreen();
                } else {
                    Bundle getBundle = arg2.getExtras();
                    DocumentObject documentObject = (DocumentObject) getBundle
                            .getSerializable("document");
                    documentList.add(documentObject);
                    showUploadedDocumentsOnScreen();
                }
            }

        }

        if (arg0 == SELECT && arg1 == SELECT) {

            Bundle getBundle = arg2.getExtras();
            ArrayList<DocumentList> parcelableArrayList = getBundle
                    .getParcelableArrayList("documents");
            selectedDocuments.addAll(parcelableArrayList);
            showDocumentsOnScreen();

        }
        if (arg0 == 1) {
            finish();
        }

    }

    private void showUploadedDocumentsOnScreen() {

        if (documents_container.getChildCount() > 0) {
            documents_container.removeAllViews();
        }

        if (documentList.size() > 0) {
            for (int i = 0; i < documentList.size(); i++) {

                LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                final View view = inflator.inflate(
                        R.layout.attach_document_row, null);
                TextView document_name = (TextView) view
                        .findViewById(R.id.document_name);

                ImageView delete = (ImageView) view.findViewById(R.id.remove);

                document_name.setText(documentList.get(i).getName());
                final String id = documentList.get(i).getName();

                documents_container.addView(view);
                delete.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        ((LinearLayout) view.getParent()).removeView(view);
                        documents_container.postInvalidate();

                        for (int j = 0; j < documentList.size(); j++) {
                            if (documentList.get(j).getName() == id) {
                                documentList.remove(j);
                            }
                        }

                    }
                });

            }
        }

    }

    private void showDocumentsOnScreen() {

        if (documents_container.getChildCount() > 0) {
            documents_container.removeAllViews();
        }

        if (selectedDocuments.size() > 0) {
            for (int i = 0; i < selectedDocuments.size(); i++) {

                LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                final View view = inflator.inflate(
                        R.layout.attach_document_row, null);
                TextView document_name = (TextView) view
                        .findViewById(R.id.document_name);

                ImageView delete = (ImageView) view.findViewById(R.id.remove);

                document_name.setText(selectedDocuments.get(i).getName());
                final int id = selectedDocuments.get(i).getId();

                documents_container.addView(view);
                delete.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        ((LinearLayout) view.getParent()).removeView(view);
                        documents_container.postInvalidate();

                        for (int j = 0; j < selectedDocuments.size(); j++) {
                            if (selectedDocuments.get(j).getId() == id) {
                                selectedDocuments.remove(j);
                            }
                        }

                    }
                });

            }
        }

    }

    // set Gender

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_submit:
                sendRequest();
                break;
            case R.id.btn_attachment:

                if (session.getLoggedInStatus()) {
                    // user logged in -- Show user dialog to choose from device or
                    // MedValut

                    DocumentOptionDialog showDialog = new DocumentOptionDialog(this);
                    showDialog.show();
                } else {
                    // User is not logged in -- take him directly to Upload document
                    // screen
                    Intent attachIntent = new Intent(this,
                            DocumentUploadActivity.class);
                    startActivityForResult(attachIntent, ATTACH);
                }
                break;
        }

    }

    private void sendRequest() {
        boolean isAllValid = true;

        if (!general_medicine.isChecked() && !general_health.isChecked()) {
            Crouton.makeText(
                    PurchaseRequest.this,
                    "Please enter either medicine or general healthcare product to order",
                    Style.ALERT).show();
            return;

        }

        if (general_medicine.isChecked()) {
            for (int i = 0; i < container_order.getChildCount(); i++) {
                LinearLayout childAt = (LinearLayout) container_order
                        .getChildAt(i);

                AutoCompleteTextView childAt2 = (AutoCompleteTextView) childAt
                        .getChildAt(1);
                EditText childAt3 = (EditText) childAt.getChildAt(2);

                if (CommonCode.isNullOrEmpty(childAt2.getText().toString())) {
                    isAllValid = false;
                    Crouton.makeText(PurchaseRequest.this,
                            "Please enter Order Item", Style.ALERT).show();
                    childAt2.requestFocus();
                    return;
                }

                if (CommonCode.isNullOrEmpty(childAt3.getText().toString())) {
                    isAllValid = false;
                    Crouton.makeText(PurchaseRequest.this,
                            "Please enter Order Item Quantity", Style.ALERT)
                            .show();
                    childAt3.requestFocus();
                    return;
                }

                if (Integer.valueOf(childAt3.getText().toString()) > 100) {
                    isAllValid = false;
                    Crouton.makeText(
                            PurchaseRequest.this,
                            "Order Item Quantity must be less than or equal to 100",
                            Style.ALERT).show();
                    childAt2.requestFocus();
                    return;
                }
            }
        }

        if (general_health.isChecked()) {
            if (CommonCode.isNullOrEmpty(general_message.getText().toString()
                    .trim())) {
                isAllValid = false;
                Crouton.makeText(PurchaseRequest.this,
                        "Please enter general healtcare details", Style.ALERT)
                        .show();
                general_message.requestFocus();
            }
        }

        if (CommonCode.isNullOrEmpty(timePreference)) {
            isAllValid = false;
            Crouton.makeText(PurchaseRequest.this,
                    "Please select delivery time preference", Style.ALERT)
                    .show();
        }
        if (isAllValid) {
            makeCall();
        }
    }

    private void makeCall() {
        requestSendMessage = new CallToActionRequest();
        requestSendMessage.setId(0);
        requestSendMessage.setDocumentIds("");
        if (!isDirectPurchaseRequest) {
            requestSendMessage.setServiceConfigurationId(String
                    .valueOf(service_id));
        }
        requestSendMessage.setIsSupportRequest(false);

        if (!isDirectPurchaseRequest) {
            requestSendMessage.setFees(String.valueOf(fees));

            if (requestor_type.equalsIgnoreCase("professional")) {
                requestSendMessage.setToProfessionalId(String
                        .valueOf(professional_profile.getId()));
            } else {
                requestSendMessage
                        .setToFacilityId(String.valueOf(facility_profile
                                .getFacilityProfile().getId()));
            }
        } else {
            requestSendMessage.setIsSupportRequest(true);
            requestSendMessage.setDefaultServiceConfigurationId("8");
        }

        if (selectedDocuments != null && selectedDocuments.size() > 0) {

            StringBuilder stringValue = new StringBuilder();
            for (int i = 0; i < selectedDocuments.size(); i++) {

                if (i == 0) {
                    stringValue.append(selectedDocuments.get(i).getId());
                } else {
                    stringValue.append(", " + selectedDocuments.get(i).getId());
                }
            }

            requestSendMessage.setDocumentIds(stringValue.toString());
        } else {
            requestSendMessage.setDocumentIds("");
        }
        // order attributes
        OrderAttributes attr = new OrderAttributes();
        StringBuilder cat = new StringBuilder();

        if (general_medicine.isChecked()) {
            cat.append("Medicine");
            if (general_health.isChecked()) {
                cat.append("|HealthCareProduct");
            }

            for (int i = 0; i < container_order.getChildCount(); i++) {
                LinearLayout childAt = (LinearLayout) container_order
                        .getChildAt(i);

                EditText childAt2 = (EditText) childAt.getChildAt(1);
                EditText childAt3 = (EditText) childAt.getChildAt(2);
                if (i == 0) {
                    attr.setItem1Name(childAt2.getText().toString().trim());
                    attr.setItem1Qty(childAt3.getText().toString().trim());
                }
                if (i == 1) {
                    attr.setItem2Name(childAt2.getText().toString().trim());
                    attr.setItem2Qty(childAt3.getText().toString().trim());
                }
                if (i == 2) {
                    attr.setItem3Name(childAt2.getText().toString().trim());
                    attr.setItem3Qty(childAt3.getText().toString().trim());
                }
                if (i == 3) {
                    attr.setItem4Name(childAt2.getText().toString().trim());
                    attr.setItem4Qty(childAt3.getText().toString().trim());
                }
                if (i == 4) {
                    attr.setItem5Name(childAt2.getText().toString().trim());
                    attr.setItem5Qty(childAt3.getText().toString().trim());
                }
                if (i == 5) {
                    attr.setItem6Name(childAt2.getText().toString().trim());
                    attr.setItem6Qty(childAt3.getText().toString().trim());
                }
                if (i == 6) {
                    attr.setItem7Name(childAt2.getText().toString().trim());
                    attr.setItem7Qty(childAt3.getText().toString().trim());
                }
                if (i == 7) {
                    attr.setItem8Name(childAt2.getText().toString().trim());
                    attr.setItem8Qty(childAt3.getText().toString().trim());
                }
                if (i == 8) {
                    attr.setItem9Name(childAt2.getText().toString().trim());
                    attr.setItem9Qty(childAt3.getText().toString().trim());
                }
                if (i == 9) {
                    attr.setItem10Name(childAt2.getText().toString().trim());
                    attr.setItem10Qty(childAt3.getText().toString().trim());
                }
                if (i == 10) {
                    attr.setItem11Name(childAt2.getText().toString().trim());
                    attr.setItem11Qty(childAt3.getText().toString().trim());
                }
                if (i == 11) {
                    attr.setItem12Name(childAt2.getText().toString().trim());
                    attr.setItem12Qty(childAt3.getText().toString().trim());
                }
                if (i == 12) {
                    attr.setItem13Name(childAt2.getText().toString().trim());
                    attr.setItem13Qty(childAt3.getText().toString().trim());
                }
                if (i == 13) {
                    attr.setItem14Name(childAt2.getText().toString().trim());
                    attr.setItem14Qty(childAt3.getText().toString().trim());
                }
                if (i == 14) {
                    attr.setItem15Name(childAt2.getText().toString().trim());
                    attr.setItem15Qty(childAt3.getText().toString().trim());
                }
            }

        } else {
            if (general_health.isChecked()) {
                cat.append("HealthCareProduct");
            }
        }

        attr.setCategory(cat.toString());
        attr.setGeneralHealthCareRequirements(general_message.getText()
                .toString().trim());
        attr.setTimePreference(timePreference);
        requestSendMessage.setAttributes(attr);

        goToStepTwo();

    }

    private void goToStepTwo() {
        Intent stepTwoIntent = new Intent(this, PurchaseRequestStepTwo.class);
        Bundle bundle = new Bundle();
        if (isDirectPurchaseRequest) {
            bundle.putBoolean("isDirectRequest", true);
            bundle.putString("img_url", null);
            bundle.putString("name", null);
        } else {
            bundle.putString("img_url", imageUrl);
            bundle.putString("name", prof_name.getText().toString());
        }
        bundle.putSerializable("requestObject", requestSendMessage);
        if (!session.getLoggedInStatus()) {
            bundle.putSerializable("documents", (Serializable) documentList);
        }
        stepTwoIntent.putExtras(bundle);
        startActivityForResult(stepTwoIntent, 1);
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    private void showAlertFinish(String message) {
        globalAlertFinish.show();
        globalAlertFinish.setMessage(message);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.radio_button_immediate)
            timePreference = "Immediate";
        if (checkedId == R.id.radio_button_morning)
            timePreference = "Morning";
        if (checkedId == R.id.radio_button_afternoon)
            timePreference = "Afternoon";
        if (checkedId == R.id.radio_button_evening)
            timePreference = "Evening";

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.general_medicine:
                if (isChecked) {
                    medicine_order_layout.setVisibility(View.VISIBLE);
                    Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
                            R.anim.fade_in);
                    medicine_order_layout.startAnimation(fadeInAnimation);
                } else
                    medicine_order_layout.setVisibility(View.GONE);

                break;

            case R.id.general_health:

                if (isChecked) {
                    general_healthcare_layout.setVisibility(View.VISIBLE);
                    Animation fadeInAnimation = AnimationUtils.loadAnimation(this,
                            R.anim.fade_in);
                    general_healthcare_layout.startAnimation(fadeInAnimation);
                } else {
                    general_healthcare_layout.setVisibility(View.GONE);
                }

                break;
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
    public void optionChoosenForDocumentUpload(String value) {

        if (value.equals("device")) {
            Intent attachIntent = new Intent(this, DocumentUploadActivity.class);
            startActivityForResult(attachIntent, ATTACH);
        } else {
            Intent medvaultIntent = new Intent(this,
                    SelectDocumentForAttachment.class);
            startActivityForResult(medvaultIntent, SELECT);
        }

    }


}