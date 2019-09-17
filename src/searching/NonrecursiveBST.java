package searching;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @param <Key>
 * @param <Value>
 * @author Direct-X
 */
public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
    //根节点
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val) {
        Node z = new Node(key, val);
        if (root == null) {
            root = z;
            return;
        }

        Node parent = null, x = root;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            }
            else if (cmp > 0) {
                x = x.right;
            }
            else {
                x.val = val;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = z;
        }
        else {
            parent.right = z;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            }
            else if (cmp > 0) {
                x = x.right;
            }
            else {
                return x.val;
            }
        }
        return null;
    }

    public Iterable<Key> keys() {
        Stack<Node> stack = new Stack<Node>();
        Queue<Key> queue = new Queue<Key>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            }
            else {
                x = stack.pop();
                queue.enqueue(x.key);
                x = x.right;
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int n = a.length;
        NonrecursiveBST<String, Integer> st = new NonrecursiveBST<String, Integer>();
        for (int i = 0; i < n; i++) {

            st.put(a[i], i);
        }
        for (String s : st.keys()) {

            StdOut.println(s + " " + st.get(s));
        }
    }
}
