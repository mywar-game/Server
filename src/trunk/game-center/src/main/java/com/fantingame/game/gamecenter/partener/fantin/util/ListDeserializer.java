package com.fantingame.game.gamecenter.partener.fantin.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class ListDeserializer implements JsonDeserializer<List<Object>> {
	public ArrayList<Object> deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) {
		if (json.isJsonNull())
			return null;
		JsonArray array = json.getAsJsonArray();
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.size(); i++) {
			list.add(array.get(i));
		}
		return list;
	}
}
