package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.view.TrackerServGUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.Calendar;

public class TrackerServ implements Runnable{

    private volatile ServerSocket server;
    private volatile Socket socket;
    private volatile boolean running = false;
    private volatile TrackerServGUI serverGUI;

    public TrackerServ(TrackerServGUI GUI) throws Exception {
        serverGUI = GUI;
        server = new ServerSocket(1700);
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

            serverGUI.TrackerInputAddData(received);

        }
    }
}
