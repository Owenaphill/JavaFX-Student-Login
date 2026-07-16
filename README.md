# JavaFX-Student-Login
A login UI screen made with Java and JavaFX. I built this as a final project for my Computer Science II class. I focused on using what I had learned from Java and new features that JavaFX opened up for me to use.

## Technologies
* Java
* JavaFX

## Features
What the program does is
  * **Entering student information**: Utilizing text boxes and check boxes to add information
  * **Reset button**: If you want to start over you can reset the whole program back to the start
  * **Save button**: Saves the data to a file called "record" that writes what you picked to a text file

## The Process
I started by setting up the border and the promary stage for the pop up window. As well as details for it such as a background color, title, and the size of the window. I then created a vertical box for the future so I can put the information going from top to bottom, as well as the horizontal boxes that will be used so I can put information together.

I next started by adding the text and textfield for entering the student's full name. I align it to the left and add it to the vertical box that holds everthing. I then repeated the process for the student's email address since it was the same.

I then created a Combobox box for the students nationality. It uses a method that I create later and reads from a file called "nations-1.txt" to get the list of nationalities. And then I align it to the left and add it to the vertical box that holds everthing.

I next added a toggle group and radio buttons to check if the student is an undergraduate or graduate. I also added it where it should automatically be toggled to undergraduate once booted, and that only one box can be checked. The user can not check none of them, nor all of them. And then I align it to the left and add it to the vertical box that holds everthing.

Next was adding the transferred checkbox. This simply took adding the checkbox and making sure it was preselected to be unchecked once the program started. And then I align it to the left and add it to the vertical box that holds everthing.

Next was the save and reset buttons. When the save button is pressed it will run a method to save all the information line by line to the "records.txt" file. The reset button will restore everything, such as clearing the text boxes, unclearing the checkboxes, and resetting the nationality. And then I align it to the left and add it to the vertical box that holds everthing.

Finally, I created two methods for the program to use. The first one is called "loadItemsFromFile" with an argument for a ComboBox. This is to load all of the nationalities for the student nationality selection, without it I would have to singlehandedly add all of them to the Combobox. I also have a method called "saveDataFileWriter" which takes the arguments of everything the user picks and saves it to "records.txt"

## What I learned
During the project, I learned some new skills and better improved my own skills, which also helps my creative and critical thinking

### JavaFX and UI Design
  * Creative Thinking: Creating a UI taught me how to make a visually appealing way of showing off a program, rather than just running it from the terminal.

### Using a text file to fill a combo box
  * Critical Thinking: Finding new ways to add items to objects, without just going one by one to add them all.

### Data saving with JavaFX
  * Accuracy: I've become better at using these functions outside of the usual projects I work on, and having to use them for important data saving

### Multiple ways to choose data
  * Exploration: I got to explore more ways to save data and how to logically think about what would work best for what kind of data

## Overall Growth
The project taught me more not only about Java but the UI element of programming. It gave me a base idea on creating UIs and ways for users to interact with my work in new ways.

## How can it be improved?
* Looks very bland, need more flair
* Text is a little big and looks very dark with the background
* Add more data that it can save such as an ID
* Add a way to retrive your data by name

## Running the Project
To run the project in your local environment, follow these steps:

1. Install the ZIP file
2. Find App.Java in JavaFX-Student-Login\demo\src\main\java\com\example
3. Run the file
