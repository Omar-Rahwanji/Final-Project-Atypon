package models.operations_components;
import cache_components.Cache;

public interface Delete {
    boolean deleteRecord(int tableIndex, String delimiter, Cache[] cachedRecords);

}
