package models.operations_components;

import cache_components.Cache;

public interface Update {
    boolean updateRecord(int tableIndex, String updateStatement, Cache[] cachedRecords);
}
