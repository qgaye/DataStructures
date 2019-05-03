package graph.weighted;

import graph.weighted.minimumspantree.KruskalMST;
import graph.weighted.minimumspantree.LazyPrimMST;
import graph.weighted.minimumspantree.PrimMST;
import graph.weighted.shortestpath.BellmanFord;
import graph.weighted.shortestpath.Dijkstra;

import java.util.ArrayList;

/**
 * @author qgaye
 * @date 2019/04/25
 */
public class Main {

    public static void main(String[] args) {
        Integer[][] arr = new Integer[][]{{4, 5}, {4, 7}, {5, 7}, {0, 7}, {1, 5}, {0, 4}, {2, 3}, {1, 7}, {0, 2}, {1, 2}, {1, 3}, {2, 7}, {6, 2}, {3, 6}, {6, 0}, {6, 4}};
        Double[] weights = new Double[]{0.35, 0.37, 0.28, 0.16, 0.32, 0.38, 0.17, 0.19, 0.26, 0.36, 0.29, 0.34, 0.40, 0.52, 0.58, 0.93};
        DenseWeightedGraph<Double> denseWeightedGraph = new DenseWeightedGraph<>(8, false);
        InitWeightGraph.init(denseWeightedGraph, arr, weights);
        denseWeightedGraph.show();

        SparseWeightGraph<Double> sparseWeightGraph = new SparseWeightGraph<>(8, false);
        InitWeightGraph.init(sparseWeightGraph, arr, weights);
        sparseWeightGraph.show();

        System.out.println("Test Lazy Prim MST: ");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<>(sparseWeightGraph);
        ArrayList<Edge<Double>> mstEdges = lazyPrimMST.mstEdges();
        for (Edge<Double> edge : mstEdges) {
            System.out.println(edge);
        }
        System.out.println("The MST weight is " + lazyPrimMST.result());

        System.out.println("Test Prim MST: ");
        PrimMST<Double> primMST = new PrimMST<>(sparseWeightGraph);
        ArrayList<Edge<Double>> mstEdges1 = primMST.mstEdges();
        for (Edge<Double> edge : mstEdges1) {
            System.out.println(edge);
        }
        System.out.println("The MST weight is " + primMST.result());

        System.out.println("Test Kriskal MST: ");
        KruskalMST<Double> kruskalMST = new KruskalMST<>(sparseWeightGraph);
        ArrayList<Edge<Double>> mstEdges2 = kruskalMST.mstEdges();
        for (Edge<Double> edge : mstEdges2) {
            System.out.println(edge);
        }
        System.out.println("The MST weight is " + kruskalMST.result());


        Integer[][] arr2 = new Integer[][]{{0, 1}, {0, 2}, {0, 3}, {2, 1}, {2, 3}, {1, 4}, {2, 4}, {3, 4}};
        Integer[] weights2 = new Integer[]{5, 2, 6, 1, 3, 1, 5, 2};
        SparseWeightGraph<Integer> sparseWeightGraph2 = new SparseWeightGraph<>(5, true);
        InitWeightGraph.init(sparseWeightGraph2, arr2, weights2);
        sparseWeightGraph2.show();

        System.out.println("Test Dijkstra shortest path: ");
        Dijkstra<Integer> dijkstra = new Dijkstra<>(sparseWeightGraph2, 0);
        System.out.println("The shortest path to 4:");
        dijkstra.showPath(4);
        System.out.println("value: " + dijkstra.shortestPathTo(4));

        Integer[][] arr3 = new Integer[][]{{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}, {1, 4}, {2, 4}, {4, 3}};
        Integer[] weights3 = new Integer[]{5, 2, 6, -4, 3, 2, 5, -3};
        SparseWeightGraph<Integer> sparseWeightGraph3 = new SparseWeightGraph<>(5, true);
        InitWeightGraph.init(sparseWeightGraph3, arr3, weights3);
        sparseWeightGraph3.show();

        System.out.println("Test BellmanFord shortest path: ");
        BellmanFord<Integer> bellmanFord = new BellmanFord<>(sparseWeightGraph3, 0);
        System.out.println("The shortest path to 4: ");
        bellmanFord.showPath(3);
        System.out.println("value: " + bellmanFord.shortestPathTo(3));
    }
}
