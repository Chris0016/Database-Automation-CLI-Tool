package com.dbautomation.model;

public class ModelConfigs {

    // ..._s denotes the string version of a non-string variable.
    // The Reason for this is the the parameters sfor picocli options require a
    // fixed string.
    // Hence, having ints and then doing conversion cannot be done.
    // On the other hand just having strings and then converting to int each time
    // would be inefficient

    public static final Integer TEXT_UPPERBOUND = 200;
    public static final Integer TEXT_LOWERBOUND = 4;

    public static final String TEXT_UPPERBOUND_s = "200";
    public static final String TEXT_LOWERBOUND_s = "4";

}
