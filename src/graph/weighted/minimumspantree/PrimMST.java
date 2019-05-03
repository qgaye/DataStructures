package graph.weighted.minimumspantree;

import graph.weighted.Edge;
import graph.weighted.IndexMinHeap;
import graph.weighted.WeightedGraph;

import java.util.ArrayList;

/**
 * 优化的Prim算法求解图的最小生成树 (使用索引堆)
 * O(ElogV)
 * @author qgaye
 * @date 2019/04/26
 */
public class PrimMST<Weight extends Number & Comparable> {

    // 图的引用
    private WeightedGraph<Weight> graph;
    // 最小索引堆
    private IndexMinHeap<Edge<Weight>> indexMinHeap;
    // 该节点是否被访问过
    private boolean[] marked;
    // 最小生成树包含的所有边
    private ArrayList<Edge<Weight>> mst;
    // 最小生成树的权值
    private Number mstWeight;

    public PrimMST(WeightedGraph<Weight> graph) {
        this.graph = graph;
        indexMinHeap = new IndexMinHeap<>(graph.V());
        marked = new boolean[graph.V()];
        mst = new ArrayList<>(graph.V());
        mstWeight = 0;

        // Prim
        visit(0);
        while (!indexMinHeap.isEmpty()) {
            Integer index = indexMinHeap.poll();
            Edge<Weight> edge = indexMinHeap.getData(index);
            mst.add(edge);
            visit(index);
        }

        // 计算最小生成树的权值
        for (int i = 0; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.get(i).weight().doubleValue();
        }
    }

    private void visit(int v) {
        marked[v] = true;
        for (Edge<Weight> edge : graph.adj(v)) {
            int w = edge.other(v);
            if (!marked[w]) {
                Edge<Weight> e = indexMinHeap.getData(w);
                // 当索引堆中的w索引处无内容或者权重比edge中大时，更新
                if (e == null || e.compareTo(edge) > 0) {
                    indexMinHeap.update(w, edge);
                }
            }
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
