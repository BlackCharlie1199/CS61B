package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.LinkedList;

public class Percolation {
    private boolean[][] siteStatus; // true for open
    private int len;
    private int openSiteNumber;
    private WeightedQuickUnionUF siteGroup;
    private WeightedQuickUnionUF virtualTop;


    // create N-by-N site, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new java.lang.IllegalArgumentException("Grid's length should be greater or equal to 0");
        }
        len = N;
        siteStatus = new boolean[N][N];
        openSiteNumber = 0;
        // Use the virtual bottom and top side to speed up our isFull and percolate method
        // 0 stands for top, N^2 + 1 stands for bottom
        siteGroup = new WeightedQuickUnionUF(N * N + 2);
        virtualTop = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            siteGroup.union(0, this.xyTo1D(0, i));
            siteGroup.union(N * N + 1, this.xyTo1D(N - 1, i));
            virtualTop.union(0, this.xyTo1D(0, i));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (indexOutBounds(row, col)) {
            throw new IndexOutOfBoundsException("Index out bound");
        }
        if (!this.siteStatus[row][col]) {
            this.siteStatus[row][col] = true;
            this.openSiteNumber++;
            LinkedList<Integer> nearByOpenState = findOpenGrid(row, col);
            for (int i : nearByOpenState) {
                this.siteGroup.union(xyTo1D(row, col), i);
                this.virtualTop.union(xyTo1D(row, col), i);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (this.indexOutBounds(row, col)) {
            throw new IndexOutOfBoundsException("Index out bound");
        }
        return this.siteStatus[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (this.indexOutBounds(row, col)) {
            throw new IndexOutOfBoundsException("Index out bound");
        }
        return this.virtualTop.connected(0, xyTo1D(row, col)) && this.siteStatus[row][col];
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.openSiteNumber;
    }

    // does the system percolate?
    public boolean percolates() {
        if (this.len != 1) {
            return this.siteGroup.connected(0, this.len * this.len + 1);
        } else {
            return this.isFull(0,0);
        }
    }


    // convert (x, y) to unique integer
    private int xyTo1D (int row, int col) {
        if (this.indexOutBounds(row, col)) {
            throw new IndexOutOfBoundsException("Index out bound");
        }
        return row * this.len + col + 1;
    }

    private boolean indexOutBounds (int row, int col) {
        return (row < 0 || row >= this.len) || (col < 0 || col >= this.len);
    }

    private LinkedList<Integer> findOpenGrid (int row, int col) {
        LinkedList<Integer> openGrid = new LinkedList<>();
        for (int i = -1; i <= 1; i += 2) {
            if (!this.indexOutBounds(row + i, col) && this.siteStatus[row + i][col]) {
                openGrid.addLast(this.xyTo1D(row + i, col));
            }
        }
        for (int i = -1; i <= 1; i += 2) {
            if (!this.indexOutBounds(row, col + i) && this.siteStatus[row][col + i]) {
                openGrid.addLast(this.xyTo1D(row, col + i));
            }
        }
        return openGrid;
    }

    private void printState() {
        for (int i = 0; i < this.len; i++) {
            for (int j = 0; j < this.len; j++) {
                System.out.print(this.siteStatus[i][j] + " ");
            }
            System.out.println();
        }
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 0);
        p.open(0, 1);
        p.open(1, 1);
        p.open(1, 2);
        p.open(2, 2);
        p.printState();
        System.out.println(p.isFull(2, 2));
        System.out.println(p.percolates());
    }
}