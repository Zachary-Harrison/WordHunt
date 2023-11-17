import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WordGenerator extends GameMaker
{
    public static List<String> wordFoundList = new ArrayList<>();
    static boolean[][] visited = new boolean[X_LENGTH][Y_LENGTH];
    public static void main(String[] args)
    {
        displayTable(GameMaker.wordHuntBoard);
        for (int row = 1; row < (Y_LENGTH + 1); row++)
        {
            for (int column = 1; column < (X_LENGTH + 1); column++)
            {
                findWords("", row-1, column-1);
            }
        }
        wordFoundList.sort(new sortByWordLength());
        displayFoundWords();
    }

    public static void findWords(String word, int rowCurrent, int columnCurrent)
    {
        //System.out.println("row: " + (rowCurrent+1) + ", column: " + (columnCurrent+1));
        boolean xInRange, yInRange;
        if (visited[rowCurrent][columnCurrent]) return;
        visited[rowCurrent][columnCurrent] = true;

        if (visited[rowCurrent][columnCurrent])
        {
            word = word + wordHuntBoard[rowCurrent][columnCurrent];
        }
        if (dictionary.search(word) && !wordFoundList.contains(word) && word.length() > 2)
        {
            if (word.length() > 10)
            {
                System.out.println("BIG BOY WORD!!!: " + word);
            }
            wordFoundList.add(word);
        }
        for (int rowChange= -1; rowChange < 2; rowChange++)
        {
            for (int columnChange= -1; columnChange < 2; columnChange++)
            {
                xInRange = (rowChange + rowCurrent < X_LENGTH && rowChange + rowCurrent >= 0);
                yInRange = (columnChange + columnCurrent < Y_LENGTH && columnChange + columnCurrent >= 0);
                if (xInRange && yInRange && !(columnChange == 0 && rowChange == 0))
                {
                    findWords(word, rowCurrent + rowChange, columnCurrent + columnChange);
                }
            }
        }
        visited[rowCurrent][columnCurrent] = false;
    }
    public static void displayFoundWords()
    {
        int i = 0;
        for (String word :wordFoundList) {
            if (i % 10 == 0) System.out.println();
            System.out.print(word + ", ");
            i++;
        }
    }
    public static class sortByWordLength implements Comparator<String> {
        @Override
        public int compare(String o2, String o1) {
            if (o1.length()!=o2.length()) {
                return o1.length()-o2.length(); //overflow impossible since lengths are non-negative
            }
            return o1.compareTo(o2);
        }
    }
}
