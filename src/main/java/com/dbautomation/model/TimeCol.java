package com.dbautomation.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class TimeCol implements Model{

    private Date startDate;
    private Date endDate;
    private SimpleDateFormat formatter;
   

    public  TimeCol(Date startDate, Date endDate, SimpleDateFormat formatter) throws IllegalArgumentException{
        if (startDate.getTime() > endDate.getTime())
             throw new IllegalArgumentException("Error Start Date \'" + startDate.toString() + "\' is greater than end date \'" + endDate.toString() + "\'.\n->Please specify a later end date using -to <date in format> or an earlier start date using -from <date in format>");
        
        this.startDate = startDate;
        this.endDate = endDate;
        this.formatter = formatter;

        
    }

    public String generateValue(){
        //System.out.println("Start Date: " + startDate.toString());
        //System.out.println("End Date: " + endDate.toString());
        
        Date randomDate =  new Date(ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime()));
        return formatter.format(randomDate);

    }    
    

    public String toString(){
        return "Date:"
        +"\nStart Date: " + startDate.toString()
        +"\nEnd Date: " + endDate.toString()
        +"\nFormat: " + formatter.toString();
    }
    
}
