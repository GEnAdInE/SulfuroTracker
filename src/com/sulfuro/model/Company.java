package com.sulfuro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable {
    private List<Employee> company;

    public Company(){
        company = new ArrayList<Employee>();
    }
    public List<Employee> getCompany() {
        return company;
    }
    public void setCompany(List<Employee> company) {
        this.company = company;
    }
    public void add(Employee employee) {
        company.add(employee);
    }
    public boolean checkIdNotUsed(Employee employee){
        for(Employee e: company){
            if(e.getId() == employee.getId()){
                return false;
            }
        }
        return true;
    }
    public CheckInOutDATATable getEmployeeDataTable(Employee employee, CheckInOutDATATable dataTable){
        CheckInOutDATATable employeeDataTable = new CheckInOutDATATable();
        for(CheckInOutDATA data: dataTable.getTable()){
            if(data.getId() == employee.getId()){
                employeeDataTable.add(data);
            }
        }
        return employeeDataTable;
    }
}
