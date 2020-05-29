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

/*
    ActionListener validateButtonAction = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane checkedPane = new JOptionPane();
            if (portfield.getText().isEmpty() || portfield.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please put a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Time time = new Time();
                 = Integer.parseInt(portfield.getText());
            }

        }
    }*/
}
