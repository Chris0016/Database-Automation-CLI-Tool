# Database Automation Tool

Essentially this program is a tool to insert random data into tables. The kind of data can be customized in a variety of ways and order. This is is all done as a command that is carried out. The project was built using Picocli.

## So why use this tool?

Most often, the data across different tables is the same such as name, email, phone numbers, or money and so on. This program takes the approach treating those column values as reusable entities that can be shared and reused across different tables. What this implies is that we can build a variety of tables with different values using discrete units. Furthermore, you won't need to download massive csv files for every table.

<br />


## Usage

Example

    Java -table myTable -rows 100 -cols name lastname email age address -nameC -nameC -emailC -numberC -min 18 -max 100 -addressC

<br />

Required Options:

Table

    -table: Table to insert in

Rows

    -rows: Number of rows to add

Column Names

    -cols: Space separated list of columns names. Must match the number of args. Min is 1 and Max is 15. 



## Commands
nameC

textC

    -mnLen: minimum length (optional)
    -mxLen: max length (optional)

    Non-numeric random string of characters is generated. Users can specify max or minimum length.  Default min is 4 and Default max is 200. 

customC: 

        -src: REQUIRED. Path of input file relative to root dir. 
        -isLarge: Specifiy if largefile. Program will not parse to determine number of items. Instead the program expects the first item to be the number of items in the file. 

        -o : Select values in order. Not that number of items must match number of rows. 

        Default behavior: Values are selected randomly. 
     

*Col values are selected(can be random with option) from user directed file. If file is not found then the program quits.*
        
    Number: Using options users can specify whether they want an int or decimal. 
        -dec : SPecify if values should be decimals. 
        -curr: default of falseM
        -max: default 100
        -min: default 0

        default behavior: an integer > 0 is provided.

emailC

    -domains <space separated list of acceptable domains> max accepted is 4. 
    -maxLength
    -minLength

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
    -format < dd/MM/yyyy, MM/dd/yyyy, ..., MM-dd-yyyy> make your own. Default format is 'YYYY-MM-dd hh:mm:ss'.

    default behavior: Gives a random date from 1/1/1990 to current date. 
   

    Same as Date but for sake of clarity to separate commands are made. 
    
*Make sure the format matches the one expected by the database.*

*If the format option is given then the start and endDate arguements need to follow the same format.*


addressC:

    -lcl: Locale default is en-US
    -street: Specify only street name

cityC:

    -lcl: Locale default is en-US

zipcodeC:

    -lcl: Locale default is en-US
    
stateC:

    -lcl: Locale default is en-US

countryC:

    -lcl: Locale default is en-US

timezoneC:

    -lcl: Locale default is en-US

## Version 1.0.0:

<br />
<br />
