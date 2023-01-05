package com.dbautomation;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dbautomation.database.DBActions;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import picocli.CommandLine;

import com.dbautomation.model.Model;


public class App {
    public static void main(String[] args) {
        String file = "largeUserFile.txt";
        String filePath = "src/main/ja    va/com/dbautomation/resources/" + file;
        String numRows = "1000";
        //boolean isLargeFile = file.equals("largeUserFile.txt");
        //boolean fetchInOrder = false;

        String[] myArgs = { "-table", "test_table", 
                            "-rows", numRows, 
                            "-cols", "name", "age", "email", "dob",  "comments", "sec_email", "third_email",
                            "nameC",
                            "numberC",
                            "emailC",
                            "dateC",
                            "textC",
                            "textC",
                            "textC" 
                            };

        MainCommand mc = new MainCommand();  
        int exitCode = new CommandLine(mc).execute(myArgs);

       try {

            FileWriter report = new FileWriter("report.txt", true);
            report.write("\nBenchmark for inserting " + numRows +" rows in databse.");
            report.write("\nRun Script: " + myArgs);

            long startTime = System.currentTimeMillis();
            DBActions.insertCols(mc.getCols(), mc.getColumnNames() ,mc.getRows() , mc.getTable());
            long endTime = System.currentTimeMillis();

            report.write("\nDuration(1000 rows all columns(except custom)): " + (endTime - startTime) + " milliseconds" + "\n----------");
       
            report.close();
        } catch (Exception e) {

            if (e instanceof MysqlDataTruncation)
                System.out.println("Error inserting MySQL statement. The format given for date does not match what MySQL expects. " + 
                "\n ->Change input date format to match mysql expected format. (Default for mysql:  YYYY-MM-DD hh:mm:ss for string type or YYYYMMDDhhmmss) " +
                "\nVisit: https://sebhastian.com/mysql-incorrect-datetime-value/  for more info. " 
                );
            else    
                e.printStackTrace();
        }
    
        //generateReport();

        //System.exit(exitCode);

    }

     static void generateReport(){
       
        String[] myArgs = { "-table", "test_table", 
                            "-rows", "100",  
                            "-cols", "name", "age", "email", "dob",  "comments", "sec_email", "third_email",
                            "nameC",
                            "numberC",
                            "emailC",
                            "dateC",
                            "textC",
                            "textC",
                            "textC" 
                            };

        MainCommand mc = new MainCommand();  
        int exitCode = new CommandLine(mc).execute(myArgs);

        try {
            
        FileWriter report = new FileWriter("report.txt", true);
   
        report.write("Perfomance report for Model.generateValue()\n\n");

            for (Object item: mc.getCols()) {
                long startTime = System.nanoTime();
                ((Model)item).generateValue();
                long endTime = System.nanoTime();
                report.write("\nColum: " + ((Model)item).toString());
                report.write("\nDuration: " + (endTime - startTime) + " milliseconds" + "\n----------");
            }

        report.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
     } 



}
