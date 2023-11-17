public class BinarySearchTree<E extends Comparable<E>> {
    private TreeNode<E> root;

    public boolean insert(E valueInsert) {
        if (this.root == null) {
            this.root = new TreeNode<>(valueInsert);
            return true;
        } else {
            // Search/find the insert location
            TreeNode<E> parent = null;
            TreeNode<E> node = this.root;
            while (node != null) {
                parent = node;
                if (valueInsert.compareTo(node.value) < 0) {
                    node = node.left;
                } else if (valueInsert.compareTo(node.value) > 0) {
                    node = node.right;
                } else {
                    return false;
                }
            }

            // Add the node to the tree
            TreeNode<E> newNode = new TreeNode<>(valueInsert);
            if (valueInsert.compareTo(parent.value) < 0) {
                parent.left = newNode;
            } else if (valueInsert.compareTo(parent.value) > 0) {
                parent.right = newNode;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean remove(E valueRemove) {
        boolean valueInTree = false;
        // Step 1: find the node to remove
        TreeNode<E> parent = null;
        TreeNode<E> node = this.root;
        boolean done = false;

        while (!done) {
            if (node == null) {
                return false;
            }
            if (valueRemove.compareTo(node.value) < 0) {
                parent = node;
                node = node.left;
            } else if (valueRemove.compareTo(node.value) > 0) {
                parent = node;
                node = node.right;
            } else {
                valueInTree = true;
                done = true;
            }
        }

        // Step 2a: case for no left child of the node_Remove
        if (node.left == null) {
            if (parent == null) {
                this.root = node.right;
            } else {
                if (valueRemove.compareTo(parent.value) < 0) {
                    parent.left = node.right;
                } else {
                    parent.right = node.right;
                }
            }
        } else { // Step 2b: case for when node_Remove has a left child
            TreeNode<E> parentOfRight = node;
            TreeNode<E> rightMost = node.left;
            while (rightMost.right != null) {
                parentOfRight = rightMost;
                rightMost = rightMost.right;
            }
            // Copy the largest value into the node being removed
            node.value = rightMost.value;
            if (parentOfRight.right == rightMost) {
                parentOfRight.right = rightMost.left;
            } else {
                parentOfRight.left = rightMost.left;
            }
        }
        return valueInTree;
    }

    public boolean search(E searchElement) {
        TreeNode<E> node = this.root;

        while (node != null)  // Note: node is null if subtree is empty
        {
            if (searchElement.compareTo(node.value) == 0) {
                return true;
            } else if (searchElement.compareTo(node.value) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return false;
    }
}
class TreeNode<E extends Comparable<E>>
{
    protected E value;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e)
    {
        value = e;
        left = null;
        right = null;
    }
}