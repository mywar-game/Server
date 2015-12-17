package com.fantingame.game.gamecenter.partener.fantin.service;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * 
 * @author jay
 * @since 2012.10.16
 * @version 1.0
 */
public class ClientConfig {

	/** 默认配置文件路径 */
	private static String DEFAULT_PROPERTIES_PATH = "/client.properties";
	/** 所有参数属性值*/
	private static Properties properties;
	
	static {
		InputStream is = ClientConfig.class
				.getResourceAsStream(DEFAULT_PROPERTIES_PATH);
		if (is != null) {
			properties = new Properties();
			try {
				properties.load(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try{
				throw new Exception("no found the ["+DEFAULT_PROPERTIES_PATH+"] config file...");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key,String defaultVaule){
		return properties.getProperty(key,defaultVaule);
	}
	
	public static void main(String[] args) {
		System.out.println(getProperty("secertKey","1"));
	}

}
