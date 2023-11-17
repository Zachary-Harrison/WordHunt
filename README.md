# WordHunt

This program is designed to solve word connect games similar to Boggle or WordHunt on the GamePigeon iOS extension. Because speed is critical, it stores words in an AVL tree and uses a binary search algorithm to find potential words within a given 4x4 grid, though it can be modified to work for any square grid.

> $\color{red}{Disclaimer}$: This program is not guaranteed to find *every* word in your puzzle, nor does every word it finds necessarily exist for your game. GamePigeon, for instance, uses a private dictionary that differs from the one I used: Collins Scrabble Words (2019).


## Usage


### Building and Running as an IntelliJ Project

To use the WordHunt Solver, follow these steps:

1. Open IntelliJ IDEA.
2. Click on `File > Open`, navigate to the project directory, and click `OK`.
3. Once the project has loaded, click on `Run > Run 'Main'` or press `Shift` + `F10` to start the program.
   > $\color{yellow}{Note}$: You can change the 
4. Provide each letter on your board (one per prompt), starting at the top row and moving from the left to right. 
   > $\color{yellow}{Note}$: If your word search has multiple letters in one square (i.e. "qu"), put them in the same prompt.
5. After all coordinates have been filled, the program will provide the solutions to the puzzle in order from longest to shortest. 

### Running the Java Executable from Command Line

Alternatively, you can navigate to [src/](src/) use my program from the command like this:
```bash
java Main [width] [height]
```
Please replace [width] and [height] with the actual weight and height for your puzzle.
>$\color{yellow}{Note}$: This implementation allows for irregularly shaped puzzles. To do so, choose the smallest square size that fits the shape and press enter or type a space for unnecessary letter spots.

### Running the Precompiled JAR File

1. Download the JAR file: [WordHunt.jar](https://github.com/yourusername/wordhunt-solver/raw/main/WordHunt.jar)
2. Open a terminal/command prompt.
3. (optional) Navigate to the directory where you downloaded the JAR file.
4. Run the JAR file using the command: 
   ```bash
   java -jar <path>/WordHunt.jar [width] [height]
   ```