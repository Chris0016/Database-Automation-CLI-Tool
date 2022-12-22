package com.dbautomation.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;

import com.dbautomation.ConstraintValidator;

public class Text implements StringModelInterface {
    private int minLength;
    private int maxLength;

    private Scanner reader;

    public Text(int minLength, int maxLength) throws IllegalArgumentException {

        // Throws illegal arguement exception if a test is failed.
        ConstraintValidator.validateArguments(minLength, maxLength, ModelConfigs.TEXT_LOWERBOUND,
                ModelConfigs.TEXT_UPPERBOUND);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public Text(String inputDir) throws FileNotFoundException {
        reader = new Scanner(new File(inputDir));
    }

    public String generateValue() {
        if (reader != null) // Select random text from given file
            return "Random word from file"; // TODO

        int length = (int) (Math.random() * (maxLength - minLength + 1)) + minLength;
        return RandomStringUtils.random(length, true, false);

    }
}
