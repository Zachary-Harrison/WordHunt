public class AvlTree<E extends Comparable<E>>{
    private AvlNode<E> root;
    public AvlTree(){root = null;}

    private int getHeight(AvlNode<E> v){
        if(v == null)
            return -1;
        else
            return v.height;
    }
    public void insert(E x) { root = insert(root, x); }

    public void remove(E x) { root = remove(root, x); }

    //please complete the following seven functions
    private AvlNode<E> insert(AvlNode<E> v, E x)//this function is overloaded
    {
        if (v == null) return new AvlNode<>(x);
        if (x == v.key) return v;
        if (x.compareTo(v.key) < 0) v.left = insert(v.left, x);
        if (x.compareTo(v.key) > 0) v.right = insert(v.right, x);
        return balance(v);
    }

    private AvlNode<E> remove(AvlNode<E> v, E x)//this function is overloaded
    {
        if (v == null) return null;
        if (x.compareTo(v.key) < 0) v.left = remove(v.left, x);
        if (x.compareTo(v.key) > 0) v.right = remove(v.right, x);
        if (x == v.key)
        {
            if (v.left == null) return balance(v.right);
            if (v.right == null) return balance(v.left);
            // v must have left and right child to reach this code
            AvlNode<E> u = v.right;
            while (u.left != null) u = u.left;
            v.key = u.key; //replacing v by u
            v.right = remove(v.right, u.key);
        }
        return balance(v);
    }

    private AvlNode<E> balance(AvlNode<E> v)
    {
        if (v == null) return v;
        if (getHeight(v.left) - getHeight(v.right) > 1)
        {
            if (getHeight(v.left.left) >= getHeight(v.left.right)) v = rightRotate(v);
            else v = doubleLeftRightRotate(v);
        }else if (getHeight(v.left) - getHeight(v.right) < -1)
        {
            if (getHeight(v.right.right) >= getHeight(v.right.left)) v = leftRotate(v);
            else v = doubleRightLeftRotate(v);
        }
        v.height = 1 + Math.max(getHeight(v.left), getHeight(v.right));
        return v;
    }
    private AvlNode<E> rightRotate (AvlNode<E> z)
    { //left left case
        AvlNode<E> y = z.left;
        z.left = y.right;
        y.right = z;
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        z.height = 1 + Math.max(getHeight(z.left), getHeight(z.right));
        return y;
    }

    private AvlNode<E> leftRotate (AvlNode<E> z)
    { //right right case
        AvlNode<E> y = z.right;
        z.right= y.left;
        y.left = z;
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        z.height = 1 + Math.max(getHeight(z.left), getHeight(z.right));
        return y;
    }

    private AvlNode<E> doubleLeftRightRotate (AvlNode<E> v)
    { //left right case
        v.left = leftRotate(v.left);
        return rightRotate(v);
    }

    private AvlNode<E> doubleRightLeftRotate (AvlNode<E> v)
    { //right left case
        v.right = rightRotate(v.right);
        return leftRotate(v);
    }

    public boolean search(E x) {
        AvlNode<E> node = this.root;

        while (node != null)  // Note: node is null if subtree is empty
        {
            if (x.compareTo(node.key) == 0) {
                return true;
            } else if (x.compareTo(node.key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }
}
class AvlNode <E extends Comparable<E>>{
    protected E key;
    protected AvlNode<E> left;
    protected AvlNode<E> right;
    public int height;

    public AvlNode(E key_input)
    {
        key = key_input;
        height = 0;
        left = null;
        right = null;
    }

}