package com.framework.testcore;

import junit.framework.TestCase;

import com.framework.context.SpringLoad;
import com.framework.plugin.AppPluginFactory;
import com.framework.servicemanager.CustomerContextHolder;

/**
 * 该基类会启动插件
 * 
 * @author mengchao
 * 
 */
public class SessionTest extends TestCase {
	public SessionTest() {
		super();
		SpringLoad.getApplicationLoad();
		// 启动各个应用插件
		AppPluginFactory.startup();
		
		CustomerContextHolder.setSystemNum(10001);

	}
}
