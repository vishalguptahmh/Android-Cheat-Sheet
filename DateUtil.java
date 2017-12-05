package com.charpixel.baseandroidproject.common;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.charpixel.baseandroidproject.Constants.TAG;

/**
 * Created by aapple on 04/09/17.
 */

public class DateUtil {



//    input 1755 ;  output : 12:56 Am
    public static String ConvertMilitaryToRegular(String timeInMilitary){
        int milTime = Integer.parseInt(timeInMilitary);
        if(milTime<0){
            milTime=milTime*(-1);
        }
            // Convert the int to a string ensuring its 4 characters wide, parse as a date
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hhmm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            date = simpleDateFormat.parse(String.format("%04d", milTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
            // Set format: print the hours and minutes of the date, with AM or PM at the end
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            // Output: 12:56 AM
        return sdf.format(date);
    }

    //    input 1755 ;  output : 12:56 Am
    public static String ConvertMilitaryToRegular(int timeInMilitary){
        int milTime = timeInMilitary;
        if(milTime<0){
            milTime=milTime*(-1);
        }
        // Convert the int to a string ensuring its 4 characters wide, parse as a date
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hhmm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            date = simpleDateFormat.parse(String.format("%04d", milTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Set format: print the hours and minutes of the date, with AM or PM at the end
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        // Output: 12:56 AM
        return sdf.format(date);
    }

    //    input 1755 ;  Output: 1 hr 5 min
    public static String DifferenceMilitaryInHrAndMin(int timeInMilitary){
        int milTime = timeInMilitary;
        if(milTime<0){
            milTime=milTime*(-1);
        }
        // Convert the int to a string ensuring its 4 characters wide, parse as a date
        Date date = new Date();
        try {
            date = new SimpleDateFormat("hhmm").parse(String.format("%04d", milTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Set format: print the hours and minutes of the date, with AM or PM at the end
        SimpleDateFormat sdfHr = new SimpleDateFormat("hh");
        SimpleDateFormat sdfMin = new SimpleDateFormat("mm");

        if(sdfMin.format(date).equals("00")){
            return  sdfHr.format(date)+" hr ";
        }
        else {

            return (sdfHr.format(date)+" hr " +sdfMin.format(date)+" min");
        }
        // Output: 1 hr 5 min
    }



    public static  long MilitaryToMilliSeconds(int militaryTime,Date date1){
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat sdfHr = new SimpleDateFormat("hh");
        SimpleDateFormat sdfMin = new SimpleDateFormat("mm");
        SimpleDateFormat sdfyear = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd");

        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hhmm");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            date = simpleDateFormat.parse(String.format("%04d", militaryTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Log.d(TAG, "MilitaryToMilliSeconds: \n"+

            "year : "+Integer.valueOf(sdfyear.format(date1))+
             "\nMonth : "+  Integer.valueOf(sdfMonth.format(date1))+
                "\nDate : "+Integer.valueOf(sdfDate.format( date1))+
                "\nhr : "+ Integer.valueOf(sdfHr.format(date))+
                "\nmm: "+ Integer.valueOf(sdfMin.format(date))


        );


        /* month is putting -1 here */

        beginTime.set(Integer.valueOf(sdfyear.format(date1)),
                Integer.valueOf(sdfMonth.format(date1))-1,
                Integer.valueOf(sdfDate.format( date1)),
                Integer.valueOf(sdfHr.format(date)),
                Integer.valueOf(sdfMin.format(date)));


        long begintime=beginTime.getTimeInMillis();
        TimeZone timeZone=beginTime.getTimeZone();

        return beginTime.getTimeInMillis();
    }



 /*     ==== ==== ====    get 12-09-2017       === === ===     */

    public  static String getDate_Month_Year(Date date){
        Calendar c = Calendar.getInstance();
      if( date == null ) {
           date =  new Date() ;
          date.setYear(date.getYear()-1);

       }


        c.setTime(date);

        Calendar beginTime = Calendar.getInstance();
        SimpleDateFormat sdfyear = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd");

        return sdfDate.format(date)+"-"+sdfMonth.format(date)+"-"+sdfyear.format(date);
    }




    //get thu,sep 07 ,2017

    public static String  getDay_Month_date_Year(Date date){


        if(date==null){
            date=new Date();
        }

        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getDefault());
        c.setTime(date);

        c.setTimeZone(TimeZone.getDefault());
        String s = new SimpleDateFormat("MMM").format(date);

      String ss= getDayText(c.get(Calendar.DAY_OF_WEEK))+", "+c.get(Calendar.DAY_OF_MONTH)+" "+s+", "+c.get(Calendar.YEAR);
      return ss;

    }


    public static String getDayText(int dayOfWeek)
    {


        if (Calendar.MONDAY == dayOfWeek) {
            return  "Mon";
        } else if (Calendar.TUESDAY == dayOfWeek) {
            return  "Tue";
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            return  "Wed";
        } else if (Calendar.THURSDAY == dayOfWeek) {
            return  "Thu";
        } else if (Calendar.FRIDAY == dayOfWeek) {
            return  "Fri";
        } else if (Calendar.SATURDAY == dayOfWeek) {
            return  "Sat";
        } else if (Calendar.SUNDAY == dayOfWeek) {
            return  "Sun";
        }

        return "Day";

    }


    public static String getDayAgoTime(String yyy_dd_MMTO_HH_MM_Z)
    {
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = utcFormat.parse(yyy_dd_MMTO_HH_MM_Z);
        long delta=date.getTime();
        long difference=0;
        Long mDate = java.lang.System.currentTimeMillis();

        if(mDate > delta)
        {
            difference= mDate - delta;
            final long seconds = difference/1000;
            final long minutes = seconds/60;
            final long hours = minutes/60;
            final long days = hours/24;
            final long months = days/31;
            final long years = days/365;

            if (seconds < 0)
            {
                return "not yet";
            }
            else if (seconds < 60)
            {
                return seconds == 1 ? "one second ago" : seconds + " seconds ago";
            }
            else if (seconds < 120)
            {
                return "a minute ago";
            }
            else if (seconds < 2700) // 45 * 60
            {
                return minutes + " minutes ago";
            }
            else if (seconds < 5400) // 90 * 60
            {
                return "an hour ago";
            }
            else if (seconds < 86400) // 24 * 60 * 60
            {
                return hours + " hours ago";
            }
            else if (seconds < 172800) // 48 * 60 * 60
            {
                return "yesterday";
            }
            else if (seconds < 2592000) // 30 * 24 * 60 * 60
            {
                return days + " days ago";
            }
            else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
            {

                return months <= 1 ? "one month ago" : days + " months ago";
            }
            else
            {

                return years <= 1 ? "one year ago" : years + " years ago";
            }
        }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




    public static Integer getOffset(){

        TimeZone timeZone=TimeZone.getDefault();
        Date date=new Date();
        int offsetFromUtc = (timeZone.getOffset(date.getTime())) / (1000*60);

        return offsetFromUtc;
    }


    public static boolean isDateLessThanCurrentDate(Date date){

        if(date==null){
            return false;
        }
        else {
            Log.d(TAG, "dateLessThanCurrentDate: cureent Date : "+(new Date()));
            Log.d(TAG, "dateLessThanCurrentDate: argument Date : "+date);

            Date currentDate=new Date();
            if(currentDate.after(date)){
                Log.d(TAG, "isDateLessThanCurrentDate: current date is after enterend date");

               SimpleDateFormat simpleDateFormat= new SimpleDateFormat("HHmm");
               SimpleDateFormat dates=new SimpleDateFormat("dd");

                Log.d(TAG, "isDateLessThanCurrentDate: current date :"+dates.format(currentDate));
                Log.d(TAG, "isDateLessThanCurrentDate: entered date :"+dates.format(date));

                Log.d(TAG, "isDateLessThanCurrentDate: current time : "+Integer.valueOf(simpleDateFormat.format(currentDate)));
                Log.d(TAG, "isDateLessThanCurrentDate: time entered : "+Integer.valueOf(simpleDateFormat.format(date)));
               if(Integer.valueOf(simpleDateFormat.format(currentDate))>Integer.valueOf(simpleDateFormat.format(date))){
                   Log.d(TAG, "isDateLessThanCurrentDate: return value : false");
                   return false;
               }
               else {
                   Log.d(TAG, "isDateLessThanCurrentDate: return value : true");

                   return true;
               }

            }
            else {
                Log.d(TAG, "isDateLessThanCurrentDate: current date is before enterend date");

                return false;
            }
        }

    }



}
