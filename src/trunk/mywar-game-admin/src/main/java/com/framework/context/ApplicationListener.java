package com.framework.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.framework.plugin.AppPluginFactory;
	/**
	 * web服务器启动应用类
	 * 
	 * @author mengchao
	 * 
	 */
	public class ApplicationListener implements ServletContextListener {

		public void contextInitialized(ServletContextEvent sce) {
			// 加载应用，主要加载bean
			SpringLoad.getApplicationLoad();

			// 初始化webLoad，主要缓存ServletContext
			WebLaod.init(sce.getServletContext());
			
			
			// 启动各个应用插件
			AppPluginFactory.startup();
		}

		public void contextDestroyed(ServletContextEvent sce) {
			// 关闭各个应用插件
			AppPluginFactory.shutdown();
		}

}
