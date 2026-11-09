import java.util.concurrent.RecursiveTask;

public class ParallelRiskCalculator extends RecursiveTask<Double> {
    private double[] portfolio;
    private int start;
    private int end;
    private int threshold;

    public ParallelRiskCalculator(double[] portfolio, int start, int end, int threshold) {
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

        int mid = (start + end) / 2;

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
