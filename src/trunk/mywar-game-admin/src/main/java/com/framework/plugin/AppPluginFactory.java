package com.framework.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.framework.log.LogSystem;

/**
 * 
 * 应用工厂类, 主要提供应用实例
 * 
 * @author mengchao
 * 
 */
public class AppPluginFactory implements BeanPostProcessor {

	// 插件列表
	private static List<IAppPlugin> appList = new ArrayList<IAppPlugin>();
	//系统插件
	private static List<SystemAppPluginBase> systemAppList = new ArrayList<SystemAppPluginBase>();

	public static List<IAppPlugin> getAppList() {
		return appList;
	}

	/**
	 * 启动插件，按顺序
	 */
	public static void startup() {
		//先启动系统插件
		Collections.sort(systemAppList, new SystemAppPluginBase.SystemAppPluginBaseCompara());
		if (systemAppList != null && systemAppList.size() > 0) {
			for (ISystemAppPlugin systemAppPlugin:systemAppList) {
				try {
				systemAppPlugin.startup();
				} catch (Exception e) {
					LogSystem.error(e, "插件启动失败！");
				}
			}
		}
		//启动一般应用插件
		List<IAppPlugin> appList = AppPluginFactory.getAppList();
		if (appList != null) {
			for (IAppPlugin application : appList) {
				try {
					application.startup();
				} catch (Exception e) {
					LogSystem.error(e, "插件启动失败！");
				}
			}
		}
	}

	/**
	 * 关闭插件，按倒序
	 */
	public static void shutdown() {
		//关闭系统插件
		if (systemAppList != null && systemAppList.size() > 0) {
			for (ISystemAppPlugin systemAppPlugin:systemAppList) {
				systemAppPlugin.shutdown();
			}
		}
		
		List<IAppPlugin> appList = AppPluginFactory.getAppList();
		if (appList != null) {
			int size = appList.size();
			for (int i = size - 1; i >= 0; i--) {
				appList.get(i).shutdown();
			}
		}
	}

	/**
	 * 是在bean加载每个bean之前，都会调用该办法。
	 */
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof IAppPlugin) {
			IAppPlugin appPlugin = (IAppPlugin) bean;
			AppPluginFactory.appList.add(appPlugin);
		}
		if (bean instanceof SystemAppPluginBase) {
			SystemAppPluginBase systemAppPlugin = (SystemAppPluginBase) bean;
			AppPluginFactory.systemAppList.add(systemAppPlugin);
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
