package com.dbautomation.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Word implements StringModelInterface {

    private Scanner reader;

    private final String WORD_FILE_PATH = "./some/file/path";

    public Word(String inputDir) throws FileNotFoundException {
        reader = new Scanner(new File(inputDir));
    }

    public Word() throws FileNotFoundException {
        try {
            reader = new Scanner(new File(WORD_FILE_PATH));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(
                    "Error: word file not found. File may have been deleted. Visit source code to retrieve file");
        }

    }

    // Generic method to get a radom word from file
    // Note: Remember to reset scanner
    private String getRandomWord(Scanner inputFile) {
        return "Imaginary random Gotten word...";
    }

    // TODO
    @Override
    public String generateValue() {
        if (reader == null)
            // return getRandomWord(reader)
            return "Get word from private input file";
        return "Get word fromuser input file";
    }

}
