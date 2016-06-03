package com.schoolteacher.library.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.schoolteacher.mylibrary.R;
import com.schoolteacher.mylibrary.adapter.SuggestionAdapter;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.interfaces.AsyncTaskListner;
import com.schoolteacher.mylibrary.interfaces.LocationTrackListener;
import com.schoolteacher.mylibrary.location.Drawing;
import com.schoolteacher.mylibrary.location.GPSTracker;
import com.schoolteacher.mylibrary.location.SearchAddressTask;
import com.schoolteacher.mylibrary.location.SearchLatLngByAddress;
import com.schoolteacher.mylibrary.model.Area;
import com.schoolteacher.mylibrary.model.City;
import com.schoolteacher.mylibrary.model.Country;
import com.schoolteacher.mylibrary.model.DataContainer;
import com.schoolteacher.mylibrary.model.State;
import com.schoolteacher.mylibrary.model.UserAddressData;
import com.schoolteacher.mylibrary.service.GetAsyncTask;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class AddressBasedMap extends ActionBarActivity implements
		LocationTrackListener, OnFocusChangeListener, OnClickListener,
		AsyncTaskListner {
	public static final int REQUEST_CODE = 10;
	GoogleMap map;
	private EditText localAddress;
	AutoCompleteTextView country_value, state_value, city_value, area_value;
	private static final int ZOOM_LEVEL_COUNTRY = 5, ZOOM_LEVEL_CITY = 10,
			ZOOM_LEVEL_STATE = 7, ZOOM_LEVEL_ADDRESS = 14,
			ZOOM_LEVEL_AREA = 13, RADIUS_CITY = 15000, RADIUS_STATE = 60000,
			RADIUS_ADDRESS = 1000, RESULT_CODE_COUNTRY = 0,
			RESULT_CODE_STATE = 1, RESULT_CODE_CITY = 2,
			RESULT_CODE_ADDRESS = 3, RESULT_CODE_AREA = 4,
			RESULT_CODE_SAVE = 5, RESULT_CODE_SHOW_ON_MAP_CLICK = 6,
			RADIUS_AREA = 3000;
	private String country, state, city, area, postal, tempArea = "",
			tempCity = "", tempAddress = "", tempCompleteAddress = "";
	private LatLng stateLatLng = null;
	private LatLng cityLatLng = null;
	private LatLng areaLatLng = null;
	private LatLng addressLatLng = null;
	private LatLng userFinalLatLng;
	GPSTracker gpsTracker;
	private Intent addressIntent;
	DialogFragment newFragment;
	private List<Country> countryList;
	List<String> listData = new ArrayList<String>();
	private List<State> stateList;
	private List<City> cityList;
	private List<Area> areaList;
	private int zoomLevel;
	private String previousArea = "", previousState = "", autoFillAddress,
			tempLocalAddress, tempAddressShowOnMap;
	private UserAddressData userAddressData;
	private static final LatLng INDIA = new LatLng(23.626641, 79.384803);
	private static final LatLng DIGIGRAPES = new LatLng(28.631209, 77.38432);
	private ArrayList<LatLng> markerPoints;
	Button btnShowOnMap;
	RelativeLayout address_layout;
	GlobalAlert globalAlert;
	private LatLng loc;
	protected LatLng onScreenClickLatLng;
	private EditText zipcode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_container);
		globalAlert = new GlobalAlert(AddressBasedMap.this);
		address_layout = (RelativeLayout) findViewById(R.id.address_container);

		// set upper bar on view
		setActionBarOnMapField();
		markerPoints = new ArrayList<LatLng>();
		gpsTracker = new GPSTracker(this);
		addressIntent = getIntent();
		userAddressData = (UserAddressData) addressIntent
				.getSerializableExtra("user_address_data");
		Address address = addressIntent.getParcelableExtra("address");
		loc = addressIntent.getParcelableExtra("latlng");
		zoomLevel = ZOOM_LEVEL_ADDRESS;
		if (loc == null || (loc.latitude == 0.0 && loc.longitude == 0.0)) {
			loc = INDIA;
			zoomLevel = ZOOM_LEVEL_COUNTRY;
		}
		// AutoCompleteTextView References
		country_value = (AutoCompleteTextView) findViewById(R.id.country_lookup);
		zipcode = (EditText) findViewById(R.id.zipcode);
		state_value = (AutoCompleteTextView) findViewById(R.id.state_lookup);
		city_value = (AutoCompleteTextView) findViewById(R.id.city_lookup);
		area_value = (AutoCompleteTextView) findViewById(R.id.area_lookup);

		getCountryList(JeevOMUtil.mapCountryList);

		country_value.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				country_value.clearFocus();
				country_value.setError(null);
				int countryId = getCountryId(country_value.getAdapter()
						.getItem(position).toString().trim());
				getStateList(JeevOMUtil.mapStateList + countryId);
				String inputCountry = country_value.getText().toString().trim();
				if (!CommonCode.isNullOrEmpty(inputCountry)) {
					SearchLatLngByAddress latLngByAddress = new SearchLatLngByAddress(
							AddressBasedMap.this, RESULT_CODE_COUNTRY);
					latLngByAddress.execute(inputCountry);
				}
			}
		});
		state_value.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				state_value.clearFocus();
				state_value.setError(null);
				int stateId = getStateId(state_value.getAdapter()
						.getItem(position).toString().trim());
				String inputState = state_value.getText().toString().trim()
						+ "," + country_value.getText().toString().trim();
				if (!previousState.equals(inputState)) {
					previousState = inputState;
					getCityList(JeevOMUtil.mapCityList + stateId);
				}

				// map redirect
				if (!CommonCode.isNullOrEmpty(inputState)) {
					SearchLatLngByAddress latLngByAddress = new SearchLatLngByAddress(
							AddressBasedMap.this, RESULT_CODE_STATE);
					latLngByAddress.execute(inputState);
				}

			}
		});
		city_value.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				city_value.clearFocus();
				city_value.setError(null);
				int cityId = getCityId(city_value.getAdapter()
						.getItem(position).toString().trim());
				String inputCity = city_value.getText().toString().trim() + ","
						+ state_value.getText().toString().trim() + ","
						+ country_value.getText().toString().trim();
				if (!previousArea.equals(inputCity)) {
					previousArea = inputCity;
					getAreaList(JeevOMUtil.mapAreaList + cityId);
				}
				// map redirect
				if (!(inputCity.equals("")) && !(inputCity.equals(tempCity))) {
					SearchLatLngByAddress latLngByAddress = new SearchLatLngByAddress(
							AddressBasedMap.this, RESULT_CODE_CITY);
					latLngByAddress.execute(inputCity);
				}

			}
		});
		area_value.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				area_value.clearFocus();
				area_value.setError(null);
				// map redirect
				String inputArea = area_value.getText().toString().trim() + ","
						+ city_value.getText().toString().trim() + ","
						+ state_value.getText().toString().trim() + ","
						+ country_value.getText().toString().trim();
				if (!(inputArea.equals("")) && !(inputArea.equals(tempArea))) {
					SearchLatLngByAddress latLngByAddress = new SearchLatLngByAddress(
							AddressBasedMap.this, RESULT_CODE_AREA);
					latLngByAddress.execute(inputArea);
				}
			}
		});

		localAddress = (EditText) this.findViewById(R.id.edit_text_address);
		initializeMap();
		onApplicationResume(address, loc);
		localAddress.setOnFocusChangeListener(this);
		Button btn = (Button) this.findViewById(R.id.button_save);
		btn.setOnClickListener(this);
		btnShowOnMap = (Button) this.findViewById(R.id.button_ok);
		btnShowOnMap.setOnClickListener(this);

		map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

			@Override
			public void onMarkerDragStart(Marker marker) {
			}

			@Override
			public void onMarkerDragEnd(Marker marker) {
				userFinalLatLng = marker.getPosition();
				onScreenClickLatLng = marker.getPosition();

				map.clear();
				Drawing.getInstance(
						map,
						new LatLng(marker.getPosition().latitude, marker
								.getPosition().longitude)).addMarkerLocaly();
				Drawing.getInstance(
						map,
						new LatLng(marker.getPosition().latitude, marker
								.getPosition().longitude)).cameraUpdation(
						ZOOM_LEVEL_ADDRESS);
				SearchAddressTask address = new SearchAddressTask(
						AddressBasedMap.this);
				address.execute(marker.getPosition());
			}

			@Override
			public void onMarkerDrag(Marker marker) {
			}
		});

		map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {

				return false;
			}
		});
		map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

			@Override
			public void onMapClick(LatLng latLng) {
				if (markerPoints.size() > 1) {
					markerPoints.clear();
				}
				markerPoints.add(latLng);
				if (markerPoints.size() == 1) {
					Toast.makeText(
							AddressBasedMap.this,
							"Now touch at your address on map after navigating the map",
							Toast.LENGTH_LONG).show();
				} else if (markerPoints.size() == 2) {
					userFinalLatLng = latLng;
					onScreenClickLatLng = latLng;
					map.clear();
					Drawing.getInstance(map, latLng).addMarkerLocaly();
					Drawing.getInstance(map, latLng).cameraUpdation(
							ZOOM_LEVEL_ADDRESS);
					SearchAddressTask searchAddress = new SearchAddressTask(
							AddressBasedMap.this);
					searchAddress.execute(latLng);
				}
			}
		});
		map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

			@Override
			public void onMapLongClick(LatLng latLng) {
				if (markerPoints.size() > 1) {
					markerPoints.clear();
				}
				markerPoints.add(latLng);
				if (markerPoints.size() == 1) {
					Toast.makeText(
							AddressBasedMap.this,
							"Now touch at your address on map after navigating the map",
							Toast.LENGTH_LONG).show();
				} else if (markerPoints.size() == 2) {
					userFinalLatLng = latLng;
					onScreenClickLatLng = latLng;
					map.clear();
					Drawing.getInstance(map, latLng).addMarkerLocaly();
					Drawing.getInstance(map, latLng).cameraUpdation(
							ZOOM_LEVEL_ADDRESS);
					SearchAddressTask searchAddress = new SearchAddressTask(
							AddressBasedMap.this);
					searchAddress.execute(latLng);
				}
			}
		});
	}

	private void setActionBarOnMapField() {

		ImageView imageButton = (ImageView) findViewById(R.id.location);
		imageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				RelativeLayout address_layout = (RelativeLayout) findViewById(R.id.address_container);

				if (address_layout.getVisibility() == View.GONE) {
					address_layout.setVisibility(View.VISIBLE);
				} else {
					address_layout.setVisibility(View.GONE);
				}
			}
		});
	}

	private void getCountryList(String lookupUrl) {
		newFragment = ProgressDialogFragment.newInstance();
		GetAsyncTask gat = new GetAsyncTask(AddressBasedMap.this, newFragment);
		gat.setOnXMLLoadFinishedListener(this);
		gat.execute(JeevOMUtil.baseUrl + lookupUrl);
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
	}

	private void getStateList(String lookupUrl) {
		newFragment = ProgressDialogFragment.newInstance();
		GetAsyncTask gat = new GetAsyncTask(AddressBasedMap.this, newFragment);
		gat.setOnXMLLoadFinishedListener(new AsyncTaskListner() {

			@Override
			public void onTaskComplete(String result) {
				DataContainer data = CommonCode
						.returnDataContainerObject(result);
				if (isSuccessResult(result, data)) {
					stateList = data.getData().getStates();
					ArrayList<String> listData = new ArrayList<String>();
					Iterator<State> iterator = stateList.iterator();
					while (iterator.hasNext()) {
						listData.add(iterator.next().getName());
					}
					state_value.setAdapter(new SuggestionAdapter(
							AddressBasedMap.this, listData, null,
							"state_lookup", state_value.getText().toString()
									.trim()));
				}
			}

			@Override
			public void onMemberSignUp(String result) {

			}

			@Override
			public void onFacilitySignUp(String result) {

			}
		});
		gat.execute(JeevOMUtil.baseUrl + lookupUrl);
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
	}

	private void getCityList(String lookupUrl) {
		newFragment = ProgressDialogFragment.newInstance();
		GetAsyncTask gat = new GetAsyncTask(AddressBasedMap.this, newFragment);
		gat.setOnXMLLoadFinishedListener(new AsyncTaskListner() {

			@Override
			public void onTaskComplete(String result) {
				DataContainer data = CommonCode
						.returnDataContainerObject(result);
				if (isSuccessResult(result, data)) {
					cityList = data.getData().getCities();
					ArrayList<String> listData = new ArrayList<String>();
					Iterator<City> iterator = cityList.iterator();
					while (iterator.hasNext()) {
						listData.add(iterator.next().getName());
					}
					city_value.setAdapter(new SuggestionAdapter(
							AddressBasedMap.this, listData, null,
							"city_lookup", city_value.getText().toString()
									.trim()));
				}
			}

			@Override
			public void onMemberSignUp(String result) {

			}

			@Override
			public void onFacilitySignUp(String result) {

			}
		});
		gat.execute(JeevOMUtil.baseUrl + lookupUrl);
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
	}

	private void getAreaList(String lookupUrl) {
		newFragment = ProgressDialogFragment.newInstance();
		GetAsyncTask gat = new GetAsyncTask(AddressBasedMap.this, newFragment);
		gat.setOnXMLLoadFinishedListener(new AsyncTaskListner() {

			@Override
			public void onTaskComplete(String result) {
				DataContainer data = CommonCode
						.returnDataContainerObject(result);
				if (isSuccessResult(result, data)) {
					areaList = data.getData().getAreas();
					ArrayList<String> listData = new ArrayList<String>();
					Iterator<Area> iterator = areaList.iterator();
					while (iterator.hasNext()) {
						listData.add(iterator.next().getName());
					}
					area_value.setAdapter(new SuggestionAdapter(
							AddressBasedMap.this, listData, null,
							"area_lookup", area_value.getText().toString()
									.trim()));
				}
			}

			@Override
			public void onMemberSignUp(String result) {

			}

			@Override
			public void onFacilitySignUp(String result) {

			}
		});
		gat.execute(JeevOMUtil.baseUrl + lookupUrl);
		newFragment.show(getSupportFragmentManager(), "dialog");
		newFragment.setCancelable(false);
	}

	private void onApplicationResume(Address address, LatLng loc) {
		String user_country = userAddressData.getCountry();
		String user_state = userAddressData.getState();
		String user_city = userAddressData.getCity();
		String user_area = userAddressData.getArea();
		String user_address = userAddressData.getAddress();
		String zipcode = userAddressData.getPostalCode();
		if (!CommonCode.isNullOrEmpty(user_country)
				|| !CommonCode.isNullOrEmpty(user_state)
				|| !CommonCode.isNullOrEmpty(user_city)
				|| !CommonCode.isNullOrEmpty(user_area)
				|| !CommonCode.isNullOrEmpty(user_address)) {
			setLookupAddress(user_country, user_state, user_city, user_area,
					zipcode);
			if (!CommonCode.isNullOrEmpty(user_address)) {
				localAddress.setText(user_address);
				tempLocalAddress = localAddress.getText().toString().trim();
			}
			userFinalLatLng = new LatLng(userAddressData.getLattitude(),
					userAddressData.getLongitude());
			if (userFinalLatLng == null || userFinalLatLng.latitude == 0
					&& userFinalLatLng.longitude == 0) {
				userFinalLatLng = DIGIGRAPES;
			}
			intialDrawingMarkerAndCameraUpdation(userFinalLatLng);
		} else {

			setAddress(address, loc);
			intialDrawingMarkerAndCameraUpdation(loc);
		}

	}

	private void intialDrawingMarkerAndCameraUpdation(LatLng loc) {
		Drawing drawing = Drawing.getInstance(map, loc);
		drawing.addMarkerLocaly();
		drawing.cameraUpdation(zoomLevel);
	}

	private void initializeMap() {
		if (map == null) {
			map = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map_fragment)).getMap();
		}
		if (map == null) {
			Toast.makeText(this, "Map is not created", Toast.LENGTH_SHORT)
					.show();
		}
	}

	public void setAddress(Address address, LatLng latLng) {

		if (address != null) {
			userFinalLatLng = latLng;
			String countryName = address.getCountryName();
			String stateName = address.getAdminArea();
			String cityName = address.getLocality();
			String areaName = address.getSubLocality();
			String postalCode = address.getPostalCode();
			setLookupAddress(countryName, stateName, cityName, areaName,
					postalCode);
			setFullAddress(address);
			tempLocalAddress = localAddress.getText().toString().trim();
		}
	}

	private void setFullAddress(Address address) {
		int count = address.getMaxAddressLineIndex();
		int i = 0;
		String newAddressText = "";
		// while (count > 1) {
		// count--;
		if (!CommonCode.isNullOrEmpty(address.getFeatureName())) {
			newAddressText += address.getFeatureName();
		}
		// if (count > 0) {
		// newAddressText += String.format("%s", address.getAddressLine(0));
		// if (count > 1) {
		//
		// //newAddressText += ", " + String.format("%s",
		// address.getAddressLine(1));
		// }
		// }
		// }
		if (newAddressText != null && newAddressText.length() != 0) {
			localAddress.setText(newAddressText);
		} else {
			localAddress.setText("");
			localAddress.setHint("Address");
		}
	}

	@Override
	public void onFocusChange(View view, boolean hasFocus) {
		int id = view.getId();
		if (id == R.id.edit_text_address) {
			if (!hasFocus) {
				String inputAddress = localAddress.getText().toString().trim();
				String completeAddress = inputAddress + ","
						+ area_value.getText().toString().trim() + ","
						+ city_value.getText().toString().trim() + ","
						+ state_value.getText().toString().trim() + ","
						+ country_value.getText().toString().trim();

				if (!(inputAddress.equals(""))
						&& !(inputAddress.equals(tempAddress))) {
					SearchLatLngByAddress latLngByAddress = new SearchLatLngByAddress(
							this, RESULT_CODE_ADDRESS);
					latLngByAddress.execute(completeAddress);
					tempAddress = inputAddress;
				}
			}
		}
	}

	public void updateMap(Address address, int resultCode) {
		String countryName = null;
		String stateName = null;
		String cityName = null;
		String areaName = null;

		LatLng latLng = null;
		if (address != null) {
			latLng = new LatLng(address.getLatitude(), address.getLongitude());
			countryName = address.getCountryName();
			stateName = address.getAdminArea();
			cityName = address.getLocality();
			areaName = address.getSubLocality();
		}

		Drawing drawing = Drawing.getInstance(map, latLng);
		switch (resultCode) {
		case RESULT_CODE_COUNTRY:
			if (latLng != null) {
				map.clear();
				state_value.setText("");
				state_value.setHint("State");
				city_value.setText("");
				city_value.setHint("City");
				area_value.setText("");
				area_value.setHint("Area");
				localAddress.setText("");
				localAddress.setHint("Address");
				drawing.addMarkerLocaly();
				drawing.cameraUpdation(ZOOM_LEVEL_COUNTRY);
			}
			break;
		case RESULT_CODE_STATE:
			if (latLng != null) {
				map.clear();
				stateLatLng = latLng;
				cityLatLng = null;
				areaLatLng = null;
				addressLatLng = null;
				// if (!Strings.isNullOrEmpty(stateName)) {
				// state_value.setText(stateName);
				// }
				city_value.setText("");
				city_value.setHint("City");
				area_value.setText("");
				area_value.setHint("Area");
				localAddress.setText("");
				localAddress.setHint("Address");

				drawing.addMarkerLocaly();
				drawing.cameraUpdation(ZOOM_LEVEL_STATE);
			}
			break;

		case RESULT_CODE_CITY:
			if (latLng != null) {
				map.clear();
				cityLatLng = latLng;
				areaLatLng = null;
				addressLatLng = null;
				area_value.setText("");
				area_value.setHint("Area");
				drawing.addMarkerLocaly();
				drawing.cameraUpdation(ZOOM_LEVEL_CITY);
				break;
			}
		case RESULT_CODE_AREA:
			if (latLng != null) {
				map.clear();
				areaLatLng = latLng;
				addressLatLng = null;
				localAddress.setText("");
				localAddress.setHint("Area");
				drawing.addMarkerLocaly();
				drawing.cameraUpdation(ZOOM_LEVEL_AREA);
				break;
			}

		case RESULT_CODE_ADDRESS:
			if (latLng != null) {
				map.clear();
				// userFinalLatLng = latLng;
				addressLatLng = latLng;
				drawing.addMarkerLocaly();
				// setLookupAddress(countryName, stateName, cityName,
				// areaName);
				drawing.addMarkerLocaly();
				drawing.cameraUpdation(ZOOM_LEVEL_ADDRESS);

			}
			break;
		case RESULT_CODE_SHOW_ON_MAP_CLICK:
			if (latLng != null) {
				map.clear();
				userFinalLatLng = latLng;
				drawing.addMarkerLocaly();
				drawing.cameraUpdation(ZOOM_LEVEL_ADDRESS);
			}
			break;
		case RESULT_CODE_SAVE:
			if (address == null) {
				if (addressLatLng != null) {
					userFinalLatLng = addressLatLng;

				} else if (areaLatLng != null) {
					userFinalLatLng = areaLatLng;

				} else if (cityLatLng != null) {
					userFinalLatLng = cityLatLng;
				} else if (stateLatLng != null) {
					userFinalLatLng = stateLatLng;
				} else if (onScreenClickLatLng != null) {
					userFinalLatLng = onScreenClickLatLng;
				} else if (loc != null
						|| (loc.latitude != 0 && loc.longitude != 0)) {

					userFinalLatLng = loc;
				} else if (INDIA != null) {
					userFinalLatLng = INDIA;
				}
				finishMapAfterSaveData();
			} else {
				userFinalLatLng = latLng;
				finishMapAfterSaveData();
			}
			break;
		}
	}

	private void setLookupAddress(String countryName, String stateName,
			String cityName, String areaName, String postalCode) {
		if (!CommonCode.isNullOrEmpty(countryName)) {
			country_value.setText(countryName);

		}
		if (!CommonCode.isNullOrEmpty(stateName)) {
			state_value.setText(stateName);
		}

		if (!CommonCode.isNullOrEmpty(cityName)) {
			city_value.setText(cityName);
		}
		if (!CommonCode.isNullOrEmpty(areaName)) {
			area_value.setText(areaName);
		}
		if (!CommonCode.isNullOrEmpty(postalCode)) {
			zipcode.setText(postalCode);
		}

	}

	@Override
	public void searchLatLngByAddressTaskComplete(Address address,
			int responseCode) {
		updateMap(address, responseCode);
	}

	@Override
	public void respondCurrentLocationAddress(Address address,
			LatLng userLocation) {
		state_value.setText("");
		state_value.setHint("State");
		city_value.setText("");
		city_value.setHint("City");
		area_value.setText("");
		area_value.setHint("Area");
		localAddress.setText("");
		localAddress.setHint("Address");
		setAddress(address, userLocation);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.button_save) {
			country_value.clearFocus();
			state_value.clearFocus();
			city_value.clearFocus();
			area_value.clearFocus();
			localAddress.clearFocus();

			String inputAddress = localAddress.getText().toString().trim();
			String completeAddress = inputAddress + ","
					+ area_value.getText().toString().trim() + ","
					+ city_value.getText().toString().trim() + ","
					+ state_value.getText().toString().trim() + ","
					+ country_value.getText().toString().trim();
			if (CommonCode.isNullOrEmpty(country_value.getText().toString()
					.trim())) {
				Crouton.makeText(AddressBasedMap.this,
						JeevOMUtil.MANDATORY_COUNTRY, Style.ALERT).show();
				country_value.requestFocus();
			} else if (CommonCode.isNullOrEmpty(state_value.getText()
					.toString().trim())) {
				Crouton.makeText(AddressBasedMap.this,
						JeevOMUtil.MANDATORY_STATE, Style.ALERT).show();
				state_value.requestFocus();
			} else if (CommonCode.isNullOrEmpty(city_value.getText().toString()
					.trim())) {
				Crouton.makeText(AddressBasedMap.this,
						JeevOMUtil.MANDATORY_CITY, Style.ALERT).show();
				city_value.requestFocus();
			} else if (CommonCode.isNullOrEmpty(area_value.getText().toString()
					.trim())) {
				Crouton.makeText(AddressBasedMap.this,
						JeevOMUtil.MANDATORY_AREA, Style.ALERT).show();
				area_value.requestFocus();
			} else if (CommonCode.isNullOrEmpty(localAddress.getText()
					.toString().trim())) {
				Crouton.makeText(AddressBasedMap.this,
						JeevOMUtil.MANDATORY_LOCAL_ADDRESS, Style.ALERT).show();
				localAddress.requestFocus();
			} else {
				SearchLatLngByAddress lngByAddress = new SearchLatLngByAddress(
						AddressBasedMap.this, RESULT_CODE_SAVE);
				lngByAddress.execute(completeAddress);
			}

		}
		if (id == R.id.button_ok) {
			address_layout.setVisibility(View.GONE);
			String inputAddress = localAddress.getText().toString().trim();
			String completetAddress = inputAddress + ","
					+ area_value.getText().toString().trim() + ","
					+ city_value.getText().toString().trim() + ","
					+ state_value.getText().toString().trim() + ","
					+ country_value.getText().toString().trim();
			SearchLatLngByAddress lngByAddress = new SearchLatLngByAddress(
					AddressBasedMap.this, RESULT_CODE_SHOW_ON_MAP_CLICK);
			lngByAddress.execute(completetAddress);
			tempAddressShowOnMap = completetAddress;
		}
	}

	private void finishMapAfterSaveData() {
		UserAddressData userAddressData = new UserAddressData();
		country = country_value.getText().toString().trim();
		userAddressData.setCountry(country);
		state = state_value.getText().toString().trim();
		userAddressData.setState(state);
		city = city_value.getText().toString().trim();
		userAddressData.setCity(city);
		area = area_value.getText().toString().trim();
		userAddressData.setArea(area);
		postal = zipcode.getText().toString().trim();
		userAddressData.setPostalCode(postal);
		userAddressData.setAddress(localAddress.getText().toString().trim());

		try {
			userAddressData.setLattitude(userFinalLatLng.latitude);
			userAddressData.setLongitude(userFinalLatLng.longitude);
		} catch (Exception e) {

		}
		Intent mapIntent = addressIntent.putExtra("map_address",
				userAddressData);
		setResult(AddressBasedMap.RESULT_OK, mapIntent);
		finish();
	}

	@Override
	public void onTaskComplete(String result) {
		DataContainer data = CommonCode.returnDataContainerObject(result);
		if (isSuccessResult(result, data)) {
			countryList = data.getData().getCountries();
			ArrayList<String> listData = new ArrayList<String>();
			Iterator<Country> iterator = countryList.iterator();
			while (iterator.hasNext()) {
				listData.add(iterator.next().getName());
			}
			country_value.setAdapter(new SuggestionAdapter(
					AddressBasedMap.this, listData, null, "country_lookup",
					country_value.getText().toString().trim()));
			int id = getCountryId("india");
			getStateList(JeevOMUtil.mapStateList + id);
		}

	}

	private int getCountryId(String countryName) {
		int id = 0;
		for (Country country : countryList) {
			if (country.getName().equalsIgnoreCase(countryName)) {
				id = country.getId();
				break;
			}
		}
		return id;
	}

	private int getStateId(String stateName) {
		int id = 0;
		for (State state : stateList) {
			if (state.getName().equalsIgnoreCase(stateName)) {
				id = state.getId();
				break;
			}
		}
		return id;
	}

	private int getCityId(String cityName) {
		int id = 0;
		for (City city : cityList) {
			if (city.getName().equalsIgnoreCase(cityName)) {
				id = city.getId();
				break;
			}
		}
		return id;
	}

	private boolean isSuccessResult(String result, DataContainer data) {
		newFragment.dismissAllowingStateLoss();

		if (result.equals("No Internet Connectivity")) {
			showAlert(JeevOMUtil.INTERNET_CONNECTION);

		} else if (result.equals("Service Error")) {
			showAlert(JeevOMUtil.SOMETHING_WRONG);
		} else {
			String code = data.getStatus().getCode();
			String message = data.getStatus().getMessage();
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
			} else if (code.equals("Success")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onFacilitySignUp(String result) {

	}

	@Override
	public void onMemberSignUp(String result) {

	}

	// Show Global Message
	private void showAlert(String message) {
		globalAlert.show();
		globalAlert.setMessage(message);
	}

}
