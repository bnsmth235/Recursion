// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.ArrayList;

public class Tree<E extends Comparable<? super E>> {
    private BinaryNode<E> root;  // Root of tree
    private String treeName;     // Name of tree

    /**
     * Create an empty tree
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Create non ordered tree from list in preorder
     * @param arr   List of elements
     * @param label Name of tree
     */
    public Tree(E[] arr, String label, boolean ordered) {
        treeName = label;
        if (ordered) {
            root = null;
            for (int i = 0; i < arr.length; i++) {
                bstInsert(arr[i]);
            }
        } else root = buildUnordered(arr, 0, arr.length - 1);
    }

    /**
     * Build a NON BST tree by preorder
     * @param arr nodes to be added
     * @return new tree
     */
    private BinaryNode<E> buildUnordered(E[] arr, int low, int high) {
        if (low > high) return null;
        int mid = (low + high) / 2;
        BinaryNode<E> curr = new BinaryNode<>(arr[mid], null, null);
        curr.left = buildUnordered(arr, low, mid - 1);
        curr.right = buildUnordered(arr, mid + 1, high);
        return curr;
    }
    /**
     * Create BST from Array
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            bstInsert(arr[i]);
        }
    }

    /**
     * Change name of tree
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }

    /**
     * Return a string displaying the tree contents as a tree with one node per line
     */
    public String toString() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString(root);

    }

    private String toString(BinaryNode<E> t) {
        if (t == null) return "";
        String sb = toString(t.left) +
                t.element.toString() + " " +
                toString(t.right);
        return sb;
    }

    /**
     * Return a string displaying the tree contents as a single line
     */

    // complexity O(n)
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + "\n" + toString2(root, "");
    }

    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     *
     * @param t the node that roots the subtree.
     */

    //complexity O(n log n)
    private String toString2(BinaryNode t, String indent) {
        if(t == null) return "";
        StringBuilder sb = new StringBuilder();
        indent += "  ";
        sb.append(toString2(t.right,indent));
        sb.append(indent + t.element.toString() + "\n");
        sb.append(toString2(t.left, indent));
        return sb.toString();
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
        flipTail(root);
    }

    //complexity O(n log n)
    private void flipTail(BinaryNode cur){
        BinaryNode templeft = cur.left; //start at the top, grab left and right nodes
        BinaryNode tempright = cur.right;

        if(cur.left != null){
            cur.left = tempright;
            cur.right = templeft;
            if(cur.left != null){  //swap left and right and move to next level
                flipTail(cur.left);
            }
            else return;
        }
        if(cur.right != null){ //same for right side if left is null
            if(cur.right.right == null && cur.right.left == null){
                cur.left = cur.right;
                cur.right = null;
            }else{ flipTail(cur.right); }
        }
    }

    public BinaryNode deepestNode() {
        deepestNodeTail(root, 0);
        return deepNode;
    }

    private int maxLevel = 0;
    private BinaryNode deepNode = null;

    //complexity O(n)
    private void deepestNodeTail(BinaryNode cur, int curlevel){ //simply traverse and return deepest node
        if (cur != null) {
            deepestNodeTail(cur.left, ++curlevel);
            if(curlevel > maxLevel){
                deepNode = cur;
                maxLevel = curlevel;
            }
            deepestNodeTail(cur.right, curlevel);
        }
    }




    /**
     * Counts number of nodes in specified level
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        count = 0;
        nodesInLevelTail(root, 0, level);
        return count;
    }

    private int count = 0;

    //complexity O(n)
    private void nodesInLevelTail(BinaryNode cur, int curLevel, int level){ //counts up nodes up to level given
        if (cur != null){
            if(curLevel == level){
                count++;
                return;
            }
            nodesInLevelTail(cur.left, ++curLevel, level);
            nodesInLevelTail(cur.right, curLevel, level);
        }
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        printAllPathsTail(root, "");
    }

    //complexity O(n)

    private void printAllPathsTail(BinaryNode cur, String path){ //grabs and prints path to every node
        if(cur != null){
            path += cur.element + " ";
            printAllPathsTail(cur.left, path);
            printAllPathsTail(cur.right, path);
            if(cur.element != root.element){
                System.out.println(path);
            }
        }
    }

    /**
     * Remove all paths from tree that sum to less than given value
     * @param sum: minimum path sum allowed in final tree
     */
    public void pruneK(Integer sum) {
        root = pruneKTail(root, sum, 0);
    }

    //complexity O(n)
    private BinaryNode pruneKTail(BinaryNode cur, Integer min, int curSum){
        if(cur != null){
            if(curSum < min){  //checking this twice eliminates a lot of recursion if we get past the threshold and there are more nodes below
                curSum += (int) cur.element;
                cur.left = pruneKTail(cur.left, min, curSum);
                cur.right = pruneKTail(cur.right, min, curSum);
                if(curSum < min) {
                    if(cur.left == null && cur.right == null){
                        cur = null;
                    }

                }
            }
        }
        return cur;
    }

    /**
     * Find the least common ancestor of two nodes
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    public String lca(E a, E b) {
        if(!this.contains(a) || !this.contains(b)){
            return "none";
        }
        BinaryNode ancestor = lcaTail(root, a, b);
        if (ancestor == null) return "none";
        else return ancestor.toString();
    }


    //complexity O(n log n)
    private BinaryNode lcaTail(BinaryNode cur, E a, E b){
        if (cur != null) {
            if(cur.element == a || cur.element == b){
                return cur;
            }
            BinaryNode leftlca = lcaTail(cur.left, a ,b); //finds one of the nodes on the left of the current node
            BinaryNode rightlca = lcaTail(cur.right,a ,b); //finds on the right

            if(leftlca != null && rightlca != null){
                return cur;
            }

            if(leftlca != null){
                return leftlca;
            }else return rightlca;

        }else return null;
    }


    /**
     * Balance the tree
     */
    public void balanceTree() {
        convertTreeToList(root);
        root = orderList(0, values.size() - 1);
    }

    private final ArrayList<BinaryNode> values = new ArrayList<>();

    //complexity O(n)
    private void convertTreeToList(BinaryNode cur){
        if(cur != null) {
            convertTreeToList(cur.left);
            values.add(cur);
            convertTreeToList(cur.right);
        }
    }

    //complexity O(n)
    private BinaryNode orderList(int low, int high){
        if(low <= high){
            int mid = (low+high)/2;
            BinaryNode<E> cur = values.get(mid);
            cur.left = orderList(low, mid - 1);
            cur.right = orderList(mid + 1, high);
            return cur;
        }
        return null;
    }

    /**
     * In a BST, keep only nodes between range
     *
     * @param a lowest value
     * @param b highest value
     */
    public void keepRange(int a, int b) {
        root = keepRangeTail(root, a, b);
    }

    //complexity O(n)
    private BinaryNode keepRangeTail(BinaryNode cur, int a, int b){
        if(cur == null) return null;
        cur.left = keepRangeTail(cur.left, a, b);
        cur.right = keepRangeTail(cur.right, a, b);

        if(a > (int) cur.element){
            BinaryNode right = cur.right;
            return right;
        }
        if(b < (int) cur.element){
            BinaryNode left = cur.left;
            return left;
        }

        return cur;

    }

    /**
     * Counts all non-null binary search trees embedded in tree
     * @return Count of embedded binary search trees
     */
    public int countBST() {
        if (root == null) return 0;
        countBSTTail(root);
        return BSTCount;
    }
    private int BSTCount = 0;

    //complexity O(n)
    private void countBSTTail(BinaryNode cur){
        if(cur != null) {
            int val = (int) cur.element; //value of current node
            countBSTTail(cur.left);
            countBSTTail(cur.right);

            if (cur.left == null && cur.right == null) { //if both are null then it counts as a tree (had to break it up into this many cases because if cur ends up being null, it can't have a left and a right of course
                ++BSTCount;
            }
            else if(cur.left == null){
                if((int) cur.right.element > val){ //if left is null and right is greater than current
                    ++BSTCount;
                }
            }
            else if(cur.right == null){ //if right is null and left is less than current
                if((int) cur.left.element < val){
                    ++BSTCount;
                }
            }
            else if((int) cur.left.element < val && (int) cur.right.element > val){ //if left is less and right is more
                ++BSTCount;
            }
        }
    }





    /**
     * Insert into a bst tree; duplicates are allowed
     * @param x the item to insert.
     */
    public void bstInsert(E x) {
        root = bstInsert(x, root);
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> bstInsert(E x, BinaryNode<E> t) {
        if (t == null)
            return new BinaryNode<E>(x, null, null);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = bstInsert(x, t.left);
        } else {
            t.right = bstInsert(x, t.right);
        }
        return t;
    }

    /**
     * Determines if item is in tree
     * @param item the item to search for.
     * @return true if found.
     */
    public boolean contains(E item) {
        return contains(item, root);
    }

    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryNode<E> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return true;    // Match
        }
    }

    // Basic node stored in unbalanced binary  trees
    private static class BinaryNode<E> {
        E element;            // The data in the node
        BinaryNode<E> left;   // Left child
        BinaryNode<E> right;  // Right child

        // Constructors
        BinaryNode(E theElement) {
            this(theElement, null, null);
        }

        BinaryNode(E theElement, BinaryNode<E> lt, BinaryNode<E> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        // toString for BinaryNode
        public String toString() {
            String sb = "Node: " +
                    element;
            return sb;
        }

    }

}
