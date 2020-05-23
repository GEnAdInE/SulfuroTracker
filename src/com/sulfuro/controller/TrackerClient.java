package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackerClient {

    private TrackerGUI view;



    public TrackerClient(TrackerGUI v)
    {
        view = v;
    }


    public void updateView()
    {


        Timer t = new Timer(1000, updateTime);//actu every 1s
        t.start();

        view.getSendButton().addActionListener(sendButtonAction);
        view.getSettingsButton().addActionListener(settingsButtonAction);





    }


   ActionListener updateTime = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Time time = new Time();
            view.getDayLabel().setText(time.getDate());
            view.getCurrentTimeLabel().setText(time.getTime());



            StringBuilder roundedTime = new StringBuilder();
            roundedTime.append("Let's say : ").append(time.timeToString(time.getRoundedTime()));
            view.getTimeRoundedLabel().setText(roundedTime.toString());
        }
    };



    ActionListener sendButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane checkedPane = new JOptionPane();
            if(view.getUserIdText().getText().isEmpty() || view.getUserIdText().getText().equals("User id"))
            {
                checkedPane.showMessageDialog(view, "Please put a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                //Check if ID exist OR not
                //SERIALISE AND SEND INFO
                Time time = new Time();
                StringBuilder str = new StringBuilder();
                str.append(view.getUserIdText().getText()).append(" Cheked in/out at ").append(time.timeToString(time.getRoundedTime()));

                checkedPane.showMessageDialog(view, str.toString(), "Information", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    };

    ActionListener settingsButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane settingsWindow = new JOptionPane();
            String value = settingsWindow.showInputDialog(view,"Enter the IP and the port");
            //TEST CONNECTION
            //POP UP status of the connection
            System.out.println(value);
        }
    };


}
