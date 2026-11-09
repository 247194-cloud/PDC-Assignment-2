import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InventoryBenchmarkApp {
    private static final int ITEM_COUNT = 1000;
    private static final int THREAD_COUNT = 10;
    private static final int TOTAL_OPERATIONS = 50000;

    public static void main(String[] args) throws Exception {
        InventoryCatalogManager manager =
                new InventoryCatalogManager(ITEM_COUNT);

        long synchronizedTime = benchmark(manager, true);
        long concurrentTime = benchmark(manager, false);

        double synchronizedThroughput =
                TOTAL_OPERATIONS / (synchronizedTime / 1_000_000_000.0);

        double concurrentThroughput =
                TOTAL_OPERATIONS / (concurrentTime / 1_000_000_000.0);

        System.out.println("Inventory Benchmark Results");
        System.out.println("---------------------------");
        System.out.println("Worker Threads: " + THREAD_COUNT);
        System.out.println("Operations per Catalog: " + TOTAL_OPERATIONS);
        System.out.println("SynchronizedMap Time: " + synchronizedTime / 1_000_000.0 + " ms");
        System.out.println("ConcurrentHashMap Time: " + concurrentTime / 1_000_000.0 + " ms");
        System.out.printf("SynchronizedMap Throughput: %.2f operations/second%n",
                synchronizedThroughput);
        System.out.printf("ConcurrentHashMap Throughput: %.2f operations/second%n",
                concurrentThroughput);

        if (concurrentTime < synchronizedTime) {
            System.out.println("Faster Result: ConcurrentHashMap");
        } else if (synchronizedTime < concurrentTime) {
            System.out.println("Faster Result: SynchronizedMap");
        } else {
            System.out.println("Faster Result: Both took equal time");
        }
    }

    private static long benchmark(
            InventoryCatalogManager manager, boolean useSynchronizedMap)
            throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<?>> tasks = new ArrayList<>();

        int operationsPerThread = TOTAL_OPERATIONS / THREAD_COUNT;

        long startTime = System.nanoTime();

        for (int t = 0; t < THREAD_COUNT; t++) {
            final int workerId = t;

            tasks.add(executor.submit(() -> {
                for (int i = 0; i < operationsPerThread; i++) {
                    int itemId = (i + workerId) % ITEM_COUNT;

                    if (i % 2 == 0) {
                        if (useSynchronizedMap) {
                            manager.lookupSynchronized(itemId);
                        } else {
                            manager.lookupConcurrent(itemId);
                        }
                    } else {
                        if (useSynchronizedMap) {
                            manager.updateSynchronized(itemId);
                        } else {
                            manager.updateConcurrent(itemId);
                        }
                    }
                }
            }));
        }

        for (Future<?> task : tasks) {
            task.get();
        }

        long elapsed = System.nanoTime() - startTime;
        executor.shutdown();

        return elapsed;
    }
}
