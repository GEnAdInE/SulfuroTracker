package com.sulfuro.model;

import java.io.*;

public class IOmanager {

    public static CheckInOutDATATable getDataFromFile(String filename)
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

    public static void writeDataToFile(String filename, CheckInOutDATA data){
        CheckInOutDATATable dataTable = IOmanager.getDataFromFile(filename);
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
