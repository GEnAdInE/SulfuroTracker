package com.sulfuro;

import com.sulfuro.controller.TrackerClient;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerGUI;

public class MainClient {
    public static void main(String[] args) throws Exception {


        TrackerGUI frame = new TrackerGUI("Tracker");
        TrackerClient controllerClient = new TrackerClient(frame);
        controllerClient.updateView();



        com.sulfuro.model.Time t0 = new com.sulfuro.model.Time();
       com.sulfuro.model.Time  t1 = new Time(17,10);
       Time t3 = new Time();

        System.out.println(Time.timeToString(t0));
        System.out.println(Time.timeToString(t1));
        t3.getCalendar().setTimeInMillis(t1.getCalendar().getTimeInMillis()-t0.getCalendar().getTimeInMillis());
        System.out.println(Time.timeToString(t3));

    }
}
