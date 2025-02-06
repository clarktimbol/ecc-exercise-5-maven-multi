package com.clark.model;
import java.util.ArrayList;


public class TableArray {
    private ArrayList<ArrayList<String>> tableArray;

    public TableArray() {
        this.tableArray = new ArrayList<>();
    }

    public ArrayList<ArrayList<String>> getTableArray() {
        return tableArray;
    }

    public void setTableArray(ArrayList<ArrayList<String>> tableArray) {
        this.tableArray = tableArray;
    }
}
