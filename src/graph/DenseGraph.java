package graph;

import java.util.ArrayList;

/**
 * 邻接矩阵 -- 稠密图
 * @author qgaye
 * @date 2019/04/23
 */
public class DenseGraph implements Graph {

    // 节点数
    private int n;
    // 边数
    private int m;
    // 是否为有向图
    private boolean directed;
    // 矩阵
    private boolean[][] g;

    public DenseGraph(int n, boolean directed) {
        if (n < 0) {
            throw new IllegalArgumentException("Illegal argument m.");
        }
        this.n = n;
        this.directed = directed;
        this.m = 0;
        // 为n*n的矩阵全部赋值为false
        g = new boolean[n][n];
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

        if (hasEdge(v, w)) {
            return;
        }

        g[v][w] = true;
        if (v != w && !directed) {
            g[w][v] = true;
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
        return g[v][w];
    }

    @Override
    public Iterable<Integer> adj(int v) {
        if (v < 0 || v >= n) {
            throw new IllegalArgumentException("Illegal argument v.");
        }
        ArrayList<Integer> adjV = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (g[v][i]) {
                adjV.add(i);
            }
        }
        return adjV;
    }

    @Override
    public void show() {
        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(i + " :");
            for (int j = 0; j < n; j++) {
                System.out.printf("%4s", (g[i][j] ? 1 : 0));
            }
            System.out.println();
        }
    }
}
