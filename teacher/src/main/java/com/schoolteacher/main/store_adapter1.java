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


public class store_adapter1 extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final String[] name;
    public store_adapter1(Activity context,String[] web,String[] name) {
        super(context, R.layout.store_june, web);
        this.context = context;
        this.web = web;
        this.name=name;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.store_june, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.store_d_detaillss);
        txtTitle.setText(web[position]);
        TextView name1 = (TextView) rowView.findViewById(R.id.store_n_namee);
        name1.setText(name[position]);
        return rowView;
    }
}