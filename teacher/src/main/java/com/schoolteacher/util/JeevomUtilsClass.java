package com.schoolteacher.util;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.schoolteacher.appointment.AppData.Days;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class JeevomUtilsClass {

    public static final int DEFAULT_RADIUS = 0;

    public static final double DEFAULT_LATITUDE = 28.631462799999998;
    public static final double DEFAULT_LONGITUDE = 77.3836299;
    public static boolean isFreshLoad = true;

    public static int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                // Log.d(DEBUG_TAG, "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    public static String milliToDate(long timeInMilliSeconds) {
        // TODO Auto-generated method stub
        String time = null;
        long seconds = timeInMilliSeconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long week = days / 7;
        long month = days / 30;

        if (week == 0) {
            if (days == 0) {
                if (hours == 0) {
                    if (minutes == 0) {
                        time = seconds % 60 + "s ";
                    } else {
                        time = minutes % 60 + "m " + " " + seconds % 60 + "s ";
                    }
                } else {
                    time = hours % 24 + "h " + " " + minutes % 60 + "m ";
                }
                // time = hours % 24 + "h " + ":" + minutes % 60 + "m " + ":"
                // + seconds % 60 + "s ";
            } else {
                // time = days + "d " + ":" + hours % 24 + "h " + ":" + minutes
                // % 60
                // + "m " + ":" + seconds % 60 + "s ";
                time = days + "d " + " " + hours % 24 + "h " + minutes % 60
                        + "m ";

            }
        } else {
            time = week + "w " + days % 7 + "d " + " " + hours % 24 + "h ";
        }
        return time;

    }

    public static String milliToMin(long timeInMilliSeconds) {
        // TODO Auto-generated method stub
        String time;
        long seconds = timeInMilliSeconds / 1000;
        long minutes = seconds / 60;
        time = minutes % 60 + "m " + ":" + seconds % 60 + "s ";
        return time;

    }

    public static long dateToMillis(String timeStamp) {
        String input = timeStamp;
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MMM-yyyy h:mm a", Locale.ENGLISH)
                    .parse(input);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long milliseconds = date.getTime();
        long millisecondsFromNow = milliseconds - (new Date()).getTime();
        return millisecondsFromNow;
    }

    public static String[] formatDateTime(String date) {
        String[] time = date.split("T");

        DateFormat inputTime = new SimpleDateFormat("HH:mm:ss");
        DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputDate = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat outputTime = new SimpleDateFormat("KK:mm a");
        String Ftime = null;
        String FDate = null;
        try {
            FDate = outputDate.format(inputDate.parse(date));
            Ftime = outputTime.format(inputTime.parse(time[1]));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Ftime = Ftime.replace("am", "AM").replace("pm", "PM");
        String[] formatedDateTime = {FDate, Ftime};
        return formatedDateTime;

    }

    public static String formatTime(String time) {

        DateFormat inputTime = new SimpleDateFormat("HH:mm:ss");
        DateFormat outputTime = new SimpleDateFormat("h:mm a");
        String Ftime = null;
        try {

            Ftime = outputTime.format(inputTime.parse(time));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return Ftime.replace("am", "AM").replace("pm", "PM");
        return Ftime;
    }

    public static String formatDate(String date) {

        DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputDate = new SimpleDateFormat("dd-MMM-yyyy");
        String FDate = null;
        try {
            FDate = outputDate.format(inputDate.parse(date));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return FDate;
    }

    public static void rotate6to12ClockWise(ImageView view) {
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(view, "rotation",
                180);
        animation1.setDuration(500);
        animation1.start();
    }

    public static void rotate12to6ClockWise(ImageView view) {
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(view, "rotation", 0);
        animation1.setDuration(500);
        animation1.start();
    }

    public static void autoScroll(final ScrollView scrollView) {
        int maxScrollY = scrollView.getChildAt(0).getMeasuredHeight()
                - scrollView.getMeasuredHeight();
        if (maxScrollY < scrollView.getBottom()) {
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
        }
    }

    public static String formatToYesterdayOrToday(String date)
            throws ParseException {
        // Date dateTime = new SimpleDateFormat("EEE hh:mma MMM d, yyyy")
        // .parse(date);
        Date dateTime = new SimpleDateFormat("dd-MMM-yyyy h:mm a",
                Locale.ENGLISH).parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        Calendar today = Calendar.getInstance();
        Calendar yesterday = Calendar.getInstance();
        Calendar tommorrow = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        tommorrow.add(Calendar.DATE, +1);
        DateFormat timeFormatter = new SimpleDateFormat("hh:mm a");

        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) == today
                .get(Calendar.DAY_OF_YEAR)) {
            return "Today, " + timeFormatter.format(dateTime);
        } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) == yesterday
                .get(Calendar.DAY_OF_YEAR)) {
            return "Yesterday, " + timeFormatter.format(dateTime);
        } else if (calendar.get(Calendar.YEAR) == tommorrow.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) == tommorrow
                .get(Calendar.DAY_OF_YEAR)) {
            return "Tommorrow, " + timeFormatter.format(dateTime);
        } else {
            return date;
        }
    }

    public static Days formatToday(String date) throws ParseException {
        Date dateTime = new SimpleDateFormat("dd-MMM-yyyy h:mm a",
                Locale.ENGLISH).parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        Calendar today = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) == today
                .get(Calendar.DAY_OF_YEAR)) {
            return Days.TODAY;

        }
        return Days.VOID;
    }

    public static String getDocumentContent(String realPathFromURI) {
        if (realPathFromURI != null) {
            try {
                Bitmap bitmapOrg = BitmapFactory.decodeFile(realPathFromURI);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                // Resize the image
                double width = bitmapOrg.getWidth();
                double height = bitmapOrg.getHeight();
                double ratio = 400 / width;
                int newheight = (int) (ratio * height);
                bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, 400,
                        newheight, true);
                // Here you can define .PNG as well
                bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 95, bao);
                byte[] ba = bao.toByteArray();
                String ba1 = Base64.encodeToString(ba, 0);
                return ba1;
            } catch (Exception e) {
            }
        }
        return null;
    }
}
