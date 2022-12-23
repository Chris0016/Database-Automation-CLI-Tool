package com.dbautomation;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.dbautomation.model.ModelConfigs;
import com.dbautomation.model.Name;
import com.dbautomation.model.Text;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "main", description = "DB Insertion automation", subcommandsRepeatable = true)
public class MainCommand implements Callable<Integer> {

    private ArrayList<Object> columns = new ArrayList<>();

    // @Option(names = { "-table", "--table" }, arity = "1", required = true)
    // private String tableName;

    // @Option(names = { "-rows", "--rows" }, arity = "1", required = true)
    // private int numRows;

    @Command(name = "name")
    public void addName() {
        try {
            columns.add(new Name());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    // ... More comands here

    @Command(name = "text")
    public void addText(
            @Option(names = { "-mnLen",
                    "minLength" }, arity = "1", defaultValue = ModelConfigs.TEXT_LOWERBOUND_s) int minLength,
            @Option(names = { "-mxLen",
                    "--maxLength" }, arity = "1", defaultValue = ModelConfigs.TEXT_UPPERBOUND_s) int maxLength) {

        try {

            columns.add(new Text(minLength, maxLength));

            printColumns();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("For help type: \'main -help\'");
            System.exit(1); // Error Invalid Input
        }

    }

    private void printColumns() {
        for (Object obj : columns)
            System.out.println(obj.getClass().toString() + "\n");

    }

    @Override
    public Integer call() throws Exception {

        return 0;
    }

}
