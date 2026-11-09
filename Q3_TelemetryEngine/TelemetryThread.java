public class TelemetryThread extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {

            System.out.println(
                "Telemetry Update " + i +
                " | CPU Health: Normal" +
                " | Memory Health: Normal" +
                " | Current Thread: " +
                Thread.currentThread().getName()
            );

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}