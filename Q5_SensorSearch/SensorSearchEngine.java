public abstract class SensorSearchEngine {

    protected final double[] sensorData;
    protected final int startIdx;
    protected final int endIdx;

    public SensorSearchEngine(
            double[] sensorData, int startIdx, int endIdx) {

        if (sensorData == null) {
            throw new IllegalArgumentException("Sensor data cannot be null");
        }

        if (startIdx < 0 || endIdx > sensorData.length
                || startIdx >= endIdx) {
            throw new IllegalArgumentException("Invalid sensor search range");
        }

        this.sensorData = sensorData;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    public abstract double processGrid();
}