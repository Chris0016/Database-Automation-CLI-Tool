package com.dbautomation.model;

import java.text.DecimalFormat;

public class NumberModel {
    
    private boolean isDecimal;
    private String currency;
    private double max;
    private double min;

    private final String format = "#.###";
    private final DecimalFormat df = new DecimalFormat(format);; 

    public NumberModel( double max, double min, boolean isDecimal, String currency) throws IllegalArgumentException{

        validateArgs(min, max);
        
        this.max = max;
        this.min = min;

        this.isDecimal = isDecimal;
        this.currency = currency;
    }

    private String format(double num){
        return df.format(num);
    }

    private void validateArgs(double min, double max) throws IllegalArgumentException{
        if (min > max)
            throw new IllegalArgumentException("Error in Number column. Min( " + min + " )" + " is greater than the max( "+ max + " )");
    }



    public String generateValue(){
        double val = (Math.random() * (max - min + 1)) + min;
        return currency + ((isDecimal)?  format(val) : (int)val);
    }
}
