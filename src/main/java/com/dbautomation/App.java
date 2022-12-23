package com.dbautomation;

import com.dbautomation.model.Name;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {

        // try {
        // Name nsrc = new Name();
        // System.out.println(nsrc.generateValue().trim());

        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }
        int exitCode = new CommandLine(new MainCommand()).execute(args);
        System.exit(exitCode);

    }
}
