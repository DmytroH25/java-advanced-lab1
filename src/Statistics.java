/**
 * A class to hold statistical data for a set of car prices.
 */
public class Statistics {
    private final int minPrice;
    private final int maxPrice;
    private final double averagePrice;
    private final double standardDeviation;

    /**
     * Constructs a Statistics object with provided statistical values.
     *
     * @param minPrice           the minimum car price
     * @param maxPrice           the maximum car price
     * @param averagePrice       the average car price
     * @param standardDeviation  the standard deviation of car prices
     */
    public Statistics(int minPrice, int maxPrice, double averagePrice, double standardDeviation) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.averagePrice = averagePrice;
        this.standardDeviation = standardDeviation;
    }

    @Override
    public String toString() {
        return String.format("Min Price: %d, Max Price: %d, Avg Price: %.2f, StdDev: %.2f",
                minPrice, maxPrice, averagePrice, standardDeviation);
    }
}
