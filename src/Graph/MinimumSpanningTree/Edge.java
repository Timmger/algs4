package Graph.MinimumSpanningTree;

import edu.princeton.cs.algs4.StdOut;

public class Edge implements Comparable<Edge>{
    //顶点之一
    private final int v;
    //另一个顶点
    private final int w;
    //边的权重
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        }
        else if (vertex == w) {
            return v;
        }
        else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }

    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        StdOut.println(e);
    }
}
