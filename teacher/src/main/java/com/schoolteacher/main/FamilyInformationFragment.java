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
import com.schoolteacher.pojos.BloodGroup;
import com.schoolteacher.pojos.MemberAssociation;
import com.schoolteacher.util.JeevomUtilsClass;

import java.text.ParseException;
import java.util.List;

public class FamilyInformationFragment extends Fragment {
    View rootView;
    LinearLayout family_details;
    List<MemberAssociation> memberAssociation;
    private ScrollView scrollView_doctor_detail;

    // String bloodGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getActivity()));
        rootView = inflater.inflate(R.layout.family_fragment_layout, container, false);
        memberAssociation = ViewProfile.familyDetails.getData().getMemberAssociation();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Family Information Details Fragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        family_details = (LinearLayout) rootView.findViewById(R.id.family_details);
        scrollView_doctor_detail = (ScrollView) rootView.findViewById(R.id.sv_family_detail);
        // fill family members
        if (family_details.getChildCount() > 0) {
            family_details.removeAllViews();
        }
        if (memberAssociation.size() > 0) {

            for (MemberAssociation object : memberAssociation) {
                LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View view = inflator.inflate(R.layout.insurance_details_row, null);
                if (family_details.getChildCount() % 2 == 0) {
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

                TextView website_tag = (TextView) view.findViewById(R.id.website_tag);
                TextView valid_from_tag = (TextView) view.findViewById(R.id.valid_from_tag);
                TextView valid_till_tag = (TextView) view.findViewById(R.id.valid_till_tag);
                TextView coverage_tag = (TextView) view.findViewById(R.id.coverage_tag);
                TextView claim_tag = (TextView) view.findViewById(R.id.claim_tag);

                website_tag.setText("Gender");
                valid_from_tag.setText("Blood Group");
                valid_till_tag.setText("Date Of Birth");
                coverage_tag.setText("Email");
                claim_tag.setText("Cell No.");

                final ImageView show_hide_image = (ImageView) view.findViewById(R.id.show_hide);
                RelativeLayout heading = (RelativeLayout) view.findViewById(R.id.heading);
                final RelativeLayout content = (RelativeLayout) view.findViewById(R.id.content);

                heading.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (content.getVisibility() == View.GONE) {
                            content.setVisibility(View.VISIBLE);
                            JeevomUtilsClass.autoScroll(scrollView_doctor_detail);
                            JeevomUtilsClass.rotate6to12ClockWise(show_hide_image);
                        } else {
                            content.setVisibility(View.GONE);
                            JeevomUtilsClass.rotate12to6ClockWise(show_hide_image);
                        }

                    }
                });

                // set heading
                StringBuilder head_value = new StringBuilder();
                if (!CommonCode.isNullOrEmpty(object.getFirstName())) {
                    head_value.append(object.getFirstName());
                }
                if (!CommonCode.isNullOrEmpty(object.getLastName())) {
                    head_value.append(" " + object.getLastName());
                }
                if (!CommonCode.isNullOrEmpty(object.getAssociateNameString())) {
                    head_value.append(", " + object.getAssociateNameString());
                }

                if (!CommonCode.isNullOrEmpty(head_value.toString())) {
                    heading_value.setText(head_value.toString());
                } else {
                    heading_value.setText("N/A");
                }

                // set gender
                if (!CommonCode.isNullOrEmpty(object.getGender())) {
                    website_value.setText(object.getGender());
                } else {
                    LinearLayout website_layout = (LinearLayout) view.findViewById(R.id.website_layout);
                    website_layout.setVisibility(View.GONE);
                }

                // set date of birth
                int[] formatDT;
                if (!CommonCode.isNullOrEmpty(object.getDob())) {
                    try {
                        formatDT = CommonCode.formatDT(object.getDob());
                        int current_year = formatDT[2];
                        int currentDay = formatDT[0];
                        int currentMonth = formatDT[1] + 1;
                        valid_till_value.setText(CommonCode.monthYear(currentDay, currentMonth - 1, current_year));

                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    LinearLayout valid_till_layout = (LinearLayout) view.findViewById(R.id.valid_till_layout);
                    valid_till_layout.setVisibility(View.GONE);
                }
                if (!CommonCode.isNullOrEmpty(object.getCellNumber())) {
                    claim_value.setText(object.getCellNumber());
                } else {
                    LinearLayout claim_layout = (LinearLayout) view.findViewById(R.id.claim_layout);
                    claim_layout.setVisibility(View.GONE);
                }

                if (!CommonCode.isNullOrEmpty(object.getEmail())) {
                    coverage_value.setText(object.getEmail());
                } else {
                    LinearLayout coverage_layout = (LinearLayout) view.findViewById(R.id.coverage_layout);
                    coverage_layout.setVisibility(View.GONE);
                }

                if (object.getBloodGroupType() > 0) {
                    String bloodName = null;
                    for (BloodGroup bloodGroup : ViewProfile.bloodGroups) {
                        if (bloodGroup.getId() == object.getBloodGroupType()) {
                            bloodName = bloodGroup.getName();

                        }
                    }
                    valid_from_value.setText(bloodName);
                } else {
                    LinearLayout valid_from_layout = (LinearLayout) view.findViewById(R.id.valid_from_layout);
                    valid_from_layout.setVisibility(View.GONE);

                }
                family_details.addView(view);

            }

        }

    }
}
