package Graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 顶点对可达性（传递闭包）
 */
public class TransitiveClosure {
    //tc[v] = reachable from v
    private DirectedDFS[] tc;

    public TransitiveClosure(Digraph G) {
        tc = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++) {
            tc[v] = new DirectedDFS(G, v);
        }
    }

    public boolean reachable(int v, int w) {
        return tc[v].marked(w);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        TransitiveClosure tc = new TransitiveClosure(G);

        //print header
        StdOut.print("   ");
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%3d", v);
        }
        StdOut.println();
        StdOut.println("-----------------------------------------------");

        //print transitive closure
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%3d", v);
            for (int w = 0; w < G.V(); w++) {
                if (tc.reachable(v, w)) {
                    StdOut.printf("  T");
                }
                else {
                    StdOut.printf("   ");
                }
            }
            StdOut.println();
        }
    }

}
