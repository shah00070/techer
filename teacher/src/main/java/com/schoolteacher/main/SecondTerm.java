package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.schoolteacher.R;

/**
 * Created by perveen akhtar on 5/10/2016.
 */
public class SecondTerm extends Fragment {


    View rootView;
    LinearLayout classseven, classeight;
    Spinner spinner12;

    String[] text1 = {"classVII", "classVIII"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.secondterm, container,
                false);


        setUiOnScreen();
        return rootView;
    }

    private void setUiOnScreen() {


        classseven = (LinearLayout) rootView.findViewById(R.id.classseven);
        classeight = (LinearLayout) rootView.findViewById(R.id.classeight);
        spinner12 = (Spinner) rootView.findViewById(R.id.spinner12);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.populate_searc));
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner12.setAdapter(adapter);
        spinner12.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spinner12.getSelectedItemPosition();


                        if (position == 0) {
                            classseven.setVisibility(View.VISIBLE);
                            classeight.setVisibility(View.GONE);


                        } else if (position == 1) {
                            classseven.setVisibility(View.GONE);
                            classeight.setVisibility(View.VISIBLE);

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {


                    }

                }
        );
    }



}







