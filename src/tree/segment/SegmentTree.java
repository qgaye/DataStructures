package tree.segment;

/**
 * 线段树
 * @author qgaye
 * @date 2019/03/05
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[arr.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }

    private void buildSegmentTree(Integer treeIndex, Integer left, Integer right) {
        if (right.equals(left)) {
            tree[treeIndex] = data[left];
            return;
        }

        Integer leftTreeIndex = leftChild(treeIndex);
        Integer rightTreeIndex = rightChild(treeIndex);

        Integer mid = left + (right - left) / 2;

        buildSegmentTree(leftTreeIndex, left, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, right);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E query(Integer queryL, Integer queryR) {
        if (queryL > queryR || queryL < 0 || queryR > data.length - 1) {
            throw new IllegalArgumentException("queryL or queryR is illegal.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(Integer treeIndex, Integer left, Integer right, Integer queryL, Integer queryR) {
        if (queryL.equals(left) && queryR.equals(right)) {
            return tree[treeIndex];
        }

        Integer mid = left + (right - left) / 2;
        Integer leftTreeIndex = leftChild(treeIndex);
        Integer rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, right, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, left, mid, queryL, queryR);
        } else {
            E leftResult = query(leftTreeIndex, left, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, right, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }
    }

    public void set(Integer index, E val) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is Illegal");
        }
        set(0, 0, data.length - 1, index, val);
    }

    private void set(Integer treeIndex, Integer left, Integer right, Integer index, E val) {
        if (left.equals(right)) {
            tree[treeIndex] = val;
            return;
        }
        Integer mid = left + (right - left) / 2;
        Integer leftTreeIndex = leftChild(treeIndex);
        Integer rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, right, index, val);
        } else if (index <= mid){
            set(leftTreeIndex, left, mid, index, val);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public Integer getSize() {
        return data.length;
    }

    public E get(Integer index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is Illegal");
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
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
