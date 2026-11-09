import java.util.concurrent.RecursiveTask;

public class ParallelRiskCalculator extends RecursiveTask<Double> {

    private final double[] portfolio;
    private final int start;
    private final int end;
    private final int threshold;

    public ParallelRiskCalculator(
            double[] portfolio, int start, int end, int threshold) {

        if (portfolio == null) {
            throw new IllegalArgumentException("Portfolio cannot be null");
        }

        if (start < 0 || end > portfolio.length || start > end) {
            throw new IllegalArgumentException("Invalid portfolio range");
        }

        if (threshold <= 0) {
            throw new IllegalArgumentException("Threshold must be positive");
        }

        this.portfolio = portfolio;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Double compute() {

        if (end - start <= threshold) {
            double totalRisk = 0;

            for (int i = start; i < end; i++) {
                totalRisk += portfolio[i];
            }

            return totalRisk;
        }

        int mid = start + (end - start) / 2;

        ParallelRiskCalculator leftTask =
                new ParallelRiskCalculator(portfolio, start, mid, threshold);

        ParallelRiskCalculator rightTask =
                new ParallelRiskCalculator(portfolio, mid, end, threshold);

        leftTask.fork();

        double rightResult = rightTask.compute();
        double leftResult = leftTask.join();

        return leftResult + rightResult;
    }
}