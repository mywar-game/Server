package com.example;

import com.example.cache.ExampleLocalCache;
import com.framework.cache.CacheManager;
import com.framework.testcore.DBTest;

public class CachedTest extends DBTest {
	public void testOSCache() {
		ExampleLocalCache localCache = CacheManager.getCacheInstance(ExampleLocalCache.class);
		localCache.put("test", "123456");
		System.out.println(localCache.get("test"));
	}

}
