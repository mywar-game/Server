package com.framework.servicemanager;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private int dataSourceCount;

	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println("||||||||||||||||||");
		return CustomerContextHolder.getCustomerType();
	}

	public int getDataSourceCount() {
		return dataSourceCount;
	}

	public void setDataSourceCount(int dataSourceCount) {
		this.dataSourceCount = dataSourceCount;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
