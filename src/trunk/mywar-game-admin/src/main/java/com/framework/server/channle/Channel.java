package com.framework.server.channle;

public interface Channel {

	public void write(byte[] datas);

	public boolean isClosed();

	public void close();

	public int getProtocolType();
	
	public void setProtocolType(int protocolType);

	public void addAttribute(String key, Object value);

	public Object getAttribute(String key);
	
	public int getClientType();
}
