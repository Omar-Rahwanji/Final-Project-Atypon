package cache_components;

import java.util.HashMap;
import java.util.Map;

public class DatabaseCache extends Cache {

    public DatabaseCache(int capacity) {
        this.capacity = capacity;  //
        cachedItems = new HashMap<>(this.capacity);
        size = 0;
    }

    @Override
    public void freeCache() { //Eviction Policy: LFU
        cachedItems.remove(getLFU());
    }

    public int getLFU() {
        int minHitCount = (int) Double.POSITIVE_INFINITY;
        int minHitCountKey= -1;
        for (Map.Entry<Integer,CacheItem> entry : cachedItems.entrySet()) {
            CacheItem cachedItem = entry.getValue();
            if (cachedItem.getHitCount() < minHitCount) {
                minHitCount = cachedItem.getHitCount();
                minHitCountKey = entry.getKey();
            }
        }
        return minHitCountKey;
    }
}
