import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMinSearch extends SensorSearchEngine {
    public ParallelMinSearch(double[][] sensorData) {
        super(sensorData);
    }

    @Override
    public double search() {
        ForkJoinPool pool = new ForkJoinPool(4);
        double result = pool.invoke(new MinTask(0, sensorData.length * sensorData[0].length));
        pool.shutdown();
        return result;
    }

    private class MinTask extends RecursiveTask<Double> {
        private int start;
        private int end;
        private final int threshold = 1000;
        private int cols = sensorData[0].length;

        MinTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Double compute() {
            if (end - start <= threshold) {
                double min = Double.MAX_VALUE;

                for (int i = start; i < end; i++) {
                    min = Math.min(min, sensorData[i / cols][i % cols]);
                }

                return min;
            }

            int mid = (start + end) / 2;
            MinTask left = new MinTask(start, mid);
            MinTask right = new MinTask(mid, end);

            left.fork();
            double rightMin = right.compute();
            double leftMin = left.join();

            return Math.min(leftMin, rightMin);
        }
    }
}
