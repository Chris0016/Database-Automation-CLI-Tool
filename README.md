# Database Automation Tool

A tool to insert random data into tables. The kind of data can be customized in a variety of ways and order as the user sees fit. 
## So why use this tool?

Most often, the data across different tables is the same such as name, email, phone numbers, or money and so on. This program takes the approach treating those columns as objects with properties. What this implies is that we can fill tables in a parametize manner and quickly change those parameters for testing. Furthermore, you won't need to download massive csv files for every table.

<br />

## Usage

Configure 'config.properties' file for database connection parameters. Each column is passed as a sub-command. Subcommands can have arguements or no arguements attached. 

Example:

    Java -table myTable -rows 100 -cols name lastname email age address -nameC -nameC -emailC -numberC -min 18 -max 100 -addressC

<br />

### Required Options:

Table

    -table <table name>: Table to insert in

Rows

    -rows <integer>: Number of rows to add

Column Names

    -cols: <col names>: Space separated list of columns names. Must match the number of args. Min is 1 and Max is 15. 

## Commands

nameC

textC

    -mnLen <integer > 0>: minimum length (optional)
    -mxLen <integer > mnLen> : max length (optional)

    default behavior: Non-numeric random string of characters is generated. Users can specify max or minimum length.  Default min is 4 and Default max is 200. 

customC: 

        -src <file path>: REQUIRED. Path of input file relative to root dir. 
        -isLarge: Specifiy if largefile. Program will not parse to determine number of items. Instead the program expects the first item to be the number of items in the file. 

        -o : Select values in order. Not that number of items must match number of rows. 

        default behavior: Values are selected randomly. 
     

*Col values are selected(can be random with option) from user directed file. If file is not found then the program quits.*
        
    Number: Using options users can specify whether they want an int or decimal. 
        -dec : SPecify if values should be decimals. 
        -curr <currency to use>: default is none. -> ex: -curr "$"
        -max <max value>: default 100
        -min <min value>: default 0

        default behavior: an integer > 0 is provided.

emailC

    -domains <space separated list of acceptable domains> max accepted is 4. 
    -maxLength <integer > minLength>
    -minLength <integer > 0>

    default behavior: outputs emails containing letters, numbers, underscores, and dots. At least one letter. From domains: gmail.com, outlook.com and yahoo.com
        
dateC:

    -from: <start date> 
    -to: <end date>
    -format < dd/MM/yyyy, MM/dd/yyyy, ..., MM-dd-yyyy> make your own. Default format is 'YYYY-MM-dd hh:mm:ss'.

    default behavior: Gives a random date from 1/1/1990 to current date. 
    If the format option is given then the start and endDate arguements need to follow the same format. 


 *Make sure the format matches the one expected by the database.*

 *If the format option is given then the start and endDate arguements need to follow the same format.*
Timestamp

    -from: <start time> 
    -to: <end time>
    -format < dd/MM/yyyy, MM/dd/yyyy, ..., MM-dd-yyyy>: Input your own. Default format is 'YYYY-MM-dd hh:mm:ss'.

    default behavior: Gives a random date from 1/1/1990 to current date. 

    Same as Date but for sake of clarity to separate commands are made. 
    
*Make sure the format matches the one expected by the database.*

*If the format option is given then the start and endDate arguements need to follow the same format.*

Formatting guide:

* https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html
* https://www.javatpoint.com/java-simpledateformat
* https://stackoverflow.com/questions/4216745/java-string-to-date-conversion 


addressC:

    -lcl <string locale>: Locale default is en-US -> ex: -lcl en-GB
    -street <string streetname>: Specify only street name

cityC:

    -lcl <string locale>: Locale default is en-US -> ex: -lcl en-GB

zipcodeC:

    -lcl <string locale>: Locale default is en-US -> ex: -lcl en-GB
    
stateC:

    -lcl <string locale>: Locale default is en-US -> ex: -lcl en-GB

countryC:

    -lcl <string locale>: Locale default is en-US -> ex: -lcl en-GB

timezoneC:

    -lcl <string locale>: Locale default is en-US -> ex: -lcl en-GB

## Version 1.0.0:

The project was built using Picocli.

