package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
 * Created by chandan on 25/5/16.
 */




    public class Social_Media extends Fragment {
        ListView list;


        private View rootView;
        String[] web = null;
        Integer[] imageId = null;
        public SocialMedia_adapter1 adapter1;
        public SocialMedia_aug adapter;
        String[] emailids =null;
    String[] field =null;
        public TextView descriptionn;
        Spinner spinner;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.class_social_media, container,
                    false);

            data();


            return rootView;
        }

        public void data(){


            spinner = (Spinner)rootView.findViewById(R.id.socialmedia_spin);

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


                                        field = new String[]{
                                        "Presidium School Facebook Page:",
                                        "Presidium School Facebook Page:",
                                        "Presidium School Facebook Page:",

                                        "Presidium School Twitter Page:",
                                        "Presidium School Twitter Page:",
                                        "Presidium School Twitter Page:",

                                };

///
                                web = new String[]{
                                        "2000",
                                        "5000",
                                        "3000",
                                        "600",
                                        "1000",
                                        "1500",

                                };

                                emailids = new String[]{
                                        "Number of Likes:",
                                        "Number of Page Impressions:",
                                        "Number of Page views:",


                                        "Number of Followers:",
                                        "Number of Retweets:",
                                        "Number of Page Impressions:",
                                };

                                call_without_image();


                            } else if (position == 1) {
                                field = new String[]{
                                        "Presidium School Facebook Page:",
                                        "Presidium School Facebook Page:",
                                        "Presidium School Facebook Page:",

                                        "Presidium School Twitter Page:",
                                        "Presidium School Twitter Page:",
                                        "Presidium School Twitter Page:",

                                };

                                web = new String[]{
                                        "2500",
                                        "5500",
                                        "3800",
                                        "640",
                                        "1050",
                                        "1800",

                                };

                                emailids = new String[]{
                                        "Number of Likes:",
                                        "Number of Page Impressions:",
                                        "Number of Page views:",


                                        "Number of Followers:",
                                        "Number of Retweets:",
                                        "Number of Page Impressions:",
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
            adapter = new SocialMedia_aug(getActivity(), web,emailids,field);
            list = (ListView) rootView.findViewById(R.id.socialmedia_list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                }
            });
        }


        public void call_without_image(){
            adapter1 = new SocialMedia_adapter1(getActivity(), web,emailids,field);
            list = (ListView) rootView.findViewById(R.id.socialmedia_list);
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





