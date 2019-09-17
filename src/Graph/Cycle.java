package Graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    /**
     * 确定给定的无向图是否包含环 如果包含，找出它
     */
    public Cycle(Graph G) {
        if (hasSelfLoop(G)) {
            return;
        }
        if (hasParallelEdges(G)) {
            return;
        }

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, -1, v);
            }
        }
    }

    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) {
                return;
            }

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, v, w);
            }

            // 检查cycle(但忽略连接到v的边的反转）
            else if (w != u) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }


    /**
     * 给定的图是否有自环 副作用： 初始化cycle为自环
     *
     * @param G
     * @return
     */
    private boolean hasSelfLoop(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 给定的图是否含有平行边 副作用： 初始化cycle为两平行边
     *
     * @param G
     * @return
     */
    private boolean hasParallelEdges(Graph G) {
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            // 检查v的平行边
            for (int w : G.adj(v)) {
                if (marked[v]) {
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                }
                marked[w] = true;
            }

            // 重置
            for (int w : G.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("Graph is acyclic");
        }
    }
}
