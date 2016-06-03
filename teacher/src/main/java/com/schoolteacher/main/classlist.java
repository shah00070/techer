package com.schoolteacher.main;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolteacher.R;



public class classlist extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] emailids;
    private final String[] class_name;
    private final String[] web;
    private final Integer[] imageId;
    public classlist(Activity context,
                       String[] web, Integer[] imageId,String[] emailids,String[] classn) {
        super(context, R.layout.classlistitem, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.emailids = emailids;
        this.class_name=classn;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.classlistitem, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.st_name);
        TextView descriptionn = (TextView) rowView.findViewById(R.id.dis);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.class_st_image);
        TextView class_namee = (TextView) rowView.findViewById(R.id.class_name);
        class_namee.setText(class_name[position]);
        txtTitle.setText(web[position]);
        descriptionn.setText(emailids[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}