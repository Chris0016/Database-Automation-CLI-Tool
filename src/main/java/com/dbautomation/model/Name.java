package com.dbautomation.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Name implements StringModelInterface {

    private Scanner reader;

    private final String NAME_FILE_PATH = "src/main/java/com/dbautomation/resources/names.txt";
    private final String DELIMETER = ",";
    private int totalNames;

    public Name() throws FileNotFoundException {
        try {
            reader = new Scanner(new File(NAME_FILE_PATH));
            reader.useDelimiter(DELIMETER);
            setTotalNames();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(
                    "Error: Names.txt file not found. File may have been deleted. Visit source code to retrieve file");
        }

    }

    private void setTotalNames() {
        totalNames = Integer.parseInt(reader.next());
    }

    // Generic method to get a radom word from file
    // Note: Remember to reset scanner
    private String getRandomName() throws FileNotFoundException {

        // Scanner needs to be reset before use bc after scanner
        // is closed(and needs to be closed) it needs to be reset.
        resetScanner();

        int index = (int) (Math.random() * totalNames);
        for (int i = 0; i < index; i++) {
            reader.next();
        }
        String choosenName = reader.next().trim(); // .trim() to remove newline character before it

        reader.close();

        return choosenName;
    }

    private void resetScanner() throws FileNotFoundException {

        reader = new Scanner(new File(NAME_FILE_PATH));
        reader.useDelimiter(DELIMETER);
        reader.next(); // Shift one item. First Item in file will be number of names within the file.

    }

    @Override
    public String generateValue() {
        try {
            return getRandomName();
        } catch (FileNotFoundException e) {
            // Names.txt has been deleted mid-program.
            // Error must be handled here.
            // SQL statement cannot be constructucted and therefore program cannot continue.
            System.out.println("Error internal names file has been deleted. Please see source code to obtain file. ");
            System.exit(1);
            return null;
        }

    }

}
