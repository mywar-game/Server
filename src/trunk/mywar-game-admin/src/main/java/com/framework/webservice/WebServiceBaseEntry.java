package com.framework.webservice;


import javax.xml.ws.Endpoint;

import com.framework.config.LocalTools;
import com.framework.log.LogSystem;
import com.framework.plugin.IAppPlugin;
import com.framework.servicemanager.ServiceCacheFactory;

public class WebServiceBaseEntry implements IAppPlugin, IWebServiceBase {

	private String BASE_URL;
	private String address;
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public void startup() {
		// TODO Auto-generated method stub
		BASE_URL = "http://" + LocalTools.getLocalConfig().getWebServiceIp() + ":" + LocalTools.getLocalConfig().getWebServicePort();
		
		Class[] c = this.getClass().getInterfaces();
		Class currentClass = null;
		if (c == null || c.length == 0) {
			throw new NullPointerException("WebService Class must implements 1 interface");
		} else if (c.length > 1) {
			throw new NullPointerException("WebService Class can't implements more than 2 interface");
		} else {
			currentClass = c[0];
		}
	    Object object = ServiceCacheFactory.getServiceCache().getService(currentClass);
		Endpoint.publish(BASE_URL + address, object);
		LogSystem.info("webService publish:" + BASE_URL + address);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
