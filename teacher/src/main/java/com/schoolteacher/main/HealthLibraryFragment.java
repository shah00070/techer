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
public class HealthLibraryFragment extends Fragment implements View.OnClickListener {
    public View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.health_library_layout, container,
                false);
        setUiOnScreen();
        return rootView;
    }

    public void setUiOnScreen() {
        LinearLayout articles_layout = (LinearLayout) rootView.findViewById(R.id.articles_layout);
        LinearLayout forums_layout = (LinearLayout) rootView.findViewById(R.id.forums_layout);
        LinearLayout live_healthy_layout = (LinearLayout) rootView.findViewById(R.id.health_tips_layout);

        articles_layout.setOnClickListener(this);
        forums_layout.setOnClickListener(this);
        live_healthy_layout.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Library Fragment");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.articles_layout:
                ((HomeActivity) getActivity()).showToastMessage();
                break;

            case R.id.forums_layout:
                ((HomeActivity) getActivity()).showToastMessage();
                break;

            case R.id.health_tips_layout:
                ((HomeActivity) getActivity()).callHealthTipsScreen();
                break;

            default:
                break;
        }
    }

}
