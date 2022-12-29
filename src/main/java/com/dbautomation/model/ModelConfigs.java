package com.dbautomation.model;

import java.util.Calendar;
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


    private static final int DEFAULT_START_YEAR = 1990;
    private static final int DEFAULT_START_MONTH = 1;
    private static final int DEFAULT_START_DAY = 1;
    

    public static final Date DEFAULT_START_DATE = new Date(DEFAULT_START_YEAR, DEFAULT_START_MONTH, DEFAULT_START_DAY);  //Calendar.getInstance().set(DEFAULT_START_YEAR, DEFAULT_START_MONTH, DEFAULT_START_DAY);;
    public static final Date DEFAULT_END_DATE = new Date();
    


}
