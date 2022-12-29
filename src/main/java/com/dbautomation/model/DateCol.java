package com.dbautomation.model;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DateCol {

    private Date startDate;
    private Date endDate;

    private static final int DEFAULT_START_YEAR = 90;
    private static final int DEFAULT_START_MONTH = 1;
    private static final int DEFAULT_START_DAY = 1;


    public DateCol(Date startDate, Date endDate) {


        this.startDate = (startDate != null)? startDate :  new Date(DEFAULT_START_YEAR, DEFAULT_START_MONTH, DEFAULT_START_DAY);;
        this.endDate = (endDate != null)? endDate: new Date(); //Current Date. 


        if (this.startDate.getTime() > this.endDate.getTime())
             throw new IllegalArgumentException("Error Start Date \'" + startDate.toString() + "\' is greater than end date \'" + endDate.toString() + "\'");

    }


    public String generateValue(){
        System.out.println("Start Date: " + startDate.toString());
        System.out.println("End Date: " + endDate.toString());
        
        Date randomDate =  new Date(ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime()));
        return randomDate.getMonth() + "/"+ randomDate.getDate() + "/" +  (randomDate.getYear()+ 1900);

    }    
    
    
}
