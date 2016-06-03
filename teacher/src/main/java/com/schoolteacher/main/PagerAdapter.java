package com.schoolteacher.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Arun Sharma on 11-May-16.
 */
public class PagerAdapter  extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FirstTerm tab1 = new FirstTerm();
                return tab1;
            case 1:
                SecondTerm tab2 = new SecondTerm();
                return tab2;
            case 2:
                ThirdTerm tab3 = new ThirdTerm();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

