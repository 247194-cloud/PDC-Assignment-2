import java.util.concurrent.ForkJoinPool;

public class RiskPortfolioApp {
    public static void main(String[] args) {
        int size = 5000;
        double[] portfolio = new double[size];

        for (int i = 0; i < size; i++) {
            portfolio[i] = (i % 100) * 150.0;
        }

        ForkJoinPool pool = new ForkJoinPool();

        ParallelRiskCalculator calculator =
                new ParallelRiskCalculator(portfolio, 0, portfolio.length, 1000);

        double totalRisk = pool.invoke(calculator);

        System.out.println("Total Portfolio Risk: " + totalRisk);

        PortfolioThresholdTask thresholdTask =
                new PortfolioThresholdTask(portfolio, 0, portfolio.length, 1000, 10000);

        pool.invoke(thresholdTask);

        int adjustedCount = 0;

        for (double risk : portfolio) {
            if (risk > 10000) {
                adjustedCount++;
            }
        }

        System.out.println("Portfolio Size: " + portfolio.length);
        System.out.println("Risk Threshold: 10000");
        System.out.println("Penalty Applied: 5%");
        System.out.println("Values Above Threshold After Adjustment: " + adjustedCount);

        pool.shutdown();
    }
}
