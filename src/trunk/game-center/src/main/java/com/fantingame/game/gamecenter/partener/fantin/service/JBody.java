package com.fantingame.game.gamecenter.partener.fantin.service;

import java.util.HashMap;

import com.fantingame.game.gamecenter.partener.fantin.util.GsonUtil;
import com.google.gson.JsonElement;

public class JBody extends HashMap<String, Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6605682619731152303L;

	public void putContent(String name,Object obj) {
		put(name, obj);
	}
	
	/**
	 * 注意，该方法只在返回的jbody中使用
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		JsonElement element = (JsonElement)get(key);
		return GsonUtil.fromJson(element, String.class);
	}
	
	/**
	 * 注意，该方法只在返回的jbody中使用
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> T getObject(String key, Class<T> clazz) {
		JsonElement element=(JsonElement)get(key);
		return GsonUtil.fromJson(element, clazz);
	}
}
