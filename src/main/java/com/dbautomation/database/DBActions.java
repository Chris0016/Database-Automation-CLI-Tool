package com.dbautomation.database;

import java.util.ArrayList;
import java.util.List;

import com.dbautomation.model.Model;


public class DBActions {
 

    //https://github.com/Chris0016/JavaFX-Password-Manager/blob/main/DBConnection/DBConnection.java

    private ArrayList<Object> cols;
    private int numRows;
    private String table;
   //private String columnNamesString;

    private String startSQL; 

    public DBActions(ArrayList<Object> cols, List<String> columnNames, int numRows, String table ){
        this.cols = cols;
        this.numRows = numRows;
        this.table = table;
        // this.columnNamesString = this.parseColumnNames(columnNames);

        startSQL =  "INSERT INTO " + table + " (" + parseColumnNames(columnNames) + ")" + " VALUES (";
    }
    
    public int insertCols(){
        
        
        String query;

        for (int i = 0; i < numRows; i++) {
            query =  startSQL + getVals() + ");";
            System.out.println("\nInsert Query:");
            System.out.println(query);
            //con.insertSQL(query);
        }


        // try (DBConnection con = new DBConnection()) {
        //     for (int i = 0; i < numRows; i++) {
        //         query =  startSQL + getVals() + ");";
        //         con.insertSQL(query);
        //     }
            
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        return 0;
    }

    //TODO: MAKE ME PRIVATE`
    public String getVals(){

        String vals = ((Model)cols.get(0)).generateValue();
        
        for( int i = 1; i < cols.size(); i++){
            vals += ", " + ((Model)cols.get(i)).generateValue();
        }
        
        return vals;
    }

    //TODO: MAKE ME PRIVATE`
    public String parseColumnNames(List<String>  locn){
        String vals = locn.get(0);
        
        for( int i = 1; i < locn.size(); i++){
            vals += ", " + locn.get(i);
        }
        
        return vals;
    }

    public String getStartSQL(){
        return this.startSQL;
    }

}
