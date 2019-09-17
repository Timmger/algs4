package sort;

import edu.princeton.cs.algs4.StdOut;

public class OrderedArrayMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public OrderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Key x) {
        int i = n - 1;
        while (i >= 0 && less(x, pq[i])) {
            pq[i+1] = pq[i];
            i--;
        }
        pq[i+1] = x;
        n++;
    }

    public Key delMax() {
        return pq[--n];
    }

    private boolean less(Key i, Key j) {
        return i.compareTo(j) < 0;
    }

    public static void main(String[] args) {
        OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }

}
