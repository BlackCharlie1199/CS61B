package hw2;

import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;



public class PercolationStats {
    private double[] percolationThreshold;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (T <= 0 || N <= 0) {
            throw new IllegalArgumentException("T or N should be greater than 0");
        }
        percolationThreshold = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation grid = pf.make(N);
            double openGrid = 0;
            while (!grid.percolates()) {
                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);
                grid.open(randomRow, randomCol);
                openGrid ++;
            }
            percolationThreshold[i] = openGrid / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.percolationThreshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.percolationThreshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double sampleMean = this.mean();
        double sampleStandardDeviation = this.stddev();

        return sampleMean - 1.96 * sampleStandardDeviation / Math.sqrt(this.percolationThreshold.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double sampleMean = this.mean();
        double sampleStandardDeviation = this.stddev();

        return sampleMean + 1.96 * sampleStandardDeviation / Math.sqrt(this.percolationThreshold.length);
    }
}
