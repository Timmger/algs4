package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.DepthFirstOrder;
/**
 * 拓扑排序(有向无环图)
 * @author Direct-X
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if (!cyclefinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String seperator = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, seperator);

        Topological top = new Topological(sg.digraph());
        for (int v : top.order()) {
            StdOut.println(sg.nameOf(v));
        }
    }
}
