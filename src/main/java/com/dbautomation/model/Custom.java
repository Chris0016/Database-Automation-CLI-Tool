package com.dbautomation.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Custom { // Implements?

    private File inputFile;
    private Scanner reader;
    private int numberOfItems;

    private boolean isLargeFile; // Implies that first item in file is the number of items in the file.

    private boolean fetchInOrder;
    private int currentItemIndex; // If traversing in order then necessary to keep count of last position.

    public Custom(String inputDir, boolean isLargeFile, boolean fetchInOrder) throws FileNotFoundException {
        try {
            inputFile = new File(inputDir);
            reader = new Scanner(inputFile);

            this.isLargeFile = isLargeFile;
            this.fetchInOrder = fetchInOrder;

            findNumberofItems();
            System.out.println("Num Items: " + numberOfItems);
            System.out.println("Is large: " + isLargeFile);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(
                    "Error the input file: \'" + inputDir +  "\' \nCannot be found.");
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                "Error in file: \'" + inputDir + "\' Expected first item to be a number with flag -isLarge"
            );
        }

    }

    
    private void findNumberofItems() throws FileNotFoundException, NumberFormatException {

        if (isLargeFile) {

            //String givenVal = reader.next();
            // System.out.println("Given number of values: " + givenVal);

            reader = new Scanner(inputFile);

            numberOfItems = Integer.parseInt(reader.nextLine()); // First value must be a number
            reader.close();
            return;
        }

        // Find the number of items in the file.
        reader.reset();

        while (reader.hasNext()) {
            numberOfItems++;
            reader.nextLine();
        }

        //System.out.println(inputFile.getName() + ": Total items Found: " + numberOfItems);
        reader.close();

    }

    private void resetScanner() throws FileNotFoundException {
        reader = new Scanner(inputFile);
        if (isLargeFile)
            reader.nextLine(); //Skip first item. Is metadata. 
        
        
    }



    public String generateValue() throws FileNotFoundException, NoSuchElementException {
        // Handle exception in caller for isLargeFile where number of items given does
        // not match the actual number of items in file(numItemsGiven > numItems).
        // Therefore a NoSuchElementException occurrs while iterating through the file.

        resetScanner();
        String itemToGive;

        if (fetchInOrder) {

            for (int i = 0; i < currentItemIndex; i++) {
                reader.nextLine();
            }

            itemToGive = reader.nextLine();
            reader.close();
            currentItemIndex++;
            return itemToGive;
        }

        // Return random item from file


        // item is fetched zero-index based,do not add 1 to call and save .next in a
        // variable
        int itemIdx = (int) (Math.random() * (numberOfItems));

 
        //System.out.println("Item idx: " + itemIdx);

        for (int i = 0; i < itemIdx ; i++) {
            reader.nextLine();
        }

        itemToGive = reader.nextLine();
        reader.close();

        return itemToGive;
    }


    public int getTotalItems() {
        return numberOfItems;
    }

}
