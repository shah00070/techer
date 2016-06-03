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
import com.schoolteacher.fragments.ThreeFragment;
import com.schoolteacher.fragments.TwoFragment;
import com.schoolteacher.interfaces.AppConstants;
import com.schoolteacher.mylibrary.dialog.GlobalAlert;
import com.schoolteacher.mylibrary.session.JeevomSession;
import com.schoolteacher.mylibrary.util.CommonCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandan on 25/5/16.
 */

public class Class_Analytics extends Fragment implements  AppConstants {
    public View parent;
    public JeevomSession session;
    private View rootView;
    private GlobalAlert globalAlert;
    String authToken = null;
    private Toolbar toolbar;
    int v=0;
    int myInt;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab_favourite,
            R.drawable.ic_tab_call,
            R.drawable.ic_tab_contacts
    };
    public FloatingActionButton fab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new JeevomSession(getActivity());

        globalAlert = new GlobalAlert(getActivity());
        if (!CommonCode.isNullOrEmpty(session.getAuthToken())) {
            authToken = "Basic " + session.getAuthToken();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.class_analytic, container,
                false);


        setUiOnScreen();
        return rootView;
    }

    public void setUiOnScreen() {

    /*    Bundle bundle = this.getArguments();

        if(this.getArguments()!=null)
        {
            myInt = bundle.getInt("stote_view",0);
        }else{
            myInt=0;
        }
*/

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpagerr);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabss);
        tabLayout.setupWithViewPager(viewPager);




    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapterr adapter = new ViewPagerAdapterr(getChildFragmentManager());
        adapter.addFrag(new Student_Fee(), "Student Fee");
        adapter.addFrag(new Social_Media(), "Social Media");
        adapter.addFrag(new Store(), "Store");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapterr extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapterr(FragmentManager manager) {
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
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Buddy Fragment");
    }





}
