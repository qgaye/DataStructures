package map;

/**
 * @author qgaye
 * @date 2019/02/26
 */
public class Main {

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
        bst.add(3, 10);
        bst.add(4, 1);
        bst.add(2, 41);
        bst.add(9, 111);
        bst.add(8, 5);
        bst.add(11, 33);

        bst.remove(9);
        System.out.println(bst);
    }
}
