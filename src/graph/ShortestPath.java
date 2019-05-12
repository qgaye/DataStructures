package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 最短路径
 * @author qgaye
 * @date 2019/04/24
 */
public class ShortestPath {

    private Graph graph;
    // 起始点
    private int start;
    // 该节点是否已经被访问过
    private boolean[] visited;
    // 记录路径，from[i]表示查找的路径上i的上一个节点
    private int[] from;
    // 记录路径中节点次序，ord[i]表示节点i在路径中次序
    private int[] ord;

    public ShortestPath(Graph graph, int start) {
        this.graph = graph;
        if (start < 0 || start > graph.V()) {
            throw new IllegalArgumentException("Start is illegal.");
        }
        this.start = start;
        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        ord = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }

        // 广度优先遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        ord[start] = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer i : graph.adj(v)) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }
    }

    /**
     * start到w之间是否有路径
     */
    public boolean hasPath(int w) {
        if (w < 0 || w > graph.V()) {
            throw new IllegalArgumentException("w is illegal");
        }
        return visited[w];
    }

    /**
     * 将start点到w点之间的最短路径返回
     */
    public ArrayList<Integer> path(int w) {
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
     * 打印从start点到w点的最短路径
     */
    public void showPath(int w) {
        if (w < 0 || w > graph.V()) {
            throw new IllegalArgumentException("w is illegal");
        }
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

    /**
     * 从s到w最短路径到长度
     */
    public int length(int w) {
        if (w < 0 || w > graph.V()) {
            throw new IllegalArgumentException("w is illegal");
        }
        if (!hasPath(w)) {
            throw new IllegalArgumentException("No road from " + start + " to " + w);
        }
        return ord[w];
    }
}
