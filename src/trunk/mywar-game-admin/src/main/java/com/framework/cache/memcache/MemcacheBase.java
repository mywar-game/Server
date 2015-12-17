package com.framework.cache.memcache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import com.framework.cache.ICache;
import com.framework.log.LogSystem;

@SuppressWarnings("hiding")
public class MemcacheBase<T> implements ICache<T> {

	private MemcachedClient memcachedClient;
	private String cacheName;
	
	
	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public MemcacheBase() {
		cacheName = this.getClass().getSimpleName();
	}
	
	public void delete(String key) {
		// TODO Auto-generated method stub
		try {
			memcachedClient.deleteWithNoReply(cacheName + key);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		}
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	public boolean deleteWithReplay(String key) {
		// TODO Auto-generated method stub
		try {
			return memcachedClient.delete(cacheName + key.toString());
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		}
		return false;
	}

	public T get(String key) {
		// TODO Auto-generated method stub
		try {
			 T t = memcachedClient.get(cacheName + key.toString());
			 return t;
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			LogSystem.error(e, "");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> gets(Collection<String> keys) {
		// TODO Auto-generated method stub
			Collection<T> list = new ArrayList<T>();
			if (keys == null || keys.size() == 0) {
				return list;
			}
			for (String str:keys) {
				list.add(get(str));
			}
			return list;
	}

	public boolean isExistKey(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isExistValue(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	public void put(String key, T value) {
		// TODO Auto-generated method stub
		try {
			memcachedClient.set(cacheName + key, 0, value);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		 MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil
				.getAddresses("222.175.114.75:11211"));
		MemcachedClient memcachedClient = null;
		try {
			memcachedClient = builder.build();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(memcachedClient.get("1"));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			memcachedClient.set("hello", 0, "Hello,xmemcached");
//			String value = memcachedClient.get("hello");
//			System.out.println("hello = " + value);
//			memcachedClient.delete("hello");
//			value = memcachedClient.get("hello");
//			System.out.println("hello = " + value);
//		} catch (MemcachedException e) {
//			System.err.println("MemcachedClient operation fail");
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			System.err.println("MemcachedClient operation timeout");
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// ignore
//		}
//		try {
//			// close memcached client
//			memcachedClient.shutdown();
//		} catch (IOException e) {
//			System.err.println("Shutdown MemcachedClient fail");
//			e.printStackTrace();
//		}
	}
}
