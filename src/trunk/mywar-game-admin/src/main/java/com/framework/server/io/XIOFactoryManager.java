package com.framework.server.io;

import java.util.HashMap;
import java.util.Map;

import com.framework.constant.SystemConstant;
import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.io.iface.IoFactory;
import com.framework.server.io.java.JavaIoFactory;

public class XIOFactoryManager {
    private static Map<Integer, IoFactory> ioMap = new HashMap<Integer, IoFactory>();
    static {
    	ioMap.put(SystemConstant.JAVA_CLIENT, new JavaIoFactory());
    }
    
	
	public static IoFactory getIoFactoryByKey(Integer type) {
		return ioMap.get(type);
	}
	
	public static int getTypeByInputStream(IXInputStream input) {
		return SystemConstant.JAVA_CLIENT;
	}
	
	public static int getTypeByOutputStream(IXOutStream output) {
		return SystemConstant.JAVA_CLIENT;
	}
	
}
