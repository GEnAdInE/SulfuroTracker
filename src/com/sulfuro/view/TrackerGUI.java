package com.sulfuro.view;

import com.sulfuro.Main;

import javax.swing.*;
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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));



        Timer t = new Timer(1000, updateTime);
        t.start();


        this.pack();
    }
    ActionListener updateTime = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            Time time = new Time();
            dayLabel.setText(time.getDate());
            currentTimeLabel.setText(time.getTime());


            StringBuilder roundedTime = new StringBuilder();
            roundedTime.append("Let's say : ").append(time.getRoundedTime());
            timeRoundedLabel.setText(roundedTime.toString());
        }
    };

}
