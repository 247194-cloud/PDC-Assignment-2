import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryCatalogManager {
    private final Map<Integer, Integer> synchronizedCatalog =
            Collections.synchronizedMap(new HashMap<>());

    private final Map<Integer, Integer> concurrentCatalog =
            new ConcurrentHashMap<>();

    public InventoryCatalogManager(int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            synchronizedCatalog.put(i, 100);
            concurrentCatalog.put(i, 100);
        }
    }

    public void updateSynchronized(int itemId) {
        synchronizedCatalog.compute(itemId, (key, value) -> value + 1);
    }

    public void updateConcurrent(int itemId) {
        concurrentCatalog.compute(itemId, (key, value) -> value + 1);
    }

    public int lookupSynchronized(int itemId) {
        return synchronizedCatalog.getOrDefault(itemId, 0);
    }

    public int lookupConcurrent(int itemId) {
        return concurrentCatalog.getOrDefault(itemId, 0);
    }
}
