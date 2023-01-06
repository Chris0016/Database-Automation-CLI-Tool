package com.dbautomation;
import java.io.FileWriter;

import com.dbautomation.database.DBActions;

import picocli.CommandLine;

import com.dbautomation.model.Model;

/*
 * Class for perfomance analysis. 
 * 
 * Use statically to write reports to ./report.txt
 * 
 * 
 */
public class Reports {
    
    static void generateOverallTimeReport(String[] args, String numRows){
        try {

            MainCommand mc = new MainCommand();
            FileWriter report = new FileWriter("report.txt", true);
            report.write("\nBenchmark for inserting " + numRows +" rows in databse.");
            report.write("\nRun Script: " + args);

            long startTime = System.currentTimeMillis();
            DBActions.insertCols(mc.getCols(), mc.getColumnNames() ,mc.getRows() , mc.getTable());
            long endTime = System.currentTimeMillis();
            report.write("\nDuration(1000 rows all columns(except custom)): " + (endTime - startTime) + " milliseconds" + "\n----------");
            report.close();
        } catch (Exception e) {  
                e.printStackTrace();
        }
    }

     static void generateModelTimeReport(String[] args, String numRows){
       
        MainCommand mc = new MainCommand();
        int exitCode = new CommandLine(mc).execute(args);

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
