package com.sulfuro.model;

import java.io.*;
import java.util.Collections;

/**
 * Class that handle all the input and output with file
 */
public class IOmanager {

    /**
     * Get all the checkin and check out data from a file
     * @param filename file to write
     * @return a CheckInOuTDATAtable of eveything that was in the file
     */
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

    /**
     * Write CheckinOut data to a file (so save them)
     * @param filename the file to write to
     * @param data the data to write
     */
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

    /**
     * Get all data related to the company from a file
     * @param filename file to read
     * @return the Company structure
     */
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

    /**
     * Write the comapny structure (to save data)
     * @param filename file to write
     * @param employee employee to be added to the data
     * @throws Exception exception related to writing data
     */
    public static void writeCompanyToFile(String filename, Employee employee) throws Exception {
        Company companyTable;
        try
        {
            companyTable = IOmanager.getCompanyFromFile(filename);
            if(companyTable.checkIdNotUsed(employee)){
                companyTable.addEmployee(employee);
            } else {
                throw new Exception("ID ALREADY USED COULDNT CREATE USER");
            }
            Collections.sort(companyTable.getCompany());

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(companyTable);

            out.close();
            file.close();
            System.out.println("WROTE C");
        }

        catch(IOException ex) {
            System.out.println("Writing IOException is caught");
        }
    }

    /**
     *  Edit data in the file
     * @param filename file to edit
     * @param oldEmployee old employe
     * @param employee new employe
     * @throws Exception exception related to writing data
     */
    public static void modifyCompanyToFile(String filename, Employee oldEmployee, Employee employee) throws Exception {
        Company companyTable;
        try
        {
            companyTable = IOmanager.getCompanyFromFile(filename);
            companyTable.removeEmployee(oldEmployee);
            companyTable.addEmployee(employee);

            Collections.sort(companyTable.getCompany());

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(companyTable);

            out.close();
            file.close();
            System.out.println("WROTE C");
        }

        catch(IOException ex) {
            System.out.println("Writing IOException is caught");
        }
    }

    /**
     * Delete an employee from the company
     * @param filename file to edit
     * @param employee employee to delete
     * @throws Exception if an error happend
     */
    public static void delCompanyToFile(String filename,Employee employee) throws Exception {
        Company companyTable;
        try
        {
            companyTable = IOmanager.getCompanyFromFile(filename);
            companyTable.removeEmployee(employee);


            Collections.sort(companyTable.getCompany());

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(companyTable);

            out.close();
            file.close();
            System.out.println("WROTE C");
        }

        catch(IOException ex) {
            System.out.println("Writing IOException is caught");
        }
    }


    /**
     * Get companyData from a file
     * @param filename file to write
     * @return CheckInOutCompanyDATAtable model
     */
    public static CheckInOutCompanyDATATable getCompanyDataFromFile(String filename)
    {
        try
        {
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

    /**
     * write company data to file
     * @param filename file to write
     * @param data data to write
     */
    public static void writeCompanyDataToFile(String filename, CheckInOutCompanyDATA data){

        CheckInOutCompanyDATATable dataTable;

        try
        {
            dataTable = IOmanager.getCompanyDataFromFile(filename);
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
}
