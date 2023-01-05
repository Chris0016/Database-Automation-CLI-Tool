package com.dbautomation.database;

import java.util.ArrayList;
import java.util.List;

import com.dbautomation.model.Model;


public class DBActions {
 
    public static void insertCols( ArrayList<Object> cols, List<String> columnNames, int numRows, String table ){
        String startSQL =  "INSERT INTO " + table + " (" + parseColumnNames(columnNames) + ")" + " VALUES (";
        String query;

        try (DBConnection con = new DBConnection()) {
            for (int i = 0; i < numRows; i++) {

                //query =  startSQL + getVals(cols) + ");";
                
                // System.out.println("\nInserting: ");
                // System.out.println(query);
                // System.out.println();
            
                con.insertSQL(startSQL + getVals(cols) + ");");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getVals(ArrayList<Object> cols){

        String vals = ((Model)cols.get(0)).generateValue();
        
        for( int i = 1; i < cols.size(); i++)
        
            vals += ", "  + ((Model)cols.get(i)).generateValue();
        
        
        
        return vals;
    }

    private static  String parseColumnNames(List<String>  locn){
        String vals = "`" + locn.get(0) + "`";
        
        for( int i = 1; i < locn.size(); i++)
            vals += ", " + "`" + locn.get(i) + "`";
        
        return vals;
    }

}
