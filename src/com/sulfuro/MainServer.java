package com.sulfuro;

import com.sulfuro.controller.TrackerServ;
import com.sulfuro.view.TrackerServGUI;

public class MainServer {

    public static void main(String[] args) throws Exception {
	// write your code here

        TrackerServGUI frame2 = new TrackerServGUI("Tracker Server");
        frame2.setVisible(true);


        Thread controllerServ = new Thread(new TrackerServ(frame2));
        controllerServ.start();

    }
}
