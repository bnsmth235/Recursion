import java.util.Random;

public class TreeTester {


    // Test program
    public static void main(String[] args) {
        long seed = 436543;
        Random generator = new Random(seed);  // Don't use a seed if you want the numbers to be different each time
        final String ENDLINE = "\n";

        Integer[] list1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9, 15,27,29,61};
        Tree<Integer> treeOne = new Tree<Integer>(list1, "TreeOne:",true);

        //Problem 1
        System.out.println("Problem 1:\n-------------------------------");
        System.out.println(treeOne.toString());
        System.out.println(treeOne.toString2());

        //Problem 2
        Integer[] list2 = {4,5,6,8,33,16,80,121,22,25, 36};
        Tree<Integer> treeTwo = new Tree<Integer>(list2, "TreeTwo:", false);

        System.out.println("\nProblem 2:\n-------------------------------");
        System.out.println(treeTwo.toString2());
        treeTwo.flip();
        treeTwo.changeName("Tree Two Now flipped:");
        System.out.println(treeTwo.toString2());
        treeTwo.flip();
        treeTwo.changeName("TreeTwo:");
        System.out.println(treeTwo.toString2());

        //Problem 3
        System.out.println("\nProblem 3:\n-------------------------------");
        final int SIZE = 10;
        Integer[] list3 = new Integer[SIZE];
        for (int i = 0; i < SIZE ; i++) {
            int t = generator.nextInt(200);
            list3[i] = t;
        }
        Tree<Integer> treeThree = new Tree<Integer>(list3, "TreeThree:", true);
        System.out.println(treeThree.toString2());

        System.out.println("Deepest Node of treeOne " + treeOne.deepestNode());
        System.out.println("Deepest Node of treeThree " + treeThree.deepestNode());


        //Problem 4
        System.out.println("\nProblem 4:\n-------------------------------");
        int mylevel=3;
        System.out.println("Number nodes at level " + mylevel + " is " + treeThree.nodesInLevel(mylevel));
        mylevel=4;
        System.out.println("Number nodes at level " + mylevel + " is " + treeThree.nodesInLevel(mylevel));

        //Problem 5
        System.out.println("\nProblem 5:\n-------------------------------");
        System.out.println("All paths from treeThree");
        treeThree.printAllPaths();


        Integer[] list4= {21, 8, 25, 6, 7, 19, 10, 40, 43, 52, 64, 80};
        Tree<Integer> treeFour = new Tree<Integer>(list4, "TreeFour:", false);

        //Problem 6
        System.out.println("\nProblem 6:\n-------------------------------");
        System.out.println(treeFour.toString2());
        treeFour.pruneK(60);
        treeFour.changeName("treeFour after pruning 60:");
        System.out.println(treeFour.toString2());
        System.out.println(treeTwo.toString2());
        treeTwo.pruneK(290);
        treeTwo.changeName("treeTwo after pruning 290: ");
        System.out.println(treeTwo.toString2());

        //Problem 7
        System.out.println("\nProblem 7:\n-------------------------------");
        System.out.println(treeOne.toString2());
        System.out.println("treeOne Least Common Ancestor of (10,15) " + treeOne.lca(10, 15) + ENDLINE);
        System.out.println("treeOne Least Common Ancestor of (55,61) " + treeOne.lca(55, 61) + ENDLINE);
        System.out.println("treeOne Least Common Ancestor of (9,50) " + treeOne.lca(9, 50) + ENDLINE);
        System.out.println("treeOne Least Common Ancestor of (29,62) " + treeOne.lca(29, 62) + ENDLINE);

        //Problem 8
        System.out.println("\nProblem 8:\n-------------------------------");
        Integer[] v8 = {15, 1,2,3,5,10, 65, 66,67,68,83, 70, 90,69,6,8};
        Tree<Integer> treeFive = new Tree<Integer>(v8, "TreeFive:",true);
        System.out.println(treeFive.toString2());
        treeFive.balanceTree();
        treeFive.changeName("treeFive after balancing");
        System.out.println(treeFive.toString2());

        //Problem 9
        System.out.println("\nProblem 9:\n-------------------------------");
        System.out.println(treeOne.toString2());
        treeOne.keepRange(14, 50);
        treeOne.changeName("treeOne after keeping only nodes between 14 and 50: ");
        System.out.println(treeOne.toString2());


        treeFive.changeName("treeFive");
        System.out.println(treeFive.toString2());
        treeFive.keepRange(3, 69);
        treeFive.changeName("treeFive after keeping only nodes between 3 and 69: ");
        System.out.println(treeFive.toString2());

        // Problem 10

        System.out.println("\nProblem 10:\n-------------------------------");
        treeTwo = new Tree<Integer>(list2, "TreeTwo:", false);
        System.out.println(treeTwo.toString2());
        System.out.println("treeTwo Contains BST: " + treeTwo.countBST());

        treeFour = new Tree<Integer>(list4, "treeFour", false);
        System.out.println(treeFour.toString2());
        System.out.println("treeFour Contains BST: " + treeFour.countBST());

    }
}
