package com.dbautomation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

import com.dbautomation.model.Custom;
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
        columns.add(new NumberModel(isDecimal, currency, max, min));

        // NumberModel nm = new NumberModel(isDecimal, currency, max, min);

        // for (int i = 0; i < 5; i++) {
        //     System.out.println("Number generated: " + nm.generateValue());
        // }
       
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
