package com.fantingame.game.common.dao.staticdata;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.game.common.utils.Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class StaticDataDaoBaseT<K,V> extends StaticDataDaoBase {
	private Map<K,V> data;
	private Class<V> entityClass;
	@Autowired
	private Jdbc jdbcConfig;
	
	@SuppressWarnings("unchecked")
	public StaticDataDaoBaseT(){
		Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        entityClass = (Class<V>) params[1];  
	}
	
	@Override
	public void startup() throws Exception {
		initCache();
	}
	@Override
	public void reload() {
		initCache();
	}
	protected Map<K,V> getAllDataMap(){
		return data;
	}
	protected V getValue(K key){
		return data.get(key);
	}
	@SuppressWarnings("unchecked")
	protected V[] getAllValueArray(){
		return (V[])(data.values().toArray());
	}
	
	protected Collection<V> values(){
		return data.values();
	}
	
	protected List<V> getValueList(){
		List<V> result = Lists.newArrayList();
		for(V v:data.values()){
			result.add(v);
		}
		return result;
	}
	private void initCache(){
		List<V> list = getAllDBData();
		 Map<K,V> dataTemp = Maps.newConcurrentMap();
		 for(V v:list){
			 if(dataTemp.containsKey(getCacheKey(v))){
				throw new RuntimeException("对象"+entityClass.getSimpleName()+"指定的key不是唯一的,是否要改成继承StaticDataDaoBaseListT?，不唯一的key="+getCacheKey(v)); 
			 }
			 dataTemp.put(getCacheKey(v), v);
		 }
		 data = dataTemp;
	}
	protected List<V> getAllDBData(){
		String table = Utils.classNameToTableName(entityClass.getSimpleName());
		String sql = "select * from "+table;
		return jdbcConfig.getList(sql, entityClass);
	}
	protected abstract K getCacheKey(V v);
}
