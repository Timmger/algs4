package sort;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private boolean[][] a;
    private int top = 0; // initialize two visual sites
    private int bottom;
    private int size; // size of grid
    private WeightedQuickUnionUF qf;
    private int count;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be positive");
        }
        count = 0;
        size = n;
        a = new boolean[n][n];
        bottom = n * n + 1;
        qf = new WeightedQuickUnionUF(n * n + 2);

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        setBounds(row, col);
        a[row - 1][col - 1] = true;
        if (row == 1) {
            qf.union(getQFIndex(row, col), top);
        }
        if (row == size) {
            qf.union(getQFIndex(row, col), bottom);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            qf.union(getQFIndex(row, col), getQFIndex(row, col - 1));
        }
        if (col < size && isOpen(row, col + 1)) {
            qf.union(getQFIndex(row, col), getQFIndex(row, col + 1));
        }
        if (row < size && isOpen(row + 1, col)) {
            qf.union(getQFIndex(row, col), getQFIndex(row + 1, col));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            qf.union(getQFIndex(row, col), getQFIndex(row - 1, col));
        }
        count++;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        setBounds(row, col);
        return a[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        setBounds(row, col);
        return qf.connected(top, getQFIndex(row, col));

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    // set row and col inside their range
    public void setBounds(int row, int col) {
        int n = a.length;
        if (row < 1 || row > n || col < 1 || row > n) {
            throw new IndexOutOfBoundsException("row and col must between 1 and n");
        }
    }

    // get the index of qf in grid
    private int getQFIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    // test client (optional)
    // public static void main(String[] args)
}
