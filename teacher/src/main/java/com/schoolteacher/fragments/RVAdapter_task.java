package com.schoolteacher.fragments;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolteacher.R;

import java.util.List;

/**
 * Created by chandan on 9/5/16.
 */

public class RVAdapter_task extends RecyclerView.Adapter<RVAdapter_task.PersonViewHolder> {

    public static class PersonViewHolder  extends RecyclerView.ViewHolder{
        LinearLayout p;
        public static int check=0;
        public static int check1=1;
        TextView del;
        TextView recname;
        CardView cv;
      //  Button b;
        TextView personName;
        EditText contentt;
        TextView personAge;
        TextView personPhoto;
        TextView time;
      //  TextView person_nafme;
       // TextView commentone;

        TextView star;
        TextView book;

       // TextView comment_t;


      //  EditText comment;

      //  View line43;


     //   TextView stasr;

        //TextView bogokmar;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.tcvv);
            personName = (TextView)itemView.findViewById(R.id.tperson_namee);
            p=(LinearLayout)itemView.findViewById(R.id.tpkk);
           personAge = (TextView)itemView.findViewById(R.id.tperson_agee);
            personPhoto = (TextView)itemView.findViewById(R.id.tperson_photoo);
            time = (TextView)itemView.findViewById(R.id.ttimeee);

            recname = (TextView)itemView.findViewById(R.id.trecnamee);
              del = (TextView)itemView.findViewById(R.id.tdelevv);

            star = (TextView)itemView.findViewById(R.id.tstarr);
            book = (TextView)itemView.findViewById(R.id.tbookmarr);


        }


    }

    List<task> taskk;
    List<tasklen> tasklenn;

    public RVAdapter_task(List<task> taskname, List<tasklen> tasklen) {
        this.taskk=taskname;
        this.tasklenn=tasklen;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(taskk.get(i).name);
        personViewHolder.personPhoto.setText(taskk.get(i).content);


        personViewHolder.personAge.setText(taskk.get(i).age);
        personViewHolder.personPhoto.setBackgroundResource(taskk.get(i).photoId);

        personViewHolder.time.setText(taskk.get(i).time);




    /*    HomeActivity.persons.add(new Person("Submit passport size photograph", "Don’t forget to submit one passport size photographs of yours by tomorrow for the NTSE form.", "21:20", "2/20/2016", R.color.transparentt, "Delivered", "Arun Sharma"));
HomeActivity.matrixx=100;
        HomeActivity.length.add(new Length(100));
*/










        personViewHolder.del.setText(taskk.get(i).del);
        personViewHolder.recname.setText(taskk.get(i).rec);


        //   DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();







        personViewHolder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PersonViewHolder.check1==0){
                    personViewHolder.book.setBackgroundResource(R.drawable.unbook);
                    PersonViewHolder.check1=1;
                }else if(personViewHolder.check1==1){
                    personViewHolder.book.setBackgroundResource(R.drawable.bbookmark);
                    PersonViewHolder.check1=0;
                }
                // personViewHolder.personPhoto.setLayoutParams(new RecyclerView.LayoutParams(500, 500));
            }
        });



        personViewHolder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PersonViewHolder.check==0){
                    personViewHolder.star.setBackgroundResource(R.drawable.unstar);
                    PersonViewHolder.check=1;
                }else if(personViewHolder.check==1){
                    personViewHolder.star.setBackgroundResource(R.drawable.stasr);
                    PersonViewHolder.check=0;
                }

                // personViewHolder.personPhoto.setLayoutParams(new RecyclerView.LayoutParams(500, 500));
            }
        });

        //   int height_in_pixels = Addnote.linex * Addnote.linex; //approx height text
        int height_in_pixels =tasklenn.get(i).leng;



        ViewGroup.LayoutParams params = personViewHolder.personPhoto.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = height_in_pixels;
        personViewHolder.personPhoto.requestLayout();



        //       this.Toast.makeText(getActivity(), "sd", Toast.LENGTH_SHORT).show();
        //    personViewHolder.personPhoto.setMovementMethod(new ScrollingMovementMethod());


    }


    @Override
    public int getItemCount() {
        return taskk.size();
    }



}
