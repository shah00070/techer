package com.schoolteacher.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.schoolteacher.R;

import java.util.List;

/**
 * Created by perveen akhtar on 5/10/2016.
 */
public class myclassmateadapter  extends RecyclerView.Adapter<myclassmateadapter.MyViewHolder> {

    private List<myclassmatemodel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView myclassmatename, myclassmaterollno;

        public MyViewHolder(View view) {
            super(view);
            myclassmatename = (TextView) view.findViewById(R.id.myclassmatename);
            myclassmaterollno = (TextView) view.findViewById(R.id.myclassmaterollno);


            // userimg = (CircleImageView) view.findViewById(R.id.user_imageview);
        }
    }


    public myclassmateadapter(List<myclassmatemodel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myclassmatesitemlist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        myclassmatemodel movie = moviesList.get(position);
        holder.myclassmatename.setText(movie.getMyclassmatename());
        holder.myclassmaterollno.setText(movie.getMyclassmaterollno());
        //  holder.userimg.setImageResource(movie.getUserimg());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

