package com.dbautomation.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;

import com.dbautomation.ConstraintValidator;

public class Text implements StringModelInterface {
    private int minLength;
    private int maxLength;

    public Text(int minLength, int maxLength) throws IllegalArgumentException {

        // Throws illegal arguement exception if a test is failed.
        ConstraintValidator.validateArguments(minLength, maxLength, ModelConfigs.TEXT_LOWERBOUND,
                ModelConfigs.TEXT_UPPERBOUND);

        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public String generateValue() {

        int length = (int) (Math.random() * (maxLength - minLength + 1)) + minLength;
        return RandomStringUtils.random(length, true, false);

    }
}
