package com.framework.servicemanager;

/**
 * service缓存接口
 * 
 * @author mengchao
 * 
 */
public interface IServiceCache {

	/**
	 * 通过类型来找service
	 * 
	 * @param t
	 * @return
	 */
	public <T> T getService(Class<T> serviceClass);
	
	
	public Object getBeanById(String id);

}
