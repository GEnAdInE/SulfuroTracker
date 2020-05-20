package com.sulfuro.view;

import com.sulfuro.Main;

import javax.swing.*;
import com.sulfuro.model.Time;

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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        Time time = new Time();
        this.dayLabel.setText(time.getDate());
        this.currentTimeLabel.setText(time.getTime());
        this.timeRoundedLabel.setText(time.getRoundedTime());

        this.pack();
    }

}
