package com.sulfuro.controller;

import com.sulfuro.model.*;
import com.sulfuro.view.TrackerServGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;


public class TrackerServ implements Runnable{

    private volatile ServerSocket server;
    private volatile Socket socket;
    private volatile boolean running = false;
    private volatile TrackerServGUI serverGUI;
    private volatile JTable TrackerInputs;
    private volatile JTable TrackerEmployees;
    private volatile String InputsFilename;
    private volatile String CompanyFilename;

    public TrackerServ(TrackerServGUI GUI) throws Exception {
        serverGUI = GUI;
        TrackerInputs = serverGUI.getTrackerInputs();
        TrackerEmployees = serverGUI.getTrackerEmployees();

        server = new ServerSocket(1700);
        InputsFilename = "InOutServerDB.ser";
        CompanyFilename = "CompanyServerDB.ser";

        CheckInOutDATATable data = IOmanager.getDataFromFile(InputsFilename);
        Company company = IOmanager.getCompanyFromFile(CompanyFilename);

        TrackerInputsInit();
        TrackerEmployeeInit();

        //TrackerEmployeeSetDBData(company);
        TrackerInputSetDBData(data);

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


            IOmanager.writeDataToFile(InputsFilename,received);
            TrackerInputInsertData(received);
        }
    }
    public void TrackerInputsInit(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("NOM-PRENOM");
        tableModel.addColumn("HEURE");

        TrackerInputs=new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(TrackerInputs);
        TrackerInputs.setFillsViewportHeight(true);
        serverGUI.getTabs().setComponentAt(0, scrollPane);
    }
    public void TrackerInputAddData(CheckInOutDATA received){

        DefaultTableModel model = (DefaultTableModel) TrackerInputs.getModel();

        int id = received.getId();
        int year = received.getTime().get(Calendar.YEAR);
        int month = received.getTime().get(Calendar.MONTH) + 1;
        int day = received.getTime().get(Calendar.DAY_OF_MONTH);
        int hour = received.getTime().get(Calendar.HOUR_OF_DAY);
        int minute = received.getTime().get(Calendar.MINUTE);

        String idData = Integer.toString(id);
        String timeData = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hour) + ":" + Integer.toString(minute);

        model.addRow(new Object[]{idData, "Test", timeData});

    }
    public void TrackerInputInsertData(CheckInOutDATA received){

        DefaultTableModel model = (DefaultTableModel) TrackerInputs.getModel();

        int id = received.getId();
        int year = received.getTime().get(Calendar.YEAR);
        int month = received.getTime().get(Calendar.MONTH) + 1;
        int day = received.getTime().get(Calendar.DAY_OF_MONTH);
        int hour = received.getTime().get(Calendar.HOUR_OF_DAY);
        int minute = received.getTime().get(Calendar.MINUTE);

        String idData = Integer.toString(id);
        String timeData = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hour) + ":" + Integer.toString(minute);

        model.insertRow(0, new Object[]{idData, "Test", timeData});

    }

    public void TrackerInputSetDBData(CheckInOutDATATable dataTable){
        List<CheckInOutDATA> dataList = dataTable.getTable();
        for (CheckInOutDATA data: dataList){
            TrackerInputAddData(data);
        }
    }
    public void TrackerEmployeeInit(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("NOM-PRENOM");

        TrackerEmployees=new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(TrackerEmployees);
        TrackerEmployees.setFillsViewportHeight(true);
        serverGUI.getEmployeeTabs().setComponentAt(0, scrollPane);
    }
    public void TrackerEmployeeAddData(Employee employee, CheckInOutDATA received){

        DefaultTableModel model = (DefaultTableModel) TrackerEmployees.getModel();

        int id = employee.getId();
        int year = received.getTime().get(Calendar.YEAR);
        int month = received.getTime().get(Calendar.MONTH) + 1;
        int day = received.getTime().get(Calendar.DAY_OF_MONTH);
        int hour = received.getTime().get(Calendar.HOUR_OF_DAY);
        int minute = received.getTime().get(Calendar.MINUTE);

        String idData = Integer.toString(id);
        String employeeInfos = employee.toString();
        String timeData = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hour) + ":" + Integer.toString(minute);

        model.addRow(new Object[]{idData, employeeInfos, timeData});

    }
    public void TrackerEmployeeInsertData(Employee employee, CheckInOutDATA received){

        DefaultTableModel model = (DefaultTableModel) TrackerInputs.getModel();

        int id = employee.getId();
        int year = received.getTime().get(Calendar.YEAR);
        int month = received.getTime().get(Calendar.MONTH) + 1;
        int day = received.getTime().get(Calendar.DAY_OF_MONTH);
        int hour = received.getTime().get(Calendar.HOUR_OF_DAY);
        int minute = received.getTime().get(Calendar.MINUTE);

        String idData = Integer.toString(id);
        String employeeInfos = employee.toString();
        String timeData = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hour) + ":" + Integer.toString(minute);

        model.insertRow(0, new Object[]{idData, employeeInfos, timeData});
    }
    public void TrackerInputSetDBData(Company company){
        List<Employee> employeeList = company.getCompany();
        for (Employee employee: employeeList){

        }
    }

}
