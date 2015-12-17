package com.tool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ParseJason {

	public static Map<String, String> jason2Map(String jasonStr) {

		Map<String, String> map = new HashMap<String, String>();

		try {
			JSONObject jsonObject = new JSONObject(jasonStr);

			Iterator<String> iterator = jsonObject.keys();

			String key = null;
			String value = null;
			while (iterator.hasNext()) {

				key = iterator.next();
				value = jsonObject.getString(key);
				map.put(key, value);

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}
}
