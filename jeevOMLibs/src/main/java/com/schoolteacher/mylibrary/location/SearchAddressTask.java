package com.schoolteacher.mylibrary.location;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.schoolteacher.mylibrary.interfaces.LocationTrackListener;

public class SearchAddressTask extends AsyncTask<LatLng, Void, List<Address>> {

	Activity activity;
	LocationTrackListener listener;

	private String newAddressText;

	private LatLng newLatLng;
	private LatLng userLocation;
	MarkerOptions markerOptions;
	private ProgressDialog dialog;
	private LatLng latLng;
	private Address address;

	public SearchAddressTask(Activity activity) {
		this.activity = activity;
		listener = (LocationTrackListener) activity;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// if (dialog == null) {
		// dialog = ProgressDialog.show(activity, "Loading",
		// "Please wait for a moment...", false);
		// dialog.setCancelable(false);
		// dialog.show();
		// }

	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected List<Address> doInBackground(LatLng... latLngs) {
		Geocoder geocoder = new Geocoder(activity.getApplicationContext());
		latLng = latLngs[0];
		List<Address> addresses = null;
		try {
			addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addresses;
	}

	@Override
	protected void onPostExecute(List<Address> addresses) {
		if (addresses == null || addresses.size() == 0) {
			Toast.makeText(activity, "No Location found", Toast.LENGTH_SHORT).show();
		}

		if (addresses != null && addresses.size() != 0) {

			address = addresses.get(0);
		}
		listener.respondCurrentLocationAddress(address, latLng);
	}
}
