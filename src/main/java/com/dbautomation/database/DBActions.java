package com.dbautomation.database;

import java.util.ArrayList;
import java.util.List;

import com.dbautomation.model.Model;


public class DBActions {
 
    public static void insertCols( ArrayList<Object> cols, List<String> columnNames, int numRows, String table ){
        String query =  "INSERT INTO " + table + " (" + parseColumnNames(columnNames) + ")" + " VALUES ";
    

        try (DBConnection con = new DBConnection()) {
            for (int i = 0; i < numRows; i++) {

                query +=  "(" + getVals(cols) + "),";
                
            
            
                
            }

            query = query.substring(0, query.length() - 1);
            query += ";";
            //System.out.println("Query: \n" + query);
            con.insertSQL(query);
            
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
