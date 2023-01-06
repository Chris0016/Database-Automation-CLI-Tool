package com.dbautomation.database;

import java.util.ArrayList;
import java.util.List;

import com.dbautomation.model.Model;


public class DBActions {

    /**
     * 
     * @param cols
     * @param columnNames
     * @param numRows
     * @param table
     * 
     * 
     * Method for inserting columns into appropriate table. 
     * 
     * Database information is created and stored in the DBConnection class. 
     * 
     * Initally the mechanism for inserting information in the database was a single statement at a time generated
     * and inserted. Essentially one statement for every row. 
     * 
     * However, that method turned out to be extremely slow, with up to 2.5 minutes for 1,000 rows. 
     *  
     * Instead, opted for only one SQL query to be inserted that contained all the rows. 
     * Performance is much more efficient and now only takes second(s) for a 1000 rows. 
     * 
     * Mechanism in detail:
     * 
     * Each Col implemnents the Model interface -> generateValue() method. Depending on what the user selected each modlel
     * is put into an array. Afterwards the array is traversed for the number of rows calling generateVal() to create the 
     * final sql query in a string format.
     * 
     * The string query is passed on to the insertSQL method from the DBConnection class which is just responsible for executing 
     * the insert sql statement. Note the DBConnection.insertSQL() expoects the query to be an insert statement. Otherwise a runtime exception occurs. 
     * 
     *  
     * getVals() : Generates the values for a single row separated by commas. 
     * parseColumnNames(): Takes all the column names and surrounds them in backticks(`). 
     * insertCols(): Generates the entire SQL string using the values generated from getVals() and parseColumns(). 
     * 
     * 
     */
    public static void insertCols( ArrayList<Object> cols, List<String> columnNames, int numRows, String table ){
        String query =  "INSERT INTO " + table + " (" + parseColumnNames(columnNames) + ")" + " VALUES ";
    

        try (DBConnection con = new DBConnection()) {
            for (int i = 0; i < numRows; i++) 
                query +=  "(" + getVals(cols) + "),";
     
            query = query.substring(0, query.length() - 1); //remove last comma
            query += ";";

            //System.out.println("Query: \n" + query);
            con.insertSQL(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param cols
     * @return  A String representation of all the colums values for a single row separated by commas. 
     * 
     *  The entire operation of generating the SQL string could be done in one mehthod. However, for the sake of readability 
     *  the functionality has been broken down into 3 methods:
     * 
     * 
     *  Why? This method really acts as a helper method to make the mechanism more readable.
     * 
     * 
     */

    private static String getVals(ArrayList<Object> cols){

        String vals = ((Model)cols.get(0)).generateValue();
        
        for( int i = 1; i < cols.size(); i++)
            vals += ", "  + ((Model)cols.get(i)).generateValue();
        
        return vals;
    }

    /**
     * 
     * @param locn
     * @return A string with all the columns names separated by commas and surrounded by backticks(`). 
     * 
     *  Why? This method really acts as a helper method to make the mechanism more readable.
     * 
     * 
     */
    private static  String parseColumnNames(List<String> locn){
        String vals = "`" + locn.get(0) + "`";
        
        for( int i = 1; i < locn.size(); i++)
            vals += ", " + "`" + locn.get(i) + "`";
        
        return vals;
    }

}
