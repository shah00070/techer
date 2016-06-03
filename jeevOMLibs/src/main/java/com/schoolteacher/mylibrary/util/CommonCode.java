package com.schoolteacher.mylibrary.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.Formatter;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.schoolteacher.mylibrary.model.DataContainer;

public class CommonCode {

    public static byte[] convertAbsolutePathToByteArray(File file) {
        FileInputStream fileInputStream;
        byte[] b = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
            for (int i = 0; i < b.length; i++) {
                System.out.print((char) b[i]);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }

        return b;
    }

    public static String getFileName(String value) {
        int posSlash = value.lastIndexOf('/');
        String fileName = value.substring(posSlash + 1, value.length());
        return fileName;
    }

    public static DataContainer returnDataContainerObject(String result) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // parse json string to object
        DataContainer data = gson.fromJson(result, DataContainer.class);
        return data;
    }

    // make list of strings to comma seprated strings
    public static String toCommaSeparatedString(List<String> strings) {
        StringBuilder stringValue = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {

            if (i == 0) {
                stringValue.append(strings.get(i));
            } else {
                stringValue.append(", " + strings.get(i));
            }
        }

        return stringValue.toString();
    }

    // make list of strings to comma seprated strings
    public static String commaSeparatedString(List<String> strings) {

        StringBuilder commaSepratedValue = new StringBuilder();

        for (int i = 0; i < strings.size(); i++) {

            if (i == 0) {
                commaSepratedValue.append(strings.get(i));
            } else {
                commaSepratedValue.append(", " + strings.get(i));
            }
        }

        return commaSepratedValue.toString();
    }

    // make list of strings to comma seprated strings
    public static String toPipeSeparatedString(List<String> strings) {
        StringBuilder commaSepratedValue = new StringBuilder();

        for (int i = 0; i < strings.size(); i++) {

            if (i == 0) {
                commaSepratedValue.append(strings.get(i));
            } else {
                commaSepratedValue.append("|" + strings.get(i));
            }
        }

        return commaSepratedValue.toString();

    }

    // Validate email
    public static boolean validateEmail(String email) {
        String emailPattern = "^[_\\w-\\+]+(\\.[_\\w-]+)*@([\\w-]+\\.)+[\\w-]{2,}$";

        return email.matches(emailPattern);
    }

    // Validate web url
    public static boolean validateWebUrl(String url) {
        String phonePattern = "(^(http[s]?://)?([w]{3}[.])?([a-z0-9]+[.])+com(((/[a-z0-9]+)*(/[a-z0-9]+/))*([a-z0-9]+[.](html|php|gif|png))?)$)|(^([.]/)?((([a-z0-9]+)/?)+|(([a-z0-9]+)/)+([a-z0-9]+[.](html|php|gif|png)))?$)";

        return url.matches(phonePattern);
    }

    // Validate phone
    public static boolean validatePhone(String phone) {
        String phonePattern = "^[789][0-9]{9}$";

        return phone.matches(phonePattern);
    }

    // year,month,day to milliseconds
    public static long convertToMilliseconds(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        long millisTime = calendar.getTimeInMillis();
        return millisTime;
    }

    // Validate contact number
    public static boolean validateContactNo(String contactNo) {

        String text = contactNo;
        boolean result = false;

        // if ((Pattern.matches("[[0-9]+]+", text) == true)
        // && !(text.contains(" "))) {
        result = (Pattern.matches("^[789][0-9]{9}$", text) == true);

        return result;
    }

    // validate Dob
    public static boolean validateBirthDate(long date) {

        boolean returnValue = false;
        long currentTime = System.currentTimeMillis();

        long differnece = currentTime - date;

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(differnece);
        int mYear = c.get(Calendar.YEAR) - 1970;

        if (mYear >= 18) {
            returnValue = true;
        }
        return returnValue;
    }

    // validate Dob
    public static int birthDate(long date) {

        int returnValue = 0;
        long currentTime = System.currentTimeMillis();

        long differnece = currentTime - date;

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(differnece);
        int mYear = c.get(Calendar.YEAR) - 1970;

        if (mYear >= 18) {
            returnValue = mYear;
        }
        return returnValue;
    }

    // validate Password
    public static boolean validatePassword(String password) {

        String text = password;
        boolean result = false;
        String passwordRegex = "^((?=.*\\d)|(?=.*[A-Z])|(?=.*[#?!@$%^&*-])).{5,20}$";
        result = (Pattern.matches(passwordRegex, text) == true);

        return result;
    }

    public static JSONObject getStatus(String result) {
        JSONObject jsonObj, status = null;
        try {
            jsonObj = new JSONObject(result);
            status = jsonObj.getJSONObject("Status");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean getData(String result) {
        JSONObject jsonObj;
        boolean data = false;
        try {
            jsonObj = new JSONObject(result);
            data = jsonObj.getBoolean("Data");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Integer> getListOfYears() {
        List<Integer> years = new ArrayList<Integer>();
        Calendar calender = Calendar.getInstance();
        int current_year = calender.get(Calendar.YEAR);

        for (int i = 1947; i <= current_year; i++) {
            years.add(i);
        }
        return years;
    }

    public static String[] splitName(String name) {
        String[] firstLastName = name.split("\\s+", 2);

        return firstLastName;
    }

    public static List<String> splitString(String value) {
        List<String> emptyList = new ArrayList<String>();
        if (!CommonCode.isNullOrEmpty(value.trim())) {
            String[] array = value.trim().split("\\s*,\\s*");
            return Arrays.asList(array);
        } else {

            return emptyList;
        }

    }

    public static List<String> splitStringByPipe(String value) {
        List<String> emptyList = new ArrayList<String>();
        if (!CommonCode.isNullOrEmpty(value.trim())) {
            String[] array = value.trim().split("\\|");
            return Arrays.asList(array);
        } else {

            return emptyList;
        }

    }

    public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm");// dd/MM/yyyy
        Date now = new Date();
        String strTime = sdfDate.format(now);
        return strTime;
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static String trimCommas(String value) {

        String finalValue = value;
        if (!CommonCode.isNullOrEmpty(value)) {
            if (finalValue.startsWith(",")) {
                finalValue = finalValue.replaceFirst(",", "");
            }

            if (finalValue.endsWith(","))
                finalValue = finalValue.replaceFirst(",", "");
        }
        return finalValue;
    }

    public static long getDayDifferenceBetweenDates(String currentDateTime,
                                                    String databaseDateTime) {
        long diffDays = 0;
        long diffMinutes = 0;
        // HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        SimpleDateFormat formatDb = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


        Date d1 = null;
        Date d2 = null;

        try {
            d1 = formatDb.parse(databaseDateTime);
            d2 = format.parse(currentDateTime);

            // in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // return diffMinutes;
        return diffDays;

    }

    public static long getYearDifferenceBetweenDates(String currentDateTime,
                                                     String databaseDateTime) {
        long diffDays = 0;
        long diffMinutes = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        // HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat formatFirst = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(databaseDateTime);
            d2 = formatFirst.parse(currentDateTime);

            // in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // return diffMinutes;
        return diffDays / 365;

    }

    public static long getUserAge(String currentDateTime,
                                  String databaseDateTime) {
        long diffDays = 0;
        long diffMinutes = 0;

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        // HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat formatFirst = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(databaseDateTime);
            d2 = formatFirst.parse(currentDateTime);

            // in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // return diffMinutes;
        return diffDays / 365;

    }

    public static long getAge(String databaseDateTime) {
        long diffDays = 0;
        long diffMinutes = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date d1 = null;
        Date d2 = new Date();

        try {
            d1 = format.parse(databaseDateTime);
            // d2 = formatFirst.parse(formatFirst);

            // in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // return diffMinutes;
        return diffDays / 365;

    }


    public static int[] formatDate(String dateValue) throws ParseException {
        int[] dateMonthYear = new int[3];
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date parse = sdf.parse(dateValue);
        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        System.out.println(c.get(Calendar.MONTH) + c.get(Calendar.DAY_OF_MONTH)
                + c.get(Calendar.YEAR));
        dateMonthYear[0] = c.get(Calendar.DAY_OF_MONTH);
        dateMonthYear[1] = c.get(Calendar.MONTH);
        dateMonthYear[2] = c.get(Calendar.YEAR);
        return dateMonthYear;
    }

    public static int[] formatTime(String dateValue) throws ParseException {
        int[] dateMonthYear = new int[3];
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date parse = sdf.parse(dateValue);
        Calendar c = Calendar.getInstance();
        c.setTime(parse);

        dateMonthYear[0] = c.get(Calendar.HOUR_OF_DAY);
        dateMonthYear[1] = c.get(Calendar.MINUTE);
        dateMonthYear[2] = c.get(Calendar.SECOND);
        return dateMonthYear;
    }

    // Add User Json Method
    public static JSONObject addUserJson(String email, String phone) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("Email", email);
            jsonObject.accumulate("CellNumber", phone);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    // split string

    public static String makeCallBackEmailName(String name) {
        String firstName = null;
        char lastName = 0;
        String[] array = name.split("\\s+");
        for (int x = 0; x < array.length; x++) {
            array[x] = array[x].trim();
        }
        String nameValue = new String();

        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                if (i == 0) {
                    firstName = array[i];

                } else
                    lastName = array[1].charAt(0);
            }

        }
        return firstName + " " + lastName;
    }

    public static int[] formatDT(String dateValue) throws ParseException {
        int[] dateMonthYear = new int[3];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date parse = sdf.parse(dateValue);
        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        System.out.println(c.get(Calendar.MONTH) + c.get(Calendar.DAY_OF_MONTH)
                + c.get(Calendar.YEAR));
        dateMonthYear[0] = c.get(Calendar.DAY_OF_MONTH);
        dateMonthYear[1] = c.get(Calendar.MONTH);
        dateMonthYear[2] = c.get(Calendar.YEAR);
        return dateMonthYear;
    }

    public static String[] extractDateTime(String dateTime) {

        String[] dateTimeArray = new String[2];

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date d;
        try {
            d = format.parse(dateTime);

            dateTimeArray[0] = String.valueOf(d.getTime());
            dateTimeArray[1] = String.valueOf(d.getDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
        // return diffMinutes;
        return dateTimeArray;

    }

    public static String currentTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");// dd/MM/yyyy
        Date now = new Date();
        String strTime = sdfDate.format(now);
        return strTime;
    }

    public static void saveImageBitmap(Context context, Bitmap b, String name) {

        FileOutputStream out;
        try {
            out = context.openFileOutput(name, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap retriveImageBitmap(Context context, String name) {
        try {
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean onlyAlphabets(String str) {
        String pattern = "^[a-zA-Z\\s]*$";
        return str.matches(pattern);
    }

    public static int compareDate(String d1, String d2) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-ddF-yyyy");
        Date date1 = null;
        Date date2 = null;
        int i = 0;
        try {
            date1 = sdf.parse(d1);
            date2 = sdf.parse(d2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(sdf.format(date1));
        System.out.println(sdf.format(date2));

        if (date1.compareTo(date2) > 0) {
            i = 1;
        } else if (date1.compareTo(date2) < 0) {
            i = -1;
        } else if (date1.compareTo(date2) == 0) {
            i = 0;
        }
        return i;
    }

    // get Year & Month
    public static String monthYear(int day, int mon, int year) {
        String month = null;
        switch (mon) {
            case 0:
                month = "Jan";
                break;
            case 1:
                month = "Feb";
                break;
            case 2:
                month = "Mar";
                break;
            case 3:
                month = "Apr";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "Jun";
                break;
            case 6:
                month = "Jul";
                break;
            case 7:
                month = "Aug";
                break;
            case 8:
                month = "Sept";
                break;
            case 9:
                month = "Oct";
                break;

            case 10:
                month = "Nov";
                break;
            case 11:
                month = "Dec";
                break;

        }
        return day + "-" + month + "-" + year;

    }

    // get Year & Month
    public static String serviceRequestDate(int day, int mon, int year) {
        String month = null;
        switch (mon) {
            case 0:
                month = "Jan";
                break;
            case 1:
                month = "Feb";
                break;
            case 2:
                month = "Mar";
                break;
            case 3:
                month = "Apr";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "Jun";
                break;
            case 6:
                month = "Jul";
                break;
            case 7:
                month = "Aug";
                break;
            case 8:
                month = "Sept";
                break;
            case 9:
                month = "Oct";
                break;

            case 10:
                month = "Nov";
                break;
            case 11:
                month = "Dec";
                break;

        }
        return day + "\n" + month;

    }

    public static Bitmap getBitmapFromURL(String src) {

        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isNullOrEmpty(String value) {
        return !(value != null && value.length() != 0);
    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress
                                .hashCode());

                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("ip", ex.toString());
        }
        return null;
    }

    public static String getDateFromTimeStamp(String datetime) {
        String dateValue = null;
        try {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date d = f.parse(datetime);
            DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat time = new SimpleDateFormat("hh:mm:ss a");
            dateValue = date.format(d);
            // System.out.println("Date: " + date.format(d));
            // System.out.println("Time: " + time.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateValue;
    }

    public static String getTimeFromTimeStamp(String datetime) {
        String timeValue = null;
        try {
            DateFormat f = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss");
            Date dte = f.parse(datetime);
            DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat time = new SimpleDateFormat("hh:mm:ss a");
            timeValue = time.format(dte);

            SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a");
            dte = df.parse(timeValue);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dte);
            // cal.add(Calendar.MINUTE, 30);
            // cal.add(Calendar.HOUR, 5);
            timeValue = df.format(cal.getTime());

            // System.out.println("Date: " + date.format(d));
            // System.out.println("Time: " + time.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeValue;
    }


    public static boolean checkForFutureDate(int year, int month, int day) {
        boolean isFutureDate;
        String date = year + "/" + month + "/" + day;
        java.util.Date utilDate = null;
        java.util.Date currentDate = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            utilDate = formatter.parse(date);

        } catch (ParseException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        //create a date one day after current date
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int currentMonth = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int currentDay = now.get(Calendar.DAY_OF_MONTH);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            currentDate = formatter.parse(currentYear + "/" + currentMonth + "/" + currentDay);

        } catch (ParseException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

//        long nextDay = System.currentTimeMillis() + 1000 * 60 * 60 * 24;
//        //create date object
//        Date next = new Date(nextDay);
        //compare both dates
//        if (utilDate.after(currentDate)) {
//            return true;
//
//        } else {
//            return false;
//
//        }


        if (year > currentYear)
            isFutureDate = true;

        else if (month > currentMonth)
            isFutureDate = true;

        else if (day > currentDay && month == currentMonth)
            isFutureDate = true;
        else isFutureDate = false;

        return isFutureDate;

    }

    public static String cmsToFeet(String height) {
        Double heightInCms = (Double.valueOf(height)) / 2.54;
        String value = String.valueOf(heightInCms.intValue() / 12);
        return value;

    }

    public static String cmsToInch(String height) {
        Double heightInCms = (double) Math
                .round(((Double.valueOf(height)) / 2.54));
        String value = String.valueOf(heightInCms.intValue() % 12);
        return value;

    }
}
