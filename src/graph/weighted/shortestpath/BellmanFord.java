package graph.weighted.shortestpath;

import graph.weighted.Edge;
import graph.weighted.WeightedGraph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 该算法适用于负权值的最短路径求解问题
 * O(EV)
 * 该算法只对有向图有效，若为无向图，只要一条边为负权值，就存在负权环，若不存在负权环，则一定权值一定为正，即可使用Dijkstra
 * @author qgaye
 * @date 2019/05/03
 */
public class BellmanFord<Weight extends Number & Comparable<Weight>> {

    private WeightedGraph<Weight> graph;

    private int start;

    private Number[] distTo;

    private Edge<Weight>[] from;
    // 图中是否有负权环
    private boolean hasNegativeCycle;

    public BellmanFord(WeightedGraph<Weight> graph, int start) {
        this.graph = graph;
        if (start < 0 || start >= graph.V()) {
            throw new IllegalArgumentException("start - " + start + " is illegal.");
        }
        this.start = start;
        distTo = new Number[graph.V()];
        from = new Edge[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            from[i] = null;
        }

        distTo[start] = 0.0;
        from[start] = new Edge<>(start, start, (Weight) (Number) 0.0);
        // pass表示做了多少次松弛操作
        // 若不存在负权环，则一定在(节点数-1)的次数内找到最短路径
        // 从1开始，到(节点数-1)表示从start节点开始经过(节点数-1)条边一定能找到最短路径，除非存在负权环
        for (int pass = 1; pass < graph.V(); pass++) {
            // 图中的的所有点
            // 对图中的每个节点做松弛操作
            for (int i = 0; i < graph.V(); i++) {
                // 每个点的邻边
                for (Edge<Weight> edge : graph.adj(i)) {
                    // 当这条邻边的一个点已存在from中(第一次遍历时，只有start点不为空)，且另一个节点处还未为空或者权值比新的大，则更新
                    if (from[edge.V()] != null && (from[edge.W()] == null || distTo[edge.V()].doubleValue() + edge.weight().doubleValue() < distTo[edge.W()].doubleValue())) {
                        distTo[edge.W()] = distTo[edge.V()].doubleValue() + edge.weight().doubleValue();
                        from[edge.W()] = edge;
                    }
                }
            }
        }
        // 再对每个节点做一次松弛操作，若这次操作存在更新情况表明存在负权环
        hasNegativeCycle = detectNegativeCycle();
    }

    /**
     * 判断图中是否有负权环
     */
    private boolean detectNegativeCycle() {
        for (int i = 0; i < graph.V(); i++) {
            for (Edge<Weight> edge : graph.adj(i)) {
                if (from[edge.V()] != null && distTo[edge.V()].doubleValue() + edge.weight().doubleValue() < distTo[edge.W()].doubleValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPathTo(int w) {
        if (w < 0 || w >= graph.V()) {
            throw new IllegalArgumentException("w - " + w + " is illegal.");
        }
        return from[w] != null;

    }

    public Number shortestPathTo(int w) {
        if (w < 0 || w >= graph.V()) {
            throw new IllegalArgumentException("w - " + w + " is illegal.");
        }
        if (hasNegativeCycle) {
            throw new IllegalArgumentException("Graph has negative cycle.");
        }
        if (!hasPathTo(w)) {
            throw new IllegalArgumentException("no path to w - " + w + ".");
        }
        return distTo[w];
    }

    public ArrayList<Edge<Weight>> shortestPath(int w) {
        if (w < 0 || w >= graph.V()) {
            throw new IllegalArgumentException("w - " + w + " is illegal.");
        }
        if (hasNegativeCycle) {
            throw new IllegalArgumentException("Graph has negative cycle.");
        }
        if (!hasPathTo(w)) {
            throw new IllegalArgumentException("no path to w - " + w + ".");
        }

        Stack<Edge<Weight>> stack = new Stack<>();
        Edge<Weight> edge = from[w];
        while (edge.V() != start) {
            stack.push(edge);
            edge = from[edge.V()];
        }
        stack.push(edge);

        ArrayList<Edge<Weight>> path = new ArrayList<>();
        while (!stack.isEmpty()) {
            Edge<Weight> edge1 = stack.pop();
            path.add(edge1);
        }
        return path;
    }

    public void showPath(int w) {
        ArrayList<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).V() + " -> ");
            if (i == path.size() - 1) {
                System.out.println(path.get(i).W());
            }
        }
    }
}
