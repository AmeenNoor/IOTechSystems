# Building and Running the Code in Eclipse

Open Eclipse and create a new Java project.

Copy the provided Java code in Exercise02_ Solution Folder and paste it into a new class in the project.

Add the following dependencies to your project:

org.json
Verify that the file path in the following line of code is correct and points to the location of the data.json file on your machine:
Ex: FileReader reader = new FileReader("/Users/ameennoor/Documents/exercises-main/exercise-02/data/data.json");

Run the main method of the class and the program will prompt you to select an option from 0-5.

Select from the following options:

1: Parse the data from json file
2: Discard the devices where the timestamp value is before the current time
3: Get the total of all value entries
4: Parse the uuid from the info field of each entry
5: Output the values total and the list of uuids to json file
0: Exit the system
The selected action will be executed and the results will be displayed in the console.

If you select option 5, a new json file named "output.json" will be created in the project directory.

Note: Make sure that you have the correct dependencies added to the project and the file path is correct to run the code successfully.
