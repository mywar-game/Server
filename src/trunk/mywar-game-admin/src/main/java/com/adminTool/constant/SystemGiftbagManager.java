package com.adminTool.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统礼包管理类
 * 
 * @author yezp
 */
public class SystemGiftbagManager {

	private Map<Integer, String> tablePrefixMap;

	public SystemGiftbagManager() {
		tablePrefixMap = new HashMap<Integer, String>();
		tablePrefixMap.put(0, "a");
		tablePrefixMap.put(1, "b");
		tablePrefixMap.put(2, "c");
		tablePrefixMap.put(3, "d");
		tablePrefixMap.put(4, "e");
		tablePrefixMap.put(5, "f");
		tablePrefixMap.put(6, "g");
		tablePrefixMap.put(7, "h");
		tablePrefixMap.put(8, "i");
		tablePrefixMap.put(9, "j");
		tablePrefixMap.put(10, "k");
		tablePrefixMap.put(11, "l");
		tablePrefixMap.put(12, "m");
		tablePrefixMap.put(13, "n");
		tablePrefixMap.put(14, "o");
		tablePrefixMap.put(15, "p");
		tablePrefixMap.put(16, "q");
		tablePrefixMap.put(17, "r");
		tablePrefixMap.put(18, "s");
		tablePrefixMap.put(19, "t");
		tablePrefixMap.put(20, "u");
		tablePrefixMap.put(21, "v");
		tablePrefixMap.put(22, "w");
		tablePrefixMap.put(23, "x");
		tablePrefixMap.put(24, "y");
		tablePrefixMap.put(25, "z");
	}

	public Map<Integer, String> getTablePrefixMap() {
		return tablePrefixMap;
	}

	public void setTablePrefixMap(Map<Integer, String> tablePrefixMap) {
		this.tablePrefixMap = tablePrefixMap;
	}

}