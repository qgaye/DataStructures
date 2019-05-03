package graph.weighted.minimumspantree;

import graph.weighted.Edge;
import graph.weighted.MinHeap;
import graph.weighted.WeightedGraph;

import java.util.ArrayList;

/**
 * Prim算法求解图的最小生成树
 * 切分定理，将图分为两部分，找出之间权值最小的边，则该边一定在最小生成树中
 * O(ElogE)
 * @author qgaye
 * @date 2019/04/25
 */
public class LazyPrimMST<Weight extends Number & Comparable> {

    // 图的引用
    private WeightedGraph<Weight> graph;
    // 最小堆
    private MinHeap<Edge<Weight>> minHeap;
    // 该节点是否被访问过
    private boolean[] marked;
    // 最小生成树包含的所有边
    private ArrayList<Edge<Weight>> mst;
    // 最小生成树的权值
    private Number mstWeight;

    public LazyPrimMST(WeightedGraph<Weight> graph) {
        this.graph = graph;
        minHeap = new MinHeap<>(graph.E());
        marked = new boolean[graph.V()];
        mst = new ArrayList<>(graph.V());
        mstWeight = 0;

        // Lazy Prim
        visit(0);
        while (!minHeap.isEmpty()) {
            Edge<Weight> edge = minHeap.poll();
            if (marked[edge.V()] && marked[edge.W()]) {
                continue;
            }
            mst.add(edge);
            if (!marked[edge.V()]) {
                visit(edge.V());
            } else {
                visit(edge.W());
            }
        }

        // 计算最小生成树的权值
        for (int i = 0; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.get(i).weight().doubleValue();
        }
    }

    private void visit(int v) {
        marked[v] = true;
        // 将与节点v相邻的所有未访问的边放入最小堆中
        for (Edge<Weight> edge : graph.adj(v)) {
            minHeap.offer(edge);
        }
    }

    /**
     * 返回最小生成树的所有边
     */
    public ArrayList<Edge<Weight>> mstEdges() {
        return mst;
    }

    /**
     * 返回最小生成树的权值
     */
    public Number result() {
        return mstWeight;
    }
}
