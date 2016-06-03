package com.schoolteacher.main;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.schoolteacher.R;

/**
 * Created by chandan on 25/5/16.
 */



public class SocialMedia_aug extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final String[] name;
    private final String[] field;
    public SocialMedia_aug(Activity context,String[] web,String[] name,String[] field) {
        super(context, R.layout.socialmedia_aug, web);
        this.context = context;
        this.web = web;
        this.name=name;
        this.field=field;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.socialmedia_aug, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.socialmedia_d_detaillss);
        txtTitle.setText(web[position]);
        TextView name1 = (TextView) rowView.findViewById(R.id.socialmedia_n_namee);
        name1.setText(name[position]);


        TextView field1 = (TextView) rowView.findViewById(R.id.chatdeciderr);
        field1.setText(field[position]);
        return rowView;
    }
}