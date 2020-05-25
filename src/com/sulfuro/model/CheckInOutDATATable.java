package com.sulfuro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckInOutDATATable implements Serializable {

    private List<CheckInOutDATA> dataTable;

    public CheckInOutDATATable() {
        dataTable = new ArrayList<>();
    }

    public CheckInOutDATATable(CheckInOutDATA data) {
        dataTable.add(0, data);
    }

    public void add(CheckInOutDATA data)
    {
        dataTable.add(0, data);
    }
    public List<CheckInOutDATA> getTable(){
        return dataTable;
    }


}
