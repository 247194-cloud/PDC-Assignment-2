public class AsyncWebEngineApp {

    public static void main(String[] args) {

        // Display the default main thread
        System.out.println(
            "Main Thread: " +
            Thread.currentThread().getName()
        );

        // Create telemetry worker
        TelemetryThread telemetryThread =
                new TelemetryThread();

        telemetryThread.setName("Telemetry-Worker");

        // Create log processing worker
        LogProcessorTask logTask =
                new LogProcessorTask();

        Thread logThread = new Thread(logTask);

        logThread.setName("Log-Worker");

        // Start both worker threads
        telemetryThread.start();
        logThread.start();

        // Display execution states
        System.out.println(
            "Telemetry Thread State: " +
            telemetryThread.getState()
        );

        System.out.println(
            "Log Thread State: " +
            logThread.getState()
        );

        // Wait for both threads to finish
        try {
            telemetryThread.join();
            logThread.join();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                "Main thread was interrupted."
            );

            return;
        }

        System.out.println(
            "Telemetry Thread Final State: " +
            telemetryThread.getState()
        );

        System.out.println(
            "Log Thread Final State: " +
            logThread.getState()
        );

        System.out.println(
            "All telemetry processing completed."
        );
    }
}