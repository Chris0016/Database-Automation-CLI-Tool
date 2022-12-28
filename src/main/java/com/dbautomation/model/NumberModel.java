package com.dbautomation.model;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class NumberModel {
    
    private boolean isDecimal;
    private String currency;
    private double max;
    private double min;

    private final String format = "#.###";
    private final DecimalFormat df = new DecimalFormat(format);; 

    public NumberModel(boolean isDecimal, String currency, double max, double min){
        this.isDecimal = isDecimal;
        this.currency = currency;
        this.max = max;
        this.min = min;
    }

    public NumberModel(boolean isDecimal, double max, double min){
        this.isDecimal = isDecimal;
        currency = "";
        this.max = max;
        this.min = min;

    }

    private String format(double num){
        return df.format(num);
    }

    

    public String generateValue(){
        double val = (Math.random() * (max - min + 1)) + min;
        return currency + ((isDecimal)?  format(val) : (int)val);
    }
}
