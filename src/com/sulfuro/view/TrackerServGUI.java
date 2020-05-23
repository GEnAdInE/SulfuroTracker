package com.sulfuro.view;

import com.sulfuro.model.CheckInOutDATA;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Calendar;

public class TrackerServGUI extends JFrame {
    private JTabbedPane Tabs;
    private JPanel MainPanel;
    private JTable TrackerInputs;

    public TrackerServGUI(String title)
    {
        super(title);

        TrackerInputsInit();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        this.setSize(1000, 500);
    }
    public void TrackerInputsInit(){
        String[] columnNames = { "ID", "Name", "Last Entry" };
        String[][] data = TrackerInputGetData();

        //CREATE TABLE
        //TrackerInputs=new JTable(data,columnNames);

        TableModel tableModel = new DefaultTableModel(0, 3);
        TrackerInputs=new JTable(tableModel);

        //INSERT IT INSIDE PANEL
        JScrollPane scrollPane = new JScrollPane(TrackerInputs);
        TrackerInputs.setFillsViewportHeight(true);
        this.Tabs.setComponentAt(0, scrollPane);
    }
    public String[][] TrackerInputGetData(){
        //GET DATA FROM FILE
        String[][] data = {
                { "4031", "Kundan Kumar Jha", "20h30" },
                { "6014", "Anand Jha", "19h30" }
        };
        return data;
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

}
