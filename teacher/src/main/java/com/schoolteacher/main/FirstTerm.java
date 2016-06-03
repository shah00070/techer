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
public class FirstTerm extends Fragment {

    View rootView;
    LinearLayout classVII, classVIII;
    Spinner spinner11;

    String[] text1 = {"classVII", "classVIII"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.firstterm, container,
                false);


        setUiOnScreen();
        return rootView;
    }

    private void setUiOnScreen() {


        classVII = (LinearLayout) rootView.findViewById(R.id.classVII);
        classVIII = (LinearLayout) rootView.findViewById(R.id.classVIII);
        spinner11 = (Spinner) rootView.findViewById(R.id.spinner11);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.populate_searc));
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner11.setAdapter(adapter);
        spinner11.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spinner11.getSelectedItemPosition();


                        if (position == 0) {
                            classVII.setVisibility(View.VISIBLE);
                            classVIII.setVisibility(View.GONE);


                        } else if (position == 1) {
                            classVII.setVisibility(View.GONE);
                            classVIII.setVisibility(View.VISIBLE);

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {


                    }

                }
        );
    }



}

