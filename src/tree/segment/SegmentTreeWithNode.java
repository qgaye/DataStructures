package tree.segment;

/**
 * 线段树，使用Node节点存储该处的left和right值，代替在递归函数中不断传递
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

        tree = new Node[arr.length * 4];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new Node<>();
        }
        buildSegmentTree(0, 0, data.length - 1);
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

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, queryL, queryR);
        } else {
            E leftResult = query(leftTreeIndex, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

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

    private Integer leftChild(Integer index) {
        return index * 2 + 1;
    }

    private Integer rightChild(Integer index) {
        return index * 2 + 2;
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
