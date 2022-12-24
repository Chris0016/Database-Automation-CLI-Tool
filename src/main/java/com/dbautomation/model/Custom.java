package com.dbautomation.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Custom { // Implements?

    private File inputFile;
    private Scanner reader;
    private final String DELIMETER = ",";
    private int numberOfItems;

    private boolean isLargeFile; // Implies that first item in file is the number of items in the file.

    private boolean fetchInOrder = false;
    private int currentItemIndex; // If traversing in order then necessary to keep count of last position.

    public Custom(String inputDir, boolean isLargeFile) throws FileNotFoundException {
        try {
            inputFile = new File(inputDir);
            reader = new Scanner(inputFile);
            reader.useDelimiter(DELIMETER);

            this.isLargeFile = isLargeFile;

            findNumberofItems();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(
                    "Error the input file provided cannot be found. Please double check path given for custom. ");
        }

    }

    // Will start from beginning. Be WARRY NOT TO CALL MID PROCESS AS THE ITEM INDEX
    // IS RESET.
    public void fetchInOrder() {

        this.fetchInOrder = true;
        if (isLargeFile)
            currentItemIndex = 1; // We expect the first item to be metadata(number of actual values).
    }

    private void findNumberofItems() throws FileNotFoundException {

        if (isLargeFile) {

            // String givenVal = reader.next();
            // System.out.println("Given number of values: " + givenVal); // Maybe needs a
            // .trim() for new line characters
            reader = new Scanner(inputFile);
            reader.useDelimiter(DELIMETER);

            numberOfItems = Integer.parseInt(reader.next()); // First value must be a number
            reader.close();
            return;
        }

        // Find the number of items in the file.
        resetScanner();

        while (reader.hasNext()) {
            numberOfItems++;
            reader.next();
        }

        System.out.println(inputFile.getName() + ": Total items Found: " + numberOfItems);
        reader.close();

    }

    private void resetScanner() throws FileNotFoundException {
        reader = new Scanner(inputFile);
        reader.useDelimiter(DELIMETER);
        if (isLargeFile)
            reader.next(); // First item is expected to be the number of items in the file.

    }

    // ADD ME TO CALLER
    // try {
    // } catch (NoSuchElementException e) {
    // throw new NoSuchElementException("Error in custom file: " +
    // inputFile.getName()
    // + "\n Tried to retreived an element that does not exist at the end of the
    // file."
    // + "\n To fix error: MAKE SURE NUMBER OF ITEMS PROVIDED MATCHES NUMBER OF
    // ACTUAL ITEMS IN FILE");
    // }
    // ADD ME TO CALLER

    public String generateValue() throws FileNotFoundException {
        // Handle exception in caller for isLargeFile where number of items given does
        // not match the actual number of items in file(numItemsGiven > numItems).
        // Therefore a NoSuchElementException occurrs while iterating through the file.

        resetScanner();
        String itemToGive;

        if (fetchInOrder) {

            for (int i = 0; i < currentItemIndex; i++) {
                reader.next();
            }
            itemToGive = reader.next();
            reader.close();
            currentItemIndex++;
            return itemToGive;
        }

        // Return random item from file

        // item is fetched zero-index based,do not add 1 to call and save .next in a
        // variable
        int itemIdx = (int) (Math.random() * (numberOfItems + 1));

        // Start from the second item if its a large file.
        // Item at zero-th idx is metadata
        // ...)? 1 : 0; however more readeable with variable names.
        itemIdx = (isLargeFile && itemIdx == 0) ? itemIdx++ : itemIdx;

        for (int i = 0; i < itemIdx - 1; i++) {
            reader.next();
        }

        itemToGive = reader.next();
        reader.close();

        return itemToGive;
    }

}
