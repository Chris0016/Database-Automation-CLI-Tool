package com.dbautomation;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.ObjectUtils.Null;

import com.dbautomation.model.Custom;
import com.dbautomation.model.DateCol;
import com.dbautomation.model.Email;
import com.dbautomation.model.ModelConfigs;
import com.dbautomation.model.Name;
import com.dbautomation.model.NumberModel;
import com.dbautomation.model.Text;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "main", description = "DB Insertion automation", subcommandsRepeatable = true)
public class MainCommand implements Callable<Integer> {


     

    private ArrayList<Object> columns = new ArrayList<>();
   

    // @Option(names = { "-table", "--table" }, arity = "1", required = true)
    // private String tableName;

    // @Option(names = { "-rows", "--rows" }, arity = "1", required = true)
    // private int numRows;

    @Command(name = "name")
    public void addName() {
        try {
            columns.add(new Name());
            System.out.println(new Name().generateValue());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    @Command(name = "text")
    public void addText(
            @Option(names = { "-mnLen",
                    "minLength" }, arity = "1", defaultValue = ModelConfigs.LOWER_BOUND_STRING) int minLength,
            @Option(names = { "-mxLen",
                    "--maxLength" }, arity = "1", defaultValue = ModelConfigs.UPPER_BOUND_STRING) int maxLength) {

        try {

            columns.add(new Text(minLength, maxLength));

            printColumns();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("For help type: \'main -help\'");
            System.exit(1); // Error Invalid Input
        }

    }


    /**
     * 
     * @param inputDir
     * @param isLargeFile
     * @param fetchInOrder
     * 
     * 
     * Custom Col: Col values are selected(can be random with option) from user directed file. If file is not found then the program quits. 
     *  -o : Select values in order. Note that number of items must match number of rows. 
     *  -isLarge: Specifiy if largefile. Program will not parse to determine number of items(Potentially slow IO).
     *            Instead the program expects the first item to be the number of items in the file. 
     *   Default behavior: Values are selected randomly. 
     *
     * 
     */
    @Command(name = "custom")
    public void addCustomColumn(
        @Option(names = { "-src", "--source" }, arity = "1", required = true) String inputDir,
        @Option(names = {"-isLarge", "--isLargeFile" }, arity = "0") boolean isLargeFile, 
        @Option(names = { "-o", "--order" }, arity = "0") boolean fetchInOrder){
            
           
            try {
                columns.add ( new Custom(inputDir, isLargeFile, fetchInOrder));
                
                //Custom cs = new Custom(inputDir, isLargeFile, fetchInOrder);
                // System.out.println("Fetching in order: " + fetchInOrder);
                // for(int i = 0; i < cs.getTotalItems(); i++){
                //     System.out.println( "-> " + cs.generateValue());
                // }

            } catch (FileNotFoundException e){
                System.out.println( e.getMessage());
                System.exit(1);
            } catch (NoSuchElementException e) {
                System.out.println("Error in custom file: " + inputDir 
                    + "\n Tried to retreived an element that does not exist at the end of the ile." 
                    + "\n To fix error: MAKE SURE NUMBER OF ITEMS PROVIDED MATCHES NUMBER OF ACTUAL ITEMS IN FILE");
                System.exit(1);
                }
    }


    /**
     * @param isDecimal
     * @param currency
     * @param max
     * @param min
     * 
     * Number: Using options users can specify whether they want an int or decimal. 
     *  -dec  
     *   -currency -> default of false
     *   -max -> default 100
     *   -min -> default 0
     *
     *   default behavior: an integer > 0 is provided
     */
    @Command(name = "number")
    public void addNumber(
        @Option(names = {"-dec", "--decimal"}, arity = "0") boolean isDecimal, 
        @Option(names = {"-curr", "--currency"}, arity = "1", defaultValue = "") String currency,
        @Option(names = { "-max", "--maximum" }, arity = "1", defaultValue = ModelConfigs.UPPER_BOUND_STRING) double max, 
        @Option(names = {"-min", "--minimum"}, arity = "1", defaultValue = ModelConfigs.LOWER_BOUND_STRING) double min 
    ){
        columns.add(new NumberModel( max, min, isDecimal, currency));

       
    }
    
    /**
     * 
     * @param domains
     * @param maxLen
     * @param minLen
     * 
     * email:
     * -domains <space separated list of acceptable domains> max is 4 
     * -maxLength default is 20
     * -minLength default is 5
     *      Note that max length and minlength only count for every character before the @ symbol.
     * 
     * default behavior: outputs emails containing letters and numbers. At least one letter. 
     * From domains: gmail.com, outlook.com and yahoo.com
     * 
     * Note: There is no error checking on user inputted domains. Since an expected usage of this program may be to test a
     * database against invalid domains. 
     * 
     */
    @Command(name = "email")
    public void addEmail(
        @Option(names = {"-domains", "--domains"}, arity = "1...4") String[] domains, 
        @Option(names = {"-maxLen", "--maxLength"}, arity = "1", defaultValue = ModelConfigs.MAX_EMAIL_LENGTH) int maxLen,
        @Option(names = {"-minLen", "--minLength"}, arity = "1", defaultValue = ModelConfigs.MIN_EMAIL_LENGTH) int minLen
    ){
        try {
            
            if (domains == null)
                columns.add(new Email(maxLen, minLen));
            else
                columns.add(new Email(domains, maxLen, minLen));

            // //Testing:
            // Email e;
            // if (domains == null)
            //     e = new Email(maxLen, minLen);
            // else
            //     e = new Email(domains, maxLen, minLen);

            // for (int i = 0; i < 5; i++) {
            //     System.out.println("Value generated: " + e.generateValue());
            // }   

        } catch (Exception e){
            System.out.println("Error:" + e.getMessage());
            System.exit(1);
        }
       

    }

 

    /**
     * 
     * @param startDate
     * @param endDate
     * 
     *  Date:
     *   -from   Start date 
     *   -to     End date
     *   -format < dd/MM/yyyy, MM/dd/yyyy, ..., MM-dd-yyyy> choose one. 
     * 
     * For more information on date formats:
     *       https://stackoverflow.com/questions/4216745/java-string-to-date-conversion 
     *      https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html
     *      
     *   default behavior: Gives a random date from 1/1/1990 to current date.    
     *   
     * 
     */
    @Command(name = "date")
    public void addEmail(
        @Option(names = {"-from", "--from"}, arity = "1") String startDate,
        @Option(names = {"-to", "--to"}, arity = "1") String endDate,
        @Option(names = {"-format", "--format"}, arity = "1", defaultValue = "MM/dd/yyyy" ) String format
    ){
       
        try {

            SimpleDateFormat formatter =  new SimpleDateFormat(format);

            columns.add (
                new DateCol(
                    (startDate != null)? formatter.parse(startDate) : ModelConfigs.DEFAULT_START_DATE,
                    (endDate != null)? formatter.parse(endDate) : new Date(), //Current Date. 
                    formatter
                )
            );
            //columns.add(new DateCol(startDate, endDate, format));
                  
            //DateCol dc = new DateCol(formatter.parse(startDate), formatter.parse(endDate));
            DateCol dc = (DateCol)columns.get(columns.size() - 1);
            System.out.println( dc.generateValue());
            

        } catch (Exception e) {
            
            if (e instanceof ParseException)
                System.out.println("Error Expected input date to be in format \'"+ format +"\'.\n Please check \'" + startDate + "\' and \'" + endDate + "\'");
            else
                System.out.println(e.getMessage());
                //e.printStackTrace();

            System.exit(1);
        }
        
    }

    private void printColumns() {
        for (Object obj : columns)
            System.out.println(obj.getClass().toString() + "\n");

    }


    @Override
    public Integer call() throws Exception {
        
        return 0;
    }

}
