package tree.segment;

/**
 * @author qgaye
 * @date 2019/03/08
 */
public class Main {

    public static void main(String[] args) {
        Integer[] arr = {-2, 0, 1, 3, 6, -4};

        SegmentTreeWithNode<Integer> segmentTreeWithNode = new SegmentTreeWithNode<>(arr, (o1, o2) -> o1 + o2);
        System.out.println(segmentTreeWithNode);
        System.out.println(segmentTreeWithNode.query(2, 4));
        segmentTreeWithNode.set(2, 100);
        System.out.println(segmentTreeWithNode);

        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr, (o1, o2) -> o1 + o2);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(2, 4));
        segmentTree.set(2, 100);
        System.out.println(segmentTree);
    }
}
