package com.schoolteacher.main;

/**
 * Created by chandan on 5/5/16.
 */
public class confiPerson {

    String name;
    String age;
    int photoId;
    String time;
    String content;

    confiPerson(String name, String content,String age, String time,int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.content=content;
        this.time=time;
    }
}
