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
 * Created by chandan on 24/5/16.
 */

public class Head_count extends Fragment {
    ListView list;


    private View rootView;
    String[] web = null;
    Integer[] imageId = null;
    public head_list_no_image adapter1;
    public head_list adapter;
    String[] emailids =null;
    String[] class_name =null;
    public TextView descriptionn;
    Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.head_count, container,
                false);

        data();


        return rootView;
    }

    public void data(){

         descriptionn = (TextView) rootView.findViewById(R.id.main_date);
        spinner = (Spinner)rootView.findViewById(R.id.head_spin);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.head_search));
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

///
                            web = new String[]{
                                    "I",
                                    "II",
                                    "III",
                                    "IV",
                                    "V",
                                    "VI",
                                    "VII",
                                    "VIII",
                                    "IX",
                                    "X",
                                    "XI",
                                    "XII",

                            };

                            emailids = new String[]{
                                    "20/50",
                                    "30/50",
                                    "20/50",
                                    "20/50",
                                    "10/50",
                                    "40/50",
                                    "50/50",
                                    "40/50",
                                    "50/50",
                                    "20/50",
                                    "30/50",
                                    "40/50",

                            };

                            descriptionn.setText("300/500");
                            call_without_image();


                        } else if (position == 1) {


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



                            descriptionn.setText("400/500");

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
        adapter = new head_list(getActivity(), web, imageId);
        list = (ListView) rootView.findViewById(R.id.head_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            }
        });
    }


    public void call_without_image(){
        adapter1 = new head_list_no_image(getActivity(), web, emailids);
        list = (ListView) rootView.findViewById(R.id.head_list);
        list.setAdapter(adapter1);
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
