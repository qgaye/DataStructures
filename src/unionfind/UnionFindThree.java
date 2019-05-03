package unionfind;

/**
 * 基于树的节点树进行优化
 * @author qgaye
 * @date 2019/03/20
 */
public class UnionFindThree implements UF {

    private int[] parent;
    private int[] sz;

    public UnionFindThree(int size) {
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of  bound");
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

        if (sz[pRoot] <= sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
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
