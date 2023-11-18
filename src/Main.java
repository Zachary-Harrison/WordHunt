import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


// This is a new, improved version that is *significantly* easier to maintain
public class Main {
    static final String FILE_USAGE = "File Usage: java Main -f [path_to_file]";
    static final String ARG_USAGE = "Argument Usage: java Main [width] [height]";
    static final String DICTIONARY_FILE = "Collins Scrabble Words (2019).txt";

    static String[][] board;
    static HashSet<String> dictionary = new HashSet<>();
    static ArrayList<String> wordsFound = new ArrayList<>();

    public static void main(String[] args) {
        handleInput(args);
        displayBoard();
        createDictionary(DICTIONARY_FILE);
        findWords();
        displayFoundWords();
    }

    // the board will be created after this method is called
    public static void handleInput(String[] args) {
        // FILE USAGE
        if (args[0].equals("-f")) {
            String filePath = args[1];
            File file = new File(filePath);
            System.out.println("Trying to read file: " + file.getAbsolutePath());
            try {
                String content = Files.readString(file.toPath());
                createBoardFrom(content);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                System.exit(1);
            }
        }
        // ARGUMENT USAGE
        else {
            try {
                int xLength = Integer.parseInt(args[0]);
                int yLength = Integer.parseInt(args[1]);
                board = new String[xLength][yLength];
                createBoardFromUserInput();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: width and height should be integers.");
                System.exit(1);
            }
        }
    }

    public static void createBoardFrom(String str) {
        List<List<String>> data = new ArrayList<>();
        String[] lines = str.split("\n");
        int maxLen = 0;
        for (String line : lines) {
            String[] letters = line.toLowerCase().split(",", -1);  // change delimiter to comma
            List<String> row = new ArrayList<>();
            for (String letter : letters) {
                letter = letter.trim();
                row.add(letter.isEmpty() ? " " : letter);
            }
            maxLen = Math.max(maxLen, row.size());
            data.add(row);
        }
    
        board = new String[data.size()][maxLen];
        for (int i = 0; i < data.size(); i++) {
            List<String> row = data.get(i);
            for (int j = 0; j < maxLen; j++) {
                if (j < row.size() && row.get(j) != null) {
                    board[i][j] = row.get(j);
                } else {
                    board[i][j] = " ";
                }
            }
        } 

        
        // Remove columns that only contain spaces
        for (int j = 0; j < maxLen; j++) {
            boolean allSpaces = true;
            for (int i = 0; i < data.size(); i++) {
                if (!board[i][j].equals(" ")) {
                    allSpaces = false;
                    break;
                }
            }
            if (allSpaces) {
                for (int i = 0; i < data.size(); i++) {
                    board[i] = removeElement(board[i], j);
                }
                maxLen--;
                j--;
            }
        }
    }

    private static String[] removeElement(String[] array, int index) {
        String[] newArray = new String[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        return newArray;
    }

    private static String getOrdinalSuffix(int number) {
        if (number >= 11 && number <= 13) {
            return "th";
        }
        return switch (number % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }

    public static void createBoardFromUserInput() {
        try (Scanner reader = new Scanner(System.in)) {
            for (int row = 0; row < board.length; row++)
            {
                for (int column = 0; column < board[0].length; column++)
                {
                    System.out.print("The " + (column+1) +  getOrdinalSuffix(column+1) + " letter in the " + (row+1) + getOrdinalSuffix(row+1) + " row is: ");
                    board[row][column] = reader.nextLine();
                }
            }
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

        if (dictionary.size() == 0) {
            System.out.println("The dictionary is empty.");
            System.exit(1);
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
        if (board[row][column].equals(" ")) {
            return;
        }

        String newWord = prefix + board[row][column];
        if (dictionary.contains(newWord)) {
            wordsFound.add(newWord);
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
        wordsFound.sort((a, b) -> b.length() - a.length());

        // displaying only words that aren't suffixes of other words (i.e. If "spaces" is found, do not show "space")
        // Note: this also catches duplicates
        Set<String> displayedWords = new HashSet<>();
        int count = 0;
        for (String word : wordsFound) {
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