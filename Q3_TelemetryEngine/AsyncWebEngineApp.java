public class AsyncWebEngineApp {
    public static void main(String[] args) {
        LogProcessorTask logTask = new LogProcessorTask("Log Processor");
        TelemetryThread telemetryThread = new TelemetryThread("Telemetry Thread");

        Thread logThread = new Thread(logTask);

        logThread.start();
        telemetryThread.start();

        try {
            logThread.join();
            telemetryThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All telemetry processing completed.");
    }
}

