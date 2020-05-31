package com.sulfuro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable {
    private List<Employee> company;

    public Company(){ company = new ArrayList<Employee>(); }
    public List<Employee> getCompany() {
        return company;
    }
    public void add(Employee employee) {
        company.add(employee);
    }
    public void remove(Employee employee) { company.remove(employee); }
    public boolean checkIdNotUsed(Employee employee){
        for(Employee e: company){
            if(e.equals(employee)){
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
    public Employee getEmployeeByID(int ID){
        for(Employee employee: this.getCompany()){
            if(employee.getId() == ID){
                return employee;
            }
        }
        return null;
    }
}
