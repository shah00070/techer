package com.schoolteacher.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolteacher.R;


public class PopularSearchAdapter extends BaseAdapter {
    private Activity activity;
    private String[] itemList;
    private String[] date;

    public PopularSearchAdapter(Activity activity, String[] itemList,String[] date) {
        this.activity = activity;
        this.itemList = itemList;
        this.date=date;
    }

    @Override
    public int getCount() {
        return itemList.length;
    }

    @Override
    public Object getItem(int position) {
        return itemList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflator.inflate(R.layout.popular_search_list_item, parent, false);

        TextView searchTitleTxtvw = (TextView) convertView.findViewById(R.id.title_search);
        ImageView arrowImageView = (ImageView) convertView.findViewById(R.id.arrow_imagevw);
        searchTitleTxtvw.setText(itemList[position]);


        TextView date1 = (TextView) convertView.findViewById(R.id.datetitle);
        date1.setText(date[position]);


        return convertView;
    }
}
