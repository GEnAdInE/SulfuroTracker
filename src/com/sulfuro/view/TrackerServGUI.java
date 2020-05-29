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
    private JTabbedPane EmployeeTabs;
    private JPanel MainPanel;
    private JTable TrackerInputs;
    private JTable TrackerEmployees;

    public TrackerServGUI(String title)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        this.setSize(1000, 500);
    }
    public JPanel getMainPanel() {
        return MainPanel;
    }
    public JTabbedPane getTabs() {
        return Tabs;
    }
    public JTabbedPane getEmployeeTabs() {
        return EmployeeTabs;
    }

    public JTable getTrackerInputs() {
        return TrackerInputs;
    }
    public JTable getTrackerEmployees() {
        return TrackerEmployees;
    }

}
