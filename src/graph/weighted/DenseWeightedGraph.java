package graph.weighted;

import java.util.ArrayList;

/**
 * @author qgaye
 * @date 2019/04/24
 */
public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph<Weight> {

    // 节点数
    private int n;
    // 边数
    private int m;
    // 是否为有向图
    private boolean directed;
    // 矩阵
    private Edge<Weight>[][] g;

    public DenseWeightedGraph(int n, boolean directed) {
        if (n < 0) {
            throw new IllegalArgumentException("n is illegal.");
        }
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = (Edge<Weight>[][]) new Edge[n][n];
        for (Edge<Weight>[] edges : g) {
            for (Edge<Weight> edge : edges) {
                edge = null;
            }
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
    public void addEdge(Edge<Weight> edge) {
        if (edge.V() < 0 || edge.V() > n) {
            throw new IllegalArgumentException(edge.V() + "is illegal.");
        }
        if (edge.W() < 0 || edge.W() > n) {
            throw new IllegalArgumentException(edge.W() + "is illegal.");
        }
        if (hasEdge(edge.V(), edge.W())) {
            return;
        }
        g[edge.V()][edge.W()] = new Edge<>(edge);
        if (edge.V() != edge.W() && !directed) {
            g[edge.W()][edge.V()] = new Edge<>(edge.W(), edge.V(), edge.weight());
        }
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v > n) {
            throw new IllegalArgumentException(v + "is illegal.");
        }
        if (w < 0 || w > n) {
            throw new IllegalArgumentException(w + "is illegal.");
        }
        return g[v][w] != null;
    }

    @Override
    public void show() {
        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%6s", i);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(i + " :");
            for (int j = 0; j < n; j++) {
                if (g[i][j] != null) {
                    System.out.printf("%6s", g[i][j].weight());
                } else {
                    System.out.printf("%6s", "NULL");
                }
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        if (v < 0 || v > n) {
            throw new IllegalArgumentException(v + "is illegal");
        }
        ArrayList<Edge<Weight>> adjV = new ArrayList<>();
        for (Edge<Weight> edge : g[v]) {
            if (edge != null) {
                adjV.add(edge);
            }
        }
        return adjV;
    }
}
