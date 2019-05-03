package graph;

import java.util.ArrayList;

/**
 * 邻接表 -- 稀疏图
 *
 * @author qgaye
 * @date 2019/04/23
 */
public class SparseGraph implements Graph {

    // 节点数
    private int n;
    // 边数
    private int m;
    // 是否为有向图
    private boolean directed;
    // 表
    private ArrayList<Integer>[] g;

    public SparseGraph(int n, boolean directed) {
        if (n < 0) {
            throw new IllegalArgumentException("Illegal argument n.");
        }
        this.n = n;
        this.directed = directed;
        this.m = 0;
        g = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
    }

    @Override
    public int V() {
        return n;
    }

    @Override
    public int E() {
        return m;
    }

    @Override
    public void addEdge(int v, int w) {
        if (v < 0 || v >= n) {
            throw new IllegalArgumentException("Illegal argument v.");
        }
        if (w < 0 || w >= n) {
            throw new IllegalArgumentException("Illegal argument v.");
        }
        // 当使用邻接表时，计算平行边当代价过大，故平行边也一同记入
        g[v].add(w);
        if (v != w && hasEdge(v, w)) {
            g[w].add(v);
        }
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= n) {
            throw new IllegalArgumentException("Illegal argument v.");
        }
        if (w < 0 || w >= n) {
            throw new IllegalArgumentException("Illegal argument v.");
        }

        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].get(i) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        if (v < 0 || v >= n) {
            throw new IllegalArgumentException("Illegal argument v.");
        }
        return g[v];
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("arrayList " + i + ":\t");
            for (int j = 0; j < g[i].size(); j++) {
                System.out.print(g[i].get(j) + "\t");
            }
            System.out.println();
        }
    }
}
