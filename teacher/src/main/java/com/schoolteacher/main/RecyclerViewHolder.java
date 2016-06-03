package com.schoolteacher.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.schoolteacher.R;

/**
 * Created by kundan on 10/26/2015.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView tv1,tv2,tv3;
    ImageView imageView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        tv1= (TextView) itemView.findViewById(R.id.list_title);
        tv2= (TextView) itemView.findViewById(R.id.list_desc);
      //  tv3 = (TextView) itemView.findViewById(R.id.list_fac);
        imageView= (ImageView) itemView.findViewById(R.id.list_avatar);

    }
}
