package com.schoolteacher.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.maps.model.LatLng;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.library.main.IntroScreenActivity;
import com.schoolteacher.mylibrary.interfaces.LocationTrackListener;
import com.schoolteacher.mylibrary.location.GPSTracker;
import com.schoolteacher.mylibrary.location.SearchAddressByLatLng;
import com.schoolteacher.mylibrary.pojo.AddressOfUser;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.session.ValuesManager;
import com.schoolteacher.mylibrary.util.Utils;

public class SplashActivity extends FragmentActivity
        implements
        LocationTrackListener {

    JeevomSession session;
    ValuesManager valuesManager;
    ProgressBar progressBar;
    GPSTracker gpsTracker;
    String city;
    double latitude;
    double longitude;
    int version;
    PackageInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        session = new JeevomSession(getApplicationContext());
        valuesManager = new ValuesManager(getApplicationContext());
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = info.versionCode;
            valuesManager.saveVersion(version);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Utils.initializeScreenDisplay(this);
        session.setAppType("jeevom");

















        // getActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                gpsTracker = new GPSTracker(SplashActivity.this
                        .getApplicationContext());

                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();
                LatLng myLocation = new LatLng(latitude,
                        longitude);
                SearchAddressByLatLng addressByLatLng = new SearchAddressByLatLng(
                        SplashActivity.this);
                addressByLatLng.execute(myLocation);
                // launchLandingScreen();
            }
        }, 2000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Splash Screen");
    }

    @Override
    protected void onStart() {
        // Get an Analytics tracker to report app starts & uncaught exceptions
        // etc.
        // GoogleAnalytics.getInstance(this).reportActivityStart(this);
        // Log.d("meraHealthcare", "Splash Activity Started");
        super.onStart();
    }

    @Override
    protected void onStop() {
        // Stop the analytics tracking
        // GoogleAnalytics.getInstance(this).reportActivityStop(this);
        // Log.d("meraHealthcare", "Splash Activity Stopped");
        super.onStop();
    }

    @Override
    public void respondCurrentLocationAddress(Address address,
                                              LatLng userLocation) {
        progressBar.setVisibility(View.GONE);

        if (address != null) {
            city = address.getLocality();
            latitude = address.getLatitude();
            longitude = address.getLongitude();

            UserCurrentLocationManager userCurrentLocation = new UserCurrentLocationManager(
                    getApplicationContext());
            userCurrentLocation.saveValue(address.getPostalCode(),
                    address.getFeatureName(), address.getAdminArea(),
                    address.getSubAdminArea(), address.getLocality(),
                    address.getLatitude(), address.getLongitude(),
                    address.getAddressLine(0), address.getAddressLine(1),
                    address.getAddressLine(2), address.getAddressLine(3));

            AddressOfUser addressObject = new AddressOfUser();
            addressObject.setLatitude(latitude);
            addressObject.setLongitude(longitude);
            addressObject.setAddress0(address.getAddressLine(0));
            addressObject.setAddress1(address.getAddressLine(1));
            addressObject.setAddress2(address.getAddressLine(2));
            addressObject.setAddress3(address.getAddressLine(3));
            addressObject.setArea(address.getSubAdminArea());
            addressObject.setCity(address.getAdminArea());
            addressObject.setZipCode(address.getPostalCode());
            addressObject.setCountry(address.getAddressLine(3));

            UserCurrentLocationManager locationManager = new UserCurrentLocationManager(getApplicationContext());
            locationManager.userLocationObject(addressObject);
        } else {
            AddressOfUser addressObject = new AddressOfUser();
            addressObject.setLatitude(latitude);
            addressObject.setLongitude(longitude);

            UserCurrentLocationManager locationManager = new UserCurrentLocationManager(getApplicationContext());
            locationManager.userLocationObject(addressObject);
        }
        gpsTracker.stopUsingGPS();
        launchLandingScreen();
    }

    private void launchLandingScreen() {
        JeevomSession session = new JeevomSession(SplashActivity.this);


        if (session.getLoggedInStatus()) {
            // Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            //session.logoutUser();
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("city", city);
            bundle.putDouble("latitude", latitude);
            bundle.putDouble("longitude", longitude);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);
            finish();

          /*  Intent intent = new Intent(this, IntroScreenActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);
            finish();*/
        } else {
            Intent intent = new Intent(this, IntroScreenActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_exit);
            finish();
        }

    }

    @Override
    public void searchLatLngByAddressTaskComplete(Address address,
                                                  int responseCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Applying Exit Animation;
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
    }
}
