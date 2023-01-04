package com.dbautomation;

import java.util.ArrayList;
import java.util.List;

import com.dbautomation.database.DBActions;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        String file = "largeUserFile.txt";
        String filePath = "src/main/java/com/dbautomation/resources/" + file;
        

        //boolean isLargeFile = file.equals("largeUserFile.txt");
        //boolean fetchInOrder = false;


        String[] myArgs = { "-table", "myTable", 
                            "-rows", "10",  
                            "-cols", "name", "comment", "email", "date",
                            "nameC",
                            "textC", "-mnLen", "5", "-mxLen", "10",
                            "emailC", "-maxLen", "5",
                            "numberC", "-max", "800", "-min", "500", "-curr", "Y"

                           
                            };

        MainCommand mc = new MainCommand();
     
        int exitCode = new CommandLine(mc).execute(myArgs);
        
        int rows = mc.getRows();
        String table = mc.getTable();
        List<String> colNames = mc.getColumnNames();
        


      


        DBActions ac = new DBActions( mc.getCols(), mc.getColumnNames() ,mc.getRows() , mc.getTable());
        

        System.out.println(
            "Rows: " + rows
            + "\nTable: " + table
            + "\nColumns: " + colNames.toString()
        );

        // for (int i = 0; i < 10; i++) {
        //     System.out.println( ac.getVals() );
        // }
        
       System.out.println(ac.getStartSQL());
       
        ac.insertCols();
        
        System.exit(exitCode);

    }
}
