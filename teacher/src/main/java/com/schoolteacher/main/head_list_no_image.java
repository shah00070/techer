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


public class head_list_no_image extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] emailids;

    private final String[] web;

    public head_list_no_image(Activity context,
                     String[] web,String[] emailids) {
        super(context, R.layout.head_item, web);
        this.context = context;
        this.web = web;
        this.emailids = emailids;



    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.head_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.head_class_name);
        TextView descriptionn = (TextView) rowView.findViewById(R.id.head_class_detail);
        txtTitle.setText(web[position]);
        descriptionn.setText(emailids[position]);

        return rowView;
    }
}