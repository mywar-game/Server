package com.framework.cache.oscache;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.framework.cache.ICache;

public class OsCacheBase<T> implements ICache<T> {
	/**
	 * 缓存基类
	 */
	private static final long serialVersionUID = 3516212969511576003L;

	
	private String cacheName;
	
	private OsCacheDataSource osCacheManager;
	
	@SuppressWarnings("unchecked")
	public OsCacheBase() {
		cacheName = this.getClass().getSimpleName();
		osCacheManager = OsCacheDataSource.getInstanceCacheManager();
		if (!osCacheManager.isExistKey(cacheName)) {
			osCacheManager.putValue(cacheName, new HashMap<String, T>());
		} else {
			throw new RuntimeException("已存在缓存名称" + cacheName + "请更改cacheName！");
		}

		try {

			@SuppressWarnings("unused")
			Class<T> entityClassT = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			String clsName = getClass().getSimpleName();
			throw new RuntimeException(getClass().getCanonicalName()
					+ "未定义泛型! 继承于:" + OsCacheBase.class.getCanonicalName()
					+ "的类都必需声明所操作实体的泛型, 如:\npublic class " + clsName
					+ " extends " + OsCacheBase.class.getSimpleName() + "<KeyType, ValueType"
					+ "> " + "{\n\t...\n}");
		}
	}
    @SuppressWarnings("unchecked")
	public OsCacheBase(String cacheName) {
        osCacheManager = OsCacheDataSource.getInstanceCacheManager();
		if (osCacheManager.isExistKey(cacheName)) {
			this.cacheName = cacheName;
			osCacheManager.putValue(cacheName, 
					new HashMap<String, T>());
		} else {
			throw new RuntimeException("已存在缓存名称" + cacheName + "请更改cacheName！");
		}

		try {
			@SuppressWarnings("unused")
			Class<T> entityClassT = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[1];
		} catch (Exception e) {
			String clsName = getClass().getSimpleName();
			throw new RuntimeException(getClass().getCanonicalName()
					+ "未定义泛型! 继承于:" + OsCacheBase.class.getCanonicalName()
					+ "的类都必需声明所操作实体的泛型, 如:\npublic class " + clsName
					+ " extends " + OsCacheBase.class.getSimpleName() + "<KeyType, ValueType"
					+ "> " + "{\n\t...\n}");
		}
     }
	@SuppressWarnings("unchecked")
	public void delete(String key) {
		// TODO Auto-generated method stub
		getValueFromDataSource().remove(key);
	}

	public T get(String key) {
		// TODO Auto-generated method stub
		return getValueFromDataSource().get(key);
	}

	public void put(String key, T value) {
		// TODO Auto-generated method stub
		getValueFromDataSource().put(key, value);
	}
	
	public boolean isExistKey(String key) {
		if (getValueFromDataSource().containsKey(key)) {
			return true;
		} else {
			return false;
		}
	}
    
	public boolean isExistValue(T value) {
		if (getValueFromDataSource().containsValue(value)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteAll() {
		// TODO Auto-generated method stub
		osCacheManager.putValue(cacheName, new HashMap<String, T>());
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, T> getValueFromDataSource() {
		return (Map<String, T>) osCacheManager.getValue(cacheName);
	}
	
	public boolean deleteWithReplay(String key) {
		// TODO Auto-generated method stub
		delete(key);
		return true;
	}

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
	
}
