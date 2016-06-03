package com.schoolteacher.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.schoolteacher.R;

/**
 * Created by chandan on 9/5/16.
 */
public class Month extends Fragment {

    private RecyclerView rv;
    public View rootView;
    public EditText pre,abs;
public LinearLayout present,absent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.month, container, false);


call();
        return rootView;
    }
public void call(){

 /*   abs=(EditText)rootView.findViewById(R.id.absenmonth);
    pre=(EditText)rootView.findViewById(R.id.presentmonth);
    present=(LinearLayout)rootView.findViewById(R.id.present);

    absent=(LinearLayout)rootView.findViewById(R.id.absent);

*/

    /*pre.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            absent.setVisibility(View.GONE);
            present.setVisibility(View.VISIBLE);
            abs.setBackgroundResource(R.color.colorOrange);
            Toast.makeText(getActivity(), "premon", Toast.LENGTH_SHORT).show();
            pre.setBackgroundResource(R.color.transparentt);
        }
    });



    abs.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            present.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "abssmon", Toast.LENGTH_SHORT).show();
            absent.setVisibility(View.VISIBLE);
            pre.setBackgroundResource(R.color.colorOrange);
            abs.setBackgroundResource(R.color.transparentt);

        }
    });*/



}


    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint())
        {
            return;
        }
        call();

    }


}
