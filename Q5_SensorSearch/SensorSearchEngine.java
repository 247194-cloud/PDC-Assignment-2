public abstract class SensorSearchEngine {
    protected double[][] sensorData;

    public SensorSearchEngine(double[][] sensorData) {
        this.sensorData = sensorData;
    }

    public abstract double search();
}
