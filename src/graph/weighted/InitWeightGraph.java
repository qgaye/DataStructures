package graph.weighted;

/**
 * @author qgaye
 * @date 2019/04/25
 */
public class InitWeightGraph {

    public static <T extends Number & Comparable> void init(WeightedGraph graph, Integer[][] arr, T[] weights) {
        if (arr.length != weights.length) {
            throw new IllegalArgumentException("arr not pair weights.");
        }
        if (arr[0].length != 2) {
            throw new IllegalArgumentException("arr size is illegal.");
        }
        for (int i = 0; i < arr.length; i++) {
            graph.addEdge(new Edge<T>(arr[i][0], arr[i][1], weights[i]));
        }
    }
}
