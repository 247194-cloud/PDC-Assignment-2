import java.util.concurrent.RecursiveAction;

public class PortfolioThresholdTask extends RecursiveAction {
    private double[] portfolio;
    private int start;
    private int end;
    private int threshold;
    private double riskLimit;

    public PortfolioThresholdTask(double[] portfolio, int start, int end,
                                  int threshold, double riskLimit) {
        this.portfolio = portfolio;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
        this.riskLimit = riskLimit;
    }

    @Override
    protected void compute() {
        if (end - start <= threshold) {
            for (int i = start; i < end; i++) {
                if (portfolio[i] > riskLimit) {
                    portfolio[i] = portfolio[i] * 1.05;
                }
            }
            return;
        }

        int mid = (start + end) / 2;

        PortfolioThresholdTask leftTask =
                new PortfolioThresholdTask(portfolio, start, mid, threshold, riskLimit);

        PortfolioThresholdTask rightTask =
                new PortfolioThresholdTask(portfolio, mid, end, threshold, riskLimit);

        invokeAll(leftTask, rightTask);
    }
}

