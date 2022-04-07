package hw2;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] times;
    private int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        times = new double[T];
        this.T = T;
        int row;
        int col;
        for (int i = 0; i < T; i++) {
            Percolation pc = pf.make(N);
            while (pc.percolates() == false) {
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
                pc.open(row, col);
            }
            times[i] = ((double) pc.numberOfOpenSites()) / (double) N * N;
        }
    }

    public double mean() {
        return StdStats.mean(times);
    }

    public double stddev() {
        return StdStats.stddev(times);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
