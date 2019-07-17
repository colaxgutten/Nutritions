package com.example.nutritions;

import java.util.ArrayList;
import java.util.Calendar;

public class Utility {

    public static final int[] monthsWith31Days = {0,2,4,6,7,9,11};
    public static final int[] monthsWith30Days = {3,5,8,10};
    public static final int february = 1;

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String date="";
        date+=calendar.get(Calendar.DAY_OF_MONTH);
        date+="-"+(calendar.get(Calendar.MONTH)+1);
        date+="-"+calendar.get(Calendar.YEAR);
        return date;
    }

    public static ArrayList<String> getPast7Days(){
        ArrayList<String> past7 = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0;i<7;i++){
            String s = "";
            if (i==0) {
                s = getCurrentDate();
            }
            else {
                past7.add(getDateMinusxDays(i));
            }
            past7.add(s);
        }
        return past7;
    }

    public static String getDateMinusxDays(int x){
        Calendar calendar = Calendar.getInstance();
        String date = "";
        int finalDay =0;
        int finalMonth=0;
        int finalYear=0;
        //1st day of month = 1
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
        //first month of year = 0
        int monthOfYear = calendar.get(calendar.MONTH);
        int year = calendar.get(calendar.YEAR);
        finalDay = dayOfMonth-x;
        if (finalDay<=0){
            //we have to get previous month
            finalMonth = monthOfYear-1;
            if (monthOfYear<0){
                //we have to get previous year
                finalYear=year-1;
                monthOfYear=monthOfYear%12;
            } else {
                finalYear = year;
            }
            finalDay+=getDaysInMonth(finalMonth);
        } else {
            finalYear = year;
            finalMonth = monthOfYear;
        }
        date+=finalDay;
        date+="-"+(finalMonth+1);
        date+="-"+finalYear;
        return date;
    }
    //returns how many days in Month
    public static int getDaysInMonth(int month){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (month==february) {
            //this is a leap year according to the gregorian calendar
            if (year%4 == 0 && (year%100!=0 || year%400==0))
                return 29;
            return 28;
        }
        for (int i = 0;i<monthsWith31Days.length;i++){
            if (month==monthsWith31Days[i])
                return 31;
        }
        for (int i =0;i<monthsWith30Days.length;i++){
            if (month==monthsWith30Days[i])
                return 30;
        }
        return 0;
    }
}
