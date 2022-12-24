# Database Automation Tool

Essentially this program is a tool to insert random data into tables. An alternative to using this program is to simply download a csv file fo data and import it into the table.
 
## So why use this tool?
Most often, the data across different tables is the same such as name, email, phone numbers, or money and so on. This program takes the approach treating those column values as reusable entities that can be shared and reused across different tables. What this implies is that we can build a variety of tables with different values using discrete units. Furthermore, you won't need to download massive csv files for every table.

## How to use?
Download or create a single column csv file. This csv file will then be transformed into an object-like entity in the program that can be used to put it in several tables. For instance, downloading a list of names will then be used to create a table that is a combination of other entities like phone numbers and emails. 


<br />
<br />

## Instructions to use:
- Update Configs Files to your database. 

- Change the files(filename and content or add files) in the resources accordingly to the column values you'd like.

- Load the files using the bLoader utility. 

- Create a 2d array containing all the columns in order. 

Order is important for array that holds all the strings. As they will be loaded in order. 

- Update the current highest primary key value(pkValue) count. This number specifies the next aviable value of the primary key in your table. This number has to be updated manually. 

<br />
<br />

## Version 1.1.0:

Currently there are some issues or things that can be done better in the program.
For instance:
	-Some column values cannot be duplicate so selecting at random will inevitably cause a collision. Once that collision occurs the program comes to a halt. 
	
	To solve this, add error handling if mySQL gives this specific type of error and then run again. Reduce the pkValue in the function when the error occurs.


	-Instead of having to manually update the current pkValue in the primary function, automatically retrieve the highest  pkValue on a given table. -> Maybe a custom script? 

	-Some pk values are not numbers(or even increment sequentially). Hence, having the 
	option to treat the pkValues columns as an entity of the users choice is also a good idea. 

<br />
<br />

## Version 2.0:

	-GUI interface might make the program easier? Web or native app? 