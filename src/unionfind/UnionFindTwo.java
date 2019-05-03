package unionfind;

/**
 * 将并查集作为由子节点指向父节点的树
 * parent[]保存其上一个父亲节点
 * @author qgaye
 * @date 2019/03/20
 */
public class UnionFindTwo implements UF {

    private int[] parent;

    public UnionFindTwo(int size) {
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    // O(h)   h为树高
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

    // O(h)   h为树高
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // O(h)   h为树高
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        parent[pRoot] = qRoot;
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
