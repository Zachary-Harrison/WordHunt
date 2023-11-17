/*
import org.junit.Assert;


public class WordGeneratorTester {

    @org.junit.Test
    public void BinarySearchTreeConstructor() {
        BinarySearchTree<Integer> intBst = new BinarySearchTree<>();

        Assert.assertNotNull ("BinarySearchTree must not be null", intBst);
    }

    @org.junit.Test
    public void BinarySearchTreeInsertTest1() {
        BinarySearchTree<Integer> intBst = new BinarySearchTree<>();
        String [][] array = {
                {"a0", "a1", "a2"},
                {"b0", "b1", "b2"},
                {"c0", "c1", "c2"},
        };
        int count = 0;

        for (int item : array) {
            count++;
            Assert.assertTrue(String.format("BST failed to insert %s and return true", Integer.toString(item)), intBst.insert(item));
            Assert.assertEquals("BST Insert must correctly handle the number of nodes", count, intBst.numberNodes());
        }
    }
    */