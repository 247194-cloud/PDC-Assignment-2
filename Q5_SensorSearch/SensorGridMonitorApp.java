
public class SensorGridMonitorApp {

    public static void main(String[] args) {

        final int totalReadings = 50000;
        final int workerCount = 4;

        double[] sensorData = new double[totalReadings];

        // Generate 50,000 sensor readings
        for (int i = 0; i < totalReadings; i++) {
            sensorData[i] = i % 10000;
        }

        ParallelMinSearch[] minWorkers =
                new ParallelMinSearch[workerCount];

        ParallelMaxSearch[] maxWorkers =
                new ParallelMaxSearch[workerCount];

        Thread[] minThreads = new Thread[workerCount];
        Thread[] maxThreads = new Thread[workerCount];

        double[] minResults = new double[workerCount];
        double[] maxResults = new double[workerCount];

        int chunkSize = totalReadings / workerCount;

        long startTime = System.nanoTime();

        // Divide readings among four minimum-search workers
        for (int i = 0; i < workerCount; i++) {

            int start = i * chunkSize;
            int end = (i == workerCount - 1)
                    ? totalReadings
                    : start + chunkSize;

            minWorkers[i] =
                    new ParallelMinSearch(sensorData, start, end);

            final int workerIndex = i;

            minThreads[i] = new Thread(() -> {
                minResults[workerIndex] =
                        minWorkers[workerIndex].processGrid();
            }, "Min-Worker-" + i);

            minThreads[i].start();
        }

        // Wait for minimum-search workers
        joinWorkers(minThreads);

        // Divide readings among four maximum-search workers
        for (int i = 0; i < workerCount; i++) {

            int start = i * chunkSize;
            int end = (i == workerCount - 1)
                    ? totalReadings
                    : start + chunkSize;

            maxWorkers[i] =
                    new ParallelMaxSearch(sensorData, start, end);

            final int workerIndex = i;

            maxThreads[i] = new Thread(() -> {
                maxResults[workerIndex] =
                        maxWorkers[workerIndex].processGrid();
            }, "Max-Worker-" + i);

            maxThreads[i].start();
        }

        // Wait for maximum-search workers
        joinWorkers(maxThreads);

        // Combine local results into global minimum and maximum
        double globalMin = Double.MAX_VALUE;
        double globalMax = -Double.MAX_VALUE;

        for (int i = 0; i < workerCount; i++) {
            globalMin = Math.min(globalMin, minResults[i]);
            globalMax = Math.max(globalMax, maxResults[i]);
        }

        long endTime = System.nanoTime();

        double executionTime =
                (endTime - startTime) / 1_000_000.0;

        System.out.println("===== Parallel Sensor Grid Monitor =====");
        System.out.println("Total Sensor Readings: " + totalReadings);
        System.out.println("Worker Threads: " + workerCount);
        System.out.println("Global Minimum Reading: " + globalMin);
        System.out.println("Global Maximum Reading: " + globalMax);
        System.out.println("Execution Time: " + executionTime + " ms");
        System.out.println("Sensor monitoring completed.");
    }

    private static void joinWorkers(Thread[] workers) {

        for (Thread worker : workers) {
            try {
                worker.join();

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                throw new IllegalStateException(
                        "Sensor search was interrupted.", e
                );
            }
        }
    }
}
