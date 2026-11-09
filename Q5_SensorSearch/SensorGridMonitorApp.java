public class SensorGridMonitorApp {
    public static void main(String[] args) {
        int rows = 250;
        int cols = 200;
        double[][] sensorData = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sensorData[i][j] = (i * cols + j) % 10000;
            }
        }

        ParallelMinSearch minSearch = new ParallelMinSearch(sensorData);
        ParallelMaxSearch maxSearch = new ParallelMaxSearch(sensorData);

        System.out.println("Total Sensor Readings: " + (rows * cols));
        System.out.println("Worker Threads: 4");
        System.out.println("Minimum Sensor Reading: " + minSearch.search());
        System.out.println("Maximum Sensor Reading: " + maxSearch.search());
        System.out.println("Sensor monitoring completed.");
    }
}

