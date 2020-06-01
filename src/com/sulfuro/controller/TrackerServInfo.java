package com.sulfuro.controller;

import com.sulfuro.model.Company;
import com.sulfuro.model.Employee;
import com.sulfuro.model.Time;
import com.sulfuro.view.TrackerServUserInfoGUI;

import javax.swing.*;

public class TrackerServInfo {

    private TrackerServUserInfoGUI view;
    private Employee employee;
    private Company company;
    private JTable tab;


    public TrackerServInfo(TrackerServUserInfoGUI v , Employee emp , Company comp, JTable table)
    {
        view = v;
        employee = emp;
        company = comp;
        tab = table;
    }

    public void updateView()
    {
        view.getId().setText(Integer.toString(employee.getId()));
        view.getFirstname().setText(employee.getFirstname());
        view.getLastname().setText(employee.getName());
        view.getDepartment().setText(company.getDep(employee.getDepId()));
        view.getStarttime().setText(Time.TimeToString(employee.getStartTime()));
        view.getEndtime().setText(Time.TimeToString(employee.getEndTime()));
        view.getBonustime().setText(Time.TimeToString(employee.getBonusTime()));

        String workingstr;
        if(employee.getIsWorking())
        {
            workingstr = "Is working";
        }
        else
        {
            workingstr = "Is not working";
        }
        view.getIsworking().setText(workingstr);

        view.getCheckInOut().getViewport().add(tab);


        view.setVisible(true);

    }

}
