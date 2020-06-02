package com.sulfuro.view;

import com.sulfuro.model.Employee;
import com.sulfuro.model.JTextFieldHintUI;

import javax.swing.*;
import java.awt.*;

/**
 * TrackServer GUI with his constructor and all the getter and setter
 */
public class TrackerServUserInfoGUI extends JFrame {
    private JLabel id;
    private JLabel firstname;
    private JLabel lastname;
    private JLabel department;
    private JLabel starttime;
    private JLabel endtime;
    private JLabel bonustime;
    private JLabel isworking;
    private JPanel MainPanel;
    private JScrollPane CheckInOut;

    /**
     * UserInfoGUI constructor
     */
    public TrackerServUserInfoGUI()
    {
        super("User Information");
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setSize(500, 500);
    }




    public JLabel getId() {
        return id;
    }

    public JLabel getFirstname() {
        return firstname;
    }

    public JLabel getLastname() {
        return lastname;
    }

    public JLabel getDepartment() {
        return department;
    }

    public JLabel getStarttime() {
        return starttime;
    }

    public JLabel getEndtime() {
        return endtime;
    }

    public JLabel getBonustime() {
        return bonustime;
    }

    public JLabel getIsworking() {
        return isworking;
    }

    public JScrollPane getCheckInOut() {
        return CheckInOut;
    }

    public void setCheckInOut(JScrollPane checkInOut) {
        CheckInOut = checkInOut;
    }
}
