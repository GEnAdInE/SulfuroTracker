package com.sulfuro.model;

import java.io.Serializable;

public class Employee implements Serializable, Comparable<Employee> {
    private int id;
    private String name;
    private String firstname;
    private Time startTime;
    private Time endTime;
    private Time bonusTime;

    public Employee(int id, String name, String firstname, Time startTime, Time endTime){
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bonusTime = new Time(0,0);
    }
    public int getId() {
        return id;
    }
    public Time getStartTime()
    {
        return startTime;
    }
    public Time getEndTime()
    {
        return endTime;
    }
    public Time getBonusTime() {
        return bonusTime;
    }
    @Override
    public String toString(){
        return this.name + " " + this.firstname;
    }
    @Override
    public int compareTo(Employee employee) {
        return this.toString().compareTo(employee.toString());
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee c = (Employee) o;
        if(this.getId() == c.getId()){
            return true;
        }
        return false;
    }
}
