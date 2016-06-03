package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

/**
 * Created by chandan on 16/12/15.
 */
public class HealthSocialFragment extends Fragment implements View.OnClickListener {

    public View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.health_social_layout, container,
                false);
        setUiOnScreen();
        return rootView;
    }


    public void setUiOnScreen() {
        LinearLayout greenshield_layout = (LinearLayout) rootView.findViewById(R.id.greenshield_layout);
        LinearLayout lifeline_layout = (LinearLayout) rootView.findViewById(R.id.lifeline_layout);

        greenshield_layout.setOnClickListener(this);
        lifeline_layout.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Social Fragment");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.greenshield_layout:
                ((HomeActivity) getActivity()).showToastMessage();
                break;

            case R.id.lifeline_layout:
                ((HomeActivity) getActivity()).showToastMessage();
                break;
        }
    }
}
