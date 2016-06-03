package com.schoolteacher.main;

/**
 * Created by perveen akhtar on 5/10/2016.
 */
public class myteachersmodel {


    private String myteachername, myteachersubject;
    private int userimg;


    public myteachersmodel(String teacherone, String s, int s1) {
    }

    public myteachersmodel(String myteachername, String myteachersubject) {
        this.myteachername = myteachername;
        this.myteachersubject = myteachersubject;
    }

    public String getMyteachername() {
        return myteachername;
    }

    public void setMyteachername(String myteachername) {
        this.myteachername = myteachername;
    }

    public String getMyteachersubject() {
        return myteachersubject;
    }

    public void setMyteachersubject(String myteachersubject) {
        this.myteachersubject = myteachersubject;
    }

    public int getUserimg() {
        return userimg;
    }

    public void setUserimg(int userimg) {
        this.userimg = userimg;
    }


}
