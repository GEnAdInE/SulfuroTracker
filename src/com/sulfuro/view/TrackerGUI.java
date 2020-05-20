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

        //config of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));



        //current time an day
        Timer t = new Timer(1000, updateTime);//actu every 1s
        t.start();

        //button
        sendButton.addActionListener(sendButtonAction);
        settingsButton.addActionListener(settingsButtonAction);


        this.pack();
    }
    ActionListener updateTime = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Time time = new Time();
            dayLabel.setText(time.getDate());
            currentTimeLabel.setText(time.getTime());



            StringBuilder roundedTime = new StringBuilder();
            roundedTime.append("Let's say : ").append(time.timeToString(time.getRoundedTime()));
            timeRoundedLabel.setText(roundedTime.toString());
        }
    };


    ActionListener sendButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane checkedPane = new JOptionPane();
            if(userIdText.getText().isEmpty() || userIdText.getText().equals("User id"))
            {
                checkedPane.showMessageDialog(null, "Please put a valid ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                //Check if ID exist OR not
                //SERIALISE AND SEND INFO
                Time time = new Time();
                StringBuilder str = new StringBuilder();
                str.append(userIdText.getText()).append(" Cheked in/out at ").append(time.timeToString(time.getRoundedTime()));

               checkedPane.showMessageDialog(null, str.toString(), "Information", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    };

    ActionListener settingsButtonAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane settingsWindow = new JOptionPane();
            String value = settingsWindow.showInputDialog(TrackerGUI.this,"Enter the IP and the port");
            //TEST CONNECTION
            //POP UP status of the connection
            System.out.println(value);
        }
    };

}
