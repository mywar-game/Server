package com.fantingame.game.gamecenter.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * sql帮助类
 * 
 * @author jacky
 * 
 */
public class SqlUtil {

	/**
	 * 拼接lsit成字符串 [a, b] => 'a','b'
	 * 
	 * @param list
	 * @return
	 */
	public static String join(List<String> list) {
		List<String> l = new ArrayList<String>();
		for (String s : list) {
			l.add("'" + s + "'");
		}
		return StringUtils.join(l, ",");
	}

	public static String joinInteger(List<Integer> list) {
		List<String> l = new ArrayList<String>();
		for (Integer s : list) {
			l.add(String.valueOf(s));
		}
		return StringUtils.join(l, ",");
	}
}
