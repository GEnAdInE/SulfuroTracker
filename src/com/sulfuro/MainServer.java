package com.sulfuro;

import com.sulfuro.controller.TrackerServ;
import com.sulfuro.view.TrackerServGUI;

public class MainServer {

    /**
     * Main server function
     * @param args not used here
     * @throws Exception catch all exception than can happend on the server
     */
    public static void main(String[] args) throws Exception {

        TrackerServGUI serverGUI = new TrackerServGUI("Tracker Server");
        serverGUI.setVisible(true);

        TrackerServ server = new TrackerServ(serverGUI);
        Thread receptionThread = new Thread(server);
        receptionThread.start();
    }
}
