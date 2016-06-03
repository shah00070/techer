package com.schoolteacher.main;

public class Person {
    String name;
    String age;
    int photoId;
    String time;
    String content;

    String rec;

    Person(String name, String content,String age, String time,int photoId,String recname) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
this.content=content;
    this.time=time;

        this.rec=recname;
    }
}