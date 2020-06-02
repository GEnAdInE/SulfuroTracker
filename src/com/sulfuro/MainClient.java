package com.sulfuro;

import com.sulfuro.controller.TrackerClient;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerGUI;

public class MainClient {

    /**
     * Main client function
     * @param args not used here
     */
    public static void main(String[] args){

        TrackerGUI frame = new TrackerGUI("Tracker");
        TrackerClient controllerClient = new TrackerClient(frame);
        controllerClient.updateView();

    }
}
