package com.sulfuro.view;

import com.sulfuro.model.JTextFieldHintUI;

import javax.swing.*;
import java.awt.*;


/**
 * TrackServGUI with is constructor and all getter and setter
 */
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
    private JTextField endTimeAddTextfield;
    private JTextField startTimeAddTextfield;
    private JLabel endTimeLabel;
    private JTextField startTimeModifyTextfield;
    private JTextField endTimeModifyTextfield;
    private JTextField oldIDModifyTextField;
    private JComboBox comboBoxAdd;
    private JComboBox comboBoxModify;
    private JTable TrackerInputs;
    private JTable TrackerEmployees;

    /**
     * TrackerServerGUI constructor
     * @param title title of the windows
     */
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

    public JTextField getOldIDModifyTextField() {
        return oldIDModifyTextField;
    }

    public JTextField getIdModifyTextfield() {
        return idModifyTextfield;
    }
    public JTextField getIdDeleteTextfield() {
        return idDeleteTextfield;
    }

    public JButton getDeleteButton() {
        return deleteButton;
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


    public JTextField getEndTimeAddTextfield() {
        return endTimeAddTextfield;
    }

    public JTextField getStartTimeAddTextfield() {
        return startTimeAddTextfield;
    }

    public JTextField getStartTimeModifyTextfield() {
        return startTimeModifyTextfield;
    }

    public JTextField getEndTimeModifyTextfield() {
        return endTimeModifyTextfield;
    }


    public JComboBox getComboBoxAdd() {
        return comboBoxAdd;
    }

    public void setComboBoxAdd(JComboBox comboBoxAdd) {
        this.comboBoxAdd = comboBoxAdd;
    }

    public JComboBox getComboBoxModify() {
        return comboBoxModify;
    }

    public void setComboBoxModify(JComboBox comboBoxModify) {
        this.comboBoxModify = comboBoxModify;
    }
}
