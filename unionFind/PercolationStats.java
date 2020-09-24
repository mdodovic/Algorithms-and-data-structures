package unionFind;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double STATISTIC_CONSTANT = 1.96;

    private double mean;
    private double standardDeviation;
    private double lowBound;
    private double highBound;

    /**
     * Initialize system with grid size and number of independent trials
     * Perform trials Monte Carlo simulations. In each simulation, 
     * sites are going to be opened until system percolates. After percolation, 
     * simulation stops and threshold is calculated.
     * @param n size of n-by-n grid
     * @param trials number of independent attempts
     * @throws IllegalArgumentException if n <= 0 or trials <= 0
     */    
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        monteCarlo(n, trials);
    }
    
    /**
     * Perform Monte Carlo simulation
     * @param n size of n-by-n grid
     * @param trials number of independent attempts
     */
    private void monteCarlo(int n, int trials) {
        double[] xT = new double[trials];
        int nSquare = n * n;
        
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            for (int j = 1; true; j++) {
                while (true) {
                    int row = 1 + StdRandom.uniform(n);
                    int col = 1 + StdRandom.uniform(n);
                    if (!p.isOpen(row, col)) {
                        p.open(row, col);
                        break;
                    }
                }
                if (p.percolates()) {
                    xT[i] = ((double) j) / nSquare;
                    break;
                }
            }
        }
        
        mean = StdStats.mean(xT);
        standardDeviation = (n > 1 ? StdStats.stddev(xT) : Double.NaN);
        
        lowBound = mean - STATISTIC_CONSTANT * standardDeviation / Math.sqrt(trials);
        highBound = mean + STATISTIC_CONSTANT * standardDeviation / Math.sqrt(trials);
        
    }

    /**
     * Return mean value of percolation threshold
     * @return mean value of percolation threshold
     */
    public double mean() {
        return mean;
    }
    
    /**
     * Return standard deviation of percolation threshold
     * @return standard deviation of percolation threshold
     */
    public double stddev() {
        return standardDeviation;
    }

    /**
     * Return low bound of 95% confidence interval
     * @return low bound of 95% confidence interval
     */    
    public double confidenceLo() {
        return lowBound;
    }

    /**
     * Return high bound of 95% confidence interval
     * @return high bound of 95% confidence interval
     */    
    public double confidenceHi() {
        return highBound;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, t);
        
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = "
                + "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
        
    }

}