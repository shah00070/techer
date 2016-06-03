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

import com.schoolteacher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arun Sharma on 05-May-16.
 */
public class Inbox extends Fragment {

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.inbox, container,
                false);


        setUiOnScreen();
        return rootView;
    }

    public void setUiOnScreen() {



        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        prepareMovieData();


    }





    private void prepareMovieData() {

        Movie movie = new Movie("Ajay Sharma -Principal", "SchoolFee Mail", "PTM for classes Nursery to X  will be on 11th June, 2016 from 07:30 am to 10:00 am.");
        movieList.add(movie);

        movie = new Movie("Shailesh Tiwari- Student", "SchoolFee Mail", "Hello Ma’am. Just wanted to clear my doubts on Active and Passive Voices.");
        movieList.add(movie);

        movie = new Movie("Deepak Dhar-School Administration", "Science Notebook", "Check your salary slip for month of May.");
        movieList.add(movie);

        movie = new Movie("Shivam Raj- IT Department", "Science Notebook", "Internet will not be working on Monday  i.e. 13th June, 2016 from 12:00 pm to 3:00 pm.");
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










