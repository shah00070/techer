package com.schoolteacher.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolteacher.R;

/**
 * Created by chandan on 12/5/16.
 */

public class CustomListl extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] emailids;
    private final String[] web;
    private final Integer[] imageId;
    public CustomListl(Activity context,
                      String[] web, Integer[] imageId,String[] emailids) {
        super(context, R.layout.newsocial_media, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.emailids = emailids;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.newsocial_media, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtt);
        TextView descriptionn = (TextView) rowView.findViewById(R.id.descriptionnn);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgg);
        txtTitle.setText(web[position]);
        descriptionn.setText(emailids[position]);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}