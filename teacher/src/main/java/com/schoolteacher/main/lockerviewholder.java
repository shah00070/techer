package com.schoolteacher.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.schoolteacher.R;

/**
 * Created by perveen akhtar on 5/8/2016.
 */
public class lockerviewholder  extends RecyclerView.ViewHolder {

    TextView t1,t2,t3,t4,t5,t6;

    public lockerviewholder(View itemView) {
        super(itemView);

        t1= (TextView) itemView.findViewById(R.id.loc_title);
        t2= (TextView) itemView.findViewById(R.id.loc_date);
        t3 = (TextView) itemView.findViewById(R.id.loc_type);
        t4 = (TextView) itemView.findViewById(R.id.loc_description);
        t5 = (TextView) itemView.findViewById(R.id.loc_tag);
        t6 = (TextView) itemView.findViewById(R.id.self);

    }
}
