package com.schoolteacher.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionMenu;
import com.schoolteacher.R;
import com.schoolteacher.main.Addnote;
import com.schoolteacher.main.HomeActivity;


public class ThreeFragment extends Fragment {

    private RecyclerView rv;
    public View rootView;
    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_three, container, false);



        callm();
        return rootView;
    }
    public void callm(){


        rv=(RecyclerView)rootView.findViewById(R.id.rvv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        final FloatingActionMenu menuLabelsRight = (FloatingActionMenu) rootView.findViewById(R.id.menu_labels_righttt);

        com.github.clans.fab.FloatingActionButton invfrn = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.inv_frnnn);

        invfrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Addnote.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.trans_left_in,
                        R.anim.trans_left_exit);
                menuLabelsRight.toggle(true);

            }
        });



    }
    private void initializeData() {

        HomeActivity.eventname.add(new event("Workshop on Group Discussion", "A workshop to be organized on Group Discussion on upcoming Saturday for class VIII D from 9:30 A.M. to 10:30 A.M.", "21:20", "29/5/2016", R.color.transparentt, "Delivered", "Arun Sharma"));
        HomeActivity.matrixx=300;
        HomeActivity.eventlength.add(new eventlen(300));


    }
    @Override
    public void onResume() {

        initializeAdapter();
       super.onResume();
    }


    private void initializeAdapter(){

        RVAdapter_event adapter = new RVAdapter_event(HomeActivity.eventname,HomeActivity.eventlength);
        rv.setAdapter(adapter);


    }

}
