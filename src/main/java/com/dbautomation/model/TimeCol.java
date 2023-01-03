package com.dbautomation.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class TimeCol implements Model {

    private Date startDate;
    private Date endDate;
    private SimpleDateFormat formatter;
   

    public  TimeCol(Date startDate, Date endDate, SimpleDateFormat formatter) throws IllegalArgumentException{
        this.startDate = startDate;
        this.endDate = endDate;
        this.formatter = formatter;

        if (this.startDate.getTime() > this.endDate.getTime())
             throw new IllegalArgumentException("Error Start Date \'" + startDate.toString() + "\' is greater than end date \'" + endDate.toString() + "\'");

    }


    @Override
    public String generateValue(){
        System.out.println("Start Date: " + startDate.toString());
        System.out.println("End Date: " + endDate.toString());
        
        Date randomDate =  new Date(ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime()));
        return formatter.format(randomDate);

    }    
    
    
}
