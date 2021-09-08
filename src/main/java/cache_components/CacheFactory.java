package cache_components;

public class CacheFactory {
    private static CacheFactory cacheFactory;
    private CacheFactory(){}

    public static CacheFactory getCacheFactory(){
        if(cacheFactory == null)
            cacheFactory=new CacheFactory();
        return cacheFactory;
    }

    public Cache getCacheByType(String type, int size){
        if(type.equalsIgnoreCase("database"))
            return new DatabaseCache(size);
        return new NullCache();
    }
}
