package com.fantingame.game.gamecenter.partener.fantin.util;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fantingame.game.gamecenter.partener.fantin.service.EucAPIException;
import com.fantingame.game.gamecenter.partener.fantin.service.JBean;
import com.fantingame.game.gamecenter.partener.fantin.service.JBody;
import com.fantingame.game.gamecenter.partener.fantin.service.JDesc;
import com.fantingame.game.gamecenter.partener.fantin.service.JHead;
import com.fantingame.game.gamecenter.partener.fantin.service.JReason;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {

	private static Type headType = new TypeToken<JHead>() {}.getType();
	private static Type bodyType = new TypeToken<Map<String, JsonElement>>() {}.getType();
	private static Type descType = new TypeToken<List<JReason>>() {}.getType();
	private static Type beanType = new TypeToken<Map<String, JsonElement>>() {}.getType();
	
	private static Gson gson = new GsonBuilder()
			.registerTypeAdapter(Object.class, new NaturalDeserializer())
			.registerTypeAdapter(JsonElement.class, new JElementDeserializer())
			.create();

	public static String toJson(Object src) {
		return gson.toJson(src);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		return (T)gson.fromJson(json, clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, Type typeOfT) {
		return (T)gson.fromJson(json, typeOfT);
	}
	
	public static <T> T fromJson(JsonElement json, Class<T> clazz) {
		return (T)gson.fromJson(json, clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromJson(JsonElement json, Type typeOfT) {
		return (T)gson.fromJson(json, typeOfT);
	}
	
	public static JBean extraJsonBean(String json) throws EucAPIException {
		try {
			JBean jbean = new JBean();
			Map<String, JsonElement> beanMap =GsonUtil.fromJson(json, beanType);
			JsonElement headElement = beanMap.get("head");
			JHead head = GsonUtil.fromJson(headElement, headType);
			jbean.setHead(head);
			JsonElement descElement = beanMap.get("desc");
			if(null!=descElement) {
				LinkedList<JReason> descList= GsonUtil.fromJson(descElement, descType);
				JDesc desc = new JDesc();
				for (Iterator<JReason> iterator = descList.iterator(); iterator.hasNext();) {
					JReason jReason = iterator.next();
					desc.add(jReason);
				}
				jbean.setDesc(desc);
			}
			JsonElement bodyElement = beanMap.get("body");
			if(null!=bodyElement) {
				Map<String, JsonElement> bodyMap = GsonUtil.fromJson(bodyElement, bodyType);
				JBody body = new JBody();
				if(null!=bodyMap) {
					body.putAll(bodyMap);
				}
			jbean.setBody(body);
			}
			return jbean;
		} catch(Exception e) {
			throw new EucAPIException("返回json错误");
		}
	}
}
