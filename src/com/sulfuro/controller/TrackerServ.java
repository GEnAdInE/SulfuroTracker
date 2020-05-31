package com.sulfuro.controller;

import com.sulfuro.model.*;
import com.sulfuro.view.TrackerServGUI;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TrackerServ implements Runnable{

    private volatile ServerSocket server;
    private volatile int port;
    private volatile Socket socket;
    private volatile boolean running = false;
    private volatile TrackerServGUI serverGUI;
    private volatile JPanel MainPanel;
    private volatile JTable TrackerInputs;
    private volatile JTable TrackerEmployees;
    private volatile JTable TrackerEmployeesVisualize;
    private volatile String InputsFilename;
    private volatile String CompanyFilename;
    private volatile Company company;

    public TrackerServ(TrackerServGUI GUI) throws Exception {
        port = 1700;
        serverGUI = GUI;
        MainPanel = serverGUI.getMainPanel();
        TrackerInputs = serverGUI.getTrackerInputs();
        TrackerEmployees = serverGUI.getTrackerEmployees();

        server = new ServerSocket(port);
        InputsFilename = "InOutServerDB.ser";
        CompanyFilename = "CompanyServerDB.ser";

        CheckInOutCompanyDATATable data = IOmanager.getCompanyDataFromFile(InputsFilename);
        company = IOmanager.getCompanyFromFile(CompanyFilename);

        TrackerInputsInit();
        TrackerEmployeeInit();

        TrackerInputSetDBData(data);
        TrackerEmployeeSetDBData(company);




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
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
        int year = received.getData().getTime().getYear();
        int month = received.getData().getTime().getMonth() + 1;
        int day = received.getData().getTime().getDay();
        int hour = received.getData().getTime().getHour();
        int minute = received.getData().getTime().getMinute();

        String idData = Integer.toString(id);
        String name = received.getEmployee().toString();
        String timeData = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hour) + ":" + Integer.toString(minute);

        model.addRow(new Object[]{idData, name, timeData});

    }
    public void TrackerInputInsertData(CheckInOutCompanyDATA received){

        DefaultTableModel model = (DefaultTableModel) TrackerInputs.getModel();

        int id = received.getEmployee().getId();
        int year = received.getData().getTime().getYear();
        int month = received.getData().getTime().getMonth() + 1;
        int day = received.getData().getTime().getDay();
        int hour = received.getData().getTime().getHour();
        int minute = received.getData().getTime().getMinute();

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
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableModel.addColumn("ID");
        tableModel.addColumn("NOM-PRENOM");

        TrackerEmployees=new JTable(tableModel);
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = TrackerEmployees.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), TrackerEmployees));
                        if (rowAtPoint > -1) {
                            TrackerEmployees.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        });

        JMenuItem visualize = new JMenuItem("More infos");
        visualize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = TrackerEmployees.getSelectedRow();
                int ID = Integer.parseInt(TrackerEmployees.getValueAt(selectedRow, 0).toString());
                Employee employee = company.getEmployeeByID(ID);
                CheckInOutCompanyDATATable dataTable = IOmanager.getCompanyDataFromFile(InputsFilename);
                CheckInOutCompanyDATATable employeeDataTable = company.getEmployeeDataTable(employee, dataTable);
                TrackerVisualizeEmployeeInit();
                for(CheckInOutCompanyDATA data: employeeDataTable.getTable()){
                    TrackerEmployeeVisualizeAddData(data);
                }
                JOptionPane.showMessageDialog(MainPanel, new JScrollPane(TrackerEmployeesVisualize));
            }
        });
        popupMenu.add(visualize);
        TrackerEmployees.setComponentPopupMenu(popupMenu);

        JScrollPane scrollPane = new JScrollPane(TrackerEmployees);
        TrackerEmployees.setFillsViewportHeight(true);
        serverGUI.getEmployeeTabs().setComponentAt(0, scrollPane);
    }
    public void TrackerEmployeeAddData(Employee employee){

        DefaultTableModel model = (DefaultTableModel) TrackerEmployees.getModel();

        int id = employee.getId();

        String idData = Integer.toString(id);
        String name = employee.toString();

        model.addRow(new Object[]{idData, name});

    }
    public void TrackerEmployeeDelData(Employee employee){

        DefaultTableModel model = (DefaultTableModel) TrackerEmployees.getModel();

        int id = employee.getId();
        String idData = Integer.toString(id);

        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            if (model.getValueAt(i, 0).equals(idData)) {
                model.removeRow(i);
            }
        }
    }
    public void TrackerEmployeeSetDBData(Company company){
        List<Employee> employeeList = company.getCompany();
        for (Employee employee: employeeList){
            TrackerEmployeeAddData(employee);
        }
    }
    public void TrackerVisualizeEmployeeInit(){
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("ID");
        tableModel.addColumn("NOM-PRENOM");
        tableModel.addColumn("HEURE");
        tableModel.addColumn("BONUS TIME");

        TrackerEmployeesVisualize=new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(TrackerEmployeesVisualize);
        TrackerEmployeesVisualize.setFillsViewportHeight(true);
        serverGUI.getTabs().setComponentAt(0, scrollPane);
    }
    public void TrackerEmployeeVisualizeAddData(CheckInOutCompanyDATA received){

        DefaultTableModel model = (DefaultTableModel) TrackerEmployeesVisualize.getModel();

        int id = received.getEmployee().getId();
        String idData = Integer.toString(id);
        String name = received.getEmployee().toString();

        int year = received.getData().getTime().getYear();
        int month = received.getData().getTime().getMonth() + 1;
        int day = received.getData().getTime().getDay();
        int hour = received.getData().getTime().getHour();
        int minute = received.getData().getTime().getMinute();

        String timeData = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hour) + ":" + Integer.toString(minute);

        model.addRow(new Object[]{idData, name, timeData, "NOT IMPLEMENTED"});

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
            int id = -1;
            String firstName = null;
            String lastName = null;
            Time startTime = null;
            Time endTime = null;
            if(!serverGUI.getIdAddTextfield().getText().isEmpty() || !serverGUI.getFirstnameAddTextfield().getText().isEmpty() || !serverGUI.getLastnameAddTextfield().getText().isEmpty() || !serverGUI.getStartTimeAddTextfield().getText().isEmpty() || !serverGUI.getEndTimeAddTextfield().getText().isEmpty())
            {
                    id = Integer.parseInt(serverGUI.getIdAddTextfield().getText());
                    firstName = serverGUI.getFirstnameAddTextfield().getText();
                    lastName = serverGUI.getLastnameAddTextfield().getText();
                    LocalTime lts = LocalTime.parse(serverGUI.getStartTimeAddTextfield().getText());
                    LocalTime lte = LocalTime.parse(serverGUI.getEndTimeAddTextfield().getText());
                    startTime= new Time(lts.getHour(),lts.getMinute());
                    endTime = new Time(lte.getHour(),lte.getMinute());
            }
            else {
                JOptionPane.showMessageDialog(serverGUI, "Id or Firstname or Lastname or Start time or End time can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(id != -1 && firstName != null && lastName != null && startTime != null && endTime != null){
                Employee translated = new Employee(id, lastName, firstName, startTime, endTime);
                try {
                    IOmanager.writeCompanyToFile(CompanyFilename, translated);
                    TrackerEmployeeAddData(translated);
                    company = IOmanager.getCompanyFromFile(CompanyFilename);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    };


    ActionListener modifyButtonAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int oldID = -1;
            int id = -1;
            String firstName = null;
            String lastName = null;
            Time startTime = null;
            Time endTime = null;
            if(!serverGUI.getOldIDModifyTextField().getText().isEmpty() || !serverGUI.getIdModifyTextfield().getText().isEmpty() || !serverGUI.getFirstnameModifyTextfield().getText().isEmpty() || !serverGUI.getLastnameModifyTextField().getText().isEmpty())
            {
                oldID = Integer.parseInt(serverGUI.getOldIDModifyTextField().getText());
                id = Integer.parseInt(serverGUI.getIdModifyTextfield().getText());
                firstName = serverGUI.getFirstnameModifyTextfield().getText();
                lastName = serverGUI.getLastnameModifyTextField().getText();
                LocalTime lts = LocalTime.parse(serverGUI.getStartTimeModifyTextfield().getText());
                LocalTime lte = LocalTime.parse(serverGUI.getEndTimeModifyTextfield().getText());
                startTime= new Time(lts.getHour(),lts.getMinute());
                endTime = new Time(lte.getHour(),lte.getMinute());
            }
            else {
                JOptionPane.showMessageDialog(serverGUI, "Old ID or New ID or Firstname or Lastname or Start time or End time can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(oldID != -1 && id != -1 && firstName != null && lastName != null && startTime != null && endTime != null){
                Employee translated = company.getEmployeeByID(oldID);
                if(translated != null){
                    Employee modifiedTranslated = new Employee(id, firstName, lastName, startTime, endTime);
                    try {
                        IOmanager.modifyCompanyToFile(CompanyFilename, translated, modifiedTranslated);
                        TrackerEmployeeDelData(translated);
                        TrackerEmployeeAddData(modifiedTranslated);
                        company = IOmanager.getCompanyFromFile(CompanyFilename);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }
            }
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
