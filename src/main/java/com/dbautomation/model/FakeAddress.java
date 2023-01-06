package com.dbautomation.model;

import java.util.Locale;

import net.datafaker.Address;
import net.datafaker.Faker;


/*
 * One of the considerations was how to generate random addresses?
 * 
 * One option was to maintain multiple files or arrays one that would contain the city, streetname, zipcodes, etc... and then join them together
 * accordingly. 
 * 
 * The other option was datafaker library that was built to generate random data across all sorts of field. Furthermore, 
 * it even supports the Faker.resolve() method where a string of a data type can be passed on like "music.genre" and returns a corresponding val.
 * 
 * The Faker option offered a lot of extension and flexibility and could be used for a lot of different things although it came with more overhead. 
 * Therefore, the flexibility and easibility that Faker offers overode the performance overhead that came along. 
 * 
 * For a comparison the typical columns of generate value last somewhere around 770,980 nanoseconds, while the address took up somewhere about 844,245,172 nanoseconds. 
 * A huge difference but the overall impact is acceptable. 
 * 
 * 
 *   
 */

public class FakeAddress implements Model{
    
    private Address faker;
    private String key;


    public FakeAddress(String key, String locale){
       
        faker = new Faker(new Locale(locale)).address();
        this.key = key;
  
    }

    private String genVal(){

        //preferred:
        //faker.resolve(key);

        switch(key){
            case "address":
                return faker.fullAddress();
            case "address.streetname":
                return faker.streetName();
            case "address.city":
                return faker.city();
            case "address.zipcode":
                return faker.zipCode();
            case "address.state":
                return faker.state();
            case "address.country":
                return faker.country();
            case "address.timezone":
                return faker.timeZone();
            default:
                return faker.fullAddress();
        }
    }

    public String generateValue(){
        return  "\'" + (genVal().replaceAll("\'", "")) +  "\'";
    }
  
    public String toString(){
        return "Address Col(streetname/city/zipcode/state/country/timezone):"
        +"\nKey: " + key;
    }

}


