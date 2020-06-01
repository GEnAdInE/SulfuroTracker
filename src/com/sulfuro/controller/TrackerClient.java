package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.model.CheckInOutDATATable;
import com.sulfuro.model.IOmanager;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class TrackerClient {

    private final TrackerGUI view;
    private String ip;
    private int port;
    Socket socket;
    private final String filename;


    /**
     * Constructor of the controller for the view
     * Initialised with the default IP:PORT configuration
     * @param v take the view as argument to set the value
     *
     */
    public TrackerClient(TrackerGUI v){
        view = v;
        ip = null;
        port = 0;
        filename = "InOutClientDB.ser";

        view.getSendButton().addActionListener(sendButtonAction);
        view.getSettingsButton().addActionListener(settingsButtonAction);

    }


    /**
     * update the view (time) every seconds
     */
    public void updateView() {

        Timer t = new Timer(1000, updateTime);//actu every 1s
        t.start();

    }

    ActionListener updateTime = new ActionListener() {
        /**
         * update all the time and date of the GUI
         * @param e event
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            Time time = new Time();
            view.getDayLabel().setText(time.getDate());
            view.getCurrentTimeLabel().setText(time.getTime());


            StringBuilder roundedTime = new StringBuilder();
            roundedTime.append("Let's say : ").append(time.TimeToString(time.getRoundedTime()));
            view.getTimeRoundedLabel().setText(roundedTime.toString());
        }
    };


    ActionListener sendButtonAction = new ActionListener() {
        /**
         * Create the data to be send and call the Send function
         * @param e eveny
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getUserIdText().getText().isEmpty() || view.getUserIdText().getText().equals("")) {
                JOptionPane.showMessageDialog(view, "Please put a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Time time = new Time();
                int Id =0;
                try
                {
                    Id = Integer.parseInt(view.getUserIdText().getText());

                }
                 catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(view, "Please put a number ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                }

                CheckInOutDATA data = new CheckInOutDATA(time.getRoundedTime(), Id);
                StringBuilder str = new StringBuilder();
                if(SendData(data))
                {
                    str.append(Id).append(" Cheked in/out at ").append(time.TimeToString(data.getTime()));
                    JOptionPane.showMessageDialog(view, str.toString(), "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {

                    IOmanager.writeDataToFile(filename,data);
                    JOptionPane.showMessageDialog(view, "Impossible to connect , but your check in/out has been saved :)", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    ActionListener settingsButtonAction = new ActionListener() {
        /**
         * Edit the settings to connect to the server
         * @param e event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane settingsWindow = new JOptionPane();
            String value = JOptionPane.showInputDialog(view, "Enter the IP and the port");

            try {
                URI uri = new URI("my://" + value);
                ip = uri.getHost();
                port = uri.getPort();

                File f = new File(filename);
                if(f.exists())
                {
                    CheckInOutDATATable buffer = IOmanager.getDataFromFile(filename);
                    boolean sended = true;
                    int i =0;
                    while(buffer.getTable().size() > 0)
                    {
                        if(SendData(buffer.getTable().get(i)))
                        {
                            buffer.getTable().remove(i);
                        }
                        else
                        {
                            sended = false;
                            break;
                        }
                    }

                    if(sended && buffer.getTable().isEmpty())
                    {
                        if(f.delete())
                        {
                            JOptionPane.showMessageDialog(settingsWindow, "All saved data has been sent", "Data sended", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(settingsWindow, "All saved data has been sent but we can't delete the backup file , contact the support", "Data sended", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            } catch (URISyntaxException ex) {
                JOptionPane errorPane = new JOptionPane();
                JOptionPane.showMessageDialog(settingsWindow, "Please put a valid Host and Port", "Settings Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    };


    /**
     * Function to send the data to the server
     * @param data data to be send
     * @return true if it has been send and false if not
     */
    public boolean SendData(CheckInOutDATA data) {
        try {
            if (ip != null && port != 0) {
                socket = new Socket(ip,port);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(data);
                socket.close();
            }
            else {
                JOptionPane errorPane = new JOptionPane();
                JOptionPane.showMessageDialog(view, "Please check your settings", "Error in settings", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            JOptionPane errorPane = new JOptionPane();
            JOptionPane.showMessageDialog(view, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }
}
