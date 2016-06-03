package com.schoolteacher.mylibrary.location;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.schoolteacher.mylibrary.interfaces.LocationTrackListener;

public class SearchLatLngByAddress extends AsyncTask<String, Void, List<Address>> {

	Activity activity;

	private LatLng newLatLng;

	private int responseCode;
	LocationTrackListener asyncTaskListener;

	private Address address;

	public SearchLatLngByAddress(Activity activity, int responseCode) {
		this.activity = activity;
		this.responseCode = responseCode;
		asyncTaskListener = (LocationTrackListener) activity;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected List<Address> doInBackground(String... locationName) {
		Geocoder geocoder = new Geocoder(activity.getApplicationContext());
		List<Address> addresses = null;
		try {
			addresses = geocoder.getFromLocationName(locationName[0], 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addresses;
	}

	@Override
	protected void onPostExecute(List<Address> addresses) {
		if (addresses == null || addresses.size() == 0) {
			// Toast.makeText(activity, "No Location found",
			// Toast.LENGTH_SHORT).show();
		}

		if (addresses != null && addresses.size() != 0) {

			address = addresses.get(0);

		}
		asyncTaskListener.searchLatLngByAddressTaskComplete(address, responseCode);
	}
}
