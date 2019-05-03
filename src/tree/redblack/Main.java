package tree.redblack;

/**
 * @author qgaye
 * @date 2019/03/26
 */
public class Main {

    public static void main(String[] args) {
//        RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<>();
//        redBlackTree.add(100, 1);
//        redBlackTree.add(50, 1);
//        redBlackTree.add(20, 1);
//        redBlackTree.add(10, 1);
//        redBlackTree.add(1, 1);
//        redBlackTree.add(15, 1);
//        redBlackTree.add(18, 1);
//        System.out.println(redBlackTree);

        RedBlackTree2<Integer, Integer> redBlackTree2 = new RedBlackTree2<>();
        redBlackTree2.add(100, 1);
        redBlackTree2.add(50, 1);
        redBlackTree2.add(20, 1);
        redBlackTree2.add(10, 1);
        redBlackTree2.add(1, 1);
        redBlackTree2.add(15, 1);
        redBlackTree2.add(18, 1);

        redBlackTree2.add(80, 1);
        redBlackTree2.add(70, 1);
        redBlackTree2.add(60, 1);

        redBlackTree2.add(16, 1);
        redBlackTree2.add(14, 1);
        redBlackTree2.add(17, 1);

//        redBlackTree2.remove(60);

//        redBlackTree2.add(66, 1);
//        redBlackTree2.add(50, 1);
//        redBlackTree2.add(100, 1);
//        redBlackTree2.add(40, 1);
//        redBlackTree2.add(56, 1);
//        redBlackTree2.add(38, 1);
//        redBlackTree2.add(39, 1);

        System.out.println(redBlackTree2);
    }
}
