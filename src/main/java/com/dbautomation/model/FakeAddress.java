package com.dbautomation.model;

import java.util.Locale;

import net.datafaker.Address;
import net.datafaker.Faker;

public class FakeAddress implements Model{
    
    private Address faker;
    private String key;


    public FakeAddress(String key, String locale){
       
        faker = new Faker(new Locale(locale)).address();
        this.key = key;
  
    }

    public String generateValue(){

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

    public String toString(){
        return "Address Col(streetname/city/zipcode/state/country/timezone):"
        +"\nKey: " + key;
    }

}


