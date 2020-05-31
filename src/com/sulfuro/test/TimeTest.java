package com.sulfuro.test;

import com.sulfuro.model.Time;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {

    @Test
    public void testTime()
    {
        Time time = new Time();
        time.setHour(10);
        time.setMinute(50);
        Assert.assertEquals(10,time.getHour());
        Assert.assertEquals(50,time.getMinute());
    }





}