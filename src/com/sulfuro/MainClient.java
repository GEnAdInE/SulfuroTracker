package com.sulfuro;

import com.sulfuro.controller.TrackerClient;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerGUI;

public class MainClient {
    public static void main(String[] args) throws Exception {


        TrackerGUI frame = new TrackerGUI("Tracker");
        TrackerClient controllerClient = new TrackerClient(frame);
        controllerClient.updateView();



        com.sulfuro.model.Time t0 = new Time(02,00);
       com.sulfuro.model.Time  t1 = new Time(10,00);
       Time t3;
       Time t4;

        System.out.println(Time.TimeToString(t0));
        System.out.println(Time.TimeToString(t1));
        t3 = Time.Substraction(t0,t1);
        System.out.println(Time.TimeToString(t3));
        t4 = Time.Addition(t0,t3);
        System.out.println(Time.TimeToString(t4));

    }
}
