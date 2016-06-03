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

/**
 * Created by chandan on 24/5/16.
 */


public class head_list extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public head_list(Activity context,
                          String[] web, Integer[] imageId) {
        super(context, R.layout.head_no_item, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;



    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.head_no_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.head_class_detail_no);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.head_image);
        txtTitle.setText(web[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}