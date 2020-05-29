package com.sulfuro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckInOutCompanyDATATable implements Serializable {
    private List<CheckInOutCompanyDATA> dataTable;


    public CheckInOutCompanyDATATable() {
        dataTable = new ArrayList<>();
    }

    public CheckInOutCompanyDATATable(CheckInOutCompanyDATA data) {
        dataTable.add(0, data);
    }
    public CheckInOutCompanyDATATable(CheckInOutCompanyDATATable dataTable) { this.dataTable = dataTable.getTable(); }
    public void add(CheckInOutCompanyDATA data)
    {
        dataTable.add(0, data);
    }
    public List<CheckInOutCompanyDATA> getTable(){
        return dataTable;
    }
}
