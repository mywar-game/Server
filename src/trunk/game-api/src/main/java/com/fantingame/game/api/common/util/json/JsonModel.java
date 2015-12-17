package com.fantingame.game.api.common.util.json;

import java.lang.reflect.ParameterizedType;

import java.lang.reflect.Type;

/**
 * Json对象基类，给对象提供自我转换JSON的功能
 * @author chenjian
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public abstract class JsonModel<T extends JsonModel> {
	//TODO toModel未实现自我转化，只能产生一个新的对象
	public String toJson() {
		return Json.toJson(this);
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> objectClass() {
		Type type = getClass().getGenericSuperclass();
		Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
		return (Class<T>) trueType;
	}
}
