package com.sulfuro;

import com.sulfuro.view.TrackerGUI;
import com.sulfuro.view.TrackerServGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here


        JFrame frame = new TrackerGUI("Tracker");
        frame.setVisible(true);

        JFrame frame2 = new TrackerServGUI("Tracker Server");
        frame2.setVisible(true);

    }
}
