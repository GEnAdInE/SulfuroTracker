package com.sulfuro.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Time {
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    public Time()
    {
        calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    public String getDate()
    {
        String strMonth = new SimpleDateFormat("MMM", Locale.US).format(month);
        String prefix;
        switch (day)
        {
            case 1: prefix = "st ";
            case 2: prefix = "nd ";
            case 3: prefix = "rd ";
            default: prefix = "th ";
        }

        StringBuilder str = new StringBuilder();
        str.append(strMonth).append(" ").append(day).append(prefix).append(", ").append(year);
        return str.toString();

    }
    public String getTime()
    {
        return timeToString(hour,minute);
    }

    public String getRoundedTime()
    {
        int h = hour;
        int m = minute;

        if(m<8)
        {
            m=0;
        }
        if(m>8 && m<23)
        {
            m=15;
        }
        if(m>22 && m<38)
        {
            m=30;
        }
        if(m>37 && m<53)
        {
            m=45;
        }
        if(m>52 && m<60)
        {
            if(h<23)
            {
                h++;
            }
            else
            {
                h=0;
            }

            m=0;
        }
        return timeToString(h,m);

    }


    public String timeToString(int h,int m)
    {
        StringBuilder str = new StringBuilder();
        if(h<10)
            str.append("0");

        str.append(h).append(':');
        if(m<10)
            str.append("0");
        str.append(m);
        return str.toString();
    }

}
