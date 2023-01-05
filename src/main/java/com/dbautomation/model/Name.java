package com.dbautomation.model;

public class Name implements Model{

    public Name() {

    }

    public String generateValue(){

        return "\'" + ModelConfigs.names[(int)(Math.random() * ModelConfigs.names.length)] + "\'";
    }

    public String toString(){
        return "Name: <OK: No internal details>";
        
    }

}
