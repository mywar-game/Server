/**
 * 多玩游戏 ©2005-2010. 多玩通行证系统 udb.duowan.com
 * 
 * @(#)JsonUtil.java V0.0.1 2010-5-21
 */
package com.fantingame.game.api.common.util.json;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author CJ
 */
public class JsonUtil {

	public static String toJson(Map<String, Object> map) {
		return Json.toJson(map);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json) {
		return Json.toObject(json, Map.class);
	}

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public static <T extends JsonModel> List<T> toList(String json, Class<T> c) {
//		if (StringUtils.isEmpty(json)) {
//			return null;
//		}
//		List<T> results = new ArrayList<T>();
//		Map<String, String> map = Json.toObject(json, LinkedHashMap.class);
//		Iterator<String> it = map.values().iterator();
//		while (it.hasNext()) {
//			String value = it.next();
//			T t = Json.toObject(value, c);
//			results.add(t);
//		}
//		return results;
//	}

	@SuppressWarnings("rawtypes")
	public static <T extends JsonModel> String toJson(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			String key = Integer.toString(i);
			String json = t.toJson();
			map.put(key, json);
		}
		String json = Json.toJson(map);
		return json;
	}

	@SuppressWarnings("rawtypes")
	public static <T extends JsonModel> String toJsonOnce(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			String key = Integer.toString(i);
			// String json = t.toJson();
			map.put(key, t);
		}
		String json = Json.toJson(map);
		return json;
	}
}
