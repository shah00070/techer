package com.schoolteacher.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.adapters.SearchListAdapter;
import com.schoolteacher.dialog.GooglePlayAlert;
import com.schoolteacher.main.DoctorProfile;
import com.schoolteacher.main.FacilityProfile;
import com.schoolteacher.main.FloatingActionButton;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.Connectivity;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.mylibrary.util.MyUrlConnectionClient;
import com.schoolteacher.pojos.InstantAppointmentData;
import com.schoolteacher.pojos.JeevCriteria;
import com.schoolteacher.pojos.JeevSearchAvailability;
import com.schoolteacher.pojos.JeevSearchCategory;
import com.schoolteacher.pojos.JeevSearchCriteria;
import com.schoolteacher.pojos.JeevSearchFilter;
import com.schoolteacher.pojos.JeevSearchGender;
import com.schoolteacher.pojos.JeevSearchRequisition;
import com.schoolteacher.pojos.JeevSearchResult;
import com.schoolteacher.pojos.JeevSearchTiming;
import com.schoolteacher.pojos.SearchResultsResponse;
import com.schoolteacher.pojos.ServiceConfiguration;
import com.schoolteacher.pojos.ServiceRequisitionResult;
import com.schoolteacher.service.Search;
import com.schoolteacher.service.ServiceRequisition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class JeevSearchFragment extends ListFragment {

    UserCurrentLocationManager locationManager;
    GlobalAlert globalAlert;
    GooglePlayAlert googleAlert;
    boolean isNewSearch = true;
    int callCount = 0;
    int NoOfItems = 30;
    Gson gson;
    ValuesManager valuesManager;
    private JeevomSession session;
    JeevSearchFilter filter;
    JeevSearchCriteria jeevSearchCriteria;
    private MenuItem search_map_view;
    private SearchListAdapter adapterList;
    static ArrayList<JeevSearchResult> searchResults;
    int totalRecords, currentNoOfRecords;
    View footerView;
    TextView footerText;
    ProgressBar footerProgressBar;

    JeevCriteria jeevCriteria;
    JeevSearchRequisition requisition;
    JeevSearchCategory category;
    JeevSearchTiming timing;
    JeevSearchAvailability availability;
    JeevSearchGender gender;
    FloatingActionButton fabButton;

    String authToken = null;
    boolean isNearMeChecked;
    private boolean isSearchRequested;
    private ListView listView;
    List<ServiceConfiguration> serviceConfigurations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        session = new JeevomSession(getActivity().getApplicationContext());

        locationManager = new UserCurrentLocationManager(getActivity());

        serviceConfigurations = new ArrayList<>();

        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }

        valuesManager = new ValuesManager(getActivity().getApplicationContext());
        globalAlert = new GlobalAlert(getActivity());
        googleAlert = new GooglePlayAlert(getActivity());

        gson = new GsonBuilder().create();

        jeevSearchCriteria = new JeevSearchCriteria();
        jeevCriteria = (JeevCriteria) getArguments().getSerializable("object");
        filter = (JeevSearchFilter) getArguments().getSerializable("filter");
        isNearMeChecked = getArguments().getBoolean("isNearMeChecked");

        // modes object
        requisition = jeevCriteria.getRequisition();

        // category object
        category = jeevCriteria.getCategory();
        // timing object
        timing = jeevCriteria.getTiming();

        // day object
        availability = jeevCriteria.getAvailability();

        // gender
        gender = jeevCriteria.getGender();

        makeSearchObject();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void makeSearchObject() {
        makeFilterObject();
        makeCriteriaObject();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Search Fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            callCount = 0;
            NoOfItems = 30;
            setListShown(false);
            jeevCriteria = null;
            requisition = null;
            category = null;
            timing = null;
            availability = null;
            gender = null;

            isNearMeChecked = data.getBooleanExtra("isNearMeChecked", false);
            jeevCriteria = (JeevCriteria) data.getSerializableExtra("criteria");
            filter = (JeevSearchFilter) data.getSerializableExtra("filter");
            // modes object
            requisition = jeevCriteria.getRequisition();

            // category object
            category = jeevCriteria.getCategory();
            // timing object
            timing = jeevCriteria.getTiming();

            // day object
            availability = jeevCriteria.getAvailability();

            // gender
            gender = jeevCriteria.getGender();


            // show hide map icon
            if (search_map_view != null) {
                if (!isNearMeChecked)
                    search_map_view.setVisible(false);
                else
                    search_map_view.setVisible(true);
            }
            makeSearchObject();
            performSearch();
        }

        if (requestCode == 1010 && resultCode == Activity.RESULT_OK) {
            int itemPos = data.getIntExtra("item_position", 0);
            setInstantAppointment(itemPos);
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fabButton = new FloatingActionButton.Builder(getActivity())
                .withDrawable(getResources().getDrawable(R.drawable.ic_filter))
                .withButtonColor(getResources().getColor(R.color.green))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16).create();
        fabButton.setVisibility(View.GONE);
       /* fabButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                isNewSearch = true;
                Intent openFilterActivity = new Intent(getActivity(),
                        JeevSearchFilters.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("criteria", jeevCriteria);
                bundle.putSerializable("filter", filter);
                bundle.putBoolean("isNearMeChecked", isNearMeChecked);
                bundle.putInt("activity_type", 1); // 0 for advance search and 1
                // for
                // fiter
                openFilterActivity.putExtras(bundle);

                startActivityForResult(openFilterActivity, 1);

            }
        });
*/
    }

   /* public void openFilterActivity() {

        isNewSearch = true;
        Intent openFilterActivity = new Intent(getActivity(),
                JeevSearchFilters.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("criteria", jeevCriteria);
        bundle.putSerializable("filter", filter);
        bundle.putBoolean("isNearMeChecked", isNearMeChecked);
        bundle.putInt("activity_type", 1); // 0 for advance search and 1
        // for
        // fiter
        openFilterActivity.putExtras(bundle);

        startActivityForResult(openFilterActivity, 1);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getListView();
        listView.setDividerHeight(20);
        listView.setDivider(null);

        searchResults = new ArrayList<>();
        setListShown(false);

        performSearch();

        // current no of records
        currentNoOfRecords = searchResults.size();

        listView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
                int count = 0;
                if (currentNoOfRecords < totalRecords) {
                    int threshold = 1;
                    count = listView.getCount();

                    if (scrollState == SCROLL_STATE_IDLE) {

                        if (listView.getLastVisiblePosition() >= count
                                - threshold) {
                            if (!isSearchRequested) {

                                if (count < totalRecords) {
                                    callCount = callCount + 30;
                                    // NoOfItems += 30;

                                    if (listView.getFooterViewsCount() == 0) {
                                        try {
                                            footerView = ((LayoutInflater) getActivity()
                                                    .getSystemService(
                                                            Context.LAYOUT_INFLATER_SERVICE))
                                                    .inflate(
                                                            R.layout.load_more_footer,
                                                            null, false);
                                            footerText = (TextView) footerView
                                                    .findViewById(R.id.footer_text);
                                            footerProgressBar = (ProgressBar) footerView
                                                    .findViewById(R.id.progressBar);
                                            footerText.setText("Loading ...");
                                            listView.addFooterView(footerView);
                                            adapterList.notifyDataSetChanged();
                                        } catch (Exception e) {
                                            e.getMessage();
                                        }
                                    }

                                    if (footerText
                                            .getText()
                                            .toString()
                                            .equals("Internet Connection Problem")) {
                                        footerText.setText("Loading ...");
                                        footerProgressBar
                                                .setVisibility(View.VISIBLE);
                                    }

                                    filter.setSkip(callCount);
                                    filter.setTop(NoOfItems);

                                    if (listView.getFooterViewsCount() == 0) {
                                        try {
                                            footerView = ((LayoutInflater) getActivity()
                                                    .getSystemService(
                                                            Context.LAYOUT_INFLATER_SERVICE))
                                                    .inflate(
                                                            R.layout.load_more_footer,
                                                            null, false);
                                            footerText = (TextView) footerView
                                                    .findViewById(R.id.footer_text);
                                            footerProgressBar = (ProgressBar) footerView
                                                    .findViewById(R.id.progressBar);
                                            footerText.setText("Loading ...");
                                            if (footerProgressBar
                                                    .getVisibility() == View.GONE) {
                                                footerProgressBar
                                                        .setVisibility(View.VISIBLE);
                                            }

                                            listView.addFooterView(footerView);

                                            adapterList.notifyDataSetChanged();
                                        } catch (Exception e) {
                                            e.getMessage();
                                        }
                                    }
                                    performSearch();

                                }
                            }
                        }
                    }
                } else {
                    if (listView.getFooterViewsCount() > 0) {

                        listView.removeFooterView(footerView);
                    }

                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });

    }

    private void performSearch() {

        isSearchRequested = true;
        // setListShown(false);
        RestAdapter searchAdapter = new RestAdapter.Builder()
                .setClient(new MyUrlConnectionClient())
                .setLogLevel(LogLevel.FULL).setLog(new AndroidLog("search"))
                .setEndpoint(JeevOMUtil.searchUrl).build();
        Search jeevSearch = searchAdapter.create(Search.class);

        jeevSearch.search(
                gson.toJson(locationManager.getUserLocation()).toString(), gson.toJson(jeevSearchCriteria).toString(), gson
                        .toJson(filter).toString(), "", String.valueOf(valuesManager
                        .getVersion()), authToken,
                new Callback<com.schoolteacher.pojos.JeevSearch>() {

                    @Override
                    public void failure(RetrofitError arg0) {
                        setListShown(true);

                        isSearchRequested = false;
                        if (arg0.isNetworkError()) {
                            if (!(Connectivity.checkConnectivity(getActivity()))) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION);
                            } else if (arg0.getCause() instanceof SocketTimeoutException) {
                                showAlert(JeevOMUtil.INTERNET_CONNECTION_SLOW);
                            } else if (arg0.getResponse() == null) {
                                showAlert(JeevOMUtil.SOMETHING_WRONG);
                            }
                        } else if (arg0.getResponse().getStatus() == 426) {
                            showGooglePlayAlert(getResources().getString(
                                    R.string.google_update));
                        } else if (arg0.getResponse().getStatus() > 400) {
                            showAlert(JeevOMUtil.SOMETHING_WRONG);
                        } else {
                            String json = new String(((TypedByteArray) arg0
                                    .getResponse().getBody()).getBytes());
                            Gson gson = new GsonBuilder().setPrettyPrinting()
                                    .create();
                            SearchResultsResponse searchResultsResponse = gson
                                    .fromJson(json, SearchResultsResponse.class);
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

                    @Override
                    public void success(com.schoolteacher.pojos.JeevSearch arg0,
                                        Response arg1) {
                        isSearchRequested = false;

                        // total records
                        // totalRecords = arg0.getData().getTotalRecords();
                        totalRecords = arg0.getData().getTotalFilteredRecords();

                        if (isNewSearch) {
                            searchResults.clear();
                        }
                        if (arg0.getData().getSearchResults() != null) {
                            searchResults.addAll(arg0.getData()
                                    .getSearchResults());
                        }

                        currentNoOfRecords = searchResults.size();
                        bindAdapterNewSearch();

                    }
                });
    }

    private void bindAdapterNewSearch() {

        try {
            if (searchResults.size() == 0) {
                if (search_map_view != null)
                    search_map_view.setVisible(false);
                listView.setEmptyView(noItems("Oops!! No results found :("));
                listView.getEmptyView().setVisibility(View.VISIBLE);
            } else {
                // search_map_view.setVisible(true);
                View emptyView = listView.getEmptyView();
                if (emptyView != null)
                    listView.getEmptyView().setVisibility(View.GONE);
            }

            if (currentNoOfRecords < totalRecords) {

                // Add Footer in list View
                if (listView.getFooterViewsCount() == 0) {
                    try {
                        footerView = ((LayoutInflater) getActivity()
                                .getSystemService(
                                        Context.LAYOUT_INFLATER_SERVICE))
                                .inflate(R.layout.load_more_footer, null, false);
                        footerText = (TextView) footerView
                                .findViewById(R.id.footer_text);
                        footerProgressBar = (ProgressBar) footerView
                                .findViewById(R.id.progressBar);
                        footerText.setText("Loading ...");
                        listView.addFooterView(footerView);
                        adapterList.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            }

            if (isNewSearch) {
                adapterList = new SearchListAdapter(JeevSearchFragment.this,
                        searchResults);
                setListAdapter(adapterList);
                listView.setAdapter(adapterList);
                isNewSearch = false;
            } else {
                adapterList.notifyDataSetChanged();
            }

            setListShown(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeFilterObject() {

        filter.setDistance(filter.getDistance());
        filter.setIsPremium(jeevCriteria.isValued());
        filter.setIsRecommended(false);
        filter.setIsVerified(jeevCriteria.isVerified());
        filter.setLatitude(0.0);
        filter.setLongitude(0.0);
        filter.setSkip(callCount);
        filter.setTop(NoOfItems);
        filter.setIsDiscountOffered(jeevCriteria.isDiscount());
        filter.setLocation(filter.getLocation());
    }

    private void makeCriteriaObject() {
        // set search string
        if (!CommonCode.isNullOrEmpty(jeevCriteria.getSearchString())) {
            jeevSearchCriteria.setSearchString(jeevCriteria.getSearchString());
        } else {
            jeevSearchCriteria.setSearchString("");
        }

        // set gender type
        if (gender.isBoth()) {
            jeevSearchCriteria.setGenderType("All");
        } else if (gender.isMale()) {
            jeevSearchCriteria.setGenderType("Male");
        } else {
            jeevSearchCriteria.setGenderType("Female");
        }

        // set timing
        if (!timing.isAfternoon() && !timing.isEvening() && !timing.isMorning()) {
            jeevSearchCriteria.setTimings("All");
        } else if (timing.isAfternoon()) {
            jeevSearchCriteria.setTimings("After");
        } else if (timing.isEvening()) {
            jeevSearchCriteria.setTimings("Even");
        } else
            jeevSearchCriteria.setTimings("Morn");

        // set availability
        jeevSearchCriteria.setAvailability(availability);

        // set categories
        jeevSearchCriteria.setCategory(makeCategories());

        // set service requisition types
        jeevSearchCriteria.setServiceRequisitionTypes(serviceRequisition());

    }

    private List<String> serviceRequisition() {
        List<String> serviceRequistion = new LinkedList<>();

        if (requisition.isChat())
            serviceRequistion.add("SG5");

        if (requisition.isClinic())
            serviceRequistion.add("SG1");

        if (requisition.isEmail()) {
            serviceRequistion.add("SG6");
            serviceRequistion.add("SG7");
        }

        if (requisition.isHome())
            serviceRequistion.add("SG2");

        if (requisition.isPhone())
            serviceRequistion.add("SG4");

        if (requisition.isVideo())
            serviceRequistion.add("SG3");
        return serviceRequistion;
    }

    private List<String> makeCategories() {
        List<String> categories = new LinkedList<>();
        if (category.isAlternateMedicine())
            categories.add("Alternate Medicine");

        if (category.isDoctorClinic())
            categories.add("Doctors & Clinics");

        if (category.isGymFitness())
            categories.add("Gyms, Fitness, Aerobics services");

        if (category.isHealing())
            categories.add("Healing Services including Naturopathy");

        if (category.isHealthcareSupport())
            categories.add("Healthcare support services");

        if (category.isHospitalNursing())
            categories.add("Hospital & Nursing Home");

        if (category.isLabDiagnostic())
            categories.add("Labs, Diagnostics and Imaging centres");

        if (category.isPharmacies())
            categories.add("Pharmacies and Healthcare suppliers");

        if (category.isSpaWellness())
            categories.add("Wellness, Spa, Yoga");

        return categories;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if (v.getId() != R.id.footer) {


            Intent profileIntent;
            if (searchResults.get(position).getType().equals("Professional")) {
                profileIntent = new Intent(getActivity(), DoctorProfile.class);
                profileIntent.putExtra("id", searchResults.get(position)
                        .getProfessionalProfileId());

            } else {
                profileIntent = new Intent(getActivity(), FacilityProfile.class);
                profileIntent.putExtra("id", searchResults.get(position)
                        .getFacilityProfileId());

            }

            startActivity(profileIntent);
            getActivity().overridePendingTransition(R.anim.trans_left_in,
                    R.anim.trans_left_exit);

        }
        super.onListItemClick(l, v, position, id);
    }

    private TextView noItems(String text) {
        TextView emptyView = new TextView(getActivity());
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
    public void onStart() {
        super.onStart();

    }

    // Show Global Message
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    // Show Global Message
    private void showGooglePlayAlert(String message) {
        googleAlert.show();
        googleAlert.setMessage(message);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (isNearMeChecked && !filter.getLocation().trim().equalsIgnoreCase("")) {
            search_map_view = menu.add("Map View");

            search_map_view.setIcon(R.drawable.ic_action_map);

            search_map_view.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
                    | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

            search_map_view.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    // get location value from filter
                    String location = filter.getLocation();
                    location = location.replace(" ", "+");

                    //  if (!CommonCode.isNullOrEmpty(location)) {
                    FetchLatLongFromService latLongService = new FetchLatLongFromService(location);
                    latLongService.execute();
//                    } else {
//                        Intent map_intent = new Intent(getActivity(), MapsSearchViewActivity.class);
//                        map_intent.putExtra("distance", filter.getDistance());
//                        startActivity(map_intent);
//                    }
                    return false;
                }
            });
        }
    }

    // get lat long from address
    public class FetchLatLongFromService extends
            AsyncTask<Void, Void, StringBuilder> {
        String place;

        public FetchLatLongFromService(String place) {
            super();
            this.place = place;

        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
            super.onCancelled();
            this.cancel(true);
        }

        @Override
        protected StringBuilder doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                HttpURLConnection conn = null;
                StringBuilder jsonResults = new StringBuilder();
                String googleMapUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="
                        + this.place + "&sensor=false";

                URL url = new URL(googleMapUrl);
                conn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(
                        conn.getInputStream());
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
                String a = "";
                return jsonResults;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(StringBuilder result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            try {
                JSONObject jsonObj = new JSONObject(result.toString());
                JSONArray resultJsonArray = jsonObj.getJSONArray("results");

                // Extract the Place descriptions from the results
                // resultList = new ArrayList<String>(resultJsonArray.length());

                JSONObject before_geometry_jsonObj = resultJsonArray
                        .getJSONObject(0);

                JSONObject geometry_jsonObj = before_geometry_jsonObj
                        .getJSONObject("geometry");

                JSONObject location_jsonObj = geometry_jsonObj
                        .getJSONObject("location");

                String lat_helper = location_jsonObj.getString("lat");
                double lat = Double.valueOf(lat_helper);

                String lng_helper = location_jsonObj.getString("lng");
                double lng = Double.valueOf(lng_helper);

                LatLng point = new LatLng(lat, lng);

                Intent map_intent = new Intent(getActivity(), MapsSearchViewActivity.class);

                map_intent.putExtra("latitude", lat);
                map_intent.putExtra("longitude", lng);
                map_intent.putExtra("distance", filter.getDistance());
                map_intent.putExtra("place", place);
                startActivity(map_intent);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
    }

    //// SET INSTANT APPOINTMENT  ///////////////
    public void setInstantAppointment(int itemPosition) {
        String type = searchResults.get(itemPosition).getType();

        if (authToken == null && !CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }

        if (type.equals("Professional")) {
            String professionalProfileId = searchResults.get(itemPosition).getProfessionalProfileId();
            InstantAppointmentData appointmentData = new InstantAppointmentData();
            appointmentData.setMemberPublicId(session.getUniquePublicId());
            appointmentData.setProfessionalPubicId(professionalProfileId);
            appointmentData.setServiceRequisitionType("SG1");
            appointmentData.setFacilityPublicId("");
            makeServiceCall(appointmentData);
        } else {
            String facilityProfileId = searchResults.get(itemPosition).getFacilityProfileId();
            InstantAppointmentData appointmentData = new InstantAppointmentData();
            appointmentData.setMemberPublicId(session.getUniquePublicId());
            appointmentData.setFacilityPublicId(facilityProfileId);
            appointmentData.setServiceRequisitionType("SG1");
            appointmentData.setProfessionalPubicId("");
            makeServiceCall(appointmentData);
        }

    }

    private void makeServiceCall(final InstantAppointmentData instantAppointmentData) {

        RestAdapter serviceRequisitionAdapter = new RestAdapter.Builder()
                .setLogLevel(LogLevel.FULL)
                .setLog(new AndroidLog("service_requisition"))
                .setEndpoint(JeevOMUtil.baseUrl).build();
        ServiceRequisition service_req_interface = serviceRequisitionAdapter
                .create(ServiceRequisition.class);

        final DialogFragment progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(getFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        service_req_interface.makeInstantServiceRequest(locationManager.getUserLocation(), authToken, instantAppointmentData,

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

                            if (arg0.getResponse().getStatus() == 400) {
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

                            // String referenceNo = arg0.getData();

                            showAlertFinish(getResources().getString(R.string.instant_appoint_done));

                        }
                    }
                });
    }

    private void showAlertFinish(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

}
