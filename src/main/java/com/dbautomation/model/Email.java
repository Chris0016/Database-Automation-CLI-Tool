package com.dbautomation.model;

public class Email {
    
    
    private String[] domains = {"gmail.com", "outlook.com", "yahoo.com"};
    private int maxLen; 
    private int minLen;


    private Text name;
    private final int MIN_NUMBERS_IN_USERNAME = 1;


    public Email(String[] domains, int maxLen, int minLen) throws Exception{
        this.domains = domains;
        this.maxLen = maxLen;
        this.minLen = minLen;

        name = new Text(minLen, maxLen - MIN_NUMBERS_IN_USERNAME); //At least 1 number in the username
       
    }

    public Email(int maxLen, int minLen) throws Exception {
        this.maxLen = maxLen;
        this.minLen = minLen;

        name = new Text(minLen, maxLen - MIN_NUMBERS_IN_USERNAME);
    }


    public String generateValue() throws Exception{
        String username = name.generateValue();

        double numDigitsAvailable = (maxLen - username.length() + 1) ;
        int digits = (int)( Math.random() * Math.pow(10d, numDigitsAvailable) );
        
        
        //For emails of the same length:
        // int digits += (int)Math.pow(10d, numDigitsAvailable - 1) ; 
        

        String domain = domains[(int)(Math.random() * domains.length)];

        return username + digits + "@" + domain;
    }

    


}
