package com.schoolteacher.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.schoolteacher.R;

/**
 * Created by perveen akhtar on 5/8/2016.
 */
public class lockeradapter extends  RecyclerView.Adapter<lockerviewholder> {

    String[] title={"Article on NTSE","Payslip of June,2016","School Rules 2016","Syllabus for Social Sciences"};

    String[] date = {"5/6/2016","6/6/2016","3/6/2016","3/6/2016"};

    String[] loctype = {"Academic Document","Official Document","Admin Document","Academic Document"};

    String[] description = {"Article explaining the change in format of the NTSE examination along with the guidelines for teachers who coach students for NTSE exam.","Salary payslip  of the month of June,2016 with leave days deduction.","Rules  and Guidelines to abide by in school.",
            "Syllabus of Social Sciences for Class test 2."

    };

    String[] tag = {"#NTSE","#payslip","#Circular","#Syllabus"};

    String[] selfdescribe = {"Self Document","Self Document","Sent to- Class VIII-D","Sent to- Class VIII-D"};

    Context context;
    LayoutInflater inflater;
    public lockeradapter(Context context) {
        this.context=context;
        inflater= LayoutInflater.from(context);
    }
    @Override
    public lockerviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.mylocker_item_list, parent, false);

        lockerviewholder vHolder=new lockerviewholder(v);
        return vHolder;
    }



    @Override
    public void onBindViewHolder(lockerviewholder holder, int position) {

        holder.t1.setText(title[position]);
        holder.t2.setText(date[position]);
        holder.t3.setText(loctype[position]);
       holder.t4.setText(description[position]);
        holder.t5.setText(tag[position]);
        holder.t6.setText(selfdescribe[position]);


    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            lockerviewholder vholder = (lockerviewholder) v.getTag();
            int position = vholder.getPosition();
            //  Toast.makeText(context,""+position,Toast.LENGTH_LONG ).show();

        }
    };



    @Override
    public int getItemCount() {

        return title.length;
    }
}


