package com.dbautomation;

import java.util.ArrayList;

import com.dbautomation.model.ModelConfigs;
import com.dbautomation.model.Text;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "main", description = "Says Hello")
public class MainCommand implements Runnable {

    private ArrayList<ArrayList<Object>> columns;
    private int currrentColumn = 0;

    @Command(name = "name")
    public Integer addName() {
        System.out.println("Name()");
        return 0;
    }

    @Command(name = "text")
    public void addText(
            @Option(names = { "-mnLen",
                    "minLength" }, arity = "1", defaultValue = ModelConfigs.TEXT_LOWERBOUNDs_s) int minLength,
            @Option(names = { "-mxLen",
                    "--maxLength" }, arity = "1", defaultValue = ModelConfigs.TEXT_UPPERBOUND_s) int maxLength,
            @Option(names = { "-src", "--source" }, arity = "1") String inputDir) {

        try {
            Text txt = new Text(minLength, maxLength);

            // TEMPORARY GRID INITIALIZATION
            for (int i = 0; i < 5; i++) {
                columns.add(new ArrayList<Object>());
            }
            // TEMPORARY GRID INITIALIZATION

            for (int i = 0; i < 5; i++) {
                columns.get(currrentColumn).add(txt.generateValue());
            }

            printColumns();

            // currrentColumn++;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("For help type: \'main -help\'");
            System.exit(1); // Error Invalid Input
        }

    }

    private void printColumns() {
        for (ArrayList<Object> col : columns) {
            for (Object obj : col)
                System.out.print(obj.toString() + "\n");
            System.out.println("___");
        }

    }

    @Override
    public void run() {
        System.out.println("run()");
    }
}
