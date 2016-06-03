package com.schoolteacher.main;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.dialog.ProgressDialogFragment;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;

import java.util.Calendar;


public class Addnote extends BaseClass   {
   EditText mobile,email;
   Gson gson;   Toolbar toolbar;
   public boolean invoke=false;
   // public boolean emailfound=false;
   Button invite; JeevomSession session;
   String authToken = null;
   public  int tag1=0,tag11=0;
   public EditText e;
   public ProgressDialogFragment progressBarFragment;
   public boolean finalmobile=false,finalemail=false;
   private GlobalAlert globalAlert;
   boolean x; String data;
    public static int linex;
   public String date,time;
    public EditText title,content;
    private Toolbar toolbar_careathome;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.appnote);
       setUpActionBar();
       session = new JeevomSession(getApplicationContext());
       //  System.out.print(session.getAuthToken());
       if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
           authToken = "Basic " + session.getAuthToken();
       }
       globalAlert = new GlobalAlert(Addnote.this);


       final Calendar c = Calendar.getInstance();
       int mYear = c.get(Calendar.YEAR);
       int  mMonth = c.get(Calendar.MONTH);
       int   mDay = c.get(Calendar.DAY_OF_MONTH);
          date = (mMonth+1) + "/"
               +(mDay )  + "/" + mYear;

       final Calendar cc = Calendar.getInstance();
       int   mHour = cc.get(Calendar.HOUR_OF_DAY);
       int  mMinute = cc.get(Calendar.MINUTE);

         time = mHour + ":" + mMinute;

       title=(EditText)findViewById(R.id.Titlee);
       content=(EditText)findViewById(R.id.cuntommmsg);

       Button clic=(Button)findViewById(R.id.upload_notes);
       clic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
               int dp=content.getLineCount();
               int fpixels= (int) ((dp*24) * metrics.density );
               HomeActivity.persons.add(new Person(title.getText().toString(), content.getText().toString(),  time,date, R.color.transparentt,"Arun Sharma"));
               HomeActivity.length.add(new Length(fpixels));
               HomeActivity.matrixx=(int) metrics.density;




               finish();
           }
       });


     /*  toolbar = (Toolbar) findViewById(R.id.toolbar_view_profile);
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle("Invite");
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/


   }
    private void setUpActionBar() {
        toolbar_careathome = (Toolbar) findViewById(R.id.tool);

        setSupportActionBar(toolbar_careathome);
        getSupportActionBar().setTitle("Upload notes");
        getSupportActionBar().setIcon(R.drawable.notify_icon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
   private void showAlert(String message) {
       globalAlert.show();
       globalAlert.setMessage(message);
   }



   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case android.R.id.home:
               onBackPressed();
               return true;
           default:
               return super.onOptionsItemSelected(item);
       }
   }

   @Override
   public boolean onPrepareOptionsMenu(Menu menu) {
       super.onPrepareOptionsMenu(menu);
//		MenuItem my_profile = menu.findItem(R.id.my_profile);
//		my_profile.setVisible(false);
       return true;
   }

   @Override
   public void onBackPressed() {
       super.onBackPressed();
       // Applying Exit Animation;
       overridePendingTransition(R.anim.trans_right_in,
               R.anim.trans_right_exit);
   }



}