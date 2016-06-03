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
 * Created by shah on 16/12/15.
 */
public class HealthBuddyFragment extends Fragment implements View.OnClickListener, AppConstants {
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
        rootView = inflater.inflate(R.layout.health_buddy_layout, container,
                false);


        setUiOnScreen();
        return rootView;
    }

    public void setUiOnScreen() {

        Bundle bundle = this.getArguments();

        if(this.getArguments()!=null)
        {
             myInt = bundle.getInt("stote_view",0);
          //  int myInt = getArguments().getInt(key, defaultValue);
        }else{
            myInt=0;
        }


        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
viewPager.setCurrentItem(myInt);
      //  viewPager.getTabAt(myInt).select();
       // viewPager.setSelected(true);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        //setupTabIcons();


    }



    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
   /* private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);

       // tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
        tabOne.setText("Notes");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Tasks");
     //   tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_call, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        tabThree.setText("Events");
      //  tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }*/

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new RecyclerViewActivity(), "Notes");
        adapter.addFrag(new TwoFragment(), "Tasks");
        adapter.addFrag(new ThreeFragment(), "Events");

        viewPager.setAdapter(adapter);
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
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Buddy Fragment");
    }



    @Override
    public void onClick(View v) {


    }

}
