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

import java.util.ArrayList;
import java.util.List;

public class MedicalProfileFragment extends Fragment {
    View rootView;
    TextView weight_value, height_value, bmi_value, bmr_value, medical_value, allergies_value, marks_value;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getActivity()));
        rootView = inflater.inflate(R.layout.medical_fragment_layout, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        weight_value = (TextView) rootView.findViewById(R.id.weight_value);
        height_value = (TextView) rootView.findViewById(R.id.height_value);
        bmi_value = (TextView) rootView.findViewById(R.id.bmi_value);
        bmr_value = (TextView) rootView.findViewById(R.id.bmr_value);
        medical_value = (TextView) rootView.findViewById(R.id.medical_value);
        allergies_value = (TextView) rootView.findViewById(R.id.allergies_value);
        marks_value = (TextView) rootView.findViewById(R.id.marks_value);

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MyApplication.getInstance().trackScreenView("Medical Profile Fragment");


        // set bmi and bmr
        if (String.valueOf((int) Math.floor(ViewProfile.getbMI)).equals("0")) {
            bmi_value.setText("N/A");
        } else {
            bmi_value.setText(String.valueOf((int) Math.floor(ViewProfile.getbMI)));
        }

        if (String.valueOf((int) Math.floor(ViewProfile.dailyCalorieRequirement)).equals("0")) {
            bmr_value.setText("N/A");
        } else {
            bmr_value.setText(String.valueOf((int) Math.floor(ViewProfile.dailyCalorieRequirement)));
        }
        // set weight
        if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData().getWeight())) {
            if (ViewProfile.consumer.getData().getWeight().equals("0")) {
                weight_value.setText("N/A");
            } else {

                weight_value.setText(ViewProfile.consumer.getData().getWeight());
            }
        } else {
            weight_value.setText("N/A");
        }
        // set height
        if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData().getHeight())) {
            if (ViewProfile.consumer.getData().getHeight().equals("0")) {
                height_value.setText("N/A");
            } else {
                height_value.setText(cmsToFeet(ViewProfile.consumer.getData().getHeight()) + " Feet " + cmsToInch(ViewProfile.consumer.getData().getHeight()) + " Inches");
            }
        } else {
            height_value.setText("N/A");
        }

        // set specific medical conditions
        if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData().getMedicalConditions())) {

            List<String> splitStringByPipe = CommonCode.splitStringByPipe(ViewProfile.consumer.getData().getMedicalConditions());
            List<String> list = new ArrayList<>();
            if (splitStringByPipe.size() > 0) {
                for (String split : splitStringByPipe) {
                    if (!(list.contains(split.trim()))) {
                        list.add(split.trim());
                    }
                }
            }

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    builder.append(list.get(i));
                } else {
                    builder.append("," + list.get(i));
                }

            }
            medical_value.setText(builder.toString());
            // medical_value.setText(ViewProfile.consumer.getData().getMedicalConditions().replace("|",
            // ","));
        } else {
            medical_value.setText("N/A");
        }

        // set allergies values
        if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData().getAllergies())) {
            List<String> splitStringByPipe = CommonCode.splitStringByPipe(ViewProfile.consumer.getData().getAllergies());
            List<String> list = new ArrayList<>();
            if (splitStringByPipe.size() > 0) {
                for (String split : splitStringByPipe) {
                    if (!(list.contains(split.trim()))) {
                        list.add(split.trim());
                    }
                }
            }

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    builder.append(list.get(i));
                } else {
                    builder.append("," + list.get(i));
                }

            }
            allergies_value.setText(builder.toString());
            // allergies_value.setText(ViewProfile.consumer.getData().getAllergies().replace("|",
            // ","));
        } else {
            allergies_value.setText("N/A");
        }

        // set identity marks
        if (!CommonCode.isNullOrEmpty(ViewProfile.consumer.getData().getIdentityMarks())) {
            marks_value.setText(ViewProfile.consumer.getData().getIdentityMarks());
        } else {
            marks_value.setText("N/A");
        }
    }

    private String cmsToFeet(String height) {
        Double heightInCms = (Double.valueOf(height)) / 2.54;
        String value = String.valueOf(heightInCms.intValue() / 12);
        return value;

    }

    private String cmsToInch(String height) {
        Double heightInCms = (double) Math.round(((Double.valueOf(height)) / 2.54));
        String value = String.valueOf(heightInCms.intValue() % 12);
        return value;

    }
}
