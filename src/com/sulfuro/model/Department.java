package com.sulfuro.model;

import java.io.Serializable;

public class Department implements Serializable {
    private int id;
    private String name;


    /**
     * Default constructor
     */
    public Department()
    {
        id = 0;
        name = "NUll";
    }

    /**
     * Department main constructor to create a department
     * @param i Id of dept
     * @param s Name of dept
     */
    public Department(int i, String s)
    {
        id = i;
        name = s;
    }


    /**
     * Get name of dep
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of dep
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get id of dep
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Set id of dep
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Override of toString to show the name of the dep
     * @return name
     */
    @Override
    public String toString(){
        return this.name;
    }
}
