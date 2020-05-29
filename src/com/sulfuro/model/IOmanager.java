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

            CheckInOutDATATable InOutDB = (CheckInOutDATATable)objStream.readObject();;
            objStream.close();
            fileStream.close();

            System.out.println("GOT DATA");
            return InOutDB;

        }

        catch(IOException ex)
        {
            System.out.println("Geting IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return new CheckInOutDATATable();
    }

    public static void writeDataToFile(String filename, CheckInOutDATA data){


        CheckInOutDATATable dataTable;

        try
        {
            dataTable = IOmanager.getDataFromFile(filename);
            dataTable.add(data);

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(dataTable);

            out.close();
            file.close();
            System.out.println("WROTE D");
        }

        catch(IOException ex)
        {
            System.out.println("Writing IOException is caught");
        }
    }
    public static Company getCompanyFromFile(String filename)
    {
        try
        {
            // Reading the object from a file
            FileInputStream fileStream = new FileInputStream(filename);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);

            Company CompanyDB = (Company) objStream.readObject();;
            objStream.close();
            fileStream.close();

            System.out.println("GOT COMPANY");
            return CompanyDB;

        }

        catch(IOException ex)
        {
            System.out.println("Getting IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return new Company();
    }

    public static void writeCompanyToFile(String filename, Employee employee){


        Company companyTable;

        try
        {
            companyTable = IOmanager.getCompanyFromFile(filename);
            companyTable.add(employee);

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(companyTable);

            out.close();
            file.close();
            System.out.println("WROTE C");
        }

        catch(IOException ex)
        {
            System.out.println("Writing IOException is caught");
        }
    }
    public static CheckInOutCompanyDATATable getCompanyDataFromFile(String filename)
    {
        try
        {
            // Reading the object from a file
            FileInputStream fileStream = new FileInputStream(filename);
            ObjectInputStream objStream = new ObjectInputStream(fileStream);

            CheckInOutCompanyDATATable CompanyDB = (CheckInOutCompanyDATATable) objStream.readObject();;
            objStream.close();
            fileStream.close();

            System.out.println("GOT COMPANY DATA");
            return CompanyDB;

        }

        catch(IOException ex)
        {
            System.out.println("Getting IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return new CheckInOutCompanyDATATable();
    }

    public static void writeCompanyDataToFile(String filename, CheckInOutCompanyDATATable dataTable){

        /*
        Company companyTable;

        try
        {
            companyTable = IOmanager.getCompanyFromFile(filename);
            companyTable.add(employee);

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(companyTable);

            out.close();
            file.close();
            System.out.println("WROTE C");
        }

        catch(IOException ex)
        {
            System.out.println("Writing IOException is caught");
        }

         */
    }
}
