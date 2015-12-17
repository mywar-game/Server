package com.framework.servicemanager;

public class DataSourceUtil {
	private static final int DataSourceCount = ServiceCacheFactory.getServiceCache().getService(DynamicDataSource.class).getDataSourceCount();

	public static int getDataSourceCount() {
		return DataSourceCount;
	}
}
