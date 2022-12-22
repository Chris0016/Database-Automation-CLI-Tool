package com.dbautomation;

import picocli.CommandLine;

public class App {
    // @Spec
    // CommandSpec spec;

    public static void main(String[] args) {

        CommandLine.run(new MainCommand(), args);

    }
}
