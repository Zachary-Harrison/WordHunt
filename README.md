# WordHunt

This program is designed to solve word connect games similar to Boggle or WordHunt on the GamePigeon iOS extension. Because speed is critical, it stores words in an AVL tree and uses a binary search algorithm to find potential words within a given 4x4 grid, though it can be modified to work for any square grid.

> $\color{red} \text{Disclaimer}$: This program is not guaranteed to find *every* word in your puzzle, nor does every word it finds necessarily exist for your game. GamePigeon, for instance, uses a private dictionary that differs from the one I used: Collins Scrabble Words (2019).


## Usage


### Building and Running as an IntelliJ Project

To use the WordHunt Solver, follow these steps:

1. Clone this repo using:
   ```bash
   git clone https://github.com/Zachary-Harrison/WordHunt.git
   ```
2. Open the project in IntelliJ IDEA.
3. Click on `File` > `Open`, navigate to the project directory, and click `OK`.
4. Once the project has loaded, click on `Run` > `Run Main` or press `Shift` + `F10` to start the program.
   > $\color{lightgreen} \text{Tip}$: The width and height of the puzzle are CLI arguments, which you can modify in the `Main` configuration. 
5. Provide each letter on your board (one per prompt), starting at the top row and moving from the left to right. 
   > $\color{lightblue} \text{Note}$: If your word search has multiple letters in one square (i.e. "qu"), put them in the same prompt.
6. After all coordinates have been filled, the program will provide the solutions to the puzzle in order from longest to shortest. 

### Running the Java Executable from Command Line

1. Download [Main](src/Main.java) and [Collins Scrabble Words (2019).txt](src/Collins%20Scrabble%20Words%20(2019).txt) and put them in the same directory.
2. Run the following command in your terminal, replacing [width] and [height] with the actual weight and height for your puzzle:
   ```bash
   java Main [width] [height]
   ```
> $\color{lightblue} \text{Note}$: This implementation allows for irregularly shaped puzzles. To do so, choose the smallest square size that fits the shape and press enter or type a space into letter spots that aren't in your puzzle.

### Running the Precompiled JAR File

1. Download the JAR file: [WordHunt.jar](out/artifacts/WordHunt_jar/WordHunt.jar)
2. Run the following command in your terminal, replacing [width] and [height] with the actual weight and height for your puzzle:
   ```bash
   java -jar WordHunt.jar [width] [height]
   ```

> $\color{lightblue} \text{Note}$: This implementation allows for irregularly shaped puzzles. To do so, choose the smallest square size that fits the shape and press enter or type a space into letter spots that aren't in your puzzle.