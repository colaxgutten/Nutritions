package com.example.nutritions;

import java.util.Calendar;

public class CurrentDate {
    /*
    returns current date in format d-m-y
     */
    public String getCurrentDate(){
        String date="";
        Calendar calendar = Calendar.getInstance();
        date+=calendar.get(Calendar.DAY_OF_MONTH);
        date+="-"+(calendar.get(Calendar.MONTH)+1);
        date+="-"+calendar.get(Calendar.YEAR);
        return date;
    }
}
