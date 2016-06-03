package com.schoolteacher.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolteacher.R;

import java.util.List;

/**
 * Created by perveen akhtar on 5/10/2016.
 */
public class myteacheradapter extends RecyclerView.Adapter<myteacheradapter.MyViewHolder> {

    private List<myteachersmodel> moviesList;
ImageView image;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView myteachername, myteachersubject;
        public CircleImageView userimg;

        public MyViewHolder(View view) {
            super(view);
            myteachername = (TextView) view.findViewById(R.id.myteachername);
            myteachersubject = (TextView) view.findViewById(R.id.myteachersubject);


           // userimg = (CircleImageView) view.findViewById(R.id.user_imageview);
        }
    }


    public myteacheradapter(List<myteachersmodel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myteacherslistrow, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        myteachersmodel movie = moviesList.get(position);
        holder.myteachername.setText(movie.getMyteachername());
        holder.myteachersubject.setText(movie.getMyteachersubject());
      //  holder.userimg.setImageResource(movie.getUserimg());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}


