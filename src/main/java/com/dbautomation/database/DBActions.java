package com.dbautomation.database;

import java.util.ArrayList;

import com.dbautomation.model.Model;


public class DBActions {
 

    //https://github.com/Chris0016/JavaFX-Password-Manager/blob/main/DBConnection/DBConnection.java

    private ArrayList<Object> cols;
    private int numRows;
    private String table;

    public DBActions(ArrayList<Object> cols, int numRows, String table ){
        this.cols = cols;
        this.numRows = numRows;
        this.table = table;

    }
    
    public int insertCols(){
        
        String startSQL =  "INSERT INTO " + table + " VALUES (";
        String sql;
        try (DBConnection con = new DBConnection()) {
            for (int i = 0; i < numRows; i++) {
                sql =  startSQL + getVals() + ");";
                con.insertSQL( sql);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getVals(){

        String vals = "";

        for (Object col : cols) {
            vals += ((Model)col).generateValue() + ", ";
        }

        return vals.substring(0, vals.length() - 2);
    }

}
