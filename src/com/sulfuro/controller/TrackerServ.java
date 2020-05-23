package com.sulfuro.controller;

import com.sulfuro.model.CheckInOutDATA;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TrackerServ {

    public TrackerServ()
    {

    }
    public void StartServer() throws Exception
    {
        //ne recupere que une seul donné close a faire que quand on ferme la fenettre
        ServerSocket server=new ServerSocket(1700);
        Socket socket =  server.accept();
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        CheckInOutDATA received = (CheckInOutDATA)inputStream.readObject();
        System.out.println(received.getId());
        socket.close();
    }


}
