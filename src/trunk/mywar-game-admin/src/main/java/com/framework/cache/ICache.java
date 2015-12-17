package com.framework.cache;

import java.util.Collection;


@SuppressWarnings("hiding")
public interface ICache<T> {
	/**
	 * 添加
	 * @param key
	 * @param value
	 */
	public void put(String key, T value);

	/**
	 * 删除
	 * @param key
	 */
	public void delete(String key);

	/**
	 * 带回复的删除
	 * @param key
	 * @return
	 */
	public boolean deleteWithReplay(String key);
	/**
	 * 查询记录
	 * @param key
	 * @return
	 */
	public T get(String key);
	
	/**
	 * 批量查询记录
	 */
	public Collection<T> gets(Collection<String> keys);
	
	/**
	 * 是否存在key
	 * @return
	 */
	public boolean isExistKey(String key);
	/**
	 * 是否存在value
	 * @param value
	 * @return
	 */
	public boolean isExistValue(T value);
	
}
