package com.sulfuro.view;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.model.CheckInOutDATATable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Calendar;
import java.util.List;

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
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("NOM-PRENOM");
        tableModel.addColumn("HEURE");

        TrackerInputs=new JTable(tableModel);

        //INSERT IT INSIDE PANEL
        JScrollPane scrollPane = new JScrollPane(TrackerInputs);
        TrackerInputs.setFillsViewportHeight(true);
        this.Tabs.setComponentAt(0, scrollPane);
    }

    public void TrackerInputSetDBData(CheckInOutDATATable dataTable){
        List<CheckInOutDATA> dataList = dataTable.getTable();
        for (CheckInOutDATA data: dataList){
            TrackerInputAddData(data);
        }
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
