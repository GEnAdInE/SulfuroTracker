package com.sulfuro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CheckInOutDATATable implements Serializable {

    private List<CheckInOutDATA> dataTable;

    public CheckInOutDATATable() {
        dataTable = new ArrayList<>();
    }

    public CheckInOutDATATable(CheckInOutDATA data) {
        dataTable.add(data);
    }

    public void add(CheckInOutDATA data)
    {
        dataTable.add(data);
    }
    public List<CheckInOutDATA> getTable(){
        return dataTable;
    }


}
