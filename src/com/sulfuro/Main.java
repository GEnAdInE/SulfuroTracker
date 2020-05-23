package com.sulfuro;

import com.sulfuro.controller.TrackerClient;
import com.sulfuro.controller.TrackerServ;
import com.sulfuro.view.TrackerGUI;
import com.sulfuro.view.TrackerServGUI;

import javax.swing.*;
import java.nio.channels.spi.AbstractSelectionKey;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here



        TrackerGUI frame = new TrackerGUI("Tracker");
        TrackerClient controllerClient = new TrackerClient(frame);
        controllerClient.updateView();


        JFrame frame2 = new TrackerServGUI("Tracker Server");
        frame2.setVisible(true);

        Thread controllerServ = new Thread(new TrackerServ());
        controllerServ.start();

    }
}
