package ru.clevertec.patterns.task.cache.factory;

import ru.clevertec.patterns.task.cache.Cache;
import ru.clevertec.patterns.task.cache.impl.LFUCache;
import ru.clevertec.patterns.task.cache.impl.LRUCache;
import ru.clevertec.patterns.task.util.Constants;

public class CacheFactory {

    public Cache createCache(String typeOfCache) {
        Cache cache = null;
        if (typeOfCache.equals(Constants.CACHE_TYPE_LRU)) {
            cache = new LRUCache();
        }
        if (typeOfCache.equals(Constants.CACHE_TYPE_LFU)) {
            cache = new LFUCache();
        }
        return cache;
    }

}
