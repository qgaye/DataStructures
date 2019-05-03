package graph.weighted.minimumspantree;

import graph.weighted.Edge;
import graph.weighted.MinHeap;
import graph.weighted.UnionFind;
import graph.weighted.WeightedGraph;

import java.util.ArrayList;

/**
 * 使用Kruskal算法求解图的最小生成树
 * 将所有边进行排序，以此将最小边添加进最小生成树中，若新添加的边会导致成环，则抛弃这条边
 * O(ElogE)
 * @author qgaye
 * @date 2019/04/27
 */
public class KruskalMST<Weight extends Number & Comparable> {

    // 图的引用
    private WeightedGraph<Weight> graph;
    // 最小生成树所包含的所有边
    private ArrayList<Edge<Weight>> mst;
    // 最小生成树的权值
    private Number mstWeight;

    public KruskalMST(WeightedGraph<Weight> graph) {
        this.graph = graph;
        mst = new ArrayList<>(graph.V());
        mstWeight = 0;

        // 对所有边进行进行一次排序
        MinHeap<Edge<Weight>> minHeap = new MinHeap<>(graph.E());
        for (int v = 0; v < graph.V(); v++) {
            for (Edge<Weight> edge : graph.adj(v)) {
                if (edge.V() <= edge.W()) {
                    minHeap.offer(edge);
                }
            }
        }

        // 使用并查集判断是否存在环
        UnionFind unionFind = new UnionFind(graph.V());
        while (!minHeap.isEmpty() && mst.size() != graph.V()) {
            Edge<Weight> edge = minHeap.poll();
            // 不会形成环，则这条边才是最小生成树中到边
            if (!unionFind.isConnected(edge.V(), edge.W())) {
                mst.add(edge);
                unionFind.unionElement(edge.V(), edge.W());
            }
        }

        // 计算最小生成树的权值
        for (int i = 0; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.get(i).weight().doubleValue();
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
