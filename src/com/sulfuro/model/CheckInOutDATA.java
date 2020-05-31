package com.sulfuro.model;

import java.io.Serializable;
import java.util.Calendar;

public class CheckInOutDATA implements Serializable {

    private Calendar time; //passer en Time ca serait mieux
    private int id;

    /**
     * CheckInOut data to be transfered
     * @param t time of the check in/out , use time.GetRoudedTime()
     * @param i id of the worker from the textField
     */
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
