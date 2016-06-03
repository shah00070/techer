package com.schoolteacher.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.R;
import com.schoolteacher.appointment.ServiceRequestsActivity;
import com.schoolteacher.broadcast.AppointmentNotifier;
import com.schoolteacher.fragments.event;
import com.schoolteacher.fragments.eventlen;
import com.schoolteacher.fragments.task;
import com.schoolteacher.fragments.tasklen;
import com.schoolteacher.healthvitals.HealthVitals;
import com.schoolteacher.interfaces.AppConstants;
import com.schoolteacher.interfaces.Voice;
import com.schoolteacher.library.main.IntroScreenActivity;
import com.schoolteacher.library.main.SignUpLoginActivity;
import com.schoolteacher.library.pojo.ApiResponse;
import com.schoolteacher.medvault.MedVaultActivity;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.Home;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.session.UserCurrentLocationManager;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.mylibrary.util.JeevOMUtil;
import com.schoolteacher.pojos.JeevCriteria;
import com.schoolteacher.pojos.JeevSearchAvailability;
import com.schoolteacher.pojos.JeevSearchCategory;
import com.schoolteacher.pojos.JeevSearchFilter;
import com.schoolteacher.pojos.JeevSearchGender;
import com.schoolteacher.pojos.JeevSearchRequisition;
import com.schoolteacher.pojos.JeevSearchTiming;
import com.schoolteacher.pojos.MemberHealthCardsData;
import com.schoolteacher.search.UserSavedSearches;

import com.schoolteacher.service.HealthVitalInterface;
import com.schoolteacher.util.JeevomUtilsClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class HomeActivity extends BaseClass
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AppConstants, TextToSpeech.OnUtteranceCompletedListener {
    ImageView a;
    private ProgressDialogFragment progressDialog;
    List<Integer> activatedIndicators1;
    private DrawerLayout drawer;
    private EventBus bus;
    private final long FIVE_MINUTE_IN_MILLIS = 5 * 60 * 1000;
    List<MemberHealthCardsData> healthCardList;
    private GlobalAlert globalAlert;
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1001;
    private TextToSpeech tts;
    Gson gson;

    public static List<confiPerson> confipersons;
   public static List<confiLength> confilength;
    public static List<Person> persons;
    public static List<Length> length;


    public static List<event> eventname;
    public static List<eventlen> eventlength;

    public static List<task> taskk;
    public static List<tasklen> tasklenn;


    private final int CHECK_CODE = 0x1;
    int speakint=9;
    private JeevomSession session;
    private long backPressedCounts = 0;
    RelativeLayout home_layout, MY_DAIRY_layout, MY_LOCKER_layout, MY_TIMETABLE_layout, MY_SYLLABUS_layout, deals_offer_layout, health_tips_layout, profile_layout;
    private JeevCriteria jeevCriteria;
    private JeevSearchCategory jeevCategory;
    private JeevSearchAvailability jeevAvailability;
    private JeevSearchGender jeevGender;
    private JeevSearchRequisition jeevRequistion;
    private JeevSearchTiming jeevTiming;
    private JeevSearchFilter filter;
    private boolean isNearMeChecked;
    private UserCurrentLocationManager locationManager;
    private String locationFromGps;
    int callCount = 0;
    int NoOfItems = 30;
    public FragmentTransaction fragTransaction;
    public static int s=0;
    public FragmentManager fragmentManager;
    public int x=0;
    public static int matrixx;
   public  Home hsession;
    public Fragment fragment;
    ImageView addfnd,mike;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        hsession = new Home(HomeActivity.this);

       session = new JeevomSession(HomeActivity.this);
        bus = EventBus.getDefault();
        bus.register(this);

        locationManager = new UserCurrentLocationManager(this);

        globalAlert = new GlobalAlert(HomeActivity.this);

        gson = new GsonBuilder().create();
        if (locationManager.getUserLocation().getAddress0() != null && locationManager.getUserLocation().getAddress1() != null && locationManager.getUserLocation().getAddress2() != null)
            locationFromGps = locationManager.getUserLocation().getAddress0() + "," + locationManager.getUserLocation().getAddress1() + "," + locationManager.getUserLocation().getAddress2();

       // Toast.makeText(HomeActivity.this,getDeviceName()+ "",Toast.LENGTH_SHORT).show();
      //  System.out.println(getDeviceName());
// Using https://github.com/jaredrummler/AndroidDeviceNames
        //System.out.println(DeviceName.getDeviceName());
        hsession.setKey_HomeId("true");
        hsession.setKey_Profile("true");

       // mike=(ImageView)findViewById(R.id.btSpeak);
        initializeData();
        //confi_initializeData();
       // tts = new TextToSpeech(HomeActivity.this, HomeActivity.this);

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {

                 //   Toast.makeText(HomeActivity.this, "doner", Toast.LENGTH_SHORT).show();
                    tts.setOnUtteranceCompletedListener(HomeActivity.this);
                    if (tts.isLanguageAvailable(Locale.ENGLISH) >= 0)
                        tts.setLanguage(Locale.ENGLISH);
                    //tts.setLanguage(tts.getDefaultLanguage());

                    tts.setPitch(5.0f);
                    tts.setSpeechRate(1.0f);
                }
            }
        });

      /*  mike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(HomeActivity.this, "hio", Toast.LENGTH_SHORT).show();

if(session.getLoggedInStatus()==true){
                speaktext();}else{

}
                // speak();
            }
        });*/

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }re
        });*/
      //  checkTTS();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    t=(TextView)findViewById(R.id.t);
      //  addfnd=(ImageView)findViewById(R.id.a);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // if session is logged in , set the alarm for notification

        if (session.getLoggedInStatus()) {
            setNotificationAlarm();
        }

        navigationView.getMenu().performIdentifierAction(R.id.home_btn, 0);


