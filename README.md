# WordHunt

This program is designed to solve word searches, specifically those found in games like Boggle or WordHunt on the GamePigeon iOS extension. It stores words in an AVL tree and uses a binary search algorithm to find possible words within a given 4x4 grid. 

> $\color{red}{Disclaimer}$: This program is not guaranteed to find *every* word in your puzzle, as GamePigeon uses a private dictionary that differs from the one I used: Collins Scrabble Words (2019).


## Usage

To use the WordHunt Solver, follow these steps:

1. Open IntelliJ IDEA.
2. Click on `File > Open`, navigate to the project directory, and click `OK`.
3. Once the project has loaded, click on `Run > Run 'Main'` or press `Shift` + `F10` to start the program.
4. Provide each letter on your board (one per prompt), starting at the top row and moving from the left to right. 
   > $\color{yellow}{Note}$: If your word search has multiple letters in one square (i.e. "qu"), put them in the same prompt.
5. After all coordinates have been filled, the program will provide the solutions to the puzzle in order from longest to shortest. 