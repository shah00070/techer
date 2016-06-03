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
public class social_media extends Fragment {
    public View rootView;

    ListView list;
    String[] web = {
            "Facebook",
            "Google",
            "Instagram",
            "Pinterest",
            "Skype",
            "Twitter",

    } ;
    Integer[] imageId = {
            R.drawable.fb_wh,
            R.drawable.google,
            R.drawable.instagram,
            R.drawable.pintrst_wh,
            R.drawable.skype,
            R.drawable.twt_wh,



    };

    String[] emailids = {
           "www.facebook.com/profile/XYZschool",
            "www.google.com/plus/profile/XYZschool",
            "www.instagram.com/profile/XYZschool",
            "www.pinterest.com/profile/XYZschool",
            "www.skype.com/profile/XYZschool",
            "www.twitter.com/profile/XYZschool",


    } ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.social_media, container,
                false);
call();
        return rootView;
    }


    public void call(){

        CustomList adapter = new
                CustomList(getActivity(), web, imageId,emailids);
        list=(ListView)rootView.findViewById(R.id.list_view);
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
