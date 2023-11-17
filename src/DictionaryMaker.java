import java.io.File;
import java.util.*;



public class DictionaryMaker extends AvlTree<String>
{
    public static AvlTree<String> readDictionary()
    {
        AvlTree<String> tree = new AvlTree<>();

        List<String> wordList = new ArrayList<>();
        File file = new File("enable1.txt");
        try (Scanner input = new Scanner(file))
        {
            while (input.hasNextLine())
            {
                String word = input.nextLine();
                word = word.replaceAll("\\W", ""); //Removes all non-word characters (AKA Punctuation)
                wordList.add(word.toLowerCase());
            }
        }
        catch (java.io.IOException ex)
        {
            System.out.println("An error occurred trying to read the file: " + ex);
        }
        java.util.Collections.shuffle(wordList, new java.util.Random(System.currentTimeMillis()));
        for(String word:wordList){
            tree.insert(word);
        }
        return tree;
    }
}
