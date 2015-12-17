package com.example.common;


public class SubClassFactory {

	@SuppressWarnings("unchecked")
	public static Object getInstance(Class c, int uid) {
		int tIndex = uid % 16 + 1;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(c.getPackage().getName());
			buff.append(tIndex);
			buff.append('.');
			buff.append(c.getSimpleName());
			buff.append(tIndex);
			return Class.forName(buff.toString()).newInstance();
		} catch (Exception e) {
			return null;
		}
		
	}

}
