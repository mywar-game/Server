package com.fantingame.game.common.dao.userdata;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class BaseCacheMapDao<E> extends BaseCacheDao<Map<String,E>>{
	/**
	 * 获取map中的key对应的value  如果key1不存在缓存中 则会自动从DB中加载
	 * @param key1   主键key
	 * @param key2 map中对应的元素key
	 * @return
	 */
	protected E getByKey(String key1,String key2){
		Map<String,E> map = super.getEntry(key1);
		if(map!=null){
			return map.get(key2);
		}
		return null;
	}
	/**
	 * 添加一个键值  如果key1不存在缓存中 则会自动从DB中加载
	 * @param key1
	 * @param key2
	 * @param e
	 * @return
	 */
	protected boolean addMapValues(String key1,String key2,E e){
		Map<String,E> map = super.getEntry(key1);
		if(map!=null){
			map.put(key2, e);
		}else{
			map = Maps.newConcurrentMap();
			map.put(key2, e);
			this.addEntry(key1, map);
		}
		return true;
	}
	/**
	 * 获取map中的size  如果key1不存在缓存中 则会自动从DB中加载
	 * @param key
	 * @return
	 */
	protected int getEntrySize(String key){
		Map<String,E> map = super.getEntry(key);
		if(map!=null){
			return map.size();
		}
		return 0;
	}
	/**
	 * 获取所有值的列表  如果key不存在缓存中 则会自动从DB中加载
	 * @param key
	 * @return
	 */
	protected Collection<E> getMapValues(String key){
		Map<String,E> map = super.getEntry(key);
		if(map!=null){
			return map.values();
		}else{
			return null;
		}
	}
	/**
	 * 获取所有值的列表   如果key不存在缓存中 则会自动从DB中加载
	 * @param key
	 * @return
	 */
	protected List<E> getMapList(String key){
		Map<String,E> map = super.getEntry(key);
		if(map!=null){
			List<E> list = Lists.newArrayList();
			for(E e:map.values()){
				list.add(e);
			}
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取所有key  如果key不存在缓存中 则会自动从DB中加载
	 * @param key
	 * @return
	 */
	protected Set<String> getMapKeySet(String key){
		Map<String,E> map = super.getEntry(key);
		if(map!=null){
			return map.keySet();
		}else{
			return null;
		}
	}
	/**
	 * 删除  如果key1不存在缓存中 则会自动从DB中加载
	 * @param key1
	 * @param key2
	 */
	protected void deleteKey(String key1,String key2){
		Map<String,E> map = super.getEntry(key1);
		if(map!=null){
			map.remove(key2);
		}
	}
	/**
	 * 获取map
	 * @param key
	 * @return
	 */
	protected Map<String,E> getMap(String key){
		return super.getEntry(key);
	}
}
