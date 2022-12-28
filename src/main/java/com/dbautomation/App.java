package com.dbautomation;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        String file = "largeUserFile.txt";
        String filePath = "src/main/java/com/dbautomation/resources/" + file;
        

        boolean isLargeFile = file.equals("largeUserFile.txt");
        boolean fetchInOrder = false;


        String[] myArgs = { "email", "-domains", "blanco.com"};
     
        int exitCode = new CommandLine(new MainCommand()).execute(myArgs);
        System.exit(exitCode);

    }
}
