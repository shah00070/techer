package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

/**
 * Created by chandan on 12/5/16.
 */

public class newMyTeachers extends Fragment {
    public View rootView;

    ListView list;
    String[] web = {
            "Ekant Jain",
            "Parul Sharma",
            "Sangita Bhatnagar",
            "Ankit Singh",
            "Rahul Gupta",
            "Akansha Sharma",

    } ;
    Integer[] imageId = {
            R.drawable.onet,
            R.drawable.two1,
            R.drawable.threet,
            R.drawable.fourt,
            R.drawable.fivet,
            R.drawable.sixt,



    };

    String[] emailids = {
            "Hindi",
            "Science",
            "Sanskrit",
            "Mathematics",
            "Social Science",
            "English",

    } ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.newsocial_mediaa, container,
                false);
        call();
        return rootView;
    }


    public void call(){

        CustomListl adapter = new
                CustomListl(getActivity(), web, imageId,emailids);
        list=(ListView)rootView.findViewById(R.id.list_viewe);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //    Toast.makeText(getActivity(), "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        // Tracking the screen view
        MyApplication.getInstance().trackScreenView("Health Mart Fragment");
    }
}
