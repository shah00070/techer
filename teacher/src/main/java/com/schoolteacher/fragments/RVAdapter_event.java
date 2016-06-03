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
public class RVAdapter_event extends RecyclerView.Adapter<RVAdapter_event.PersonViewHolder> {

    public static class PersonViewHolder  extends RecyclerView.ViewHolder{
        LinearLayout p;
        public static int check=0;
        public static int check1=1;
        TextView del;
        TextView recname;
        CardView cv;
        Button b;
        TextView personName;
        EditText contentt;
        TextView personAge;
        TextView personPhoto;
        TextView time;
     //   TextView person_nafme;
        TextView commentone;

        TextView star;
        TextView book;

        TextView comment_t;


        EditText comment;

        View line43;


      //  TextView stasr;

       // TextView bogokmar;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvv);
            personName = (TextView)itemView.findViewById(R.id.person_namee);
            p=(LinearLayout)itemView.findViewById(R.id.pkk);
            personAge = (TextView)itemView.findViewById(R.id.person_agee);
            personPhoto = (TextView)itemView.findViewById(R.id.person_photoo);
            time = (TextView)itemView.findViewById(R.id.timeee);

            recname = (TextView)itemView.findViewById(R.id.recnamee);
           // line43=(View)itemView.findViewById(R.id.line43);
      del = (TextView)itemView.findViewById(R.id.delevv);

            star = (TextView)itemView.findViewById(R.id.starr);
            book = (TextView)itemView.findViewById(R.id.bookmarr);

        }


    }

    List<event> event;
    List<eventlen> eventlen;
    public RVAdapter_event(List<event> eventname, List<eventlen> eventlength) {
        this.event=eventname;
        this.eventlen=eventlength;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(event.get(i).name);
        personViewHolder.personPhoto.setText(event.get(i).content);


        personViewHolder.personAge.setText(event.get(i).age);
        personViewHolder.personPhoto.setBackgroundResource(event.get(i).photoId);

        personViewHolder.time.setText(event.get(i).time);




            personViewHolder.del.setText(event.get(i).del);
            personViewHolder.recname.setText(event.get(i).rec);







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
            int height_in_pixels =eventlen.get(i).leng;



            ViewGroup.LayoutParams params = personViewHolder.personPhoto.getLayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = height_in_pixels;
            personViewHolder.personPhoto.requestLayout();



            //       this.Toast.makeText(getActivity(), "sd", Toast.LENGTH_SHORT).show();
            //    personViewHolder.personPhoto.setMovementMethod(new ScrollingMovementMethod());


        }


        @Override
            public int getItemCount() {
                return event.size();
            }



}
