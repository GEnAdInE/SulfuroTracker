package com.sulfuro.model;

import java.util.Calendar;

public class CheckInOutDATA {

    private Calendar time;
    private int id;

    public CheckInOutDATA(Calendar t , int i)
    {
        time = t;
        id = i;
    }

    public int getId()
    {
        return id;
    }

    public Calendar getTime()
    {
        return time;
    }


}
