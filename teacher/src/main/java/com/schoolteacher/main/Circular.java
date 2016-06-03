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
import com.schoolteacher.adapters.PopularSearchAdapter;

/**
 * Created by chandan on 23/5/16.
 */

public class Circular extends Fragment {
    public CardView one,two,three,four;
    private View rootView;
public String[] update;
    public String[] uptime;
    private String[] itemList;
    private String[] date;

    Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.circulars, container,
                false);
        setUiOnScreen();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Popular search fragment");
    }

    public void setUiOnScreen() {
        ListView listView = (ListView) rootView.findViewById(R.id.circular_listview);
        itemList = getActivity().getResources().getStringArray(R.array.titles);
        date = getActivity().getResources().getStringArray(R.array.descriptions);
        update = getActivity().getResources().getStringArray(R.array.update);
        uptime = getActivity().getResources().getStringArray(R.array.uptime);

        Circular_adapter adapter = new Circular_adapter(getActivity(), itemList,date,update,uptime);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {


                } else if (position == 1) {

                } else if (position == 2) {

                } else if (position == 3) {

                } else if (position == 4) {

                } else if (position == 5) {

                } else if (position == 6) {

                } else if (position == 7) {

                } else if (position == 8) {

                } else if (position == 9) {

                } else if (position == 10) {

                } else if (position == 11) {

                } else if (position == 12) {

                } else if (position == 13) {

                } else if (position == 14) {

                }


            }
        });
    }
}
