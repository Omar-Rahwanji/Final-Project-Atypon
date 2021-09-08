package cache_components;


import models.entities.Entity;
import models.entities.NullEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Cache {
    protected HashMap<Integer, CacheItem> cachedItems;
    protected int size;
    protected int capacity;

    public void put(int key, CacheItem value) {
        if (!cachedItems.containsKey(key)) {
            if (size >= capacity)
                freeCache();
            cachedItems.put(key, value);
            size++;
        }
    }

    public Entity get(int key) {
        CacheItem cachedItem = cachedItems.get(key);
        if (cachedItem != null) {
            cachedItem.incrementHitCount();
            return cachedItem.row;
        }
        return NullEntity.getInstance();
    }

    public List<Integer> getAllKeys(){
        List<Integer> keys = new ArrayList<>();
        for (int key : cachedItems.keySet() ) {
            keys.add(key);
        }
        return keys;
    }

    public abstract void freeCache();

    public void deleteByKey(int key) {
        cachedItems.remove(key);
    }

    public void wipe() {
        cachedItems.clear();
    }

}
