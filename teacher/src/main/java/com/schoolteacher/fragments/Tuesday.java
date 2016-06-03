package com.schoolteacher.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.schoolteacher.R;


public class Tuesday extends Fragment {

    private RecyclerView rv;
    public View rootView;
    public Tuesday() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.tuesday, container, false);




        return rootView;
    }

    @Override
    public void onResume() {

        super.onResume();
    }



}
