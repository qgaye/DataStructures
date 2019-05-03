package graph;

/**
 * @author qgaye
 * @date 2019/04/23
 */
public class InitGraph {

    public static void init(Graph graph, Integer[][] array) {
        if (array[0].length != 2) {
            throw new IllegalArgumentException("Array size is illegal.");
        }
        for (Integer[] arr : array) {
            graph.addEdge(arr[0], arr[1]);
        }
    }
}