/*
        if(s==1){
            showRelatedFragment(TEACHER_AND_CLASSMATE);
            resetSelectedItems(TEACHER_AND_CLASSMATE);


        }
*/

       /* a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ///  Toast.makeText(HomeActivity.this, "dddddd", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(HomeActivity.this, Invitefriend.class);
                startActivity(myIntent);
                overridePendingTransition(com.jeevom.mylibrary.R.anim.trans_left_in, com.jeevom.mylibrary.R.anim.trans_left_exit);
                //  finish();
            }
        });*/

/*
        addfnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ///  Toast.makeText(HomeActivity.this, "dddddd", Toast.LENGTH_SHORT).show();

               if(session.getLoggedInStatus()){
                Intent myIntent = new Intent(HomeActivity.this,Invitefriend.class);
                startActivity(myIntent);
                overridePendingTransition(com.school.mylibrary.R.anim.trans_left_in, com.school.mylibrary.R.anim.trans_left_exit);
                //  finish();
                }
            }
        });*/
    }

    public void confi_initializeData(){


        confipersons= new ArrayList<>();


        confilength=new ArrayList<>();
    }
    private void initializeData(){





        taskk = new ArrayList<>();


        tasklenn=new ArrayList<>();



        eventname = new ArrayList<>();


        eventlength=new ArrayList<>();


        persons = new ArrayList<>();


length=new ArrayList<>();



    }
    public void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // Specify the calling package to identify your application
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
                .getPackage().getName());

        // Display an hint to the user about what he should say.
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Cortex(Virtual Assistent)");

        // Given an hint to the recognizer about what the user is going to say
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);


        /*intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,
                100);*/
        // If number of Matches is not selected then return show toast message
       /* if (msTextMatches.getSelectedItemPosition() == AdapterView.INVALID_POSITION) {
            Toast.makeText(this, "Please select No. of Matches from spinner",
                    Toast.LENGTH_SHORT).show();
            return;
        }*/

       /* int noOfMatches = Integer.parseInt(msTextMatches.getSelectedItem()
                .toString());*/
        // Specify how many results you want to receive. The results will be
        // sorted where the first result is the one with higher confidence.

        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);

        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            } else {

                if (session != null && !session.getLoggedInStatus()) {
                    Intent intent = new Intent(this, IntroScreenActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.trans_right_in,
                            R.anim.trans_right_exit);
                    finish();
                } else {
                    long t = System.currentTimeMillis();

                    if (t - backPressedCounts > 2000) {
                        backPressedCounts = t;
                        Toast.makeText(HomeActivity.this, "Press back again to exit",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        super.onBackPressed();
                        overridePendingTransition(R.anim.trans_right_in,
                                R.anim.trans_right_exit);
                    }

                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
          /*  case R.id.a:
                //  Toast.makeText(HomeActivity.this, "sdfs", Toast.LENGTH_SHORT).show();
                break;*/
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;




            case R.id.noti:

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new NotificationSlideDown();
                fragTransaction.setCustomAnimations(R.anim.trans_up_in, R.anim.trans_up_exit);
                fragTransaction.replace(R.id.container_body, fragment);
                fragTransaction.commit();
                t.setText("Notifications");

                return true;


            case R.id.inbox:
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fg = new Inbox();
                t.setText("Inbox");
                ft.setCustomAnimations(R.anim.trans_up_in, R.anim.trans_up_exit);
                ft.replace(R.id.container_body, fg);
                ft.commit();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        item.setChecked(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (id == R.id.home_btn) {
                    showRelatedFragment(HOME_SCREEN);
                    //  resetSelectedItems(HOME_SCREEN);

                } else if (id == R.id.My_Colleagues) {
                    showRelatedFragment(MY_COLLEAGUES);
                    //   resetSelectedItems(MY_DAIRY);

                } else if (id == R.id.Circulars) {
                    showRelatedFragment(CIRCULARS);
                    //   resetSelectedItems(MY_DAIRY);

                } else if (id == R.id.SCH_PTM) {
                    showRelatedFragment(MEMORYLANE);
                    //   resetSelectedItems(MY_DAIRY);

                } else if (id == R.id.CLASS_LIST) {
                    showRelatedFragment(CLASS_LIST);
                    //   resetSelectedItems(MY_DAIRY);

                } else if (id == R.id.MY_DAIRY) {
                    showRelatedFragment(MY_DAIRY);
                    //   resetSelectedItems(MY_DAIRY);

                } else if (id == R.id.SHOWCASE) {
                    showRelatedFragment(SHOWCASE);
                    //  resetSelectedItems(HOME_SCREEN);

                } else if (id == R.id.SOCIAL_MEDIA) {
                    showRelatedFragment(SOCIAL_MEDIA);
                    //callMyRequestScreen();

                } else if (id == R.id.NOTICE_BOARD) {
                    showRelatedFragment(NOTICE_BOARD);
                    //   resetSelectedItems(HOME_SCREEN);

                } else if (id == R.id.MY_LOCKER) {
                    showRelatedFragment(MY_LOCKER);
                    //  resetSelectedItems(MY_LOCKER);

                } else if (id == R.id.MY_ATTENDANCE) {
                    showRelatedFragment(MY_ATTENDANCE);
                    //   resetSelectedItems(MY_ATTENDANCE);

                } else if (id == R.id.MY_TIMETABLE) {
                    showRelatedFragment(MY_TIMETABLE);
                    //  resetSelectedItems(MY_TIMETABLE);

                } else if (id == R.id.CLASS_D_BOARD) {
                    showRelatedFragment(CLASS_D_BOARD);
                    //   resetSelectedItems(MY_REPORTCARD);

                } else if (id == R.id.CLASS_HOMEWORK) {
                    showRelatedFragment(CLASS_HOMEWORK);
                    //   resetSelectedItems(MY_REPORTCARD);

                } else if (id == R.id.School_Holidays) {
                    showRelatedFragment(SCHOOL_HOLIDAYS);
                    //   resetSelectedItems(MY_DAIRY);
                } else if (id == R.id.Head_Count) {
                    showRelatedFragment(HEAD_COUNT);
                    //   resetSelectedItems(MY_DAIRY);
                }
                else if (id == R.id.School_Analytics) {
                    showRelatedFragment(SCHOOL_ANALYTICS);
                    //   resetSelectedItems(MY_DAIRY);
                }


            }
        }, 250);

        return true;
    }

    @Override
    public void onClick(View view) {

     //   resetSelectedItems(0);

        view.setSelected(true);


    }

