package com.chalenge.exchangerate.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

    private static final String AAAAMMJJ_FORMAT = "yyyy-MM-dd";
    private static final String JJMMAAAA_FORMAT = "dd-MM-yyyy";
    private static final int NUMBER_OF_MONTHS = 2;

    private DateUtils(){/*Utility class that contains only static methods doesn't need constructor*/}


    public static String getCurrentDateAAAAMMJJ(){
        SimpleDateFormat formatter = new SimpleDateFormat(AAAAMMJJ_FORMAT,Locale.US);
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getTwoMonthsEarlierDateAAAAMMJJ(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, - NUMBER_OF_MONTHS);
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(AAAAMMJJ_FORMAT,Locale.US);
        return formatter.format(date);
    }


    public static long fromISO8601UTC(@NonNull String dateStr) {
        DateFormat df = new SimpleDateFormat(AAAAMMJJ_FORMAT,Locale.US);
        try {
            Date date = df.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            Log.e("DateUtils","Error parsing date " + e);
        }
        return -1;
    }

    public static String fromStrMillisToAAAAMMJJ(@NonNull double dateStr) {
        long unixTimeStamp = (long)dateStr;
        Date date = new Date(unixTimeStamp);
        DateFormat df = new SimpleDateFormat(JJMMAAAA_FORMAT,Locale.US);
        return df.format(date);
    }
}
