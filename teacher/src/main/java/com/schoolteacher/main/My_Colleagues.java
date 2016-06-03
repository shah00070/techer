package com.schoolteacher.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

/**
 * Created by chandan on 23/5/16.
 */

public class My_Colleagues extends Fragment {
    ListView list;


    private View rootView;
    String[] web = null;
    Integer[] imageId = null;
    public colleague_list adapter;
    String[] emailids =null;
    String[] class_name =null;
    Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.my_colleagues, container,
                false);

        data();


        return rootView;
    }

    public void data(){
        spinner = (Spinner)rootView.findViewById(R.id.collieagues_spin);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.colleague_search));
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);

                        int position = spinner.getSelectedItemPosition();
                        if (position == 0) {


                            class_name = new String[]{
                                    " A-101",
                                    "P-01",
                                    "P-02",
                                    "E-01",
                                    "E-02",
                                    "E-03",


                            };
                            web = new String[]{
                                    "Shivam Raj",
                                    "Ajay Sharma",
                                    "Vanita Malik",
                                    "Rashmi Lal",
                                    "Renuka Jha",
                                    "Sunita Dhakad",

                            };
                            imageId = new Integer[]{
                                    R.drawable.tt1,
                                    R.drawable.tt2,
                                    R.drawable.tt3,
                                    R.drawable.tt4,
                                    R.drawable.tt5,
                                    R.drawable.tt6,


                            };

                            emailids = new String[]{
                                    "IT Dept.",
                                    "Principal",
                                    "Vice Principal",
                                    "Examination Cell",
                                    "School Clinic",
                                    "Librarian",

                            };
                            call();


                        } else if (position == 1) {

                            class_name = new String[]{
                                    "LT-101",
                                    "LT-102",
                                    "LT-103",
                                    "LT-104",
                                    "LT-105",
                                    "LT-106",


                            };
                            web = new String[]{
                                    "Ekant Jain",
                                    "Parul Sharma",
                                    "Sangita Bhatnagar",
                                    "Ankit Singh",
                                    "Rahul Gupta",
                                    "Akansha Sharma",

                            };
                            imageId = new Integer[]{
                                    R.drawable.onet,
                                    R.drawable.two1,
                                    R.drawable.threet,
                                    R.drawable.fourt,
                                    R.drawable.fivet,
                                    R.drawable.sixt,


                            };

                            emailids = new String[]{
                                    "Staffroom 1",
                                    "Staffroom 2",
                                    "Staffroom 3",
                                    "Staffroom 1",
                                    "Staffroom 2",
                                    "Staffroom 3",

                            };

//
                            call();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {


                    }

                }
        );



    }
    public void call(){
        adapter = new colleague_list(getActivity(), web, imageId, emailids,class_name);
        list = (ListView) rootView.findViewById(R.id.colleagues_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Popular search fragment");
    }

}
