package graph.weighted;

/**
 * @author qgaye
 * @date 2019/04/24
 */
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge<Weight>> {

    // 边的两个端点
    private int v, w;
    // 边的权值
    private Weight weight;

    public Edge() {
        this.v = 0;
        this.w = 0;
        this.weight = null;
    }

    public Edge(int v, int w, Weight weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Edge(Edge<Weight> edge) {
        this.v = edge.v;
        this.w = edge.w;
        this.weight = edge.weight;
    }

    public int V() {
        return v;
    }

    public int W() {
        return w;
    }

    public Weight weight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    //  给一个节点，返回edge中另一个节点
    public int other(int x) {
        if (x != v && x != w) {
            throw new IllegalArgumentException(x + " is not illegal.");
        }
        return x == v ? w : v;
    }

    @Override
    public String toString() {
        return "" + v + " - " + w + " : " + weight;
    }

    @Override
    public int compareTo(Edge another) {
        if (weight.compareTo(another.weight()) < 0) {
            return -1;
        } else if (weight.compareTo(another.weight()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
