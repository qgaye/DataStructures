package graph;

/**
 * @author qgaye
 * @date 2019/04/23
 */
public interface Graph {

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
    void addEdge(int v, int w);

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
    Iterable<Integer> adj(int v);
}
