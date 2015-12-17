package com.fantingame.game.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 职位获取工具
 * 
 * @author CJ
 * 
 */

//TODO 职位是否固定？
public class OfficialUtils {
	private static Map<Integer, String> map;
	static {
		map = new HashMap<Integer, String>();
		map.put(0, "普通会员");
		map.put(1, "会长");
	}
	
	public static String getOfficiaName(int officiaId){
		return map.get(officiaId);
	}
}
