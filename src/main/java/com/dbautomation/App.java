package com.dbautomation;

import java.util.ArrayList;

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
                            "name",
                            "text", "-mnLen", "4", "-mxLen", "5",
                            "email", "-minLen", "3",  "-maxLen", "8" ,
                            "date", "-format", "dd/MM/yyyy", "-from", "03/12/2010"
        
                            };

        MainCommand mc = new MainCommand();
     
        int exitCode = new CommandLine(mc).execute(myArgs);
        
        int rows = mc.getRows();
        String table = mc.getTable();

        System.out.println(
        "Rows: " + rows
            + "\nTable: " + table
        );


        DBActions ac = new DBActions( mc.getCols(), mc.getRows() , mc.getTable());
        
        for (int i = 0; i < 10; i++) {
            System.out.println( ac.getVals() );
        }

        //ac.insertCols();
        
        System.exit(exitCode);

    }
}
