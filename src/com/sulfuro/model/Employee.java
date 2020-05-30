package com.sulfuro.model;

import java.io.Serializable;

public class Employee implements Serializable, Comparable<Employee> {
    private int id;
    private String name;
    private String firstname;

    public Employee(int id, String name, String firstname){
        this.id = id;
        this.name = name;
        this.firstname = firstname;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getFirstname() {
        return firstname;
    }
    public String toString(){
        return this.name + " " + this.firstname;
    }
    @Override
    public int compareTo(Employee employee) {
        return this.toString().compareTo(employee.toString());
    }
}
