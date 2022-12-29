package com.dbautomation.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DateCol {

    private Date startDate;
    private Date endDate;

    private static final int DEFAULT_START_YEAR = 90;
    private static final int DEFAULT_START_MONTH = 1;
    private static final int DEFAULT_START_DAY = 1;

    private final String DATE_FORMAT = "MM/dd/yyyy";
    private SimpleDateFormat formatter;



    public  DateCol(String startDate, String endDate, String format) throws ParseException, IllegalArgumentException{

        if (format == null)
            formatter = new SimpleDateFormat(DATE_FORMAT);
        else
            formatter = new SimpleDateFormat(format);


        try {

            this.startDate = (startDate!= null)? formatter.parse(startDate) : new Date(DEFAULT_START_YEAR, DEFAULT_START_MONTH, DEFAULT_START_DAY);
            this.endDate = (endDate != null)? formatter.parse(endDate) : new Date(); //Current Date. 

        } catch (ParseException e) {
            throw new ParseException("Error: Expected format for date in command input in: " + format + "\nVisit for more info: https://stackoverflow.com/questions/4216745/java-string-to-date-conversion", 0);
        }
        

        if (this.startDate.getTime() > this.endDate.getTime())
             throw new IllegalArgumentException("Error Start Date \'" + startDate.toString() + "\' is greater than end date \'" + endDate.toString() + "\'");

    }


    public String generateValue(){
        System.out.println("Start Date: " + startDate.toString());
        System.out.println("End Date: " + endDate.toString());
        
        Date randomDate =  new Date(ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime()));
        return formatter.format(randomDate);

    }    
    
    
}
