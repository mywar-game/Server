package com.framework.config;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

public class LocalConfig implements ICodeAble {
	// 本机系统编号
	private int systemNum;
	// 系统描述
	private String systemName;
	//系统能负载的最大人数
	private Integer maxPeopleNum;
	// 本机IP地址
	private String ip;
	// 本机socket端口号
	private int port;
	// 本机http接口地址
	private String httpUrl;
    //WebService开放的端口号
	private int webServicePort;
	//WebService开放的端口号
	private String webServiceIp;

	public int getSystemNum() {
		return systemNum;
	}

	public void setSystemNum(int systemNum) {
		this.systemNum = systemNum;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public void decode(IXInputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
		systemNum = inputStream.readInt();
		systemName = inputStream.readUTF();
		maxPeopleNum = inputStream.readInt();
		ip = inputStream.readUTF();
		port = inputStream.readInt();
		httpUrl = inputStream.readUTF();
	}

	public void encode(IXOutStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		outputStream.writeInt(systemNum);
		outputStream.writeUTF(systemName);
		outputStream.writeInt(maxPeopleNum);
		outputStream.writeUTF(ip);
		outputStream.writeInt(port);
		outputStream.writeUTF(httpUrl);
	}

	public Integer getMaxPeopleNum() {
		return maxPeopleNum;
	}

	public void setMaxPeopleNum(Integer maxPeopleNum) {
		this.maxPeopleNum = maxPeopleNum;
	}

	public int getWebServicePort() {
		return webServicePort;
	}

	public void setWebServicePort(int webServicePort) {
		this.webServicePort = webServicePort;
	}

	public String getWebServiceIp() {
		if (webServiceIp == null || webServiceIp.equals("")) {
			return ip;
		}
		return webServiceIp;
	}

	public void setWebServiceIp(String webServiceIp) {
		this.webServiceIp = webServiceIp;
	}


}
