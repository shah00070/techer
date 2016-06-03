package com.schoolteacher.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionMenu;
import com.schoolteacher.R;

/**
 * Created by perveen akhtar on 5/8/2016.
 */
public class mylocker extends Fragment {

    private View rootView;
    RecyclerView recyclerView;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.mylocker, container,
                false);


        setUiOnScreen();
        return rootView;
    }


    private void setUiOnScreen(){
        recyclerView= (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        final FloatingActionMenu menuLabelsRight = (FloatingActionMenu) rootView.findViewById(R.id.menu_labels_rightt_up);

        com.github.clans.fab.FloatingActionButton invfrn = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.inv_frnn_up);

        invfrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent i = new Intent(getActivity(), Addnote.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.trans_left_in,
                        R.anim.trans_left_exit);
                menuLabelsRight.toggle(true);*/

            }
        });





        lockeradapter adapter=new lockeradapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }


}
