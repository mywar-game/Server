package com.framework.servicemanager;

/**
 * service缓存工厂�?,该工厂不是spring注入，是在ApplicationLao中注入�??
 * 
 * @author mengchao
 * 
 */
public class ServiceCacheFactory {

	private static IServiceCache serviceCache;

	public ServiceCacheFactory(IServiceCache serviceCache) {
		if (ServiceCacheFactory.serviceCache == null) {
			ServiceCacheFactory.serviceCache = serviceCache;
		}
	}

	public static IServiceCache getServiceCache() {
		return serviceCache;
	}
	

}
