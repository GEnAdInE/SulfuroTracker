package com.sulfuro;

import com.sulfuro.controller.TrackerServ;
import com.sulfuro.view.TrackerServGUI;

public class MainServer {

    public static void main(String[] args) throws Exception {

        TrackerServGUI server = new TrackerServGUI("Tracker Server");
        server.setVisible(true);


        Thread receptionThread = new Thread(new TrackerServ(server));
        receptionThread.start();

    }
}
