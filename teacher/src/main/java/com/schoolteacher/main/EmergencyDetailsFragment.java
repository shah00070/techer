package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.library.main.ExceptionHandler;
import com.schoolteacher.mylibrary.util.CommonCode;
import com.schoolteacher.pojos.MemberEmergencyDetail;

import java.util.List;

public class EmergencyDetailsFragment extends Fragment {
    View rootView;
    TextView name_value, phone_value, email_value;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getActivity()));
        rootView = inflater.inflate(R.layout.emergency_details_fragment,
                container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Emergency Details Fragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name_value = (TextView) rootView.findViewById(R.id.name_value);
        phone_value = (TextView) rootView.findViewById(R.id.phone_value);
        email_value = (TextView) rootView.findViewById(R.id.email_value);

        // set address
        // StringBuilder name = new StringBuilder();

        List<MemberEmergencyDetail> memberEmergencyDetails = ViewProfile.consumer
                .getData().getMemberEmergencyDetails();

        if (memberEmergencyDetails != null) {
            MemberEmergencyDetail memberEmergencyDetail = memberEmergencyDetails
                    .get(0);

            StringBuilder nameVal = new StringBuilder();

            if (!CommonCode.isNullOrEmpty(memberEmergencyDetail.getTitle())) {
                nameVal.append(memberEmergencyDetail.getTitle() + " ");
            }
            if (!CommonCode.isNullOrEmpty(memberEmergencyDetail.getFirstName())) {
                nameVal.append(memberEmergencyDetail.getFirstName() + " ");
            }
            if (!CommonCode.isNullOrEmpty(memberEmergencyDetail.getLastName())) {
                nameVal.append(memberEmergencyDetail.getLastName());
            }

            if (!CommonCode.isNullOrEmpty(nameVal.toString())) {
                name_value.setText(nameVal.toString().trim());
            }
            if (!CommonCode.isNullOrEmpty(memberEmergencyDetail.getEmail())) {
                email_value.setText(memberEmergencyDetail.getEmail());
            }
            if (!CommonCode
                    .isNullOrEmpty(memberEmergencyDetail.getCellNumber())) {
                phone_value.setText(memberEmergencyDetail.getCellNumber());
            }

        }

    }
}
