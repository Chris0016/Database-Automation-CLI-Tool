package com.dbautomation.model;

public class Email implements Model{
    
    
    private String[] domains = {"gmail.com", "outlook.com", "yahoo.com"};
    private int maxLen; 
    private int minLen;


    private Text name;
    private final int MIN_NUMBERS_IN_USERNAME = 1;


    public Email(String[] domains, int minLen, int maxLen) throws Exception{
        this.domains = domains;

        this.maxLen = maxLen;
        this.minLen = minLen;

        if(maxLen < minLen)
            throw new IllegalArgumentException("Error: min length: " + minLen + " is greater than max length " + maxLen);


        //TODO: Fix Bug
        //For input email maxLen 5
        name = new Text(minLen, maxLen - MIN_NUMBERS_IN_USERNAME); //At least 1 number in the username
       
    }

    public Email(int minLen, int maxLen) {
        this.maxLen = maxLen;
        this.minLen = minLen;

        if(maxLen < minLen)
        throw new IllegalArgumentException("Error: min length: " + minLen + " is greater than max length " + maxLen);

        name = new Text(minLen, maxLen - MIN_NUMBERS_IN_USERNAME);
    }


    public String generateValue() {
        String username = name.generateValue();

        double numDigitsAvailable = (maxLen - username.length() + 1) ;
        int digits = (int)( Math.random() * Math.pow(10d, numDigitsAvailable) );
        
        
        //For emails of the same length:
        // int digits += (int)Math.pow(10d, numDigitsAvailable - 1) ; 
        

        String domain = domains[(int)(Math.random() * domains.length)];

        return username + digits + "@" + domain;
    }

    


}
