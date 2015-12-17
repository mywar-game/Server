package com.framework.server.io.java;


import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.io.iface.IoFactory;

public class JavaIoFactory implements IoFactory {
    
	public IXInputStream getIXInputStream() {
		// TODO Auto-generated method stub
		return new JavaInput();
	}

	public IXOutStream getIXOutStream() {
		// TODO Auto-generated method stub
		return new JavaOutput();
	}
}
