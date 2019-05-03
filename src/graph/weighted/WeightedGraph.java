package graph.weighted;

/**
 * @author qgaye
 * @date 2019/04/24
 */
public interface WeightedGraph<Weight extends Number & Comparable> {

    /**
     * 返回节点数
     */
    int V();

    /**
     * 返回边数
     */
    int E();

    /**
     * 向图中添加一条边
     */
    void addEdge(Edge<Weight> edge);

    /**
     * 点v到点w间是否有边
     */
    boolean hasEdge(int v, int w);

    /**
     * 打印图的信息
     */
    void show();

    /**
     * 返回图中一个顶点的所有邻边
     */
    Iterable<Edge<Weight>> adj(int v);
}
