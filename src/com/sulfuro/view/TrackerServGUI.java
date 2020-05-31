package com.sulfuro.view;

import com.sulfuro.model.JTextFieldHintUI;

import javax.swing.*;
import java.awt.*;

public class TrackerServGUI extends JFrame {
    private JTabbedPane Tabs;
    private JTabbedPane EmployeeTabs;
    private JPanel MainPanel;
    private JTextField textPort;
    private JButton validateButton;
    private JTextField idAddTextfield;
    private JTextField firstnameAddTextfield;
    private JTextField lastnameAddTextfield;
    private JButton AddButton;
    private JTextField idModifyTextfield;
    private JButton modifyButton;
    private JTextField LastnameModifyTextField;
    private JTextField FirstnameModifyTextfield;
    private JButton deleteButton;
    private JTextField idDeleteTextfield;
    private JTextField oldIDModifyTextField;
    private JTextField textField1;
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


    public JTextField getLastnameAddTextfield() {
        return lastnameAddTextfield;
    }

    public JTextField getFirstnameAddTextfield() {
        return firstnameAddTextfield;
    }

    public JTextField getIdAddTextfield() {
        return idAddTextfield;
    }

    public JButton getAddButton() {
        return AddButton;
    }

    public JTextField getOldIDModifyTextField() { return oldIDModifyTextField; }

    public JTextField getIdModifyTextfield() {
        return idModifyTextfield;
    }

    public JTextField getFirstnameModifyTextfield() {
        return FirstnameModifyTextfield;
    }

    public JTextField getLastnameModifyTextField() {
        return LastnameModifyTextField;
    }

    public JButton getModifyButton() {
        return modifyButton;
    }


    public JTextField getIdDeleteTextfield() {
        return idDeleteTextfield;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
