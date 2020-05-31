package com.sulfuro.model;

import java.io.Serializable;

public class Department implements Serializable {
    private int id;
    private String name;


    public Department()
    {
        id = 0;
        name = "NUll";
    }

    public Department(int i, String s)
    {
        id = i;
        name = s;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return this.name;
    }
}
