package tree.segment;

/**
 * 线段树，使用Node节点存储该处的left和right值，代替在递归函数中不断传递
 * 复杂度O(logn)
 * 主要用于随机范围查询，并且数值是动态更改的
 * 数据值不发生改变：动态规划
 * 数据值会发生变化：线段树，树状数组
 *                   [0, 5]
 *                  /     \
 *             [0, 2]     [3, 5]
 *             /   \      /    \
 *        [0, 1]  [2,2] [3, 4]  [5,5]
 *        /    \        /    \
 *   [0,0]    [1,1]  [3,3]   [4,4]
 *
 * @author qgaye
 * @date 2019/03/08
 */
public class SegmentTreeWithNode<E> {

    private class Node<E> {
        public E res;
        public Integer left;
        public Integer right;

        public Node(E res, Integer left, Integer right) {
            this.res = res;
            this.left = left;
            this.right = right;
        }

        public Node() {
            this(null, null, null);
        }
    }

    private E[] data;
    private Node<E>[] tree;
    private Merger<E> merger;

    public SegmentTreeWithNode(E[] arr, Merger<E> merger) {
        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        // 长度为n的数组若要存储为线段树，最多需要4n空间
        tree = new Node[arr.length * 4];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node<>();
        }
        buildSegmentTree(0, 0, data.length - 1);
    }

    private Integer leftChild(Integer index) {
        return index * 2 + 1;
    }

    private Integer rightChild(Integer index) {
        return index * 2 + 2;
    }

    private void buildSegmentTree(Integer treeIndex, Integer left, Integer right) {
        tree[treeIndex].left = left;
        tree[treeIndex].right = right;

        if (left.equals(right)) {
            tree[treeIndex].res = data[left];
            return;
        }

        Integer mid = left + (right - left) / 2;
        Integer leftTreeIndex = leftChild(treeIndex);
        Integer rightTreeIndex = rightChild(treeIndex);

        buildSegmentTree(leftTreeIndex, left, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, right);

        tree[treeIndex].res = merger.merge(tree[leftTreeIndex].res, tree[rightTreeIndex].res);
    }

    /**
     * 查询范围为queryL到queryR之间的值
     */
    public E query(Integer queryL, Integer queryR) {
        if (queryL > queryR || queryL < 0 || queryR > data.length - 1) {
            throw new IllegalArgumentException("queryL or queryR is illegal.");
        }
        return query(0, queryL, queryR);
    }

    private E query(Integer treeIndex, Integer queryL, Integer queryR) {
        if (queryL.equals(tree[treeIndex].left) && queryR.equals(tree[treeIndex].right)) {
            return tree[treeIndex].res;
        }

        Integer mid = tree[treeIndex].left + (tree[treeIndex].right - tree[treeIndex].left) / 2;
        Integer leftTreeIndex = leftChild(treeIndex);
        Integer rightTreeIndex = rightChild(treeIndex);

        // 查询范围刚好全在某个节点的左节点或右节点，返回某个节点值即可
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, queryL, queryR);
        } else {  // 查询范围分布在左节点和右节点，分别求出左边和右边的节点值，再做merge
            E leftResult = query(leftTreeIndex, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    /**
     * 更新index节点的值
     */
    public void set(Integer index, E val) {
        if (index < 0 || index > data.length - 1) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        set(0, index, val);
    }

    private void set(Integer treeIndex, Integer index, E val) {
        if (tree[treeIndex].left.equals(tree[treeIndex].right)) {
            tree[treeIndex].res = val;
            return;
        }
        Integer mid = tree[treeIndex].left + (tree[treeIndex].right - tree[treeIndex].left) / 2;
        Integer leftTreeIndex = leftChild(treeIndex);
        Integer rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1) {
            set(rightTreeIndex, index, val);
        } else if (index <= mid) {
            set(leftTreeIndex, index, val);
        }
        tree[treeIndex].res = merger.merge(tree[leftTreeIndex].res, tree[rightTreeIndex].res);
    }

    public Integer getSize() {
        return data.length;
    }

    public E get(Integer index) {
        if (index < 0 || index > data.length - 1) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            res.append(i).append(":");
            res.append("(");
            if (tree[i].left == null && tree[i].right == null && tree[i].res == null) {
                res.append("null");
            } else {
                res.append("index:").append(tree[i].left).append("-").append(tree[i].right).append(",");
                res.append("res:").append(tree[i].res);
            }
            res.append(")");
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
