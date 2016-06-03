package com.schoolteacher.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

/**
 * Created by chandan on 25/5/16.
 */


    public class Student_Fee extends Fragment {
        ListView list;


        private View rootView;
        String[] web = null;
        Integer[] imageId = null;
        public analytic_adapter1 adapter1;
        public analylits_aug adapter;
        String[] emailids =null;
        public TextView descriptionn;
        Spinner spinner;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.student_fee, container,
                    false);

            data();


            return rootView;
        }

        public void data(){


            spinner = (Spinner)rootView.findViewById(R.id.school_analytic_spin);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.school_analytic_search));
            // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                   int arg2, long arg3) {
                            int position = spinner.getSelectedItemPosition();
                      //      ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);


                            if (position == 0) {

///
                                web = new String[]{
                                       "450",
                                        "100",
                                        "50",

                                };

                                emailids = new String[]{
                                        "School Fee paid by:",
                                        "Late payee:",
                                        "Defaulters:",
                                };

                                call_without_image();


                            } else if (position == 1) {


                                web = new String[]{
                                        "640",
                                        "100",
                                        "10",

                                };

                                emailids = new String[]{
                                        "School Fee paid by:",
                                        "Late payee:",
                                        "Defaulters:",
                                };

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
            adapter = new analylits_aug(getActivity(), web,emailids);
            list = (ListView) rootView.findViewById(R.id.school_analytic_list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                }
            });
        }


        public void call_without_image(){
            adapter1 = new analytic_adapter1(getActivity(), web,emailids);
            list = (ListView) rootView.findViewById(R.id.school_analytic_list);
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
