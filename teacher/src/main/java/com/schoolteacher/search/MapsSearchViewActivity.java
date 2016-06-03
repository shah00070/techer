package com.schoolteacher.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.schoolteacher.R;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.main.CircleImageView;
import com.schoolteacher.main.DoctorProfile;
import com.schoolteacher.main.FacilityProfile;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.JeevAddress2;
import com.schoolteacher.pojos.JeevSearchResult;
import com.schoolteacher.pojos.MarkerSnippet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MapsSearchViewActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ArrayList<JeevSearchResult> searchResults;
    UserCurrentLocationManager userLocationManager;
    private int distance;
    private JeevSearchResult searchObject;
    private double latitude, longitude;
    private String place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_maps_search_view);
        Intent intent = getIntent();
        distance = intent.getIntExtra("distance", 0);
        if (intent.getExtras().containsKey("latitude")) {
            latitude = intent.getDoubleExtra("latitude", 0);
        }
        if (intent.getExtras().containsKey("longitude")) {
            longitude = intent.getDoubleExtra("longitude", 0);
        }
        if (intent.getExtras().containsKey("place")) {
            place = intent.getStringExtra("place");
        }

        userLocationManager = new UserCurrentLocationManager(getApplicationContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        // set Markers for all the search results
        searchResults = JeevSearchFragment.searchResults;
        for (JeevSearchResult object : searchResults
                ) {
            searchObject = object;
            if (object != null) {
                if (object.getContactInformations() != null) {
                    if (object.getContactInformations().size() > 0) {

                        String id;
                        String educational_qualification = null;
                        String image_url = null;
                        StringBuilder name = new StringBuilder();


                        String type = null;
                        if (searchObject.getType().equals("Professional")) {
                            id = searchObject
                                    .getProfessionalProfileId();

                            if (!(CommonCode.isNullOrEmpty(object.getProfilePhoto()))) {
                                image_url = object.getProfilePhoto().replace(" ", "%20");
                            }

                            type = searchObject.getType();
                            // for professional
                            String doctor_title = object.getTitle();
                            String firstName = object.getFirstName();
                            String lastName = object.getLastName();


                            if (!CommonCode.isNullOrEmpty(doctor_title)) {
                                if (!CommonCode.isNullOrEmpty(firstName)) {
                                    if (!CommonCode.isNullOrEmpty(lastName)) {
                                        name.append(doctor_title + " " + firstName
                                                + " " + lastName);
                                    } else {
                                        name.append((doctor_title + " " + firstName)
                                                .trim());
                                    }
                                } else if (!CommonCode.isNullOrEmpty(lastName)) {
                                    name.append((doctor_title + " " + lastName).trim());
                                }

                            } else {

                                if (!CommonCode.isNullOrEmpty(firstName)) {

                                    if (!CommonCode.isNullOrEmpty(lastName)) {
                                        name.append(firstName + " " + lastName);
                                    } else {
                                        name.append(firstName);
                                    }
                                } else if (!CommonCode.isNullOrEmpty(lastName)) {

                                    name.append(lastName);
                                }

                            }
                        } else {
                            id = searchObject
                                    .getFacilityProfileId();
                            type = searchObject.getType();
                            name.append(object.getFullName());
                            if (!(CommonCode.isNullOrEmpty(object.getProfilePhoto()))) {
                                image_url = object.getProfilePhoto().replace(" ", "%20");
                            }
                        }

                        JeevAddress2 address = object.getContactInformations().get(0).getAddress();
                        LatLng loc = new LatLng(address.getLatitude(), address.getLongitude());

                        List<String> educationalQualifications = object
                                .getEducationalQualifications();
                        if (educationalQualifications != null
                                && !educationalQualifications.isEmpty()) {

                            educational_qualification = CommonCode
                                    .toCommaSeparatedString(object
                                            .getEducationalQualifications());

                        }

                        final Gson gson = new Gson(); // remark: only one Gson instane is needed
                        String snippetString = gson.toJson(new MarkerSnippet(id, type, name.toString(), educational_qualification, image_url));


                        map.addMarker(new MarkerOptions().position(loc).snippet(snippetString));


                        // Setting a custom info window adapter for the google map
                        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                            // Use default InfoWindow frame
                            @Override
                            public View getInfoWindow(Marker arg0) {
                                return null;
                            }

                            // Defines the contents of the InfoWindow
                            @Override
                            public View getInfoContents(Marker arg0) {

                                String snippet = arg0.getSnippet();

                                // Getting view from the layout file info_window_layout
                                View v = getLayoutInflater().inflate(R.layout.custom_info_window, null);

                                // Getting the position from the marker
                                LatLng latLng = arg0.getPosition();

                                // Getting reference to the TextView to set latitude
                                TextView nameValue = (TextView) v.findViewById(R.id.name);

                                // Getting reference to the TextView to set longitude
                                TextView degree = (TextView) v.findViewById(R.id.degree);


                                // image view
                                CircleImageView image_view = (CircleImageView) v.findViewById(R.id.image_view);


                                if (!CommonCode.isNullOrEmpty(snippet)) {

                                    if (!("UserLocation".equalsIgnoreCase(snippet))) {
                                        MarkerSnippet snippetValue = gson.fromJson(snippet, MarkerSnippet.class);
                                        if (snippetValue != null) {
                                            nameValue.setText(snippetValue.getName());
                                            degree.setText(snippetValue.getDegree());
                                            if (!CommonCode.isNullOrEmpty(snippetValue.getImage())) {
                                                Picasso.with(MapsSearchViewActivity.this).load(snippetValue.getImage())
                                                        .placeholder(R.drawable.jeevom_back)
                                                        .error(R.drawable.jeevom_back).into(image_view);
                                            }
                                        }
                                    } else {
                                        nameValue.setText(place);
                                        degree.setVisibility(View.GONE);
                                        image_view.setVisibility(View.GONE);
                                    }
                                }
                                // Returning the view containing InfoWindow contents
                                return v;

                            }
                        });


                        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {

                                Intent profileIntent = null;
                                String snippet = marker.getSnippet();

                                if (!CommonCode.isNullOrEmpty(snippet)) {
                                    if (!("UserLocation".equalsIgnoreCase(snippet))) {
                                        MarkerSnippet snippetValue = gson.fromJson(snippet, MarkerSnippet.class);
                                        if (snippetValue != null) {

                                            if ("Professional".equals(snippetValue.getType())) {
                                                profileIntent = new Intent(MapsSearchViewActivity.this, DoctorProfile.class);
                                                profileIntent.putExtra("id", snippetValue.getId());
                                                //Toast.makeText(MapsSearchViewActivity.this,snippetValue.getId()+" "+snippetValue.getType(), Toast.LENGTH_SHORT).show();
                                            } else if ("facility".equalsIgnoreCase(snippetValue.getType())) {
                                                profileIntent = new Intent(MapsSearchViewActivity.this, FacilityProfile.class);
                                                profileIntent.putExtra("id", snippetValue.getId());
                                                // Toast.makeText(MapsSearchViewActivity.this, snippetValue.getId()+" "+snippetValue.getType(), Toast.LENGTH_SHORT).show();
                                            }
                                            startActivity(profileIntent);
                                        }
                                    }
                                }
                            }
                        });

                    }
                }
            }
        }


        //set Marker for user current location
        if (latitude > 0 && longitude > 0) {
            LatLng loc = new LatLng(latitude, longitude);
            Marker myMarker = map.addMarker(new MarkerOptions().position(loc).snippet("UserLocation").icon(BitmapDescriptorFactory.fromResource(R.drawable.my_loc_marker)));


            map.moveCamera(CameraUpdateFactory.newLatLng(loc));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 11.0f));
            //  myMarker.showInfoWindow();
            //add circle
            CircleOptions circle = new CircleOptions();
            circle.strokeWidth(1).strokeColor(R.color.colorPrimary);
            circle.center(new LatLng(latitude, longitude)).fillColor(Color.TRANSPARENT).radius(distance * 1000);
            map.addCircle(circle);
        } else {

            place = (String) userLocationManager.getUserLocationDetails().get(UserCurrentLocationManager.KEY_ADDRESS_ONE);
            LatLng loc = new LatLng(((double) userLocationManager.getUserLocationDetails().get(UserCurrentLocationManager.KEY_LATITUDE)), ((double) userLocationManager.getUserLocationDetails().get(UserCurrentLocationManager.KEY_LONGITUDE)));
            Marker myMarker = map.addMarker(new MarkerOptions().snippet("UserLocation").position(loc).icon(BitmapDescriptorFactory.fromResource(R.drawable.my_loc_marker)));
//
            map.moveCamera(CameraUpdateFactory.newLatLng(loc));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(((double) userLocationManager.getUserLocationDetails().get(UserCurrentLocationManager.KEY_LATITUDE)), ((double) userLocationManager.getUserLocationDetails().get(UserCurrentLocationManager.KEY_LONGITUDE))), 11.0f));
            // myMarker.showInfoWindow();
            //add circle
            CircleOptions circle = new CircleOptions();
            circle.strokeWidth(1).strokeColor(R.color.colorPrimary);
            circle.center(new LatLng(((double) userLocationManager.getUserLocationDetails().get(UserCurrentLocationManager.KEY_LATITUDE)), ((double) userLocationManager.getUserLocationDetails().get(UserCurrentLocationManager.KEY_LONGITUDE)))).fillColor(Color.TRANSPARENT).radius(distance * 1000);
            map.addCircle(circle);
        }


    }


}