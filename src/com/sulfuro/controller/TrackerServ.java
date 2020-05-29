package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.model.CheckInOutDATATable;
import com.sulfuro.model.IOmanager;
import com.sulfuro.view.TrackerServGUI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TrackerServ implements Runnable{

    private volatile ServerSocket server;
    private volatile int port;
    private volatile Socket socket;
    private volatile boolean running = false;
    private volatile TrackerServGUI serverGUI;
    private volatile String filename;

    public TrackerServ(TrackerServGUI GUI) throws Exception {
        port = 1700;
        serverGUI = GUI;
        server = new ServerSocket(port);
        filename = "InOutServerDB.ser";

        CheckInOutDATATable data = IOmanager.getDataFromFile(filename);

        serverGUI.TrackerInputSetDBData(data);


        running = true;
    }
    public void stop() throws  Exception{
        socket.close();
        running = false;
    }
    public void run()
    {
        while(running){

            try {
                socket = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            CheckInOutDATA received = null;
            try {
                received = (CheckInOutDATA)inputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            IOmanager.writeDataToFile(filename,received);
            serverGUI.TrackerInputInsertData(received);
        }
    }
}
