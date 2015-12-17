package com.framework.cache.memcache;

import java.io.IOException;

import net.rubyeye.xmemcached.XMemcachedClientBuilder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.framework.log.LogSystem;


/**
 * 
 * memcache客户端注入
 * 
 * @author mengc
 * 
 */
public class MecacheClientAutoInsert implements BeanPostProcessor {

	private boolean flushCache;
	private XMemcachedClientBuilder xmemcachedClientBuilder;


	public XMemcachedClientBuilder getXmemcachedClientBuilder() {
		return xmemcachedClientBuilder;
	}

	public void setXmemcachedClientBuilder(
			XMemcachedClientBuilder xmemcachedClientBuilder) {
		this.xmemcachedClientBuilder = xmemcachedClientBuilder;
	}


	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {

		return bean;
	}
	@SuppressWarnings("unchecked")
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
        if (bean instanceof MemcacheBase) {
        	try {
				((MemcacheBase) bean).setMemcachedClient(xmemcachedClientBuilder.build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LogSystem.error(e, "");
			}
        }
		return bean;
	}
	public boolean isFlushCache() {
		return flushCache;
	}

	public void setFlushCache(boolean flushCache) {
		this.flushCache = flushCache;
	}
	
}
