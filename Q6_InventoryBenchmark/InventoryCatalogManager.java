
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryCatalogManager {

    private final Map<String, Integer> synchronizedCatalog =
            Collections.synchronizedMap(new HashMap<>());

    private final Map<String, Integer> concurrentCatalog =
            new ConcurrentHashMap<>();

    public InventoryCatalogManager(int itemCount) {

        for (int i = 0; i < itemCount; i++) {

            String productId = "PRODUCT-" + i;

            synchronizedCatalog.put(productId, 100);
            concurrentCatalog.put(productId, 100);
        }
    }

    public void updateSynchronized(String productId) {

        synchronizedCatalog.compute(
                productId,
                (key, value) -> value == null ? 1 : value + 1
        );
    }

    public void updateConcurrent(String productId) {

        concurrentCatalog.compute(
                productId,
                (key, value) -> value == null ? 1 : value + 1
        );
    }

    public int lookupSynchronized(String productId) {

        return synchronizedCatalog.getOrDefault(productId, 0);
    }

    public int lookupConcurrent(String productId) {

        return concurrentCatalog.getOrDefault(productId, 0);
    }
}
