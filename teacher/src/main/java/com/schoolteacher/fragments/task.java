package com.schoolteacher.fragments;

public class task {
    String name;
    String age;
    int photoId;
    String time;
    String content;
    String del;
    String rec;

    task(String name, String content, String age, String time, int photoId, String deli, String recname) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
this.content=content;
    this.time=time;

        this.del=deli;
        this.rec=recname;
    }
}