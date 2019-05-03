package graph;

/**
 * 求无权图的连通分量
 * @author qgaye
 * @date 2019/04/23
 */
public class Componnts {

    private Graph graph;
    // 该节点是否已经被访问过
    private boolean[] visited;
    // 连通分量个数
    private int ccount;
    // 每个节点对应的连通分量标记
    private int[] id;

    public Componnts(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        id = new int[graph.V()];
        ccount = 0;
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }
        for (int i = 0; i < graph.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }
    }

    /**
     * 深度优先遍历
     */
    private void dfs(int v) {
        visited[v] = true;
        id[v] = ccount;
        for (Integer i : graph.adj(v)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public int ccount() {
        return ccount;
    }

    public boolean isConnected(int v, int w) {
        if (v < 0 || v >= graph.V()) {
            throw new IllegalArgumentException("Illegal argument v.");
        }
        if (w < 0 || w >= graph.V()) {
            throw new IllegalArgumentException("Illegal argument v.");
        }
        return id[v] == id[w];
    }
}
