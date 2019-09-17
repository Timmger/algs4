package Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.net.InterfaceAddress;

/**
 * 有向环检测
 * @author Direct-X
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    // 顶点在stack上吗
    private boolean[] onStack;
    // 有向环（如果没有，则为null）
    private Stack<Integer> cycle;


    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            // 如果找到有向环，则短路返回
            if (cycle != null) {
                return;
            }
            // 递归遍历
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
            // 追踪有向环
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);

            }

        }
        onStack[v] =false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }
}
