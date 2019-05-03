package graph.weighted.shortestpath;

import graph.weighted.Edge;
import graph.weighted.IndexMinHeap;
import graph.weighted.WeightedGraph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Dijkstra最短路径算法
 * 权值中不能有负值
 * 在一个节点的所有邻边中选出最短的那条，则这条一定是最短路径
 * O(ElogV)q
 * @author qgaye
 * @date 2019/04/27
 */
public class Dijkstra<Weight extends Number & Comparable<Weight>> {

    // 图
    private WeightedGraph<Weight> graph;
    // 起始点
    private int start;
    // 存储到各点到最短路径
    // 最小索引堆中的data存储了各个索引处的最小路径值
    private IndexMinHeap<Weight> indexMinHeap;
    // 该节点是否被访问过
    private boolean[] marked;
    // 该节点的上一个节点
    private Edge<Weight>[] from;

    public Dijkstra(WeightedGraph<Weight> graph, int start) {
        this.graph = graph;
        if (start < 0 || start >= graph.V()) {
            throw new IllegalArgumentException("start - " + start + " is illegal.");
        }
        this.start = start;
        indexMinHeap = new IndexMinHeap<>(graph.V());
        marked = new boolean[graph.V()];
        from = new Edge[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            marked[i] = false;
            from[i] = null;
        }


        from[start] = new Edge<>(start, start, null);
        // 起始点的最短路径设为null，在使用时，null表示0.0
        indexMinHeap.update(start, (Weight) (Number)0.0);
        while (!indexMinHeap.isEmpty()) {
            Integer index = indexMinHeap.poll();
            marked[index] = true;
            for (Edge<Weight> edge : graph.adj(index)) {
                // edge中的V()表示的是起始点，W()表示的是终点
                // Integer w = edge.other(index);
                Integer w = edge.W();
                if (!marked[w]) {
                    // 当最小索引堆中的data的值为空，或者通过松弛操作得到的新值比原先的小，则更新
                    Weight newWeight = (Weight) (Number) (edge.weight().doubleValue() + indexMinHeap.getData(index).doubleValue());
                    if (indexMinHeap.getData(w) == null || indexMinHeap.getData(w).compareTo(newWeight) > 0) {
                        indexMinHeap.update(w, newWeight);
                        from[w] = edge;
                    }
                }

            }
        }
    }

    // 判断从start点到w点是否连通
    public boolean hasPathTo(int w) {
        if (w < 0 || w >= graph.V()) {
            throw new IllegalArgumentException("w - " + w + " is illegal.");
        }
        return marked[w];
    }

    // 返回从start点到w点的最短路径长度
    public Number shortestPathTo(int w) {
        if (w < 0 || w >= graph.V()) {
            throw new IllegalArgumentException("w - " + w + " is illegal.");
        }
        if (!hasPathTo(w)) {
            throw new IllegalArgumentException("no path to w - " + w + ".");
        }
        return indexMinHeap.getData(w);
    }

    // 寻找从start到w的最短路径, 将整个路径经过的边存放在ArrayList中
    public ArrayList<Edge<Weight>> shortestPath(int w) {
        if (w < 0 || w >= graph.V()) {
            throw new IllegalArgumentException("w - " + w + " is illegal.");
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
            Edge<Weight> e = stack.pop();
            path.add(e);
        }
        return path;
    }

    // 打印出从s点到w点的路径
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
