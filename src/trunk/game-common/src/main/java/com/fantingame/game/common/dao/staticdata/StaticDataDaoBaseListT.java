package com.fantingame.game.common.dao.staticdata;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.game.common.utils.Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class StaticDataDaoBaseListT<K,V> extends StaticDataDaoBase {
	private Map<K,List<V>> dataList;
	
	private Class<V> entityClass;
	
	@Autowired
	private Jdbc jdbcConfig;
	@Override
	public void startup() throws Exception {
		initCache();
	}
	@Override
	public void reload() {
		initCache();
	}
	
	@SuppressWarnings("unchecked")
	public StaticDataDaoBaseListT(){
		Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        entityClass = (Class<V>) params[1];  
	}
	
	protected Map<K,List<V>> getDataMap(){
		return dataList;
	}
	protected List<V> getValue(K key){
		return dataList.get(key);
	}
	private void initCache(){
		List<V> list = getAllDBData();
		 Map<K,List<V>> dataListTemp = Maps.newConcurrentMap();
		 for(V v:list){
			 K k = getCacheKey(v);
			 if(dataListTemp.containsKey(k)){
				 dataListTemp.get(k).add(v);
			 }else{
				 List<V> listTemp = Lists.newArrayList();
				 listTemp.add(v);
				 dataListTemp.put(k, listTemp);
				 dataListTemp.put(k,listTemp);
			 }
		 }
		 dataList = dataListTemp;
	}
	protected List<V> getAllDBData(){
		String table = Utils.classNameToTableName(entityClass.getSimpleName());
		String sql = "select * from "+table;
		return jdbcConfig.getList(sql, entityClass);
	}
	protected abstract K getCacheKey(V v);
}
