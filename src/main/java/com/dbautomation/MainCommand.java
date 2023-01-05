package com.dbautomation;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

import com.dbautomation.model.FakeAddress;
import com.dbautomation.model.Model;
import com.dbautomation.model.Custom;
import com.dbautomation.model.TimeCol;
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

    String lcl = "en-US";
    FakeAddress fk = new FakeAddress("", lcl);
   

    @Option(names = { "-table", "--table" }, arity = "1", required = true)
    private String tableName;

    @Option(names = { "-rows", "--rows" }, arity = "1", required = true)
    private int numRows;

    @Option(names = {"-cols", "--colum-names"}, arity = "1...15", required = true)
    private List<String> columnNames;


    

    @Command(name = "nameC")
    public void addName() {
        try {
            columns.add(new Name());
            //System.out.println(new Name().generateValue());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    @Command(name = "textC")
    public void addText(
            @Option(names = { "-mnLen",
                    "minLength" }, arity = "1", defaultValue = ModelConfigs.LOWER_BOUND_STRING) int minLength,
            @Option(names = { "-mxLen",
                    "--maxLength" }, arity = "1", defaultValue = ModelConfigs.UPPER_BOUND_STRING) int maxLength) {

        try {

            columns.add(new Text(minLength, maxLength));

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
    @Command(name = "customC")
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
    @Command(name = "numberC")
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
    @Command(name = "emailC")
    public void addEmail(
        @Option(names = {"-domains", "--domains"}, arity = "1...4") String[] domains, 
        @Option(names = {"-maxLen", "--maxLength"}, arity = "1", defaultValue = ModelConfigs.MAX_EMAIL_LENGTH) int maxLen,
        @Option(names = {"-minLen", "--minLength"}, arity = "1", defaultValue = ModelConfigs.MIN_EMAIL_LENGTH) int minLen
    ){
        try {
            
            if (domains == null)
                columns.add(new Email(minLen, maxLen));
            else
                columns.add(new Email(domains, minLen, maxLen));

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
            System.out.println( e.getMessage());
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
     *       https://www.javatpoint.com/java-simpledateformat
     *       https://stackoverflow.com/questions/4216745/java-string-to-date-conversion 
     *       https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html
     *      
     *   default behavior: Gives a random date from 1/1/1990 to current date.    
     *   
     * 
     */
    @Command(name = "dateC")
    public void addDate(
        @Option(names = {"-from", "--from"}, arity = "1") String startDate,
        @Option(names = {"-to", "--to"}, arity = "1") String endDate,
        @Option(names = {"-format", "--format"}, arity = "1", defaultValue = " YYYY-MM-dd hh:mm:ss" ) String format
    ){
       
        try {

            SimpleDateFormat formatter =  new SimpleDateFormat(format);

            columns.add (
                new TimeCol(
                    (startDate != null)? formatter.parse(startDate) : ModelConfigs.DEFAULT_START_DATE,
                    (endDate != null)? formatter.parse(endDate) : new Date(), //Current Date. 
                    formatter
                )
            );
            //columns.add(new DateCol(startDate, endDate, format));
                  
            //DateCol dc = new DateCol(formatter.parse(startDate), formatter.parse(endDate));
            //DateCol dc = (DateCol)columns.get(columns.size() - 1);
            //System.out.println( dc.generateValue());
            

        } catch (Exception e) {
            
            if (e instanceof ParseException)
                System.out.println("Error Expected input date to be in format \'"+ format + "\' .");
            else
                System.out.println(e.getMessage());
                //e.printStackTrace();

            System.exit(1);
        }
        
    }

    /**
     * 
     * @param startTime
     * @param endTime
     * @param format
     * 
     *  -from
     *  -to
     *  -format
     * 
     *   Same structure as date col. In fact these could be used interchangebly. However, for the sake of
     *  simplicity, they have been broken in two. 
     * 
     *  An example usage of this col would be. timestamp -format "HH:mm:ss Z" 
     *  For more information you can visit the link:
     *      https://www.javatpoint.com/java-simpledateformat
     *      https://stackoverflow.com/questions/4216745/java-string-to-date-conversion 
     *      https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html
     *      
     * 
     */
    @Command(name = "timestampC")
    public void addTimeStamp(
        @Option(names = {"-from", "--from"}, arity = "1") String startTime, 
        @Option(names = {"-to", "--to"}, arity = "1") String endTime, 
        @Option(names = {"-format", "--format"}, arity = "1", defaultValue = "YYYY-MM-dd hh:mm:ss") String format
    ){
        try {

            SimpleDateFormat formatter =  new SimpleDateFormat(format);

            columns.add (
                new TimeCol(
                    (startTime != null)? formatter.parse(startTime) : ModelConfigs.DEFAULT_START_DATE,
                    (endTime != null)? formatter.parse(endTime) : new Date(), //Current Date. 
                    formatter
                )
            );
            //columns.add(new DateCol(startTime, endTime, format));
                  
            //DateCol dc = new DateCol(formatter.parse(startTime), formatter.parse(endDate));
            //TimeCol dc = (TimeCol)columns.get(columns.size() - 1);
            //System.out.println( dc.generateValue());
            

        } catch (Exception e) {
            
            if (e instanceof ParseException)
                System.out.println("Error Expected input date to be in format \'"+ format +"\'.\n Please check \'" + startTime + "\' and \'" + endTime + "\'");
            else
                System.out.println(e.getMessage());
                //e.printStackTrace();

            System.exit(1);
        }
    }

    /**
     * 
     * @param isStreetAddress
     * @param locale
     * 
     * -lcl Locale default is en-US
     * -street: Specify only street name
     */
    @Command(name = "addressC")
    public void addAddress(
        @Option(names = {"-street", "--street-address"}, arity = "0") boolean isStreetAddress,
        @Option(names = {"-lcl", "--locale"}, arity = "1", defaultValue = "en-US") String locale
        
    ){
        if (isStreetAddress)
            columns.add(new FakeAddress("address.streetname", locale));
        else
            columns.add(new FakeAddress("address", locale));
    }

    /**
     * 
     * @param locale
     * 
     *  -lcl Locale default is en-US
     */
    @Command(name = "cityC")
    public void addCity(
        @Option(names = {"-lcl", "--locale"}, arity = "1", defaultValue = "en-US") String locale
        
    ){
        columns.add(new FakeAddress("address.city", locale));

    }

    /**
     * 
     * @param locale
     * 
     *  -lcl Locale default is en-US
     */
    @Command(name = "zipcodeC")
    public void addZipCode(
        @Option(names = {"-lcl", "--locale"}, arity = "1", defaultValue = "en-US") String locale
        
    ){
        columns.add(new FakeAddress("address.zipcode", locale));
    }



    /**
     * 
     * @param locale
     * 
     *  -lcl Locale default is en-US
     */
    @Command(name = "stateC")
    public void addState(
        @Option(names = {"-lcl", "--locale"}, arity = "1", defaultValue = "en-US") String locale
        
    ){
        columns.add(new FakeAddress("address.state", locale));
    }


    /**
     * 
     * @param locale
     * 
     *  -lcl Locale default is en-US
     *  Locale doesn't do anything for country. For consistency purpose it has been kept. 
     */
    @Command(name = "countryC")
    public void addCountry(
        @Option(names = {"-lcl", "--locale"}, arity = "1", defaultValue = "en-US") String locale
        
    ){
        columns.add(new FakeAddress("address.country", locale));
    }


    /**
     * 
     * @param locale
     * 
     *  -lcl Locale default is en-US
     *  Locale doesn't do anything for country. For consistency purpose it has been kept. 
     */
    @Command(name = "timezoneC")
    public void addTimeZone(
        @Option(names = {"-lcl", "--locale"}, arity = "1", defaultValue = "en-US") String locale
        
    ){
        columns.add(new FakeAddress("address.timezone", locale));
    }
    

    private void printColumns() {
        for (Object obj : columns)
            System.out.println(obj.getClass().toString() + "\n");

    }

    public ArrayList<Object> getCols(){
        return this.columns;
    }

    public int getRows(){
        return this.numRows;
    }

    public String getTable(){
        return this.tableName;
    }

    public List<String> getColumnNames(){
        return this.columnNames;
    }



    @Override
    public Integer call() throws Exception {
        
        return 0;
    }

}
