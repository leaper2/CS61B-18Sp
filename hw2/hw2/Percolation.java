package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] sites;
    private int N;
    private WeightedQuickUnionUF connection;
    private int virtualTop;
    // private int virtualBottom;
    /*
     * 1. If we use the virtualBottom, the Backwash issue is unavoidable
     * 2. However, if we don't use the virtualBottom node, we will have
     * to iterate the bottom row to check the percolation state, apparently. That is
     * unacceptable.
     * 3. In order to eliminate the need to iterate over the bottom
     * row at the last place, we have to refine the underlying data structure to
     * store the bottom-connecting state of every component.
     * Break the whole into parts.
     */
    private int numOfopen;
    private boolean percolated;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Error, N should large than 0");
        }
        sites = new int[N][N];
        // 0 denote blocked,1 denote open site
        this.N = N;
        connection = new WeightedQuickUnionUF(N * N + 2);
        virtualTop = N * N;
        // virtualBottom = N * N - 1;
        // plus 2 virtual node
        // connect the two virtual nodes with the corresponding top row and bottom row
        // for (int i = 0; i < N; i++) {
        // // index N*N as the virtual node assotiating with the top row
        // connection.union(i, virtualTop);
        // connection.union((N - 1) * N + i, virtualBottom);
        // }
    }

    // translate the element indices of the matrix to its corresponding disjoin set
    // index
    private int map(int row, int col) {
        return row * N + col;
    }

    private void testBounds(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

    }

    private void tryConnect(int row1, int col1, int row, int col) {
        try {
            if (isOpen(row1, col1)) {
                connection.union(map(row1, col1), map(row, col));
            }
        } catch (IndexOutOfBoundsException e) {
            ;
        }
    }

    public void open(int row, int col) {
        testBounds(row, col);
        if (sites[row][col] == 0) {
            sites[row][col] = 1;
            numOfopen++;
            if (row == 0) {
                connection.union(map(row, col), virtualTop);
            }
            // if (row == N - 1) {
            // connection.union(map(row, col), virtualBottom);
            // }
            // open a site and connect with its neighbours
            tryConnect(row, col - 1, row, col);
            tryConnect(row, col + 1, row, col);
            tryConnect(row - 1, col, row, col);
            tryConnect(row + 1, col, row, col);

        }
    }

    public boolean isOpen(int row, int col) {
        testBounds(row, col);
        return sites[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        testBounds(row, col);
        return connection.connected(map(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        return numOfopen;
    }

    public boolean percolates() {
        return connection.connected(virtualBottom, virtualTop);
    }

}