public void healthtips(){
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
    Fragment fragment = null;
    fragment = new HealthTipsListFragment();

    t.setText("Teaching Tips");
    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    fragTransaction.replace(R.id.container_body, fragment);
    fragTransaction.commit();


}
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }
    public void showRelatedFragment(int i) {

         fragmentManager = getSupportFragmentManager();
         fragTransaction = fragmentManager.beginTransaction();
         fragment = null;

        if (i == HOME_SCREEN) {
            fragment = new HomeFragment();
            getSupportActionBar().setTitle(getResources().getString(R.string.home));
            t.setText("Class Dashboard");
        }
        else if (i == MY_DAIRY) {
            fragment = new HealthBuddyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("stote_view", x);
            fragment.setArguments(bundle);
            t.setText("My Diary");

        }
        else if (i == CLASS_LIST) {
            fragment = new Clas_list();
            t.setText("Class List");
        }
        else if (i == HEAD_COUNT) {
            fragment = new Head_count();
            t.setText("Head Count");
        }
        else if (i == CIRCULARS) {
            fragment = new Circular();
            t.setText("Circulars");
        }
        else if (i == MY_COLLEAGUES) {
            fragment = new My_Colleagues();
            t.setText("My Colleagues");
        }

        else if (i == SOCIAL_MEDIA) {
            fragment = new social_media();

            t.setText("Social Media");
        }

        else if (i == NOTICE_BOARD) {
            fragment = new PopularSearchFragment();
            t.setText("Notice Board");

        } else if (i == SHOWCASE) {
            fragment = new showcase();
            t.setText("Showcase");

        }
        else if (i == MY_LOCKER) {

            fragment = new mylocker();
            t.setText("My Locker");

        } else if (i == MY_ATTENDANCE) {
            fragment = new DealsAndOfferFragment();

            t.setText("Class Attendance");
        }

        else if (i == MY_TIMETABLE) {
           fragment = new HealthCloudFragment();
            t.setText("My Timetable");



        }
        else if (i == CLASS_HOMEWORK) {
            fragment = new class_homework();

            t.setText("Class Homework");
        }
       else if (i == SCHOOL_HOLIDAYS) {
            fragment = new School_Holiday();
            t.setText("School Holiday");

        }

        else if (i == MEMORYLANE) {
            fragment = new ProductOverviewFragment();
            t.setText("Schedule PTM");

        }
        else if (i == CLASS_D_BOARD) {
            fragment = new AboutUsFragment();
            t.setText("Discussion Board");

        }
        else if (i == SCHOOL_ANALYTICS) {
            fragment = new Class_Analytics();
            t.setText("School Analytics");

        }


        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragTransaction.replace(R.id.container_body, fragment);
        fragTransaction.commit();
    }


