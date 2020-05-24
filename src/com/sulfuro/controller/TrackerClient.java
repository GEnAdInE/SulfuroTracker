package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.model.CheckInOutDATATable;
import com.sulfuro.model.IOmanager;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class TrackerClient {

    private TrackerGUI view;
    private String ip;
    private int port;
    Socket socket;
    private volatile String filename;
    private CheckInOutDATATable buffer;


    /**
     * Constructor of the controller for the view
     * Initialised with the default IP:PORT configuration
     * @param v take the view as argument to set the value
     */
    public TrackerClient(TrackerGUI v) throws Exception {
        view = v;
        ip = null;
        port = 0;
        filename = "InOutClientDB.ser";
    }


    /**
     * update the view (time)
     */
    public void updateView() {

        Timer t = new Timer(1000, updateTime);//actu every 1s
        t.start();

        view.getSendButton().addActionListener(sendButtonAction);
        view.getSettingsButton().addActionListener(settingsButtonAction);

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                super.windowClosing(e);
            }
        });

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
            if (view.getUserIdText().getText().isEmpty() || view.getUserIdText().getText().equals("")) {
                checkedPane.showMessageDialog(view, "Please put a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
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
                    IOmanager.writeDataToFile(filename,data);
                    checkedPane.showMessageDialog(view, "Impossible to connect , but your check in/out has been saved :)", "Error", JOptionPane.ERROR_MESSAGE);
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

            try {
                URI uri = new URI("my://" + value);
                ip = uri.getHost();
                port = uri.getPort();
                //tester la connection
                //POP UP status of the connection
                //si ca marche envoyer les donnés stocker dans le fichier
                buffer = IOmanager.getDataFromFile(filename);

                Boolean sended = true;
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

                //IO ERROR quand on ferme le pop up
                if(sended = true && buffer.getTable().isEmpty())
                {
                  File f = new File(filename);
                  if(f.delete())
                  {
                      System.out.println("deleted");
                  }
                  else
                  {
                      System.out.println("not deleted");
                  }
                }




            } catch (URISyntaxException ex) {
                JOptionPane errorPane = new JOptionPane();
                errorPane.showMessageDialog(settingsWindow, "Please put a valid Host and Port", "Settings Error", JOptionPane.ERROR_MESSAGE);

            }//catch socket error en plus

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
                socket = new Socket(ip,port);
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
