package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.model.CheckInOutDATATable;
import com.sulfuro.view.TrackerServGUI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TrackerServ implements Runnable{

    private volatile ServerSocket server;
    private volatile Socket socket;
    private volatile boolean running = false;
    private volatile TrackerServGUI serverGUI;
    private volatile String filename;

    public TrackerServ(TrackerServGUI GUI) throws Exception {
        serverGUI = GUI;
        server = new ServerSocket(1700);
        filename = "InOutServerDB.ser";

        CheckInOutDATATable data = getDataFromFile();

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


            writeDataToFile(received);
            serverGUI.TrackerInputAddData(received);
        }
    }

    public CheckInOutDATATable getDataFromFile()
    {
        try
        {
            // Reading the object from a file
            FileInputStream fileStream = new FileInputStream(filename);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);

            CheckInOutDATATable InOutDB = null;

            InOutDB = (CheckInOutDATATable) objStream.readObject();

            objStream.close();
            fileStream.close();

            System.out.println("GOT DATA");
            return InOutDB;

        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return new CheckInOutDATATable();
    }
    public void writeDataToFile (CheckInOutDATA data){
        CheckInOutDATATable dataTable = getDataFromFile();
        dataTable.add(data);

        try
        {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(dataTable);

            out.close();
            file.close();
            System.out.println("WROTE");
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }
}
