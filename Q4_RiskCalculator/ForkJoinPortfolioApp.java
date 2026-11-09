
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPortfolioApp {

    public static void main(String[] args) {

        final int size = 10000;
        final int leafSize = 1000;
        final double riskLimit = 10000.0;

        double[] portfolio = new double[size];

        // Generate 10,000 portfolio asset values
        for (int i = 0; i < size; i++) {
            portfolio[i] = (i % 100) * 150.0;
        }

        ForkJoinPool pool = new ForkJoinPool();

        // Calculate total portfolio risk
        long riskStartTime = System.nanoTime();

        ParallelRiskCalculator calculator =
                new ParallelRiskCalculator(
                        portfolio, 0, portfolio.length, leafSize
                );

        double totalRisk = pool.invoke(calculator);

        long riskEndTime = System.nanoTime();

        // Apply 5% stress penalty to values above the risk limit
        long stressStartTime = System.nanoTime();

        PortfolioThresholdTask thresholdTask =
                new PortfolioThresholdTask(
                        portfolio, 0, portfolio.length,
                        leafSize, riskLimit
                );

        pool.invoke(thresholdTask);

        long stressEndTime = System.nanoTime();

        // Count values still above the risk limit
        int adjustedCount = 0;

        for (double risk : portfolio) {
            if (risk > riskLimit) {
                adjustedCount++;
            }
        }

        double riskTimeMs =
                (riskEndTime - riskStartTime) / 1_000_000.0;

        double stressTimeMs =
                (stressEndTime - stressStartTime) / 1_000_000.0;

        System.out.println("===== Fork/Join Portfolio Risk Calculator =====");
        System.out.println("Portfolio Size: " + portfolio.length);
        System.out.println("Task Chunk Size: " + leafSize);
        System.out.println("Risk Limit: " + riskLimit);
        System.out.println("Stress Penalty: 5%");
        System.out.println("Total Portfolio Risk Before Stress: " + totalRisk);
        System.out.println("Risk Calculation Time: " + riskTimeMs + " ms");
        System.out.println("Stress Adjustment Time: " + stressTimeMs + " ms");
        System.out.println("Total Processing Time: "
                + (riskTimeMs + stressTimeMs) + " ms");
        System.out.println("Values Above Risk Limit After Adjustment: "
                + adjustedCount);

        pool.shutdown();
    }
}
