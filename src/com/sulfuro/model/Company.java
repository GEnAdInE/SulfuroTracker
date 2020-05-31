package com.sulfuro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable {
    private List<Employee> company;
    private List<Department> dep;

    public Company(){
        company = new ArrayList<Employee>();
        dep = new ArrayList<Department>();
        dep.add(new Department(0,"Worker"));
        dep.add(new Department(1,"Manager"));
        dep.add(new Department(2,"CEO"));
    }
    public List<Employee> getCompany() {
        return company;
    }
    public List<Department> getDep(){ return dep;}
    public void addEmployee(Employee employee) {
        company.add(employee);
    }
    public void removeEmployee(Employee employee) { company.remove(employee); }
    public boolean checkIdNotUsed(Employee employee){
        for(Employee e: company){
            if(e.equals(employee)){
                return false;
            }
        }
        return true;
    }
    public CheckInOutCompanyDATATable getEmployeeDataTable(Employee employee, CheckInOutCompanyDATATable dataTable){
        CheckInOutCompanyDATATable employeeDataTable = new CheckInOutCompanyDATATable();
        for(CheckInOutCompanyDATA data: dataTable.getTable()){
            if(data.getEmployee().getId() == employee.getId()){
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

    public String getDep(int id)
    {
        for(int i=0;i<dep.size();i++)
        {
            if(dep.get(i).getId() == id)
            {
                return dep.get(i).getName();
            }
        }
        return "";
    }

}
