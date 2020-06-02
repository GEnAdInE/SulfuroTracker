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
    private Boolean negativeTime;


    /**
     * Time constructor , initialize all value
     */
    public Time()
    {
        calendar = new GregorianCalendar();
        negativeTime = false;
    }

    /**
     * Time constructor with specific hours and minuyte
     * @param h hours
     * @param m minute
     */
    public Time(int h,int m)
    {
        calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY,h);
        calendar.set(Calendar.MINUTE,m);
        negativeTime = false;
    }

    /**
     * Set the current Time to 0
     */
    public void emptyTime()
    {
        calendar.set(Calendar.YEAR,0);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
    }

    /**
     * Get the current date
     * @return string version of the date with (st,nd,rd,th)
     */
    public String getDate()
    {
        String strMonth = new SimpleDateFormat("MMMM", Locale.US).format(calendar.getTime());
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
     *
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
     *  timeToString(calendar) : 10h05
     */
    static public String TimeToString(Time t)
    {
        StringBuilder str = new StringBuilder();
        if(t.negativeTime == true)
        {
            str.append("- ");
        }

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
     * @param h hour
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
     * @param m minute
     */
    public void setMinute(int m)
    {
        if(m>=0) {
            calendar.set(Calendar.MINUTE, m);
        }
    }


    /**
     * Allow you to do Time0 - Time1
     * @param t0 time0
     * @param t1 time1
     * @return new time = time0-time1
     */
    public static Time Substraction(Time t0 , Time t1)
    {
        Time result = new Time();
        result.emptyTime();



        if(t0.negativeTime == false && t1.negativeTime ==false) {
            int day = result.getDay();
            long defaultTime = result.getCalendar().getTimeInMillis();
            result.getCalendar().add(Calendar.HOUR_OF_DAY, t0.getHour() - t1.getHour());
            result.getCalendar().add(Calendar.MINUTE, t0.getMinute() - t1.getMinute());

            if (day != result.getDay()) {
                long test = defaultTime - result.getCalendar().getTimeInMillis();
                result.emptyTime();
                result.getCalendar().setTimeInMillis(test);
                result.getCalendar().add(Calendar.HOUR_OF_DAY, -1);
                result.negativeTime = true;
                return result;
            }
        }
        if(t0.negativeTime == false && t1.negativeTime == true)
        {
            t1.negativeTime = false;
            Time res = Time.Addition(t0,t1);
            t1.negativeTime = true;
            return res;
        }

        if(t0.negativeTime == true && t1.negativeTime == false) {

            t1.negativeTime = false;
            Time res = Time.Addition(t0,t1);
            t1.negativeTime = true;
            return res;
        }

        if(t0.negativeTime == true && t1.negativeTime == true) {
            t1.negativeTime = false;
            Time res = Time.Addition(t0,t1);
            t1.negativeTime = true;
            res.negativeTime = true;
            return res;
        }



        return result;
    }


    /**
     * Allow you to do Time0 + Time1
     * @param t0 time0
     * @param t1 time1
     * @return new time = time0+time1
     */
    public static Time Addition(Time t0,Time t1)
    {
        Time result = new Time();
        result.emptyTime();


        if(t0.negativeTime == true && t1.negativeTime ==false)
        {
            t0.negativeTime = false;
            Time res = Time.Substraction(t1,t0);
            t0.negativeTime = true;
            return res;

        }
        if(t0.negativeTime == false && t1.negativeTime == true)
        {
            t1.negativeTime = false;
            Time res = Time.Substraction(t0,t1);
            t1.negativeTime = true;
            return res;

        }
        if(t0.negativeTime == true && t1.negativeTime == true)
        {

            result.getCalendar().add(Calendar.HOUR_OF_DAY,t0.getHour()+t1.getHour());
            result.getCalendar().add(Calendar.MINUTE,t0.getMinute()+t1.getMinute());
            result.negativeTime = true;
            return result;
        }
        if(t0.negativeTime == false && t1.negativeTime == false)
        {
            result.getCalendar().add(Calendar.HOUR_OF_DAY,t0.getHour()+t1.getHour());
            result.getCalendar().add(Calendar.MINUTE,t0.getMinute()+t1.getMinute());
            return result;
        }
        return result;
    }

    /**
     * Day getter
     * @return day
     */
    public int getDay()
    {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Month getter
     * @return month
     */
    public int getMonth()
    {
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Get hour
     * @return hour
     */
    public int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Get minute
     * @return minute
     */
    public int getMinute()
    {
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * Get year
     * @return year
     */
    public int getYear()
    {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Get calendar
     * @return calendar
     */
    public Calendar getCalendar()
    {
        return calendar;
    }

    public Boolean getNegativeTime() {
        return negativeTime;
    }
    public void setNegativeTime(boolean bool) {
        this.negativeTime = bool;
    }
}
