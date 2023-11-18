import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void testCreateBoardFrom() throws IOException {
        testBoard("src/example-puzzles/square-3x3-1.txt", new String[][]{
                {"a", "b", "c"},
                {"d", "e", "f"},
                {"g", "h", "i"}
        });

        testBoard("src/example-puzzles/square-3x3-2.txt", new String[][]{
                {"a", "b", "c"},
                {"d", "e", "f"},
                {"g", "h", "i"}
        });

        testBoard("src/example-puzzles/square-3x3-3.txt", new String[][]{
                {"a", "b", "c"},
                {"d", "e", "f"},
                {"g", "h", "i"}
        });

        testBoard("src/example-puzzles/rect-2x4-1.txt", new String[][]{
                {"a", "b"},
                {"c", "d"},
                {"e", "f"},
                {"g", "h"}
        });

        testBoard("src/example-puzzles/rect-2x4-2.txt", new String[][]{
                {"a", "b"},
                {"c", "d"},
                {"e", "f"},
                {"g", "h"}
        });

        testBoard("src/example-puzzles/rect-2x4-3.txt", new String[][]{
                {"a", "b"},
                {"c", "d"},
                {"e", "f"},
                {"g", "h"}
        });

        testBoard("src/example-puzzles/irregular-3x4-1.txt", new String[][]{
                {" ", "a", "b"},
                {" ", "c", " "},
                {"d", "e", "f"},
                {" ", "g", " "}
        });

        testBoard("src/example-puzzles/irregular-3x4-2.txt", new String[][]{
                {"a", "b", " "},
                {"c", " ", " "},
                {"d", "e", "f"},
                {"g", " ", " "}
        });

        testBoard("src/example-puzzles/irregular-3x4-3.txt", new String[][]{
                {"a", "b", " "},
                {"c", " ", " "},
                {"d", "e", "f"},
                {"g", " ", " "}
        });
    }

    private void testBoard(String filePath, String[][] expected) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(filePath)));
        Main.createBoardFrom(input);
        try {
            Assertions.assertArrayEquals(expected, Main.board);
        } catch (AssertionError e) {
            System.out.println("Expected and actual boards are not equal.");
            System.out.println("Expected board:");
            printBoard(expected);
            System.out.println("Actual board:");
            printBoard(Main.board);
            throw e;  // rethrow the exception to fail the test
        }
    }

    private void printBoard(String[][] board) {
        for (String[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}