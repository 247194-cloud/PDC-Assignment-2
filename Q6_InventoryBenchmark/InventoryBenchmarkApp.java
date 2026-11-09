
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InventoryBenchmarkApp {

    private static final int ITEM_COUNT = 1000;
    private static final int THREAD_COUNT = 10;
    private static final int OPERATIONS_PER_THREAD = 50000;

    private static final int TOTAL_OPERATIONS =
            THREAD_COUNT * OPERATIONS_PER_THREAD;

    public static void main(String[] args) throws Exception {

        InventoryCatalogManager manager =
                new InventoryCatalogManager(ITEM_COUNT);

        long synchronizedTime = benchmark(manager, true);
        long concurrentTime = benchmark(manager, false);

        double synchronizedThroughput =
                TOTAL_OPERATIONS /
                (synchronizedTime / 1_000_000_000.0);

        double concurrentThroughput =
                TOTAL_OPERATIONS /
                (concurrentTime / 1_000_000_000.0);

        System.out.println("Inventory Concurrency Benchmark");
        System.out.println("--------------------------------");
        System.out.println("Products in Catalog: " + ITEM_COUNT);
        System.out.println("Worker Threads: " + THREAD_COUNT);
        System.out.println("Operations per Worker: " +
                OPERATIONS_PER_THREAD);
        System.out.println("Total Operations per Catalog: " +
                TOTAL_OPERATIONS);

        System.out.println();

        System.out.printf("SynchronizedMap Time: %.2f ms%n",
                synchronizedTime / 1_000_000.0);

        System.out.printf("ConcurrentHashMap Time: %.2f ms%n",
                concurrentTime / 1_000_000.0);

        System.out.printf("SynchronizedMap Throughput: %.2f operations/second%n",
                synchronizedThroughput);

        System.out.printf("ConcurrentHashMap Throughput: %.2f operations/second%n",
                concurrentThroughput);

        System.out.println();

        if (concurrentTime < synchronizedTime) {
            System.out.println("Faster Result: ConcurrentHashMap");
        } else if (synchronizedTime < concurrentTime) {
            System.out.println("Faster Result: SynchronizedMap");
        } else {
            System.out.println("Faster Result: Both took equal time");
        }

        System.out.println("Inventory benchmark completed.");
    }

    private static long benchmark(
            InventoryCatalogManager manager,
            boolean useSynchronizedMap) throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(THREAD_COUNT);

        List<Future<?>> tasks = new ArrayList<>();

        long startTime = System.nanoTime();

        for (int t = 0; t < THREAD_COUNT; t++) {

            final int workerId = t;

            tasks.add(executor.submit(() -> {

                for (int i = 0;
                     i < OPERATIONS_PER_THREAD;
                     i++) {

                    String productId =
                            "PRODUCT-" + ((i + workerId) % ITEM_COUNT);

                    if (i % 2 == 0) {

                        if (useSynchronizedMap) {
                            manager.lookupSynchronized(productId);
                        } else {
                            manager.lookupConcurrent(productId);
                        }

                    } else {

                        if (useSynchronizedMap) {
                            manager.updateSynchronized(productId);
                        } else {
                            manager.updateConcurrent(productId);
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
