package com.schoolteacher.main;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.schoolteacher.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {



    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        LinearLayout p;
        public static int check=0;
        public static int check1=1;
        TextView del;
        TextView recname;
        CardView cv;
     //   Button b;
        TextView personName;
        EditText contentt;
        TextView personAge;
        TextView personPhoto;
        TextView time;
        //TextView person_nafme;
      //  TextView commentone;

        TextView star;
        TextView book;

       // TextView comment_t;

LinearLayout changec;
       // TextView click,click2;


     //   EditText comment;

     //   View line43;


        //TextView stasr;

       // TextView bogokmar;
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
p=(LinearLayout)itemView.findViewById(R.id.pk);
        //    linear=(LinearLayout)itemView.findViewById(R.id.linear);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (TextView)itemView.findViewById(R.id.person_photo);
         //   time = (TextView)itemView.findViewById(R.id.timee);

            recname = (TextView)itemView.findViewById(R.id.recname);
           // line43=(View)itemView.findViewById(R.id.line43);



            changec=(LinearLayout)itemView.findViewById(R.id.changec);
          //  click = (TextView)itemView.findViewById(R.id.ack);


         /*   changee=(LinearLayout)itemView.findViewById(R.id.changeec);
            click2 = (TextView)itemView.findViewById(R.id.akk);
*/

/*
            person_nafme = (TextView)itemView.findViewById(R.id.person_nafme);

            comment_t = (TextView)itemView.findViewById(R.id.comment_t);*/
         //   del = (TextView)itemView.findViewById(R.id.delev);

            star = (TextView)itemView.findViewById(R.id.star);
            book = (TextView)itemView.findViewById(R.id.bookmar);/*

b=(Button)itemView.findViewById(R.id.commentbutton);
            comment=(EditText)itemView.findViewById(R.id.comment);
            stasr = (TextView)itemView.findViewById(R.id.stasr);
            bogokmar = (TextView)itemView.findViewById(R.id.bogokmar);
            commentone=(TextView)itemView.findViewById(R.id.commentone);*/
        }
    }

    List<Person> persons;
    List<Length> le;
    public RVAdapter(List<Person> persons,List<Length> len){
        this.persons = persons;
        this.le=len;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemmmm, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {

        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.personPhoto.setText(persons.get(i).content);


        personViewHolder.personAge.setText(persons.get(i).time+"    "+persons.get(i).age);
        personViewHolder.personPhoto.setBackgroundResource(persons.get(i).photoId);

      //  personViewHolder.time.setText(persons.get(i).time);

/*
        personViewHolder.click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personViewHolder.changee.setBackgroundResource(R.color.transparent_black);

            }
        });*/


/*
        personViewHolder.click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
personViewHolder.changec.setBackgroundResource(R.color.transparent_black);

            } });*/

/*
        personViewHolder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







                personViewHolder.comment_t.setVisibility(View.VISIBLE);



                ViewGroup.LayoutParams params = personViewHolder.commentone.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
               // HomeActivity.matrixx=5;
                params.height = (personViewHolder.comment.getLineCount()*25)*4;
                personViewHolder.commentone.requestLayout();


                personViewHolder. linear.setVisibility(View.GONE);
                personViewHolder. comment.setVisibility(View.GONE);
                personViewHolder. b.setVisibility(View.GONE);

                // personViewHolder.commentone.setHeight();

                personViewHolder.commentone.setVisibility(View.VISIBLE);
                personViewHolder.commentone.setText(personViewHolder.comment.getText());

personViewHolder.line43.setVisibility(View.VISIBLE);



            }
        });*/






      //  personViewHolder.del.setText(persons.get(i).del);
        personViewHolder.recname.setText(persons.get(i).rec);
        personViewHolder.personPhoto.setMovementMethod(new ScrollingMovementMethod());

     //   DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();



//
//        personViewHolder.stasr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(PersonViewHolder.check==0){
//                    personViewHolder.stasr.setBackgroundResource(R.drawable.unstar);
//                    PersonViewHolder.check=1;
//                }else if(personViewHolder.check==1){
//                    personViewHolder.stasr.setBackgroundResource(R.drawable.stasr);
//                    PersonViewHolder.check=0;
//                }
//                // personViewHolder.personPhoto.setLayoutParams(new RecyclerView.LayoutParams(500, 500));
//            }
//        });



//        personViewHolder.bogokmar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(PersonViewHolder.check1==0){
//                    personViewHolder.bogokmar.setBackgroundResource(R.drawable.unbook);
//                    PersonViewHolder.check1=1;
//                }else if(personViewHolder.check1==1){
//                    personViewHolder.bogokmar.setBackgroundResource(R.drawable.bbookmark);
//                    PersonViewHolder.check1=0;
//                }
//
//                // personViewHolder.personPhoto.setLayoutParams(new RecyclerView.LayoutParams(500, 500));
//            }
//        });


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
        int height_in_pixels =le.get(i).leng;



        ViewGroup.LayoutParams params = personViewHolder.personPhoto.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = height_in_pixels;
        personViewHolder.personPhoto.requestLayout();



 //       this.Toast.makeText(getActivity(), "sd", Toast.LENGTH_SHORT).show();
    //    personViewHolder.personPhoto.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
