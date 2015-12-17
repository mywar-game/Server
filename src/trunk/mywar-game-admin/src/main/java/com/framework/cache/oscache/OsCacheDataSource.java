package com.framework.cache.oscache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存数据源
 * @author mengc
 *
 */
public class OsCacheDataSource {
    private static OsCacheDataSource cacheManager;
    private static Map<String,Object> osCache = new ConcurrentHashMap<String, Object>();
    
    private OsCacheDataSource() {
    	
    }
    public static OsCacheDataSource getInstanceCacheManager() {
    	if (cacheManager == null) {
    		cacheManager = new OsCacheDataSource();
    	}
    	return cacheManager;
    }
    
    public Object getValue(String key) {
    	return osCache.get(key);
    }
    
    public void putValue(String key, Object value) {
    	osCache.put(key, value);
    }
    
    public boolean isExistKey(String key) {
    	return this.getValue(key) == null ? false : true;
    }
}
