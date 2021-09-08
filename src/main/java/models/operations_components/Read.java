package models.operations_components;

import cache_components.Cache;
import models.entities.Entity;

public interface Read {
    Entity readRecord(int tableIndex, String delimiter, Cache[] cachedRecords);
}
