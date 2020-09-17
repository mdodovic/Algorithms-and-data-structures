package unionFind;
import edu.princeton.cs.algs4.In;
import unionFind.WeightedQuickUnionUF;
public class Percolation {

    private final int n;
    private final int virtualBegin;
    private final int virtualEnd;
    private int numberOfOpenSites;
    private boolean[][] grid;
    private final WeightedQuickUnionUF unionFinderTwoVirtual;
    private final WeightedQuickUnionUF unionFinderOneVirtual;

    /**
     * Creates n-by-n grid, with all sites initially blocked
     * @param n the size of grid
     * @throws IllegalArgumentException - if n <= 0
     */
    public Percolation(int n) {
        if (n <= 0) 
            throw new IllegalArgumentException();
        this.n = n;
        virtualBegin = 0;
        virtualEnd = n * n + 1;
        grid = new boolean[n][n];
        unionFinderTwoVirtual = new WeightedQuickUnionUF(n*n + 2);
        unionFinderOneVirtual = new WeightedQuickUnionUF(n*n + 1);
    }

    /**
     * Map row and column coordinates to single unique coordinate
     * @param row between 1 and size of grid
     * @param col between 1 and size of grid
     * @return (row - 1) * 6 + col, number between 1 and square of size of grid 
     */
    private int map2Dto1D(int row, int col) {
        return (row - 1) * n + col;
    }

    /**
     * Opens the site (row, col) if it is not open already
     * @param row - row of the site
     * @param col - column of the site
     * @throws IllegalArgumentException - if both row and col aren't between 1 and n (the size of the grid)
     */
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();
        if (grid[row - 1][col - 1])
            return;
        grid[row - 1][col - 1] = true;
        numberOfOpenSites++;

        if (n == 1) {
            unionFinderTwoVirtual.union(virtualBegin, map2Dto1D(row, col));
            unionFinderTwoVirtual.union(virtualEnd, map2Dto1D(row, col));
            unionFinderOneVirtual.union(virtualBegin, map2Dto1D(row, col));
            return;
        }

        if (row == 1) {
            unionFinderOneVirtual.union(virtualBegin, map2Dto1D(row, col));
            unionFinderTwoVirtual.union(virtualBegin, map2Dto1D(row, col));
            if (grid[row - 1 + 1][col - 1]) {
                unionFinderTwoVirtual.union(map2Dto1D(row + 1, col), map2Dto1D(row, col));
                unionFinderOneVirtual.union(map2Dto1D(row + 1, col), map2Dto1D(row, col));
            }
        }
        if (row == n) {
            unionFinderTwoVirtual.union(virtualEnd, map2Dto1D(row, col));
            if (grid[row - 1 - 1][col - 1]) {
                unionFinderTwoVirtual.union(map2Dto1D(row - 1, col), map2Dto1D(row, col));
                unionFinderOneVirtual.union(map2Dto1D(row - 1, col), map2Dto1D(row, col));
            }
        }

        if (row > 1) {
            if (grid[row - 1 - 1][col - 1]) {
                unionFinderTwoVirtual.union(map2Dto1D(row - 1, col), map2Dto1D(row, col));
                unionFinderOneVirtual.union(map2Dto1D(row - 1, col), map2Dto1D(row, col));
            }
        }
        if (row < n) {
            if (grid[row - 1 + 1][col - 1]) {
                unionFinderTwoVirtual.union(map2Dto1D(row + 1, col), map2Dto1D(row, col));
                unionFinderOneVirtual.union(map2Dto1D(row + 1, col), map2Dto1D(row, col));
            }
        }
        if (col > 1) {
            if (grid[row - 1][col - 1 - 1]) {
                unionFinderTwoVirtual.union(map2Dto1D(row, col - 1), map2Dto1D(row, col));
                unionFinderOneVirtual.union(map2Dto1D(row, col - 1), map2Dto1D(row, col));
            }
        }
        if (col < n) {
            if (grid[row - 1][col + 1 - 1]) {
                unionFinderTwoVirtual.union(map2Dto1D(row, col + 1), map2Dto1D(row, col));
                unionFinderOneVirtual.union(map2Dto1D(row, col + 1), map2Dto1D(row, col));
            }
        }

    }

    /**
     * Check if the site (row, col) open?
     * @param row - row of the site
     * @param col - column of the site
     * @throws IllegalArgumentException
     * if both row and col aren't between 1 and n (the size of the grid)
     */
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();
        return grid[row - 1][col - 1];
    }

    /**
     * Check if the site (row, col) full? 
     * A full site is an open site that can be 
     * connected to an open site in the top row 
     * via a chain of neighboring (left, right, up, down) open sites.
     * @param row - row of the site
     * @param col - column of the site
     * @throws IllegalArgumentException if both row and col aren't between 1 and n (the size of the grid)
     */
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();
        return unionFinderOneVirtual.find(0) == unionFinderOneVirtual.find(map2Dto1D(row, col));
    }

    /**
     * Return the number of open sites  
     * @return the number of open sites
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Check if the system percolate? 
     * We say the system percolates if there is a full site in the bottom row.
     */
    public boolean percolates() {
        return unionFinderTwoVirtual.find(0) == unionFinderTwoVirtual.find(n*n + 1);
    }

    public static void main(String[] args) { 
        In in = new In(args[0]); // input file
        int n = in.readInt(); // n-by-n percolation system
        Percolation perc = new Percolation(n);
        
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }

    }

}
