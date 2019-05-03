package unionfind;

/**
 * 路径压缩
 * 在find操作中的向上遍历的过程中，将子节点挂载到父节点的父节点上，从而降低树高
 * 当在find操作时进行路径压缩时，rank不在表示树高，而代表着树高的权重
 * @author qgaye
 * @date 2019/03/21
 */
public class UnionFindFive implements UF {

    private int[] parent;
    // 此处到rank不再代表树高，只是代表树高到权重
    private int[] rank;

    public UnionFindFive(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    private int find(int p) {
        if (p < 0 || p > parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public Integer getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < parent.length; i++) {
            res.append("(").append(i).append(":").append(parent[i]).append(")");
            if (i != parent.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
