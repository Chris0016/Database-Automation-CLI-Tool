package com.dbautomation;

import com.dbautomation.model.Custom;
import com.dbautomation.model.Name;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        String filePath = "dbautomationtool/src/main/java/com/dbautomation/resources/customvalues.txt";
        boolean isLargeFile = true;
        boolean fetchInOrder = false;

        try {
            Custom cs = new Custom(filePath, isLargeFile);

            if (fetchInOrder)
                cs.fetchInOrder();

            System.out.println("Val fetched: " + cs.generateValue());

        } catch (Exception e) {
            e.printStackTrace();
        }

        // int exitCode = new CommandLine(new MainCommand()).execute(args);
        // System.exit(exitCode);

    }
}
