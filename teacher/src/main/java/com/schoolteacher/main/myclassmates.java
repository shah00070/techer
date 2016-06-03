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
import android.widget.ImageView;

import com.schoolteacher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perveen akhtar on 5/10/2016.
 */
public class myclassmates extends Fragment {



    private List<myclassmatemodel> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private myclassmateadapter mAdapter;

    private View rootView;
    ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.myclassmates, container,
                false);


        setUiOnScreen();
        return rootView;
    }

    public void setUiOnScreen() {



        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new myclassmateadapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                myclassmatemodel movie = movieList.get(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));








        prepareMovieData();


    }





    private void prepareMovieData() {

        myclassmatemodel movie = new myclassmatemodel("Kushagra Sharma", "Roll No. 12213");
        movieList.add(movie);

        movie = new myclassmatemodel("Gajendra Prasad", "Roll No. 12215");
        movieList.add(movie);

        movie = new myclassmatemodel("Vipul Mishra", "Roll No. 12216");
        movieList.add(movie);

        movie = new myclassmatemodel("Ankit Choudhary", "Roll No. 12218");
        movieList.add(movie);

        movie = new myclassmatemodel("Tarun Rawat", "Roll No. 12220");
        movieList.add(movie);

        movie = new myclassmatemodel("Roopak Singh", "Roll No. 12221");
        movieList.add(movie);


        mAdapter.notifyDataSetChanged();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }




    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }





}
