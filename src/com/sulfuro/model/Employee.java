package com.sulfuro.model;

import java.io.Serializable;
import java.security.PublicKey;

public class Employee implements Serializable, Comparable<Employee> {
    private int id;
    private String name;
    private String firstname;
    private Time startTime;
    private Time endTime;
    private Time bonusTime;
    private int depId;
    private boolean isWorking;

    /**
     * Employee base constructor
     * @param id id of the worker
     * @param name his name
     * @param firstname his first name
     * @param startTime his start time of work
     * @param endTime his end time of work
     * @param depId his department id
     */
    public Employee(int id, String name, String firstname, Time startTime, Time endTime,int depId){
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bonusTime = new Time(0,0);
        this.depId = depId;
        this.isWorking = false;
    }

    /**
     * Employee copy constructor
     * @param employee employee to be copied
     */
    public Employee(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.firstname = employee.getFirstname();
        this.startTime = employee.getStartTime();
        this.endTime = employee.getEndTime();
        this.bonusTime = employee.getBonusTime();
        this.depId = employee.getDepId();
        this.isWorking = employee.getIsWorking();
    }


    /**
     * Get dep id , combined with Company.getDep(id)can return the name of the departement
     * @return id of dept
     */
    public int getDepId()
    {
        return depId;
    }

    /**
     * get worker id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get name of the worker
     * @return name
     */
    public String getName() { return  name; }

    /**
     * Get firstname
     * @return firstname
     */
    public String getFirstname() { return  firstname; }

    /**
     * Get Start time
     * @return time
     */
    public Time getStartTime()
    {
        return startTime;
    }

    /**
     * get end time
     * @return time
     */
    public Time getEndTime()
    {
        return endTime;
    }

    /**
     * Get bonus time (negativ of positive if the worker did some extre work or not)
     * @return time
     */
    public Time getBonusTime() {
        return bonusTime;
    }

    /**
     * Set the bonus time
     * @param bonusTime time to be seted
     */
    public void setBonusTime(Time bonusTime) {
        this.bonusTime = bonusTime;
    }

    /**
     * Know if the worker is actually working or not
     * @return true if he is working and false if not
     */
    public boolean getIsWorking() {
        return isWorking;
    }

    /**
     * Set if the worker is working or not
     * @param working
     */
    public void setWorking(boolean working) {
        isWorking = working;
    }


    /**
     * Override of the toString function
     * @return Name and Firstname of the worker
     */
    @Override
    public String toString(){
        return this.name + " " + this.firstname;
    }

    /**
     * Override of compartor
     * @param employee employee to be compared to
     *
     */
    @Override
    public int compareTo(Employee employee) {
        return this.toString().compareTo(employee.toString());
    }


    /**
     * Check if 2 employee are equal
     * @param o employee
     * @return true if the same and false if not
     */
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
