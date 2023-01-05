package com.dbautomation.model;

import org.apache.commons.lang3.RandomStringUtils;

public class Email implements Model{
    
    
    private String[] domains = {"gmail.com", "outlook.com", "yahoo.com"};
    private int maxLen; 
    private int minLen;


    public Email(String[] domains, int minLen, int maxLen) throws Exception{
        

        if(maxLen < minLen)
            throw new IllegalArgumentException("Error in Email Col: min length: " + minLen + " is greater than max length " + maxLen);

        this.domains = domains;

        this.maxLen = maxLen;
        this.minLen = minLen;
       
    }

    public Email(int minLen, int maxLen) {
        if(maxLen < minLen)
            throw new IllegalArgumentException("Error in Email Col: min length: " + minLen + " is greater than max length " + maxLen);

        this.maxLen = maxLen;
        this.minLen = minLen;
    }

    public String generateValue() {

        int length = (int) (Math.random() * (maxLen - minLen+ 1)) + minLen; 
        return  "\'" +  RandomStringUtils.random(length, true, true) + "@" + domains[(int)(Math.random() * domains.length)] + "\'";
    }

    
    public String toString(){
        return "Email: "
        + "\nMinimum Length: " + minLen
        +  "\nMax Length: " + maxLen
        + "\nDomains: " + domains.toString();
    }



}
