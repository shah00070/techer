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

public class analylits_aug extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final String[] name;
    public analylits_aug(Activity context,String[] web,String[] name) {
        super(context, R.layout.analytic_aug, web);
        this.context = context;
        this.web = web;
        this.name=name;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.analytic_aug, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.head_class_detaills);
        txtTitle.setText(web[position]);
        TextView name1 = (TextView) rowView.findViewById(R.id.head_class_name);
        name1.setText(name[position]);
        return rowView;
    }
}