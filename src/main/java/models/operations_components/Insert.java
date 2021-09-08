package models.operations_components;
import cache_components.Cache;

public interface Insert {
    boolean insertRecord(int tableIndex, String newRecord, Cache[] cachedRecords);
}
