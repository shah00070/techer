package com.schoolteacher.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.schoolteacher.MyApplication;
import com.schoolteacher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandan on 16/5/16.
 */



public class Inboxx extends Fragment {
    ListView list;
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;




    String[] web = {
            "Ekant Jain",
            "Parul Sharma",
            "Rahul Gupta",
            "Akansha Sharma",
            "",
            "Akansha Sharma",

    } ;
    Integer[] imageId = {
            R.drawable.stu1,
            R.drawable.stu2,
            R.drawable.stu3,
            R.drawable.stu4,
            R.drawable.stu5,
            R.drawable.stu6,



    };

    String[] emailids = {
            "Hindi",
            "Science",
            "Sanskrit",
            "English",
            "",
            "",


    } ;


    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.inboxx, container,
                false);
        cll();
 return rootView;
    }
public void cll(){

    studentlistt adapter = new
            studentlistt(getActivity(), web, imageId,emailids);
    list=(ListView)rootView.findViewById(R.id.list_viewee);
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

        MyApplication.getInstance().trackScreenView("Deals & Offer details");
    }







}










