# WordHunt

This program solves word connect games similar to Boggle or WordHunt on the GamePigeon iOS extension. Because speed is critical, it stores words in an AVL tree and uses a binary search algorithm to find potential words within a given 4x4 grid, though it can be modified to work for any square grid.

> $\color{red} \text{Disclaimer}$: This program may not find every possible word in your puzzle, and not all words it identifies may be valid in your specific game. For instance, GamePigeon utilizes a proprietary dictionary that differs from the Collins Scrabble Words (2019) dictionary used in this program. Therefore, the results may vary based on the specific dictionary used by your game.


## Usage

The original `Solver` class is no longer supported, but it remains in the code base for posterity. It used an AVL tree to store the dictionary instead of a HashSet, which performs worse and is more complex. It also had a number of limitations that are not present in the new implementation.

Instead, use the improved `Main` class, which has the following benefits:
- Faster runtime
- Puzzle width and height will now be specified at runtime
- Irregular puzzle shapes are now allowed
- File-based usage

### Compiling Main.java from the Command Line

1. Download the [Main](src/Main.java) java file and [Collins Scrabble Words (2019).txt](src/Collins%20Scrabble%20Words%20(2019).txt) and put them in the same directory.
2. Open a terminal, navigating to the directory with the above downloaded files.
3. Run the following command to compile the java files into bytecode:
    ```bash
    javac Main.java
    ```
    > $\color{lightblue} \text{Note}$: You will only need to do this once, unless you modify the source code.
4. Run the following command to execute the program, replacing [width] and [height] with the desired width and height of the puzzle:
    ```bash
    java -cp . Main [width] [height]
    ```
    Alternatively, you can provide a text file with comma-separated rows of letters:
    ```bash
    java -cp . Main -f [path_to_file]
    ```

### Running the Precompiled JAR File

1. Download the [WordHunt.jar](out/artifacts/WordHunt_jar/WordHunt.jar) jar file.
2. Open a terminal, navigating to the directory with the above downloaded file.
3. Run the following command to execute the program, replacing appropriate bracketed parameters:
    ```bash
    java -jar WordHunt.jar [width] [height]
    ```
    Alternatively, you can provide a text file with comma-separated rows of letters:
    ```bash
    java -jar WordHunt.jar -f [path_to_file]
    ```
> $\color{lightgreen} \text{Tip}$: For irregularly shaped puzzle, choose the smallest width and height that fits and run the program. When you reach a coordinate that should be empty, press enter instead of typing a letter.

## Board File Format

The board file should be a text file where each line represents a row on the board. Each cell in the row should be separated by a comma. Here's an example of what a board file might look like:
```
a,b,c
d,e,f
g,h,i
```
> $\color{lightblue} \text{Note}$: Spaces are ignored, and commas are needed to separate the letters in each cell.

If your puzzle has an irregular shape, make sure to place commas to make empty cells. For instance, a 3x3 puzzle with an empty middle cell could be represented as follows:
```
a,b,c
d, ,f
g,h,i
```

> $\color{lightblue} \text{Note}$: If your board ends up having an entire column of empty cells, the empty column will be removed.

For more examples, see the [example puzzles](src/example-puzzles/) I made.
