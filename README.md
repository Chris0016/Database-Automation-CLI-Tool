# Database Automation Tool

Essentially this program is a tool to insert random data into tables. The kind of data can be customized in a variety of ways and order. This is is all done as a command that is carried out. The project was built using Picocli.

## So why use this tool?

Most often, the data across different tables is the same such as name, email, phone numbers, or money and so on. This program takes the approach treating those column values as reusable entities that can be shared and reused across different tables. What this implies is that we can build a variety of tables with different values using discrete units. Furthermore, you won't need to download massive csv files for every table.

<br />
<br />

## Instructions to use:

    In progress

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
