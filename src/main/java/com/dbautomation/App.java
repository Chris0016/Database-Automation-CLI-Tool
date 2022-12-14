package com.dbautomation;

import com.dbautomation.database.DBConnection;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try (DBConnection connection = new DBConnection()) {
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("Error unable to connect to database");
            e.printStackTrace();
        }
    }
}
