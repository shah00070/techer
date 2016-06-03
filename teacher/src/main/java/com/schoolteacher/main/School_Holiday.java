package com.schoolteacher.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

/**
 * Created by chandan on 23/5/16.
 */

public class School_Holiday extends Fragment {
    public Spinner spinner;
    public View rootView;
    public LinearLayout day,month,week;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.school_holiday, container, false);

        call();
        return rootView;
    }

    private void call() {
    }


    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Product overview Fragment");
    }

}

