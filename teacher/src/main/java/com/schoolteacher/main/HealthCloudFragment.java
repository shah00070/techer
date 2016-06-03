package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.fragments.Friday;
import com.schoolteacher.fragments.Monday;
import com.schoolteacher.fragments.Saturday;
import com.schoolteacher.fragments.Tuesday;
import com.schoolteacher.fragments.Wednesday;
import com.schoolteacher.fragments.thusday;
import com.schoolteacher.interfaces.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandan on 16/12/15.
 */
public class HealthCloudFragment extends Fragment implements  AppConstants {
    public View rootView;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // session = new JeevomSession(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.health_cloud_layout, container,
                false);
        setUiOnScreen();
        return rootView;
    }


    public void setUiOnScreen() {

      /*  toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
       // ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle();
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpagertt);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabsst);
        tabLayout.setupWithViewPager(viewPager);
       /* LinearLayout healthProfileLayout = (LinearLayout) rootView.findViewById(R.id.health_profile_layout);
        LinearLayout healthAnalyzerLayout = (LinearLayout) rootView.findViewById(R.id.health_analyzer_layout);
        LinearLayout healthLockerLayout = (LinearLayout) rootView.findViewById(R.id.health_locker_layout);
        LinearLayout healthDiaryLayout = (LinearLayout) rootView.findViewById(R.id.health_diary_layout);
        LinearLayout health_connections = (LinearLayout) rootView.findViewById(R.id.health_connection_layout);

        healthProfileLayout.setOnClickListener(this);
        healthAnalyzerLayout.setOnClickListener(this);
        healthLockerLayout.setOnClickListener(this);
        healthDiaryLayout.setOnClickListener(this);
        health_connections.setOnClickListener(this);*/

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new Monday(), "Monday");
        adapter.addFrag(new Tuesday(), "Tuesday");
        adapter.addFrag(new Wednesday(), "Wednesday");
        adapter.addFrag(new thusday(), "Thusday");
        adapter.addFrag(new Friday(), "Friday");
        adapter.addFrag(new Saturday(), "Saturday");
     /*   adapter.addFrag(new Sunday(), "Sunday");*/

        viewPager.setAdapter(adapter);
    }
    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        setUiOnScreen();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Cloud Fragment");
    }


}
