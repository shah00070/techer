package com.schoolteacher.main;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;
import com.schoolteacher.fragments.Friday;
import com.schoolteacher.fragments.Monday;
import com.schoolteacher.fragments.Saturday;
import com.schoolteacher.fragments.Tuesday;
import com.schoolteacher.fragments.Wednesday;
import com.schoolteacher.fragments.thusday;
import com.schoolteacher.interfaces.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandan on 19/5/16.
 */

public class Clas_list extends Fragment implements AppConstants {
    public View rootView;
    public classlist adapter;
    public RelativeLayout filter1,submitup,rev;
    ListView list;

    public Spinner spinner;
    String[] web = null;
    Integer[] imageId = null;

    String[] emailids =null;
    String[] class_name =null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // session = new JeevomSession(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.classlist, container,
                false);
        setUiOnScreen();
        return rootView;
    }

    private void setUiOnScreen() {

        spinner = (Spinner)rootView.findViewById(R.id.spin);

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
                            };
                            imageId = new Integer[]{
                                    R.drawable.stu1,
                                    R.drawable.stu2,
                                    R.drawable.stu3,
                                    R.drawable.stu4,
                                    R.drawable.stu5,
                                    R.drawable.stu6,
                     };
                            emailids = new String[]{
                                    "Roll No. 10",
                                    "Roll No. 11",
                                    "Roll No. 12",
                                    "Roll No. 13",
                                    "Roll No. 14",
                                    "Roll No. 15",
                  };


                            class_name = new String[]{
                                    "Class VII-D",
                                    "Class VII-D",
                                    "Class VII-D",
                                    "Class VII-D",
                                    "Class VII-D",
                                    "Class VII-D",


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
           };
                            imageId = new Integer[]{
                                    R.drawable.st1,
                                    R.drawable.st5,
                                    R.drawable.st2,
                                    R.drawable.st6,
                                    R.drawable.st3,
                                    R.drawable.st4,





                            };
                            emailids = new String[]{
                                    "Roll No. 21",
                                    "Roll No. 22",
                                    "Roll No. 23",
                                    "Roll No. 24",
                                    "Roll No. 25",
                                    "Roll No. 26",


                            };
                            class_name = new String[]{
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "Class VIII-C",
                                    "Class VIII-C",


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
    adapter = new classlist(getActivity(), web, imageId, emailids,class_name);
    list = (ListView) rootView.findViewById(R.id.students_list);
    list.setAdapter(adapter);
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
        }
    });
}

    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint(visible);
        if (visible && isResumed()) {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
    }
    @Override
    public void onResume() {
        super.onResume();

        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Cloud Fragment");
    }


}
