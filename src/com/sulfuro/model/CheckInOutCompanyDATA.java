package com.sulfuro.model;

import java.io.Serializable;

public class CheckInOutCompanyDATA implements Serializable {
    private CheckInOutDATA data;
    private Employee employee;

    public CheckInOutCompanyDATA(CheckInOutDATA data, Employee employee){
        this.data = data;
        this.employee = employee;
    }
    public CheckInOutCompanyDATA(CheckInOutDATA data, Company company){
        this.data = data;
        for(Employee employee: company.getCompany()){
            if(data.getId() == employee.getId()){
                this.employee = employee;
                break;
            }
        }
    }
    public void setData(CheckInOutDATA data) {
        this.data = data;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Employee getEmployee() {
        return employee;
    }
    public CheckInOutDATA getData() {
        return data;
    }
}
