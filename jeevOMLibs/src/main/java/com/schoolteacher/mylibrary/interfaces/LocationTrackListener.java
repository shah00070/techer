package com.schoolteacher.mylibrary.interfaces;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

public interface LocationTrackListener {
	void searchLatLngByAddressTaskComplete(Address address, int responseCode);
	void respondCurrentLocationAddress(Address address, LatLng userLocation);
}
