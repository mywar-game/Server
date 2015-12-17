package com.system.plugin;


import com.framework.plugin.SystemAppPluginBase;
import com.system.manager.DataSourceManager;


public class DataSourceRefreshPlugin extends SystemAppPluginBase {

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		DataSourceManager manager = DataSourceManager.getInstatnce();
		manager.initDataSource();//刷新数据源
	}
   
	
}
