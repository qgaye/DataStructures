package tree.avl;

/**
 * @author qgaye
 * @date 2019/03/22
 */
public class Main {

    public static void main(String[] args) {
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        avlTree.add(10, 1);
        avlTree.add(20, 1);
        avlTree.add(5, 1);
        avlTree.add(15, 1);
        avlTree.add(30, 1);
        avlTree.add(17, 1);
        System.out.println(avlTree);

        System.out.println(avlTree.isBST());
        System.out.println(avlTree.isBalanced());
    }
}
