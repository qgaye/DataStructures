package tree.bst;

/**
 * @author qgaye
 * @date 2019/02/23
 */
public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.addEasy(3);
        bst.addEasy(5);
        bst.addEasy(7);
        bst.addEasy(1);
        bst.addEasy(100);
        bst.addEasy(2);
        bst.addEasy(12);
        bst.addEasy(23);

        System.out.println(bst);

        bst.inOrder();

        System.out.println(bst.calculateSize());
    }
}
