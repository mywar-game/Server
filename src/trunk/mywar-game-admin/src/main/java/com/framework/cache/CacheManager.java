package com.framework.cache;

import com.framework.servicemanager.ServiceCacheFactory;

public class CacheManager {
   public static <T> T getCacheInstance(Class<T> cacheClass) {
	   return ServiceCacheFactory.getServiceCache().getService(cacheClass);
   }
}
