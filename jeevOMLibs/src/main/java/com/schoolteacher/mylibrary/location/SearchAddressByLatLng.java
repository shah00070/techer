package com.schoolteacher.mylibrary.location;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.schoolteacher.mylibrary.interfaces.LocationTrackListener;

public class SearchAddressByLatLng extends AsyncTask<LatLng, Void, List<Address>> {

    Activity activity;

    private String newAddressText;

    private LatLng userLocation;
    private ProgressDialog dialog;
    LocationTrackListener locationTrackListener;

    public SearchAddressByLatLng(Activity activity) {
        this.activity = activity;
        locationTrackListener = (LocationTrackListener) activity;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        GPSTracker tracker = new GPSTracker(activity.getApplicationContext());

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected List<Address> doInBackground(LatLng... locationLatLng) {
        userLocation = locationLatLng[0];

        Geocoder geocoder = new Geocoder(activity.getApplicationContext());
        LatLng latLng = locationLatLng[0];
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return addresses;
    }

    @Override
    protected void onPostExecute(List<Address> addresses) {
        Address address = null;
        if (addresses == null || addresses.size() == 0) {
            // Toast.makeText(activity, "No Location found",
            // Toast.LENGTH_SHORT).show();
        }
        // map.clear();

        if (addresses != null && addresses.size() > 0) {
            address = addresses.get(0);
        }
        locationTrackListener.respondCurrentLocationAddress(address, userLocation);
    }

}
