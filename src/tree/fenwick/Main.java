package tree.fenwick;

/**
 * @author qgaye
 * @date 2019/06/03
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5};
        FenwickTree fenwickTree = new FenwickTree(arr);
        System.out.println(fenwickTree.rangeSum(0, 2));
        fenwickTree.update(1, 2);
        System.out.println(fenwickTree.rangeSum(0, 2));
    }
}
