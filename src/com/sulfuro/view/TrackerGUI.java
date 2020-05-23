package com.sulfuro.view;

import com.sulfuro.Main;

import javax.swing.*;

import com.sulfuro.controller.TrackerClient;
import com.sulfuro.model.Time;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackerGUI extends JFrame{
    private JPanel MainPanel;
    private JTextField userIdText;
    private JButton sendButton;
    private JPanel panelDay;
    private JLabel dayLabel;
    private JLabel currentTimeLabel;
    private JLabel timeRoundedLabel;
    private JPanel timePanel;
    private JPanel inputPanel;
    private JButton settingsButton;
    private JPanel settingPanel;



    public TrackerGUI(String title)
    {
        super(title);


        //config of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.pack();
        this.setVisible(true);
    }

    public JPanel getPanelDay() {
        return panelDay;
    }

    public JLabel getDayLabel() {
        return dayLabel;
    }

    public JLabel getCurrentTimeLabel() {
        return currentTimeLabel;
    }

    public JLabel getTimeRoundedLabel() {
        return timeRoundedLabel;
    }

    public JPanel getTimePanel() {
        return timePanel;
    }

    public JPanel getInputPanel() {
        return inputPanel;
    }

    public JPanel getSettingPanel() {
        return settingPanel;
    }

    public JButton getSendButton()
    {
        return sendButton;
    }

    public JButton getSettingsButton()
    {
        return settingsButton;
    }

    public JTextField getUserIdText() {
        return userIdText;
    }
}
