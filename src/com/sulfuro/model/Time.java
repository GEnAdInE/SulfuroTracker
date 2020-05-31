package com.sulfuro.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Custom class time
 */
public class Time implements Serializable {
    private Calendar calendar;


    /**
     * Time constructor , initialize all value
     */
    public Time()
    {
        calendar = new GregorianCalendar();
    }
    public Time(int h,int m)
    {
        calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY,h);
        calendar.set(Calendar.MINUTE,m);
    }

    /**
     * Get the current date
     * @return string version of the date with (st,nd,rd,th)
     */
    public String getDate()
    {
        String strMonth = new SimpleDateFormat("MMM", Locale.US).format(calendar.get(Calendar.MONTH));
        String prefix;
        switch (calendar.get(Calendar.DAY_OF_MONTH))
        {
            case 1: prefix = "st ";
            case 2: prefix = "nd ";
            case 3: prefix = "rd ";
            default: prefix = "th ";
        }

        StringBuilder str = new StringBuilder();
        str.append(strMonth).append(" ").append(getDay()).append(prefix).append(", ").append(getYear());
        return str.toString();

    }
    /**
     * Get the current time
     * @return string of the time
     * @exemple 10:35
     */
    public String getTime()
    {
        return TimeToString(this);
    }
    /**
     * Fully Rounded time to get send to the server
     * @return array of time [year|month|day|hour|minute]
     */
    public ArrayList<Integer> getFullyRoundedTime()
    {
        ArrayList<Integer> arrayTime = new ArrayList<Integer>();
        Time c = getRoundedTime();
        arrayTime.add(c.getYear());
        arrayTime.add(c.getMonth());
        arrayTime.add(c.getDay());
        arrayTime.add(c.getHour());
        arrayTime.add(c.getMinute());
        return arrayTime;

    }
    /**
     * Calculate the rounded time to 15min into a new Calendar
     * @return Time of the rounded time
     */
    public Time getRoundedTime()
    {
        Time result = new Time();
        int mod = result.getMinute() % 15;
        result.getCalendar().add(Calendar.MINUTE,mod < 8 ? -mod : (15-mod));
        return result;

    }

    /**
     * Convert hours and minute to a string but with a calendar
     * @param t Calendar of the time to be converted to string
     * @return String hours:minute
     * @exemple timeToString(calendar) -> 10h05
     */
    static public String TimeToString(Time t)
    {
        StringBuilder str = new StringBuilder();
        if(t.getHour()<10)
            str.append("0");
        str.append(t.getHour()).append(':');
        if(t.getMinute()<10)
            str.append("0");
        str.append(t.getMinute());
        return str.toString();

    }

    /**
     * set a specific hour
     * @param h
     */
    public void setHour(int h)
    {
        if(h>=0)
        {
            calendar.set(Calendar.HOUR_OF_DAY,h);
        }
    }

    /**
     * set a specific minute
     * @param m
     */
    public void setMinute(int m)
    {
        if(m>=0) {
            calendar.set(Calendar.MINUTE, m);
        }
    }



    public static Time Substraction(Time t0 , Time t1)
    {
        Time result = new Time();
        result.getCalendar().setTimeInMillis(t0.getCalendar().getTimeInMillis() - t1.getCalendar().getTimeInMillis());
        return result;
    }

    public int getDay()
    {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public int getMonth()
    {
        return calendar.get(Calendar.MONTH);
    }

    public int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute()
    {
        return calendar.get(Calendar.MINUTE);
    }

    public int getYear()
    {
        return calendar.get(Calendar.YEAR);
    }

    public Calendar getCalendar()
    {
        return calendar;
    }

}
