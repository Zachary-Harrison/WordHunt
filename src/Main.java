import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


// This is a new, improved version that is *significantly* easier to maintain
public class Main {
    static final String DICTIONARY_FILE = "Collins Scrabble Words (2019).txt";
    static String[][] board;
    static HashSet<String> dictionary = new HashSet<>();
    static ArrayList<String> foundWords = new ArrayList<>();

    public static void main(String[] args) {
        int[] dimensions = parseArguments(args);
        if (dimensions == null) {
            System.out.println("Usage: java Main [width] [height]");
            return;
        }

        createDictionary(DICTIONARY_FILE);
        if (dictionary.size() == 0) {
            System.out.println("The dictionary is empty.");
            return;
        }
        createBoardFromUserInput(dimensions[0], dimensions[1]);
        findWords();
        displayBoard();
        displayFoundWords();
    }

    public static int[] parseArguments(String[] args) {
        if (args.length < 2) {
            return null;
        }

        try {
            int xLength = Integer.parseInt(args[0]);
            int yLength = Integer.parseInt(args[1]);
            return new int[]{xLength, yLength};
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: width and height should be integers.");
            return null;
        }
    }

    public static void createDictionary(String filename) {
        File file = new File(filename);
        try (Scanner input = new Scanner(file))
        {
            while (input.hasNextLine())
            {
                String word = input.nextLine();
                word = word.replaceAll("\\W", ""); // Removes non-word characters
                dictionary.add(word.toLowerCase());
            }
        }
        catch (java.io.IOException ex)
        {
            System.out.println("An error occurred trying to read the file: " + ex);
        }
    }

    private static String getOrdinalSuffix(int number) {
        if (number >= 11 && number <= 13) {
            return "th";
        }
        switch (number % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

    public static void createBoardFromUserInput(int width, int height) {
        try (Scanner reader = new Scanner(System.in)) {
            board = new String[width][height];
            for (int row = 0; row < height; row++)
            {
                for (int column = 0; column < width; column++)
                {
                    System.out.print("The " + (column+1) +  getOrdinalSuffix(column+1) + " letter in the " + (row+1) + getOrdinalSuffix(row+1) + " row is: ");
                    board[row][column] = reader.nextLine();
                }
            }
        }
    }

    public static void findWords() {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                dfs(row, column, "", visited);
            }
        }
    }

    private static void dfs(int row, int column, String prefix, boolean[][] visited) {
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
            return;
        }

        if (visited[row][column]) {
            return;
        }

        String newWord = prefix + board[row][column];
        if (dictionary.contains(newWord)) {
            foundWords.add(newWord);
        }

        visited[row][column] = true;
        for (int dirX = -1; dirX <= 1; dirX++) {
            for (int dirY = -1; dirY <= 1; dirY++) {
                dfs(row + dirX, column + dirY, newWord, visited);
            }
        }
        visited[row][column] = false;
    }

    public static void displayBoard() {
        System.out.println("\nHere is what your board looks like:");
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayFoundWords() {
        foundWords.sort((a, b) -> b.length() - a.length());

        // displaying only words that aren't suffixes of other words (i.e. If "spaces" is found, do not show "space")
        // Note: this also catches duplicates
        Set<String> displayedWords = new HashSet<>();
        int count = 0;
        for (String word : foundWords) {
            boolean isPrefixOfDisplayedWord = false;
            for (String displayedWord : displayedWords) {
                if (displayedWord.startsWith(word)) {
                    isPrefixOfDisplayedWord = true;
                    break;
                }
            }

            if (!isPrefixOfDisplayedWord) {
                System.out.print(word + " ");
                displayedWords.add(word);
                count++;
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
        }
    }
}