package com.schoolteacher.main;

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


public class RecyclerViewActivity extends Fragment {


    private RecyclerView rv;
    public View rootView;
    public RecyclerViewActivity() {
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
        rootView = inflater.inflate(R.layout.fragment_one, container,
                false);



        callm();

        return rootView;
    }


    public void callm(){


        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
main_screen();

        final FloatingActionMenu menuLabelsRight = (FloatingActionMenu) rootView.findViewById(R.id.menu_labels_rightt);

        com.github.clans.fab.FloatingActionButton invfrn = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.inv_frnn);
        //   com.github.clans.fab.FloatingActionButton reqquote = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.req_quote);
    /*    com.github.clans.fab.FloatingActionButton attachdoc = (com.github.clans.fab.FloatingActionButton) rootView.findViewById(R.id.attach_docc);
*/


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


       /* attachdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ConfidentialNote.class);

                getActivity().startActivity(i);

                getActivity().overridePendingTransition(R.anim.trans_left_in,
                        R.anim.trans_left_exit);
                menuLabelsRight.toggle(true);


            }
        });
*/
    }

    private void main_screen() {
;

    }

    private void initializeData() {
//        HomeActivity.persons.add(new Person("Submit passport size photograph", "Don’t forget to submit one passport size photographs of yours by tomorrow for the NTSE form.", "21:20","2/20/2016", R.color.transparent_black,"Delivered","Arun Sharma"));
//
//        HomeActivity.length.add(new Length(50));


        HomeActivity.persons.add(new Person("Presentation on Passive and Active Voice", "Make a ppt for class VIII A, describing the rules of Active and Passive voice.", "21:20", "30/4/2016", R.color.transparentt, "Self Note"));
HomeActivity.matrixx=220;
        HomeActivity.length.add(new Length(220));


    }


    @Override
    public void onResume() {

       // Toast.makeText(getActivity(), "rrt", Toast.LENGTH_SHORT).show();
       initializeAdapter();
//        Toast.makeText(getActivity(), HomeActivity.persons.get(0)+ "", Toast.LENGTH_SHORT).show();
        super.onResume();
    }


    private void initializeAdapter(){

        RVAdapter adapter = new RVAdapter(HomeActivity.persons,HomeActivity.length);
        rv.setAdapter(adapter);


    }
}
