package com.sulfuro;

import com.sulfuro.controller.TrackerClient;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerGUI;

public class MainClient {
    public static void main(String[] args) throws Exception {


        TrackerGUI frame = new TrackerGUI("Tracker");
        TrackerClient controllerClient = new TrackerClient(frame);
        controllerClient.updateView();

    }
}
