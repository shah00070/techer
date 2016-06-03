package com.schoolteacher.main;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolteacher.R;


public class Circular_adapter extends BaseAdapter {
    private Activity activity;
    private String[] itemList;
    private String[] date;
    private String[] update;
    private String[] uptime;

    public Circular_adapter(Activity activity, String[] itemList,String[] date,String[] update,String[] uptime) {
        this.activity = activity;
        this.itemList = itemList;
        this.date=date;
        this.update=update;
        this.uptime=uptime;
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
        convertView = inflator.inflate(R.layout.circular_item, parent, false);

        TextView searchTitleTxtvw = (TextView) convertView.findViewById(R.id.title_circular);
        ImageView arrowImageView = (ImageView) convertView.findViewById(R.id.arrow_circular);
        searchTitleTxtvw.setText(itemList[position]);



        TextView updatee = (TextView) convertView.findViewById(R.id.update);
        updatee.setText(update[position]);

        TextView uptimee = (TextView) convertView.findViewById(R.id.uptime);
        uptimee.setText(uptime[position]);





        TextView date1 = (TextView) convertView.findViewById(R.id.date_circular);
        date1.setText(date[position]);


        return convertView;
    }
}
