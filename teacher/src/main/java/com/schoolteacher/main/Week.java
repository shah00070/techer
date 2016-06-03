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
public class Week extends Fragment {


    private RecyclerView rv;
    public View rootView;

    public EditText pre,abs;
    public LinearLayout presentt,absentt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.week, container, false);
      callvoid();
        return rootView;
    }


    public void callvoid(){
     /*   presentt=(LinearLayout)rootView.findViewById(R.id.presentt);

        absentt=(LinearLayout)rootView.findViewById(R.id.absentt);

        abs=(EditText)rootView.findViewById(R.id.absweek);
        pre=(EditText)rootView.findViewById(R.id.preweek);



        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentt.setVisibility(View.VISIBLE);
                absentt.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "preweek", Toast.LENGTH_SHORT).show();
                pre.setBackgroundResource(R.color.transparentt);
                abs.setBackgroundResource(R.color.colorOrange);
            }
        });
        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "preweek", Toast.LENGTH_SHORT).show();
                absentt.setVisibility(View.VISIBLE);
                presentt.setVisibility(View.GONE);
                pre.setBackgroundResource(R.color.colorOrange);
                abs.setBackgroundResource(R.color.transparentt);
            }
        });

*/
    }
    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible && isResumed())
        {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
    }
    @Override
    public void onResume() {

        super.onResume();
        if (!getUserVisibleHint())
        {
            return;
        }
        callvoid();
    }

}