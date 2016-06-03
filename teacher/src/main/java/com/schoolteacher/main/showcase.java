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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

/**
 * Created by chandan on 11/5/16.
 */
public class showcase extends Fragment {
public CardView one,two,three,four;
    private View rootView;
    String[] celebrities = {
            "Chris Hemsworth",
            "Jennifer Lawrence",
            "Jessica Alba",
            "Brad Pitt",
            "Tom Cruise",
            "Johnny Depp",
            "Megan Fox",
            "Paul Walker",
            "Vin Diesel"
    };


    Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.showcase, container,
                false);
        two=(CardView)rootView.findViewById(R.id.two);
        one=(CardView)rootView.findViewById(R.id.one);

        three=(CardView)rootView.findViewById(R.id.three);
        four=(CardView)rootView.findViewById(R.id.four);
        spinner = (Spinner)rootView.findViewById(R.id.spinner11);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.event_call));
       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spinner.getSelectedItemPosition();
                      //  Toast.makeText(getActivity(), "You have selected " + getActivity().getResources().getStringArray(R.array.event_call)[+position], Toast.LENGTH_LONG).show();
                        ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);


                        if(position==0){
                         //   Toast.makeText(getActivity(), "000", Toast.LENGTH_SHORT).show();
                            two.setVisibility(View.GONE);
                            three.setVisibility(View.GONE);
                            four.setVisibility(View.GONE);
                            one.setVisibility(View.VISIBLE);


                        }else if(position==1){

                            one.setVisibility(View.VISIBLE);
                            three.setVisibility(View.GONE);
                            four.setVisibility(View.GONE);
                            two.setVisibility(View.VISIBLE);
                        }else if(position==2){

                            one.setVisibility(View.VISIBLE);
                            three.setVisibility(View.VISIBLE);
                            four.setVisibility(View.GONE);
                            two.setVisibility(View.VISIBLE);
                        }else if(position==3){

                            one.setVisibility(View.VISIBLE);
                            three.setVisibility(View.GONE);
                            four.setVisibility(View.VISIBLE);
                            two.setVisibility(View.VISIBLE);
                        }else if(position==4){

                            one.setVisibility(View.VISIBLE);
                            three.setVisibility(View.VISIBLE);
                            four.setVisibility(View.VISIBLE);
                            two.setVisibility(View.VISIBLE);
                        }




                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {


                    }

                }
        );
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Popular search fragment");
    }
}
