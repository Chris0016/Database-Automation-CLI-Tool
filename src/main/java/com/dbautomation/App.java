package com.dbautomation;

import com.dbautomation.database.DBActions;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {

        //Example
        String table = "test_table";
        String numRows = "100";
        String[] myArgs = { "-table", table, 
                            "-rows", numRows, 
                            "-cols", "name", "age", "email", "dob",  "comments", "sec_email", "third_email",
                            "nameC",
                            "numberC",
                            "emailC",
                            "dateC",
                            "customC", "-src", "src/main/java/com/dbautomation/resources/largeUserFile.txt", "-isLarge",
                            "textC",
                            "textC" 
                            };
        //Example

        MainCommand mc = new MainCommand();  
        int exitCode = new CommandLine(mc).execute(myArgs);

        try {

            System.out.println("Inserting " + mc.getRows() + " row(s) into table \'" + mc.getTable() + "\'");
            DBActions.insertCols(mc.getCols(), mc.getColumnNames() ,mc.getRows() , mc.getTable());
            
        } catch (Exception e) {

            if (e instanceof MysqlDataTruncation)
                System.out.println("Error inserting MySQL statement. The format given for date does not match what MySQL expects. " + 
                "\n ->Change input date format to match mysql expected format. (Default for mysql:  YYYY-MM-DD hh:mm:ss for string type or YYYYMMDDhhmmss) " +
                "\nVisit: https://sebhastian.com/mysql-incorrect-datetime-value/  for more info. " 
                );
            else 
                System.out.println(e.getMessage());
        }

        System.exit(exitCode);

    }

}
