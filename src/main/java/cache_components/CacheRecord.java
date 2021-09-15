package cache_components;

import models.entities.Entity;

public class CacheRecord extends CacheItem {
    public CacheRecord(Entity row) {
        this.row=row;
    }
}