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

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Employee)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Employee c = (Employee) o;

        if(this.getId() == c.getId() && this.toString().equals(c.toString())){
            return true;
        }
        return false;
    }
}
