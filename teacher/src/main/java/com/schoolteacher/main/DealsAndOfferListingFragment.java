package com.schoolteacher.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.adapters.DealsAndOfferAdapter;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.CallToActionMessage;
import com.schoolteacher.pojos.DealOfferPackageAttribute;
import com.schoolteacher.pojos.DealsAndOfferResponse;
import com.schoolteacher.pojos.GrabOfferData;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceRequisitionResult;
import com.schoolteacher.service.ServiceRequisition;

import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by chandan on 8/12/15.
 */
public class DealsAndOfferListingFragment extends Fragment {

    private List<DealsAndOfferResponse> dealsList;
    private DealsAndOfferAdapter offerAdapter;
    private GlobalAlert globalAlert;
    private JeevomSession session;
    private String authToken;
    private UserCurrentLocationManager locationManager;


    public static DealsAndOfferListingFragment getInstance(List<DealsAndOfferResponse> dealsList) {

        DealsAndOfferListingFragment dealsOfferListFragment = new DealsAndOfferListingFragment();
        dealsOfferListFragment.dealsList = dealsList;
        return dealsOfferListFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        globalAlert = new GlobalAlert(activity);
        session = new JeevomSession(activity);
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
        locationManager = new UserCurrentLocationManager(activity);


    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Deals & Offer listing Fragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        offerAdapter = new DealsAndOfferAdapter(DealsAndOfferListingFragment.this, dealsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.deals_offer_listing_fragment, container,
                false);

        ListView dealsListView = (ListView) rootView.findViewById(R.id.listview_offers);
        dealsListView.setEmptyView(rootView.findViewById(R.id.emptyView));
        dealsListView.setAdapter(offerAdapter);
        return rootView;
    }

    public void requestForGrabOffer(int position) {

        if (session.getLoggedInStatus()) {

            GrabOfferData requestSendMessage = new GrabOfferData();

            requestSendMessage.setByMemberId(String.valueOf(session
                    .getMemberId()));

            requestSendMessage.setForMemberId(String.valueOf(session
                    .getMemberId()));
            requestSendMessage.setCreatedBy(String.valueOf(session
                    .getMemberId()));
            requestSendMessage.setUpdatedBy(String.valueOf(session
                    .getMemberId()));

            requestSendMessage.setDefaultServiceConfigurationId("15");

            requestSendMessage.setForAge(session.getAge());
            requestSendMessage.setForGender(session.getGender());
            requestSendMessage.setForName(session.getName());

            requestSendMessage.setIsBasicDetailsShared(false);
            requestSendMessage.setIsMedicalInfoShared(false);
            requestSendMessage.setIsHealthTrackShared(false);
            requestSendMessage.setIsSupportRequest(true);
            requestSendMessage.setIsAppointment(false);
            requestSendMessage.setIsIndirectRequest(false);

            //requestSendMessage.setServiceConfigurationId("23961");

            List<CallToActionMessage> messageList = new LinkedList<>();
            CallToActionMessage message = new CallToActionMessage();

            if (session.getLoggedInStatus()) {
                message.setFromMemberId(String.valueOf(session.getMemberId()));
            }

            message.setToProfessionalId(dealsList.get(position).getProfessionalPublicId());
            message.setSubject("Claim Offer");
            message.setMessage("Grab offer request");
            messageList.add(message);
            requestSendMessage.setMessages(messageList);

            DealOfferPackageAttribute packageAttribute = new DealOfferPackageAttribute();
            packageAttribute.setName(dealsList.get(position).getName());
            packageAttribute.setId(dealsList.get(position).getId());
            packageAttribute.setDescription(dealsList.get(position).getDescription());
            packageAttribute.setPromotionCode(dealsList.get(position).getCode());

            requestSendMessage.setAttributes(packageAttribute);

            makeServiceCall(requestSendMessage);
        } else {
            Toast.makeText(getActivity(), "Please login!", Toast.LENGTH_LONG).show();
        }

    }

    public void makeServiceCall(final GrabOfferData requestSendMessage) {

        RestAdapter serviceRequisitionAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("care"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_req_interface = serviceRequisitionAdapter
                .create(ServiceRequisition.class);
        final DialogFragment progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        service_req_interface.makeServiceRequestClaimOffer(locationManager.getUserLocation(), authToken, requestSendMessage,

                new Callback<ServiceRequisitionResult>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        progressDialog.dismissAllowingStateLoss();

                        if (arg0.isNetworkError()) {
                            if (!(Connectivity
                                    .checkConnectivity(getActivity()))) {
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
                            SearchResultsResponse searchResultsResponse = gson
                                    .fromJson(json, SearchResultsResponse.class);
                            String code = searchResultsResponse.getStatus().getCode();
                            String message = searchResultsResponse.getStatus()
                                    .getMessage();

                            if (code.equals("IOE-1000")) {
                                showAlert(message);
                            } else if (code.equals("BE-1001")) {
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
                    public void success(ServiceRequisitionResult arg0, Response arg1) {
                        progressDialog.dismissAllowingStateLoss();
                        String code = arg0.getStatus().getCode();

                        if (code.equals("Success")) {

                            String referenceNo = arg0.getData();

                            showAlert("Thank you for Submitting your request."
                                    + "\n" + "Your Reference Number is: " + referenceNo);

                        }

                    }
                });
    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

}
