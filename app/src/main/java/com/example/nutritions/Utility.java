package com.example.nutritions;

import java.util.Calendar;

public class Utility {
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        String date="";
        date+=calendar.get(Calendar.DAY_OF_MONTH);
        date+="-"+(calendar.get(Calendar.MONTH)+1);
        date+="-"+calendar.get(Calendar.YEAR);
        return date;
    }
}
