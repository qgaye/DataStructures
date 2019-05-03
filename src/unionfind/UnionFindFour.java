package unionfind;

/**
 * 基于树高进行优化
 * 树高不同时，合并后树高一定是原先树高较高的那个
 * 树高相同时，合并后树高必定比原先高1
 * @author qgaye
 * @date 2019/03/21
 */
public class UnionFindFour implements UF {

    private int[] parent;
    // 树高
    private int[] rank;

    public UnionFindFour(int size) {
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
