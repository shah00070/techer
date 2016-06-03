package com.schoolteacher.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

/**
 * Created by shah on 24/12/15.
 */
public class ProductOverviewFragment extends Fragment {
public Spinner spinner;
    public View rootView;
    public LinearLayout day,month,week;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.product_overview_fragment, container, false);

call();
        return rootView;
    }

    public void call(){
        day=(LinearLayout)rootView.findViewById(R.id.day);
        month=(LinearLayout)rootView.findViewById(R.id.month);
        week=(LinearLayout)rootView.findViewById(R.id.week);

        spinner = (Spinner)rootView.findViewById(R.id.SHEDULE_PTM);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.day_week_month));
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
                            day.setVisibility(View.VISIBLE);
                            month.setVisibility(View.GONE);
                            week.setVisibility(View.GONE);

                        } else if (position == 1) {
                            day.setVisibility(View.GONE);
                            week.setVisibility(View.VISIBLE);
                            month.setVisibility(View.GONE);

                        }else if (position == 2) {
                            day.setVisibility(View.GONE);
                            month.setVisibility(View.VISIBLE);
                            week.setVisibility(View.GONE);

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {


                    }

                }
        );
    }
    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().trackScreenView("Product overview Fragment");
    }

}

