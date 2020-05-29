package com.sulfuro.view;

import com.sulfuro.model.CheckInOutDATA;
import com.sulfuro.model.CheckInOutDATATable;
import com.sulfuro.model.JTextFieldHintUI;
import com.sulfuro.model.Time;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

public class TrackerServGUI extends JFrame {
    private JTabbedPane Tabs;
    private JTabbedPane EmployeeTabs;
    private JPanel MainPanel;
    private JTextField textPort;
    private JButton validateButton;
    private JTextField id;
    private JTextField firstname;
    private JTextField lastname;
    private JTable TrackerInputs;
    private JTable TrackerEmployees;

    public TrackerServGUI(String title)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        textPort.setUI(new JTextFieldHintUI("Port", Color.gray));

        this.setSize(1000, 500);
    }
    public JTextField getTextPort() {return textPort;}
    public JButton getValidateButton() {return validateButton;}

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
