package com.schoolteacher.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.schoolteacher.R;

public class FacilityMapView extends ActionBarActivity {
	private GoogleMap map;
	double latitude, longitude;
	private static final int ZOOM_LEVEL_COUNTRY = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facility_map_view);
		Intent intent = getIntent();

		latitude = intent.getDoubleExtra("latitude", 0.0);
		longitude = intent.getDoubleExtra("longitude", 0.0);

		if (map == null) {
			map = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map_fragment)).getMap();
		}
		if (map == null) {
			Toast.makeText(this, "Map is not created", Toast.LENGTH_SHORT)
					.show();
		}

		MarkerOptions options = new MarkerOptions().position(new LatLng(
				latitude, longitude));
		map.addMarker(options);

		map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
				latitude, longitude), ZOOM_LEVEL_COUNTRY), 2000, null);
	}
}
