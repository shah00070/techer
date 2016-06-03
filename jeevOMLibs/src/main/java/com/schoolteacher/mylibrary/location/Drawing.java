package com.schoolteacher.mylibrary.location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Drawing {
	private LatLng latLng;
	private GoogleMap map;
	private CircleOptions circleOptions;
	private CameraPosition cameraPosition;
	private static Drawing drawing;

	private Drawing(GoogleMap map, LatLng myLocation) {
		this.map = map;
		this.latLng = myLocation;
	}

	public static Drawing getInstance(GoogleMap map, LatLng myLocation) {
		if (drawing == null) {
			return new Drawing(map, myLocation);
		}
		return drawing;

	}

//	public void drawCircle(int radius) {
//		MarkerOptions options = new MarkerOptions().position(latLng).title("You are Here");
//		options.draggable(true);
//		circleOptions = new CircleOptions();
//		circleOptions.center(latLng).radius(radius);
//		circleOptions.fillColor(Color.parseColor("#1A000000"));
//		circleOptions.strokeColor(Color.BLUE);
//		circleOptions.strokeWidth(3);
//		map.addCircle(circleOptions);
//		map.addMarker(options);
//	}

	public void cameraUpdation(int zoom) {
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom), 2000, null);
	}

	public void addMarkerLocaly() {
		MarkerOptions options = new MarkerOptions().position(latLng).title("You are Here");
		options.draggable(true);
		map.addMarker(options);
	}
}
