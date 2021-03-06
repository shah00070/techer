package com.schoolteacher.main;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.mylibrary.util.JeevOMUtil;

/**
 * Created by chandan on 8/12/15.
 */
public class DealsAndOfferFragment extends Fragment implements JeevOMUtil {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public View rootView;
    public studentlist adapter1;
    public Spinner spinner;
    public RelativeLayout filter1,submitup,rev;
    ListView list;
    String[] web = null;
    Integer[] imageId = null;

    String[] emailids =null;
    String[] class_name =null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        MyApplication.getInstance().trackScreenView("Deals & Offer details");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.deals_offer_fragment, container,
                false);

call1();
        return rootView;
    }

    public void call1(){



                spinner = (Spinner)rootView.findViewById(R.id.spinner_att);

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
                            web = new String[]{
                                    "Ankit Jain",
                                    "Ankit Singh",
                                    "Akansha Sharma",
                                    "Parul Sharma",
                                    "Rahul Gupta",
                                    "Viraj Bhatnagar",
                                    "",
                            };
                            imageId = new Integer[]{
                                    R.drawable.stu1,
                                    R.drawable.stu2,
                                    R.drawable.stu3,
                                    R.drawable.stu4,
                                    R.drawable.stu5,
                                    R.drawable.stu6,
                                    R.color.tarns,
                            };
                            emailids = new String[]{
                                    "Roll No. 10",
                                    "Roll No. 11",
                                    "Roll No. 12",
                                    "Roll No. 13",
                                    "Roll No. 14",
                                    "Roll No. 15",
                                    "",
                            };


                            class_name = new String[]{
                                    "Class VII-D",
                                    "Class VII-D",
                                    "Class VII-D",
                                    "Class VII-D",
                                    "Class VII-D",
                                    "Class VII-D",
                                    "",


                            };

                            call();


                        } else if (position == 1) {


                            web = new String[]{
                                    "Babli Singh",
                                    "Himanshu Gupta",
                                    "Priya Singh",
                                    "Ranjeet Singh",
                                    "Sonal Sharma",
                                    "Yusuf Sharma",
                                    "",
                            };
                            imageId = new Integer[]{
                                    R.drawable.st1,
                                    R.drawable.st5,
                                    R.drawable.st2,
                                    R.drawable.st6,
                                    R.drawable.st3,
                                    R.drawable.st4,
                                    R.color.tarns,





                            };
                            emailids = new String[]{
                                    "Roll No. 21",
                                    "Roll No. 22",
                                    "Roll No. 23",
                                    "Roll No. 24",
                                    "Roll No. 25",
                                    "Roll No. 26",
                                    "",


                            };
                            class_name = new String[]{
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "",


                            };
                            call();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {


                    }

                }
        );






        rev=(RelativeLayout)rootView.findViewById(R.id.save_search_layout1);
        rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fg = new Inboxx();
                ft.setCustomAnimations(R.anim.trans_up_in, R.anim.trans_up_exit);
                ft.replace(R.id.container_body, fg);
                ft.commit();
                Toast.makeText(getActivity(), "sss", Toast.LENGTH_SHORT).show();
            //    rev.setBackgroundResource(R.color.transparent_black);
            }




        });

         submitup=(RelativeLayout)rootView.findViewById(R.id.filter_layout);
        submitup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   submitup.setBackgroundResource(R.color.transparent_black);

                Toast.makeText(getActivity(), "Submitting...", Toast.LENGTH_SHORT).show();
            }



        });



         filter1=(RelativeLayout)rootView.findViewById(R.id.save_search_layout);
        filter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //   filter1.setBackgroundResource(R.color.transparent_black);

                final CharSequence[] items = {"All Present", "All Absent"};

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pick a color");
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //    Toast.makeText(getActivity(), "sss", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }


                });
                AlertDialog alert = builder.create();
//And if the line above didn't bring ur dialog up use this bellow also:
                alert.show();
            }
        });








    }
    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
    }

public void call(){

    adapter1 = new
            studentlist(getActivity(), web, imageId,emailids);
    list=(ListView)rootView.findViewById(R.id.attendencelist);
    list.setAdapter(adapter1);
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            //    Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

        }
    });

}
}
