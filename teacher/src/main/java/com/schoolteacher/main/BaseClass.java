package com.schoolteacher.main;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.schoolteacher.R;
import com.schoolteacher.interfaces.AppConstants;
import com.schoolteacher.library.main.IntroScreenActivity;
import com.schoolteacher.mylibrary.interfaces.LocationTrackListener;
import com.schoolteacher.mylibrary.location.GPSTracker;
import com.schoolteacher.mylibrary.session.Home;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.pojos.DealsAndOfferResponse;
import com.schoolteacher.pojos.JeevCriteria;
import com.schoolteacher.pojos.JeevSearchFilter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class BaseClass extends AppCompatActivity implements AppConstants,
        LocationTrackListener {
    private Home hsession;
    private JeevomSession session;
    private String username, city;
    private GPSTracker gpsTracker;
    private double latitude;
    private double longitude;
    private DialogFragment newFragment;
    JeevCriteria jeevCriteria;
    JeevSearchFilter filter;
    boolean isNearMeChecked;

    @Override
    protected void onResume() {
        super.onResume();
        gpsTracker = new GPSTracker(getApplicationContext());
        getOverflowMenu();
        session = new JeevomSession(getApplicationContext());
        hsession = new Home(getApplicationContext());
        username = session.getName();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.login:

           /*     if (!(session.getLoggedInStatus())) {
                    Intent signup_intent = new Intent(this,
                            SignUpLoginActivity.class);
                    session.setAppType("jeevom");
                    startActivityForResult(signup_intent, 1111);
                    overridePendingTransition(R.anim.trans_right_in,
                            R.anim.trans_right_exit);
                }*/
                break;

            case R.id.change_password:
/*
                if (session.getLoggedInStatus()) {
                    Intent intent = new Intent(this, ChangePasswordActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.trans_left_in,
                            R.anim.trans_left_exit);
                }*/
                break;
//            case R.id.my_profile:
            /*    Fragment d=  getVisibleFragment();
//



                ArrayList<String> runningactivities = new ArrayList<String>();

                ActivityManager activityManager = (ActivityManager)getBaseContext().getSystemService (Context.ACTIVITY_SERVICE);

                List<ActivityManager.RunningTaskInfo> services = activityManager.getRunningTasks(Integer.MAX_VALUE);

                for (int i1 = 0; i1 < services.size(); i1++) {
                    runningactivities.add(0,services.get(i1).topActivity.toString());
                }

                //

                if(d instanceof HomeFragment){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                    IntroScreenActivity.r.setHome("true");
                    Fragment fragment = new HomeFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.home));
                    getSupportActionBar().setTitle(getResources().getString(R.string.home));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();

                }else if(d instanceof HealthBuddyFragment){
                    // Toast.makeText(BaseClass.this,"HealthBuddyFragment", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new HealthBuddyFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.health_buddy));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();

                }else if(d instanceof HealthCloudFragment){
                    // Toast.makeText(BaseClass.this,"HealthCloudFragment", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new HealthCloudFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.health_cloud));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();

                }else if(d instanceof HealthMartFragment){

                    // Toast.makeText(BaseClass.this,"HealthMartFragment", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new HealthMartFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.health_mart));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                }else if(d instanceof HealthSocialFragment){

                    //Toast.makeText(BaseClass.this,"HealthSocialFragment", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new HealthSocialFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.health_social));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                }else if(d instanceof DealsAndOfferFragment){

                    //  Toast.makeText(BaseClass.this,"DealsAndOfferFragment", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new DealsAndOfferFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.deals_offer));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                }else if(d instanceof HealthLibraryFragment){

                    // Toast.makeText(BaseClass.this,"HealthLibraryFragment", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new HealthLibraryFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.health_library));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                }else if(d instanceof ProfileFragment){

                    // Toast.makeText(BaseClass.this,"ProfileFragment", Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                    IntroScreenActivity.r.setProfile("true");
                    Fragment fragment = new ProfileFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.profile));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                }else if(d instanceof ProductOverviewFragment){


                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new ProductOverviewFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.product_overview));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                    // Toast.makeText(BaseClass.this,"ProductOverviewFragment", Toast.LENGTH_SHORT).show();

                }else if(d instanceof AboutUsFragment){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new AboutUsFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.about));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                    //   Toast.makeText(BaseClass.this,"AboutUsFragment", Toast.LENGTH_SHORT).show();

                }else if(d instanceof PopularSearchFragment){

                    // Toast.makeText(BaseClass.this,"PopularSearchFragment", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new PopularSearchFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.popular_search));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                }
                else if(d instanceof JeevSearchFragment){

                    Toast.makeText(BaseClass.this,"JeevSearchFragment", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();

                    jeevCriteria = (JeevCriteria) intent.getSerializableExtra("object");
                    filter = (JeevSearchFilter) intent.getSerializableExtra("filter");
                    isNearMeChecked = intent.getBooleanExtra("isNearMeChecked", false);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object", jeevCriteria);
                    bundle.putSerializable("filter", filter);
                    bundle.putBoolean("isNearMeChecked", isNearMeChecked);

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new JeevSearchFragment();
                    fragment.setArguments(bundle);
                    getSupportActionBar().setTitle("Jeevom Search List");
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.list_map_container, fragment);
                    fragTransaction.commit();

              *//*  Fragment   fragment = new JeevSearchFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.list_map_container, fragment).commit();*//*
                }
                else if(d instanceof HealthTipsListFragment && runningactivities.contains("ComponentInfo{bizcloud.jeevom.com/com.jeevom.main.GeneralActivity}")==false){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    Fragment fragment = new HealthTipsListFragment();
                    getSupportActionBar().setTitle(getResources().getString(R.string.popular_search));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment);
                    fragTransaction.commit();
                }

//1
                else if(d instanceof HealthTipsListFragment && runningactivities.contains("ComponentInfo{bizcloud.jeevom.com/com.jeevom.main.GeneralActivity}")==true){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                    int screen_name = getIntent().getIntExtra("screen_name", 0);
                    Fragment fragment = null;

                    if (screen_name == HEALTH_TIPS_PAGE) {
                        fragment = new HealthTipsListFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.health_tips));
                    }

                    fragTransaction.replace(R.id.container_general_body, fragment);
                    fragTransaction.commit();
                }

                else if(d instanceof DealsAndOfferListingFragment){

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                    List<DealsAndOfferResponse> list = null;
                    Fragment fragment = new DealsAndOfferListingFragment().getInstance(loadSharedPreferencesLogList(BaseClass.this));
                    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragTransaction.replace(R.id.container_body, fragment,null);
                    fragTransaction.commit();
                    Toast.makeText(BaseClass.this, "hit", Toast.LENGTH_SHORT).show();
            *//*    List<DealsAndOfferResponse> list = null;
               final Fragment fragment = DealsAndOfferListingFragment.getInstance(list);
                 FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container_body, fragment, null);
                ft.commit();*//*
                }


                else if(runningactivities.contains("ComponentInfo{bizcloud.jeevom.com/com.jeevom.main.UserSavedSearches}")==true){
                    Toast.makeText(getBaseContext(),"Activity DoctorProfile, active",Toast.LENGTH_LONG).show();
                    //  FragmentManager fm = getSupportFragmentManager();

//if you added fragment via layout xml
//                DoctorProfile fragment = (DoctorProfile)fm.findFragmentById(R.id.doctor_profile);
//                fragment.getDoctorProfile("");

                    ((UserSavedSearches)getApplicationContext()).getSavedSearches();
                    //  DoctorProfile.class.getClass().getMethods();
                }

                else if(runningactivities.contains("ComponentInfo{bizcloud.jeevom.com/com.jeevom.main.DoctorProfile}")==true){
                    Toast.makeText(getBaseContext(),"Activity DoctorProfile, active",Toast.LENGTH_LONG).show();
                    //  FragmentManager fm = getSupportFragmentManager();

//if you added fragment via layout xml
//                DoctorProfile fragment = (DoctorProfile)fm.findFragmentById(R.id.doctor_profile);
//                fragment.getDoctorProfile("");
                    String profileId = getIntent().getStringExtra("id");
                    ((DoctorProfile)BaseClass.this).getDoctorProfile(profileId);
                    //  DoctorProfile.class.getClass().getMethods();
                }



                else if(runningactivities.contains("ComponentInfo{bizcloud.jeevom.com/com.jeevom.medvault.MedVaultActivity}")==true){
                    Toast.makeText(getBaseContext(),"Activity MedVaultActivity, active",Toast.LENGTH_LONG).show();
                    //  FragmentManager fm = getSupportFragmentManager();

//if you added fragment via layout xml
//                DoctorProfile fragment = (DoctorProfile)fm.findFragmentById(R.id.doctor_profile);
//                fragment.getDoctorProfile("");
                    String profileId = getIntent().getStringExtra("id");
                    ((MedVaultActivity)BaseClass.this).getUserDocuments();
                    //  DoctorProfile.class.getClass().getMethods();
                }





                else if(runningactivities.contains("ComponentInfo{bizcloud.jeevom.com/com.jeevom.main.FacilityProfile}")==true){
                    Toast.makeText(getBaseContext(),"Activity  FacilityProfile, active",Toast.LENGTH_LONG).show();

                    String profileId = getIntent().getStringExtra("id");
                    ((FacilityProfile)BaseClass.this).getFacilityProfile(profileId);
                }else if(runningactivities.contains("ComponentInfo{bizcloud.jeevom.com/com.jeevom.main.ViewProfile}")==true){
                    Toast.makeText(getBaseContext(),"Activity  ViewProfile, active",Toast.LENGTH_LONG).show();
                    String memberId, consumerId;

                    if (getIntent().getStringExtra("member_profile_id") != null)
                        consumerId = getIntent().getStringExtra("member_profile_id");
                    else
                        consumerId = session.getConsumerIds().get(
                                JeevomSession.JEEVOM_CONSUMER_ID);

                    ((ViewProfile)BaseClass.this).getConsumerDetails(consumerId);
                }


*/












          //      break;



          /*  case R.id.wallet:
                Intent walletIntent = new Intent(this, WalletActivity.class);
                startActivity(walletIntent);
                overridePendingTransition(R.anim.trans_left_in,
                        R.anim.trans_left_exit);
                break;*/
            case R.id.logout:

                NotificationManager manager = (NotificationManager) this
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(001);
                Toast.makeText(getApplicationContext(),
                        "You have successfully logged out of SchoolTriangle",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, IntroScreenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.trans_right_in,
                        R.anim.trans_right_exit);
                finish();


                break;

        }

        return true;
    }
    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = BaseClass.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }
    public  static List<DealsAndOfferResponse> loadSharedPreferencesLogList(Context context) {
        List<DealsAndOfferResponse> callLog = new ArrayList<DealsAndOfferResponse>();
        SharedPreferences mPrefs = context.getSharedPreferences("DealsAndOfferResponse", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("myJsonn", null);
        if (json.isEmpty()) {
            callLog = new ArrayList<DealsAndOfferResponse>();
        } else {
            Type type = new TypeToken<List<DealsAndOfferResponse>>() {
            }.getType();
            callLog = gson.fromJson(json, type);
        }
        return callLog;
    }
/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (session.getLoggedInStatus()) {
            menu.getItem(0).setTitle(username);
            menu.getItem(1).setVisible(true);

            menu.getItem(2).setVisible(true);
            menu.getItem(3).setVisible(true);
            menu.getItem(4).setVisible(true);

        } else {
            menu.getItem(3).setVisible(false);
            menu.getItem(1).setVisible(false);

            menu.getItem(2).setVisible(false);
            menu.getItem(0).setVisible(true);
            menu.getItem(4).setVisible(false);
        }

        return false;
    }
*/

    private void getOverflowMenu() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                    Log.e("JeevOM_Log", "onMenuOpened", e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void respondCurrentLocationAddress(Address address,
                                              LatLng userLocation) {

        newFragment.dismissAllowingStateLoss();
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
        }
        gpsTracker.stopUsingGPS();
        launchLandingScreen();

    }

    private void launchLandingScreen() {

        Intent intent = new Intent(this, IntroScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_exit);
        //finishAffinity();
    }

    @Override
    public void searchLatLngByAddressTaskComplete(Address address,
                                                  int responseCode) {

    }

}
