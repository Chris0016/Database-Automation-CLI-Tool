package com.dbautomation.model;

import java.util.Date;

public class ModelConfigs {

    // ..._s denotes the string version of a non-string variable.
    // The Reason for this is the the parameters sfor picocli options require a
    // fixed string.
    // Hence, having ints and then doing conversion cannot be done.
    // On the other hand just having strings and then converting to int each time
    // would be inefficient

    public static final Integer TEXT_UPPERBOUND = 200;
    public static final Integer TEXT_LOWERBOUND = 1;

    public static final String UPPER_BOUND_STRING = "200";
    public static final String LOWER_BOUND_STRING = "1";


    public static final String MAX_EMAIL_LENGTH = "20";
    public static final String MIN_EMAIL_LENGTH = "5";


    private static final int DEFAULT_START_YEAR = 90;
    private static final int DEFAULT_START_MONTH = 0;
    private static final int DEFAULT_START_DAY = 1;

    private static final int DEFAULT_START_HOUR = 0;
    private static final int DEFAULT_START_MINUTE = 0;
    private static final int DEFAULT_START_SECOND = 0;
    
    public static final Date DEFAULT_START_DATE = new Date(
        DEFAULT_START_YEAR, 
        DEFAULT_START_MONTH, 
        DEFAULT_START_DAY, 
        DEFAULT_START_HOUR, 
        DEFAULT_START_MINUTE, 
        DEFAULT_START_SECOND);  //Calendar.getInstance().set(DEFAULT_START_YEAR, DEFAULT_START_MONTH, DEFAULT_START_DAY);
        

    //private static String[] DOMAINS = {"gmail.com", "outlook.com", "yahoo.com"};

}
