package com.dbautomation.model;



/*
 * All columns must implement this interface. 
 * 
 * In order to generate the SQL string all columns need to have a set and agreed upon method to genate their corresponding
 * value. All of which are strings. 
 * 
 * 
 * Design considerations:
 *  There was some columns that initally threw exception and therefore could not be cast to the Model interface or implement the model 
 *  interface. For instance, there columns that read from files and therefore there was the possibility of FileNotFoundException 
 *  in case the file was deleted midway. Originally, the contract was that the caller of generateValue would need handle these exceptions.
 *  But this meant that there was uniformity across the Models with some implementing the interface and not others. 
 * 
 *  For the sake of having all Models implement the same interface, the columns that did throw exception  in the generateValue() would need to handle their
 *  own exceptions. This made it so that all Model columns were uniform. 
 * 
 */
public interface Model {

    public String generateValue();

}
