package com.schoolteacher.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

/**
 * Created by chandan on 12/5/16.
 */


public class class_homework extends Fragment {
    public View rootView;
    int star17=0,star27=0,star37=0,star47=0;
    int book17=0,book27=0,book37=0,book47=0;
    TextView star71,star72,star73,star74;
    TextView bookmar71,bookmar72,bookmar73,bookmar74;


    int star18=0,star28=0,star38=0,star48=0;
    int book18=0,book28=0,book38=0,book48=0;
    TextView star81,star82,star83,star84;
    TextView bookmar81,bookmar82,bookmar83,bookmar84;
    
public LinearLayout class7,class8;
    Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.studentsocial_media, container,
                false);
        setUiOnScreen();
        return rootView;
    }

    private void setUiOnScreen() {
        class7=(LinearLayout)rootView.findViewById(R.id.class7);
        class8=(LinearLayout)rootView.findViewById(R.id.class8);





        star71=(TextView)rootView.findViewById(R.id.star71);
        star72=(TextView)rootView.findViewById(R.id.star72);
        star73=(TextView)rootView.findViewById(R.id.star73);
        star74=(TextView)rootView.findViewById(R.id.star74);
        star71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star17==0){
                    star71.setBackgroundResource(R.drawable.unstar);
                    star17=1;
                }else if(star17==1){
                    star71.setBackgroundResource(R.drawable.stasr);
                    star17=0;
                }
            }
        });
        star72.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star27==0){
                    star72.setBackgroundResource(R.drawable.unstar);
                    star27=1;
                }else if(star27==1){
                    star72.setBackgroundResource(R.drawable.stasr);
                    star27=0;
                }
            }
        });
        star73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star37==0){
                    star73.setBackgroundResource(R.drawable.unstar);
                    star37=3;
                }else if(star37==3){
                    star73.setBackgroundResource(R.drawable.stasr);
                    star37=0;
                }
            }
        });
        star74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star47==0){
                    star74.setBackgroundResource(R.drawable.unstar);
                    star47=4;
                }else if(star47==4){
                    star74.setBackgroundResource(R.drawable.stasr);
                    star47=0;
                }
            }
        });
        bookmar71=(TextView)rootView.findViewById(R.id.bookmar71);
        bookmar72=(TextView)rootView.findViewById(R.id.bookmar72);
        bookmar73=(TextView)rootView.findViewById(R.id.bookmar73);
        bookmar74=(TextView)rootView.findViewById(R.id.bookmar74);
        bookmar71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book17==0){
                    bookmar71.setBackgroundResource(R.drawable.unbook);
                    book17=1;
                }else if(book17==1){
                    bookmar71.setBackgroundResource(R.drawable.bbookmark);
                    book17=0;
                }
            }
        });
        bookmar72.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book27==0){
                    bookmar72.setBackgroundResource(R.drawable.unbook);
                    book27=2;
                }else if(book27==2){
                    bookmar72.setBackgroundResource(R.drawable.bbookmark);
                    book27=0;
                }
            }
        });
        bookmar73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book37==0){
                    bookmar73.setBackgroundResource(R.drawable.unbook);
                    book37=3;
                }else if(book37==3){
                    bookmar73.setBackgroundResource(R.drawable.bbookmark);
                    book37=0;
                }
            }
        });
        bookmar74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book47==0){
                    bookmar74.setBackgroundResource(R.drawable.unbook);
                    book47=4;
                }else if(book47==4){
                    bookmar74.setBackgroundResource(R.drawable.bbookmark);
                    book47=0;
                }
            }
        });








        star81=(TextView)rootView.findViewById(R.id.star81);
        star82=(TextView)rootView.findViewById(R.id.star82);
        star83=(TextView)rootView.findViewById(R.id.star83);
        star84=(TextView)rootView.findViewById(R.id.star84);
        star81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star18==0){
                    star81.setBackgroundResource(R.drawable.unstar);
                    star18=1;
                }else if(star18==1){
                    star81.setBackgroundResource(R.drawable.stasr);
                    star18=0;
                }
            }
        });
        star82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star28==0){
                    star82.setBackgroundResource(R.drawable.unstar);
                    star28=1;
                }else if(star28==1){
                    star82.setBackgroundResource(R.drawable.stasr);
                    star28=0;
                }
            }
        });
        star83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star38==0){
                    star83.setBackgroundResource(R.drawable.unstar);
                    star38=3;
                }else if(star38==3){
                    star83.setBackgroundResource(R.drawable.stasr);
                    star38=0;
                }
            }
        });
        star84.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star48==0){
                    star84.setBackgroundResource(R.drawable.unstar);
                    star48=4;
                }else if(star48==4){
                    star84.setBackgroundResource(R.drawable.stasr);
                    star48=0;
                }
            }
        });
        bookmar81=(TextView)rootView.findViewById(R.id.bookmar81);
        bookmar82=(TextView)rootView.findViewById(R.id.bookmar82);
        bookmar83=(TextView)rootView.findViewById(R.id.bookmar83);
        bookmar84=(TextView)rootView.findViewById(R.id.bookmar84);
        bookmar81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book18==0){
                    bookmar81.setBackgroundResource(R.drawable.unbook);
                    book18=1;
                }else if(book18==1){
                    bookmar81.setBackgroundResource(R.drawable.bbookmark);
                    book18=0;
                }
            }
        });
        bookmar82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book28==0){
                    bookmar82.setBackgroundResource(R.drawable.unbook);
                    book28=2;
                }else if(book28==2){
                    bookmar82.setBackgroundResource(R.drawable.bbookmark);
                    book28=0;
                }
            }
        });
        bookmar83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book38==0){
                    bookmar83.setBackgroundResource(R.drawable.unbook);
                    book38=3;
                }else if(book38==3){
                    bookmar83.setBackgroundResource(R.drawable.bbookmark);
                    book38=0;
                }
            }
        });
        bookmar84.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book48==0){
                    bookmar84.setBackgroundResource(R.drawable.unbook);
                    book48=4;
                }else if(book48==4){
                    bookmar84.setBackgroundResource(R.drawable.bbookmark);
                    book48=0;
                }
            }
        });






        spinner = (Spinner)rootView.findViewById(R.id.spin_homework);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.populate_searc));
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
                            class7.setVisibility(View.VISIBLE);
                            class8.setVisibility(View.GONE);

                        } else if (position == 1) {
                            class7.setVisibility(View.GONE);
                            class8.setVisibility(View.VISIBLE);

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
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Mart Fragment");
    }
}
