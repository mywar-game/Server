package com.framework.cache.memcache;

import net.rubyeye.xmemcached.CASOperation;

public class CASOperImpl<T> implements CASOperation<T> {

	private T obj;
	
	public CASOperImpl(T obj) {
		this.obj = obj;
	}

	public int getMaxTries() {
		return Integer.MAX_VALUE;
	}

	public T getNewValue(long cas, T newValue) {
		return obj;
	}

}
