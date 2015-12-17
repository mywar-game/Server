package com.fantingame.game.gamecenter.partener.fantin.util;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class JElementDeserializer implements JsonDeserializer<JsonElement> {
	public JsonElement deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) {
		if (json.isJsonNull())
			return null;
		else
			return json;
	}
}
