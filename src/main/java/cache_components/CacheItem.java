package cache_components;
import models.entities.Entity;

public abstract class CacheItem {
    protected Entity row;
    private int hitCount = 0;
    public int getHitCount() {
        return hitCount;
    }

    public void incrementHitCount() {
        this.hitCount++;
    }
}

