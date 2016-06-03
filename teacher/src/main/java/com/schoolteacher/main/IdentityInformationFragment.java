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

public class IdentityInformationFragment extends Fragment {
    View rootView;
    TextView address_value, dob_value, blood_value, gender_value;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getActivity()));
        rootView = inflater.inflate(R.layout.identity_fragment_layout,
                container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Identity Information Fragment");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        address_value = (TextView) rootView.findViewById(R.id.address_value);
        dob_value = (TextView) rootView.findViewById(R.id.dob_value);
        blood_value = (TextView) rootView.findViewById(R.id.blood_value);
        gender_value = (TextView) rootView.findViewById(R.id.gender_value);

        // set address
        StringBuilder addressDetails = new StringBuilder();
        if (ViewProfile.consumer.getData().getMemberContactInformation() != null) {

            if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData()
                    .getMemberContactInformation().getAddressLine1())) {
                addressDetails.append(ViewProfile.consumer.getData()
                        .getMemberContactInformation().getAddressLine1());
            }

            if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData()
                    .getMemberContactInformation().getArea())) {
                addressDetails.append(","
                        + ViewProfile.consumer.getData()
                        .getMemberContactInformation().getArea());
            }

            if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData()
                    .getMemberContactInformation().getCity())) {
                addressDetails.append(","
                        + ViewProfile.consumer.getData()
                        .getMemberContactInformation().getCity());
            }
            if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData()
                    .getMemberContactInformation().getState())) {
                addressDetails.append(","
                        + ViewProfile.consumer.getData()
                        .getMemberContactInformation().getState());
            }
            if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData()
                    .getMemberContactInformation().getZipCode())) {
                addressDetails.append(","
                        + ViewProfile.consumer.getData()
                        .getMemberContactInformation().getZipCode());
            }
            if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData()
                    .getMemberContactInformation().getCountry())) {
                addressDetails.append(","
                        + ViewProfile.consumer.getData()
                        .getMemberContactInformation().getCountry());
            }

            if (!CommonCode.isNullOrEmpty(addressDetails.toString())) {
                address_value.setText(addressDetails.toString());
            } else {
                address_value.setText("");
            }
        }

        // set gender
        if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData()
                .getGender())) {
            gender_value.setText(ViewProfile.consumer.getData().getGender());
        }

        // set blood group
        blood_value.setText(ViewProfile.bloodGroup);

        // set date of birth
        if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData()
                .getDateOfBirth())) {

            int[] formatDT = null;
            try {
                formatDT = CommonCode.formatDT(ViewProfile.consumer.getData()
                        .getDateOfBirth());
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            dob_value.setText(CommonCode.monthYear(formatDT[0], formatDT[1],
                    formatDT[2]));

        } else {
            dob_value.setText("");
        }

    }
}
