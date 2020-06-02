package com.sulfuro.view;

import com.sulfuro.model.JTextFieldHintUI;

import javax.swing.*;
import java.awt.*;

/**
 * Tracker Client gui with his constructor and all the getter and setter
 */
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


    /**
     * TrackerGui constructor
     * @param title title of the windows
     */
    public TrackerGUI(String title)
    {
        super(title);


        //config of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        userIdText.setUI(new JTextFieldHintUI("User ID", Color.gray));
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        this.setSize(400, 150);

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
