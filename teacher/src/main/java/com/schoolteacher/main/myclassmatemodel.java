package com.schoolteacher.main;

/**
 * Created by perveen akhtar on 5/10/2016.
 */
public class myclassmatemodel {


    private String myclassmatename, myclassmaterollno;


        public myclassmatemodel() {
        }

        public myclassmatemodel(String myclassmatename, String myclassmaterollno) {
            this.myclassmatename = myclassmatename;
            this.myclassmaterollno = myclassmaterollno;
        }

        public String getMyclassmatename() {
            return myclassmatename;
        }

        public void setMyclassmatename(String myclassmatename) {
            this.myclassmatename = myclassmatename;
        }

        public String getMyclassmaterollno() {
            return myclassmaterollno;
        }

        public void setMyclassmaterollno(String myclassmaterollno) {
            this.myclassmaterollno = myclassmaterollno;
        }

}
