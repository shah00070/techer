package com.schoolteacher.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.schoolteacher.R;
import com.schoolteacher.interfaces.AppConstants;

/**
 * Created by Arun Sharma on 04-May-16.
 */
public class NotificationSlideDown extends Fragment implements AppConstants {

    private View rootView;
    RecyclerView recyclerView;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.notificationslidedown, container,
                false);


        setUiOnScreen();
        return rootView;
    }


    private void setUiOnScreen(){
        recyclerView= (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        RecyclerAdapter adapter=new RecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }


}
