package com.sulfuro.view;

import javax.swing.*;

public class TrackerServGUI extends JFrame {
    private JTabbedPane Tabs;
    private JPanel MainPanel;

    public TrackerServGUI(String title)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        this.setSize(1000, 500);
    }
}
