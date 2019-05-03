package unionfind;

/**
 * 最普通的并查集
 * 相连的节点中id[]相同
 * @author qgaye
 * @date 2019/03/19
 */
public class UnionFindOne implements UF {

    private int[] id;

    public UnionFindOne(Integer size) {

        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    // O(1)
    public int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        return id[p];
    }

    @Override
    public Integer getSize() {
        return id.length;
    }

    // O(1)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // O(n)
    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < id.length; i++) {
            res.append("(").append(i).append(":").append(id[i]).append(")");
            if (i != id.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
