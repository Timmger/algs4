package sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation pl;
    private int trials; // 实验次数
    private double[] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int T) {
        if (n < 1 || T < 1) {
            throw new IllegalArgumentException("n and T must be positive");
        }
        trials = T;
        fractions = new double[trials];
        for (int i = 0; i < trials; i++) {
            pl = new Percolation(n);
            while (!pl.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!pl.isOpen(row, col)) {
                    pl.open(row, col);
                }
            }
            double fraction = (double)pl.numberOfOpenSites() / (n * n);
            fractions[i] = fraction;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(n, T);

        String confidence = p.confidenceLo() + ", " + p.confidenceHi();
        StdOut.println("mean                 =" + p.mean());
        StdOut.println("stddev                 =" + p.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }

}