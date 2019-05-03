package unionfind;

/**
 * 路径压缩
 * 与UnionFindFive思路相同，只是在find操作中直接将子节点挂载到了根节点上
 * @author qgaye
 * @date 2019/03/21
 */
public class UnionFindSix implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFindSix(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 使用递归使得将节点直接挂在根节点之下
     * 其实使用非递归方法只需要多次调用find方法也可以达到目的
     */
    private int find(int p) {
        if (p < 0 || p > parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        if  (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
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
