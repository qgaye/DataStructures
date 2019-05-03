package graph.weighted;

import java.util.ArrayList;

/**
 * @author qgaye
 * @date 2019/04/24
 */
public class SparseWeightGraph<Weight extends Number & Comparable> implements WeightedGraph<Weight> {

    // 节点数
    private int n;
    // 边数
    private int m;
    // 是否为有向图
    private boolean directed;
    // 表
    private ArrayList<Edge<Weight>>[] g;

    public SparseWeightGraph(int n, boolean directed) {
        if (n < 0) {
            throw new IllegalArgumentException("n is illegal.");
        }
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = (ArrayList<Edge<Weight>>[]) new ArrayList[n];
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
    public void addEdge(Edge<Weight> edge) {
        if (edge.V() < 0 || edge.V() > n) {
            throw new IllegalArgumentException(edge.V() + "is illegal.");
        }
        if (edge.W() < 0 || edge.W() > n) {
            throw new IllegalArgumentException(edge.W() + "is illegal.");
        }
        // 当使用邻接表时，计算平行边当代价过大，故平行边也一同记入
        g[edge.V()].add(new Edge<>(edge));
        if (edge.V() != edge.W() && !directed) {
            g[edge.W()].add(new Edge<>(edge.W(), edge.V(), edge.weight()));
        }
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v > n) {
            throw new IllegalArgumentException("v is illegal");
        }
        if (w < 0 || w > n) {
            throw new IllegalArgumentException("w is illegal");
        }
        for (Edge<Weight> edge : g[v]) {
            if (edge.other(v) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            System.out.print("arrayList " + i + " : ");
            for (Edge<Weight> edge : g[i]) {
                System.out.printf("(to: %2s, weight: %4s)  ", edge.other(i), edge.weight());
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        if (v < 0 || v > n) {
            throw new IllegalArgumentException("v is illegal");
        }
        return g[v];
    }
}
