package com.sulfuro.model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Custom class time
 */
public class Time {
    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * Time constructor , initialize all value
     */
    public Time()
    {
        calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    /**
     * Get the current date
     * @return string version of the date with (st,nd,rd,th)
     */
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

    /**
     * Get the current time
     * @return string of the time
     * @exemple 10:35
     */
    public String getTime()
    {
        return timeToString(calendar);
    }

    /**
     * Fully Rounded time to get send to the server
     * @return array of time [year|month|day|hour|minute]
     */
    public ArrayList<Integer> getFullyRoundedTime()
    {
        ArrayList<Integer> arrayTime = new ArrayList<Integer>();
        Calendar c = getRoundedTime();
        arrayTime.add(c.get(Calendar.YEAR));
        arrayTime.add(c.get(Calendar.MONTH));
        arrayTime.add(c.get(Calendar.DAY_OF_MONTH));
        arrayTime.add(c.get(Calendar.HOUR_OF_DAY));
        arrayTime.add(c.get(Calendar.MINUTE));
        return arrayTime;

    }

    /**
     * Calculate the rounded time to 15min into a new Calendar
     * @return Calendar of the rounded time
     */
    public Calendar getRoundedTime()
    {
        Calendar c = calendar;

        int h = hour;
        int m = minute;

        if(m<8)
        {
            c.set(Calendar.MINUTE,0);
        }
        if(m>8 && m<23)
        {
            c.set(Calendar.MINUTE,15);
        }
        if(m>22 && m<38)
        {
            c.set(Calendar.MINUTE,030);
        }
        if(m>37 && m<53)
        {
            c.set(Calendar.MINUTE,45);
        }
        if(m>52 && m<60)
        {
            if(h<23)
            {
                c.add(Calendar.HOUR_OF_DAY,1);
            }
            else
            {
                c.set(Calendar.HOUR_OF_DAY,0);
                c.add(Calendar.DAY_OF_MONTH,1);
                //!!! day++
            }

            c.set(Calendar.MINUTE,0);
        }
        return c;
    }




    /**
     * Convert hours and minute to a string but with a calendar
     * @param c Calendar of the time to be converted to string
     * @return String hours:minute
     * @exemple timeToString(calendar) -> 10h05
     */
    public String timeToString(Calendar c)
    {
        StringBuilder str = new StringBuilder();
        if(c.get(Calendar.HOUR_OF_DAY)<10)
            str.append("0");
        str.append(c.get(Calendar.HOUR_OF_DAY)).append(':');
        if(c.get(Calendar.MINUTE)<10)
            str.append("0");
        str.append(c.get(Calendar.MINUTE));
        return str.toString();

    }



}
