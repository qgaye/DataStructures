package graph.weighted.minimumspantree;

import graph.weighted.Edge;
import graph.weighted.WeightedGraph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 优化的Prim算法求解图的最小生成树  (使用优先队列)
 * O(ElogV)
 * @author qgaye
 * @date 2019/04/26
 */
public class PrimMST2<Weight extends Number & Comparable> {

    // 图的引用
    private WeightedGraph<Weight> graph;
    // 最小堆，将data中当索引作为存储值，data中的weight越小，其索引则位于头部
    private PriorityQueue<Integer> order;
    // 存放edge，索引表示节点
    private ArrayList<Edge<Weight>> data;
    // 该节点是否被访问过
    private boolean[] marked;
    // 最小生成树包含的所有边
    private ArrayList<Edge<Weight>> mst;
    // 最小生成树的权值
    private Number mstWeight;

    public PrimMST2(WeightedGraph<Weight> graph) {
        this.graph = graph;
        order = new PriorityQueue<>((o1, o2) -> {
            if (data.get(o1).compareTo(data.get(o2)) < 0) {
                return -1;
            } else if (data.get(o1).compareTo(data.get(o2)) > 0) {
                return 1;
            } else {
                return 0;
            }
        });
        data = new ArrayList<>(graph.V());
        for (int i = 0; i < graph.V(); i++) {
            data.add(new Edge<>());
        }
        marked = new boolean[graph.V()];
        mst = new ArrayList<>(graph.V());
        mstWeight = 0;

        // Prim
        visit(0);
        while (!order.isEmpty()) {
            Integer index = order.poll();
            Edge<Weight> edge = data.get(index);
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
                // 当该节点处还没有edge，添加edge，并放入最小堆
                if (data.get(w).weight() == null) {
                    data.set(w, new Edge<>(edge));
                    order.offer(w);
                } // 当该节点处已经有了edge，更新edge，先将最小堆中这个索引删除，再将这个索引放入，以到达重新排序当作用
                else if (data.get(w).weight().compareTo(edge.weight()) > 0) {
                    for (int i = 0; i < order.size(); i++) {
                        if (order.toArray()[i].equals(w)) {
                            order.remove(w);
                        }
                    }
                    data.set(w, new Edge<>(edge));
                    order.offer(w);
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

