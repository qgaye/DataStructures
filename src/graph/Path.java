package graph;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 寻路算法
 * @author qgaye
 * @date 2019/04/23
 */
public class Path {

    private Graph graph;
    // 起始点
    private int start;
    // 该节点是否已经被访问过
    private boolean[] visited;
    // 记录路径，from[i]表示查找的路径上i的上一个节点
    private int[] from;

    /**
     * 寻找图graph从start点到其他点点路径
     */
    public Path(Graph graph, int start) {
        this.graph = graph;
        if (start < 0 || start > graph.V()) {
            throw new IllegalArgumentException("Start is illegal.");
        }
        this.start = start;
        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        dfs(start);
    }

    /**
     * 深度优先遍历
     */
    private void dfs(int v) {
        visited[v] = true;
        for (Integer i : graph.adj(v)) {
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
            }
        }
    }

    /**
     * 从start点到w点间是否有路径
     */
    public boolean hasPath(int w) {
        if (w < 0 || w > graph.V()) {
            throw new IllegalArgumentException("W is illegal.");
        }
        return visited[w];
    }

    /**
     * 将start点到w点之间的路径返回
     */
    public ArrayList<Integer> path(int w) {
        if (w < 0 || w > graph.V()) {
            throw new IllegalArgumentException("w is illegal");
        }
        if (!hasPath(w)) {
            throw new IllegalArgumentException("No road from " + start + " to " + w);
        }
        // 按w到s到路径入栈
        Stack<Integer> stack = new Stack<>();
        int p = w;
        while (p != -1) {
            stack.push(p);
            p = from[p];
        }
        // 按s到w出栈，放入arrayList
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!stack.empty()) {
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    /**
     * 打印从start点到w点的路径
     */
    public void showPath(int w) {
        if (!hasPath(w)) {
            throw new IllegalArgumentException("No road from " + start + " to " + w);
        }
        ArrayList<Integer> arrayList = path(w);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i));
            if (i == arrayList.size() - 1) {
                System.out.println();
            } else {
                System.out.print(" -> ");
            }
        }

    }
}
