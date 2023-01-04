package com.dbautomation;



public class ConstraintValidator {


    

    public static void validateArguments(int minLength, int maxLength, int TEXT_LOWERBOUND, int TEXT_UPPERBOUND)
            throws IllegalArgumentException {
        if (minLength < TEXT_LOWERBOUND) {
            throw new IllegalArgumentException("Minimum length cannot be less than lowerbound: " + TEXT_LOWERBOUND);
        }

        if (maxLength > TEXT_UPPERBOUND)
            throw new IllegalArgumentException("Maximum length cannot be greater than  upperbound: " + TEXT_UPPERBOUND);

        if (minLength > maxLength)
            throw new IllegalArgumentException("Minimum length cannot be greater than max length");

    }
}
