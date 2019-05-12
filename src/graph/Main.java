package graph;

/**
 * @author qgaye
 * @date 2019/04/23
 */
public class Main {

    public static void main(String[] args) {
        Integer[][] arr = new Integer[][]{{0, 1}, {0, 2}, {0, 5}, {0, 6}, {5, 3}, {5, 4}, {3, 4}, {6, 4}};
        SparseGraph sparseGraph = new SparseGraph(7, false);
        InitGraph.init(sparseGraph, arr);
        sparseGraph.show();

        Path path = new Path(sparseGraph, 0);
        path.showPath(6);

        DenseGraph denseGraph = new DenseGraph(7, false);
        InitGraph.init(denseGraph, arr);
        denseGraph.show();

        ShortestPath shortestPath = new ShortestPath(sparseGraph, 0);
        shortestPath.showPath(4);
    }
}
