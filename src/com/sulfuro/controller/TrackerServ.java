package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;

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

    public TrackerServ() throws Exception {
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

            int id = received.getId();
            int year = received.getTime().get(Calendar.YEAR);
            int month = received.getTime().get(Calendar.MONTH) + 1;
            int day = received.getTime().get(Calendar.DAY_OF_MONTH);
            int hour = received.getTime().get(Calendar.HOUR_OF_DAY);
            int minute = received.getTime().get(Calendar.MINUTE);

            System.out.printf("Checked %d at %d-%02d-%02d %02d:%02d \n", id, year, month, day, hour, minute);
        }
    }
}
