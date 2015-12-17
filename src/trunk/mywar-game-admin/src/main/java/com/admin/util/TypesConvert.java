package com.admin.util;

public class TypesConvert {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static byte StringTobyte(String str) throws Exception {
		
		byte b = 0;
		int in = Integer.parseInt(str);
		if (in < -128 || in > 127) {
			throw new Exception();
		}
		b = ((Integer) in).byteValue();
		return b;
	}

	
	public static int StringToInt(String str) {
		if (str == null || str == "") {
			return 0;
		} else {
		return Integer.valueOf(str);
		}
	}
}
