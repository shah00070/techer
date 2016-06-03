package com.schoolteacher.main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;import android.support.v7.widget.LinearLayoutManager;import android.widget.LinearLayout;

/**
 * Created by chandan on 24/12/15.
 */
public class AboutUsFragment extends Fragment {
    EditText et;
    TextView reply;
    Spinner spinner;

    View vv1,v2,v3;
    TextView dynamic_inc_height,dynamic_inc;
    public LinearLayout l1,l2,l3,l4;


    public LinearLayout ll1,ll2,ll3,ll4;
    public View v11,vv2,vv3;
    public  TextView dynamic_incc,replyy;


    public LinearLayout lll1,lll2,lll3,lll4;
    public View vv11,vvv2,vvv3;
    public  TextView dynamic_inccc,replyyy;

    public ScrollView VII_D,VIII_C,staff;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_us_fragment, container,
                false);

        l1=(LinearLayout)rootView.findViewById(R.id.l1);
        l2=(LinearLayout)rootView.findViewById(R.id.l2);
        l3=(LinearLayout)rootView.findViewById(R.id.l3);
        l4=(LinearLayout)rootView.findViewById(R.id.l4);
        vv1=(View)rootView.findViewById(R.id.v1);
        v2=(View)rootView.findViewById(R.id.v2);
        v3=(View)rootView.findViewById(R.id.v3);
        dynamic_inc=(TextView)rootView.findViewById(R.id.dynamic_inc);
        reply=(TextView)rootView.findViewById(R.id.reply);


        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamic_inc.setVisibility(View.VISIBLE);

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);

                vv1.setVisibility(View.GONE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
            }
        });

        dynamic_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  dynamic_inc_height.we
                dynamic_inc.setVisibility(View.GONE);
              l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);

                vv1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.VISIBLE);
                v3.setVisibility(View.VISIBLE);

            }
        });



        ll1=(LinearLayout)rootView.findViewById(R.id.ll1);
        ll2=(LinearLayout)rootView.findViewById(R.id.ll2);
        ll3=(LinearLayout)rootView.findViewById(R.id.ll3);
        ll4=(LinearLayout)rootView.findViewById(R.id.ll4);
        v11=(View)rootView.findViewById(R.id.vv1);
        vv2=(View)rootView.findViewById(R.id.vv2);
        vv3=(View)rootView.findViewById(R.id.vv3);
        dynamic_incc=(TextView)rootView.findViewById(R.id.dynamic_incc);
        replyy=(TextView)rootView.findViewById(R.id.replyy);



        replyy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamic_incc.setVisibility(View.VISIBLE);

                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.GONE);
                ll3.setVisibility(View.GONE);
                ll4.setVisibility(View.GONE);

                v11.setVisibility(View.GONE);
                vv2.setVisibility(View.GONE);
                vv3.setVisibility(View.GONE);
            }
        });

        dynamic_incc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  dynamic_inc_height.we
                dynamic_incc.setVisibility(View.GONE);
                ll1.setVisibility(View.VISIBLE);
                ll2.setVisibility(View.VISIBLE);
                ll3.setVisibility(View.VISIBLE);
                ll4.setVisibility(View.VISIBLE);

                v11.setVisibility(View.VISIBLE);
                vv2.setVisibility(View.VISIBLE);
                vv3.setVisibility(View.VISIBLE);

            }
        });








        lll1=(LinearLayout)rootView.findViewById(R.id.lll1);
        lll2=(LinearLayout)rootView.findViewById(R.id.lll2);
        lll3=(LinearLayout)rootView.findViewById(R.id.lll3);
        lll4=(LinearLayout)rootView.findViewById(R.id.lll4);
        vv11=(View)rootView.findViewById(R.id.vvv1);
        vvv2=(View)rootView.findViewById(R.id.vvv2);
        vvv3=(View)rootView.findViewById(R.id.vvv3);
        dynamic_inccc=(TextView)rootView.findViewById(R.id.dynamic_inccc);
        replyyy=(TextView)rootView.findViewById(R.id.replyyy);






        replyyy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dynamic_inccc.setVisibility(View.VISIBLE);

                lll1.setVisibility(View.GONE);
                lll2.setVisibility(View.GONE);
                lll3.setVisibility(View.GONE);
                lll4.setVisibility(View.GONE);

                vv11.setVisibility(View.GONE);
                vvv2.setVisibility(View.GONE);
                vvv3.setVisibility(View.GONE);
            }
        });

        dynamic_inccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  dynamic_inc_height.we
                dynamic_inccc.setVisibility(View.GONE);
                lll1.setVisibility(View.VISIBLE);
                lll2.setVisibility(View.VISIBLE);
                lll3.setVisibility(View.VISIBLE);
                lll4.setVisibility(View.VISIBLE);

                vv11.setVisibility(View.VISIBLE);
                vvv2.setVisibility(View.VISIBLE);
                vvv3.setVisibility(View.VISIBLE);

            }
        });













        VII_D=(ScrollView)rootView.findViewById(R.id.class_VII);
        VIII_C=(ScrollView)rootView.findViewById(R.id.class_VIII);
        staff=(ScrollView)rootView.findViewById(R.id.staff_room_diss);

        spinner = (Spinner)rootView.findViewById(R.id.spin_disscus);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_single_choice,getActivity().getResources().getStringArray(R.array.spin_disscus));
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
                             VII_D.setVisibility(View.VISIBLE);
                            VIII_C.setVisibility(View.GONE);
                            staff.setVisibility(View.GONE);
                        }else if(position==1){
                            VII_D.setVisibility(View.GONE);
                            VIII_C.setVisibility(View.VISIBLE);
                            staff.setVisibility(View.GONE);
                        }else if(position==2){
                            VII_D.setVisibility(View.GONE);
                            VIII_C.setVisibility(View.GONE);
                            staff.setVisibility(View.VISIBLE);

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
        MyApplication.getInstance().trackScreenView("About us Fragment");
    }

}

