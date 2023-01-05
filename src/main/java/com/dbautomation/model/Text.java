package com.dbautomation.model;

import org.apache.commons.lang3.RandomStringUtils;


public class Text implements Model {
    private int minLength;
    private int maxLength;

    public Text(int minLength, int maxLength) throws IllegalArgumentException {

        // Throws illegal arguement exception if a test is failed.
        if (minLength > maxLength)
            throw new IllegalArgumentException("Error: Minimum length: \'" + minLength + "\'cannot be greater than max length: \'" + maxLength + "\'");


        this.minLength = minLength;
        this.maxLength = maxLength;
    }


    public String generateValue() {

        int length = (int) (Math.random() * (maxLength - minLength + 1)) + minLength;
        return   "\'" + RandomStringUtils.random(length, true, false) +  "\'";

    }


    @Override
    public String toString() {
        return "Text:"
            +"\nminLength: " + minLength 
            +"\nmaxLength: " + maxLength;
    }


    
}
