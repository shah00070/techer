package com.schoolteacher.main;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.MemberInsuranceDetail;
import com.schoolteacher.util.JeevomUtilsClass;

import java.util.List;

public class InsuranceDetailsFragment extends Fragment {
    View rootView;
    LinearLayout insurance_details;
    boolean isNothingVisible;
    private ScrollView scrollView_insurance_detail;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getActivity()));
        rootView = inflater.inflate(R.layout.insurance_fragment_layout, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Insurance Details Fragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        insurance_details = (LinearLayout) rootView.findViewById(R.id.insurance_details);

        // fill insurance details
        if (ViewProfile.consumer.getData().getMemberInsuranceDetails() != null) {
            List<MemberInsuranceDetail> insurance = ViewProfile.consumer.getData().getMemberInsuranceDetails();

            scrollView_insurance_detail = (ScrollView) rootView.findViewById(R.id.sv_insurance_detail);

            if (insurance_details.getChildCount() > 0) {
                insurance_details.removeAllViews();
            }

            for (MemberInsuranceDetail object : insurance) {

                isNothingVisible = true;
                LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View view = inflator.inflate(R.layout.insurance_details_row, null);
                if (insurance_details.getChildCount() % 2 == 0) {
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#efefef"));
                }
                TextView heading_value = (TextView) view.findViewById(R.id.heading_value);
                TextView website_value = (TextView) view.findViewById(R.id.website_value);
                TextView valid_from_value = (TextView) view.findViewById(R.id.valid_from_value);
                TextView valid_till_value = (TextView) view.findViewById(R.id.valid_till_value);
                TextView coverage_value = (TextView) view.findViewById(R.id.coverage_value);
                TextView claim_value = (TextView) view.findViewById(R.id.claim_value);
                final ImageView show_hide_image = (ImageView) view.findViewById(R.id.show_hide);
                RelativeLayout heading = (RelativeLayout) view.findViewById(R.id.heading);
                final RelativeLayout content = (RelativeLayout) view.findViewById(R.id.content);

                heading.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (content.getVisibility() == View.GONE) {
                            content.setVisibility(View.VISIBLE);
                            JeevomUtilsClass.autoScroll(scrollView_insurance_detail);
                            JeevomUtilsClass.rotate6to12ClockWise(show_hide_image);
                        } else {
                            content.setVisibility(View.GONE);
                            JeevomUtilsClass.rotate12to6ClockWise(show_hide_image);
                        }
                    }

                });

                // set heading
                StringBuilder head_value = new StringBuilder();
                if (!CommonCode.isNullOrEmpty(object.getProviderName())) {
                    head_value.append(object.getProviderName());
                }
                if (!CommonCode.isNullOrEmpty(object.getPolicyName())) {
                    head_value.append(", " + object.getPolicyName());
                }
                if (!CommonCode.isNullOrEmpty(object.getPolicyNumber())) {
                    head_value.append(", " + object.getPolicyNumber());
                }

                if (!CommonCode.isNullOrEmpty(head_value.toString())) {
                    heading_value.setText(head_value.toString());
                } else {
                    heading_value.setText("N/A");
                }

                // set website
                if (!CommonCode.isNullOrEmpty(object.getProviderUrl())) {
                    website_value.setText(object.getProviderUrl());
                    isNothingVisible = false;
                } else {
                    LinearLayout website_layout = (LinearLayout) view.findViewById(R.id.website_layout);
                    website_layout.setVisibility(View.GONE);
                }

                // set valid from date
                if (!CommonCode.isNullOrEmpty(object.getValidFrom())) {

                    int[] formatDT = null;
                    try {
                        formatDT = CommonCode.formatDT(object.getValidFrom());
                    } catch (java.text.ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    valid_from_value.setText(formatDT[0] + "/" + (formatDT[1] + 1) + "/" + formatDT[2]);
                    isNothingVisible = false;
                } else {
                    LinearLayout valid_from_layout = (LinearLayout) view.findViewById(R.id.valid_from_layout);
                    valid_from_layout.setVisibility(View.GONE);
                }

                // set valid through date
                if (!CommonCode.isNullOrEmpty(object.getValidTill())) {

                    int[] formatDT = null;
                    try {
                        formatDT = CommonCode.formatDT(object.getValidTill());
                    } catch (java.text.ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    valid_till_value.setText(formatDT[0] + "/" + (formatDT[1] + 1) + "/" + formatDT[2]);
                    isNothingVisible = false;
                } else {
                    LinearLayout valid_till_layout = (LinearLayout) view.findViewById(R.id.valid_till_layout);
                    valid_till_layout.setVisibility(View.GONE);
                }

                // set coverage amount
                if (!CommonCode.isNullOrEmpty(object.getCoverageAmount())) {
                    coverage_value.setText(object.getCoverageAmount());
                    isNothingVisible = false;
                } else {
                    LinearLayout coverage_layout = (LinearLayout) view.findViewById(R.id.coverage_layout);
                    coverage_layout.setVisibility(View.GONE);
                }

                // set claimed Amount
                if (!CommonCode.isNullOrEmpty(object.getClaimedAmount())) {
                    claim_value.setText(object.getClaimedAmount());
                    isNothingVisible = false;
                } else {
                    LinearLayout claim_layout = (LinearLayout) view.findViewById(R.id.claim_layout);
                    claim_layout.setVisibility(View.GONE);
                }
                insurance_details.addView(view);
            }
        }

    }

}
