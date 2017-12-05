package com.charpixel.baseandroidproject.common;

/**
 * Created by aapple on 02/09/17.
 */

public class TextUtils {

    public static boolean isBlank(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static boolean hasAnyPrefix(String number, String... prefixes) {
        if (number == null) {
            return false;
        }

        for (String prefix : prefixes) {
            if (number.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static String nullIfBlank(String value) {
        if (isBlank(value)) {
            return null;
        }
        return value;
    }


    public static boolean isBlank(Object object){
        return  object==null;
    }




    public static String captalizeWords(String value){
        String finalValue="";
        value=value.trim();

        if(value.length()>0){

            try {
                String[] strArr = value.split(" ");
                for (String str : strArr) {
                    char[] stringArray = str.trim().toCharArray();
                    stringArray[0] = Character.toUpperCase(stringArray[0]);
                    str = new String(stringArray);

                    finalValue=finalValue+str+" ";

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return finalValue.trim();
    }



    public static double ConvertMeterToMile(double meter){
        if(meter==0){
            return  0;
        }
        else {
            return meter/1609.433;
        }

    }
    public static double ConvertMileToMeter(double mile){

        if (mile==0){
            return 0;
        }
        else {
            return    mile * 1609.344;
        }
    }
}
