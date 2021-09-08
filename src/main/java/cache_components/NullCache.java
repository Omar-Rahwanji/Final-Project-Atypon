package cache_components;

import models.entities.Entity;

import java.util.List;

public class NullCache extends Cache {
    @Override
    public List<Integer> getAllKeys() {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }

    @Override
    public void freeCache() {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(int key, CacheItem value) {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }

    @Override
    public Entity get(int key) {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteByKey(int key) {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }

    @Override
    public void wipe() {
        //This method is unsupported
        throw new UnsupportedOperationException();
    }
}
