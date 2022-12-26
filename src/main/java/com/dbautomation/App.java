package com.dbautomation;

import com.dbautomation.model.Custom;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        String file = "largeUserFile.txt";
        String filePath = "src/main/java/com/dbautomation/resources/" + file;
        

        boolean isLargeFile = file.equals("largeUserFile.txt");
        boolean fetchInOrder = false;
// 
        // try {
            // Custom cs = new Custom(filePath, isLargeFile);
// 
            // if (fetchInOrder)
                // cs.fetchInOrder();
// 
            // System.out.println("Val fetched: " + cs.generateValue());
// 
        // } catch (Exception e) {
            // e.printStackTrace();
        // }

        String[] myArgs = { "name", "custom", "-src", filePath, "-isLarge", "-o"};

        int exitCode = new CommandLine(new MainCommand()).execute(myArgs);
        System.exit(exitCode);

    }
}
