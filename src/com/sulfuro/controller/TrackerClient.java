package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class TrackerClient {

    private TrackerGUI view;
    private String ip;
    private int port;


    /**
     * Constructor of the controller for the view
     * @param v take the view as argument to set the value
     */
    public TrackerClient(TrackerGUI v) {
        view = v;
        ip = null;
        port = 0;
    }


    /**
     * update the view (time)
     */
    public void updateView() {


        Timer t = new Timer(1000, updateTime);//actu every 1s
        t.start();

        view.getSendButton().addActionListener(sendButtonAction);
        view.getSettingsButton().addActionListener(settingsButtonAction);


    }



    ActionListener updateTime = new ActionListener() {
        /**
         * update all the time and date of the GUI
         * @param e
         */
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
        /**
         * Create the data to be send and call the Send function
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane checkedPane = new JOptionPane();
            if (view.getUserIdText().getText().isEmpty() || view.getUserIdText().getText().equals("User id")) {
                checkedPane.showMessageDialog(view, "Please put a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                //Check if ID exist OR not maybe not my problem :/


                //SERIALISE AND SEND INFO
                Time time = new Time();
                int Id = Integer.parseInt(view.getUserIdText().getText());

                CheckInOutDATA data = new CheckInOutDATA(time.getRoundedTime(), Id);
                StringBuilder str = new StringBuilder();
                if(SendData(data))
                {

                    str.append(Id).append(" Cheked in/out at ").append(time.timeToString(data.getTime()));
                    checkedPane.showMessageDialog(view, str.toString(), "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    checkedPane.showMessageDialog(view, "Impossible to connect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    ActionListener settingsButtonAction = new ActionListener() {
        /**
         * Edit the settings to connect to the server
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane settingsWindow = new JOptionPane();
            String value = settingsWindow.showInputDialog(view, "Enter the IP and the port");
            //TEST CONNECTION
            //POP UP status of the connection

            try {
                URI uri = new URI("my://" + value);
                ip = uri.getHost();
                port = uri.getPort();

            } catch (URISyntaxException ex) {
                JOptionPane errorPane = new JOptionPane();
                errorPane.showMessageDialog(settingsWindow, "Please put a valid Host and Port", "Settings Error", JOptionPane.ERROR_MESSAGE);

            }

        }
    };


    /**
     * Function to send the data to the server
     * @param data
     * @return true if it has been send and false if not
     */
    public boolean SendData(CheckInOutDATA data) {
        try {
            if (ip != null && port != 0) {
                Socket socket = new Socket(ip, port);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(data);
                socket.close();
            }
            else {
                JOptionPane errorPane = new JOptionPane();
                errorPane.showMessageDialog(view, "Please check your settings", "Error in settings", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            JOptionPane errorPane = new JOptionPane();
            errorPane.showMessageDialog(view, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }
}
