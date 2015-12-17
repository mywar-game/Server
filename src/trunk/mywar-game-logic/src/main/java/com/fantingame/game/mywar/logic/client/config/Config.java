package com.fantingame.game.mywar.logic.client.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Config {

	private static Config cfg;
	private String signKey;
	private Properties prop;
	private String ip;
	private String port;

	public static Config ins() {
		synchronized (Config.class) {
			if (cfg == null) {
				cfg = new Config();
			}
		}

		return cfg;
	}

	private Config() {
		try {
			initConfig();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void initConfig() throws IOException {
		prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource(
				"webconfig.properties"));
		signKey = prop.getProperty("signKey", "test");
		ip = prop.getProperty("ip", "127.0.0.1");
		port = prop.getProperty("port", "9090");
	}

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
