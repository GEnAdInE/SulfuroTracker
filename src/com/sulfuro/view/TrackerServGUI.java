package com.sulfuro.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TrackerServGUI extends JFrame {
    private JTabbedPane Tabs;
    private JPanel MainPanel;

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
        JTable TrackerInputs=new JTable(data,columnNames);

        //INSERT IT INSIDE PANEL
        JScrollPane scrollPane = new JScrollPane(TrackerInputs);
        TrackerInputs.setFillsViewportHeight(true);
        this.Tabs.setComponentAt(0, scrollPane);
    }
    public String[][] TrackerInputGetData(){
        //GET DATA FROM SOCKET
        String[][] data = {
                { "4031", "Kundan Kumar Jha", "20h30" },
                { "6014", "Anand Jha", "19h30" }
        };
        return data;
    }
}
