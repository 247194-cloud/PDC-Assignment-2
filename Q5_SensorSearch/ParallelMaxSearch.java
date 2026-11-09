import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMaxSearch extends SensorSearchEngine {
    public ParallelMaxSearch(double[][] sensorData) {
        super(sensorData);
    }

    @Override
    public double search() {
        int rows = sensorData.length;
        int cols = sensorData[0].length;

        ForkJoinPool pool = new ForkJoinPool(4);
        double result = pool.invoke(new MaxTask(0, rows * cols, cols));
        pool.shutdown();

        return result;
    }

    private class MaxTask extends RecursiveTask<Double> {
        private int start;
        private int end;
        private int cols;
        private final int threshold = 1000;

        MaxTask(int start, int end, int cols) {
            this.start = start;
            this.end = end;
            this.cols = cols;
        }

        @Override
        protected Double compute() {
            if (end - start <= threshold) {
                double max = -Double.MAX_VALUE;

                for (int i = start; i < end; i++) {
                    max = Math.max(max, sensorData[i / cols][i % cols]);
                }

                return max;
            }

            int mid = (start + end) / 2;
            MaxTask left = new MaxTask(start, mid, cols);
            MaxTask right = new MaxTask(mid, end, cols);

            left.fork();
            double rightMax = right.compute();
            double leftMax = left.join();

            return Math.max(leftMax, rightMax);
        }
    }
}

