package com.schoolteacher.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.AppConstants;

/**
 * Created by chandan on 21/12/15.
 */
public class GeneralActivity extends BaseClass implements AppConstants {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_activity_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar_general);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_exit);
    }

}
