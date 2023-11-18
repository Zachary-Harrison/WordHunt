import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solver
{
    public static final int X_LENGTH = 4;
    public static final int Y_LENGTH = 4;
    public static String[][] board = getTable();
    public static final AvlTree<String> dictionary = AVLTreeFactory.createFrom("Collins Scrabble Words (2019).txt");
    public static List<String> wordsFound = new ArrayList<>();
    static boolean[][] visited = new boolean[X_LENGTH][Y_LENGTH];
    public static void main(String[] args)
    {
        System.out.println("\nHere is what the board looks like: ");
        display(board);
        for (int row = 1; row < (Y_LENGTH + 1); row++)
        {
            for (int column = 1; column < (X_LENGTH + 1); column++)
            {
                findWords("", row-1, column-1);
            }
        }
        wordsFound.sort(new sortByWordLength());
        displayFoundWords();
    }

    public static String[][] getTable()
    {
        Scanner reader = new Scanner(System.in);
        String[][] table = new String[X_LENGTH][Y_LENGTH];
        for (int row = 1; row < (Y_LENGTH + 1); row++)
        {
            for (int column = 1; column < (X_LENGTH + 1); column++)
            {
                System.out.print("The " + column +  "th letter in the " + row + "th row is: ");
                table[row-1][column-1] = reader.nextLine();
            }
        }
        reader.close();
        return table;
    }

    public static void display(String[][] board)
    {
        for (String[] row : board)
        {
            for (String letter : row)
            {
                System.out.print(letter + " ");
            }
            System.out.print("\n");
        }
    }

    public static void findWords(String word, int row, int col)
    {
        if (visited[row][col]) return;
        visited[row][col] = true;

        // adding letter if unvisited
        if (visited[row][col])
        {
            word = word + board[row][col];
        }
        if (dictionary.search(word) && !wordsFound.contains(word) && word.length() > 2)
        {
            if (word.length() > 10)
            {
                System.out.println("BIG BOY WORD!!!: " + word);
            }
            wordsFound.add(word);
        }

        for (int j = -1; j <= 1; j++)
        {
            for (int i = -1; i <= 1; i++)
            {
                boolean xInRange = (j + row < X_LENGTH && j + row >= 0);
                boolean yInRange = (i + col < Y_LENGTH && i + col >= 0);
                if (xInRange && yInRange && !(i == 0 && j == 0))
                {
                    findWords(word, row + j, col + i);
                }
            }
        }
        visited[row][col] = false;
    }
    public static void displayFoundWords()
    {
        int i = 0;
        for (String word : wordsFound) {
            if (i % 10 == 0) System.out.println();
            System.out.print(word + ", ");
            i++;
        }
    }
    public static class sortByWordLength implements Comparator<String> {
        @Override
        public int compare(String o2, String o1) {
            if (o1.length()!=o2.length()) {
                return o1.length()-o2.length();
            }
            return o1.compareTo(o2);
        }
    }
}
