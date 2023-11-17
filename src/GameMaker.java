import java.util.*;




public class GameMaker extends DictionaryMaker
{
    public static final int X_LENGTH = 4;
    public static final int Y_LENGTH = 4;
    public static String[][] wordHuntBoard = GameMaker.getTable();
    public static final AvlTree<String> dictionary = DictionaryMaker.readDictionary();

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
        return table;
    }

    public static void displayTable(String[][] table)
    {
        System.out.println("\nHere is what the board looks like: ");
        for (String[] row : table)
        {
            for (String letter : row)
            {
                System.out.print(letter + " ");
            }
            System.out.print("\n");
        }
    }
}