public void claa_circulars(){
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
    Fragment fragment = null;
    fragment = new Circular();
    t.setText("Circulars");
    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    fragTransaction.replace(R.id.container_body, fragment);
    fragTransaction.commit();
}
    private void checkTTS(){
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, CHECK_CODE);
    }

    public void call_attendence(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        fragment = new DealsAndOfferFragment();
        getSupportActionBar().setTitle(getResources().getString(R.string.MY_ATTENDANCE));
        t.setText("Class Attendance");
        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragTransaction.replace(R.id.container_body, fragment);
        fragTransaction.commit();
    }
public void call_classhomework(){
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
    Fragment fragment = null;
    fragment = new class_homework();
    t.setText("Class Homework");
    fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    fragTransaction.replace(R.id.container_body, fragment);
    fragTransaction.commit();
}

    public void call_timetablee(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        fragment = new HealthTipsListFragment();
        fragment = new HealthCloudFragment();
        t.setText("My Timetable");
        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragTransaction.replace(R.id.container_body, fragment);
        fragTransaction.commit();
    }

    public void call_inbox(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        fragment = new HealthTipsListFragment();
        fragment= new Inbox();
        t.setText("Inbox");
        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragTransaction.replace(R.id.container_body, fragment);
        fragTransaction.commit();
    }

    public void call_task(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        fragment = new HealthTipsListFragment();
        fragment = new HealthBuddyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("stote_view", 1);
        fragment.setArguments(bundle);
        t.setText("My Diary");
        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragTransaction.replace(R.id.container_body, fragment);
        fragTransaction.commit();
    }
    public void call_event(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        fragment = new HealthTipsListFragment();
        fragment = new HealthBuddyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("stote_view", 2);
        fragment.setArguments(bundle);
        t.setText("My Diary");
        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragTransaction.replace(R.id.container_body, fragment);
        fragTransaction.commit();
    }

    public void call_notes(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        fragment = new HealthTipsListFragment();
        fragment = new HealthBuddyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("stote_view", 0);
        fragment.setArguments(bundle);
        t.setText("My Diary");
        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragTransaction.replace(R.id.container_body, fragment);
        fragTransaction.commit();
    }

    public void call_attendence(int x){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        fragment = new HealthBuddyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("stote_view", x);
        fragment.setArguments(bundle);
        t.setText("Class Attendance");

        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragTransaction.replace(R.id.container_body, fragment);
        fragTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_APPOINT_REQUEST_CODE && resultCode == RESULT_OK) {
            callMyRequestScreen();
        } else if (requestCode == SAVED_SEARCHES_REQUEST_CODE && resultCode == RESULT_OK) {
            callMySavedSearchScreen();
        } else if (requestCode == MEDVAULT_REQUEST_CODE && resultCode == RESULT_OK) {
            callHealthLockerScreen();
        } else if (requestCode == TEACHER_AND_CLASSMATE_REQUEST_CODE && resultCode == RESULT_OK) {
            callHealthProfileScreen(null);
        } else if (requestCode == HEALTH_TRACK_REQUEST_CODE && resultCode == RESULT_OK) {
            callHealthVitalsScreen();
        } else if (requestCode == 9 && resultCode == RESULT_OK) {
            Intent i = new Intent(HomeActivity.this, ProfileFragment.class);
            startActivity(i);
        }
        if(requestCode == CHECK_CODE){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
            //    speaker = new Speaker(this);
            }else {
                Intent install = new Intent();
                install.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(install);
            }
        }
    if (requestCode == VOICE_RECOGNITION_REQUEST_CODE) {

            //If Voice recognition is successful then it returns RESULT_OK
            if (resultCode == RESULT_OK) {

                ArrayList<String> textMatchList = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);


                if (!textMatchList.isEmpty()) {

                  if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.openhome) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.gotohome)  || textMatchList.get(0).toString().equalsIgnoreCase(Voice.home)) {
                        //   Toast.makeText(HomeActivity.this, textMatchList.get(0).toString(), Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                        MY_LOCKER_layout.setSelected(false);
                        MY_TIMETABLE_layout.setSelected(false);
                        MY_LOCKER_layout.setSelected(false);
                        MY_DAIRY_layout.setSelected(false);
                        MY_SYLLABUS_layout.setSelected(false);
                        deals_offer_layout.setSelected(false);
                        health_tips_layout.setSelected(false);
                        profile_layout.setSelected(false);
                        home_layout.setSelected(true);
                        Fragment fragment = new HomeFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.home));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();
                      Toast.makeText(HomeActivity.this, "Opening Home...", Toast.LENGTH_SHORT).show();


                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.healthmart) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openhealthmart)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                        MY_TIMETABLE_layout.setSelected(true);

                        MY_LOCKER_layout.setSelected(false);

                        MY_LOCKER_layout.setSelected(false);
                        MY_DAIRY_layout.setSelected(false);
                        MY_SYLLABUS_layout.setSelected(false);
                        deals_offer_layout.setSelected(false);
                        health_tips_layout.setSelected(false);
                        profile_layout.setSelected(false);
                        home_layout.setSelected(false);
                        Fragment fragment = new HealthMartFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.MY_TIMETABLE));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();
                      Toast.makeText(HomeActivity.this, "Opening Health Mart...", Toast.LENGTH_SHORT).show();
                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.healthbuddy) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openhealthbuddy)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                        MY_DAIRY_layout.setSelected(true);

                        MY_TIMETABLE_layout.setSelected(false);

                        MY_LOCKER_layout.setSelected(false);

                        MY_LOCKER_layout.setSelected(false);

                        MY_SYLLABUS_layout.setSelected(false);
                        deals_offer_layout.setSelected(false);
                        health_tips_layout.setSelected(false);
                        profile_layout.setSelected(false);
                        home_layout.setSelected(false);
                        Fragment fragment = new HealthBuddyFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.MY_DAIRY));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();
                      Toast.makeText(HomeActivity.this, "Opening Health Buddy...", Toast.LENGTH_SHORT).show();
                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.healthcloud) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openhealthcloud)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                        MY_LOCKER_layout.setSelected(true);


                        MY_DAIRY_layout.setSelected(false);

                        MY_TIMETABLE_layout.setSelected(false);


                        MY_SYLLABUS_layout.setSelected(false);
                        deals_offer_layout.setSelected(false);
                        health_tips_layout.setSelected(false);
                        profile_layout.setSelected(false);
                        home_layout.setSelected(false);
                        Fragment fragment = new HealthCloudFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.MY_LOCKER));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();

                      Toast.makeText(HomeActivity.this, "Opening Health Cloud...", Toast.LENGTH_SHORT).show();
                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.dealsandoffers) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.opendealsandoffers)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                        deals_offer_layout.setSelected(true);
                        MY_LOCKER_layout.setSelected(false);


                        MY_DAIRY_layout.setSelected(false);

                        MY_TIMETABLE_layout.setSelected(false);


                        MY_SYLLABUS_layout.setSelected(false);

                        health_tips_layout.setSelected(false);
                        profile_layout.setSelected(false);
                        home_layout.setSelected(false);
                        Fragment fragment = new DealsAndOfferFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.deals_offer));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();

                      Toast.makeText(HomeActivity.this, "Opening Deals Offers...", Toast.LENGTH_SHORT).show();
                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.healthsocial) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openhealthsocial)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                        MY_SYLLABUS_layout.setSelected(true);

                        deals_offer_layout.setSelected(false);
                        MY_LOCKER_layout.setSelected(false);


                        MY_DAIRY_layout.setSelected(false);

                        MY_TIMETABLE_layout.setSelected(false);


                        health_tips_layout.setSelected(false);
                        profile_layout.setSelected(false);
                        home_layout.setSelected(false);
                        Fragment fragment = new HealthSocialFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.MY_SYLLABUS));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();

                      Toast.makeText(HomeActivity.this, "Opening Health Social...", Toast.LENGTH_SHORT).show();
                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.healthlibrary) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openhealthlibrary)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                        health_tips_layout.setSelected(true);

                        MY_SYLLABUS_layout.setSelected(false);

                        deals_offer_layout.setSelected(false);
                        MY_LOCKER_layout.setSelected(false);


                        MY_DAIRY_layout.setSelected(false);

                        MY_TIMETABLE_layout.setSelected(false);


                        profile_layout.setSelected(false);
                        home_layout.setSelected(false);
                        Fragment fragment = new HealthLibraryFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.MY_REPORTCARD));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();

                      Toast.makeText(HomeActivity.this, "Opening Health Library...", Toast.LENGTH_SHORT).show();
                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.profile) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openprofile)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                        profile_layout.setSelected(true);
                        MY_SYLLABUS_layout.setSelected(false);

                        deals_offer_layout.setSelected(false);
                        MY_LOCKER_layout.setSelected(false);


                        MY_DAIRY_layout.setSelected(false);

                        MY_TIMETABLE_layout.setSelected(false);


                        health_tips_layout.setSelected(false);

                        home_layout.setSelected(false);
                        Fragment fragment = new ProfileFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.profile));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();

                      Toast.makeText(HomeActivity.this, "Opening My Profile...", Toast.LENGTH_SHORT).show();
                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.productoverview) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openproductoverview)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
                        Fragment fragment = new ProductOverviewFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.product_overview));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();

                      Toast.makeText(HomeActivity.this, "Opening Product Over View...", Toast.LENGTH_SHORT).show();
                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.aboutus) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openaboutus)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                        Fragment fragment = new AboutUsFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.about));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();

                      Toast.makeText(HomeActivity.this, "Opening About Us...", Toast.LENGTH_SHORT).show();
                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.invitefriend) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openinvitefriend)) {
                      Intent myIntent = new Intent(HomeActivity.this,Invitefriend.class);
                      startActivity(myIntent);
                      overridePendingTransition(com.schoolteacher.mylibrary.R.anim.trans_left_in, com.schoolteacher.mylibrary.R.anim.trans_left_exit);

                      Toast.makeText(HomeActivity.this, "Opening Invite friend...", Toast.LENGTH_SHORT).show();

                    } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.popularsearch) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.openpopularsearch)) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                        Fragment fragment = new PopularSearchFragment();
                        getSupportActionBar().setTitle(getResources().getString(R.string.SHOWCASE));
                        fragTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragTransaction.replace(R.id.container_body, fragment);
                        fragTransaction.commit();
                      Toast.makeText(HomeActivity.this, "Opening Popular Searches...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addcholestrol) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.cholestrol)) {

                        fill();
                        speakint = 1;
                      Toast.makeText(HomeActivity.this, "Adding Cholestrol to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addtriglycerides) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.addtriglycerides)) {

                        fill();
                        speakint = 2;
                      Toast.makeText(HomeActivity.this, "Adding Triglycerides to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addhdl) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.hdl)) {

                        fill();
                        speakint = 3;
                      Toast.makeText(HomeActivity.this, "Adding HDL to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addldl) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.ldl)) {

                        fill();
                        speakint = 4;
                      Toast.makeText(HomeActivity.this, "Adding LDL to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addtsh) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.tsh)) {

                        fill();
                        speakint = 5;
                      Toast.makeText(HomeActivity.this, "Adding TSH to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addt3) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.t3)) {

                        fill();
                        speakint = 6;
                      Toast.makeText(HomeActivity.this, "Adding T3 to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addt4) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.t4)) {

                        fill();
                        speakint = 7;
                      Toast.makeText(HomeActivity.this, "Adding T4 to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addweight) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.weight1)) {

                        fill();
                        speakint = 13;
                      Toast.makeText(HomeActivity.this, "Adding Weight to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addheight1) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.height1)) {

                        fill();
                        speakint = 12;
                      Toast.makeText(HomeActivity.this, "Adding Height to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addbloodsugar) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.bloodsugar)) {

                        fill();
                        speakint = 10;
                      Toast.makeText(HomeActivity.this, "Adding Blood Sugar to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addhaemoglobin) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.haemoglobin)) {

                        fill();
                        speakint = 11;
                      Toast.makeText(HomeActivity.this, "Adding Haemoglobin to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addbloodpressure) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.bloodpressure)) {

                        fill();
                        speakint = 9;
                      Toast.makeText(HomeActivity.this, "Adding Blood Pressure to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addbtemperaturee) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.temperature)) {

                        fill();
                        speakint = 8;
                      Toast.makeText(HomeActivity.this, "Adding Temperature to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addtest1) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.test1)) {

                        fill();
                        speakint = 0;
                      Toast.makeText(HomeActivity.this, "Adding Test to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addiron) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.iron)) {

                        fill();
                        speakint = 20;
                      Toast.makeText(HomeActivity.this, "Adding Iron to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.adduricacid) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.uricacid)) {

                        fill();
                        speakint = 14;
                      Toast.makeText(HomeActivity.this, "Adding Uric Acid to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addcalcium) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.calcium)) {

                        fill();
                        speakint = 15;
                      Toast.makeText(HomeActivity.this, "Adding Calcium to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addvitamind) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.vitamind)) {

                        fill();
                        speakint = 16;
                      Toast.makeText(HomeActivity.this, "Adding Vitamin to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addvitaminb12) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.vitaminb12)) {

                        fill();
                        speakint = 17;
                      Toast.makeText(HomeActivity.this, "Adding Vitamin B-12 to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addaveragebloodglucose) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.averagebloodglucose)) {

                        fill();
                        speakint = 18;
                      Toast.makeText(HomeActivity.this, "Adding Average Blood Glucose to DashBoard...", Toast.LENGTH_SHORT).show();

                  } else if (textMatchList.get(0).toString().equalsIgnoreCase(Voice.addthyroid) || textMatchList.get(0).toString().equalsIgnoreCase(Voice.thyroid)) {

                        fill();
                        speakint =19 ;
                      Toast.makeText(HomeActivity.this, "Adding Thyroid to DashBoard...", Toast.LENGTH_SHORT).show();

                  }else{
                      showAlert("No result matches...");
                  }


                }
                //Result code for various error.
            } else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
                showToastMessage("Audio Error");
            } else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR) {
                showToastMessage("Client Error");
            } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
                showToastMessage("Network Error");
            } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
                showToastMessage("No Match");
            } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
                showToastMessage("Server Error");
            }
       /* else if(requestCode==9 ){
            Toast.makeText(HomeActivity.this, "exexjhsdf", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(HomeActivity.this,ViewProfile.class);
            startActivity(i);
            overridePendingTransition(R.anim.trans_left_in,
                    R.anim.trans_left_exit);

        }*/
        }

    }
    private void showAlert(String message) {
        globalAlert.show();
        globalAlert.setMessage(message);
    }

    void fill(){
        RestAdapter healthIndicatorsAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("healthcards")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        HealthVitalInterface healthVitals = healthIndicatorsAdapter
                .create(HealthVitalInterface.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(this.getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        healthVitals.getMemberAvailableHealthCards(gson.toJson(locationManager.getUserLocation()).toString(), session.getMemberId(), new Callback<ApiResponse<List<MemberHealthCardsData>>>() {
            @Override
            public void success(ApiResponse<List<MemberHealthCardsData>> listApiResponse, Response response) {
                progressDialog.dismissAllowingStateLoss();
                healthCardList = listApiResponse.getData();
                //healthCardList.get(0).getHealthIndicator().getId();

                call();

            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismissAllowingStateLoss();
               // Toast.makeText(HomeActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
    void call(){

        activatedIndicators1 = new LinkedList<>();

        for (MemberHealthCardsData object : healthCardList) {
            activatedIndicators1.add(object.getHealthIndicator().getId());

        }
        activatedIndicators1.add(speakint);
        //Toast.makeText(HomeActivity.this, flag+"", Toast.LENGTH_SHORT).show();



        RestAdapter healthIndicatorsAdapter = new RestAdapter.Builder()
                .setLog(new AndroidLog("healthcards")).setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(JeevOMUtil.baseUrl).build();
        HealthVitalInterface healthVitals = healthIndicatorsAdapter
                .create(HealthVitalInterface.class);
        progressDialog = ProgressDialogFragment.newInstance();
        progressDialog.show(this.getSupportFragmentManager(), "dialog");
        progressDialog.setCancelable(false);
        healthVitals.updateHealthIndicators(gson.toJson(locationManager.getUserLocation()).toString(), session.getMemberId(), activatedIndicators1, new Callback<ApiResponse<Boolean>>() {
            @Override
            public void success(ApiResponse<Boolean> booleanApiResponse, Response response) {
                progressDialog.dismissAllowingStateLoss();
                Toast.makeText(HomeActivity.this, "Updated Dashboard", Toast.LENGTH_SHORT).show();
                // finish();
                //upload();

                JeevomUtilsClass.isFreshLoad = true;
                Intent i = new Intent(HomeActivity.this, HealthVitals.class);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {
                progressDialog.dismissAllowingStateLoss();
             //   Toast.makeText(HomeActivity.this, "Failed to Update Dashboard.Try Again", Toast.LENGTH_SHORT).show();
                JeevomUtilsClass.isFreshLoad = true;
            }
        });
    }


    void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void setNotificationAlarm() {
        Intent intent = new Intent(HomeActivity.this, AppointmentNotifier.class);
        PendingIntent pendingintent = PendingIntent.getBroadcast(
                HomeActivity.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) HomeActivity.this
                .getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), FIVE_MINUTE_IN_MILLIS,
                pendingintent);
    }


    public void callLogIn(int requestCode) {
        Intent i = new Intent(HomeActivity.this, SignUpLoginActivity.class);
        startActivityForResult(i, requestCode);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_exit);
    }

    public void callMyRequestScreen() {
        Intent intent = new Intent(HomeActivity.this, ServiceRequestsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_exit);
    }

    public void callMySavedSearchScreen() {
        Intent saveSearchIntent = new Intent(HomeActivity.this,
                UserSavedSearches.class);
        startActivity(saveSearchIntent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_exit);
    }

    public void callHealthLockerScreen() {
        Intent medVaultIntent = new Intent(HomeActivity.this,
                MedVaultActivity.class);
        startActivity(medVaultIntent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_exit);
    }


    public void callHealthVitalsScreen() {
        Intent healthVitalIntent = new Intent(HomeActivity.this, HealthVitals.class);
        startActivity(healthVitalIntent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_exit);
    }


    public void calladdfamily() {
        Intent addMoreIntent = new Intent(HomeActivity.this,
                FamilyInformation.class);
        startActivityForResult(addMoreIntent, 1);
    }

    public void callHealthProfileScreen(String basicProfileId) {
        Intent intent = new Intent(HomeActivity.this, ViewProfile.class);
        intent.putExtra("member_profile_id", basicProfileId);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_exit);
    }

    public void callHealthDiaryScreen() {
        showToastMessage();
    }

    public void callHealthConnectionScreen() {
        showToastMessage();
    }

//    public void callDealOfferScreen() {
//        Intent intent = new Intent(HomeActivity.this, GeneralActivity.class);
//        intent.putExtra("screen_name", AppConstants.DEALS_AND_OFFER_PAGE);
//        startActivity(intent);
//        overridePendingTransition(R.anim.trans_left_in,
//                R.anim.trans_left_exit);
//    }

    public void callHealthTipsScreen() {
        Intent intent = new Intent(HomeActivity.this, GeneralActivity.class);
        intent.putExtra("screen_name", AppConstants.HEALTH_TIPS_PAGE);
        startActivity(intent);
        overridePendingTransition(R.anim.trans_left_in,
                R.anim.trans_left_exit);
    }

    @Override
    protected void onResume() {
        super.onResume();

        jeevCriteria = new JeevCriteria();

        jeevCategory = new JeevSearchCategory();
        jeevAvailability = new JeevSearchAvailability();
        jeevGender = new JeevSearchGender();
        jeevRequistion = new JeevSearchRequisition();
        jeevTiming = new JeevSearchTiming();

        makeCategoryObject("any");
        makeAvailabilityObject();
        makeGenderObject();
        makeRequisitionObject();
        makeTimingObject();
        makeCriteriaObject();
        makeFilterObject();
    }


    public void showToastMessage() {
        Toast toast = Toast.makeText(getApplicationContext(), "Coming soon! Keep visited", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }



    public void openFilterActivity() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fg = new Inboxx();

        ft.setCustomAnimations(R.anim.trans_up_in, R.anim.trans_up_exit);
        ft.replace(R.id.container_body, fg);
        ft.commit();
    }

    public void makeCriteriaObject() {
        jeevCriteria.setSearchString(null);
        jeevCriteria.setCategory(jeevCategory);
        jeevCriteria.setAvailability(jeevAvailability);
        jeevCriteria.setGender(jeevGender);
        jeevCriteria.setRequisition(jeevRequistion);
        jeevCriteria.setTiming(jeevTiming);
    }

    public void makeFilterObject() {
        filter = new JeevSearchFilter();
        filter.setDistance(5);
        filter.setIsPremium(jeevCriteria.isValued());
        filter.setIsRecommended(false);
        filter.setIsVerified(jeevCriteria.isVerified());
        filter.setLatitude(0.0);
        filter.setLocation(getLocationFromAddressOrGps());
        filter.setLongitude(0.0);
        filter.setSkip(callCount);
        filter.setTop(NoOfItems);
        filter.setIsDiscountOffered(jeevCriteria.isDiscount());
        isNearMeChecked = true;
    }

    public String getLocationFromAddressOrGps() {
        String userLocation;
        if (session.getLoggedInStatus()) {

            if (session.getUserAddress() != null) {
                if (!CommonCode.isNullOrEmpty(session.getUserAddress().getCity())) {
                    userLocation = session.getUserAddress().getArea() + "," + session.getUserAddress().getCity();
                } else if (!CommonCode.isNullOrEmpty(locationFromGps) && locationManager.getUserLocation().getAddress1() != null) {
                    userLocation = locationFromGps;
                } else {
                    userLocation = "Delhi";
                }
            } else if (!CommonCode.isNullOrEmpty(locationFromGps) && locationManager.getUserLocation().getAddress1() != null) {
                userLocation = locationFromGps;
            } else {
                userLocation = "Delhi";
            }

        } else if (!CommonCode.isNullOrEmpty(locationFromGps) && locationManager.getUserLocation().getAddress1() != null) {
            userLocation = locationFromGps;
        } else {
            userLocation = "Delhi";
        }
        return userLocation;
    }

    public void makeCategoryObject(String value) {

        if (value.equalsIgnoreCase("labs_pharmacies")) {
            jeevCategory.setAlternateMedicine(false);
            jeevCategory.setDoctorClinic(false);
            jeevCategory.setGymFitness(false);
            jeevCategory.setHealing(false);
            jeevCategory.setHealthcareSupport(false);
            jeevCategory.setHospitalNursing(false);
            jeevCategory.setLabDiagnostic(true);
            jeevCategory.setPharmacies(true);
            jeevCategory.setSpaWellness(true);
        }

    }

    public void makeAvailabilityObject() {
        jeevAvailability.setFriday(false);
        jeevAvailability.setMonday(false);
        jeevAvailability.setSaturday(false);
        jeevAvailability.setSunday(false);
        jeevAvailability.setThrusday(false);
        jeevAvailability.setTuesday(false);
        jeevAvailability.setWednesday(false);
    }

  /*  @Override
    protected void onDestroy() {
        super.onDestroy();
    }*/

    public void makeGenderObject() {
        jeevGender.setBoth(true);
        jeevGender.setFemale(false);
        jeevGender.setMale(false);
    }

    public void makeRequisitionObject() {
        jeevRequistion.setChat(false);
        jeevRequistion.setClinic(false);
        jeevRequistion.setEmail(false);
        jeevRequistion.setHome(false);
        jeevRequistion.setPhone(false);
        jeevRequistion.setVideo(false);
    }

    public void makeTimingObject() {
        jeevTiming.setAfternoon(false);
        jeevTiming.setEvening(false);
        jeevTiming.setMorning(false);
    }

    public <T> void onEvent(T event) {

    }


    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

  /*  @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Toast.makeText(HomeActivity.this, "doner", Toast.LENGTH_SHORT).show();
            tts.setOnUtteranceCompletedListener(this);
                //tts.setLanguage(tts.getDefaultLanguage());
            tts.setLanguage(Locale.UK);
            tts.setPitch(5.0f);
            tts.setSpeechRate(1.0f);

        }

    }*/
    void speaktext(){   HashMap<String, String> myHashAlarm = new HashMap<String, String>();
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
        myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SOME MESSAGE");
        tts.speak(HomeFragment.sname+"  I  am  cortex    Your Health Care virtual assistent", TextToSpeech.QUEUE_FLUSH, myHashAlarm);
    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {
        HomeActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                //Toast.makeText(HomeActivity.this, "Hello", Toast.LENGTH_SHORT).show();

                speak();
            }
        });
    }
}