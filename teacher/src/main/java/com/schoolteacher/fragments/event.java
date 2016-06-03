package com.schoolteacher.fragments;



/**
 * Created by chandan on 9/5/16.
 */
public class event  {
    String name;
    String age;
    int photoId;
    String time;
    String content;
    String del;
    String rec;

    event(String name, String content,String age, String time,int photoId,String deli,String recname) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.content=content;
        this.time=time;

        this.del=deli;
        this.rec=recname;
    }
}