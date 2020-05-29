package com.sulfuro.controller;

import com.sulfuro.model.*;
import com.sulfuro.view.TrackerServGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;


public class TrackerServ implements Runnable{

    private volatile ServerSocket server;
    private volatile int port;
    private volatile Socket socket;
    private volatile boolean running = false;
    private volatile TrackerServGUI serverGUI;
    private volatile JTable TrackerInputs;
    private volatile JTable TrackerEmployees;
    private volatile String InputsFilename;
    private volatile String CompanyFilename;
    private volatile Company company;

    public TrackerServ(TrackerServGUI GUI) throws Exception {
        port = 1700;
        serverGUI = GUI;
        TrackerInputs = serverGUI.getTrackerInputs();
        TrackerEmployees = serverGUI.getTrackerEmployees();

        server = new ServerSocket(port);
        InputsFilename = "InOutServerDB.ser";
        CompanyFilename = "CompanyServerDB.ser";

        CheckInOutCompanyDATATable data = IOmanager.getCompanyDataFromFile(InputsFilename);
        company = IOmanager.getCompanyFromFile(CompanyFilename);

        TrackerInputsInit();
        TrackerEmployeeInit();

        //TrackerEmployeeSetDBData(company);

        TrackerInputSetDBData(data);



        //adding listener
        serverGUI.getValidateButton().addActionListener(validButtonAction);
        serverGUI.getAddButton().addActionListener(addButtonAction);
        serverGUI.getModifyButton().addActionListener(modifyButtonAction);
        serverGUI.getDeleteButton().addActionListener(deleteButtonAction);

        running = true;
    }
    public void terminate() throws Exception{
        if(socket != null){
            socket.close();
        }
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
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            CheckInOutCompanyDATA translated = null;
            try {
                translated = new CheckInOutCompanyDATA(received, company);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(translated != null){
                IOmanager.writeCompanyDataToFile(InputsFilename,translated);
                TrackerInputInsertData(translated);
            }
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
    public void TrackerInputAddData(CheckInOutCompanyDATA received){

        DefaultTableModel model = (DefaultTableModel) TrackerInputs.getModel();

        int id = received.getEmployee().getId();
        int year = received.getData().getTime().get(Calendar.YEAR);
        int month = received.getData().getTime().get(Calendar.MONTH) + 1;
        int day = received.getData().getTime().get(Calendar.DAY_OF_MONTH);
        int hour = received.getData().getTime().get(Calendar.HOUR_OF_DAY);
        int minute = received.getData().getTime().get(Calendar.MINUTE);

        String idData = Integer.toString(id);
        String name = received.getEmployee().toString();
        String timeData = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hour) + ":" + Integer.toString(minute);

        model.addRow(new Object[]{idData, name, timeData});

    }
    public void TrackerInputInsertData(CheckInOutCompanyDATA received){

        DefaultTableModel model = (DefaultTableModel) TrackerInputs.getModel();

        int id = received.getEmployee().getId();
        int year = received.getData().getTime().get(Calendar.YEAR);
        int month = received.getData().getTime().get(Calendar.MONTH) + 1;
        int day = received.getData().getTime().get(Calendar.DAY_OF_MONTH);
        int hour = received.getData().getTime().get(Calendar.HOUR_OF_DAY);
        int minute = received.getData().getTime().get(Calendar.MINUTE);

        String idData = Integer.toString(id);
        String name = received.getEmployee().toString();
        String timeData = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hour) + ":" + Integer.toString(minute);

        model.insertRow(0, new Object[]{idData, name, timeData});

    }

    public void TrackerInputSetDBData(CheckInOutCompanyDATATable dataTable){
        List<CheckInOutCompanyDATA> dataList = dataTable.getTable();
        for (CheckInOutCompanyDATA data: dataList){
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

    ActionListener validButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!serverGUI.getTextPort().getText().isEmpty()) {
                try {
                    port = Integer.parseInt(serverGUI.getTextPort().getText());
                    terminate();
                    server = new ServerSocket(port);
                    Thread receptionThread = new Thread(String.valueOf(this));
                    receptionThread.start();
                    JOptionPane.showMessageDialog(serverGUI, "Port have been changed ", "Information", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException | IOException nfe) {
                    JOptionPane.showMessageDialog(serverGUI, "Please put a number ", "Error", JOptionPane.ERROR_MESSAGE);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(serverGUI, "Can't be empty", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }
    };

    ActionListener addButtonAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(!serverGUI.getIdAddTextfield().getText().isEmpty() || !serverGUI.getFirstnameModifyTextfield().getText().isEmpty() || !serverGUI.getLastnameModifyTextField().getText().isEmpty())
            {
                    int ID = Integer.parseInt(serverGUI.getIdAddTextfield().getText());
                    String firstName = serverGUI.getFirstnameAddTextfield().getText();
                    String lastName = serverGUI.getLastnameAddTextfield().getText();
            }
            else {
                JOptionPane.showMessageDialog(serverGUI, "Id or Firstname or Lastname can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //ACTION A FAIRE SUR LE BOUTTON
        }
    };


    ActionListener modifyButtonAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(!serverGUI.getIdModifyTextfield().getText().isEmpty() || !serverGUI.getFirstnameModifyTextfield().getText().isEmpty() || !serverGUI.getLastnameModifyTextField().getText().isEmpty())
            {
                int ID = Integer.parseInt(serverGUI.getIdModifyTextfield().getText());
                String firstName = serverGUI.getFirstnameModifyTextfield().getText();
                String lastName = serverGUI.getLastnameModifyTextField().getText();
            }
            else {
                JOptionPane.showMessageDialog(serverGUI, "Id or Firstname or Lastname can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //ACTION A FAIRE SUR LE BOUTTON
        }
    };



    ActionListener deleteButtonAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(!serverGUI.getIdDeleteTextfield().getText().isEmpty())
            {
                int ID = Integer.parseInt(serverGUI.getIdDeleteTextfield().getText());
            } else {
                JOptionPane.showMessageDialog(serverGUI, "Id can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }


            //ACTION A FAIRE SUR LE BOUTTON
        }
    };

}
